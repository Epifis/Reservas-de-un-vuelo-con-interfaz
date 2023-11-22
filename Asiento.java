package Logica;

/**
 *
 * @author Alexandra Tinjacá
 */
public class Asiento {
    private int numeroFila;
    private int numeroColumna;
    private Pasajero pasajero;
    private int preferencia;

    public Asiento(int numeroFila, int numeroColumna, int preferencia) {
        this.numeroFila = numeroFila;
        this.numeroColumna = numeroColumna;
        this.preferencia = preferencia;
    }

    public int getNumeroFila() {
        return numeroFila;
    }

    public int getNumeroColumna() {
        return numeroColumna;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void asignarPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public void desocupar() {
        this.pasajero = null;
    }

    public boolean estaOcupado() {
        return pasajero != null;
    }

    public int getPreferencia() {
        return preferencia;
    }

    @Override
    public String toString() {
        return "(" + numeroFila + "-" + numeroColumna + ")";
    }
}
