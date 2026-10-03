package com.ostraverde.model;

import java.time.LocalDate;

public class Cosecha {

    private String codigo;
    private LocalDate fechaCosecha;
    private String codigoSiembra;
    private double pesoTotal;
    private int cantidadBandejas;
    private String codigoResponsable;

    public Cosecha(String codigo, LocalDate fechaCosecha, String codigoSiembra, double pesoTotal, int cantidadBandejas, String codigoResponsable) {
        this.codigo = codigo;
        this.fechaCosecha = fechaCosecha;
        this.codigoSiembra = codigoSiembra;
        this.pesoTotal = pesoTotal;
        this.cantidadBandejas = cantidadBandejas;
        this.codigoResponsable = codigoResponsable;
    }

    public String getCodigo() { return codigo; }
    public LocalDate getFechaCosecha() { return fechaCosecha; }
    public String getCodigoSiembra() { return codigoSiembra; }
    public double getPesoTotal() { return pesoTotal; }
    public int getCantidadBandejas() { return cantidadBandejas; }
    public String getCodigoResponsable() { return codigoResponsable; }
}