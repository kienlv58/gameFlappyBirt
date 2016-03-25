package com.example.kien.flapybird;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Kiên on 3/12/2016.
 */
public class MyView extends View implements View.OnClickListener {
    private GameManager gameManager;
    private boolean IS_RUNING = true;
    private boolean IS_UP = false;
    private MediaPlayer player;

    public MyView(Context context) {
        super(context);
        initView();
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {

        Thread thread = new Thread(runnable);
        thread.start();
        setOnClickListener(this);
        gameManager = null;
        IS_RUNING = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (gameManager != null) {
            gameManager.draw(canvas);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (IS_RUNING) {
                int w = getWidth();
                int h = getHeight();
                if (w > 0) {
                    if (gameManager == null)
                        gameManager = new GameManager(w, h, getContext());
                }
                if (gameManager != null) {
                    IS_RUNING = gameManager.move(IS_UP);
                    IS_UP = false;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }
            MediaPlayer mediaPlayer = MediaPlayer.create(getContext(),R.raw.gameover);
            mediaPlayer.start();
            Message message = new Message();
            handler.sendMessage(message);
        }
    };

    @Override
    public void onClick(View v) {
        IS_UP = true;
        player = MediaPlayer.create(getContext(),R.raw.jump);
        player.start();
    }
    private android.os.Handler handler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            AlertDialog.Builder dialog =  new AlertDialog.Builder(getContext());
            dialog.setMessage("Continous?");
            dialog.setTitle("Game Over");
            dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity activity = (MainActivity) getContext();
                    activity.finish();
                }
            });
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    initView();
                }
            });
            dialog.setCancelable(false);
            dialog.show();
        }
    };
}
