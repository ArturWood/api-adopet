package br.com.dev.api.adopet.repository;

import br.com.dev.api.adopet.model.abrigo.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {
    Boolean existsByNomeOrTelefoneOrEmail(String nome, String telefone, String email);
}
