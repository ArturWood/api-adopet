package br.com.dev.api.adopet.service;

import br.com.dev.api.adopet.dto.pet.PetPageDto;
import br.com.dev.api.adopet.model.pet.Pet;
import br.com.dev.api.adopet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public Page<PetPageDto> listar(Pageable paginacao) {
        Page<Pet> allByAdotadoFalse = petRepository.findAllByAdotadoFalse(paginacao);
        return allByAdotadoFalse.map(PetPageDto::new);
    }
}
