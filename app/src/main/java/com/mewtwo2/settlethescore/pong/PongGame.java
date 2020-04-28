package com.mewtwo2.settlethescore.pong;

import android.graphics.Canvas;
import android.view.View;

import com.mewtwo2.settlethescore.ui.PongView;

public class PongGame {
    private PongView view;

    private Ball ball;
    Paddle paddleBottom;
    Paddle paddleTop;

    public PongGame(PongView view) {
        //Not the cleanest solution, but it works for now
        Paddle.width = view.getWidth() / 8f;
        Paddle.height = view.getHeight() / 80f;
        Paddle.inputRange = view.getHeight() / 3f;
        Ball.radius = view.getWidth()/28f;

        this.view = view;
        ball = new Ball(view.getWidth()/2f,view.getHeight()/2f, this);
        paddleBottom = new Paddle(view.getWidth()/2f - Paddle.width/2f,view.getHeight() - 50 - Paddle.height,this);
        paddleTop = new Paddle(view.getWidth()/2f - Paddle.width - Ball.radius + 1, 50f, this);
    }

    public PongView getView()
    {
        return view;
    }

    public void update(float deltaTime) {
        ball.update(deltaTime, paddleBottom, paddleTop);
        paddleBottom.update(deltaTime);
        paddleTop.update(deltaTime);
    }

    public void draw(Canvas canvas) {
        ball.draw(canvas);
        paddleBottom.draw(canvas);
        paddleTop.draw(canvas);
    }
}
