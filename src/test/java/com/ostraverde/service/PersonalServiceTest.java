package com.ostraverde.service;

import com.ostraverde.exception.IdentificacionDuplicadaException;
import com.ostraverde.model.Trabajador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonalServiceTest {

    private PersonalService personalService;

    @BeforeEach
    void setUp() {
        personalService = new PersonalService();
    }

    @Test
    void registrarTrabajador_conDatosValidos_debeGuardarlo() {
        Trabajador t = new Trabajador("1-0001-0001", "Juan Pérez", "88001122", "juan@mail.com", "Operario");
        Trabajador resultado = personalService.registrar(t);
        assertNotNull(resultado);
        assertEquals("1-0001-0001", resultado.getIdentificacion());
    }

    @Test
    void registrarTrabajador_conIdentificacionDuplicada_debeLanzarExcepcion() {
        Trabajador t1 = new Trabajador("1-0001-0001", "Juan Pérez", "88001122", "juan@mail.com", "Operario");
        Trabajador t2 = new Trabajador("1-0001-0001", "Carlos López", "88003344", "carlos@mail.com", "Supervisor");
        personalService.registrar(t1);
        assertThrows(IdentificacionDuplicadaException.class,
                () -> personalService.registrar(t2));
    }

    @Test
    void consultarTrabajadores_debeRetornarTodosLosRegistrados() {
        personalService.registrar(new Trabajador("1-0001-0001", "Juan Pérez", "88001122", "juan@mail.com", "Operario"));
        personalService.registrar(new Trabajador("1-0002-0002", "María Gómez", "88005566", "maria@mail.com", "Supervisora"));
        var lista = personalService.consultarTodos();
        assertEquals(2, lista.size());
    }

    @Test
    void actualizarTrabajador_debeCambiarLosDatos() {
        Trabajador t = new Trabajador("1-0001-0001", "Juan Pérez", "88001122", "juan@mail.com", "Operario");
        personalService.registrar(t);
        t.setNombre("Juan Pérez Actualizado");
        Trabajador resultado = personalService.actualizar(t);
        assertEquals("Juan Pérez Actualizado", resultado.getNombre());
    }
}