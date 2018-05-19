package com.sama.sqlite;

/**
 * Created by uca on 05-16-18.
 */

public class Estudiante {
    private String  nombre,carnet,nota;

    public Estudiante(String nombre, String dui, String nota) {
        this.nombre = nombre;
        this.carnet = dui;
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
