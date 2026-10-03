package com.ostraverde.service;

import com.ostraverde.exception.SiembraNoExisteException;
import com.ostraverde.exception.SiembraCosechadaException;
import com.ostraverde.model.Cosecha;
import com.ostraverde.model.Seccion;
import com.ostraverde.model.Siembra;
import com.ostraverde.model.EstadoSeccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class CosechaServiceTest {

    private CosechaService cosechaService;
    private SiembraService siembraService;
    private SeccionService seccionService;

    @BeforeEach
    void setUp() {
        seccionService = new SeccionService();
        siembraService = new SiembraService(seccionService);
        cosechaService = new CosechaService(siembraService, seccionService);

        seccionService.registrar(new Seccion("S001", "Bloque A", 100));
        siembraService.registrar(new Siembra("SB001", LocalDate.now(), "S001", 50, "1-0001-0001", ""));
    }

    @Test
    void registrarCosecha_conDatosValidos_debeGuardarla() {
        Cosecha c = new Cosecha("C001", LocalDate.now(), "SB001", 25.5, 10, "1-0001-0001");
        Cosecha resultado = cosechaService.registrar(c);
        assertNotNull(resultado);
        assertEquals("C001", resultado.getCodigo());
    }

    @Test
    void registrarCosecha_conSiembraInexistente_debeLanzarExcepcion() {
        Cosecha c = new Cosecha("C001", LocalDate.now(), "SB999", 25.5, 10, "1-0001-0001");
        assertThrows(SiembraNoExisteException.class,
                () -> cosechaService.registrar(c));
    }

    @Test
    void registrarCosecha_siembraYaCosechada_debeLanzarExcepcion() {
        cosechaService.registrar(new Cosecha("C001", LocalDate.now(), "SB001", 25.5, 10, "1-0001-0001"));
        Cosecha c2 = new Cosecha("C002", LocalDate.now(), "SB001", 10.0, 5, "1-0001-0001");
        assertThrows(SiembraCosechadaException.class,
                () -> cosechaService.registrar(c2));
    }

    @Test
    void registrarCosecha_conPesoCero_debeLanzarExcepcion() {
        Cosecha c = new Cosecha("C001", LocalDate.now(), "SB001", 0, 10, "1-0001-0001");
        assertThrows(IllegalArgumentException.class,
                () -> cosechaService.registrar(c));
    }

    @Test
    void registrarCosecha_debePonerSeccionDisponible() {
        cosechaService.registrar(new Cosecha("C001", LocalDate.now(), "SB001", 25.5, 10, "1-0001-0001"));
        assertEquals(1, seccionService.consultarDisponibles().size());
    }
}