package com.oguzhan.mordred.blackdot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends Activity {
    public GameView gameview = null;
    public TextView txt1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

        if(gameview == null) {
            gameview = (GameView) findViewById(R.id.paint_view);
        }

        txt1 = (TextView) findViewById(R.id.score_table);

        if(gameview != null) {
            gameview.setOnTouchListener(gameview);
            gameview.setTextview(txt1);
        }

        gameview.setCustomEventListener(new GameView.OnCustomEventListener() {
            public void onEvent() {
                //start activity for result
                Intent i = new Intent(GameActivity.this, GameOver.class);
                Bundle extras = new Bundle();
                extras.putInt("currScore", gameview.getCurrentScore());
                extras.putInt("hScore", gameview.getHighScore());
                extras.putBoolean("hScorePassed", gameview.getRecordState());
                i.putExtras(extras);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //do your job
        if(gameview != null) {
            gameview.stopGame();
        }
        if(gameview.getRecordState()) {
            gameview.saveHighScore(gameview.getHighScore());
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
