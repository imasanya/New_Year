package ru.samsung.newyear;

import android.util.DisplayMetrics;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;


public class Snowflake {

    private ImageView img;
    int x, y;
    int size;
    int speed;
    int screenWidth;
    int screenHeight;

    Snowflake(ConstraintLayout layout, int scWidth, int screenHeight){
        this.screenWidth = scWidth;
        this.screenHeight = screenHeight;
        this.size = rand.nextInt(100) + 75;
        this.speed = rand.nextInt(6) + 6;
        this.x = rand.nextInt(screenWidth);
        this.y = -rand.nextInt(screenHeight)-100;

        img = new ImageView(layout.getContext());
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(size, size);
        img.setLayoutParams(params);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.setImageResource(R.drawable.snowflake_photo);
        img.setY(y);
        img.setX(x);
        layout.addView(img);

    }

    Snowflake(ConstraintLayout layout, int scWidth,int screenHeight, int x, int y){
        this.screenWidth = scWidth;
        this.size = rand.nextInt(100) + 75;
        this.speed = rand.nextInt(6) + 10;
        this.x = x;
        this.y = y;

        img = new ImageView(layout.getContext());
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(size, size);
        img.setLayoutParams(params);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.setImageResource(R.drawable.snowflake_photo);
        img.setY(y);
        img.setX(x);
        layout.addView(img);

    }

    Random rand = new Random();

    void move(){
        if (img.getY() < screenHeight){
            img.setY(img.getY()+speed);
        }
        else if(img.getY() > screenHeight){
            img.setY(-rand.nextInt(screenHeight)-100);
            img.setX(rand.nextInt(screenWidth));
        }
    }



}
