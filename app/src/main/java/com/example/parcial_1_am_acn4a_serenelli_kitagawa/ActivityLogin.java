package com.example.parcial_1_am_acn4a_serenelli_kitagawa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void OpenActivity(View v){
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openRegistrationActivity(View v){
        Intent intent= new Intent(this, ActivitySignin.class);
        startActivity(intent);
    }

}