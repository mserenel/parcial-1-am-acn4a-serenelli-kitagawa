package com.example.parcial_1_am_acn4a_serenelli_kitagawa;

public class Fecha {
    private String fecha;
    private String hora;

    public Fecha() {
        // Constructor vacío necesario para Firestore
    }

    public Fecha(String fecha, String hora) {
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
