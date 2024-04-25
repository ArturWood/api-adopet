package br.com.dev.api.adopet.controller;

import br.com.dev.api.adopet.dto.tutor.TutorDetailDto;
import br.com.dev.api.adopet.dto.tutor.TutorRequestDto;
import br.com.dev.api.adopet.dto.tutor.TutorUpdateDto;
import br.com.dev.api.adopet.model.tutor.Tutor;
import br.com.dev.api.adopet.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tutores")
public class TutorController {
    @Autowired
    private TutorService tutorService;

    @PostMapping
    public ResponseEntity<TutorDetailDto> cadastrar(@RequestBody @Valid TutorRequestDto dto, UriComponentsBuilder uriBuilder) {
        Tutor tutor = tutorService.cadastrar(dto);
        var uri = uriBuilder.path("/tutores/{id}").buildAndExpand(tutor.getId()).toUri();
        return ResponseEntity.created(uri).body(new TutorDetailDto(tutor));
    }

    @PutMapping
    public ResponseEntity<TutorDetailDto> atualizar(@RequestBody @Valid TutorUpdateDto dto) {
        Tutor tutor = tutorService.atualizar(dto);
        return ResponseEntity.ok(new TutorDetailDto(tutor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorDetailDto> detalhar(@PathVariable Long id) {
        Tutor tutor = tutorService.detalhar(id);
        return ResponseEntity.ok(new TutorDetailDto(tutor));
    }
}
