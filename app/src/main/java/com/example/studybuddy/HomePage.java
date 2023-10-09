package com.example.studybuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class HomePage extends AppCompatActivity {
    private Button nextActivityButton; // UI view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        nextActivityButton = findViewById(R.id.nextActivityButton);
        nextActivityButton = (Button) findViewById(R.id.nextActivityButton);
        nextActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOCR();
            }
        });
    }

    private void openOCR() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}