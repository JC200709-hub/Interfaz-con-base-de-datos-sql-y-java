package com.mycompany.Visual;

import com.mycompany.db.ConexionCalorias;
import com.mycompany.db.RegistroCalorias;
import java.sql.SQLException;
import java.util.List;
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

    // Copia de los registros de la base de datos, sincronizada con la tabla
    private List<RegistroCalorias> registros;

    // Estado de la edición: -1 significa que no hay ninguna celda en edición
    private int filaEditando = -1;
    private int columnaEditando = -1;
    private String valorPendiente;

    private javax.swing.JButton Modificar;
    private javax.swing.JButton Confirmar;
    private javax.swing.JPanel panelBotones;

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
        jLabel5 = new javax.swing.JLabel();

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

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/Recursos/ImagenFondo2.jpg"))); // NOI18N
        loginPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 280));

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
            registros = ConexionCalorias.listar();
            for (RegistroCalorias r : registros) {
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

        // Botonera: "Modificar" siempre visible, "Confirmar" solo al editar
        panelBotones = new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
        Modificar = new javax.swing.JButton("Modificar");
        Modificar.addActionListener(this::ModificarActionPerformed);
        panelBotones.add(Modificar);
        panelContenido.add(panelBotones, java.awt.BorderLayout.SOUTH);

        panelContenido.revalidate();
        panelContenido.repaint();

        setSize(780, 520);
        setLocationRelativeTo(null);
    }

    // ---------- Modificar / Confirmar ----------

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {
        // Si ya hay una edición en curso, no crear otro botón Confirmar
        if (filaEditando != -1 || Confirmar != null) {
            return;
        }

        int filaVista = jTable1.getSelectedRow();
        int columnaVista = jTable1.getSelectedColumn();

        if (filaVista == -1 || columnaVista == -1) {
            JOptionPane.showMessageDialog(this,
                    "Selecciona una celda de la tabla antes de modificar.",
                    "Modificar", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Guardamos la celda del modelo, aunque luego cambie la selección
        filaEditando = jTable1.convertRowIndexToModel(filaVista);
        columnaEditando = jTable1.convertColumnIndexToModel(columnaVista);

        if (columnaEditando == 0) {
            terminarEdicion();
            JOptionPane.showMessageDialog(this, "El ID no se puede modificar.",
                    "Modificar", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object valorOriginal = jTable1.getModel().getValueAt(filaEditando, columnaEditando);

        // Aparece Confirmar antes de abrir el editor
        Confirmar = new javax.swing.JButton("Confirmar");
        Confirmar.addActionListener(this::ConfirmarActionPerformed);
        panelBotones.add(Confirmar);
        Modificar.setEnabled(false);
        panelBotones.revalidate();
        panelBotones.repaint();

        // Editor de la celda: muestra el valor actual como referencia
        String texto = JOptionPane.showInputDialog(this,
                "Nuevo valor (actual: " + valorOriginal + "):",
                "Modificar celda", JOptionPane.QUESTION_MESSAGE);

        if (texto == null || texto.trim().isEmpty()) {
            terminarEdicion();   // canceló o dejó vacío: no se cambia nada
            return;
        }
        valorPendiente = texto.trim();   // todavía no se escribe en la tabla
    }

    private void ConfirmarActionPerformed(java.awt.event.ActionEvent evt) {
        if (filaEditando == -1 || valorPendiente == null) {
            terminarEdicion();
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();

        // El tipo se detecta mirando el valor original de esa celda
        Object valorOriginal = modelo.getValueAt(filaEditando, columnaEditando);
        Class<?> tipo = (valorOriginal != null)
                ? valorOriginal.getClass()
                : modelo.getColumnClass(columnaEditando);

        Object valorConvertido = convertirValor(valorPendiente, tipo);
        if (valorConvertido == null) {
            JOptionPane.showMessageDialog(this,
                    "El valor no corresponde al tipo de esta columna ("
                    + tipo.getSimpleName() + ").",
                    "Tipo incorrecto", JOptionPane.ERROR_MESSAGE);
            return;   // la celda no se toca; Confirmar sigue para reintentar
        }

        // Se guarda ya convertido, conservando su tipo
        modelo.setValueAt(valorConvertido, filaEditando, columnaEditando);
        guardarCambioEnBaseDeDatos(valorConvertido);
        terminarEdicion();
    }

    /** Convierte el texto al mismo tipo que tenía la celda. */
    private Object convertirValor(String texto, Class<?> tipo) {
        try {
            if (tipo == Integer.class) return Integer.valueOf(texto);
            if (tipo == Long.class)    return Long.valueOf(texto);
            if (tipo == Double.class)  return Double.valueOf(texto);
            if (tipo == Float.class)   return Float.valueOf(texto);
            if (tipo == Short.class)   return Short.valueOf(texto);
            if (tipo == Byte.class)    return Byte.valueOf(texto);
            if (tipo == Boolean.class) {
                if ("true".equalsIgnoreCase(texto) || "false".equalsIgnoreCase(texto)) {
                    return Boolean.valueOf(texto);
                }
                return null;
            }
            return texto;   // String u otro tipo: se guarda como texto
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /** Mantiene sincronizada la lista de registros y la base de datos. */
    private void guardarCambioEnBaseDeDatos(Object valor) {
        if (registros == null || filaEditando >= registros.size()) {
            return;
        }
        RegistroCalorias actual = registros.get(filaEditando);
        RegistroCalorias nuevo = new RegistroCalorias(
                actual.getId(),
                columnaEditando == 1 ? (String) valor : actual.getTipo(),
                columnaEditando == 2 ? (String) valor : actual.getAlimento(),
                columnaEditando == 3 ? (Integer) valor : actual.getCantidad(),
                columnaEditando == 4 ? (String) valor : actual.getPreparacion(),
                columnaEditando == 5 ? (Double) valor : actual.getCalorias());
        try {
            ConexionCalorias.actualizar(nuevo);
            registros.set(filaEditando, nuevo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "La celda cambió, pero no se pudo actualizar la base de datos:\n"
                    + e.getMessage(),
                    "Base de datos", JOptionPane.WARNING_MESSAGE);
        }
    }

    /** Quita Confirmar y deja todo listo para otra modificación. */
    private void terminarEdicion() {
        if (Confirmar != null) {
            panelBotones.remove(Confirmar);
            Confirmar = null;
        }
        Modificar.setEnabled(true);
        filaEditando = -1;
        columnaEditando = -1;
        valorPendiente = null;
        panelBotones.revalidate();
        panelBotones.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Contrasena;
    private javax.swing.JButton Ingresar;
    private javax.swing.JTextField Usuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel loginPanel;
    // End of variables declaration//GEN-END:variables
}
