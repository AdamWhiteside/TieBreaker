package com.mewtwo2.settlethescore.pong;

import android.graphics.Canvas;
import android.view.View;

import com.mewtwo2.settlethescore.activities.PongActivity;
import com.mewtwo2.settlethescore.ui.PongView;

public class PongGame {
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

        this.view = view;
        ball = new Ball(view.getWidth()/2f,view.getHeight()/2f, this);
        paddleBottom = new Paddle(view.getWidth()/2f - Paddle.width/2f,view.getHeight() - 50 - Paddle.height,this);
        paddleTop = new Paddle(view.getWidth()/2f - Paddle.width/2f, 50f, this);

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
