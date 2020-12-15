package com.example.trashsolution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Map extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        String usr_id = intent.getStringExtra("usr_id");
        //OOO님 환영합니다!
        String usr_password = intent.getStringExtra("usr_password");
        Toast.makeText(getApplicationContext(), usr_id+"님, 환영합니다!", Toast.LENGTH_LONG).show();
    }
}