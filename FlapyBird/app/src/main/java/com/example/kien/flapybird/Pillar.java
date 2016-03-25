package com.example.kien.flapybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

/**
 * Created by Kiên on 3/15/2016.
 */
public class Pillar {
    private int x,y;
    private int height;
    private int speed;
    private RectF rectF;
    private Bitmap bitmap;
    private boolean isCheck=false;

    public Pillar(int x, int y, int height, int speed,Context context) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.speed = speed;

        BitmapFactory.Options options =new BitmapFactory.Options();
        options.inSampleSize = 4;
        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.cot,options);
    }
    public void draw(Canvas canvas){
        rectF = new RectF(x,y,x+bitmap.getWidth(),y+height);
        canvas.drawBitmap(bitmap,null,rectF,null);

        Log.e(x+"-"+bitmap.getWidth()+"--",y+"-"+height);
    }
    public int move(int width){
        x = x-speed;
        if (x <= -(bitmap.getWidth()))
            return 2;
        if (x == width/2)
            return 1;
        return 0;
    }

    public RectF getRectF() {
        rectF = new RectF(x,y,x+bitmap.getWidth(),y+height);
        return rectF;
    }

    public int getX() {
        return x+bitmap.getWidth();
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public boolean isCheck() {
        return isCheck;
    }
}
