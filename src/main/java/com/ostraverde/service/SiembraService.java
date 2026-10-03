package com.ostraverde.service;

import com.ostraverde.exception.FechaFuturaException;
import com.ostraverde.exception.SeccionNoDisponibleException;
import com.ostraverde.model.EstadoSeccion;
import com.ostraverde.model.Seccion;
import com.ostraverde.model.Siembra;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SiembraService {

    private List<Siembra> siembras = new ArrayList<>();
    private SeccionService seccionService;

    public SiembraService(SeccionService seccionService) {
        this.seccionService = seccionService;
    }

    public Siembra registrar(Siembra siembra) {
        if (siembra.getFechaSiembra().isAfter(LocalDate.now())) {
            throw new FechaFuturaException("La fecha de siembra no puede ser futura");
        }
        if (siembra.getCantidadSustratos() <= 0) {
            throw new IllegalArgumentException("La cantidad de sustratos debe ser mayor a cero");
        }
        Seccion seccionEncontrada = null;
        for (Seccion s : seccionService.consultarDisponibles()) {
            if (s.getCodigo().equals(siembra.getCodigoSeccion())) {
                seccionEncontrada = s;
                break;
            }
        }
        if (seccionEncontrada == null) {
            throw new SeccionNoDisponibleException("La seccion no esta disponible: " + siembra.getCodigoSeccion());
        }
        seccionEncontrada.setEstado(EstadoSeccion.OCUPADA);
        siembras.add(siembra);
        return siembra;
    }

    public List<Siembra> consultarTodas() {
        return siembras;
    }

    public Siembra buscarPorCodigo(String codigo) {
        for (Siembra s : siembras) {
            if (s.getCodigo().equals(codigo)) {
                return s;
            }
        }
        return null;
    }
}