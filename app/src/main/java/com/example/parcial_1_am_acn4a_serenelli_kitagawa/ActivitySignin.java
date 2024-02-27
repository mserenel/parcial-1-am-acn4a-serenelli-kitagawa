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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivitySignin extends AppCompatActivity {

    EditText nombre;
    EditText apellido;
    EditText correo;
    EditText nacimiento;
    EditText contrasena;

    Spinner sexo;

    private DatabaseReference User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        Spinner sexoSpinner = findViewById(R.id.sexo); // Cambio de nombre a sexoSpinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sexo, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexoSpinner.setAdapter(adapter);

        User= FirebaseDatabase.getInstance().getReference("User"); // Asignar la referencia de la base de datos

        nombre=(EditText) findViewById(R.id.nombre);
        apellido=(EditText) findViewById(R.id.apellido);
        correo=(EditText) findViewById(R.id.correo);
        nacimiento=(EditText) findViewById(R.id.nacimiento);
        contrasena=(EditText) findViewById(R.id.contrasena);
        sexo=(Spinner) findViewById(R.id.sexo);
    }


    public void registrarClase(){
        String RegistroSexo = sexo.getSelectedItem().toString();
        String RegistroNombre = nombre.getText().toString();
        String RegistroApellido = apellido.getText().toString();
        String RegistroCorreo = correo.getText().toString();
        String RegistroContrasena = contrasena.getText().toString();
        String nacimientoStr = nacimiento.getText().toString();

        if(TextUtils.isEmpty(RegistroCorreo)){
            Toast.makeText(this,"Clase no registrada",Toast.LENGTH_LONG).show();
        }else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date RegistroDate = null;
            try {
                RegistroDate = dateFormat.parse(nacimientoStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String id = User.push().getKey();
            User UsuarioBD = new User(RegistroNombre, RegistroApellido, RegistroCorreo, RegistroContrasena, RegistroSexo, RegistroDate);

            User.child(id).setValue(UsuarioBD)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ActivitySignin.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.e("FirebaseError", "Error al guardar usuario: " + task.getException().getMessage());
                                Toast.makeText(ActivitySignin.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
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
