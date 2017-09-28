package com.oguzhan.mordred.blackdot;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by mordred on 01.03.2017.
 */

public class GameView extends SurfaceView implements View.OnTouchListener{
    OnCustomEventListener mListener;
    private SurfaceHolder holder;
    private GameAnimationThread gameLoopThread;
    private float xSpeed = 1f / 30;
    private float speedIncrement = xSpeed / 4;

    private ArrayList<Circle> circleList;

    private List<PointerPairs> pairs;

    private Random generator;
    private boolean gameIsRunning = false;
    private int canvasHeight;
    private int canvasWidth;
    private boolean firstRun = true;
    private TextView txtview = null;
    private int currentScore = 0;
    private int highScore;
    private float constant = 299f / 300;

    private SharedPreferences preferences = null;
    private SharedPreferences.Editor editor = null;

    private boolean highScorePassed = false;

    private Activity parentActivity;

    private boolean firstBoot = true;

    private float circleRadius;
    private ListIterator<Circle> circleListIterator;

    public GameView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        parentActivity = (Activity) context;
        Resources r = parentActivity.getResources();
        circleRadius = dp2px(r,30);
        xSpeed = (dp2px(r,1)) / 30;
        speedIncrement = xSpeed / 4;
        xSpeed += speedIncrement;
        gameIsRunning = true;
        gameLoopThread = new GameAnimationThread(this);
        holder = getHolder();
        circleList = new ArrayList<Circle>();

        pairs = Collections.synchronizedList(new ArrayList<PointerPairs>());

        generator = new Random();

        if (preferences == null && context != null) {
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
            if (preferences != null && editor == null) {
                editor = preferences.edit();
            }
        }

        if (preferences != null) {
            highScore = preferences.getInt("lastSavedState", 0);
        }

