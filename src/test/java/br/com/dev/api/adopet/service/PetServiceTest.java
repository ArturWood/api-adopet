package br.com.dev.api.adopet.service;

import br.com.dev.api.adopet.model.pet.Pet;
import br.com.dev.api.adopet.repository.PetRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {
    @InjectMocks
    private PetService petService;
    @Mock
    private PetRepository petRepository;
    @Mock
    private Pageable pageable;
    @Mock
    private Page<Pet> allByAdotadoFalse;

    @Test
    @DisplayName("Deveria retornar page com todos os pets disponiveis")
    void scenario01() {
        // Arrange
        when(petRepository.findAllByAdotadoFalse(pageable)).thenReturn(allByAdotadoFalse);

        // Act
        petService.listar(pageable);

        // Assert
        then(petRepository).should().findAllByAdotadoFalse(pageable);
    }
}
