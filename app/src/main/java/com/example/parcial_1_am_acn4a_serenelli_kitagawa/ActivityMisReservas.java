package com.example.parcial_1_am_acn4a_serenelli_kitagawa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ActivityMisReservas extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private TextView textViewDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_reservas);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        textViewDatos = findViewById(R.id.textViewDatos);

        Button botonRegresar = findViewById(R.id.botonRegresar);
        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMisReservas.this, MainActivity.class);
                startActivity(intent);
            }
        });


        mostrarDatos();
    }

    private void mostrarDatos() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            db.collection("Users")
                    .document(userId)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            User userData = documentSnapshot.toObject(User.class);
                            if (userData != null) {
                                db.collection("Restaurante")
                                        .document(userId)
                                        .get()
                                        .addOnSuccessListener(restauranteDocumentSnapshot -> {
                                            if (restauranteDocumentSnapshot.exists()) {
                                                Restaurante restaurante = restauranteDocumentSnapshot.toObject(Restaurante.class);
                                                if (restaurante != null) {
                                                    db.collection("Comensales")
                                                            .document(userId)
                                                            .get()
                                                            .addOnSuccessListener(comensalesDocumentSnapshot -> {
                                                                if (comensalesDocumentSnapshot.exists()) {
                                                                    Comensales comensales = comensalesDocumentSnapshot.toObject(Comensales.class);
                                                                    if (comensales != null) {
                                                                        db.collection("Dia y horario")
                                                                                .document(userId)
                                                                                .get()
                                                                                .addOnSuccessListener(fechaDocumentSnapshot -> {
                                                                                    if (fechaDocumentSnapshot.exists()) {
                                                                                        String fecha = fechaDocumentSnapshot.getString("fecha");
                                                                                        String hora = fechaDocumentSnapshot.getString("hora");
                                                                                        String texto = "Usuario: " + userData.getNombre() + " " + userData.getApellido() + "\nRestaurante: " + restaurante.getNombre() + "\nComensales: " + comensales.getCantidad() + "\nFecha: " + fecha + "\nHora: " + hora;
                                                                                        textViewDatos.setText(texto);
                                                                                    }
                                                                                });
                                                                    }
                                                                }
                                                            });
                                                }
                                            }
                                        });
                            }
                        }
                    });
        }
    }

    public void volverAMenuPrincipal(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
