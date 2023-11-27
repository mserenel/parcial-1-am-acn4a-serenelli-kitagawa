package com.example.parcial_1_am_acn4a_serenelli_kitagawa;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ActivitySignin extends AppCompatActivity {

    private EditText etNombre, etCorreo, etContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Spinner sexo = findViewById(R.id.sexo);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sexo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sexo.setAdapter(adapter);

        etNombre = findViewById(R.id.nombre);
        etCorreo = findViewById(R.id.correo);
        etContraseña = findViewById(R.id.contrasena);

        Button btnRegistrar = findViewById(R.id.siguiente);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();
            }
        });
    }

    private void registrarUsuario() {
        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String contraseña = etContraseña.getText().toString();

        if (TextUtils.isEmpty(nombre)) {
            etNombre.setError("Ingrese su nombre");
            etNombre.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(correo) || !Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            etCorreo.setError("Ingrese un correo electrónico válido");
            etCorreo.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(contraseña) || contraseña.length() < 6) {
            etContraseña.setError("La contraseña debe tener al menos 6 caracteres");
            etContraseña.requestFocus();
            return;
        }

        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
    }

    public void openInicioActivity(View v) {
        Intent intent = new Intent(this, ActivityLogin.class);
        startActivity(intent);
    }
}
