package com.ostraverde.model;

public class Producto {

    private String codigo;
    private String nombre;
    private String tipo;
    private String descripcion;
    private String unidadMedida;

    public Producto(String codigo, String nombre, String tipo, String descripcion, String unidadMedida) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public String getDescripcion() { return descripcion; }
    public String getUnidadMedida() { return unidadMedida; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }
}