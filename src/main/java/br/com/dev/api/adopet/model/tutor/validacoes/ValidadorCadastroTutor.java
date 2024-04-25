package br.com.dev.api.adopet.model.tutor.validacoes;

import br.com.dev.api.adopet.dto.tutor.TutorRequestDto;

public interface ValidadorCadastroTutor {
    void validar(TutorRequestDto dto);
}
