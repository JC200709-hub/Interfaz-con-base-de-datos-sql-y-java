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
 * Conexión y operaciones compartidas contra la base de datos SQLite.
 * Esta clase existe idéntica en los proyectos SistemaEnvio y TablaModificable,
 * por lo que ambos apuntan al MISMO archivo y a la MISMA tabla "envios".
 */
public final class ConexionDB {

    public static final String RUTA_DB =
            "C:/Users/reyes/OneDrive/Documentos/NetBeansProjects/BaseDatosEnvios/envios.db";
    public static final String URL = "jdbc:sqlite:" + RUTA_DB;

    private static final String CLAVE_ADMIN = "admin123";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private ConexionDB() {
    }

    public static Connection getConnection() throws SQLException {
        java.io.File directorio = new java.io.File(RUTA_DB).getParentFile();
        if (directorio != null && !directorio.exists()) {
            directorio.mkdirs();
        }
        Connection conn = DriverManager.getConnection(URL);
        inicializar(conn);
        return conn;
    }

    /** La contraseña solo se pide para modificar o borrar registros. */
    public static boolean verificarClave(char[] clave) {
        return clave != null && new String(clave).equals(CLAVE_ADMIN);
    }

    private static void inicializar(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS envios ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nombre TEXT NOT NULL,"
                + "identificacion TEXT NOT NULL,"
                + "direccion TEXT,"
                + "pais TEXT,"
                + "ciudad TEXT,"
                + "telefono TEXT,"
                + "tipo_envio TEXT,"
                + "camisetas INTEGER DEFAULT 0,"
                + "pantalones INTEGER DEFAULT 0,"
                + "zapatillas INTEGER DEFAULT 0,"
                + "gorras INTEGER DEFAULT 0,"
                + "subtotal INTEGER DEFAULT 0,"
                + "costo_envio INTEGER DEFAULT 0,"
                + "total INTEGER DEFAULT 0,"
                + "fecha TEXT DEFAULT CURRENT_TIMESTAMP"
                + ")";
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        }
    }

    public static void guardar(RegistroEnvio r) throws SQLException {
        String sql = "INSERT INTO envios (nombre, identificacion, direccion, pais, ciudad, "
                + "telefono, tipo_envio, camisetas, pantalones, zapatillas, gorras, "
                + "subtotal, costo_envio, total) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            enlazar(ps, r, 1);
            ps.executeUpdate();
        }
    }

    public static List<RegistroEnvio> listar() throws SQLException {
        String sql = "SELECT id, nombre, identificacion, direccion, pais, ciudad, telefono, "
                + "tipo_envio, camisetas, pantalones, zapatillas, gorras, "
                + "subtotal, costo_envio, total, fecha FROM envios ORDER BY id";
        List<RegistroEnvio> lista = new ArrayList<>();
        try (Connection c = getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new RegistroEnvio(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("identificacion"),
                        rs.getString("direccion"),
                        rs.getString("pais"),
                        rs.getString("ciudad"),
                        rs.getString("telefono"),
                        rs.getString("tipo_envio"),
                        rs.getInt("camisetas"),
                        rs.getInt("pantalones"),
                        rs.getInt("zapatillas"),
                        rs.getInt("gorras"),
                        rs.getInt("subtotal"),
                        rs.getInt("costo_envio"),
                        rs.getInt("total"),
                        rs.getString("fecha")));
            }
        }
        return lista;
    }

    public static void actualizar(int id, RegistroEnvio r) throws SQLException {
        String sql = "UPDATE envios SET nombre=?, identificacion=?, direccion=?, pais=?, "
                + "ciudad=?, telefono=?, tipo_envio=?, camisetas=?, pantalones=?, "
                + "zapatillas=?, gorras=?, subtotal=?, costo_envio=?, total=? WHERE id=?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            enlazar(ps, r, 1);
            ps.setInt(15, id);
            ps.executeUpdate();
        }
    }

    public static void eliminar(int id) throws SQLException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM envios WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private static void enlazar(PreparedStatement ps, RegistroEnvio r, int inicio)
            throws SQLException {
        int i = inicio;
        ps.setString(i++, r.getNombre());
        ps.setString(i++, r.getIdentificacion());
        ps.setString(i++, r.getDireccion());
        ps.setString(i++, r.getPais());
        ps.setString(i++, r.getCiudad());
        ps.setString(i++, r.getTelefono());
        ps.setString(i++, r.getTipoEnvio());
        ps.setInt(i++, r.getCamisetas());
        ps.setInt(i++, r.getPantalones());
        ps.setInt(i++, r.getZapatillas());
        ps.setInt(i++, r.getGorras());
        ps.setInt(i++, r.getSubtotal());
        ps.setInt(i++, r.getCostoEnvio());
        ps.setInt(i, r.getTotal());
    }
}
