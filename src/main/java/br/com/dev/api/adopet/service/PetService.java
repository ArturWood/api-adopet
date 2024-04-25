package br.com.dev.api.adopet.service;

import br.com.dev.api.adopet.dto.pet.PetPageDto;
import br.com.dev.api.adopet.dto.pet.PetRequestDto;
import br.com.dev.api.adopet.infra.exception.PetNotFoundException;
import br.com.dev.api.adopet.model.abrigo.Abrigo;
import br.com.dev.api.adopet.model.pet.Pet;
import br.com.dev.api.adopet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public Page<PetPageDto> listar(Pageable paginacao) {
        Page<Pet> allByAdotadoFalse = petRepository.findAllByAdotadoFalse(paginacao);
        return allByAdotadoFalse.map(PetPageDto::new);
    }

    public Pet detalhar(Long id) {
        return this.getPet(id);
    }

    public List<Pet> listarPetsPorAbrigo(Abrigo abrigo) {
        return petRepository.findByAbrigoAndAdotadoFalse(abrigo);
    }

    @Transactional
    public Pet cadastrar(Abrigo abrigo, PetRequestDto dto) {
        Pet pet = new Pet(dto, abrigo);
        return petRepository.save(pet);
    }

    private Pet getPet(Long id) {
        return petRepository.findById(id).orElseThrow(() -> new PetNotFoundException("Não encontrado pet com ID: " + id));
    }
}
