
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author archrs
 */
public class frmOperaciones extends javax.swing.JFrame {

    DefaultTableModel tbl;
    ClsArregloOperaciones operaciones;

    Object[] fila;

    /**
     * Creates new form frmPruebas
     */
    public frmOperaciones() {
        initComponents();
        leerTodos();
    }

    int getId() {
        return Integer.parseInt(txtId.getText());
    }

    double getA() {
        return Double.parseDouble(txtA.getText());
    }

    double getB() {
            return Double.parseDouble(txtB.getText());
    }

    double getResultado() {
        return calcularResultado();
    }

    double calcularResultado() {
        double resultado = 0;
        switch (cmbOperaciones.getSelectedIndex()) {
            case 0:
                resultado = getA() + getB();
                break;
            case 1:
                resultado = getA() - getB();
                break;
            case 2:
                resultado = getA() * getB();
                break;
            case 3:
                resultado = getA() / getB();
                break;
        }
        return resultado;
    }

    String getFactor() {
        return (String) cmbOperaciones.getSelectedItem();
    }

    String getTipo() {
        return (String) cmbTipo.getSelectedItem();
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
        String columnas[] = {"ID", "A", "B", "Resultado", "Factor"};
        tbl.setColumnIdentifiers(columnas);
    }

    void leerTodos() {
        llenarTabla();
        fila = new Object[tbl.getColumnCount()];
        operaciones = new ClsArregloOperaciones();
        for (int i = 0; i < operaciones.tamano(); i++) {
            fila[0] = operaciones.obtener(i).getId();
            fila[1] = operaciones.obtener(i).getA();
            fila[2] = operaciones.obtener(i).getB();
            fila[3] = operaciones.obtener(i).getResultado();
            fila[4] = operaciones.obtener(i).getFactor();
            tbl.addRow(fila);
        }
        tblDatos.setModel(tbl);
    }

