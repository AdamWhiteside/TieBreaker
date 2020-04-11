package com.mewtwo2.settlethescore;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.mewtwo2.settlethescore.ui.PongView;

public class PongThread extends Thread {
    public static Canvas canvas;

    private final SurfaceHolder surfaceHolder;
    private final PongView pongView;

    private boolean running;

    public PongThread(SurfaceHolder surfaceHolder, PongView pongView) {
        super();

        this.surfaceHolder = surfaceHolder;
        this.pongView = pongView;
    }

    public void startThread() {
        start();
        running = true;
    }

    public void stopThread() {
        try {
            running = false;
            join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(running) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.pongView.update();
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
        }
    }
}
