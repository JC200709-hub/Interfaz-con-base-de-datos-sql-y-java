package com.mycompany.db;

/**
 * Representa una fila de la tabla "calorias" de la base de datos SQLite.
 */
public class RegistroCalorias {

    private int id;
    private final String tipo;
    private final String alimento;
    private final int cantidad;
    private final String preparacion;
    private final double calorias;

    public RegistroCalorias(String tipo, String alimento, int cantidad,
            String preparacion, double calorias) {
        this(0, tipo, alimento, cantidad, preparacion, calorias);
    }

    public RegistroCalorias(int id, String tipo, String alimento, int cantidad,
            String preparacion, double calorias) {
        this.id = id;
        this.tipo = tipo;
        this.alimento = alimento;
        this.cantidad = cantidad;
        this.preparacion = preparacion;
        this.calorias = calorias;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getAlimento() {
        return alimento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public double getCalorias() {
        return calorias;
    }
}
