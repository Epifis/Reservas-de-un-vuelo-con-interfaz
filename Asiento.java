package Clases_Mundo;


/**
 *
 * @author Alexandra Tinjaca
 */
public class Asiento {
    private final int numeroFila;
    private final int numeroColumna;
    private static int contadorAsientos = 1;
    private Pasajero pasajero;

    public Asiento(int numeroFila, int numeroColumna) {
        this.numeroFila = numeroFila;
        this.numeroColumna = numeroColumna;
    }
    public String getNumeroAsiento(Vuelo vuelo) {
        if(contadorAsientos>=51)
            Asiento.contadorAsientos = 1;
        return String.valueOf(contadorAsientos++);
    }
    public int getPasajeroCedula() {
        return (pasajero != null) ? pasajero.getCedula() : -1; // Retorna -1 si el asiento está desocupado
    }
    public String getNombrePasajero(){
        return pasajero.getNombre();
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
}
