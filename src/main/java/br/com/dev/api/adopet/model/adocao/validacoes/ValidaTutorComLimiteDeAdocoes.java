package br.com.dev.api.adopet.model.adocao.validacoes;

import br.com.dev.api.adopet.dto.adocao.AdocaoRequestDto;
import br.com.dev.api.adopet.infra.exception.ValidadorException;
import br.com.dev.api.adopet.model.adocao.StatusAdocao;
import br.com.dev.api.adopet.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaTutorComLimiteDeAdocoes implements ValidadorSolicitacaoAdocao {
    @Autowired
    private AdocaoRepository adocaoRepository;

    @Override
    public void validar(AdocaoRequestDto dto) {
        Integer count = adocaoRepository.countByTutorIdAndStatus(dto.idTutor(), StatusAdocao.APROVADO);
        if (count >= 5)
            throw new ValidadorException("Tutor chegou ao limite máximo de 5 adoções");
    }
}
