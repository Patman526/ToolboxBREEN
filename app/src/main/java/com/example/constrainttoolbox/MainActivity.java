package com.example.constrainttoolbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

import static android.graphics.Color.rgb;

public class MainActivity extends AppCompatActivity {
    private boolean wasRunning = false;
    private boolean running = false;
    private int redVal = 000;
    private int greenVal = 000;
    private int blueVal = 000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner dropdown = findViewById(R.id.dropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dropdown, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        if (savedInstanceState != null) {
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
            redVal = savedInstanceState.getInt("redVal");
            greenVal = savedInstanceState.getInt("greenVal");
            blueVal = savedInstanceState.getInt("blueVal");
        }
        runColorChange();
    }

    public void setColor(View v){
        Spinner dropdown = findViewById(R.id.dropdown);
        ConstraintLayout layout = findViewById(R.id.layout);

        //String chosen = dropdown.getSelectedItem().toString();

        layout.setBackgroundColor(rgb(255, 0, 0));
    }

    public void colorsOn(View v){
        running = true;
    }

    public void colorsOff(View v){
        running = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        running = wasRunning;
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning)
            running = true;
    }

    private void runColorChange() {
        final CheckBox checkRed = findViewById(R.id.checkBox1);
        final CheckBox checkGreen = findViewById(R.id.checkBox2);
        final CheckBox checkBlue = findViewById(R.id.checkBox3);
        final ConstraintLayout layout = findViewById(R.id.layout);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run(){
                Boolean red = checkRed.isChecked();
                Boolean green = checkGreen.isChecked();
                Boolean blue = checkBlue.isChecked();

                if(red && redVal < 255){
                    redVal++;
                }

                if(green && greenVal < 255){
                    greenVal++;
                }

                if(blue && blueVal < 255){
                    blueVal++;
                }

                layout.setBackgroundColor(rgb(redVal, greenVal, blueVal));

                handler.postDelayed(this, 10);
            }
        });
    }
}
