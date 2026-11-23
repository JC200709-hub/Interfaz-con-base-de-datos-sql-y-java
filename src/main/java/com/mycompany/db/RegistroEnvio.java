package com.mycompany.db;

/**
 * Representa un registro de envío guardado en la base de datos compartida.
 * Esta clase existe idéntica en los proyectos SistemaEnvio y TablaModificable.
 */
public class RegistroEnvio {
    private int id;
    private String nombre;
    private String identificacion;
    private String direccion;
    private String pais;
    private String ciudad;
    private String telefono;
    private String tipoEnvio;
    private int camisetas;
    private int pantalones;
    private int zapatillas;
    private int gorras;
    private int subtotal;
    private int costoEnvio;
    private int total;
    private String fecha;

    /** Constructor para un registro nuevo (sin id ni fecha). */
    public RegistroEnvio(String nombre, String identificacion, String direccion,
            String pais, String ciudad, String telefono, String tipoEnvio,
            int camisetas, int pantalones, int zapatillas, int gorras,
            int subtotal, int costoEnvio, int total) {
        this(0, nombre, identificacion, direccion, pais, ciudad, telefono,
                tipoEnvio, camisetas, pantalones, zapatillas, gorras,
                subtotal, costoEnvio, total, null);
    }

    /** Constructor completo, usado al leer desde la base de datos. */
    public RegistroEnvio(int id, String nombre, String identificacion, String direccion,
            String pais, String ciudad, String telefono, String tipoEnvio,
            int camisetas, int pantalones, int zapatillas, int gorras,
            int subtotal, int costoEnvio, int total, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.pais = pais;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.tipoEnvio = tipoEnvio;
        this.camisetas = camisetas;
        this.pantalones = pantalones;
        this.zapatillas = zapatillas;
        this.gorras = gorras;
        this.subtotal = subtotal;
        this.costoEnvio = costoEnvio;
        this.total = total;
        this.fecha = fecha;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getIdentificacion() { return identificacion; }
    public String getDireccion() { return direccion; }
    public String getPais() { return pais; }
    public String getCiudad() { return ciudad; }
    public String getTelefono() { return telefono; }
    public String getTipoEnvio() { return tipoEnvio; }
    public int getCamisetas() { return camisetas; }
    public int getPantalones() { return pantalones; }
    public int getZapatillas() { return zapatillas; }
    public int getGorras() { return gorras; }
    public int getSubtotal() { return subtotal; }
    public int getCostoEnvio() { return costoEnvio; }
    public int getTotal() { return total; }
    public String getFecha() { return fecha; }
}
