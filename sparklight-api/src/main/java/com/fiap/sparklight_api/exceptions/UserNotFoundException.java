package com.fiap.sparklight_api.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super("Usuário com ID " + id + " não encontrado.");
    }
}
