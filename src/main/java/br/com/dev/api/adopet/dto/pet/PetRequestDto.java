package br.com.dev.api.adopet.dto.pet;

import br.com.dev.api.adopet.model.pet.TipoPet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PetRequestDto(
        @NotBlank
        String nome,
        @NotBlank
        String raca,
        @NotNull
        Integer idade,
        @NotBlank
        String cor,
        @NotNull
        Float peso,
        @NotNull
        TipoPet tipo
) {
}
