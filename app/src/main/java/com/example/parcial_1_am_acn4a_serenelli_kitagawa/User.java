package com.example.parcial_1_am_acn4a_serenelli_kitagawa;

import java.util.Date;

public class User {

    public User(String nombre, String apellido, String correo, String contrasena, String sexo, Date nacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contrasena = contrasena;
        this.sexo = sexo;
        this.nacimiento = nacimiento;
    }

    String nombre,apellido,correo,contrasena,sexo;
    Date nacimiento;

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getSexo() {
        return sexo;
    }

    public Date getNacimiento() {
        return nacimiento;
    }
}

