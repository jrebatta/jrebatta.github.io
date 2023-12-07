package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import static org.example.DesencriptarController.getString;

public class SorteoIntercambio {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ingresar los nombres de los participantes
        System.out.println("Ingrese los nombres de los participantes separados por espacios:");
        String[] participantesArray = scanner.nextLine().split(" ");
        List<String> participantes = new ArrayList<>(Arrays.asList(participantesArray));

        // Ingresar las restricciones para cada participante
        Map<String, List<String>> restricciones = new HashMap<>();
        for (String participante : participantes) {
            System.out.println("Ingrese las restricciones separadas por espacios para " + participante + ":");
            String[] restriccionesArray = scanner.nextLine().split(" ");
            restricciones.put(participante, new ArrayList<>(Arrays.asList(restriccionesArray)));
        }

        Map<String, String> asignaciones = realizarSorteo(participantes, restricciones);

        if (asignaciones != null) {
            for (Map.Entry<String, String> asignacion : asignaciones.entrySet()) {
                String valorEncriptado = encriptar(asignacion.getValue());
                System.out.println(asignacion.getKey() + " regalará a " + valorEncriptado);
            }
        } else {
            System.out.println("No se pudo realizar la asignación debido a las restricciones.");
        }

        scanner.close();
    }

    public static Map<String, String> realizarSorteo(List<String> participantes, Map<String, List<String>> restricciones) {
        List<String> listaParticipantes = new ArrayList<>(participantes);
        Map<String, String> asignaciones = new HashMap<>();
        Random random = new Random();

        while (!listaParticipantes.isEmpty()) {
            String personaActual = listaParticipantes.remove(0);
            List<String> posiblesAsignaciones = new ArrayList<>(participantes);
            posiblesAsignaciones.remove(personaActual);

            if (restricciones.containsKey(personaActual)) {
                posiblesAsignaciones.removeAll(restricciones.get(personaActual));
            }

            if (posiblesAsignaciones.isEmpty()) {
                return null; // No hay opciones válidas para asignar regalo a la personaActual debido a restricciones
            }

            String posibleAsignacion = obtenerDestinatarioAleatorio(posiblesAsignaciones, asignaciones);
            asignaciones.put(personaActual, posibleAsignacion);
        }

        return asignaciones;
    }

    public static String encriptar(String texto) {
        // Ejemplo simple de cifrado César: desplazamiento de caracteres
        int desplazamiento = 3; // Puedes ajustar este valor para un cifrado más complejo
        StringBuilder textoEncriptado = new StringBuilder();

        for (char caracter : texto.toCharArray()) {
            if (Character.isLetter(caracter)) {
                char nuevoCaracter = (char) (((caracter - 'a' + desplazamiento) % 26) + 'a');
                textoEncriptado.append(nuevoCaracter);
            } else {
                textoEncriptado.append(caracter);
            }
        }

        return textoEncriptado.toString();
    }

    public static String desencriptar(String textoEncriptado) {
        return getString(textoEncriptado);
    }


    public static String obtenerDestinatarioAleatorio(List<String> posiblesAsignaciones, Map<String, String> asignaciones) {
        Random random = new Random();
        String posibleAsignacion = posiblesAsignaciones.get(random.nextInt(posiblesAsignaciones.size()));

        while (asignaciones.containsValue(posibleAsignacion)) {
            posibleAsignacion = posiblesAsignaciones.get(random.nextInt(posiblesAsignaciones.size()));
        }

        return posibleAsignacion;
    }
}
