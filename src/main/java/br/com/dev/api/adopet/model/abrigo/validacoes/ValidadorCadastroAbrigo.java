package br.com.dev.api.adopet.model.abrigo.validacoes;

import br.com.dev.api.adopet.dto.abrigo.AbrigoRequestDto;

public interface ValidadorCadastroAbrigo {
    void validar(AbrigoRequestDto dto);
}
