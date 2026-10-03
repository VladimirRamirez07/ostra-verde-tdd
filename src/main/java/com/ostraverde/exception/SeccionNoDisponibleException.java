package com.ostraverde.exception;

public class SeccionNoDisponibleException extends RuntimeException {
    public SeccionNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}