        if (highScore > 0) {
            firstBoot = false;
        }

        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if(gameLoopThread.getRunning()) {
                    gameLoopThread.setRunning(false);
                }
                if(gameLoopThread.isAlive()) {
                    boolean retry = true;
                    while (retry) {
                        try {
                            gameLoopThread.join();
                            retry = false;
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if(!gameLoopThread.getRunning()) {
                    gameLoopThread.setRunning(true);
                }
                if(gameLoopThread.getState() == Thread.State.NEW) {
                    gameLoopThread.start();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parentActivity = (Activity) context;
        Resources r = parentActivity.getResources();
        circleRadius = dp2px(r,30);
        xSpeed = (dp2px(r,1)) / 30;
        speedIncrement = xSpeed / 4;
        xSpeed += speedIncrement;
        gameIsRunning = true;
        gameLoopThread = new GameAnimationThread(this);
        holder = getHolder();
        circleList = new ArrayList<Circle>();

        pairs = Collections.synchronizedList(new ArrayList<PointerPairs>());

        generator = new Random();

        if (preferences == null && context != null) {
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
            if (preferences != null && editor == null) {
                editor = preferences.edit();
            }
        }

        if (preferences != null) {
            highScore = preferences.getInt("lastSavedState", 0);
        }

        if (highScore > 0) {
            firstBoot = false;
        }

        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if(gameLoopThread.getRunning()) {
                    gameLoopThread.setRunning(false);
                }
                if(gameLoopThread.isAlive()) {
                    boolean retry = true;
                    while (retry) {
                        try {
                            gameLoopThread.join();
                            retry = false;
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if(!gameLoopThread.getRunning()) {
                    gameLoopThread.setRunning(true);
                }
                if(gameLoopThread.getState() == Thread.State.NEW) {
                    gameLoopThread.start();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
    }

    public GameView(Context context) {
        super(context);
        parentActivity = (Activity) context;
        Resources r = parentActivity.getResources();
        circleRadius = dp2px(r,30);
        xSpeed = (dp2px(r,1)) / 30;
        speedIncrement = xSpeed / 4;
        xSpeed += speedIncrement;
        gameIsRunning = true;
        gameLoopThread = new GameAnimationThread(this);
        holder = getHolder();
        circleList = new ArrayList<Circle>();

        pairs = Collections.synchronizedList(new ArrayList<PointerPairs>());

        generator = new Random();

        if (preferences == null && context != null) {
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
            if (preferences != null && editor == null) {
                editor = preferences.edit();
            }
        }

        if (preferences != null) {
            highScore = preferences.getInt("lastSavedState", 0);
        }

        if (highScore > 0) {
            firstBoot = false;
        }

        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if(gameLoopThread.getRunning()) {
                    gameLoopThread.setRunning(false);
                }
                if(gameLoopThread.isAlive()) {
                    boolean retry = true;
                    while (retry) {
                        try {
                            gameLoopThread.join();
                            retry = false;
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if(!gameLoopThread.getRunning()) {
                    gameLoopThread.setRunning(true);
                }
                if(gameLoopThread.getState() == Thread.State.NEW) {
                    gameLoopThread.start();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (canvas == null) {
            return;
        }

        if(firstRun) {
            canvasHeight = canvas.getHeight();
            canvasWidth = canvas.getWidth();

            addCircle(8);
            firstRun = false;
        }


        if (gameIsRunning) {
            //remove circle
            int createCircleNum = 0;
            circleListIterator = circleList.listIterator();
            while(circleListIterator.hasNext()) {
                Circle a = circleListIterator.next();
                synchronized (pairs) {
                    for (int i = 0; i < pairs.size(); i++) {
                        if(insideCircle(pairs.get(i).getXCoordinate(),pairs.get(i).getYCoordinate(),a)) {
                            //pairs.remove(i);
                            circleListIterator.remove();
                            // temp
                            createCircleNum += 1;
                            currentScore += 1;
                            // harder harder harder...
                            xSpeed += (speedIncrement * ((float) Math.pow(constant, currentScore)));

                            updateScoreTable();
                            break;
                        }
                    }
                }
            }

            //add circle
            addCircle(createCircleNum);

            synchronized (pairs) {
                pairs.clear();
            }

            // Update radius
            for(int c = 0; c < circleList.size(); c++) {
                circleList.get(c).setRadius(circleList.get(c).getRadius() + xSpeed);
            }

            // check collision
            mainloop:
            for (int f = 0; f < circleList.size(); f++) {
                for (int g = f + 1; g < circleList.size(); g++) {
                    if (isColliding(circleList.get(f),circleList.get(g))) {
                        circleList.get(f).setColor(Color.RED);
                        circleList.get(g).setColor(Color.RED);
                        gameIsRunning = false;
                        if (firstBoot) {
                            highScorePassed = true;
                            highScore = currentScore;
                            saveHighScore(highScore);
                        } else {
                            if (currentScore > highScore) {
                                highScorePassed = true;
                                highScore = currentScore;
                                saveHighScore(highScore);
                            }
                        }
                        if(mListener!=null)
                            mListener.onEvent();
                        break mainloop;
                    }
                }
            }
        }

        canvas.drawColor(Color.WHITE);

        for(int d = 0; d < circleList.size(); d++) {
            //draw new circles
            canvas.drawCircle(circleList.get(d).getCenterX(), circleList.get(d).getCenterY(), circleList.get(d).getRadius(), circleList.get(d).getPaint());
        }
    }

    public void updateScoreTable() {
        parentActivity.runOnUiThread(new Runnable() {
            public void run() {
                if (txtview != null) {
                    if (firstBoot) {
                        txtview.setText("SCORE: " + String.valueOf(currentScore));
                    } else {
                        txtview.setText("SCORE: " + String.valueOf(currentScore) + "   |   HIGHSCORE: " + String.valueOf(highScore));
                    }
                }
            }
        });
    }

    public void saveHighScore(int scoreHigh) {
        editor.putInt("lastSavedState", scoreHigh);
        editor.commit();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        for (int i = 0; i < event.getPointerCount(); i++) {
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    if (gameIsRunning) {
                        synchronized (pairs) {
                            pairs.add(new PointerPairs(event.getX(i),event.getY(i)));
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_MOVE:
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    public boolean insideCircle(float x, float y, Circle c) {
        float cRadius = c.getRadius();
        return ((((x - c.getCenterX()) * (x - c.getCenterX())) + ((y - c.getCenterY()) * (y - c.getCenterY()))) <= (cRadius * cRadius));
    }

    public boolean isColliding(Circle c1, Circle c2) {
        float distance = (((c1.getCenterX() - c2.getCenterX()) * (c1.getCenterX() - c2.getCenterX())) + ((c1.getCenterY() - c2.getCenterY()) * (c1.getCenterY() - c2.getCenterY())));
        float c1Radius = c1.getRadius();
        float c2Radius = c2.getRadius();
        return distance <= ((c1Radius + c2Radius) * (c1Radius + c2Radius));
    }

    public void addCircle(final int circleNum) {
        if (canvasHeight > 0 && canvasWidth > 0) {
            new AsyncTask<Integer,Void,Void>() {
                @Override
                protected Void doInBackground(Integer... params) {
                    int limit = 0;
                    while(limit < circleNum) {
                        float circleX = generator.nextInt(canvasWidth);
                        float circleY = generator.nextInt(canvasHeight);
                        boolean b = true;
                        for (int t = 0; t < circleList.size(); t++) {
                            // minDistance was 60 as hardcoded
                            // minDistance is 2xcircleRadius so mindistance + circleRadius = circleRadius*3
                            if(!((((circleX - circleList.get(t).getCenterX()) * (circleX - circleList.get(t).getCenterX())) + ((circleY - circleList.get(t).getCenterY()) * (circleY - circleList.get(t).getCenterY()))) >= ((circleList.get(t).getRadius() + (circleRadius*3)) * (circleList.get(t).getRadius() + (circleRadius*3))))) {
                                b = false;
                                break;
                            }
                        }
                        if (b) {
                            circleList.add(new Circle(circleX, circleY, circleRadius));
                            limit += 1;
                        }
                    }
                    return null;
                }
            }.execute(circleNum);
        }
    }

    public float dp2px(Resources resrc, int dp) {
        return (float) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resrc.getDisplayMetrics());
    }

    public void stopGame() {
        if (gameIsRunning) {
            gameIsRunning = false;
        }
    }

    public void resumeGame() {
        if (!gameIsRunning) {
            gameIsRunning = true;
        }
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public boolean getRecordState() {
        return highScorePassed;
    }

    public void setTextview(TextView tv) {
        if (txtview == null) {
            txtview = tv;
        }
        if (txtview != null) {
            if(firstBoot) {
                txtview.setText("SCORE: " + String.valueOf(currentScore));
            } else {
                txtview.setText("SCORE: " + String.valueOf(currentScore) + " | HIGHSCORE: " + String.valueOf(highScore));
            }

        }
    }

    public interface OnCustomEventListener {
        void onEvent();
    }

    public void setCustomEventListener(OnCustomEventListener eventListener) {
        mListener = eventListener;
    }
}
