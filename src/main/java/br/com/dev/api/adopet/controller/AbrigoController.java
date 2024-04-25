package br.com.dev.api.adopet.controller;

import br.com.dev.api.adopet.dto.abrigo.AbrigoDetailDto;
import br.com.dev.api.adopet.dto.abrigo.AbrigoPageDto;
import br.com.dev.api.adopet.dto.abrigo.AbrigoRequestDto;
import br.com.dev.api.adopet.dto.pet.PetDetailDto;
import br.com.dev.api.adopet.dto.pet.PetRequestDto;
import br.com.dev.api.adopet.model.abrigo.Abrigo;
import br.com.dev.api.adopet.model.pet.Pet;
import br.com.dev.api.adopet.service.AbrigoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {
    @Autowired
    private AbrigoService abrigoService;

    @GetMapping
    public ResponseEntity<Page<AbrigoPageDto>> listar(@PageableDefault(size = 15) Pageable paginacao) {
        Page<AbrigoPageDto> page = abrigoService.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<AbrigoDetailDto> cadastrar(@RequestBody @Valid AbrigoRequestDto dto, UriComponentsBuilder uriBuilder) {
        Abrigo abrigo = abrigoService.cadastrar(dto);
        var uri = uriBuilder.path("/abrigos/{id}").buildAndExpand(abrigo.getId()).toUri();
        return ResponseEntity.created(uri).body(new AbrigoDetailDto(abrigo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AbrigoDetailDto> detalhar(@PathVariable Long id) {
        Abrigo abrigo = abrigoService.detalhar(id);
        return ResponseEntity.ok(new AbrigoDetailDto(abrigo));
    }

    @GetMapping("/{id}/pets")
    public ResponseEntity<List<PetDetailDto>> listarPets(@PathVariable Long id) {
        List<Pet> pets = abrigoService.listarPets(id);
        return ResponseEntity.ok(pets.stream().map(PetDetailDto::new).toList());
    }

    @PostMapping("/{id}/pets")
    public ResponseEntity<PetDetailDto> cadastrarPet(@PathVariable Long id, @RequestBody @Valid PetRequestDto dto, UriComponentsBuilder uriBuilder) {
        Pet pet = abrigoService.cadastrarPet(id, dto);
        var uri = uriBuilder.path("/pets/{id}").buildAndExpand(pet.getId()).toUri();
        return ResponseEntity.created(uri).body(new PetDetailDto(pet));
    }
}
