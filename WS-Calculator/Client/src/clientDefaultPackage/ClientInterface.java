package clientDefaultPackage;

import java.io.*;
import java.net.*;
import javax.swing.*;

/**
 * @author Alex y Caio
 */
public class ClientInterface extends JFrame {

    // El constructor ejecuta el metodo initComponents() cuando el objeto es instanciado
    public ClientInterface() {
        // El metodo initComponents() crea los elementos visuales de la aplicación, y determina sus características
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitulo = new JLabel();
        textNumero1 = new JTextField();
        textNumero2 = new JTextField();
        buttonEnviar = new JButton();
        jLabelRespuesta = new JLabel();
        comboBoxOperaciones = new JComboBox<>();
        jLabelOperacion = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente Ruby");
        setLocation(new java.awt.Point(0, 0));

        jLabelTitulo.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelTitulo.setText("Calculadora");

        textNumero1.setHorizontalAlignment(JTextField.CENTER);

        textNumero2.setHorizontalAlignment(JTextField.CENTER);

        buttonEnviar.setText("Enviar");
        buttonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEnviarActionPerformed(evt);
            }
        });

        jLabelRespuesta.setHorizontalAlignment(SwingConstants.CENTER);

        comboBoxOperaciones.setModel(new DefaultComboBoxModel<>(new String[] { "Suma", "Resta", "Multiplicación", "División", "Potencia", "Raíz" }));
        comboBoxOperaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxOperacionesActionPerformed(evt);
            }
        });

        jLabelOperacion.setText("+");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLabelRespuesta, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTitulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(comboBoxOperaciones, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(textNumero1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabelOperacion)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textNumero2, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(buttonEnviar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitulo)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(textNumero1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(textNumero2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelOperacion))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboBoxOperaciones, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonEnviar)
                .addGap(18, 18, 18)
                .addComponent(jLabelRespuesta, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEnviarActionPerformed
        // La variable type va a contener el número de la opción seleccionada en el combobox de operaciones
        int type = comboBoxOperaciones.getSelectedIndex();
        
        // Los elementos textbox contienen un metodo llamado getText() que permite capturar lo que hay dentro de las cajas. 
        // Las String a y b van a contener los números inseridos por el usuário. 
        String a = textNumero1.getText();
        String b = textNumero2.getText();
        
        // Checa si los campos contienen números, si contienen hace la petición 
        if ((!isNumeric(a) && type == 5) || (!isNumeric(a) || (!isNumeric(b) && type != 5))) {
            // Escribe una mensaje de error para el usuário
            jLabelRespuesta.setText(type == 5 ? "Digite un número!" : "Digite los números!");
        } else {
            String operacion = "";
            
            // Determina la operación a ser solicitada, según selecionado por el usuario
            switch (type) {
                case 0:
                    operacion = "suma";
                    break;
                case 1:
                    operacion = "resta";
                    break;
                case 2:
                    operacion = "multiplicacion";
                    break;
                case 3:
                    operacion = "division";
                    break;
                case 4:
                    operacion = "potencia";
                    break;
                case 5:
                    operacion = "raiz";
                    break;
            }             
            
            try {
                // Crea una URL usando los parámetros obtenidos
                URL url = new URL("http://localhost:4567/" + operacion + "?a=" + a + "&b=" + b);

                // Hace una petición HTTP para el URL indicado
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                
                // Interpreta la respuesta del servidor
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));  
                
                // Escribe la respuesta en la pantalla, para el usuario
                jLabelRespuesta.setText(in.readLine());

                // Cierra el buffer y la conexión
                in.close();
                con.disconnect();
            } catch (IOException ex) {
                // Informa el usuario que ocurrio un error, y indica el error en la consola
                jLabelRespuesta.setText("Ocurrio un error!");
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_buttonEnviarActionPerformed

    // Este metodo es ejecutado siempre que el usuário cambia el valor de la combobox
    private void comboBoxOperacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxOperacionesActionPerformed
        // Captura el valor de la operación seleccionada por el usuario
        int type = comboBoxOperaciones.getSelectedIndex();
        
        // Pone la segunda caja de texto como editable
        textNumero2.setEditable(true);
        
        // Cambia el valor del label en el medio de las cajas de texto, según la operación seleccionada por el usuário
        switch (type) {
            case 0:
                jLabelOperacion.setText("+");
                break;
            case 1:
                jLabelOperacion.setText("-");
                break;
            case 2:
                jLabelOperacion.setText("*");
                break;
            case 3:
                jLabelOperacion.setText("/");
                break;
            case 4:
                jLabelOperacion.setText("^");
                break;
            case 5:
                jLabelOperacion.setText("√");
                textNumero2.setText("");
                textNumero2.setEditable(false);
                break;
        }
    }//GEN-LAST:event_comboBoxOperacionesActionPerformed

    /* Método creado por el usuario "CraigTP". 
     * Disponible en el link: https://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-numeric-in-java */
    public static boolean isNumeric(String str) {
        // Intenta convertir la String para Double. Si logras convertir, retorna true, sino false.
        try {  
            Double.parseDouble(str);  
        } catch (NumberFormatException nfe) {  
            return false;  
        }
        
        return true;  
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton buttonEnviar;
    private JComboBox<String> comboBoxOperaciones;
    private JLabel jLabelOperacion;
    private JLabel jLabelRespuesta;
    private JLabel jLabelTitulo;
    private JTextField textNumero1;
    private JTextField textNumero2;
    // End of variables declaration//GEN-END:variables
}