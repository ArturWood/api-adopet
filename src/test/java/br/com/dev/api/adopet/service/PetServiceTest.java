package br.com.dev.api.adopet.service;

import br.com.dev.api.adopet.dto.pet.PetPageDto;
import br.com.dev.api.adopet.dto.pet.PetRequestDto;
import br.com.dev.api.adopet.infra.exception.PetNotFoundException;
import br.com.dev.api.adopet.model.abrigo.Abrigo;
import br.com.dev.api.adopet.model.pet.Pet;
import br.com.dev.api.adopet.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {
    @Mock
    private PetRepository petRepository;
    @InjectMocks
    private PetService petService;
    private static final Long PET_ID = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deveria retornar um Page de pets")
    void scenario01() {
        // Arrange
        Page<Pet> petsPage = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        when(petRepository.findAllByAdotadoFalse(pageable)).thenReturn(petsPage);

        // Act
        Page<PetPageDto> result = petService.listar(pageable);

        // Assert
        verify(petRepository).findAllByAdotadoFalse(pageable);
    }

    @Test
    @DisplayName("Deveria detalhar um pet pelo ID")
    void scenario02() {
        // Arrange
        Pet pet = new Pet();
        when(petRepository.findById(PET_ID)).thenReturn(Optional.of(pet));

        // Act
        Pet result = petService.detalhar(PET_ID);

        // Assert
        assertNotNull(result);
        assertEquals(pet, result);
        verify(petRepository).findById(PET_ID);
    }

    @Test
    @DisplayName("Deveria lancar PetNotFoundException com um ID invalido")
    void scenario03() {
        // Arrange
        when(petRepository.findById(PET_ID)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PetNotFoundException.class, () -> petService.detalhar(PET_ID));
        verify(petRepository).findById(PET_ID);
    }

    @Test
    @DisplayName("Deveria retornar um Page de pets em um abrigo pelo ID")
    void scenario04() {
        // Arrange
        Abrigo abrigo = mock(Abrigo.class);
        Page<Pet> petsPage = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        when(petRepository.findByAbrigoAndAdotadoFalse(abrigo, pageable)).thenReturn(petsPage);

        // Act
        var result = petService.listarPetsPorAbrigo(abrigo, pageable);

        // Assert
        verify(petRepository).findByAbrigoAndAdotadoFalse(abrigo, pageable);
    }

    @Test
    @DisplayName("Deveria cadastrar um pet")
    void scenario05() {
        // Arrange
        Abrigo abrigo = mock(Abrigo.class);
        PetRequestDto dto = mock(PetRequestDto.class);
        Pet pet = mock(Pet.class);
        when(petRepository.save(any())).thenReturn(pet);

        // Act
        var result = petService.cadastrar(abrigo, dto);

        // Assert
        assertNotNull(result);
        assertEquals(pet, result);
        verify(petRepository).save(any());
    }
}
