package com.mewtwo2.settlethescore.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.IntDef;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.widget.Toast;

import com.mewtwo2.settlethescore.PongGame;
import com.mewtwo2.settlethescore.PongThread;

public class PongView extends SurfaceView implements SurfaceHolder.Callback {

    PongThread thread;

    public PongView(Context context)
    {
        super(context);

        getHolder().addCallback(this);

        setFocusable(true);
    }

    public void update(float deltaTime)
    {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new PongThread(getHolder(), this, getDisplay().getRefreshRate());
        thread.startThread();
        Toast.makeText(getContext(), "Surface Created", Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.stopThread();
        Toast.makeText(getContext(), "Surface Destroyed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);

        if(canvas != null) {
            canvas.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setColor(Color.rgb(120, 200, 0));
            canvas.drawRect(100, 100, 200, 200, paint);
        }
    }
}
