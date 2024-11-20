package com.fiap.sparklight_api.exceptions;


public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(Long id) {
        super("Item não encontrado com o ID: " + id);
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}
