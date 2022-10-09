import mediapipe as mp 
import cv2
import numpy as np
import time
from playsound import playsound

try:

	flg = False

	while True:

		cap = cv2.VideoCapture(0)
		cPos = 0
		startT = 0  
		endT = 0
		userSum = 0
		dur = 0
		isAlive = 1
		isInit = False
		cStart, cEnd = 0,0
		isCinit = False
		tempSum = 0
		winner = 0
		inFrame = 0
		inFramecheck = False
		thresh = 180
		ctimer = time.time()
		legflag = False


		# def countdown():
		#     t=3
		#     while t:
		#         mins, secs = divmod(t, 60)
		#         timer = '{:02d}:{:02d}'.format(mins, secs)
		#         print(timer, end="\r")
		#         cv2.putText(mainWin, timer, (100,400), cv2.FONT_HERSHEY_SIMPLEX, 0.8, (0,255,0), 4)
		#         time.sleep(1)
		#         t -= 1

		def calc_sum(landmarkList):
			
			tsum = 0
			for i in range(11, 33):
				tsum += (landmarkList[i].x * 480)

			return tsum

		def calc_dist(landmarkList):
			return (landmarkList[28].y*640 - landmarkList[24].y*640)

		def isVisible(landmarkList):
			if (landmarkList[28].visibility > 0.7) and (landmarkList[24].visibility > 0.7):
				return True
			return False

		mp_pose = mp.solutions.pose
		pose = mp_pose.Pose()
		drawing = mp.solutions.drawing_utils

		im1 = cv2.imread('im1.png')
		im2 = cv2.imread('im2.png')

		currWindow = im1

		while True:

			_, frm = cap.read()
			rgb = cv2.cvtColor(frm, cv2.COLOR_BGR2RGB)
			res = pose.process(rgb)
			frm = cv2.blur(frm, (5,5))
			drawing.draw_landmarks(frm, res.pose_landmarks, mp_pose.POSE_CONNECTIONS)

			if not(inFramecheck):
				try:
					if isVisible(res.pose_landmarks.landmark):
						inFrame = 1 
						inFramecheck = True
					else: 
						inFrame = 0
				except:
					print("You are not visible at all")

			timer = int(120 - (time.time() - ctimer))

			if inFrame == 1:

				if not(isInit):
					
					playsound('greenLight.mp3')
					currWindow = im1
					startT = time.time()
					endT = startT
					dur = np.random.randint(1, 5)
					isInit = True
				
				if (endT - startT) <= dur:
					try:
						
						m = calc_dist(res.pose_landmarks.landmark)
						if m < thresh:
							legflag = True
						if m > thresh and legflag:
							legflag = False
							cPos += 1
							cPos += 1
							cPos += 1

						print("current progress is : ", cPos)
					except:
						print("Not visible")

					endT = time.time()

				else:		

					if cPos >= 100:
						print("WINNER")
						winner = 1

					else:
						if not(isCinit):
							isCinit = True 
							cStart = time.time()
							cEnd = cStart
							currWindow = im2
							playsound('redLight.mp3')
							userSum = calc_sum(res.pose_landmarks.landmark)

					#	time.sleep(1)
						if (cEnd - cStart) <= 3:
							tempSum = calc_sum(res.pose_landmarks.landmark)
							cEnd = time.time()
							if abs(tempSum - userSum) > 150:
								print("DEAD ", abs(tempSum - userSum))
								isAlive = 0

						else:
							isInit = False
							isCinit = False


				cv2.circle(currWindow, ((55 + 6*cPos),280), 15, (0,0,255), -1)

				mainWin = np.concatenate((cv2.resize(frm, (800,400)), currWindow), axis=0)
				
				cv2.putText(mainWin, str(timer), (650,80), cv2.FONT_HERSHEY_SIMPLEX, 2.4, (0,255,0), 4)
				cv2.namedWindow("Main Window", cv2.WINDOW_FREERATIO)
				cv2.setWindowProperty("Main Window", cv2.WND_PROP_FULLSCREEN, cv2.WINDOW_FULLSCREEN)
				cv2.imshow("Main Window", mainWin)
				#cv2.imshow("window", frm)
				#cv2.imshow("light", currWindow)

			else:
				cv2.putText(frm, "Please Make sure you are fully in frame", (20,200), cv2.FONT_HERSHEY_SIMPLEX, 0.8, (0,255,0), 4)
				cv2.namedWindow("window", cv2.WINDOW_FREERATIO)
				cv2.setWindowProperty("window", cv2.WND_PROP_FULLSCREEN, cv2.WINDOW_FULLSCREEN)
				cv2.imshow("window", frm)
				

			if cv2.waitKey(1) == 27:
				flg = True
				break

			if cv2.waitKey(1) == 27 or isAlive == 0 or winner == 1 or timer<=0:
				cv2.destroyAllWindows()
				cap.release()
				break


		if flg:
			break

		frm = cv2.blur(frm, (5,5))

		if isAlive == 0 or timer == 0:
			cv2.putText(frm, "Player Eliminated", (50,200), cv2.FONT_HERSHEY_SIMPLEX, 2, (0,0,255), 4)
			cv2.namedWindow("Main Window", cv2.WINDOW_FREERATIO)
			cv2.setWindowProperty("Main Window", cv2.WND_PROP_FULLSCREEN, cv2.WINDOW_FULLSCREEN)
			cv2.imshow("Main Window", frm)
			playsound('eliminated.mp3')

			


		if winner == 1:
			cv2.putText(frm, "You are Winner", (40,200), cv2.FONT_HERSHEY_SIMPLEX, 2, (150,255,0), 4)
			cv2.namedWindow("Main Window", cv2.WINDOW_FREERATIO)
			cv2.setWindowProperty("Main Window", cv2.WND_PROP_FULLSCREEN, cv2.WINDOW_FULLSCREEN)
			cv2.imshow("Main Window", frm)

		cv2.waitKey(0)
		cv2.destroyAllWindows()

except:
	print("error occured")