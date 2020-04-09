import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class ObtenerTiempoRemoto extends JFrame implements ActionListener {
    private JTextField entradaDelHost;
    private JTextArea display;
    private JButton botonTiempo;
    private JButton botonSalida;
    private JPanel panelBoton;
    private static Socket socket = null;

    public static void main(String[] args) {
        ObtenerTiempoRemoto frame = new ObtenerTiempoRemoto();
        frame.setSize(400,300);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException ioEx) {
                        System.out.println("\nNo fue posible cerrar el enlace!\n");
                        System.exit(1);
                    }
                }

                System.exit(0);
            }
        });
    }

    public ObtenerTiempoRemoto() {
        entradaDelHost = new JTextField(20);
        add(entradaDelHost, BorderLayout.NORTH);
        display = new JTextArea(10,15);
        display.setWrapStyleWord(true);
        display.setLineWrap(true);
        add(new JScrollPane(display), BorderLayout.CENTER);
        panelBoton = new JPanel();
        botonTiempo = new JButton("Obtener hora y fecha ");
        botonTiempo.addActionListener(this);
        panelBoton.add(botonTiempo);
        botonSalida = new JButton("Salir");
        botonSalida.addActionListener(this);
        panelBoton.add(botonSalida);
        add(panelBoton,BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == botonSalida) {
            System.exit(0);
        }

        String elTiempo;
        String host = entradaDelHost.getText();
        final int PUERTO_DAYTIME = 13;

        try {
            socket = new Socket(host, PUERTO_DAYTIME);
            Scanner input = new Scanner(socket.getInputStream());
            elTiempo = input.nextLine();
            display.append("La fecha/hora en " + host + " es " + elTiempo + "\n");
            entradaDelHost.setText("");
        } catch (UnknownHostException uhEx) {
            display.append("No existe ese host!\n");
            entradaDelHost.setText("");
        } catch (IOException ioEx) {
            display.append(ioEx.toString() + "\n");
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ioEx) {
                System.out.println("Imposible de desconectar!");
                System.exit(1);
            }
        }
    }
}