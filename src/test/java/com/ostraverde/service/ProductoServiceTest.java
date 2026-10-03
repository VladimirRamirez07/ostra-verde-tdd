package com.ostraverde.service;

import com.ostraverde.exception.CodigoDuplicadoException;
import com.ostraverde.model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoServiceTest {

    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        productoService = new ProductoService();
    }

    @Test
    void registrarProducto_conDatosValidos_debeGuardarlo() {
        Producto p = new Producto("P001", "Sustrato", "Fertilizante", "Sustrato organico", "kg");
        Producto resultado = productoService.registrar(p);
        assertNotNull(resultado);
        assertEquals("P001", resultado.getCodigo());
    }

    @Test
    void registrarProducto_conCodigoDuplicado_debeLanzarExcepcion() {
        Producto p1 = new Producto("P001", "Sustrato", "Fertilizante", "Sustrato organico", "kg");
        Producto p2 = new Producto("P001", "Otro", "Fungicida", "Otro producto", "lt");
        productoService.registrar(p1);
        assertThrows(CodigoDuplicadoException.class,
                () -> productoService.registrar(p2));
    }

    @Test
    void eliminarProducto_nuncaUsado_debeEliminarlo() {
        Producto p = new Producto("P001", "Sustrato", "Fertilizante", "Sustrato organico", "kg");
        productoService.registrar(p);
        productoService.eliminar("P001");
        assertEquals(0, productoService.consultarTodos().size());
    }

    @Test
    void consultarProductos_debeRetornarTodos() {
        productoService.registrar(new Producto("P001", "Sustrato", "Fertilizante", "Sustrato organico", "kg"));
        productoService.registrar(new Producto("P002", "Agua", "Riego", "Agua purificada", "lt"));
        assertEquals(2, productoService.consultarTodos().size());
    }
}