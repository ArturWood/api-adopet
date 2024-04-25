package br.com.dev.api.adopet.dto.pet;

import br.com.dev.api.adopet.model.pet.Pet;
import br.com.dev.api.adopet.model.pet.TipoPet;

public record PetDetailDto(
        Long id,
        TipoPet tipo,
        String nome,
        String raca,
        Integer idade,
        String cor,
        Float peso,
        Boolean adotado
) {
    public PetDetailDto(Pet pet) {
        this(pet.getId(), pet.getTipo(), pet.getNome(), pet.getRaca(), pet.getIdade(), pet.getCor(), pet.getPeso(), pet.getAdotado());
    }
}
