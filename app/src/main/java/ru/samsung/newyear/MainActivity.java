package ru.samsung.newyear;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public int screenWidth, screenHeight;
    ImageView ded;
    Snowflake[] sf = new Snowflake[75];
    Handler handler;
    Handler handler2;
    long startTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        ConstraintLayout constraintLayout = findViewById(R.id.main);
        handler = new Handler(Looper.getMainLooper());
        handler2 = new Handler(Looper.getMainLooper());
        startTime = System.currentTimeMillis();

        ded = findViewById(R.id.ded);

        ded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ded_move();
            }
        });
        Random rand = new Random();

        for (int i = 0; i < sf.length; i++) {
            sf[i] = new Snowflake(constraintLayout, screenWidth, screenHeight);
        }

        //Snowflake test = new Snowflake(constraintLayout, screenWidth, screenHeight, rand.nextInt(screenWidth), -50);
        update();

    }

    void update(){
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                moveSnowflakes();
                handler.postDelayed(this, 16);
            }
        };
        handler.post(runnable);
    }

    void moveSnowflakes(){
        for (Snowflake snowflake : sf) {
            snowflake.move();
        }
    }
    void ded_move(){
        Runnable run = new Runnable() {
            final int dedXbase = (int) ded.getX();
            @Override
            public void run() {

                if (ded.getX() <= screenWidth && ded.getX() >= dedXbase){
                    ded.setX(ded.getX() + 4);
                    handler2.postDelayed(this, 16);
                    if (ded.getX() >= screenWidth){
                        ded.setX(-50);
                    }
                } else {
                    if (ded.getX() < dedXbase){
                        ded.setX(ded.getX() + 4);
                        if (ded.getX() >= dedXbase){
                            ded.setX(dedXbase);
                        }
                        else handler2.postDelayed(this, 16);
                    }
                }
            }
        };
        handler2.post(run);
        }
    }



