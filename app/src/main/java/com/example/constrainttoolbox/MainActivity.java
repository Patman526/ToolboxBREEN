package com.example.constrainttoolbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;

import static android.graphics.Color.rgb;

public class MainActivity extends AppCompatActivity {
    private boolean wasRunning = false;
    private boolean running = false;
    private int redVal = 255;
    private int greenVal = 255;
    private int blueVal = 255;

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

        String chosen = dropdown.getSelectedItem().toString();
        String[] colors = chosen.split(",");

        layout.setBackgroundColor(rgb(Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2])));
    }

    public void colorsToggle(View v){
        Switch toggle = findViewById(R.id.switch1);
        Boolean toggleBool = toggle.isChecked();

        if(toggleBool){
            running = true;
        } else {
            running = false;
        }
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
        final RadioButton radio1 = findViewById(R.id.radio1);
        final ConstraintLayout layout = findViewById(R.id.layout);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run(){
                Boolean red = checkRed.isChecked();
                Boolean green = checkGreen.isChecked();
                Boolean blue = checkBlue.isChecked();
                Boolean increase = radio1.isChecked();

                if(running) {
                    if(increase) {
                        if (red && redVal < 255) {
                            redVal++;
                        }

                        if (green && greenVal < 255) {
                            greenVal++;
                        }

                        if (blue && blueVal < 255) {
                            blueVal++;
                        }
                    } else {
                        if (red && redVal > 0) {
                            redVal--;
                        }

                        if (green && greenVal > 0) {
                            greenVal--;
                        }

                        if (blue && blueVal > 0) {
                            blueVal--;
                        }
                    }

                    layout.setBackgroundColor(rgb(redVal, greenVal, blueVal));
                }

                handler.postDelayed(this, 50);
            }
        });
    }
}
