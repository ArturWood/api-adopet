package br.com.dev.api.adopet.infra.exception;

public class AbrigoNotFoundException extends RuntimeException {
    public AbrigoNotFoundException(String message) {
        super(message);
    }
}
