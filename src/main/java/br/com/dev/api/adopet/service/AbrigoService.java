package br.com.dev.api.adopet.service;

import br.com.dev.api.adopet.dto.abrigo.AbrigoPageDto;
import br.com.dev.api.adopet.dto.abrigo.AbrigoRequestDto;
import br.com.dev.api.adopet.dto.pet.PetRequestDto;
import br.com.dev.api.adopet.infra.exception.AbrigoNotFoundException;
import br.com.dev.api.adopet.model.abrigo.Abrigo;
import br.com.dev.api.adopet.model.abrigo.validacoes.ValidadorCadastroAbrigo;
import br.com.dev.api.adopet.model.pet.Pet;
import br.com.dev.api.adopet.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AbrigoService {
    @Autowired
    private AbrigoRepository abrigoRepository;
    @Autowired
    private List<ValidadorCadastroAbrigo> validadorCadastroAbrigo;
    @Autowired
    private PetService petService;

    public Page<AbrigoPageDto> listar(Pageable paginacao) {
        Page<Abrigo> abrigoRepositoryAll = abrigoRepository.findAll(paginacao);
        return abrigoRepositoryAll.map(AbrigoPageDto::new);
    }

    @Transactional
    public Abrigo cadastrar(AbrigoRequestDto dto) {
        validadorCadastroAbrigo.forEach(v -> v.validar(dto));
        Abrigo abrigo = new Abrigo(dto);
        return abrigoRepository.save(abrigo);
    }

    public Abrigo detalhar(Long id) {
        return this.getAbrigo(id);
    }

    public List<Pet> listarPets(Long id) {
        Abrigo abrigo = this.getAbrigo(id);
        return petService.listarPetsPorAbrigo(abrigo);
    }

    @Transactional
    public Pet cadastrarPet(Long id, PetRequestDto dto) {
        Abrigo abrigo = this.getAbrigo(id);
        return petService.cadastrar(abrigo, dto);
    }

    private Abrigo getAbrigo(Long id) {
        return abrigoRepository.findById(id).orElseThrow(() -> new AbrigoNotFoundException("Não encontrado abrigo com ID: " + id));
    }
}
