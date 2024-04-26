package br.com.dev.api.adopet.dto.adocao;

import br.com.dev.api.adopet.dto.pet.PetDetailDto;
import br.com.dev.api.adopet.dto.tutor.TutorDetailDto;
import br.com.dev.api.adopet.model.adocao.Adocao;
import br.com.dev.api.adopet.model.adocao.StatusAdocao;

import java.time.LocalDateTime;

public record AdocaoDetailDto(
        Long id,
        LocalDateTime data,
        TutorDetailDto tutor,
        PetDetailDto pet,
        String motivo,
        StatusAdocao status
) {
    public AdocaoDetailDto(Adocao adocao) {
        this(adocao.getId(), adocao.getData(),
                new TutorDetailDto(adocao.getTutor()),
                new PetDetailDto(adocao.getPet()),
                adocao.getMotivo(),
                adocao.getStatus()
        );
    }
}
