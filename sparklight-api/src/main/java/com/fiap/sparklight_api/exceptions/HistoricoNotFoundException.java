package com.fiap.sparklight_api.exceptions;

public class HistoricoNotFoundException extends RuntimeException {

    public HistoricoNotFoundException(Long id) {
        super("Histórico não encontrado com o ID: " + id);
    }

    public HistoricoNotFoundException(String message) {
        super(message);
    }
}
