package br.com.dev.api.adopet.dto.abrigo;

import br.com.dev.api.adopet.model.abrigo.Abrigo;

public record AbrigoDetailDto(
        Long id,
        String nome,
        String telefone,
        String email
) {
    public AbrigoDetailDto(Abrigo abrigo) {
        this(abrigo.getId(), abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail());
    }
}
