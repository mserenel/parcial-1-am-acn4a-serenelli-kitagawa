package com.example.parcial_1_am_acn4a_serenelli_kitagawa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ActivityNuevaReserva3 extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private Button botonMostrarDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_reserva3);

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        botonMostrarDatos = findViewById(R.id.btnMostrarDatos);
        botonMostrarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDatos();
            }
        });
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
                                                                                        String textoBoton = "Usuario: " + userData.getNombre() + " " + userData.getApellido() + "\nRestaurante: " + restaurante.getNombre() + "\nComensales: " + comensales.getCantidad() + "\nFecha: " + fecha + "\nHora: " + hora;
                                                                                        botonMostrarDatos.setText(textoBoton);
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
}
