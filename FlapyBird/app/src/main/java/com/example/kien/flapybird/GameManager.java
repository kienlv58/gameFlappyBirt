package com.example.kien.flapybird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Kiên on 3/12/2016.
 */
public class GameManager implements View.OnClickListener{
    private int width;
    private int height;
    private Context context;
    private BirdEnity birt;
    private Random random = new Random();
    private ArrayList<Pillar> arrayPillar =  new ArrayList<>();
    private int cot1,cot2;
    private int score = 0;


    public GameManager(int width, int height,Context context) {
        this.width = width;
        this.height = height;
        this.context = context;
        initGame();

    }
    private void initGame(){
        birt = new BirdEnity(width/3,height/2,1,3,context);
        cot1 = 0;
        cot2 = 1;
        initPillar();
    }
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setTextSize(40);
        paint.setColor(Color.RED);
        canvas.drawText("Score:"+ score,40,40,paint);
        birt.draw(canvas);
        for (int i = 0;i<arrayPillar.size();i++){
            arrayPillar.get(i).draw(canvas);
        }
    }
    private MediaPlayer mediaPlayer;
    public boolean move(boolean isUp){
        boolean isStop = birt.move(height,isUp);
        for (int i = arrayPillar.size()-1;i>=0;i--){
            int result = arrayPillar.get(i).move(width);
            if (result == 2){
                arrayPillar.remove(i);

            }
            if (result == 1 && i%2 == 0){
                initPillar();
            }
        }
        boolean checkTren = birt.getRectF().intersect(arrayPillar.get(cot1).getRectF());
        boolean checkduoi = birt.getRectF().intersect(arrayPillar.get(cot2).getRectF());
        if (checkTren == true || checkduoi == true)
            return false;
        for (int i=0;i<arrayPillar.size();i+=2){
            if (arrayPillar.get(i).isCheck()==false) {
                if (birt.getX() > arrayPillar.get(i).getX()) {
                    cot1 = 2;
                    cot2 = 3;
                    score++;
                    if (mediaPlayer!=null){
                        mediaPlayer.release();
                    }
                    mediaPlayer = MediaPlayer.create(context, R.raw.score);
                    mediaPlayer.start();
                    arrayPillar.get(i).setIsCheck(true);
                }
            }
        }

        return isStop;

    }

    public void initPillar(){
        int a = height/5;//chieu cao cua lo cho chim chui qua
        int min = height/5;//chieu cao toi thieu cua cot
        int max = height-a-min;//chieu cao toi da cua cot
        int hT = random.nextInt((max -min))+min;//tinh chieu cao cot tren
        int hD = height-hT-a;//tinh chieu cao cot duoi
        Pillar tren = new Pillar(width,0,hT,1,context);
        Pillar duoi = new Pillar(width,height-hD,hD,1,context);
        arrayPillar.add(tren);
        arrayPillar.add(duoi);

    }
    @Override
    public void onClick(View v) {
    }
}
