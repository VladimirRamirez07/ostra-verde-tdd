package com.ostraverde.service;

import com.ostraverde.exception.SiembraNoExisteException;
import com.ostraverde.exception.SiembraCosechadaException;
import com.ostraverde.model.Cosecha;
import com.ostraverde.model.EstadoSeccion;
import com.ostraverde.model.Seccion;
import com.ostraverde.model.Siembra;
import java.util.ArrayList;
import java.util.List;

public class CosechaService {

    private List<Cosecha> cosechas = new ArrayList<>();
    private SiembraService siembraService;
    private SeccionService seccionService;

    public CosechaService(SiembraService siembraService, SeccionService seccionService) {
        this.siembraService = siembraService;
        this.seccionService = seccionService;
    }

    public Cosecha registrar(Cosecha cosecha) {
        Siembra siembra = siembraService.buscarPorCodigo(cosecha.getCodigoSiembra());
        if (siembra == null) {
            throw new SiembraNoExisteException("La siembra no existe: " + cosecha.getCodigoSiembra());
        }
        if (siembra.isCosechada()) {
            throw new SiembraCosechadaException("Esta siembra ya fue cosechada");
        }
        if (cosecha.getPesoTotal() <= 0) {
            throw new IllegalArgumentException("El peso cosechado debe ser mayor a cero");
        }
        if (cosecha.getFechaCosecha().isBefore(siembra.getFechaSiembra())) {
            throw new IllegalArgumentException("La fecha de cosecha debe ser posterior a la fecha de siembra");
        }
        siembra.setCosechada(true);
        for (Seccion s : seccionService.consultarDisponibles()) {
        }
        List<Seccion> todasSecciones = seccionService.consultarTodas();
        for (Seccion s : todasSecciones) {
            if (s.getCodigo().equals(siembra.getCodigoSeccion())) {
                s.setEstado(EstadoSeccion.DISPONIBLE);
                break;
            }
        }
        cosechas.add(cosecha);
        return cosecha;
    }

    public List<Cosecha> consultarTodas() {
        return cosechas;
    }
}