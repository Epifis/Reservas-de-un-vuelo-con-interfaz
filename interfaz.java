package interfaz;

//importacion de clases logicas 
import Clases_Mundo.Asiento;
import Clases_Mundo.Pasajero;
import Clases_Mundo.Vuelo;

//importación de todo el paquete swing, javax.awt y eventos
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class interfaz {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            marco marco1 = new marco();
            marco1.setVisible(true);
            marco1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}

class marco extends JFrame {
public final Color colorFondo=new Color(121, 163, 232);
    // Creando constructor
    public marco() {
        
        setTitle("Reservas del vuelo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        paneles panel1 = new paneles(2, 4, 7, 6);
        panel1.setBackground(colorFondo);
        
        add(panel1);

        setVisible(true);
    }
}

final class paneles extends JPanel {
public final Color colorFondo=new Color(121, 163, 232);
    private final Vuelo vueloEjecutivo;
    private final Vuelo vueloEconomico;
    //agregacion de paneles
    private final JPanel panelEjecutivos;
    private final JPanel panelEconomicos;
    private final JPanel panelesJuntos;

    public paneles(int filasEjecutivo, int columnasEjecutivo, int filasEconomico, int columnasEconomico) {
        vueloEjecutivo = new Vuelo(filasEjecutivo, columnasEjecutivo);
        vueloEconomico = new Vuelo(filasEconomico, columnasEconomico);
        
        panelesJuntos = new JPanel();
        
        
        panelEjecutivos = new JPanel(new GridLayout(vueloEjecutivo.getFilas(), vueloEjecutivo.getColumnas()));
        panelEconomicos = new JPanel(new GridLayout(vueloEconomico.getFilas(), vueloEconomico.getColumnas()));

        panelesJuntos.add(panelEjecutivos);
        panelesJuntos.add(panelEconomicos);
        BoxLayout boxlayout = new BoxLayout(panelesJuntos, BoxLayout.Y_AXIS);
        panelesJuntos.setLayout(boxlayout); 
        
        add(panelesJuntos);

        agregarBotones();
        actualizarAsientos();
    }
   public void agregarBotones() {
        JPanel panelBotones = new JPanel();
        JButton asignarBtn = new JButton("Asignar Pasajero");
        JButton desocuparBtn = new JButton("Desocupar Asiento");
        JButton porcentajeBtn = new JButton("Porcentaje de Ocupación");
        JButton buscarPasajeroBtn = new JButton("Buscar un Pasajero");
        JButton buscarAsientoBtn = new JButton("Buscar un asiento");

        
        buscarAsientoBtn.setForeground(colorFondo);
        buscarPasajeroBtn.setForeground(colorFondo);
        porcentajeBtn.setForeground(colorFondo);
        desocuparBtn.setForeground(colorFondo);
        asignarBtn.setForeground(colorFondo);
        
        
        asignarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                asignarPasajero();
                actualizarAsientos();
            }
        });

        desocuparBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desocuparAsiento();
                actualizarAsientos();
            }
        });

        porcentajeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Porcentaje();
            }
        });
        
        buscarPasajeroBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarPasajero();
            }
        });
        
        buscarAsientoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarAsiento();
            }
        });
        panelBotones.add(asignarBtn);
        panelBotones.add(desocuparBtn);
        panelBotones.add(porcentajeBtn);
        panelBotones.add(buscarPasajeroBtn);
        panelBotones.add(buscarAsientoBtn);

        BoxLayout boxlayout = new BoxLayout(panelBotones, BoxLayout.Y_AXIS);
        panelBotones.setLayout(boxlayout); 
        panelBotones.setBackground(colorFondo);
        
        add(panelBotones, BorderLayout.SOUTH);
    }
   private void agregarBotonAsiento(Asiento asiento, JPanel panel, Vuelo vuelo) {
    JButton btnAsiento = new JButton(asiento.getNumeroAsiento(vuelo));

    if (asiento.estaOcupado()) {
        btnAsiento.setBackground(Color.RED);
        btnAsiento.setOpaque(true);
        btnAsiento.setBorderPainted(false);
        btnAsiento.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(paneles.this, "Asiento\n Pasajero " + asiento.getNombrePasajero());
        }
    });
    } else {
        btnAsiento.setBackground(Color.GREEN);
        btnAsiento.setOpaque(true);
        btnAsiento.setBorderPainted(false);
        btnAsiento.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(paneles.this, "Asiento libre");
        }
    });
    }

    

    panel.add(btnAsiento);
}

private void asignarPasajero() {
    String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del pasajero:");
    int cedula = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la cédula del pasajero:"));
    int clase = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la clase\n 1. Ejecutivo\n 2. Económico\n"));
    int preferencia = Integer.parseInt(JOptionPane.showInputDialog(this, """
            Ingrese la preferencia
            1. Ventana
            2. Centro
            3. Pasillo"""));

    do {
        switch (clase) {
            case 1 -> {     // Ejecutivo
                asignarAsientoEjecutivo(nombre, cedula, preferencia);
            }
            case 2 -> {     // Económico
                asignarAsientoEconomico(nombre, cedula, preferencia);
            }
            default -> JOptionPane.showMessageDialog(this, "Lo sentimos, número no válido");
        }
    } while (clase != 1 && clase != 2);

    actualizarAsientos();
}

