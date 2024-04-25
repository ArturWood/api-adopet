package br.com.dev.api.adopet.infra.exception;

public class ValidadorException extends RuntimeException {
    public ValidadorException(String message) {
        super(message);
    }
}
