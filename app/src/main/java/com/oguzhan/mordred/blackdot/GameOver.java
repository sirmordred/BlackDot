package com.oguzhan.mordred.blackdot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mordred on 07.03.2017.
 */

public class GameOver extends Activity {
    private int scoreCurrent;
    private int scoreHighest;
    private boolean recordBool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            scoreCurrent = extras.getInt("currScore", 0);
            scoreHighest = extras.getInt("hScore", 0);
            recordBool = extras.getBoolean("hScorePassed", false);
        }

        TextView txtCurrScore = (TextView) findViewById(R.id.textView2);
        TextView txtHighScore = (TextView) findViewById(R.id.textView3);
        TextView txtNewRecord = (TextView) findViewById(R.id.textView5);

        txtCurrScore.setText("SCORE: " + String.valueOf(scoreCurrent));
        txtHighScore.setText("HIGHSCORE: " + String.valueOf(scoreHighest));
        if(recordBool) {
            txtNewRecord.setText("CONGRATULATIONS !");
        }

        Button restartButton = (Button) findViewById(R.id.button3);
        // if button is clicked, close the custom dialog
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOver.this, GameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button scoreButton = (Button) findViewById(R.id.button4);
        // if button is clicked, close the custom dialog
        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GameOver.this, HighScore.class);
                Bundle extras = new Bundle();
                extras.putInt("hScoreGlob", scoreHighest);
                extras.putBoolean("hScorePassedGlob", recordBool);
                i.putExtras(extras);
                startActivity(i);
                finish();
            }
        });

        Button exitButton = (Button) findViewById(R.id.button2);
        // if button is clicked, close the custom dialog
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
