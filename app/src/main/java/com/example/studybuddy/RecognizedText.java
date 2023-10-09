package com.example.studybuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class RecognizedText extends AppCompatActivity {
    private EditText recognizedTextEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognized_text);
        recognizedTextEt = findViewById(R.id.recognizedText);

        String recognizedText = getIntent().getStringExtra("recognized_text_key");
        recognizedTextEt.setText(recognizedText);
    }
}