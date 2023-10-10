package com.example.studybuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RecognizedText extends AppCompatActivity {
    private EditText recognizedTextEt;
    private static final String API_KEY = "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";
    private static final String API_URL = "https://api.openai.com/v1/engines/davinci/completions";
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognized_text);
        recognizedTextEt = findViewById(R.id.recognizedText);

        String recognizedText = getIntent().getStringExtra("recognized_text_key");
        String prompt = "Create flashcards from this: " + recognizedText;
        callAPI(prompt);

    }

    void addFlashcards(String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recognizedTextEt.setText(response);
            }
        });
    }


    void callAPI(String question) {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model", "text-davinci-003");
            jsonBody.put("prompt", question);
            jsonBody.put("max_tokens", 3921);
            jsonBody.put("temperature", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(jsonBody.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .header("Authorization", "Bearer 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")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addFlashcards("Failed to load response due to " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getString("text");
                        addFlashcards(result.trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    addFlashcards("Failed to load response due to: " + response.body().string());
                }
            }
        });
    }
}