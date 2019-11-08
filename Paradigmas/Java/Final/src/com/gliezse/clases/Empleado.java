package com.gliezse.clases;

public class Empleado extends Persona {
    String legajo;

    public Empleado(String apellido, String dni, char sexo, String legajo) {
        super(apellido, dni, sexo);
        this.legajo = legajo;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "legajo='" + legajo + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", sexo=" + sexo +
                '}';
    }
}
