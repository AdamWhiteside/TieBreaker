package com.mewtwo2.settlethescore.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import com.mewtwo2.settlethescore.pong.PongGame;
import com.mewtwo2.settlethescore.pong.PongThread;

public class PongView extends SurfaceView implements SurfaceHolder.Callback {

    private volatile MotionEvent lastEvent;

    PongThread thread;
    PongGame game;

    public PongView(Context context)
    {
        super(context);

        getHolder().addCallback(this);

        setFocusable(true);

    }

    public void update(float deltaTime)
    {
        game.update(deltaTime);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        lastEvent = event;
        return true;
    }

    public MotionEvent getLatestMotionEvent()
    {
        return lastEvent;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        game = new PongGame(this);

        thread = new PongThread(getHolder(), this, getDisplay().getRefreshRate());
        thread.startThread();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.stopThread();
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);

        game.draw(canvas);
    }
}
