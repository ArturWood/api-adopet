package br.com.dev.api.adopet.model.abrigo.validacoes;

import br.com.dev.api.adopet.dto.abrigo.AbrigoRequestDto;
import br.com.dev.api.adopet.infra.exception.ValidadorException;
import br.com.dev.api.adopet.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaAbrigoJaCadastrado implements ValidadorCadastroAbrigo {
    @Autowired
    private AbrigoRepository abrigoRepository;

    @Override
    public void validar(AbrigoRequestDto dto) {
        if (abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email()))
            throw new ValidadorException("Dados já cadastrados para outro abrigo");
    }
}
