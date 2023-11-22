package Logica;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Vuelo vuelo = new Vuelo();

        int opcion;

        do {
            System.out.println("Menú:");
            System.out.println("1. Mostrar Asientos");
            System.out.println("2. Asignar Pasajero Ejecutivo");
            System.out.println("3. Asignar Pasajero Económico");
            System.out.println("4. Porcentaje de Ocupación (Ejecutivos)");
            System.out.println("5. Porcentaje de Ocupación (Económicos)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    vuelo.mostrarAsientos();
                    break;
                case 2:
                    asignarPasajero(scanner, vuelo, true);
                    break;
                case 3:
                    asignarPasajero(scanner, vuelo, false);
                    break;
                case 4:
                    mostrarPorcentajeOcupacion(vuelo, true);
                    break;
                case 5:
                    mostrarPorcentajeOcupacion(vuelo, false);
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }
        } while (opcion != 0);
    }

    private static void asignarPasajero(Scanner scanner, Vuelo vuelo, boolean esEjecutivo) {
        System.out.println("Asignar Pasajero:");
        System.out.print("Ingrese el nombre del pasajero: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la cédula del pasajero: ");
        String cedula = scanner.nextLine();
        System.out.print("¿Preferencia de asiento? (1: Ventana, 2: Pasillo, 3: Centro): ");
        int preferencia = scanner.nextInt();


        Asiento asientoAsignado= null;
        if (esEjecutivo) {
            asientoAsignado = vuelo.asignarAsientoEjecutivo(new Pasajero(nombre, cedula), preferencia);
        } else {
            asientoAsignado = vuelo.asignarAsientoEconomico(new Pasajero(nombre, cedula), preferencia);
        }


        if (asientoAsignado != null) {
            System.out.println("Pasajero asignado correctamente al asiento " + asientoAsignado.getNumeroFila() + "-" + asientoAsignado.getNumeroColumna());
        } else {
            System.out.println("No hay asientos disponibles con la preferencia especificada.");
        }
    }


    private static void mostrarPorcentajeOcupacion(Vuelo vuelo, boolean esEjecutivo) {
        String tipoAsiento = esEjecutivo ? "Ejecutivos" : "Económicos";
        double porcentaje = vuelo.porcentajeDeOcupacion(esEjecutivo);
        System.out.println("Porcentaje de Ocupación (" + tipoAsiento + "): " + porcentaje + "%");
    }
}
