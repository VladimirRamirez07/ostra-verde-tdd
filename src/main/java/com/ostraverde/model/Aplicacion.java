package com.ostraverde.model;

import java.time.LocalDate;

public class Aplicacion {

    private String codigo;
    private LocalDate fechaAplicacion;
    private String codigoSiembra;
    private String codigoProducto;
    private double cantidad;
    private String codigoResponsable;
    private String observaciones;

    public Aplicacion(String codigo, LocalDate fechaAplicacion, String codigoSiembra, String codigoProducto, double cantidad, String codigoResponsable, String observaciones) {
        this.codigo = codigo;
        this.fechaAplicacion = fechaAplicacion;
        this.codigoSiembra = codigoSiembra;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.codigoResponsable = codigoResponsable;
        this.observaciones = observaciones;
    }

    public String getCodigo() { return codigo; }
    public LocalDate getFechaAplicacion() { return fechaAplicacion; }
    public String getCodigoSiembra() { return codigoSiembra; }
    public String getCodigoProducto() { return codigoProducto; }
    public double getCantidad() { return cantidad; }
    public String getCodigoResponsable() { return codigoResponsable; }
    public String getObservaciones() { return observaciones; }
}