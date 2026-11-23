

package com.mycompany.Visual;
import com.mycompany.db.ConexionCalorias;
import com.mycompany.db.RegistroCalorias;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class VentanaDatos extends javax.swing.JFrame {
    private Map<String, String[]> ComidaPorTipo = new LinkedHashMap<>();
    private Map<String, Double> caloriasPor100g = new HashMap<>();
    private Map<String, Double> factorCoccion = new HashMap<>();
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaDatos.class.getName());

    public VentanaDatos() {
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
        if (base == null || factor == null) {
            return -1;
        }
        return base * gramos / 100.0 * factor;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TipoComida = new javax.swing.JComboBox<>();
        Comida = new javax.swing.JComboBox<>();
        TipoPreparacion = new javax.swing.JComboBox<>();
        Cantidad = new javax.swing.JComboBox<>();
        BaseDatos = new javax.swing.JButton();
        Continuar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(235, 240, 245));
        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(235, 240, 245));
        jLabel2.setText("Calculadora de calorias ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 40));

        jLabel1.setBackground(new java.awt.Color(235, 240, 245));
        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(235, 240, 245));
        jLabel1.setText("TIPO DE ALIMENTO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        TipoComida.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        TipoComida.setForeground(new java.awt.Color(235, 240, 245));
        TipoComida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Carbohidratos", "Proteinas", "Lacteos y grasas", " " }));
        TipoComida.addActionListener(this::TipoComidaActionPerformed);
        jPanel1.add(TipoComida, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 150, 40));

        Comida.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        Comida.setForeground(new java.awt.Color(235, 240, 245));
        Comida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        Comida.setSelectedIndex(-1);
        Comida.addActionListener(this::ComidaActionPerformed);
        jPanel1.add(Comida, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 150, 40));

        TipoPreparacion.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        TipoPreparacion.setForeground(new java.awt.Color(235, 240, 245));
        TipoPreparacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Crudo", "Hervido", "Asado", "Frito" }));
        jPanel1.add(TipoPreparacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 150, 40));

        Cantidad.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        Cantidad.setForeground(new java.awt.Color(235, 240, 245));
        Cantidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "50", "100", "150", "200", "250", "300", "500" }));
        Cantidad.setSelectedIndex(1);
        jPanel1.add(Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 150, 40));

        BaseDatos.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        BaseDatos.setForeground(new java.awt.Color(235, 240, 245));
        BaseDatos.setText("Base de datos");
        BaseDatos.addActionListener(this::BaseDatosActionPerformed);
        jPanel1.add(BaseDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 151, 39));

        Continuar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        Continuar.setForeground(new java.awt.Color(235, 240, 245));
        Continuar.setText("Guardar ");
        Continuar.addActionListener(this::ContinuarActionPerformed);
        jPanel1.add(Continuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 520, 151, 39));

        jLabel6.setBackground(new java.awt.Color(235, 240, 245));
        jLabel6.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(235, 240, 245));
        jLabel6.setText("PREPARACION");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        jLabel7.setBackground(new java.awt.Color(235, 240, 245));
        jLabel7.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(235, 240, 245));
        jLabel7.setText("CANTIDAD (gr)");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        jLabel8.setBackground(new java.awt.Color(235, 240, 245));
        jLabel8.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(235, 240, 245));
        jLabel8.setText("ALIMENTO");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/Recursos/ImagenFondo.jpg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 600));

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

        int gramos;
        try {
            gramos = Integer.parseInt((String) Cantidad.getSelectedItem());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Escoge una cantidad válida");
            return;
        }

        if (alimento == null || alimento.trim().isEmpty() || gramos <= 0) {
            JOptionPane.showMessageDialog(this, "Escoge un alimento y una cantidad mayor a 0");
            return;
        }

        double calorias = calcularCalorias(alimento, gramos, preparacion);
        if (calorias < 0) {
            JOptionPane.showMessageDialog(this, "Escoge un alimento y una preparación válidos");
            return;
        }

        double total = Math.round(calorias * 10) / 10.0;

        try {
            ConexionCalorias.guardar(new RegistroCalorias(tipo, alimento, gramos, preparacion, total));
            JOptionPane.showMessageDialog(this,
                    "Registro guardado.\n" + alimento + " (" + gramos + " g, " + preparacion + "): "
                    + total + " calorías.",
                    "Resultado", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo guardar en la base de datos:\n" + ex.getMessage(),
                    "Base de datos", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_ContinuarActionPerformed

    private void BaseDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BaseDatosActionPerformed
        new VentanaTablas().setVisible(true);
    }//GEN-LAST:event_BaseDatosActionPerformed

    private void ComidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComidaActionPerformed
        
    }//GEN-LAST:event_ComidaActionPerformed

    private void TipoComidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoComidaActionPerformed
        actualizarComida();        
    }//GEN-LAST:event_TipoComidaActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new VentanaDatos().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BaseDatos;
    private javax.swing.JComboBox<String> Cantidad;
    private javax.swing.JComboBox<String> Comida;
    private javax.swing.JButton Continuar;
    private javax.swing.JComboBox<String> TipoComida;
    private javax.swing.JComboBox<String> TipoPreparacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
