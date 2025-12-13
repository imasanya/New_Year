package ru.samsung.newyear;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
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
    Snowflake[] sf = new Snowflake[10];
    Handler handler;
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
        startTime = System.currentTimeMillis();

        ded = findViewById(R.id.ded);

        Random rand = new Random();

        /*for (int i = 0; i < sf.length; i++) {
            sf[i] = new Snowflake(constraintLayout, screenWidth, screenHeight);
        }*/

        Snowflake test = new Snowflake()
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
}


