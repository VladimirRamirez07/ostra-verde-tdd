package com.ostraverde.service;

import com.ostraverde.exception.SiembraNoExisteException;
import com.ostraverde.exception.ProductoNoExisteException;
import com.ostraverde.exception.SiembraCosechadaException;
import com.ostraverde.model.Aplicacion;
import com.ostraverde.model.Seccion;
import com.ostraverde.model.Siembra;
import com.ostraverde.model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class AplicacionServiceTest {

    private AplicacionService aplicacionService;
    private SiembraService siembraService;
    private SeccionService seccionService;
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        seccionService = new SeccionService();
        siembraService = new SiembraService(seccionService);
        productoService = new ProductoService();
        aplicacionService = new AplicacionService(siembraService, productoService);

        seccionService.registrar(new Seccion("S001", "Bloque A", 100));
        siembraService.registrar(new Siembra("SB001", LocalDate.now(), "S001", 50, "1-0001-0001", ""));
        productoService.registrar(new Producto("P001", "Sustrato", "Fertilizante", "Sustrato organico", "kg"));
    }

    @Test
    void registrarAplicacion_conDatosValidos_debeGuardarla() {
        Aplicacion a = new Aplicacion("A001", LocalDate.now(), "SB001", "P001", 10.0, "1-0001-0001", "");
        Aplicacion resultado = aplicacionService.registrar(a);
        assertNotNull(resultado);
        assertEquals("A001", resultado.getCodigo());
    }

    @Test
    void registrarAplicacion_conSiembraInexistente_debeLanzarExcepcion() {
        Aplicacion a = new Aplicacion("A001", LocalDate.now(), "SB999", "P001", 10.0, "1-0001-0001", "");
        assertThrows(SiembraNoExisteException.class,
                () -> aplicacionService.registrar(a));
    }

    @Test
    void registrarAplicacion_conProductoInexistente_debeLanzarExcepcion() {
        Aplicacion a = new Aplicacion("A001", LocalDate.now(), "SB001", "P999", 10.0, "1-0001-0001", "");
        assertThrows(ProductoNoExisteException.class,
                () -> aplicacionService.registrar(a));
    }

    @Test
    void registrarAplicacion_conCantidadCero_debeLanzarExcepcion() {
        Aplicacion a = new Aplicacion("A001", LocalDate.now(), "SB001", "P001", 0, "1-0001-0001", "");
        assertThrows(IllegalArgumentException.class,
                () -> aplicacionService.registrar(a));
    }

    @Test
    void registrarAplicacion_enSiembraCosechada_debeLanzarExcepcion() {
        siembraService.buscarPorCodigo("SB001").setCosechada(true);
        Aplicacion a = new Aplicacion("A001", LocalDate.now(), "SB001", "P001", 10.0, "1-0001-0001", "");
        assertThrows(SiembraCosechadaException.class,
                () -> aplicacionService.registrar(a));
    }
}