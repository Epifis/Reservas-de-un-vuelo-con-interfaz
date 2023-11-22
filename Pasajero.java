package Clases_Mundo;

/**
 *
 * @author Alexandra Tinjacá
 */
public class Pasajero {
    private final String nombre;
    private final int cedula;

    public Pasajero(String nombre, int cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCedula() {
        return cedula;
    }

    
    @Override
    public String toString() {
        return nombre + " (" + cedula + ")";
    }
}

