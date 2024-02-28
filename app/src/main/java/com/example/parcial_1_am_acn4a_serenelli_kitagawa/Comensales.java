package com.example.parcial_1_am_acn4a_serenelli_kitagawa;

public class Comensales {
    private int cantidad;

    public Comensales() {
        // Constructor vacío necesario para Firestore
    }

    public Comensales(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
