package com.example.connect3game;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //0:cross 1:ring
    int activePlayer =0;
    int[] gameState ={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;
    public  void dropin(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter =Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive){
        gameState[tappedCounter]= activePlayer;
        counter.setTranslationY(-1500);

        if (activePlayer==0){
            counter.setImageResource(R.drawable.black_cross);
        activePlayer=1;
        }
        else{
            counter.setImageResource(R.drawable.black_ring);
            activePlayer=0;
        }
        counter.animate().translationYBy(1500).setDuration(300);
        for(int[] winningPosition:winningPositions){
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&& gameState[winningPosition[1]]==gameState[winningPosition[2]]&& gameState[winningPosition[0]]!=2) {
                //someone has won
                gameActive = false;
                String winner;
                if (activePlayer == 1) {
                    winner = "Cross";
                } else {
                    winner = "Ring";
                }

                Toast.makeText(this, winner + " has won!", Toast.LENGTH_LONG).show();
                Button playAgainButton = (Button) findViewById(R.id.button);
                TextView winnerTextView = (TextView) findViewById(R.id.textView);
                winnerTextView.setText(winner + " has won!");
                playAgainButton.setVisibility(View.VISIBLE);
                winnerTextView.setVisibility(View.VISIBLE);
            }
            }
        }
    }




    public void playAgain(View v){
        Log.i("Info","Button is clicked");
        Button playAgainButton = (Button) findViewById(R.id.button);
            TextView winnerTextView = (TextView) findViewById(R.id.textView);

            playAgainButton.setVisibility(View.INVISIBLE);
            winnerTextView.setVisibility(View.INVISIBLE);
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
                ImageView counter = (ImageView) gridLayout.getChildAt(i);
                counter.setImageDrawable(null);
            }
            activePlayer = 0;
            for (int j = 0; j < gameState.length; j++) {
                gameState[j] = 2;
            }

            gameActive = true;
        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Button playAgainButton = (Button) findViewById(R.id.button);
//        playAgainButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
////    public void playAgain(View view){
//
//                TextView winnerTextView = (TextView) findViewById(R.id.textView);
//                playAgainButton.setVisibility(View.INVISIBLE);
//                winnerTextView.setVisibility(View.INVISIBLE);
//                GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
//                for (int i = 0; i < gridLayout.getChildCount(); i++) {
//                    ImageView counter = (ImageView) gridLayout.getChildAt(i);
//                    counter.setImageDrawable(null);
//                }
//                activePlayer = 0;
//                for (int j = 0; j < gameState.length; j++) {
//                    gameState[j] = 2;
//                }
//
//                gameActive = true;
//            }
//        });
    }
}