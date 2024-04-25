package br.com.dev.api.adopet.dto.tutor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TutorUpdateDto(
        @NotNull
        Long id,
        String nome,
        @Email
        String email,
        @Pattern(regexp = "\\d{11}", message = "O Telefone deve conter 11 digitos")
        String telefone
) {
}
