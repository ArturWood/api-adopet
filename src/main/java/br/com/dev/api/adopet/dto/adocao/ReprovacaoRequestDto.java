package br.com.dev.api.adopet.dto.adocao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReprovacaoRequestDto(
        @NotNull
        Long id,
        @NotBlank
        String justificativa
) {
}
