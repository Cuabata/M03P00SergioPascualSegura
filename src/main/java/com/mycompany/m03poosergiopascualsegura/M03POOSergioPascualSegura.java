/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.m03poosergiopascualsegura;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static com.mycompany.m03poosergiopascualsegura.Buscadors.buscarAparell;
import static com.mycompany.m03poosergiopascualsegura.Buscadors.buscarCasa;
import static com.mycompany.m03poosergiopascualsegura.ValidadorNIF.validarNif;
/**
 *
 * @author 34691
 */
public class M03POOSergioPascualSegura {

    public static void main(String[] args) {
        // Inicialización del scanner para leer entradas del usuario
        Scanner scanner = new Scanner(System.in);

        // Inicialización de la lista de casas
        ArrayList<Casa> listaCasas = new ArrayList<>();

        // Variable para almacenar la entrada del usuario
        String input;

        // Bucle que se repite mientras el usuario no escriba "exit" en formato do-While
        do {

            // Mostramos flecha al usuario
            System.out.print("> ");

            // Se lee la entrada del usuario
            input = scanner.nextLine();

            // Se divide la entrada del usuario en varias partes, separadas por espacios
            String[] partes = input.split(" ");

            // Guardamos la primera parte de la entrada
            String parts = partes[0];

            // Utilizamos un switch para identificar qué comando ha introducido el usuario
            switch (parts.toLowerCase()) {

                // Comando para añadir una casa a la lista
                case "addcasa":

                    // Verificar que se han ingresado la cantidad correcta de parámetros
                    if (partes.length != 4) {

                        // Si el número de parámetros es incorrecto, se muestra un mensaje de error
                        System.out.println("ERROR: Número de paràmetres incorrecte.");
                        System.out.println("Ús: addCasa [nif] [nom] [superficie]");
                        break;
                    }

                    // Se extraen los parámetros de la entrada del usuario
                    String nif = partes[1];
                    String nom = partes[2];
                    int superficie = Integer.parseInt(partes[3]);

                    // Se valida el NIF introducido
                    if (!validarNif(nif)) {
                        System.out.println("ERROR: El NIF introduit no es válid.");
                        break;
                    }

                    // Se verifica si ya hay una casa registrada con ese NIF
                    Casa casaExistente = buscarCasa(nif, listaCasas);
                    if (casaExistente != null) {
                        System.out.println("ERROR: Ja hi ha una casa registrada amb aquest nif.");
                        break;
                    }

                    // Se verifica que la superficie de la casa sea mayor que 10
                    if (superficie <= 10) {
                        System.out.println("ERROR: Superfície incorrecta. Ha de ser més gran de 10.");
                        break;
                    }

                    // En caso de que la casa sea apta se crea una nueva instancia de Casa con los parámetros recibidos
                    Casa casa = new Casa(nif, nom, superficie);

                    // Se activa el interruptor general de la casa
                    casa.activarInterruptorGeneral();

                    // Se agrega la nueva casa a la lista de casas
                    listaCasas.add(casa);
                    System.out.println("OK: Casa registrada.");
                    break;

                // Comando para añadir una placa solar a la casa
                case "addplaca":

                    // Verificar que se han ingresado la cantidad correcta de parámetros
                    if (partes.length != 5) {
                        System.out.println("ERROR: Número de paràmetres incorrecte.");
                        System.out.println("Ús: addPlaca [nif] [superficie] [preu] [potència]");
                        break;
                    }

                    // Obtener los parámetros
                    String nifPlaca = partes[1];
                    double superficiePlaca = Double.parseDouble(partes[2]);
                    double preu = Double.parseDouble(partes[3]);
                    int potencia = Integer.parseInt(partes[4]);

                    try {

                        // Buscar la casa correspondiente al NIF ingresado
                        Casa casaBuscada = buscarCasa(nifPlaca, listaCasas);
                        double superficieDisponible = casaBuscada.getSuperficieDisponible();

                        // Verificar que hay suficiente espacio disponible en la casa para instalar la placa
                        if (superficiePlaca > superficieDisponible) {
                            System.out.println("ERROR: No hi ha espai disponible per a instal·lar aquesta placa.");
                            break;
                        }

                        // Verificar que la superficie, el precio y la potencia son valores válidos
                        if (superficiePlaca <= 0) {
                            System.out.println("ERROR: Superfície incorrecta. Ha de ser més gran de 0.");
                            break;
                        }

                        if (preu <= 0) {
                            System.out.println("ERROR: Preu incorrecte. Ha de ser més gran de 0.");
                            break;
                        }

                        if (potencia <= 0) {
                            System.out.println("ERROR: Potència incorrecte. Ha de ser més gran de 0.");
                            break;
                        }

                        // Agregar la placa solar a la casa
                        casaBuscada.addPlacaSolar(new PlacaSolar(superficiePlaca, preu, potencia));
                        System.out.println("OK: Placa afegida a la casa.");
                    } catch (NullPointerException e) {
                        System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");
                    }
                    break;

                // Comando para añadir un aparato electronico a la casa
                case "addaparell":
                    if (partes.length != 4) {

                        // Verificar que se han ingresado la cantidad correcta de parámetros
                        System.out.println("ERROR: Número de paràmetres incorrecte.");
                        System.out.println("Ús: addAparell [nif] [descripció] [potència]");
                        break;
                    }
                    String nifAparell = partes[1];
                    String descripcio = partes[2];
                    double potenciaAparell;
                    Casa casaAparell = buscarCasa(nifAparell, listaCasas);
                    try {

                        // Convierte la potencia del aparato a un double.
                        potenciaAparell = Double.parseDouble(partes[3]);
                    } catch (NumberFormatException e) {

                        // Si la potencia no es un número, muestra mensaje de error y sale del case.
                        System.out.println("ERROR: la potència ha de ser un valor numèric.");
                        break;
                    }
                    if (potenciaAparell <= 0) {

                        // Si la potencia es menor o igual a 0, muestra mensaje de error y sale del case.
                        System.out.println("ERROR: Potència incorrecte. Ha de ser més gran de 0.");
                        break;
                    }
                    if (casaAparell == null) {

                        // Si la casa buscada no existe, muestra mensaje de error y sale del case.
                        System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");
                        break;
                    }
                    boolean existeAparell = false;

                    // Recorre los aparatos eléctricos de la casa buscada para ver si ya existe uno igual.
                    for (AparellElectric a : casaAparell.getAparellsElectrics()) {
                        if (a.getDescripcio().equals(descripcio) && a.getPotencia() == potenciaAparell) {
                            System.out.println("ERROR: Ja existeix un aparell amb aquesta descripció i potència a la casa \nindicada.");
                            existeAparell = true;
                            break;
                        }
                    }
                    if (!existeAparell) {

                        // Si no encontró ningún aparato igual, crea un nuevo AparellElectric y lo agrega a la casa buscada.
                        AparellElectric aparell = new AparellElectric(descripcio, potenciaAparell);
                        casaAparell.addAparellElectric(aparell);
                        System.out.println("OK: Aparell afegit a la casa.");
                    }
                    break;

                // Comando para encender la casa
                case "oncasa":

                    // Verificar que se han ingresado la cantidad correcta de parámetros
                    if (partes.length != 2) {
                        System.out.println("ERROR: Número de paràmetres incorrecte.");
                        System.out.println("Ús: onCasa");
                        break;
                    }
                    // Obtener el NIF de la casa a encender

                    String nifOnCasa = partes[1];
                    // Buscar la casa en la lista de casas registradas

                    Casa casaOn = buscarCasa(nifOnCasa, listaCasas);

                    // Verificar si la casa existe en la lista
                    if (casaOn == null) {
                        System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");
                        break;
                    }

                    // Verificar si el interruptor general de la casa ya está encendido
                    if (casaOn.isInterruptorGeneral()) {
                        System.out.println("ERROR: La casa ja té l'interruptor encès.");
                    } else {
                        // Encender el interruptor general de la casa
                        casaOn.activarInterruptorGeneral();
                        System.out.println("OK: Interruptor general activat.");
                    }
                    break;

                // Comando para encender un aparato electronico
                case "onaparell":

                    // Verificar que se han ingresado la cantidad correcta de parámetros
                    if (partes.length != 3) {
                        System.out.println("ERROR: Número de paràmetres incorrecte.");
                        System.out.println("Ús: onAparell [nif] [descripció aparell]");
                        break;
                    }

                    // Obtén el nif de la casa donde se encuentra el aparato
                    String nifOnA = partes[1];

                    // Obtén la descripción del aparato
                    String descOnA = partes[2];

                    // Busca la casa con el nif correspondiente
                    Casa casaOnA = buscarCasa(nifOnA, listaCasas);

                    // Si no se encuentra ninguna casa, muestra un mensaje de error y sale del case
                    if (casaOnA == null) {
                        System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");
                        break;
                    }

                    // Si el interruptor general de la casa está apagado, muestra un mensaje de error y sale del case
                    if (!casaOnA.isInterruptorGeneral()) {
                        System.out.println("ERROR: No es pot encendre l'aparell. L'interruptor general està apagat.");
                        break;
                    }

                    // Busca el aparato con la descripción correspondiente en la lista de aparatos eléctricos de la casa
                    AparellElectric apOnA = buscarAparell(descOnA, casaOnA.getAparellsElectrics());

                    // Si no se encuentra ningún aparato, muestra un mensaje de error y sale del case
                    if (apOnA == null) {
                        System.out.println("ERROR: No hi ha cap aparell registrat amb aquesta descripció a la casa \nindicada.");
                        break;
                    }

                    /*Si la potencia del aparato más la potencia de los aparatos encendidos supera la potencia máxima
                     de la casa, muestra un mensaje de error y apaga todos los aparatos de la casa*/
                    if (casaOnA.getPotenciaAparellsEncesos() + apOnA.getPotencia() > casaOnA.getPotenciaPlacas()) {
                        System.out.println("ERROR: Han saltat els ploms. La casa ha quedat completament apagada.");
                        casaOnA.desactivarInterruptorGeneral();
                        casaOnA.apagarAparells();
                        break;
                    }

                    // Enciende el aparato 
                    apOnA.encendre();
                    System.out.println("OK: Aparell encès.");
                    break;

                // Comando para apagar un aparato electronico    
                case "offaparell":

                    // Verificar que se han ingresado la cantidad correcta de parámetros
                    if (partes.length != 3) {
                        System.out.println("ERROR: Número de paràmetres incorrecte.");
                        System.out.println("Ús: offAparell [nif] [descripció aparell]");
                        break;
                    }

                    // Obtenemos el NIF y la descripción del aparato a apagar
                    String nifOffA = partes[1];
                    String descOffA = partes[2];

                    // Buscamos la casa correspondiente al NIF proporcionado
                    Casa casaOffA = buscarCasa(nifOffA, listaCasas);
                    if (casaOffA == null) {
                        System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");
                        break;
                    }

                    // Buscamos el aparato correspondiente a la descripción proporcionada en la casa encontrada
                    AparellElectric apOffA = buscarAparell(descOffA, casaOffA.getAparellsElectrics());
                    if (apOffA == null) {
                        System.out.println("ERROR: No hi ha cap aparell registrat amb aquesta descripció a la casa indicada.");
                        break;
                    }

                    // Comprobamos si el aparato ya está apagado
                    if (!apOffA.isEncès()) {
                        System.out.println("ERROR: L'aparell ja està apagat.");
                        break;
                    }

                    // Apagamos el aparato
                    apOffA.apagar();
                    System.out.println("OK: Aparell apagat.");
                    break;

                // Comando para mostrar el listado de las casas    
                case "list":

                    // Verificar que se han ingresado la cantidad correcta de parámetros
                    if (partes.length != 1) {
                        System.out.println("ERROR: Número de paràmetres incorrecte.");
                        System.out.println("Ús: list");
                        break;
                    }

                    // Si no hay casas registradas, se muestra un mensaje y se sale del case
                    if (listaCasas.isEmpty()) {
                        System.out.println("No hi ha cases registrades.");
                        break;
                    }

                    // Si hay casas registradas, se muestra el encabezado con el número de casas
                    int numCasas = listaCasas.size();
                    System.out.println("--- Endolls Solars, S.L. ---");
                    System.out.println("Cases enregistrades: " + numCasas);

                    // Se itera sobre la lista de casas para mostrar la información de cada una
                    int contador = 1;
                    for (Casa casaEnLista : listaCasas) {
                        System.out.println("\nCasa " + contador);
                        System.out.println("Client: " + casaEnLista.getNif() + " - " + casaEnLista.getNom());
                        // Forma para poder pasar un double a integer
                        double superficieTotal = casaEnLista.getSuperficieCasa();
                        int superficieTotalInt = (int) Math.round(superficieTotal);
                        System.out.println("Superfície de teulada: " + superficieTotalInt);
                        // Forma para poder pasar un double a integer
                        double superficieDisponible = casaEnLista.getSuperficieDisponible();
                        int superficieDisponibleInt = (int) Math.round(superficieDisponible);
                        System.out.println("Superfície disponible: " + superficieDisponibleInt);
                        System.out.println("Interruptor general: " + (casaEnLista.isInterruptorGeneral() ? "encès" : "apagat"));

                        // Si la casa no tiene placas solares registradas, se muestra un mensaje indicándolo
                        if (casaEnLista.getPlaquesSolars().isEmpty()) {
                            System.out.println("No té plaques solars instal·lades.");
                        } else {

                            // Si la casa tiene placas solares registradas, se muestra el número de placas
                            System.out.println("Plaques solars instal·lades: " + casaEnLista.getPlaquesSolars().size());
                        }

                        // Si la casa no tiene aparells eléctrics registrados, se muestra un mensaje indicándolo
                        if (casaEnLista.getAparellsElectrics().isEmpty()) {
                            System.out.println("No té cap aparell elèctric registrat.");
                        } else {

                            // Si la casa tiene aparells eléctrics registrados, se muestra el número de aparells
                            System.out.println("Aparells registrats: " + casaEnLista.getAparellsElectrics().size());
                        }
                        contador++;
                    }
                    break;

                // Comando para mostrar la informacion de la casa sobre el nif introducido
                case "info":

                    // Verificar que se han ingresado la cantidad correcta de parámetros
                    if (partes.length != 2) {
                        System.out.println("ERROR: Número de paràmetres incorrecte.");
                        System.out.println("Ús: info [nif]");
                        break;
                    }
                    String nifInfo = partes[1];
                    try {

                        // Busca la casa con el NIF especificado
                        Casa casaInfo = buscarCasa(nifInfo, listaCasas);

                        // Muestra información relevante sobre la casa
                        System.out.println("Client: " + casaInfo.getNif() + " - " + casaInfo.getNom());
                        System.out.println("Plaques solars instal·lades: " + casaInfo.getPlaquesSolars().size());
                        // Forma para poder pasar un double a integer
                        double potenciaTotal = casaInfo.getPotenciaPlacas();
                        int potenciaTotalInt = (int) Math.round(potenciaTotal);
                        System.out.println("Potència total: " + potenciaTotalInt + "W");
                        System.out.println("Inversió total: " + casaInfo.getInversionPlacasSolares() + "€");
                        System.out.println("Aparells registrats: " + casaInfo.getAparellsElectrics().size());
                        System.out.println("Consum actual: " + casaInfo.getConsumActual() + "W");

                        // Obtiene la lista de aparatos eléctricos encendidos de la casa
                        List<AparellElectric> aparatosEncendidos = casaInfo.getAparellsEncesos();
                        if (aparatosEncendidos.isEmpty()) {

                            // Si no hay aparatos encendidos, no se muestra nada
                        } else {

                            // Si hay aparatos encendidos, se muestra una lista con sus descripciones
                            System.out.println("Aparells encesos:");
                            for (AparellElectric aparato : aparatosEncendidos) {
                                System.out.println("- " + aparato.getDescripcio());
                            }
                        }
                    } catch (NullPointerException e) {

                        // Si no se encuentra una casa con el NIF especificado, muestra un mensaje de error
                        System.out.println("ERROR: No hi ha cap casa registrada amb aquest nif.");
                    }
                    break;

                // Comando para salir del programa
                case "quit":

                    // Verifica que no haya parámetros adicionales
                    if (partes.length != 1) {
                        System.out.println("ERROR: Número de paràmetres incorrecte.");
                        System.out.println("Ús: quit");
                        break;
                    }
                    break;
                // Comando para cuando el input es algo fuera de los case
                default:
                    System.out.println("ERROR: Comanda no reconeguda");
                    break;
            }
            // Cerramos el programa si se utiliza el comando quit
        } while (!input.equals("quit"));

    }

}

