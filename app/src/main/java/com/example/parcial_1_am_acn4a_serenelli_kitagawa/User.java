package com.example.parcial_1_am_acn4a_serenelli_kitagawa;

public class User {
    private String userId;
    private String nombre;
    private String apellido;
    private String correo;
    private String nacimiento;
    private String sexo;

    public User() {
        // Constructor vacío necesario para Firestore
    }

    public User(String userId, String nombre, String apellido, String correo, String nacimiento, String sexo) {
        this.userId = userId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.nacimiento = nacimiento;
        this.sexo = sexo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}