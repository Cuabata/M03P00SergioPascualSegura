/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m03poosergiopascualsegura;

import java.util.ArrayList;
import java.util.List;

public class Casa {

    private String nif;
    private String nom;
    private int superficie;
    private boolean interruptorGeneral;
    private ArrayList<PlacaSolar> plaquesSolars;
    private ArrayList<AparellElectric> aparellsElectrics;

    public Casa(String nif, String nom, int superficie) {
        this.nif = nif;
        this.nom = nom;
        this.superficie = superficie;
        this.interruptorGeneral = true; // Activado por defecto
        this.plaquesSolars = new ArrayList<>();
        this.aparellsElectrics = new ArrayList<>();
    }
    
    // Función que retorna el NIF
    public String getNif() {
        return nif;
    }
    
    // Función que retorna el nombre
    public String getNom() {
        return nom;
    }
    
    // Función que retorna la superficie
    public double getSuperficieCasa() {
        return superficie;
    }

    // Función que devuelve la superficie disponible en la casa para instalar nuevas placas solares.
    public double getSuperficieDisponible() {
        // Se inicializa la superficie total con la superficie de la casa
        double superficieTotal = this.superficie;

        // Se itera por cada placa solar instalada en la casa y se resta su superficie a la superficie total
        for (PlacaSolar placa : plaquesSolars) {
            superficieTotal -= placa.getSuperficiePlaca();
        }

        // Se devuelve la superficie disponible
        return superficieTotal;
    }
    
    // Función que retorna si el interruptor general está activado o no.
    public boolean isInterruptorGeneral() {
        return interruptorGeneral;
    }
    
    // Función que activa el interruptor general.
    public void activarInterruptorGeneral() {
        this.interruptorGeneral = true;
    }
    
    // Función que desactiva el interruptor general.
    public void desactivarInterruptorGeneral() {
        this.interruptorGeneral = false;
    }
    
    // Función que añade una placa solar a la lista de placas solares de la casa.
    public void addPlacaSolar(PlacaSolar placa) {
        this.plaquesSolars.add(placa);
    }
    
    // Función que retorna la lista de placas solares de la casa.
    public List<PlacaSolar> getPlaquesSolars() {
        return this.plaquesSolars;
    }
    
    // Función que devuelve la potencia total de todas las placas solares instaladas en la casa
    public double getPotenciaPlacas() {
        //inicializa la potencia total a cero
        double potenciaTotal = 0;
        //itera sobre todas las placas solares instaladas
        for (PlacaSolar placa : plaquesSolars) { 
            //suma la potencia de cada placa a la potencia total
            potenciaTotal += placa.getPotencia(); 
        }
        //devuelve la potencia total
        return potenciaTotal; 
    }
    
    // Función que devuelve la inversión total de todas las placas solares instaladas en la casa
    public double getInversionPlacasSolares() {
        //inicializa la inversion total a cero
        double inversionTotal = 0;
        //itera sobre todas las placas solares instaladas
        for (PlacaSolar placa : plaquesSolars) {
            //suma el precio de cada placa al precio total
            inversionTotal += placa.getPreu();
        }
        //devuelve el precio total
        return inversionTotal;
    }
    
    // Función que añade un nuevo aparato eléctrico a la lista de aparatos eléctricos de la instancia de la clase.
    public void addAparellElectric(AparellElectric aparell) {
        this.aparellsElectrics.add(aparell);
    }
    
    // Función que devuelve la lista de todos los aparatos eléctricos que se encuentran en la instancia de la clase.
    public ArrayList<AparellElectric> getAparellsElectrics() {
        return this.aparellsElectrics;
    }
    
    // Función que devuelve la potencia total de todoss los aparatos electricos instaladas en la casa
    public double getPotenciaAparellsEncesos() {
        //inicializa la potencia total a cero
        double potenciaTotal = 0;
        //itera sobre todas los aparatos instaladas
        for (AparellElectric ap : aparellsElectrics) {
            //suma la potencia de cada aparato a la potencia total
            if (ap.isEncès()) {
                potenciaTotal += ap.getPotencia();
            }
        }
        //devuelve la potencia total
        return potenciaTotal;
    }
    
    // Función que apaga todos los aparatos eléctricos que estén encendidos en la casa.
    public void apagarAparells() {
        // Se recorre la lista de aparatos eléctricos de la casa
        for (AparellElectric ap : aparellsElectrics) {
            // Si el aparato está encendido
            if (ap.isEncès()) {
                // Se apaga el aparato
                ap.apagar();
            }
        }
    }

    // Función que obtiene una lista con los aparatos eléctricos encendidos en la casa
    public List<AparellElectric> getAparellsEncesos() {
        // Se crea una lista vacía para almacenar los aparatos encendidos
        ArrayList<AparellElectric> aparatosEncendidos = new ArrayList<AparellElectric>();

        // Se recorre la lista de aparatos eléctricos de la casa
        for (AparellElectric aparato : aparellsElectrics) {
            // Si el aparato está encendido, se agrega a la lista de aparatos encendidos
            if (aparato.isEncès()) {
                aparatosEncendidos.add(aparato);
            }
        }

        // Se devuelve la lista de aparatos encendidos
        return aparatosEncendidos;
    }
    
    // Función que calcula el consumo actual de la casa
    public int getConsumActual() {
        // Inicializamos la variable consumActual a 0
        int consumActual = 0;
        // Recorremos todos los aparatos eléctricos de la casa
        for (AparellElectric aparato : aparellsElectrics) {
            // Si el aparato está encendido, sumamos su potencia al consumo actual
            if (aparato.isEncès()) {
                consumActual += aparato.getPotencia();
            }
        }
        // Devolvemos el consumo actual de la casa
        return consumActual;
    }
}
