package com.oguzhan.mordred.blackdot;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

/**
 * Created by mordred on 01.03.2017.
 */

public class GameAnimationThread extends Thread {
    private static final long FPS = 30;
    private GameView view;
    private boolean running = false;

    public GameAnimationThread(GameView view) {
        this.view = view;
    }

    public boolean getRunning() {
        return running;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    @SuppressLint("WrongCall")
    @Override
    public void run() {
        while (running) {
            Canvas c = null;
            long startTime = System.currentTimeMillis();
            try {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    if (c != null) {
                        view.onDraw(c);
                    }
                }
            } finally {
                if (c != null) {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
            long sleepTime = (1000 / FPS) - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0) {
                    sleep(sleepTime);
                }
            } catch (Exception e) {}
        }
    }
}