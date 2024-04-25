package br.com.dev.api.adopet.dto.abrigo;

import br.com.dev.api.adopet.model.abrigo.Abrigo;

public record AbrigoPageDto(
        Long id,
        String nome,
        String telefone,
        String email
) {
    public AbrigoPageDto(Abrigo abrigo) {
        this(abrigo.getId(), abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail());
    }
}
