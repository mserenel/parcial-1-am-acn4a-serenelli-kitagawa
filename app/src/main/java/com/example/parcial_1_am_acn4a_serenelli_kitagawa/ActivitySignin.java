package com.example.parcial_1_am_acn4a_serenelli_kitagawa;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class ActivitySignin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Spinner sexoSpinner = findViewById(R.id.sexo);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sexo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sexoSpinner.setAdapter(adapter);
    }

    public void SignIn(String correo, String contrasena, String nombre, String apellido, String nacimiento, String sexo) {
        mAuth.createUserWithEmailAndPassword(correo, contrasena).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // La sesión se inició correctamente
                    Toast.makeText(ActivitySignin.this, "Registro existoso", Toast.LENGTH_SHORT).show();

                    // Agregar datos a Firestore
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null) {
                        addUserToFirestore(user.getUid(), nombre, apellido, correo, nacimiento, sexo);
                    }

                    Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
                    startActivity(intent);
                } else {
                    // La sesión no se inició correctamente
                    Toast.makeText(ActivitySignin.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addUserToFirestore(String userId, String nombre, String apellido, String correo, String nacimiento, String sexo) {
        Map<String, Object> user = new HashMap<>();
        user.put("nombre", nombre);
        user.put("apellido", apellido);
        user.put("correo", correo);
        user.put("nacimiento", nacimiento);
        user.put("sexo", sexo);

        db.collection("Users")
                .document(userId)
                .set(user, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Documento agregado exitosamente!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error al agregar documento", e);
                    }
                });
    }

    public void openInicioActivity(View v) {
        Intent intent = new Intent(this, ActivityLogin.class);
        startActivity(intent);
    }

    public void onSignIn(View v) {
        EditText correoEditText = findViewById(R.id.correo);
        EditText contrasenaEditText = findViewById(R.id.contrasena);
        EditText nombreEditText = findViewById(R.id.nombre);
        EditText apellidoEditText = findViewById(R.id.apellido);
        EditText nacimientoEditText = findViewById(R.id.nacimiento);

        String correoString = correoEditText.getText().toString();
        String contrasenaString = contrasenaEditText.getText().toString();
        String nombreString = nombreEditText.getText().toString();
        String apellidoString = apellidoEditText.getText().toString();
        String nacimientoString = nacimientoEditText.getText().toString();
        String sexoString = ((Spinner) findViewById(R.id.sexo)).getSelectedItem().toString();

        if (TextUtils.isEmpty(correoString) || TextUtils.isEmpty(contrasenaString) || TextUtils.isEmpty(nombreString) || TextUtils.isEmpty(apellidoString) || TextUtils.isEmpty(nacimientoString)) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
        } else {
            this.SignIn(correoString, contrasenaString, nombreString, apellidoString, nacimientoString, sexoString);
        }
    }
}
