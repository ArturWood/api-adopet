package br.com.dev.api.adopet.service;

import br.com.dev.api.adopet.dto.tutor.TutorRequestDto;
import br.com.dev.api.adopet.dto.tutor.TutorUpdateDto;
import br.com.dev.api.adopet.model.tutor.Tutor;
import br.com.dev.api.adopet.model.tutor.validacoes.ValidadorCadastroTutor;
import br.com.dev.api.adopet.repository.TutorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TutorServiceTest {
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private List<ValidadorCadastroTutor> validadorCadastroTutor;
    @InjectMocks
    private TutorService tutorService;

    public TutorServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deveria cadastrar tutor")
    public void scenario01() {
        // Arrange
        TutorRequestDto dto = new TutorRequestDto(
                "Teste",
                "teste@teste.com",
                "11912345678"
        );
        Tutor expectedTutor = new Tutor(dto);
        when(tutorRepository.save(any(Tutor.class))).thenReturn(expectedTutor);

        // Act
        Tutor result = tutorService.cadastrar(dto);

        // Assert
        assertEquals(expectedTutor, result);
        verify(validadorCadastroTutor, times(1)).forEach(any());
        verify(tutorRepository, times(1)).save(any(Tutor.class));
    }

    @Test
    @DisplayName("Deveria atualizar dados de um tutor")
    public void scenario02() {
        // Arrange
        TutorUpdateDto dto = new TutorUpdateDto(
                1L,
                "Teste",
                "teste@teste.com",
                "11912345678"
        );
        Tutor tutor = new Tutor(/* Initialize with required values */);
        when(tutorRepository.findById(dto.id())).thenReturn(Optional.of(tutor));

        // Act
        Tutor result = tutorService.atualizar(dto);

        // Assert
        assertEquals(tutor, result);
    }

    @Test
    @DisplayName("Deveria detalhar um tutor pelo ID")
    public void scenario03() {
        // Arrange
        Long id = 1L;
        Tutor expectedTutor = new Tutor();
        when(tutorRepository.findById(id)).thenReturn(Optional.of(expectedTutor));

        // Act
        Tutor result = tutorService.detalhar(id);

        // Assert
        assertEquals(expectedTutor, result);
    }
}
