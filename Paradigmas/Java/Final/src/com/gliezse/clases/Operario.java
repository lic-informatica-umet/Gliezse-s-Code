package com.gliezse.clases;

public class Operario extends Empleado {
    double horasMensuales;
    double valorHoras;

    public Operario(String apellido, String dni, char sexo, String legajo, double horasMensuales, double valorHoras) {
        super(apellido, dni, sexo, legajo);
        this.horasMensuales = horasMensuales;
        this.valorHoras = valorHoras;
    }

    public double getHorasMensuales() {
        return horasMensuales;
    }

    public void setHorasMensuales(double horasMensuales) {
        this.horasMensuales = horasMensuales;
    }

    public double getValorHoras() {
        return valorHoras;
    }

    public void setValorHoras(double valorHoras) {
        this.valorHoras = valorHoras;
    }

    public double getSueldo(){
        return horasMensuales * valorHoras;
    }

    @Override
    public String toString() {
        return "Operario{" +
                "horasMensuales=" + horasMensuales +
                ", valorHoras=" + valorHoras +
                ", legajo='" + legajo + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", sexo=" + sexo +
                '}';
    }
}
