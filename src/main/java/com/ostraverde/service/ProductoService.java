package com.ostraverde.service;

import com.ostraverde.exception.CodigoDuplicadoException;
import com.ostraverde.model.Producto;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    private List<Producto> productos = new ArrayList<>();

    public Producto registrar(Producto producto) {
        for (Producto p : productos) {
            if (p.getCodigo().equals(producto.getCodigo())) {
                throw new CodigoDuplicadoException("Ya existe un producto con el codigo: " + producto.getCodigo());
            }
        }
        productos.add(producto);
        return producto;
    }

    public List<Producto> consultarTodos() {
        return productos;
    }

    public void eliminar(String codigo) {
        productos.removeIf(p -> p.getCodigo().equals(codigo));
    }

    public Producto actualizar(Producto producto) {
        for (Producto p : productos) {
            if (p.getCodigo().equals(producto.getCodigo())) {
                p.setNombre(producto.getNombre());
                p.setTipo(producto.getTipo());
                p.setDescripcion(producto.getDescripcion());
                p.setUnidadMedida(producto.getUnidadMedida());
                return p;
            }
        }
        return null;
    }

    public Producto buscarPorCodigo(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }
}