private void asignarAsientoEjecutivo(String nombre, int cedula, int preferencia) {
    switch (preferencia) {
        case 1 -> {
            // Ventana
            asignarAsientoVentana(vueloEjecutivo, nombre, cedula);
        }
        case 2 -> {
            // Centro
            JOptionPane.showMessageDialog(this, "Esta opción solo es válida para económicos.");
        }
        case 3 -> {
            // Pasillo
            asignarAsientoPasillo(vueloEjecutivo, nombre, cedula);
        }
        default -> {
        }
    }
    actualizarAsientos();
}

private void asignarAsientoEconomico(String nombre, int cedula, int preferencia) {
    switch (preferencia) {
        case 1 -> {
            // Ventana
            asignarAsientoVentana(vueloEconomico, nombre, cedula);
        }
        case 2 -> {
            // Centro
            asignarAsientoCentro(vueloEconomico, nombre, cedula);
        }
        case 3 -> {
            // Pasillo
            asignarAsientoPasillo(vueloEconomico, nombre, cedula);
        }
        default -> {
        }
    }
    actualizarAsientos();
}

private void asignarAsientoVentana(Vuelo vuelo, String nombre, int cedula) {
    // Lógica para asignar asiento de ventana
    if(vuelo == vueloEjecutivo){
    for (int i = 0; i < vuelo.getFilas(); i++) {
        for (int j = 0; j < vuelo.getColumnas(); j += 3) {
            Asiento asiento = vuelo.obtenerAsiento(i + 1, j + 1);
            if (!asiento.estaOcupado()) {
                Pasajero pasajero = new Pasajero(nombre, cedula);
                asiento.asignarPasajero(pasajero);
                JOptionPane.showMessageDialog(this, "Pasajero asignado correctamente al asiento " + (i + 1) + "-" + (j + 1) + "\n Pasajero " + pasajero.getNombre() + " con cédula " + pasajero.getCedula());
                return;
            }
        }
    }
    }
    else if(vuelo == vueloEconomico){
    for (int i = 0; i < vuelo.getFilas(); i++) {
        for (int j = 0; j < vuelo.getColumnas(); j += 5) {
            Asiento asiento = vuelo.obtenerAsiento(i + 1, j + 1);
            if (!asiento.estaOcupado()) {
                Pasajero pasajero = new Pasajero(nombre, cedula);
                asiento.asignarPasajero(pasajero);
                JOptionPane.showMessageDialog(this, "Pasajero asignado correctamente al asiento " + (i + 1) + "-" + (j + 1) + "\n Pasajero " + pasajero.getNombre() + " con cédula " + pasajero.getCedula());
                return;
            }
        }
    }
    }
    JOptionPane.showMessageDialog(this, "No hay asientos de ventana disponibles en la clase seleccionada.");
}

private void asignarAsientoCentro(Vuelo vuelo, String nombre, int cedula) {
    // Lógica para asignar asiento central
    for (int i = 0; i < vuelo.getFilas(); i++) {
        for (int j = 1; j < vuelo.getColumnas(); j += 3) {
            Asiento asiento = vuelo.obtenerAsiento(i + 1, j + 1);
            if (!asiento.estaOcupado()) {
                Pasajero pasajero = new Pasajero(nombre, cedula);
                asiento.asignarPasajero(pasajero);
                JOptionPane.showMessageDialog(this, "Pasajero asignado correctamente al asiento " + (i + 1) + "-" + (j + 1) + "\n Pasajero " + pasajero.getNombre() + " con cédula " + pasajero.getCedula());
                return;
            }
        }
    }
    JOptionPane.showMessageDialog(this, "No hay asientos centrales disponibles en la clase seleccionada.");
}

