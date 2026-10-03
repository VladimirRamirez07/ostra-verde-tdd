package com.ostraverde.service;

import com.ostraverde.exception.SeccionNoDisponibleException;
import com.ostraverde.exception.FechaFuturaException;
import com.ostraverde.model.Seccion;
import com.ostraverde.model.Siembra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class SiembraServiceTest {

    private SiembraService siembraService;
    private SeccionService seccionService;

    @BeforeEach
    void setUp() {
        seccionService = new SeccionService();
        siembraService = new SiembraService(seccionService);
    }

    @Test
    void registrarSiembra_conDatosValidos_debeGuardarla() {
        seccionService.registrar(new Seccion("S001", "Bloque A", 100));
        Siembra siembra = new Siembra("SB001", LocalDate.now(), "S001", 50, "1-0001-0001", "");
        Siembra resultado = siembraService.registrar(siembra);
        assertNotNull(resultado);
        assertEquals("SB001", resultado.getCodigo());
    }

    @Test
    void registrarSiembra_conSeccionOcupada_debeLanzarExcepcion() {
        seccionService.registrar(new Seccion("S001", "Bloque A", 100));
        siembraService.registrar(new Siembra("SB001", LocalDate.now(), "S001", 50, "1-0001-0001", ""));
        assertThrows(SeccionNoDisponibleException.class,
                () -> siembraService.registrar(new Siembra("SB002", LocalDate.now(), "S001", 30, "1-0001-0001", "")));
    }

    @Test
    void registrarSiembra_conFechaFutura_debeLanzarExcepcion() {
        seccionService.registrar(new Seccion("S001", "Bloque A", 100));
        assertThrows(FechaFuturaException.class,
                () -> siembraService.registrar(new Siembra("SB001", LocalDate.now().plusDays(1), "S001", 50, "1-0001-0001", "")));
    }

    @Test
    void registrarSiembra_conCantidadCero_debeLanzarExcepcion() {
        seccionService.registrar(new Seccion("S001", "Bloque A", 100));
        assertThrows(IllegalArgumentException.class,
                () -> siembraService.registrar(new Siembra("SB001", LocalDate.now(), "S001", 0, "1-0001-0001", "")));
    }

    @Test
    void registrarSiembra_debeCambiarEstadoSeccionAOcupada() {
        seccionService.registrar(new Seccion("S001", "Bloque A", 100));
        siembraService.registrar(new Siembra("SB001", LocalDate.now(), "S001", 50, "1-0001-0001", ""));
        var disponibles = seccionService.consultarDisponibles();
        assertEquals(0, disponibles.size());
    }
}