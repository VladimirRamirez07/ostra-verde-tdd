package com.ostraverde.service;

import com.ostraverde.exception.SiembraNoExisteException;
import com.ostraverde.exception.ProductoNoExisteException;
import com.ostraverde.exception.SiembraCosechadaException;
import com.ostraverde.model.Aplicacion;
import com.ostraverde.model.Siembra;
import com.ostraverde.model.Producto;
import java.util.ArrayList;
import java.util.List;

public class AplicacionService {

    private List<Aplicacion> aplicaciones = new ArrayList<>();
    private SiembraService siembraService;
    private ProductoService productoService;

    public AplicacionService(SiembraService siembraService, ProductoService productoService) {
        this.siembraService = siembraService;
        this.productoService = productoService;
    }

    public Aplicacion registrar(Aplicacion aplicacion) {
        Siembra siembra = siembraService.buscarPorCodigo(aplicacion.getCodigoSiembra());
        if (siembra == null) {
            throw new SiembraNoExisteException("La siembra no existe: " + aplicacion.getCodigoSiembra());
        }
        if (siembra.isCosechada()) {
            throw new SiembraCosechadaException("No se pueden registrar aplicaciones en cultivos cosechados");
        }
        Producto producto = productoService.buscarPorCodigo(aplicacion.getCodigoProducto());
        if (producto == null) {
            throw new ProductoNoExisteException("El producto no existe: " + aplicacion.getCodigoProducto());
        }
        if (aplicacion.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        aplicaciones.add(aplicacion);
        return aplicacion;
    }

    public List<Aplicacion> consultarTodas() {
        return aplicaciones;
    }
}