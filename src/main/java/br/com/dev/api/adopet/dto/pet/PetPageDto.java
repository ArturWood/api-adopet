package br.com.dev.api.adopet.dto.pet;

import br.com.dev.api.adopet.model.pet.Pet;
import br.com.dev.api.adopet.model.pet.TipoPet;

public record PetPageDto(
        Long petId,
        TipoPet tipo,
        String nome,
        String raca,
        Integer idade,
        Long abrigoId
) {
    public PetPageDto(Pet pet) {
        this(pet.getId(), pet.getTipo(), pet.getNome(), pet.getRaca(), pet.getIdade(), pet.getAbrigo().getId());
    }
}
