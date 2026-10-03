package com.ostraverde.service;

import com.ostraverde.exception.CodigoDuplicadoException;
import com.ostraverde.model.Seccion;
import com.ostraverde.model.EstadoSeccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SeccionServiceTest {

    private SeccionService seccionService;

    @BeforeEach
    void setUp() {
        seccionService = new SeccionService();
    }

    @Test
    void registrarSeccion_conDatosValidos_debeGuardarla() {
        Seccion seccion = new Seccion("S001", "Bloque A", 100);
        Seccion resultado = seccionService.registrar(seccion);
        assertNotNull(resultado);
        assertEquals("S001", resultado.getCodigo());
        assertEquals(EstadoSeccion.DISPONIBLE, resultado.getEstado());
    }

    @Test
    void registrarSeccion_conCodigoDuplicado_debeLanzarExcepcion() {
        Seccion seccion1 = new Seccion("S001", "Bloque A", 100);
        Seccion seccion2 = new Seccion("S001", "Bloque B", 200);
        seccionService.registrar(seccion1);
        assertThrows(CodigoDuplicadoException.class,
                () -> seccionService.registrar(seccion2));
    }

    @Test
    void consultarDisponibles_debeRetornarSoloSeccionesDisponibles() {
        seccionService.registrar(new Seccion("S001", "Bloque A", 100));
        seccionService.registrar(new Seccion("S002", "Bloque B", 150));
        var disponibles = seccionService.consultarDisponibles();
        assertEquals(2, disponibles.size());
    }
}