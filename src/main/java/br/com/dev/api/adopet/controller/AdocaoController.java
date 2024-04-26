package br.com.dev.api.adopet.controller;

import br.com.dev.api.adopet.dto.adocao.AdocaoDetailDto;
import br.com.dev.api.adopet.dto.adocao.AdocaoRequestDto;
import br.com.dev.api.adopet.dto.adocao.AprovacaoRequestDto;
import br.com.dev.api.adopet.dto.adocao.ReprovacaoRequestDto;
import br.com.dev.api.adopet.model.adocao.Adocao;
import br.com.dev.api.adopet.service.AdocaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/adocoes")
public class AdocaoController {
    @Autowired
    private AdocaoService adocaoService;

    @PostMapping
    public ResponseEntity<AdocaoDetailDto> solicitar(@RequestBody @Valid AdocaoRequestDto dto, UriComponentsBuilder uriBuilder) {
        Adocao adocao = adocaoService.solicitar(dto);
        var uri = uriBuilder.path("/adocoes/{id}").buildAndExpand(adocao.getId()).toUri();
        return ResponseEntity.created(uri).body(new AdocaoDetailDto(adocao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdocaoDetailDto> detalhar(@PathVariable Long id) {
        Adocao adocao = adocaoService.detalhar(id);
        return ResponseEntity.ok(new AdocaoDetailDto(adocao));
    }

    @PutMapping("/aprovar")
    public ResponseEntity<AdocaoDetailDto> aprovar(@RequestBody @Valid AprovacaoRequestDto dto) {
        Adocao adocao = adocaoService.aprovar(dto);
        return ResponseEntity.ok(new AdocaoDetailDto(adocao));
    }

    @PutMapping("/reprovar")
    public ResponseEntity<AdocaoDetailDto> reprovar(@RequestBody @Valid ReprovacaoRequestDto dto) {
        Adocao adocao = adocaoService.reprovar(dto);
        return ResponseEntity.ok(new AdocaoDetailDto(adocao));
    }
}
