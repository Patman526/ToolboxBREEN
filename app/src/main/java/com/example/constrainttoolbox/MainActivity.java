package com.example.constrainttoolbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner dropdown = findViewById(R.id.dropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dropdown, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
    }

    public void setColor(View v){
        Spinner dropdown = findViewById(R.id.dropdown);
        ConstraintLayout layout = findViewById(R.id.layout);

        int chosen = (int) dropdown.getSelectedItem();
        layout.setBackgroundColor(0x00 + chosen);
    }

    private void runColorChange() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run(){

                handler.postDelayed(this, 10);
            }
        });
    }
}
