package br.com.dev.api.adopet.service;

import br.com.dev.api.adopet.dto.tutor.TutorRequestDto;
import br.com.dev.api.adopet.dto.tutor.TutorUpdateDto;
import br.com.dev.api.adopet.infra.exception.TutorNotFoundException;
import br.com.dev.api.adopet.model.tutor.Tutor;
import br.com.dev.api.adopet.model.tutor.validacoes.ValidadorCadastroTutor;
import br.com.dev.api.adopet.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TutorService {
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private List<ValidadorCadastroTutor> validadorCadastroTutor;

    @Transactional
    public Tutor cadastrar(TutorRequestDto dto) {
        validadorCadastroTutor.forEach(v -> v.validar(dto));
        Tutor tutor = new Tutor(dto);
        return tutorRepository.save(tutor);
    }

    @Transactional
    public Tutor atualizar(TutorUpdateDto dto) {
        Tutor tutor = this.getTutor(dto.id());
        tutor.atualizaDados(dto);
        return tutor;
    }

    public Tutor detalhar(Long id) {
        return this.getTutor(id);
    }

    private Tutor getTutor(Long id) {
        return tutorRepository.findById(id).orElseThrow(() -> new TutorNotFoundException("Não encontrado tutor com ID: " + id));
    }
}
