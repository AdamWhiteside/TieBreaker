package com.mewtwo2.settlethescore.pong;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.mewtwo2.settlethescore.ui.PongView;

public class PongThread extends Thread {
    public static final float DELTA_MAX = 1f/30f;

    private final SurfaceHolder surfaceHolder;
    private final PongView pongView;
    private float targetFPS;

    private boolean running;

    public PongThread(SurfaceHolder surfaceHolder, PongView pongView, float targetFPS) {
        super();

        this.surfaceHolder = surfaceHolder;
        this.pongView = pongView;
        this.targetFPS = targetFPS;
    }

    public void startThread() {
        running = true;
        start();
    }

    public void stopThread() {
        running = false;
    }


    @Override
    public void run() {
        Canvas canvas;
        long lastFrameTime = System.currentTimeMillis();
        long targetDeltaTime = (long)(1000 / targetFPS);

        while(running) {
            //Calculate time passed between frames in milliseconds.
            long currentFrameTime = System.currentTimeMillis();
            //Calculate delta time in seconds, capped to a maximum value
            float deltaTime = Math.min((currentFrameTime - lastFrameTime)/ 1000f, DELTA_MAX);

            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.pongView.update(deltaTime);
                    this.pongView.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null)
                {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            //Remember what time this frame started.
            lastFrameTime = currentFrameTime;

            //Sleep until next frame should begin.
            long timeRemaining = targetDeltaTime - (System.currentTimeMillis() - currentFrameTime);
            if(timeRemaining > 0.0)
            {
                try {
                    sleep(timeRemaining);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}
