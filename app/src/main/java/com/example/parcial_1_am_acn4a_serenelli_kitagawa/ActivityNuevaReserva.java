package com.example.parcial_1_am_acn4a_serenelli_kitagawa;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class ActivityNuevaReserva extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_reserva);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        Button botonRegresar = findViewById(R.id.botonRegresar);
        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }

        });

        // Agregar OnClickListener a cada ImageView
        ImageView imagen1 = findViewById(R.id.imagen1);
        imagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarImagenSeleccionada("la_parolaccia_001");
            }
        });

        ImageView imagen2 = findViewById(R.id.imagen2);
        imagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarImagenSeleccionada("bistecca");
            }
        });

        ImageView imagen3 = findViewById(R.id.imagen3);
        imagen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarImagenSeleccionada("gourmetporteno");
            }
        });

        ImageView imagen4 = findViewById(R.id.imagen4);
        imagen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarImagenSeleccionada("hardrock");
            }
        });

    }

    private void guardarImagenSeleccionada(String nombreImagen) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            Restaurante restaurante = new Restaurante(nombreImagen);

            db.collection("Restaurante")
                    .document(userId) // Usar el userId como ID del documento
                    .set(restaurante)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "Documento agregado exitosamente!");
                            Intent intent = new Intent(ActivityNuevaReserva.this, ActivityNuevaReserva1.class);
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error al agregar documento", e);
                        }
                    });
        } else {
            Log.e(TAG, "Usuario no autenticado");
        }
    }

    public void volverAMain(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