    void buscarCodigo() {
        llenarTabla();
        fila = new Object[tbl.getColumnCount()];
        operaciones = new ClsArregloOperaciones();
        ClsOperacion operacion = operaciones.buscar(getId());
        if (operacion != null) {
            for (int i = 0; i < operaciones.tamano(); i++) {
                if (operaciones.obtener(i).getId() == operacion.getId()) {
                    operacion = operaciones.obtener(i);
                    fila[0] = operacion.getId();
                    fila[1] = operacion.getA();
                    fila[2] = operacion.getB();
                    fila[3] = operacion.getResultado();
                    fila[4] = operacion.getFactor();
                    tbl.addRow(fila);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay registros con ese ID");
        }
        limpiar();
    }

    void leerTipo() {
        llenarTabla();
        fila = new Object[tbl.getColumnCount()];
        operaciones = new ClsArregloOperaciones();
        ArrayList<ClsOperacion> lista = operaciones.buscarTodosTipos(getTipo());
        if (lista.size() > 0) {
            for (ClsOperacion operacion : lista) {
                fila[0] = operacion.getId();
                fila[1] = operacion.getA();
                fila[2] = operacion.getB();
                fila[3] = operacion.getResultado();
                fila[4] = operacion.getFactor();
                tbl.addRow(fila);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay datos de este tipo de operación");
        }
        tblDatos.setModel(tbl);
    }

    void grabar() {
        if (!txtA.getText().isEmpty() && !txtB.getText().isEmpty()) {
            if (getB() == 0 && getFactor().equals("Division")) {
                JOptionPane.showMessageDialog(null, "No se admiten las divisiones entre 0... :P");    
            } else {
                operaciones = new ClsArregloOperaciones();
                ClsOperacion operacion = new ClsOperacion(operaciones.ultimoId + 1, getA(), getB(), calcularResultado(), getFactor());
                operaciones.adicionar(operacion);
                operaciones.grabar();
                leerTodos();
                JOptionPane.showMessageDialog(null, "Registro guardado correctamente");
                limpiar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Falta ingresar un dato...");
        }

    }

    void actualizar() {
        operaciones = new ClsArregloOperaciones();
        ClsOperacion operacion = operaciones.buscar(getId());
        if (operacion != null) {
            operacion.setA(getA());
            operacion.setB(getB());
            operacion.setResultado(getResultado());
            operacion.setFactor(getFactor());
            operaciones.grabar();
            leerTodos();
            JOptionPane.showMessageDialog(null, "Registro modificado");
        } else {
            JOptionPane.showMessageDialog(null, "Registro no existente");
        }
        limpiar();
    }

    void eliminar() {
        operaciones = new ClsArregloOperaciones();
        ClsOperacion operacion = operaciones.buscar(getId());
        if (operacion != null) {
            operaciones.eliminar(operacion);
            operaciones.grabar();
            leerTodos();
            JOptionPane.showMessageDialog(null, "Registro eliminado");
        } else {
            JOptionPane.showMessageDialog(null, "Registro no existente");
        }
        limpiar();
    }

    void limpiar() {
        txtA.setText("");
        txtB.setText("");
        txtId.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtA = new javax.swing.JTextField();
        txtB = new javax.swing.JTextField();
        btnGrabar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLeerTodos = new javax.swing.JButton();
        btnLeerTipo = new javax.swing.JButton();
        btnBuscarCodigo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        cmbOperaciones = new javax.swing.JComboBox<>();
        txtId = new javax.swing.JTextField();
        labbel = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Numero A:");

        jLabel2.setText("Numero B:");

        jLabel3.setText("Operaciones :D");

        txtA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAKeyTyped(evt);
            }
        });

        txtB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBKeyTyped(evt);
            }
        });

        btnGrabar.setText("Grabar");
        btnGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrabarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnLeerTodos.setText("Leer Todos");
        btnLeerTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeerTodosActionPerformed(evt);
            }
        });

        btnLeerTipo.setText("Leer por tipo");
        btnLeerTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeerTipoActionPerformed(evt);
            }
        });

        btnBuscarCodigo.setText("Buscar por código");
        btnBuscarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCodigoActionPerformed(evt);
            }
        });

        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblDatos.setFocusCycleRoot(true);
        tblDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDatosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDatos);

        cmbOperaciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Suma", "Resta", "Multiplicacion", "Division" }));

        labbel.setText("ID:");

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Suma", "Resta", "Multiplicacion", "Division" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(334, 334, 334)
                        .addComponent(jLabel3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(jLabel1)
                                    .addGap(15, 15, 15))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtA, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(29, 29, 29)
                                    .addComponent(labbel)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnGrabar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLeerTodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(12, 12, 12)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnLeerTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBuscarCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labbel)
                            .addComponent(jLabel2)
                            .addComponent(btnActualizar)
                            .addComponent(btnLeerTodos)
                            .addComponent(btnBuscarCodigo)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGrabar)
                        .addComponent(cmbOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEliminar)
                    .addComponent(btnLeerTipo))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrabarActionPerformed
        grabar();
    }//GEN-LAST:event_btnGrabarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnLeerTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeerTodosActionPerformed
        leerTodos();
    }//GEN-LAST:event_btnLeerTodosActionPerformed

    private void btnLeerTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeerTipoActionPerformed
        leerTipo();
    }//GEN-LAST:event_btnLeerTipoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCodigoActionPerformed
        buscarCodigo();
    }//GEN-LAST:event_btnBuscarCodigoActionPerformed

    private void tblDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatosMouseClicked
        int filaseleccionada;
        try {
            filaseleccionada = tblDatos.getSelectedRow();
            if (filaseleccionada == -1) {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
            } else {
                DefaultTableModel modelotabla = (DefaultTableModel) tblDatos.getModel();
                int id = (int) modelotabla.getValueAt(filaseleccionada, 0);
                double a = (double) modelotabla.getValueAt(filaseleccionada, 1);
                double b = (double) modelotabla.getValueAt(filaseleccionada, 2);
                String factor = (String) modelotabla.getValueAt(filaseleccionada, 4);
                txtId.setText(String.valueOf(id));
                txtA.setText(String.valueOf(a));
                txtB.setText(String.valueOf(b));
                cmbOperaciones.setSelectedItem(factor);
            }

        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", " .::Error En la Operacion::.", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_tblDatosMouseClicked

    void validar(String evt) {

    }

    private void txtAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAKeyTyped
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)
                && (caracter != '.')) {
            JOptionPane.showMessageDialog(null, "¡Solo se admite agregar números!");
            evt.consume();
        }
    }//GEN-LAST:event_txtAKeyTyped

    private void txtBKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBKeyTyped
        char caracter = evt.getKeyChar();
        if (((caracter < '0') || (caracter > '9')) && (caracter != KeyEvent.VK_BACK_SPACE)
                && (caracter != '.')) {
            JOptionPane.showMessageDialog(null, "¡Solo se admite agregar números!");
            evt.consume();
        }
    }//GEN-LAST:event_txtBKeyTyped

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
            java.util.logging.Logger.getLogger(frmOperaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmOperaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmOperaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmOperaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmOperaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscarCodigo;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGrabar;
    private javax.swing.JButton btnLeerTipo;
    private javax.swing.JButton btnLeerTodos;
    private javax.swing.JComboBox<String> cmbOperaciones;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labbel;
    public javax.swing.JTable tblDatos;
    private javax.swing.JTextField txtA;
    private javax.swing.JTextField txtB;
    private javax.swing.JTextField txtId;
    // End of variables declaration//GEN-END:variables
}
