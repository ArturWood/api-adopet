package br.com.dev.api.adopet.service;

import br.com.dev.api.adopet.dto.adocao.AdocaoRequestDto;
import br.com.dev.api.adopet.dto.adocao.AprovacaoRequestDto;
import br.com.dev.api.adopet.dto.adocao.ReprovacaoRequestDto;
import br.com.dev.api.adopet.infra.exception.AdocaoNotFoundException;
import br.com.dev.api.adopet.model.adocao.Adocao;
import br.com.dev.api.adopet.model.adocao.validacoes.ValidadorSolicitacaoAdocao;
import br.com.dev.api.adopet.model.pet.Pet;
import br.com.dev.api.adopet.model.tutor.Tutor;
import br.com.dev.api.adopet.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdocaoService {
    @Autowired
    private AdocaoRepository adocaoRepository;
    @Autowired
    private PetService petService;
    @Autowired
    private TutorService tutorService;
    @Autowired
    private List<ValidadorSolicitacaoAdocao> validadorSolicitacaoAdocao;

    @Transactional
    public Adocao solicitar(AdocaoRequestDto dto) {
        Pet pet = petService.detalhar(dto.idPet());
        Tutor tutor = tutorService.detalhar(dto.idTutor());
        validadorSolicitacaoAdocao.forEach(v -> v.validar(dto));
        Adocao adocao = new Adocao(pet, tutor, dto.motivo());
        return adocaoRepository.save(adocao);
    }

    public Adocao detalhar(Long id) {
        return this.getAdocao(id);
    }

    @Transactional
    public Adocao aprovar(AprovacaoRequestDto dto) {
        Adocao adocao = this.getAdocao(dto.id());
        adocao.aprovar();
        return adocaoRepository.save(adocao);
    }

    @Transactional
    public Adocao reprovar(ReprovacaoRequestDto dto) {
        Adocao adocao = this.getAdocao(dto.id());
        adocao.reprovar(dto.justificativa());
        return adocaoRepository.save(adocao);
    }

    private Adocao getAdocao(Long id) {
        return adocaoRepository.findById(id).orElseThrow(() -> new AdocaoNotFoundException("Não encontrado adocao com ID: " + id));
    }
}
