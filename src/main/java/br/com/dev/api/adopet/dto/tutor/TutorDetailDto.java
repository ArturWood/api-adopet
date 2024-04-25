package br.com.dev.api.adopet.dto.tutor;

import br.com.dev.api.adopet.model.tutor.Tutor;

public record TutorDetailDto(
        Long id,
        String nome,
        String telefone,
        String email
) {
    public TutorDetailDto(Tutor tutor) {
        this(tutor.getId(), tutor.getNome(), tutor.getTelefone(), tutor.getEmail());
    }
}
