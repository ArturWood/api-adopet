package br.com.dev.api.adopet.controller;

import br.com.dev.api.adopet.dto.pet.PetDetailDto;
import br.com.dev.api.adopet.dto.pet.PetPageDto;
import br.com.dev.api.adopet.model.pet.Pet;
import br.com.dev.api.adopet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pets")
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<Page<PetPageDto>> listar(@PageableDefault(size = 15) Pageable paginacao) {
        Page<PetPageDto> page = petService.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDetailDto> detalhar(@PathVariable Long id) {
        Pet pet = petService.detalhar(id);
        return ResponseEntity.ok(new PetDetailDto(pet));
    }
}
