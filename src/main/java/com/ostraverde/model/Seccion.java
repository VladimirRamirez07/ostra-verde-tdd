package com.ostraverde.model;

public class Seccion {

    private String codigo;
    private String nombre;
    private int capacidadMaxima;
    private EstadoSeccion estado;

    public Seccion(String codigo, String nombre, int capacidadMaxima) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.estado = EstadoSeccion.DISPONIBLE;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getCapacidadMaxima() { return capacidadMaxima; }
    public EstadoSeccion getEstado() { return estado; }
    public void setEstado(EstadoSeccion estado) { this.estado = estado; }
}