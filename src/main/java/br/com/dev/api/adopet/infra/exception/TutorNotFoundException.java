package br.com.dev.api.adopet.infra.exception;

public class TutorNotFoundException extends RuntimeException {
    public TutorNotFoundException(String message) {
        super(message);
    }
}
