package br.com.dev.api.adopet.repository;

import br.com.dev.api.adopet.model.adocao.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
}
