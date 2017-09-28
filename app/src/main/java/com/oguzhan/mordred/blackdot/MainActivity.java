package com.oguzhan.mordred.blackdot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by mordred on 06.03.2017.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context cntxt = getApplicationContext();

        Button playButton = (Button) findViewById(R.id.button);
        // if button is clicked, close the custom dialog
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cntxt, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}
