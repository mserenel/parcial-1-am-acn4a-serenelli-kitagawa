package com.example.parcial_1_am_acn4a_serenelli_kitagawa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bt1_siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1_siguiente = (Button) findViewById(R.id.siguiente);

        bt1_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Proxima entrega",Toast.LENGTH_LONG).show();
            }
        });

        Spinner sexo=findViewById(R.id.sexo);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.sexo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        sexo.setAdapter(adapter);

        TextView ingresar_datos = findViewById(R.id.ingrese_datos);
        ingresar_datos.setText("INGRESE SUS DATOS");

    }
}