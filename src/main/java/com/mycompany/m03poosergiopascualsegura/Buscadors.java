/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m03poosergiopascualsegura;

import java.util.ArrayList;

/**
 *
 * @author 34691
 */
public class Buscadors {
     // Función para encontrar casas
    public static Casa buscarCasa(String nif, ArrayList<Casa> casas) {
        
        // Itera sobre el ArrayList de casas
        for (Casa casa : casas) {
            
            // Compara el NIF de la casa con el NIF introducido
            if (casa.getNif().equals(nif)) {
                
                // Si encuentra la casa, la devuelve
                return casa;
            }
        }
        // Si no encuentra la casa, devuelve nulo
        return null;
    }
    
    // Función para encontrar los aparatos
    public static AparellElectric buscarAparell(String descripcio, ArrayList<AparellElectric> aparells) {
        
        // Itera sobre el ArrayList de aparatos
        for (AparellElectric ap : aparells) {
            
            // Compara la descripción del aparato con la descripción introducida (ignorando mayúsculas y minúsculas)
            if (ap.getDescripcio().equalsIgnoreCase(descripcio)) {
                
                // Si encuentra el aparato, lo devuelve
                return ap;
            }
        }
        // Si no encuentra el aparato, devuelve nulo
        return null;
    }
}