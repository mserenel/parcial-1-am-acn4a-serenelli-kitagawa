package com.example.parcial_1_am_acn4a_serenelli_kitagawa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void OpenReservaActivity(View v){
        Intent intent = new Intent(this, ActivityNuevaReserva.class);
        startActivity(intent);
    }
}