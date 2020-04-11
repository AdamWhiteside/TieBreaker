package com.mewtwo2.settlethescore.ui;

import android.content.Context;
import android.support.annotation.IntDef;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.widget.Toast;

import com.mewtwo2.settlethescore.PongThread;

public class PongView extends SurfaceView implements SurfaceHolder.Callback {

    PongThread thread;

    public PongView(Context context)
    {
        super(context);

        getHolder().addCallback(this);

        thread = new PongThread(getHolder(), this);
        setFocusable(true);
    }

    public void update()
    {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.startThread();
        Toast.makeText(getContext(), "Surface Created", Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.stopThread();
        Toast.makeText(getContext(), "Surface Destroyed", Toast.LENGTH_SHORT).show();
    }
}
