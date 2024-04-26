package br.com.dev.api.adopet.model.adocao.validacoes;

import br.com.dev.api.adopet.dto.adocao.AdocaoRequestDto;
import br.com.dev.api.adopet.infra.exception.ValidadorException;
import br.com.dev.api.adopet.model.pet.Pet;
import br.com.dev.api.adopet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaPetDisponivel implements ValidadorSolicitacaoAdocao {
    @Autowired
    private PetRepository petRepository;

    @Override
    public void validar(AdocaoRequestDto dto) {
        Pet pet = petRepository.getReferenceById(dto.idPet());
        if (pet.getAdotado())
            throw new ValidadorException("Pet já foi adotado");
    }
}
