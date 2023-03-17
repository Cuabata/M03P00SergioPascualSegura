/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m03poosergiopascualsegura;

/**
 *
 * @author 34691
 */
public class AparellElectric {
    private String descripcion;
    private double potencia;
    private boolean encendido;

    public AparellElectric(String descripcio, double potencia) {
        this.descripcion = descripcio;
        this.potencia = potencia;
        this.encendido = false;
    }

    public String getDescripcio() {
        return descripcion;
    }

    public double getPotencia() {
        return potencia;
    }

    public boolean isEncès() {
        return encendido;
    }

    public void encendre() {
        this.encendido = true;
    }

    public void apagar() {
        this.encendido = false;
    }
}
