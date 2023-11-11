/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases_Mundo;

/**
 *
 * @author Kotaro
 */
public class Vuelo {
    public static final int COLUMNAS = 11;  // Ajusta el valor según tus necesidades
    private final Asiento[][] matrizAsientos;

    public Vuelo(int filas, int columnas) {
    matrizAsientos = new Asiento[filas][columnas];
    
    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            matrizAsientos[i][j] = new Asiento(i + 1, j + 1);
            }
        }
    }

    public void mostrarAsientos() {
        for (Asiento[] matrizAsiento : matrizAsientos) {
            for (int j = 0; j < matrizAsientos[0].length; j++) {
                System.out.print(matrizAsiento[j].toString() + " ");
            }
            System.out.println();
        }
    }

    public Asiento obtenerAsiento(int fila, int columna) {
        return matrizAsientos[fila - 1][columna - 1];
    }
    
     public int getFilas() {
        return matrizAsientos.length;
    }

    public int getColumnas() {
        return matrizAsientos[0].length;
    }
    public double porcentajeDeOcupacion() {
        int asientosTotales = 0;
        int asientosOcupados = 0;

        for (Asiento[] matrizAsiento : matrizAsientos) {
            for (int j = 0; j < matrizAsientos[0].length; j++) {
                if (matrizAsiento[j] != null) {
                    asientosTotales++;
                    if (matrizAsiento[j].estaOcupado()) {
                        asientosOcupados++;
                    }
                }
            }
        }

        if (asientosTotales == 0) {
            return 0.0; // Evitar división por cero
        }

        double porcentajedeocupacion = ((double) asientosOcupados / asientosTotales) * 100.0;
        return porcentajedeocupacion;
    }


}
