package com.example.parcial_1_am_acn4a_serenelli_kitagawa;

public class Restaurante {
    private String nombre;

    public Restaurante() {
        // Constructor vacío requerido por Firestore
    }

    public Restaurante(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}