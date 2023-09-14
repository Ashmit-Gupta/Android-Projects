package com.ashmit.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        //PLAYER REPRESENTATION

    // O - X
    // 1 - 0
    int activeplayer = 0;
    boolean gameActive = true;
    int[] gamestate = {2,2,2,2,2,2,2,2,2};

    //Game State Meaning
//    0 -> X
//    1 -> 0
//    2 -> NULL

    int [][] WinPositions = {
            //Horizontal
            {0,1,2},
            {3,4,5},
            {6,7,8},
            //Vertical
            {0,3,6},
            {1,4,7},
            {2,5,8},
            //Diagonal
            {0,4,8},
            {2,4,6}
    };
    public void Player_Tap(View view){

        ImageView img = (ImageView) view;//Image view ek class h uska ek obj banaya img vo lega view jo hum pass karegeny and usko typecast kar diya humne usse

        int TappedImage =Integer.parseInt(img.getTag().toString()) ;//((String)img.getTag())
        //(0,1,2,3,4,5,6,7,8 ) lega jo tag dale h humne view mai vo lega ye and then usko store karega tappedimage mai,
        // java mai hum string ko int mai convert karne ke liye Integer.parseInt use karna padrega
        //.getTag() tag fetch karne ke liye kaam ayega and Integer.parseInt String leta h isley isko .tostring mai h

        if (!gameActive) {
            gameReset(view);//This will Reset the game
        }

        if(gamestate[TappedImage] == 2 ){//Iska mtlb h jagah khali h
            gamestate[TappedImage] = activeplayer;// vaha pe humne active player rakh diya tha ,0-->X

//            img.setTranslationX(-1000f);//ye usko upar bhej dega agar Y h toh

            //Uske baad 44 ke baad 48 run hoga and isme dusre player ki turn dengey
            if (activeplayer == 0){//upar humne activeplayer lo 0 set kiya h !
                img.setImageResource(R.drawable.x); // vaha pe X laga dengey cause humne upar x set nhi kiya h X ko Uski jagah pe laga dega
                activeplayer = 1; // and phir usko next player ke liye set karengey

                img.setTranslationX(-1000f);//ye usko upar bhej dega agar X h toh
                img.animate().translationXBy(1000f).setDuration(300);//ye usko uski position pe le aagyega but with animation

                TextView status = findViewById(R.id.status);
                status.setText(" O's Turn, Tap To Play");
            }
            else{
                activeplayer = 0;
                img.setImageResource(R.drawable.o);

                img.setTranslationY(-1000f);//ye usko upar bhej dega agar Y h toh
                img.animate().translationYBy(1000f).setDuration(300);//ye usko uski position pe le aagyega but with animation


                TextView status = findViewById(R.id.status);
                status.setText(" X's Turn , Tap To Play");


            }

//            Checking If any player has won or not !!

            for (int[] WinPosition: WinPositions) {
                if(gamestate[WinPosition[0]] == gamestate[WinPosition[1]] && gamestate[WinPosition[1]] == gamestate[WinPosition[2]]  &&gamestate[WinPosition[0]] != 2   ){
                    //This means that someone has won!!!!!
                    //now fond who has won
                    String Winner;
                    gameActive = false;
                    if (gamestate[WinPosition[0]] == 0) {
                        Winner = " X has Won ";
                        TextView Status = findViewById(R.id.status);
                        Status.setText(Winner);
                        //Update the status for Winner Announcement
                    }

                    else if(gamestate[WinPosition[0]] == 1){
                        Winner = " O has Won ";
                        TextView Status = findViewById(R.id.status);
                        Status.setText(Winner);
                        //Update the status for Winner Announcement
                    }

                    else{
                        Winner = "Match Draw";
                        TextView Status = findViewById(R.id.status);
                        Status.setText(Winner);
                        gameReset(view);
                    }



                }

            }

        }

//        img.animate().translationXBy(1000f).setDuration(300);//ye usko uski position pe le aagyega but with animation



    }

    public void gameReset(View view) {
        gameActive = true;
        activeplayer = 0;
        for (int i = 0; i <gamestate.length ; i++) {

            gamestate[i] = 2;
        }


        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView Status = findViewById(R.id.status);
        Status.setText("Reseted");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}