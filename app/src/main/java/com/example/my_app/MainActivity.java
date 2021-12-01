package com.example.my_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

   ImageButton button[][]=new ImageButton[3][3];
   ImageView p1Plays;
   ImageView p2Plays;
   ImageView p1Winner;
   ImageView p2Winner;
   ImageView noWinner;
   ImageView firstRowWin;
   ImageView secRowWin;
   ImageView thirdRowWin;
   ImageView firstColWin;
   ImageView secColWin;
   ImageView thirdColWin;
   ImageView diagWin1;
   ImageView diagWin2;
   boolean player1turn=true;

   int roundCount;

   int player1Points;

   int player2Points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String p1PlaysID="player1plays";
        firstRowWin=findViewById(R.id.firstRowWin);
        secRowWin=findViewById(R.id.secRowWin);
        thirdRowWin=findViewById(R.id.thirdRowWin);
        firstColWin=findViewById(R.id.firstColWin);
        secColWin=findViewById(R.id.secColWin);
        thirdColWin=findViewById(R.id.thirdColWin);
        diagWin1=findViewById(R.id.diagWin1);
        diagWin2=findViewById(R.id.diagWin2);
        firstRowWin.setVisibility(View.INVISIBLE);
        secRowWin.setVisibility(View.INVISIBLE);
        thirdRowWin.setVisibility(View.INVISIBLE);
        firstColWin.setVisibility(View.INVISIBLE);
        secColWin.setVisibility(View.INVISIBLE);
        thirdColWin.setVisibility(View.INVISIBLE);
        diagWin1.setVisibility(View.INVISIBLE);
        diagWin2.setVisibility(View.INVISIBLE);
        p1Plays=findViewById(R.id.p1Plays);
        p2Plays=findViewById(R.id.p2plays);
        noWinner=findViewById(R.id.noWinner);
        p1Winner=findViewById(R.id.p1Wins);
        p2Winner=findViewById(R.id.p2Wins);
        noWinner.setVisibility(View.INVISIBLE);
        p1Winner.setVisibility(View.INVISIBLE);
        p2Winner.setVisibility(View.INVISIBLE);
        p2Plays.setVisibility(View.INVISIBLE);
        for(int i=0; i<3;i++){
            for(int j=0;j<3;j++){
                String buttonID="button"+i+j;
                int resID=getResources().getIdentifier(buttonID, "id", getPackageName());
                button[i][j]=findViewById(resID);
                button[i][j].setOnClickListener(this);
                button[i][j].setTag("");
            }
        }
    }

    @Override
    public void onClick(View v) {

        v.setEnabled(false);
        if(player1turn){
            ((ImageButton)v).setImageDrawable(getDrawable(R.drawable.x));
            ((ImageButton)v).setTag("R.drawable.x");
            p2Plays.setVisibility(View.VISIBLE);
            p1Plays.setVisibility(View.INVISIBLE);
        }else{
            ((ImageButton)v).setImageDrawable(getDrawable(R.drawable.o));
            ((ImageButton)v).setTag("R.drawable.o");
            p2Plays.setVisibility(View.INVISIBLE);
            p1Plays.setVisibility(View.VISIBLE);

        }

        roundCount++;
        if(roundCount==9 && !checkForWin()){
            Toast.makeText(this,"It's a draw !", Toast.LENGTH_SHORT).show();
            p1Plays.setVisibility(View.INVISIBLE);
            p2Plays.setVisibility(View.INVISIBLE);
            noWinner.setVisibility(View.VISIBLE);

        }
        if(!checkForWin()){
            player1turn=!player1turn;
        }else{
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    button[i][j].setEnabled(false);
                }
            }
            if (button[0][0].getTag().equals(button[0][1].getTag()) && button[0][0].getTag().equals(button[0][2].getTag()) && !button[0][0].getTag().equals("")){
                    firstRowWin.setVisibility(View.VISIBLE);
                }
            if (button[1][0].getTag().equals(button[1][1].getTag()) && button[1][0].getTag().equals(button[1][2].getTag()) && !button[1][0].getTag().equals("")){
                secRowWin.setVisibility(View.VISIBLE);
            }
            if (button[2][0].getTag().equals(button[2][1].getTag()) && button[2][0].getTag().equals(button[2][2].getTag()) && !button[2][0].getTag().equals("")){
                thirdRowWin.setVisibility(View.VISIBLE);
            }
            if (button[0][0].getTag().equals(button[1][0].getTag()) && button[0][0].getTag().equals(button[2][0].getTag()) && !button[0][0].getTag().equals("")){
                firstColWin.setVisibility(View.VISIBLE);
            }
            if (button[0][1].getTag().equals(button[1][1].getTag()) && button[0][1].getTag().equals(button[2][1].getTag()) && !button[0][1].getTag().equals("")){
                secColWin.setVisibility(View.VISIBLE);
            }
            if (button[0][2].getTag().equals(button[1][2].getTag()) && button[0][2].getTag().equals(button[2][2].getTag()) && !button[0][2].getTag().equals("")){
                thirdColWin.setVisibility(View.VISIBLE);
            }
            if (button[0][0].getTag().equals(button[1][1].getTag()) && button[0][0].getTag().equals(button[2][2].getTag()) && !button[0][0].getTag().equals("")){
                diagWin1.setVisibility(View.VISIBLE);
           }
            if(button[0][2].getTag().equals(button[1][1].getTag()) && button[0][2].getTag().equals(button[2][0].getTag()) && !button[0][2].getTag().equals("")){
                diagWin2.setVisibility(View.VISIBLE);
            }



            announceWinner(player1turn);
            }


        }



    public boolean checkForWin(){
        for(int i=0;i<3;i++) {
            if (button[i][0].getTag().equals(button[i][1].getTag()) && button[i][0].getTag().equals(button[i][2].getTag()) && !button[i][0].getTag().equals("")) {
                Log.d("MyTag", "checkForWin:1 ");
                return true;
            }
        }
        for(int i=0;i<3;i++) {
            if (button[0][i].getTag().equals(button[1][i].getTag()) && button[0][i].getTag().equals(button[2][i].getTag()) && !button[0][i].getTag().equals("")) {
                Log.d("MyTag", "checkForWin:2 ");
                return true;
            }
        }
        if(button[0][0].getTag().equals(button[1][1].getTag()) && button[0][0].getTag().equals(button[2][2].getTag()) && !button[0][0].getTag().equals("")){
            return true;
        }
        if(button[0][2].getTag().equals(button[1][1].getTag()) && button[0][2].getTag().equals(button[2][0].getTag()) && !button[0][2].getTag().equals("")){
            return true;
        }
        return false;
    }


    public void announceWinner(boolean winner){
        if(winner){
            Toast.makeText(this,"Player 1 Wins !", Toast.LENGTH_SHORT).show();
            p1Plays.setVisibility(View.INVISIBLE);
            p2Plays.setVisibility(View.INVISIBLE);
            p1Winner.setVisibility(View.VISIBLE);
        }else{
            Toast.makeText(this,"Player 2 Wins !", Toast.LENGTH_SHORT).show();
            p1Plays.setVisibility(View.INVISIBLE);
            p2Plays.setVisibility(View.INVISIBLE);
            p2Winner.setVisibility(View.VISIBLE);
        }


    }

}