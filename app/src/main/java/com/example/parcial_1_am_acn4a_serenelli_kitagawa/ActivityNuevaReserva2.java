package com.example.parcial_1_am_acn4a_serenelli_kitagawa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class ActivityNuevaReserva2 extends AppCompatActivity {

    TextView tv;
    private Button botonGuardar;
    TextView tv2;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_reserva2);

        tv = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        botonGuardar = findViewById(R.id.botonGuardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarFechaHoraSeleccionadas();
            }
        });
    }

    public void abrirCalendario(View view) {
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(ActivityNuevaReserva2.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                tv.setText(fecha);
            }
        }, anio, mes, dia);
        dpd.show();
    }

    public void abrirReloj(View view) {
        Calendar cal = Calendar.getInstance();
        int hora = cal.get(Calendar.HOUR_OF_DAY);
        int minutos = cal.get(Calendar.MINUTE);

        TimePickerDialog tdp = new TimePickerDialog(ActivityNuevaReserva2.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String horaSeleccionada = hourOfDay + ":" + minute;
                tv2.setText(horaSeleccionada);
            }
        }, hora, minutos, false);
        tdp.show();
    }

    private void guardarFechaHoraSeleccionadas() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();

            String fecha = tv.getText().toString();
            String hora = tv2.getText().toString();

            Fecha fechaHora = new Fecha(fecha, hora);

            db.collection("Dia y horario")
                    .document(userId)
                    .set(fechaHora)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(ActivityNuevaReserva2.this, "Fecha y hora asignada", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ActivityNuevaReserva2.this, ActivityMisReservas.class);
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            Toast.makeText(ActivityNuevaReserva2.this, "Error al guardar la reserva", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(ActivityNuevaReserva2.this, "Usuario no autenticado", Toast.LENGTH_SHORT).show();
        }
    }

}
