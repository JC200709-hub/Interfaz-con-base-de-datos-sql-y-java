package com.mycompany.Visual;

import com.mycompany.db.ConexionDB;
import com.mycompany.db.RegistroEnvio;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 * Panel de administrador: consulta la base de datos compartida SQLite.
 * Sin contraseña solo permite visualizar; con la contraseña se habilitan
 * los botones de actualizar y borrar.
 */
public class PanelAdministrador extends JFrame {

    private static final String[] CABECERAS = {
        "ID", "Nombre", "Identificación", "Dirección", "País", "Ciudad",
        "Teléfono", "Tipo envío", "Total", "Fecha"
    };

    private final DefaultTableModel modelo;
    private final JTable tabla;
    private final JPasswordField clave;
    private final JButton btnAcceder;
    private final JButton btnActualizar;
    private final JButton btnBorrar;
    private final JLabel estado;

    private List<RegistroEnvio> registros;
    private boolean autorizado = false;

    public PanelAdministrador() {
        super("Panel de administración - Base de datos");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel fondo = new JPanel(null);
        fondo.setPreferredSize(new Dimension(800, 560));
        fondo.setBackground(new Color(27, 35, 46));

        JPanel contenido = new JPanel(null);
        contenido.setOpaque(false);
        contenido.setBounds(15, 15, 770, 530);
        fondo.add(contenido);

        JLabel titulo = new JLabel("PANEL DE ADMINISTRACIÓN - BASE DE DATOS");
        titulo.setFont(new Font("Arial Black", Font.PLAIN, 17));
        titulo.setForeground(new Color(235, 240, 245));
        titulo.setBounds(15, 8, 700, 30);
        contenido.add(titulo);

        modelo = new DefaultTableModel(new Object[][]{}, CABECERAS) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setRowHeight(22);
        tabla.setBackground(new Color(245, 247, 250));
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(15, 45, 740, 355);
        contenido.add(scroll);

        JLabel etiquetaClave = new JLabel("Contraseña:");
        etiquetaClave.setForeground(new Color(235, 240, 245));
        etiquetaClave.setBounds(15, 415, 90, 25);
        contenido.add(etiquetaClave);

        clave = new JPasswordField();
        clave.setBounds(105, 415, 150, 25);
        contenido.add(clave);

        btnAcceder = new JButton("Acceder");
        btnAcceder.addActionListener(e -> acceder());
        btnAcceder.setBounds(265, 415, 100, 25);
        contenido.add(btnAcceder);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setEnabled(false);
        btnActualizar.addActionListener(e -> actualizar());
        btnActualizar.setBounds(380, 415, 115, 25);
        contenido.add(btnActualizar);

        btnBorrar = new JButton("Borrar");
        btnBorrar.setEnabled(false);
        btnBorrar.addActionListener(e -> borrar());
        btnBorrar.setBounds(505, 415, 90, 25);
        contenido.add(btnBorrar);

        JButton btnRecargar = new JButton("Recargar");
        btnRecargar.addActionListener(e -> cargarDatos());
        btnRecargar.setBounds(605, 415, 100, 25);
        contenido.add(btnRecargar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> dispose());
        btnSalir.setBounds(715, 415, 55, 25);
        contenido.add(btnSalir);

        estado = new JLabel("Solo lectura. Ingrese la contraseña para modificar registros.");
        estado.setForeground(new Color(255, 220, 150));
        estado.setBounds(15, 455, 740, 25);
        contenido.add(estado);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("Panel de administración cerrado.");
            }
        });

        setContentPane(fondo);
        pack();
        setLocationRelativeTo(null);
        cargarDatos();
    }

    private void acceder() {
        if (ConexionDB.verificarClave(clave.getPassword())) {
            autorizado = true;
            btnActualizar.setEnabled(true);
            btnBorrar.setEnabled(true);
            estado.setText("Modo administrador: puede actualizar y borrar registros.");
            estado.setForeground(new Color(180, 255, 180));
        } else {
            autorizado = false;
            btnActualizar.setEnabled(false);
            btnBorrar.setEnabled(false);
            estado.setText("Solo lectura. Contraseña incorrecta.");
            estado.setForeground(new Color(255, 180, 180));
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta.",
                    "Acceso denegado", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatos() {
        modelo.setRowCount(0);
        try {
            registros = ConexionDB.listar();
            for (RegistroEnvio r : registros) {
                modelo.addRow(new Object[]{
                    r.getId(), r.getNombre(), r.getIdentificacion(), r.getDireccion(),
                    r.getPais(), r.getCiudad(), r.getTelefono(), r.getTipoEnvio(),
                    r.getTotal(), r.getFecha()
                });
            }
            if (!autorizado) {
                estado.setText("Solo lectura. " + registros.size() + " registro(s) cargado(s).");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al leer la base de datos:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizar() {
        if (!autorizado) {
            JOptionPane.showMessageDialog(this, "Debe ingresar la contraseña para modificar registros.",
                    "Acceso denegado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila de la tabla.");
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        RegistroEnvio original = buscar(id);
        if (original == null) {
            JOptionPane.showMessageDialog(this, "No se encontró el registro.");
            return;
        }
        abrirEditor(original);
    }

    private RegistroEnvio buscar(int id) {
        if (registros == null) {
            return null;
        }
        for (RegistroEnvio r : registros) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    private void abrirEditor(RegistroEnvio original) {
        JDialog dialogo = new JDialog(this, "Modificar registro #" + original.getId(), true);
        dialogo.setLayout(new GridLayout(9, 2, 8, 8));

        String[] etiquetas = {"Nombre", "Identificación", "Dirección", "País", "Ciudad",
            "Teléfono", "Tipo de envío", "Total"};
        String[] valores = {
            original.getNombre(), original.getIdentificacion(), original.getDireccion(),
            original.getPais(), original.getCiudad(), original.getTelefono(),
            original.getTipoEnvio(), String.valueOf(original.getTotal())
        };
        JTextField[] campos = new JTextField[valores.length];
        for (int i = 0; i < etiquetas.length; i++) {
            dialogo.add(new JLabel(etiquetas[i]));
            campos[i] = new JTextField(valores[i] == null ? "" : valores[i]);
            dialogo.add(campos[i]);
        }

        JButton guardar = new JButton("Guardar");
        guardar.addActionListener(e -> {
            try {
                int total = Integer.parseInt(campos[7].getText().trim());
                RegistroEnvio modificado = new RegistroEnvio(
                        original.getId(),
                        campos[0].getText().trim(), campos[1].getText().trim(),
                        campos[2].getText().trim(), campos[3].getText().trim(),
                        campos[4].getText().trim(), campos[5].getText().trim(),
                        campos[6].getText().trim(),
                        original.getCamisetas(), original.getPantalones(),
                        original.getZapatillas(), original.getGorras(),
                        original.getSubtotal(), original.getCostoEnvio(),
                        total, original.getFecha());
                ConexionDB.actualizar(original.getId(), modificado);
                dialogo.dispose();
                cargarDatos();
                JOptionPane.showMessageDialog(this, "Registro actualizado.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialogo, "El total debe ser un número entero.",
                        "Dato inválido", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(dialogo, "Error al actualizar:\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        dialogo.add(guardar);

        JButton cancelar = new JButton("Cancelar");
        cancelar.addActionListener(e -> dialogo.dispose());
        dialogo.add(cancelar);

        dialogo.pack();
        dialogo.setLocationRelativeTo(this);
        dialogo.setVisible(true);
    }

    private void borrar() {
        if (!autorizado) {
            JOptionPane.showMessageDialog(this, "Debe ingresar la contraseña para borrar registros.",
                    "Acceso denegado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila de la tabla.");
            return;
        }
        int id = (int) modelo.getValueAt(fila, 0);
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Borrar el registro #" + id + "?", "Confirmar borrado",
                JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                ConexionDB.eliminar(id);
                cargarDatos();
                JOptionPane.showMessageDialog(this, "Registro borrado.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al borrar:\n" + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new PanelAdministrador().setVisible(true));
    }
}
