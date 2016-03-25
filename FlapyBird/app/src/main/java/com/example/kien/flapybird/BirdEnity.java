package com.example.kien.flapybird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by Kiên on 3/12/2016.
 */
public class BirdEnity {
    private int x;
    private int y;
    private int speed;
    private int time;
    private int index = 0;

    //hinh anh tai 1 thoi diem bat ky
    private Bitmap bitmap;
    //mang cac hinh anh
    private Bitmap[] arrBitmap = new Bitmap[8];
    private RectF rectF;

    public BirdEnity(int x, int y, int time, int speed, Context context) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.speed = speed;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;//hinh anh se bi giam di 8 lam
        arrBitmap[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird1, options);
        arrBitmap[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird2, options);
        arrBitmap[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird3, options);
        arrBitmap[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird4, options);
        arrBitmap[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird5, options);
        arrBitmap[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird6, options);
        arrBitmap[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird7, options);
        arrBitmap[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bird8, options);
       bitmap = arrBitmap[0];

    }

    public void draw(Canvas canvas) {
        bitmap = arrBitmap[index];
        rectF = new RectF(x, y, x + bitmap.getWidth() / 2, y + bitmap.getHeight() / 2);//*
        canvas.drawBitmap(bitmap, null, rectF, null);//*
//        canvas.drawBitmap(bitmap,x,y,null);
    }

    public boolean move(int h, boolean isUp) {
        if (isUp) {
            y = y-80;
        } else
            y += speed;
        if (y <= 0)
            return false;
        if (y >= (h - bitmap.getHeight() / 6))
            return false;
//        if (y>=(h-bitmap.getHeight())) //*
//            return false;
        if (index <arrBitmap.length-1)
            index++;
        else
            index = 0;
        return true;
    }

    public RectF getRectF() {
        rectF = new RectF(x, y, x + bitmap.getWidth() / 2, y + bitmap.getHeight() / 2);
        return rectF;
    }

    public int getX() {
        return x;
    }


}
