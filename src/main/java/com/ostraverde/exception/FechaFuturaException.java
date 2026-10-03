package com.ostraverde.exception;

public class FechaFuturaException extends RuntimeException {
    public FechaFuturaException(String mensaje) {
        super(mensaje);
    }
}