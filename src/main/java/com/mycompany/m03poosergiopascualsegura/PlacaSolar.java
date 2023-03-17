/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m03poosergiopascualsegura;

/**
 *
 * @author 34691
 */
public class PlacaSolar {
    private double superficie;
    private double preu;
    private int potencia;

    public PlacaSolar(double superficie, double preu, int potencia) {
        this.superficie = superficie;
        this.preu = preu;
        this.potencia = potencia;
    }
    
    public double getSuperficiePlaca() {
        return superficie;
    }

    public double getPreu() {
        return preu;
    }

    public double getPotencia() {
        return potencia;
    }
    
}
