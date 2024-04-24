package br.com.dev.api.adopet.repository;

import br.com.dev.api.adopet.model.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