private void asignarAsientoPasillo(Vuelo vuelo, String nombre, int cedula) {
    // Lógica para asignar asiento de pasillo
    if(vuelo == vueloEjecutivo){
    for (int i = 0; i < vuelo.getFilas(); i++) {
        for (int j = 1; j < vuelo.getColumnas(); j ++) {
            Asiento asiento = vuelo.obtenerAsiento(i + 1, j + 1);
            if (!asiento.estaOcupado()) {
                Pasajero pasajero = new Pasajero(nombre, cedula);
                asiento.asignarPasajero(pasajero);
                JOptionPane.showMessageDialog(this, "Pasajero asignado correctamente al asiento " + (i + 1) + "-" + (j + 1) + "\n Pasajero " + pasajero.getNombre() + " con cédula " + pasajero.getCedula());
                return;
            }
            if(j==2){
                break;
            }
        }
    }
    }
    else if(vuelo == vueloEconomico){
        for (int i = 0; i < vuelo.getFilas(); i++) {
        for (int j = 2; j < vuelo.getColumnas(); j ++) {
            Asiento asiento = vuelo.obtenerAsiento(i + 1, j + 1);
            if (!asiento.estaOcupado()) {
                Pasajero pasajero = new Pasajero(nombre, cedula);
                asiento.asignarPasajero(pasajero);
                JOptionPane.showMessageDialog(this, "Pasajero asignado correctamente al asiento " + (i + 1) + "-" + (j + 1) + "\n Pasajero " + pasajero.getNombre() + " con cédula " + pasajero.getCedula());
                return;
            }
            if(j==3){
                break;
            }
        }
    }
    }
    JOptionPane.showMessageDialog(this, "No hay asientos de pasillo disponibles en la clase seleccionada.");
}


    private void desocuparAsiento() {
        int fila = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la fila del asiento que desea desocupar:"));
        int columna = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la columna del asiento que desea desocupar:"));

        int clase = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la clase\n 1. Ejecutivo\n 2. Económico\n"));

        switch (clase) {
            case 1 -> desocuparAsiento(vueloEjecutivo, fila, columna);
            case 2 -> desocuparAsiento(vueloEconomico, fila, columna);
            default -> JOptionPane.showMessageDialog(this, "Lo sentimos, número no válido");
        }

        actualizarAsientos();
    }

    private void desocuparAsiento(Vuelo vuelo, int fila, int columna) {
        Asiento asiento = vuelo.obtenerAsiento(fila, columna);

        if (asiento.estaOcupado()) {
            asiento.desocupar();
            JOptionPane.showMessageDialog(this, "Asiento " + fila + "-" + columna + " desocupado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "El asiento ya está desocupado. No se puede desocupar.");
        }
        actualizarAsientos();
    }

    private void actualizarAsientos() {
        panelEjecutivos.removeAll();
        panelEconomicos.removeAll();
        
        // Mostrar los asientos ejecutivos
        for (int i = 0; i < vueloEjecutivo.getFilas(); i++) {
            for (int j = 0; j < vueloEjecutivo.getColumnas(); j++) {
                Asiento asiento = vueloEjecutivo.obtenerAsiento(i + 1 , j + 1);
                agregarBotonAsiento(asiento, panelEjecutivos, vueloEjecutivo);
            }
        }

        // Mostrar los asientos económicos
        for (int i = 0; i < vueloEconomico.getFilas(); i++) {
            for (int j = 0; j < vueloEconomico.getColumnas(); j++) {
                Asiento asiento = vueloEconomico.obtenerAsiento(i + 1, j + 1);
                agregarBotonAsiento(asiento, panelEconomicos, vueloEconomico);
            }
        }

        revalidate();
        repaint();
    }


   public void Porcentaje(){
        double porcentajeOcupacionEjecutivo = vueloEjecutivo.porcentajeDeOcupacion();
        double porcentajeOcupacionEconomico =vueloEconomico.porcentajeDeOcupacion();
        JOptionPane.showMessageDialog(this, "Porcentaje de ocupación en ejécutivo: " + porcentajeOcupacionEjecutivo + "%" + "\nPorcentaje de ocupación en económico: " + porcentajeOcupacionEconomico + "%");
    }
   private void buscarEnVuelo(Vuelo vuelo, int cedula) {
        for (int i = 0; i < vuelo.getFilas(); i++) {
            for (int j = 0; j < vuelo.getColumnas(); j++) {
                Asiento asiento = vuelo.obtenerAsiento(i + 1, j + 1);
                if (asiento.estaOcupado() && asiento.getPasajeroCedula() == cedula) {
                    JOptionPane.showMessageDialog(this, "El pasajero está en el asiento " + asiento.getNumeroAsiento(vuelo) + " del vuelo " + (vuelo == vueloEjecutivo ? "Ejecutivo" : "Económico"));
                    return;
                }
            }
        }
    }

    public void BuscarPasajero() {
        int cedula = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la cédula del pasajero a buscar"));

        buscarEnVuelo(vueloEjecutivo, cedula);
        buscarEnVuelo(vueloEconomico, cedula);
    }


   public void BuscarAsiento() {
    int numeroAsiento = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el número del asiento a buscar"));

    buscarAsientoEnVuelo(vueloEjecutivo, numeroAsiento, "Ejecutivo");
    buscarAsientoEnVuelo(vueloEconomico, numeroAsiento, "Económico");
}

private void buscarAsientoEnVuelo(Vuelo vuelo, int numeroAsiento, String tipoVuelo) {
    for (int i = 0; i < vuelo.getFilas(); i++) {
        for (int j = 0; j < vuelo.getColumnas(); j++) {
            Asiento asiento;
            asiento = vuelo.obtenerAsiento(i+1, j+1);
            if (asiento.getNumeroAsiento(vuelo).equals(String.valueOf(numeroAsiento))) {
                if (asiento.estaOcupado()) {
                    JOptionPane.showMessageDialog(this, "El asiento " + numeroAsiento + " del vuelo " + tipoVuelo + " está ocupado por " + asiento.getNombrePasajero());
                } else {
                    JOptionPane.showMessageDialog(this, "El asiento " + numeroAsiento + " del vuelo " + tipoVuelo + " no está ocupado.");
                }
                return;
            }
        }
    }
}


}
