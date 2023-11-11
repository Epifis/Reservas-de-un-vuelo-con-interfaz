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

    // Creando constructor
    public marco() {
        Color colorFondo=new Color(121, 163, 232);
        setTitle("Reservas del vuelo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 400);
        setResizable(false);
        paneles panel1 = new paneles(8, 11);
        panel1.setBackground(colorFondo);
        
        add(panel1);

        setVisible(true);
    }
}

final class paneles extends JPanel {

    private final Vuelo vuelo;
    //agregacion de paneles
    private final JPanel panelEjecutivos;
    private final JPanel panelEconomicos;

    public paneles(int filas, int columnas) {
        vuelo = new Vuelo(filas, columnas);
        panelEjecutivos = new JPanel(new GridLayout(2, 4));
        panelEconomicos = new JPanel(new GridLayout(6, 7));
        
        add(panelEjecutivos, BorderLayout.LINE_START);
        add(panelEconomicos, BorderLayout.CENTER);
        
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
                //Accion del boton
            }
        });
        
        buscarAsientoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //accion del boton
            }
        });
        panelBotones.add(asignarBtn, BorderLayout.SOUTH);
        panelBotones.add(desocuparBtn,BorderLayout.SOUTH);
        panelBotones.add(porcentajeBtn,BorderLayout.SOUTH);
        panelBotones.add(buscarPasajeroBtn,BorderLayout.SOUTH);
        panelBotones.add(buscarAsientoBtn,BorderLayout.SOUTH);
        

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void asignarPasajero() {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del pasajero:");
        String cedula = JOptionPane.showInputDialog(this, "Ingrese la cédula del pasajero:");
        int fila = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la fila del asiento:"));
        int columna = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la columna del asiento:"));

        Asiento asiento = vuelo.obtenerAsiento(fila, columna);

        if (!asiento.estaOcupado()) {
            Pasajero pasajero = new Pasajero(nombre, cedula);
            asiento.asignarPasajero(pasajero);
            JOptionPane.showMessageDialog(this, "Pasajero asignado correctamente al asiento " + fila + "-" + columna + "\n Pasajero " + pasajero.getNombre() + " con cedula " + pasajero.getCedula());
        } else {
            JOptionPane.showMessageDialog(this, "El asiento ya está ocupado. Elija otro asiento.");
        }
    }

    private void desocuparAsiento() {
        int fila = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la fila del asiento que desea desocupar:"));
        int columna = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la columna del asiento que desea desocupar:"));

        Asiento asiento = vuelo.obtenerAsiento(fila, columna);

        if (asiento.estaOcupado()) {
            asiento.desocupar();
            JOptionPane.showMessageDialog(this, "Asiento " + fila + "-" + columna + " desocupado correctamente.");
        } else {
            JOptionPane.showMessageDialog(this, "El asiento ya está desocupado. No se puede desocupar.");
        }
    }

    private void agregarBotonAsiento(Asiento asiento, JPanel panel) {
    JButton btnAsiento = new JButton(asiento.getNumeroAsiento());

    if (asiento.estaOcupado()) {
        btnAsiento.setBackground(Color.RED);
        btnAsiento.setOpaque(true);
        btnAsiento.setBorderPainted(false);
    } else {
        btnAsiento.setBackground(Color.GREEN);
        btnAsiento.setOpaque(true);
        btnAsiento.setBorderPainted(false);
    }

    btnAsiento.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(paneles.this, "Asiento " + asiento.getNumeroAsiento());
        }
    });

    panel.add(btnAsiento);
}


    private void actualizarAsientos() {
        panelEjecutivos.removeAll();
        panelEconomicos.removeAll();

        // Mostrar los asientos ejecutivos
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                Asiento asiento = vuelo.obtenerAsiento(i + 1, j + 1);
                agregarBotonAsiento(asiento, panelEjecutivos);
            }
        }

        // Mostrar los asientos económicos
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Asiento asiento = vuelo.obtenerAsiento(i + 1, j + 1);
                agregarBotonAsiento(asiento, panelEconomicos);
            }
        }

        revalidate();
        repaint();
    }
     
    public void Porcentaje(){
        double porcentajeOcupacion = vuelo.porcentajeDeOcupacion();
        JOptionPane.showMessageDialog(this, "Porcentaje de ocupación: " + porcentajeOcupacion + "%");
    }
}
