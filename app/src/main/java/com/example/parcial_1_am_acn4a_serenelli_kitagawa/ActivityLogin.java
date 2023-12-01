package com.example.parcial_1_am_acn4a_serenelli_kitagawa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


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

    }

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkConnection();
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            String email = currentUser.getEmail();
            Log.i("firebase","Hay usuario");
        }else {
            Log.i("firebase","No hay usuario");
        }
    }

    public void LogIn(String correo, String contrasena) {
        mAuth.signInWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(ActivityLogin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    public void onLogIn(View v){
        EditText correo = findViewById(R.id.correo);
        EditText contrasena = findViewById(R.id.contrasena);

        String emailString = correo.getText().toString();
        String contrasenaString= contrasena.getText().toString();

        if (TextUtils.isEmpty(emailString) || TextUtils.isEmpty(contrasenaString)) {
            Toast.makeText(this, "Correo y contraseña son obligatorios", Toast.LENGTH_SHORT).show();
        } else {
            this.LogIn(emailString,contrasenaString);
        }

    }

    public void openRegistrationActivity(View v){
        Intent intent = new Intent(this, ActivitySignin.class);
        startActivity(intent);
    }


}
