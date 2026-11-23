package com.mycompany.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Conexión SQLite local para los registros de la calculadora.
 * El archivo calorias.db se crea en la carpeta de trabajo del proyecto (o
 * junto al ejecutable, si se ejecuta desde una carpeta distinta).
 */
public final class ConexionCalorias {

    public static final String RUTA_DB =
            System.getProperty("user.dir") + "/calorias.db";
    public static final String URL = "jdbc:sqlite:" + RUTA_DB;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private ConexionCalorias() {
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        inicializar(conn);
        return conn;
    }

    /** Crea la tabla automáticamente si todavía no existe. */
    private static void inicializar(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS calorias ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "tipo TEXT,"
                + "alimento TEXT,"
                + "cantidad INTEGER,"
                + "preparacion TEXT,"
                + "calorias REAL"
                + ")";
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        }
    }

    public static void guardar(RegistroCalorias r) throws SQLException {
        String sql = "INSERT INTO calorias "
                + "(tipo, alimento, cantidad, preparacion, calorias) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, r.getTipo());
            ps.setString(2, r.getAlimento());
            ps.setInt(3, r.getCantidad());
            ps.setString(4, r.getPreparacion());
            ps.setDouble(5, r.getCalorias());
            ps.executeUpdate();
        }
    }

    /** Actualiza un registro existente usando su id. */
    public static void actualizar(RegistroCalorias r) throws SQLException {
        String sql = "UPDATE calorias SET tipo=?, alimento=?, cantidad=?, "
                + "preparacion=?, calorias=? WHERE id=?";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, r.getTipo());
            ps.setString(2, r.getAlimento());
            ps.setInt(3, r.getCantidad());
            ps.setString(4, r.getPreparacion());
            ps.setDouble(5, r.getCalorias());
            ps.setInt(6, r.getId());
            ps.executeUpdate();
        }
    }

    public static List<RegistroCalorias> listar() throws SQLException {
        String sql = "SELECT id, tipo, alimento, cantidad, preparacion, calorias "
                + "FROM calorias ORDER BY id";
        List<RegistroCalorias> registros = new ArrayList<>();
        try (Connection c = getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                registros.add(new RegistroCalorias(
                        rs.getInt("id"),
                        rs.getString("tipo"),
                        rs.getString("alimento"),
                        rs.getInt("cantidad"),
                        rs.getString("preparacion"),
                        rs.getDouble("calorias")));
            }
        }
        return registros;
    }
}
