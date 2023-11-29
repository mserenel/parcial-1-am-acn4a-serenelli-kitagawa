package com.example.parcial_1_am_acn4a_serenelli_kitagawa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity {

    private String correo;
    private String contrasena;

    public void checkConnectiononClick(View view){
        checkConnection();
    }
    public void checkConnection(){
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        TextView mensaje = findViewById(R.id.mensaje);
        Button button = findViewById(R.id.reitentar);

        if(networkInfo != null && networkInfo.isConnected()){
            Log.i("network_testing","Prueba de red");
            mensaje.setVisibility(View. INVISIBLE);
            button.setVisibility(View.INVISIBLE);
        }else {
            mensaje.setText("Internet no disponible");
            mensaje.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
        }

        Intent intent = getIntent();
        if (intent != null) {
            correo = intent.getStringExtra("correo");
            contrasena = intent.getStringExtra("clave");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkConnection();
    }

    public void OpenActivity(View v) {
        EditText editTextCorreo = findViewById(R.id.correo);
        EditText editTextContrasena = findViewById(R.id.contrasena);


        String correoIngresado = editTextCorreo.getText().toString().trim();
        String contrasenaIngresada = editTextContrasena.getText().toString().trim();

        if (correoIngresado.equals(correo) && contrasenaIngresada.equals(contrasena)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    public void openRegistrationActivity(View v){
        Intent intent = new Intent(this, ActivitySignin.class);
        startActivity(intent);
    }


}
