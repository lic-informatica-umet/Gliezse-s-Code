package com.gliezse.clases;

public class Vendedor extends Empleado {
    double sueldoBase;
    double montoVenta;
    double comisionVenta;

    public Vendedor(String apellido, String dni, String sexo, String legajo, double sueldoBase, double montoVenta, double comisionVenta) {
        super(apellido, dni, sexo, legajo);
        this.sueldoBase = sueldoBase;
        this.montoVenta = montoVenta;
        this.comisionVenta = comisionVenta;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public double getMontoVenta() {
        return montoVenta;
    }

    public void setMontoVenta(double montoVenta) {
        this.montoVenta = montoVenta;
    }

    public double getComisionVenta() {
        return comisionVenta;
    }

    public void setComisionVenta(double comisionVenta) {
        this.comisionVenta = comisionVenta;
    }

    public double getSueldo(){
        double sueldoNeto =  sueldoBase + (montoVenta * (comisionVenta/100));
        int sueldoEntero = (int) (sueldoNeto * 100);
        return (double)(sueldoEntero) / 100;
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "sueldoBase=" + sueldoBase +
                ", montoVenta=" + montoVenta +
                ", comisionVenta=" + comisionVenta +
                ", legajo='" + legajo + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", sexo=" + sexo +
                '}';
    }
}
