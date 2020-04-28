package com.mewtwo2.settlethescore.pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

class Ball {
    static float radius;

    private Paint paint;
    private PongGame game;
    private float xPos;
    private float yPos;
    private float xVel;
    private float yVel;

    Ball(float xPos, float yPos, PongGame game) {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        this.game = game;

        this.xPos = xPos;
        this.yPos = yPos;

        this.yVel = game.getView().getHeight()/5f;
    }

    void update(float deltaTime, Paddle padBottom, Paddle padTop)
    {
        xPos += xVel * deltaTime;
        yPos += yVel * deltaTime;

        if (yVel > 0)
        {
            if(yPos + radius > padBottom.yPos && xPos + radius > padBottom.xPos && xPos - radius < padBottom.xPos + Paddle.width)
            {
                yVel *= -1.1f;
                xVel *= 1.1f;
            }
        }
        else
        {
            if(yPos - radius < padTop.yPos + Paddle.height && xPos + radius > padTop.xPos && xPos - radius < padTop.xPos + Paddle.width)
            {
                yVel *= -1.1f;
                xVel *= 1.1f;
            }
        }
    }

    void draw(Canvas canvas) {
        canvas.drawCircle(xPos,yPos,radius,paint);
    }
}
