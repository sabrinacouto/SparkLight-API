package com.fiap.sparklight_api.exceptions;

public class AparelhoNotFoundException extends RuntimeException {
    public AparelhoNotFoundException(Long id) {
        super("Aparelho não encontrado com ID: " + id);
    }

    public AparelhoNotFoundException(String message) {
        super(message);
    }
}
