/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio02;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ChrstphrSands
 */
public class frmFiesta extends javax.swing.JFrame {

    ClsFiesta fiesta;
    DefaultTableModel tbl;
    Object[] fila;
    double promedio;
    int masJoven;

    /**
     * Creates new form frmFiesta
     */
    public frmFiesta() {
        initComponents();
        grupoSexo.add(rdbtnHombre);
        grupoSexo.add(rdbtnMujer);
        grupoSexo.add(rdbtnH);
        grupoSexo.add(rdbtnM);
        leerTodos();
    }

    String getNombre() {
        return (String) txtNombre.getText();
    }

    int getEdad() {
        return Integer.parseInt(txtEdad.getText());
    }

    String getSexo() {
        String sexo;
        if (rdbtnHombre.isSelected()) {
            sexo = "Hombre";
        } else {
            sexo = "Mujer";
        }
        return sexo;
    }

    String getSex() {
        String sexo;
        if (rdbtnH.isSelected()) {
            sexo = "Hombre";
        } else {
            sexo = "Mujer";
        }
        return sexo;
    }

    void getOperaciones() {
        String resultado;
        switch (cmbOperaciones.getSelectedIndex()) {
            case 0:
                resultado = "El total de asistentes es: " + fiesta.totalAsistentes();
                txtResultado.setText(resultado);
                break;
            case 1:
                resultado = "El total de  " + getSex().toLowerCase() + "es" + " es igual a " + contarCantidad();
                txtResultado.setText(resultado);
                break;
            case 2:
                resultado = "El promedio de edad " + getSex().toLowerCase() + "es" + " es igual a " + promedio();
                txtResultado.setText(resultado);
                break;
            case 3:
                resultado = "La edad mas joven en " + getSex().toLowerCase() + "es" + " es igual a " + masJoven();
                txtResultado.setText(resultado);
                break;
            case 4:
                resultado = "El mas joven de todos tiene " + masJovenTodos();
                txtResultado.setText(resultado);
                break;
            case 5:
                resultado = "El total de asistentes es: " + fiesta.totalAsistentes() + "\n"
                        + "El total de  " + getSex().toLowerCase() + "es" + " es igual a " + contarCantidad() + "\n"
                        + "El promedio de edad " + getSex().toLowerCase() + "es" + " es igual a " + promedio() + "\n"
                        + "La edad mas joven en " + getSex().toLowerCase() + "es" + " es igual a " + masJoven() + "\n";
                txtResultado.setText(resultado);
                break;
        }
    }

    void limpiar() {
        txtEdad.setText("");
        txtNombre.setText("");
        limpiarTabla();
        grupoSexo.clearSelection();
    }

