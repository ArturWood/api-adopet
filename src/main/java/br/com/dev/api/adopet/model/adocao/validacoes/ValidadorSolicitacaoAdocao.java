package br.com.dev.api.adopet.model.adocao.validacoes;

import br.com.dev.api.adopet.dto.adocao.AdocaoRequestDto;

public interface ValidadorSolicitacaoAdocao {
    void validar(AdocaoRequestDto dto);
}
