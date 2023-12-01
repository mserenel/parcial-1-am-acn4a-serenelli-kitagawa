package com.example.parcial_1_am_acn4a_serenelli_kitagawa;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivitySignin extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Spinner sexo = findViewById(R.id.sexo);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sexo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sexo.setAdapter(adapter);

    }

    private FirebaseAuth mAuth;
    public void SignIn(String correo, String contrasena) {
        mAuth.createUserWithEmailAndPassword(correo, contrasena).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // La sesión se inició correctamente
                    Toast.makeText(ActivitySignin.this, "Registro existoso", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
                    startActivity(intent);
                } else {
                    // La sesión no se inició correctamente
                    Toast.makeText(ActivitySignin.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public void openInicioActivity(View v) {
        Intent intent = new Intent(this, ActivityLogin.class);
        startActivity(intent);
    }

    public void onSignIn(View v) {
        EditText correo = findViewById(R.id.correo);
        EditText contrasena = findViewById(R.id.contrasena);

        String emailString = correo.getText().toString();
        String contrasenaString = contrasena.getText().toString();

        if (TextUtils.isEmpty(emailString) || TextUtils.isEmpty(contrasenaString)) {
            Toast.makeText(this, "Correo y contraseña son obligatorios", Toast.LENGTH_SHORT).show();
        } else {
            this.SignIn(emailString, contrasenaString);
        }
    }
}
