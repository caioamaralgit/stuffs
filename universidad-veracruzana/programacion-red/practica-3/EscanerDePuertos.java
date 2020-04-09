import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class EscanerDePuertos extends JFrame implements ActionListener {
    private JLabel prompt;
    private JTextField entradaDelHost;
    private JTextArea reporte;
    private JButton botonBuscar, botonSalir;
    private JPanel panelDelHost, buttonPanel;
    private static Socket socket = null;

    public static void main(String args[]) {
        EscanerDePuertos frame = new EscanerDePuertos();
        frame.setSize(400,350);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException ioEx) {
                        System.out.println("\nNo se pude cerrar el enlace!");
                        System.exit(1);
                    }
                }

                System.exit(0);
            }
        });        
    }

    public EscanerDePuertos() {
        panelDelHost = new JPanel();
        prompt = new JLAbel("Nombre de host: ");
        entradaDelHost = new JTextField("www.uv.mx", 20);
        panelDelHost.add(prompt);
        panelDelHost.add(entradaDelHost);
        add(PanelDelHost,BorderLayout.NORTH);
        reporte = new JTextArea(10,25);
        add(reporte,BorderLayout.CENTER);
        buttonPanel = new JPanel();
        botonBuscar = new JButton("Buscar puertos del servidor");
        botonBuscar.addActionListener(this);
        buttonPanel.add(botonBuscar);
        botonSalir = new JButton("Salir");
        botonSalir.addActionListener(this);
        buttonPanel.add(botonSalir);
        add(buttonPanel,BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == botonSalir) {
            System.exit(0);
        }

        reporte.setText("");
        String host = entradaDelHost.getText();
        try { 
            InetAddress laDireccion = InetAddress.getByName(host);
            reporte.append("Dirección IP: " + laDireccion + "\n");

            for (int i = 0; i < 30; i++) {
                try {
                    socket = new Socket(host, i);
                    reporte.append("Hay un servidor en el puerto " + i + ".\n");
                    socket.close();
                } catch (IOException ioEx) {}
            }
        } catch (UnknownHostException uhEx) {
            reporte.setText("Host desconocido!");
        }        
    }
}