/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.m03poosergiopascualsegura;

/**
 *
 * @author 34691
 */
public class ValidadorNIF {
     // Función para validar el NIF
    public static boolean validarNif(String nif) {
        
        // Guarda la letra del NIF en una variable para validarla más adelante
        String letraMayuscula = "";
        
        // Verifica que el NIF tenga 9 caracteres
        if (nif.length() == 9) {
            
            // Obtiene la letra del NIF y la convierte a mayúsculas
            letraMayuscula = nif.substring(8).toUpperCase();
            
            // Verifica que la letra sea una letra mayúscula
            if (letraMayuscula.matches("[A-Z]")) {
                // Obtiene el número del DNI, que son los 8 primeros caracteres del NIF
                String dni = nif.substring(0, 8);
                try {
                    
                    // Convierte el número del DNI a un entero para realizar la validación
                    int dniNum = Integer.parseInt(dni);
                    
                    // Obtiene el resto de la división del DNI por 23, que se usará para obtener la letra del NIF
                    int resto = dniNum % 23;
                    
                    // Crea una cadena con las letras correspondientes a cada resto
                    String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
                    
                    // Obtiene la letra del NIF correspondiente al resto obtenido anteriormente
                    String letraNif = letras.substring(resto, resto + 1);
                    // Compara la letra del NIF obtenida con la letra del NIF del NIF introducido
                    return letraNif.equalsIgnoreCase(letraMayuscula);
                } catch (NumberFormatException e) {
                    // Si el DNI no es un número válido, devuelve falso
                    return false;
                }
            }
        }
        // Si no se cumple alguna de las condiciones anteriores, devuelve falso
        return false;
    }
    
}
