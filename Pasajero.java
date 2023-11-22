package Logica;

public class Pasajero {
    private String nombre;
    private String cedula;

    public Pasajero(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }
}
