package com.mewtwo2.settlethescore.pong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.mewtwo2.settlethescore.activities.PongActivity;
import com.mewtwo2.settlethescore.ui.PongView;

public class PongGame {
    Paint paintTopHalf;
    Paint paintBottomHalf;

    Paddle paddleBottom;
    Paddle paddleTop;

    private PongView view;
    private Ball ball;
    private boolean paused;

    public PongGame(PongView view) {
        //Not the cleanest solution, but it works for now
        Paddle.width = view.getWidth() / 8f;
        Paddle.height = view.getHeight() / 80f;
        Paddle.inputRange = view.getHeight() / 3f;
        Ball.radius = view.getWidth()/28f;

        paintBottomHalf = new Paint();
        paintTopHalf = new Paint();
        paintBottomHalf.setColor(Color.rgb(150,128,181));
        paintTopHalf.setColor(Color.rgb(250,242,154));

        Paint paintBottomPaddle = new Paint();
        Paint paintTopPaddle = new Paint();
        paintBottomPaddle.setColor(Color.rgb(90,49,139));
        paintTopPaddle.setColor(Color.rgb(246,218,17));

        this.view = view;
        ball = new Ball(view.getWidth()/2f,view.getHeight()/2f, this);
        paddleBottom = new Paddle(view.getWidth()/2f - Paddle.width/2f,view.getHeight() - Ball.radius*2 - Paddle.height, paintBottomPaddle, this);
        paddleTop = new Paddle(view.getWidth()/2f - Paddle.width/2f, Ball.radius*2, paintTopPaddle, this);

        paused = false;
    }

    public PongView getView()
    {
        return view;
    }

    public void update(float deltaTime) {
        if(paused)
        {
            return;
        }

        ball.update(deltaTime, paddleBottom, paddleTop);
        paddleBottom.update(deltaTime);
        paddleTop.update(deltaTime);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(getView().getLeft(),getView().getHeight()/2f,getView().getRight(),getView().getHeight(), paintBottomHalf);
        canvas.drawRect(getView().getLeft(),getView().getTop(),getView().getRight(),getView().getHeight()/2f, paintTopHalf);

        ball.draw(canvas);
        paddleBottom.draw(canvas);
        paddleTop.draw(canvas);
    }

    void setScore(int playerTop, int playerBottom)
    {
        paused = true;

        try {
            PongActivity a = (PongActivity) getView().getContext();
            a.openResultsActivity(playerBottom, playerTop);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
