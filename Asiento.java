package Clases_Mundo;


/**
 *
 * @author Kotaro
 */
public class Asiento {
    private final int numeroFila;
    private final int numeroColumna;
    private Pasajero pasajero;

    public Asiento(int numeroFila, int numeroColumna) {
        this.numeroFila = numeroFila;
        this.numeroColumna = numeroColumna;
    }

    public String getNumeroAsiento() {
        // Calcular el número de asiento en función de la fila y la columna
        
        return String.valueOf((numeroFila - 1) * Vuelo.COLUMNAS + (numeroColumna - 1));
    }
    
    public boolean estaOcupado() {
        return pasajero != null;
    }

    public void asignarPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public void desocupar() {
        this.pasajero = null;
    }

    @Override
    public String toString() {
        if (estaOcupado()) {
            return Asiento.this.getNumeroAsiento() + "Ocupado";
        } else {
            return Asiento.this.getNumeroAsiento();
        }

    }
}

