package br.com.dev.api.adopet.repository;

import br.com.dev.api.adopet.model.tutor.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
}
