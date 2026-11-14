

package com.mycompany.Visual;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class VentanaPrincipal extends javax.swing.JFrame {
    private Map<String, String[]> ComidaPorTipo = new LinkedHashMap<>();
    private Map<String, Double> caloriasPor100g = new HashMap<>();
    private Map<String, Double> factorCoccion = new HashMap<>();
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName());

    public VentanaPrincipal() {
        initComponents();
        llenarCalorias();
        ComidaPorTipo.put("Carbohidratos", new String[]{"Arroz", "Pan", "Pasta", "Papa", "Platano", "Manzana"});
        ComidaPorTipo.put("Proteinas", new String[]{"Pollo", "Carne de Res", "Huevo", "Atun", "Lentejas", "Frijoles"});
        ComidaPorTipo.put("Lacteos y grasas", new String[]{"Leche", "Queso", "Aceite", "Aguacate"});

        // Los países del combo salen de las claves del Map
        TipoComida.setModel(new javax.swing.DefaultComboBoxModel<>(
                ComidaPorTipo.keySet().toArray(new String[0])));

        // Llena las ciudades del primer país que quedó seleccionado
        actualizarComida();
        
    }
    private void actualizarComida() {
    String Tipo= (String) TipoComida.getSelectedItem();
    String[] ComidaTipo = ComidaPorTipo.get(Tipo);
    Comida.setModel(new javax.swing.DefaultComboBoxModel<>(ComidaTipo));
}
    private void llenarCalorias() {
    // kcal por cada 100 g
    caloriasPor100g.put("Arroz", 130.0);
    caloriasPor100g.put("Pan", 265.0);
    caloriasPor100g.put("Pasta", 158.0);
    caloriasPor100g.put("Papa", 87.0);
    caloriasPor100g.put("Platano", 122.0);
    caloriasPor100g.put("Manzana", 52.0);
    caloriasPor100g.put("Pollo", 165.0);
    caloriasPor100g.put("Carne de Res", 250.0);
    caloriasPor100g.put("Huevo", 143.0);
    caloriasPor100g.put("Atun", 116.0);
    caloriasPor100g.put("Lentejas", 116.0);
    caloriasPor100g.put("Frijoles", 127.0);
    caloriasPor100g.put("Leche", 61.0);
    caloriasPor100g.put("Queso", 300.0);
    caloriasPor100g.put("Aceite", 884.0);
    caloriasPor100g.put("Aguacate", 160.0);

    // factor que se multiplica según la preparación
    factorCoccion.put("Crudo", 1.0);
    factorCoccion.put("Hervido", 1.0);
    factorCoccion.put("Asado", 1.1);
    factorCoccion.put("Frito", 1.3);
}
    private double calcularCalorias(String alimento, int gramos, String preparacion) {
    Double base = caloriasPor100g.get(alimento);
    Double factor = factorCoccion.get(preparacion);

    return base * gramos / 100.0 * factor;
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        TipoComida = new javax.swing.JComboBox<>();
        Comida = new javax.swing.JComboBox<>();
        TipoPreparacion = new javax.swing.JComboBox<>();
        Cantidad = new javax.swing.JSpinner();
        Continuar = new javax.swing.JButton();
        Eliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Showcard Gothic", 0, 24)); // NOI18N
        jLabel2.setText("Calculadora de calorias ");

        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel1.setText("tipo de alimento");

        jLabel3.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel3.setText("alimento");

        jLabel4.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel4.setText("cantidad (gr)");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tipo ", "Alimento", "Cantidad", "Preparación", "Calorias"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel5.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        jLabel5.setText("preparacion");

        TipoComida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Carbohidratos", "Proteinas", "Lacteos y grasas", " " }));
        TipoComida.addActionListener(this::TipoComidaActionPerformed);

        Comida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        Comida.setSelectedIndex(-1);
        Comida.addActionListener(this::ComidaActionPerformed);

        TipoPreparacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Crudo", "Hervido", "Asado", "Frito" }));

        Continuar.setText("Guardar y Continuar");
        Continuar.addActionListener(this::ContinuarActionPerformed);

        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(this::EliminarActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(Continuar, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TipoComida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(99, 99, 99)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TipoPreparacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Comida, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Cantidad))))
                        .addGap(0, 45, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Eliminar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TipoComida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Comida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TipoPreparacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(Continuar, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Eliminar)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContinuarActionPerformed
        String tipo = (String) TipoComida.getSelectedItem();
    String alimento = (String) Comida.getSelectedItem();
    String preparacion = (String) TipoPreparacion.getSelectedItem();
    int gramos = (int) Cantidad.getValue();

    if (gramos <= 0) {
        JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0");
        return;
    }

    double calorias = calcularCalorias(alimento, gramos, preparacion);
    if (calorias < 0) {
        JOptionPane.showMessageDialog(this, "Escoge un alimento y una preparación válidos");
        return;
    }

    DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
    modelo.addRow(new Object[]{tipo, alimento, gramos, preparacion, Math.round(calorias * 10) / 10.0});
    }//GEN-LAST:event_ContinuarActionPerformed

    private void ComidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComidaActionPerformed
        
    }//GEN-LAST:event_ComidaActionPerformed

    private void TipoComidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoComidaActionPerformed
        actualizarComida();        
    }//GEN-LAST:event_TipoComidaActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    int fila = jTable1.getSelectedRow();
    if (fila == -1) {
        return;
    }

    String tipo = (String) jTable1.getValueAt(fila, 0);
    String alimento = (String) jTable1.getValueAt(fila, 1);
    Object cantidad = jTable1.getValueAt(fila, 2);
    String preparacion = (String) jTable1.getValueAt(fila, 3);

    // Primero el tipo: al cambiarlo se recarga la lista de alimentos
    TipoComida.setSelectedItem(tipo);
    Comida.setSelectedItem(alimento);
    Cantidad.setValue(cantidad);
    TipoPreparacion.setSelectedItem(preparacion);
    }//GEN-LAST:event_jTable1MouseClicked

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Primero selecciona una fila de la tabla");
        return;
    }
    ((DefaultTableModel) jTable1.getModel()).removeRow(fila);
    }//GEN-LAST:event_EliminarActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner Cantidad;
    private javax.swing.JComboBox<String> Comida;
    private javax.swing.JButton Continuar;
    private javax.swing.JButton Eliminar;
    private javax.swing.JComboBox<String> TipoComida;
    private javax.swing.JComboBox<String> TipoPreparacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
