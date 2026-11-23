package com.mycompany.Visual;

import com.mycompany.db.ConexionCalorias;
import com.mycompany.db.RegistroCalorias;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Ventana de base de datos: primero muestra un login y, si las credenciales
 * son correctas, sustituye el formulario por una JTable con los registros.
 */
public class VentanaTablas extends javax.swing.JFrame {

    private static final String USUARIO_VALIDO = "Admin";
    private static final String CLAVE_VALIDA = "Admin123";

    private javax.swing.JPanel panelContenido;
    private javax.swing.JTable jTable1;
    private javax.swing.JScrollPane jScrollPane1;

    public VentanaTablas() {
        initComponents();
        panelContenido = (javax.swing.JPanel) getContentPane();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Usuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        Contrasena = new javax.swing.JPasswordField();
        Ingresar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Base de datos - Acceso");

        loginPanel.setPreferredSize(new java.awt.Dimension(430, 280));
        loginPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(235, 240, 245));
        jLabel1.setText("BASE DE DATOS");
        loginPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 20, 250, 35));

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(235, 240, 245));
        jLabel2.setText("Usuario:");
        loginPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 85, 95, 25));

        Usuario.setColumns(15);
        loginPanel.add(Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 85, 205, 26));

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(235, 240, 245));
        jLabel3.setText("Contraseña:");
        loginPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 125, 95, 25));

        Contrasena.setColumns(15);
        loginPanel.add(Contrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 125, 205, 26));

        Ingresar.setText("Ingresar");
        Ingresar.addActionListener(this::IngresarActionPerformed);
        loginPanel.add(Ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 175, 130, 32));

        jLabel4.setForeground(new java.awt.Color(235, 240, 245));
        jLabel4.setText("Acceso de administrador: Admin / Admin123");
        loginPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 235, 330, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IngresarActionPerformed
        String usuario = Usuario.getText().trim();
        String contrasena = new String(Contrasena.getPassword());

        if (USUARIO_VALIDO.equals(usuario) && CLAVE_VALIDA.equals(contrasena)) {
            mostrarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.",
                    "Acceso denegado", JOptionPane.ERROR_MESSAGE);
            Contrasena.setText("");
            Contrasena.requestFocus();
        }
    }//GEN-LAST:event_IngresarActionPerformed

    private void mostrarTabla() {
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"ID", "Tipo", "Alimento", "Cantidad", "Preparación", "Calorías"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        try {
            for (RegistroCalorias r : ConexionCalorias.listar()) {
                modelo.addRow(new Object[]{
                    r.getId(), r.getTipo(), r.getAlimento(), r.getCantidad(),
                    r.getPreparacion(), r.getCalorias()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo leer la base de datos:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        jTable1 = new javax.swing.JTable();
        jTable1.setModel(modelo);
        jTable1.setRowHeight(24);
        jScrollPane1 = new javax.swing.JScrollPane(jTable1);

        panelContenido.remove(loginPanel);
        // El content pane viene con GroupLayout desde el diseñador; hay que
        // cambiarlo a BorderLayout para que el scroll ocupe toda la ventana.
        panelContenido.setLayout(new java.awt.BorderLayout());
        panelContenido.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        panelContenido.revalidate();
        panelContenido.repaint();

        setSize(780, 520);
        setLocationRelativeTo(null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Contrasena;
    private javax.swing.JButton Ingresar;
    private javax.swing.JTextField Usuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel loginPanel;
    // End of variables declaration//GEN-END:variables
}
