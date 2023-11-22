package Logica;

/**
 *
 * @author Alexandra Tinjacá
 */

public class Vuelo {
    private final Asiento[][] matrizAsientosEjecutivos;
    private final Asiento[][] matrizAsientosEconomicos;

    public Vuelo() {
        matrizAsientosEjecutivos = new Asiento[4][2];
        matrizAsientosEconomicos = new Asiento[6][7];

        inicializarAsientos(matrizAsientosEjecutivos);
        inicializarAsientos(matrizAsientosEconomicos);
    }

    private void inicializarAsientos(Asiento[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                matriz[i][j] = new Asiento(i + 1, j + 1, ' '); // ' ' representa ninguna preferencia específica
            }
        }
    }

    public void mostrarAsientos() {
        System.out.println("Asientos Ejecutivos:");
        mostrarAsientos(matrizAsientosEjecutivos);
        System.out.println("Asientos Económicos:");
        mostrarAsientos(matrizAsientosEconomicos);
    }

    private void mostrarAsientos(Asiento[][] matriz) {
        for (Asiento[] fila : matriz) {
            for (Asiento asiento : fila) {
                System.out.print(asiento + " ");
            }
            System.out.println();
        }
    }

    public Asiento asignarAsientoEjecutivo(Pasajero pasajero, int preferencia) {
        return asignarAsiento(matrizAsientosEjecutivos, pasajero, preferencia);
    }

    public Asiento asignarAsientoEconomico(Pasajero pasajero, int preferencia) {
        
        return asignarAsiento(matrizAsientosEconomicos, pasajero, preferencia);
    }

    private Asiento asignarAsiento(Asiento[][] matriz, Pasajero pasajero, int preferencia) {
    int filas = matriz.length;
    int columnas = matriz[0].length;

    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            Asiento asiento = matriz[i][j];

            if (!asiento.estaOcupado() && asiento.getPreferencia() == preferencia) {
                asiento.asignarPasajero(pasajero);
                return asiento;  // Devuelve el asiento asignado
            }
        }
    }

    return null; // Devolver null si no hay asientos disponibles con la preferencia especificada.
}



    public double porcentajeDeOcupacion(boolean esEjecutivo) {
        Asiento[][] matriz = esEjecutivo ? matrizAsientosEjecutivos : matrizAsientosEconomicos;
        int asientosTotales = matriz.length * matriz[0].length;
        int asientosOcupados = 0;

        for (Asiento[] fila : matriz) {
            for (Asiento asiento : fila) {
                if (asiento.estaOcupado()) {
                    asientosOcupados++;
                }
            }
        }

        if (asientosTotales == 0) {
            return 0.0;
        }

        return ((double) asientosOcupados / asientosTotales) * 100.0;
    }
}

