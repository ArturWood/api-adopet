package br.com.dev.api.adopet.infra.exception;

public class AdocaoNotFoundException extends RuntimeException {
    public AdocaoNotFoundException(String message) {
        super(message);
    }
}
