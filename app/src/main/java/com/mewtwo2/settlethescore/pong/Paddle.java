package com.mewtwo2.settlethescore.pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;

class Paddle {
    static float width;
    static float height;
    static float inputRange;

    Paint paint;
    PongGame game;
    float xPos;
    float yPos;

    Paddle(float xPos, float yPos, Paint paint, PongGame game)
    {
        this.paint = paint;
        this.game = game;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    void update(float deltatime)
    {
        MotionEvent e = game.getView().getLatestMotionEvent();

        if(e == null)
        {
            return;
        }

        switch (e.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < e.getPointerCount(); i++) {
                    float x = e.getX(i);
                    float y = e.getY(i);

                    if(Math.abs(y - yPos) < inputRange)
                    {
                        xPos = x - width/2f;
                    }
                }
        }
    }

    void draw(Canvas canvas)
    {
        canvas.drawRect(xPos, yPos, xPos+width, yPos+height, paint);
    }
}
