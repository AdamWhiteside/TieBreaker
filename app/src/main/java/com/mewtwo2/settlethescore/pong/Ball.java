package com.mewtwo2.settlethescore.pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.GridLayout;

class Ball {
    static float radius;

    private Paint paint;
    private PongGame game;
    private float xPos;
    private float yPos;

    private static final float SPEED_MULTIPLIER = 1.2f;
    private float speed;
    private float xVel;
    private float yVel;

    Ball(float xPos, float yPos, PongGame game) {
        paint = new Paint();
        paint.setColor(Color.rgb(246,218,17));
        this.game = game;

        this.xPos = xPos;
        this.yPos = yPos;

        //Manually track speed to avoid distance calculations
        this.speed = game.getView().getHeight()/4f;
        this.xVel = speed;
        this.yVel = speed;
    }

    void update(float deltaTime, Paddle padBottom, Paddle padTop) {
        xPos += xVel * deltaTime;
        yPos += yVel * deltaTime;

        //Collision for Paddles
        if ((yVel > 0 && yPos + radius > padBottom.yPos && xPos + radius > padBottom.xPos && xPos - radius < padBottom.xPos + Paddle.width)) {
            speed *= SPEED_MULTIPLIER;

            float dirX = (xPos- (padBottom.xPos + Paddle.width/2f)) / ((Paddle.width/2f)+Ball.radius);
            float angle = (float)Math.toRadians((90f * Math.max(-.5f, Math.min(.5f,dirX))) - 90f);
            float x = (float)Math.cos(angle);
            float y = (float)Math.sin(angle);

            xVel = x * speed;
            yVel = y * speed;
        } else if (yVel < 0 && yPos - radius < padTop.yPos + Paddle.height && xPos + radius > padTop.xPos && xPos - radius < padTop.xPos + Paddle.width) {
            speed *= SPEED_MULTIPLIER;

            float dirX = (xPos- (padTop.xPos + Paddle.width/2f)) / ((Paddle.width/2f)+Ball.radius);
            float angle = (float)Math.toRadians((90f * Math.max(-.5f, Math.min(.5f,dirX))) - 90f);
            float x = (float)Math.cos(angle);
            float y = -(float)Math.sin(angle);

            xVel = x * speed;
            yVel = y * speed;
        }

        //Collision for Walls
        if(
            (xVel < 0 && xPos - radius < game.getView().getLeft()) ||
            (xVel > 0 && xPos + radius > game.getView().getRight())
        ) {
            xVel *= -1;
        }

        //Scoring
        if (yPos + radius < 0) {
            game.setScore(0,1);
        }
        else if (yPos - radius > game.getView().getHeight()) {
            game.setScore(1,0);
        }
    }

    void draw(Canvas canvas) {
        canvas.drawCircle(xPos,yPos,radius,paint);
    }
}
