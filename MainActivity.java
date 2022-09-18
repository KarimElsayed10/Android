package com.example.x_o_game;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private Button buttons [][]=new Button[3][3];
    private  boolean player1Turn=true;
    private  int roundCount;
    private int player1Points;
    private int player2Points;
    private int getPlayer2Points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewPlayer1=findViewById(R.id.textView1);
        textViewPlayer2=findViewById(R.id.textView2);
        for(int i=0 ;i<3 ;i++) {
            for(int j=0; j<3; j++){
                String ButtonID="button_"+i+j;
                int resID=getResources().getIdentifier(ButtonID,"id",getPackageName());
                buttons[i][j]=findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button buttonReset=findViewById(R.id.Button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });
    }
    @Override
    public void onClick(View view) {
        if(!((Button) view).getText().toString().equals("")){
            return;
        }
        if (player1Turn){
            ((Button) view).setText("X");
        }
        else {
            ((Button) view).setText("O");
        }

        roundCount++;
        if(chickForWon()) {
            if (player1Turn) {
                player1win();
            } else {
                player2win();
            }
        }
        else if (roundCount==9){
            draw();
        }
        else {
            player1Turn=!player1Turn;
        }
    }
    private boolean chickForWon() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }
    private void player1win(){

        Toast.makeText(MainActivity.this, "kareem wins", Toast.LENGTH_LONG).show();
        UpdatePointsText();
        resetBoard();
    }
    private void player2win(){
        player2Points++;
        Toast.makeText(MainActivity.this, "pot wins", Toast.LENGTH_LONG).show();
        UpdatePointsText();
        resetBoard();
    }
    private void draw(){
        Toast.makeText(MainActivity.this, "Draw", Toast.LENGTH_LONG).show();

        resetBoard();
    }
    private void UpdatePointsText(){
        textViewPlayer1.setText("kareem:"+player1Points);
        textViewPlayer2.setText("pot:"+player2Points);

    }
    private void resetBoard(){
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount=0;
        player1Turn=true;
    }
}
