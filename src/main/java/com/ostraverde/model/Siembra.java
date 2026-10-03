package com.ostraverde.model;

import java.time.LocalDate;

public class Siembra {
    private String codigo;
    private LocalDate fechaSiembra;
    private String codigoSeccion;
    private int cantidadSustratos;
    private String codigoResponsable;
    private String observaciones;
    private boolean cosechada;

    public Siembra(String codigo, LocalDate fechaSiembra, String codigoSeccion, int cantidadSustratos, String codigoResponsable, String observaciones) {
        this.codigo = codigo;
        this.fechaSiembra = fechaSiembra;
        this.codigoSeccion = codigoSeccion;
        this.cantidadSustratos = cantidadSustratos;
        this.codigoResponsable = codigoResponsable;
        this.observaciones = observaciones;
        this.cosechada = false;
    }

    public String getCodigo() { return codigo; }
    public LocalDate getFechaSiembra() { return fechaSiembra; }
    public String getCodigoSeccion() { return codigoSeccion; }
    public int getCantidadSustratos() { return cantidadSustratos; }
    public String getCodigoResponsable() { return codigoResponsable; }
    public String getObservaciones() { return observaciones; }
    public boolean isCosechada() { return cosechada; }
    public void setCosechada(boolean cosechada) { this.cosechada = cosechada; }
}