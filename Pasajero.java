/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases_Mundo;

/**
 *
 * @author Kotaro
 */
public class Pasajero {
    private final String nombre;
    private final String cedula;

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

    
    @Override
    public String toString() {
        return nombre + " (" + cedula + ")";
    }
}

