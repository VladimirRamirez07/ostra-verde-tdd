package com.ostraverde.service;

import com.ostraverde.exception.IdentificacionDuplicadaException;
import com.ostraverde.model.Trabajador;

import java.util.ArrayList;
import java.util.List;

public class PersonalService {

    private List<Trabajador> trabajadores = new ArrayList<>();

    public Trabajador registrar(Trabajador trabajador) {
        for (Trabajador t : trabajadores) {
            if (t.getIdentificacion().equals(trabajador.getIdentificacion())) {
                throw new IdentificacionDuplicadaException("Ya existe un trabajador con la identificación: " + trabajador.getIdentificacion());
            }
        }
        trabajadores.add(trabajador);
        return trabajador;
    }

    public List<Trabajador> consultarTodos() {
        return trabajadores;
    }

    public Trabajador actualizar(Trabajador trabajador) {
        for (Trabajador t : trabajadores) {
            if (t.getIdentificacion().equals(trabajador.getIdentificacion())) {
                t.setNombre(trabajador.getNombre());
                t.setTelefono(trabajador.getTelefono());
                t.setCorreo(trabajador.getCorreo());
                t.setCargo(trabajador.getCargo());
                return t;
            }
        }
        return null;
    }
}