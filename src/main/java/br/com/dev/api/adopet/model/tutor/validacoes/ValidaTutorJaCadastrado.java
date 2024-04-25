package br.com.dev.api.adopet.model.tutor.validacoes;

import br.com.dev.api.adopet.dto.tutor.TutorRequestDto;
import br.com.dev.api.adopet.infra.exception.ValidadorException;
import br.com.dev.api.adopet.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaTutorJaCadastrado implements ValidadorCadastroTutor {
    @Autowired
    private TutorRepository tutorRepository;

    @Override
    public void validar(TutorRequestDto dto) {
        if (tutorRepository.existsByTelefoneOrEmail(dto.telefone(), dto.email()))
            throw new ValidadorException("Dados já cadastrados para outro tutor");
    }
}
