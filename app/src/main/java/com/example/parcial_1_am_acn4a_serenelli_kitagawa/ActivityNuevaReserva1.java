package com.example.parcial_1_am_acn4a_serenelli_kitagawa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ActivityNuevaReserva1 extends Activity {

    private EditText editTextComensales;
    private Button botonGuardar;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_reserva1);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        editTextComensales = findViewById(R.id.comensales);
        botonGuardar = findViewById(R.id.botonGuardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNumeroComensales();
            }
        });

        Button botonRegresar = findViewById(R.id.botonRegresar);
        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void guardarNumeroComensales() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();

            String numeroComensalesStr = editTextComensales.getText().toString();
            if (!numeroComensalesStr.isEmpty()) {
                int numeroComensales = Integer.parseInt(numeroComensalesStr);

                Comensales comensales = new Comensales(numeroComensales);

                db.collection("Comensales")
                        .document(userId)
                        .set(comensales)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ActivityNuevaReserva1.this, "Número de comensales guardado correctamente", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ActivityNuevaReserva1.this, ActivityNuevaReserva2.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ActivityNuevaReserva1.this, "Error al guardar el número de comensales", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Toast.makeText(ActivityNuevaReserva1.this, "Ingrese un número de comensales válido", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(ActivityNuevaReserva1.this, "Usuario no autenticado", Toast.LENGTH_SHORT).show();
        }
    }

}
