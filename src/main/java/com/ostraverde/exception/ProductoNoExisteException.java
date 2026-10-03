package com.ostraverde.exception;

public class ProductoNoExisteException extends RuntimeException {
    public ProductoNoExisteException(String mensaje) {
        super(mensaje);
    }
}