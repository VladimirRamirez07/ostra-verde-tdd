package com.ostraverde.service;

import com.ostraverde.exception.CodigoDuplicadoException;
import com.ostraverde.model.EstadoSeccion;
import com.ostraverde.model.Seccion;

import java.util.ArrayList;
import java.util.List;

public class SeccionService {

    private List<Seccion> secciones = new ArrayList<>();

    public Seccion registrar(Seccion seccion) {
        for (Seccion s : secciones) {
            if (s.getCodigo().equals(seccion.getCodigo())) {
                throw new CodigoDuplicadoException("Ya existe una sección con el código: " + seccion.getCodigo());
            }
        }
        secciones.add(seccion);
        return seccion;
    }

    public List<Seccion> consultarDisponibles() {
        List<Seccion> disponibles = new ArrayList<>();
        for (Seccion s : secciones) {
            if (s.getEstado() == EstadoSeccion.DISPONIBLE) {
                disponibles.add(s);
            }
        }
        return disponibles;
    }
}