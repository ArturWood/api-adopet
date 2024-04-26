package br.com.dev.api.adopet.service;

import br.com.dev.api.adopet.dto.abrigo.AbrigoPageDto;
import br.com.dev.api.adopet.dto.abrigo.AbrigoRequestDto;
import br.com.dev.api.adopet.infra.exception.AbrigoNotFoundException;
import br.com.dev.api.adopet.model.abrigo.Abrigo;
import br.com.dev.api.adopet.model.abrigo.validacoes.ValidadorCadastroAbrigo;
import br.com.dev.api.adopet.repository.AbrigoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AbrigoServiceTest {
    @InjectMocks
    private AbrigoService abrigoService;
    @Mock
    private AbrigoRepository abrigoRepository;
    @Mock
    private List<ValidadorCadastroAbrigo> validadorCadastroAbrigo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deveria retornar um Page de abrigos")
    public void scenario01() {
        // Arrange
        Pageable pageable = mock(Pageable.class);
        Page<Abrigo> page = new PageImpl<>(Collections.emptyList());
        when(abrigoRepository.findAll(pageable)).thenReturn(page);

        // Act
        Page<AbrigoPageDto> result = abrigoService.listar(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
    }

    @Test
    @DisplayName("Deveria cadastrar um abrigo")
    public void scenario02() {
        // Arrange
        AbrigoRequestDto dto = mock(AbrigoRequestDto.class);
        Abrigo abrigo = mock(Abrigo.class);
        when(abrigoRepository.save(any())).thenReturn(abrigo);

        // Act
        Abrigo result = abrigoService.cadastrar(dto);

        // Assert
        assertNotNull(result);
        assertEquals(abrigo, result);
        verify(validadorCadastroAbrigo, times(1)).forEach(any());
    }

    @Test
    @DisplayName("Deveria detalhar um abrigo pelo ID")
    public void scenario03() {
        // Arrange
        Long id = 1L;
        Abrigo abrigo = mock(Abrigo.class);
        when(abrigoRepository.findById(id)).thenReturn(java.util.Optional.of(abrigo));

        // Act
        Abrigo result = abrigoService.detalhar(id);

        // Assert
        assertNotNull(result);
        assertEquals(abrigo, result);
    }

    @Test
    @DisplayName("Deveria lancar AbrigoNotFoundException com um ID invalido")
    public void scenario04() {
        // Arrange
        Long id = 1L;
        when(abrigoRepository.findById(id)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(AbrigoNotFoundException.class, () -> abrigoService.detalhar(id));
    }
}
