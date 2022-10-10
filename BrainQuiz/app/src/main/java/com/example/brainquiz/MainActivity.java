package com.example.brainquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView questionTextView;
    TextView resultTextView ;
    Random rand = new Random();
    int n ,m;
    boolean isGameOn;
    androidx.gridlayout.widget.GridLayout gridLayout;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    int correct;
    int numerator,denominator;
    String numeratorString;
    String denominatorString;
     int tag;

    String ss;

    public void onClick(View view){
        TextView doneTextView = (TextView) findViewById(R.id.doneTextView);
        Button playAgainButton =(Button) findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);
        doneTextView.setVisibility(View.INVISIBLE);
        numerator=0;
        denominator=0;
         correct=0;
        isGameOn = true;
        Button goButton = (Button) findViewById(R.id.goButton);
        TextView timerTextView =(TextView) findViewById(R.id.timerTextView);
        questionTextView =(TextView) findViewById(R.id.questionTextView);
        resultTextView =(TextView) findViewById(R.id.resultTextView);



        resultTextView.setText("0/1");

        goButton.setVisibility(View.INVISIBLE);
         gridLayout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);
        gridLayout.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        questionTextView.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);

        CountDownTimer countDownTimer= new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {

                timerTextView.setText("0:"+(int)(l/1000));
            }

            @Override
            public void onFinish() {
                Log.i("Finish","finished!!");
                playAgainButton.setVisibility(View.VISIBLE);
                doneTextView.setVisibility(View.VISIBLE);
                isGameOn=false;
            }
        }.start();

        int start1 = rand.nextInt(20);
        int start2 = rand.nextInt(20);
        buttonWork(start1,start2);
        String strStart = start1 + "+"+start2;
        questionTextView.setText(strStart);


    }
    public void onGridClick1(View view) {
        ++denominator;
        denominatorString = String.valueOf(denominator);
        Button counter = (Button) view;
        tag =Integer.parseInt(counter.getTag().toString());
        if(correct==tag){
            ++numerator;
        }
        numeratorString = String.valueOf(numerator);
        ss=numeratorString+"/"+denominatorString;
        resultTextView.setText(ss);


        if(isGameOn){
        n = rand.nextInt(20);
        m = rand.nextInt(20);
        String str = n + "+" + m;
        questionTextView.setText(str);
         buttonWork(n,m);
        Log.i("Click", "Item Clicked");

        }
    }

    public void onGridClick2(View view) {
        ++denominator;
        denominatorString = String.valueOf(denominator);
        Button counter = (Button) view;
        tag =Integer.parseInt(counter.getTag().toString());
        if(correct==tag){
            ++numerator;
        }
        numeratorString = String.valueOf(numerator);
        ss=numeratorString+"/"+denominatorString;
        resultTextView.setText(ss);


        if(isGameOn){
            n = rand.nextInt(20);
            m = rand.nextInt(20);
            String str = n + "+" + m;
            questionTextView.setText(str);
            buttonWork(n,m);
            Log.i("Click", "Item Clicked");

        }
    }

    public void onGridClick3(View view) {
        ++denominator;
        denominatorString = String.valueOf(denominator);
        Button counter = (Button) view;
        tag =Integer.parseInt(counter.getTag().toString());
        if(correct==tag){
            ++numerator;
        }
        numeratorString = String.valueOf(numerator);
        ss=numeratorString+"/"+denominatorString;
        resultTextView.setText(ss);


        if(isGameOn){
            n = rand.nextInt(20);
            m = rand.nextInt(20);
            String str = n + "+" + m;
            questionTextView.setText(str);
            buttonWork(n,m);
            Log.i("Click", "Item Clicked");

        }
    }

    public void onGridClick4(View view) {
        ++denominator;
        denominatorString = String.valueOf(denominator);
        Button counter = (Button) view;
        tag =Integer.parseInt(counter.getTag().toString());
        if(correct==tag){
            ++numerator;
        }
        numeratorString = String.valueOf(numerator);
        ss=numeratorString+"/"+denominatorString;
        resultTextView.setText(ss);


        if(isGameOn){
            n = rand.nextInt(20);
            m = rand.nextInt(20);
            String str = n + "+" + m;
            questionTextView.setText(str);
            buttonWork(n,m);
            Log.i("Click", "Item Clicked");

        }
    }



     public void buttonWork(int w,int x){
          button1 = (Button) findViewById(R.id.button1);
          button2 = (Button) findViewById(R.id.button2);
         button3 = (Button) findViewById(R.id.button3);
          button4 = (Button) findViewById(R.id.button4);
         int a = rand.nextInt(4);
         int b = rand.nextInt(40);
         int c = rand.nextInt(40);
         int d= rand.nextInt(40);
         String bString = String.valueOf(b);
         String cString = String.valueOf(c);
         String dString = String.valueOf(d);

         if (a==1){
             a+=1;
         }
         if(a==1){
             int buttoner = w+x;
             String buttonerString = String.valueOf(buttoner);
             button1.setText(buttonerString);
             button2.setText(bString);
             button3.setText(cString);
             button4.setText(dString);
             correct=1;
         }
         else if (a==2){

             int buttoner = w+x;
             String buttonerString = String.valueOf(buttoner);
             button2.setText(buttonerString);
             button1.setText(bString);
             button3.setText(cString);
             button4.setText(dString);
             correct=2;
         }
         else if (a==3){
             int buttoner = w+x;
             String buttonerString = String.valueOf(buttoner);
             button3.setText(buttonerString);
             button2.setText(bString);
             button1.setText(cString);
             button4.setText(dString);
             correct=3;
         }
         else {
             int buttoner = w+x;
             String buttonerString = String.valueOf(buttoner);
             button4.setText(buttonerString);
             button2.setText(bString);
             button3.setText(cString);
             button1.setText(dString);
             correct=4;
         }
     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
}