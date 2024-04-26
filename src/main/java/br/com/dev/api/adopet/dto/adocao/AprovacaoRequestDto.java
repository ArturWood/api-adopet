package br.com.dev.api.adopet.dto.adocao;

import jakarta.validation.constraints.NotNull;

public record AprovacaoRequestDto(
        @NotNull
        Long id
) {
}
