package br.com.dev.api.adopet.model.adocao.validacoes;

import br.com.dev.api.adopet.dto.adocao.AdocaoRequestDto;
import br.com.dev.api.adopet.infra.exception.ValidadorException;
import br.com.dev.api.adopet.model.adocao.StatusAdocao;
import br.com.dev.api.adopet.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaPetComAdocaoEmAndamento implements ValidadorSolicitacaoAdocao {
    @Autowired
    private AdocaoRepository adocaoRepository;

    @Override
    public void validar(AdocaoRequestDto dto) {
        if (adocaoRepository.existsByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO))
            throw new ValidadorException("Pet já está aguardando avaliação para ser adotado");
    }
}
