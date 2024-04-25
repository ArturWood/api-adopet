package br.com.dev.api.adopet.repository;

import br.com.dev.api.adopet.model.abrigo.Abrigo;
import br.com.dev.api.adopet.model.pet.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    Page<Pet> findAllByAdotadoFalse(Pageable paginacao);

    List<Pet> findByAbrigoAndAdotadoFalse(Abrigo abrigo);
}