    void limpiarTabla() {
        try {
            tbl = (DefaultTableModel) tblDatos.getModel();
            int filas = tblDatos.getRowCount();
            for (int i = 0; filas > i; i++) {
                tbl.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    void llenarTabla() {
        limpiarTabla();
        String columnas[] = {"Llegada", "Nombre", "Sexo", "Edad"};
        tbl.setColumnIdentifiers(columnas);
    }

    void leerTodos() {
        llenarTabla();
        fila = new Object[tbl.getColumnCount()];
        fiesta = new ClsFiesta();
        for (int i = 0; i < fiesta.tamano(); i++) {
            fila[0] = fiesta.obtener(i).getLlegada();
            fila[1] = fiesta.obtener(i).getNombre();
            fila[2] = fiesta.obtener(i).getSexo();
            fila[3] = fiesta.obtener(i).getEdad();
            tbl.addRow(fila);
        }
        tblDatos.setModel(tbl);
    }

    void grabar() {
        fiesta = new ClsFiesta();
        if (getEdad() != 0) {
            if (getEdad() > 18 && getEdad() <= 95) {
                if (rdbtnHombre.isSelected() || rdbtnMujer.isSelected()) {
                ClsPersona persona = new ClsPersona((fiesta.totalPersonas + 1), getNombre(), getSexo(), getEdad());
                fiesta.adicionar(persona);
                fiesta.grabar();
                leerTodos();
                JOptionPane.showMessageDialog(null, "Haz ingresado a la fiesta :P");
                limpiar();
                } else{
                    JOptionPane.showMessageDialog(null, "Selecciona tu sexo...");
                }
            } else if(getEdad() > 95){
                JOptionPane.showMessageDialog(null, "OjO Estás muy viejito... no puedes ingresar a la fiesta por tu salud :'(");
            } else{
                JOptionPane.showMessageDialog(null, "Lo siento no puedes ingresar a la fiesta, aún eres un chibolo pulpin :p");
            }
        } else {
            txtNombre.setEnabled(false);
            txtEdad.setEnabled(false);
            rdbtnHombre.setEnabled(false);
            rdbtnMujer.setEnabled(false);
            JOptionPane.showMessageDialog(null, "PiPiPiPi oCUPADo");
        }
        leerTodos();
    }

    int contarCantidad() {
        llenarTabla();
        fila = new Object[tbl.getColumnCount()];
        fiesta = new ClsFiesta();
        ArrayList<ClsPersona> lista = fiesta.buscarHM(getSex());
        if (lista.size() > 0) {
            for (ClsPersona persona : lista) {
                fila[0] = persona.getLlegada();
                fila[1] = persona.getNombre();
                fila[2] = persona.getSexo();
                fila[3] = persona.getEdad();
                tbl.addRow(fila);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay personas en la fiesta...");
        }
        tblDatos.setModel(tbl);
        return lista.size();
    }

    double promedio() {
        llenarTabla();
        fila = new Object[tbl.getColumnCount()];
        fiesta = new ClsFiesta();
        int edades = 0;
        ArrayList<ClsPersona> lista = fiesta.buscarHM(getSex());
        if (lista.size() > 0) {
            for (ClsPersona persona : lista) {
                edades += persona.getEdad();
                fila[0] = persona.getLlegada();
                fila[1] = persona.getNombre();
                fila[2] = persona.getSexo();
                fila[3] = persona.getEdad();
                tbl.addRow(fila);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay datos que calcular");
        }
        tblDatos.setModel(tbl);
        promedio = (double) edades / contarCantidad();
        return promedio;
    }

    int masJoven() {
        llenarTabla();
        fila = new Object[tbl.getColumnCount()];
        fiesta = new ClsFiesta();
        int joven = 0;
        ArrayList<ClsPersona> lista = fiesta.buscarHM(getSex());
        if (lista.size() > 0) {
            joven = lista.get(1).getEdad();
            for (ClsPersona persona : lista) {
                fila[0] = persona.getLlegada();
                fila[1] = persona.getNombre();
                fila[2] = persona.getSexo();
                fila[3] = persona.getEdad();
                tbl.addRow(fila);
                if (joven > persona.getEdad()) {
                    joven = persona.getEdad();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay datos de este tipo de operación");
        }
        tblDatos.setModel(tbl);
        return joven;
    }

    int masJovenTodos() {
        leerTodos();
        fiesta = new ClsFiesta();
        if (fiesta.tamano() > 0) {
            masJoven = fiesta.obtener(1).getEdad();
            for (int i = 0; i < fiesta.tamano(); i++) {
                if (masJoven > fiesta.obtener(i).getEdad()) {
                    masJoven = fiesta.obtener(i).getEdad();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay datos de este tipo de operación");
        }
        return masJoven;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoSexo = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCalcular = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        rdbtnHombre = new javax.swing.JRadioButton();
        rdbtnMujer = new javax.swing.JRadioButton();
        cmbOperaciones = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtResultado = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        rdbtnH = new javax.swing.JRadioButton();
        rdbtnM = new javax.swing.JRadioButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("FIESTA SEMAFORO :D");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Edad:");

        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdadKeyTyped(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnCalcular.setText("Calcular");
        btnCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblDatos);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Sexo:");

        rdbtnHombre.setText("Hombre");

        rdbtnMujer.setText("Mujer");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(rdbtnHombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(rdbtnMujer)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbtnHombre)
                    .addComponent(rdbtnMujer)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmbOperaciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Total Asistentes", "Total Hombres y Mujeres", "Promedio Hombres y Mujeres", "Mas Joven Hombres y Mujeres", "Mas Joven de Todos", "Mostrar Total" }));

        txtResultado.setColumns(20);
        txtResultado.setRows(5);
        jScrollPane3.setViewportView(txtResultado);

        jLabel5.setText("Sexo:");

        rdbtnH.setText("Hombre");

        rdbtnM.setText("Mujer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtNombre))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnCalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(rdbtnH)
                                .addGap(18, 18, 18)
                                .addComponent(rdbtnM))
                            .addComponent(cmbOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(286, 286, 286))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAgregar)
                            .addComponent(cmbOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLimpiar)
                            .addComponent(rdbtnH)
                            .addComponent(rdbtnM)
                            .addComponent(jLabel5))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(btnSalir))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCalcular)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        grabar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
        getOperaciones();
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyTyped
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)) {
            JOptionPane.showMessageDialog(null, "¡Ingresa tu edad... en números :P!");
            evt.consume();
        }
    }//GEN-LAST:event_txtEdadKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmFiesta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmFiesta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmFiesta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmFiesta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmFiesta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cmbOperaciones;
    private javax.swing.ButtonGroup grupoSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton rdbtnH;
    private javax.swing.JRadioButton rdbtnHombre;
    private javax.swing.JRadioButton rdbtnM;
    private javax.swing.JRadioButton rdbtnMujer;
    private javax.swing.JTable tblDatos;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtResultado;
    // End of variables declaration//GEN-END:variables

}
