package br.com.dev.api.adopet.service;

import br.com.dev.api.adopet.dto.adocao.AdocaoRequestDto;
import br.com.dev.api.adopet.dto.adocao.AprovacaoRequestDto;
import br.com.dev.api.adopet.dto.adocao.ReprovacaoRequestDto;
import br.com.dev.api.adopet.infra.exception.AdocaoNotFoundException;
import br.com.dev.api.adopet.model.adocao.Adocao;
import br.com.dev.api.adopet.model.pet.Pet;
import br.com.dev.api.adopet.model.tutor.Tutor;
import br.com.dev.api.adopet.repository.AdocaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AdocaoServiceTest {
    @MockBean
    private AdocaoRepository adocaoRepository;
    @MockBean
    private PetService petService;
    @MockBean
    private TutorService tutorService;
    @Autowired
    private AdocaoService adocaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deveria cadastrar uma adocao")
    public void scenario01() {
        // Arrange
        AdocaoRequestDto dto = new AdocaoRequestDto(1L, 2L, "Motivo");
        Pet pet = new Pet();
        Tutor tutor = new Tutor();
        when(petService.detalhar(dto.idPet())).thenReturn(pet);
        when(tutorService.detalhar(dto.idTutor())).thenReturn(tutor);
        when(adocaoRepository.save(any(Adocao.class))).thenReturn(new Adocao());

        // Act
        Adocao adocao = adocaoService.solicitar(dto);

        // Assert
        assertNotNull(adocao);
    }

    @Test
    @DisplayName("Deveria detalhar uma adocao pelo ID")
    public void scenario02() {
        // Arrange
        Long adocaoId = 1L;
        Adocao adocao = new Adocao();
        when(adocaoRepository.findById(adocaoId)).thenReturn(Optional.of(adocao));

        // Act
        Adocao result = adocaoService.detalhar(adocaoId);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("Deveria aprovar uma adocao pelo ID")
    public void scenario03() {
        // Arrange
        AprovacaoRequestDto dto = new AprovacaoRequestDto(1L);
        Adocao adocao = new Adocao();
        when(adocaoRepository.findById(dto.id())).thenReturn(Optional.of(adocao));
        when(adocaoRepository.save(any(Adocao.class))).thenReturn(new Adocao());

        // Act
        Adocao result = adocaoService.aprovar(dto);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("Deveria reprovar uma adocao pelo ID e justificativa")
    public void scenario04() {
        // Arrange
        ReprovacaoRequestDto dto = new ReprovacaoRequestDto(1L, "Justificativa");
        Adocao adocao = new Adocao();
        when(adocaoRepository.findById(dto.id())).thenReturn(Optional.of(adocao));
        when(adocaoRepository.save(any(Adocao.class))).thenReturn(new Adocao());

        // Act
        Adocao result = adocaoService.reprovar(dto);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("Deveria lancar AdocaoNotFoundException com um ID invalido")
    public void scenario05() {
        // Arrange
        Long id = 1L;
        when(adocaoRepository.findById(id)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(AdocaoNotFoundException.class, () -> adocaoService.detalhar(id));
    }
}
