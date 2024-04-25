package br.com.dev.api.adopet.infra.exception;

import br.com.dev.api.adopet.infra.exception.dto.ErrorResponseDto;
import br.com.dev.api.adopet.infra.exception.dto.ValidationErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleSQLIntegrityConstraintViolation() {
        return ResponseEntity.badRequest().body(new ErrorResponseDto("Informações ja cadastradas na base de dados"));
    }

    @ExceptionHandler(AbrigoNotFoundException.class)
    public ResponseEntity handleAbrigoNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(TutorNotFoundException.class)
    public ResponseEntity handleTutorNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(PetNotFoundException.class)
    public ResponseEntity handlePetNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ValidadorException.class)
    public ResponseEntity<ErrorResponseDto> handleValidador(ValidadorException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationErrorResponseDto>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(ValidationErrorResponseDto::new).toList());
    }
}
