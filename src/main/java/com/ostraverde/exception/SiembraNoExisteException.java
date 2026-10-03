package com.ostraverde.exception;

public class SiembraNoExisteException extends RuntimeException {
    public SiembraNoExisteException(String mensaje) {
        super(mensaje);
    }
}