package br.com.dev.api.adopet.repository;

import br.com.dev.api.adopet.model.adocao.Adocao;
import br.com.dev.api.adopet.model.adocao.StatusAdocao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
    Boolean existsByPetIdAndStatus(Long petId, StatusAdocao status);

    Boolean existsByTutorIdAndStatus(Long tutorId, StatusAdocao status);

    Integer countByTutorIdAndStatus(Long idTutor, StatusAdocao status);
}
