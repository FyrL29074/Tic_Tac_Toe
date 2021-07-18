package com.fyrl29074.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int score_1 = 0;
    private int score_2 = 0;
    private int counter = 0;
    private boolean draw = true;
    private int turn = 0;
    private int firstTurn = 0;
    TextView TV_score_1;
    TextView TV_score_2;

    public void btn_setOff(Button[][] btns){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                btns[i][j].setClickable(false);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void playerWin(int player_N, Button[][] btns){
        if(player_N == 1){
            this.score_1 += 1;
            this.TV_score_1.setText("Score: " + score_1);
        } else{
            this.score_2 += 1;
            this.TV_score_2.setText("Score: " + score_2);
        }
        Toast.makeText(MainActivity.this, "Player " + player_N + " win", Toast.LENGTH_SHORT).show();
        btn_setOff(btns);

        this.draw = false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TV_score_1 = findViewById(R.id.score_1);
        TV_score_2 = findViewById(R.id.score_2);
        Button reset = findViewById(R.id.reset_game);
        Button[][] btns = new Button[3][3];


        //  Инициализация кнопок
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String s = "TV_" + i + j;
                btns[i][j] = findViewById(getResources().getIdentifier(s, "id", getPackageName()));
            }
        }

        // Назначение кнопкам действия
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button a = btns[i][j];
                a.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {

                        counter++;

                        if(turn % 2 == 0){
                            a.setText("X");
                        }
                        else {
                            a.setText("O");
                        }
                        a.setEnabled(false);
                        turn++;

                        //  Проверки на конец игры
                        int counter_x = 0;
                        int counter_o = 0;

                        //  Победа по строкам
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {

                                CharSequence text = btns[k][l].getText();
                                if ("X".contentEquals(text)) {
                                    counter_x += 1;
                                } else if ("O".contentEquals(text)) {
                                    counter_o += 1;
                                }

                                if(counter_x == 3){
                                    playerWin(1, btns);
                                } else if(counter_o == 3){
                                    playerWin(2, btns);
                                }

                            }
                            counter_x = 0;
                            counter_o = 0;
                        }

                        //  Победа по столбцам
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {

                                CharSequence text = btns[l][k].getText();
                                if ("X".contentEquals(text)) {
                                    counter_x += 1;
                                } else if ("O".contentEquals(text)) {
                                    counter_o += 1;
                                }

                                if(counter_x == 3){
                                    playerWin(1, btns);
                                } else if(counter_o == 3){
                                    playerWin(2, btns);
                                }
                            }
                            counter_x = 0;
                            counter_o = 0;
                        }

                        //  Победа по главной диагонали
                        if(btns[0][0].getText() == "X" && btns[1][1].getText() == "X" && btns[2][2].getText() == "X"){
                            playerWin(1, btns);
                        } else if(btns[0][0].getText() == "O" && btns[1][1].getText() == "O" && btns[2][2].getText() == "O"){
                            playerWin(2, btns);
                        }

                        if(btns[2][0].getText() == "X" && btns[1][1].getText() == "X" && btns[0][2].getText() == "X"){
                            playerWin(1, btns);
                        } else if(btns[2][0].getText() == "O" && btns[1][1].getText() == "O" && btns[0][2].getText() == "O"){
                            playerWin(2, btns);
                        }

                        if(counter == 9 && draw){
                            Toast.makeText(MainActivity.this, "Draw!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }



        reset.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Button a = btns[i][j];
                        a.setText("");
                        a.setEnabled(true);
                        a.setClickable(true);
                        firstTurn++;
                        turn = firstTurn;
                        draw = true;
                        counter = 0;
                    }
                }
            }
        });


    }

}