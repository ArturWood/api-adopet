package br.com.dev.api.adopet.model.adocao;

import br.com.dev.api.adopet.model.pet.Pet;
import br.com.dev.api.adopet.model.tutor.Tutor;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "adocoes")
public class Adocao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime data;
    @ManyToOne(fetch = FetchType.LAZY)
    private Tutor tutor;
    @OneToOne(fetch = FetchType.LAZY)
    private Pet pet;
    private String motivo;
    @Enumerated(EnumType.STRING)
    private StatusAdocao status;
    private String justificativaStatus;

    public Adocao() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Pet getPet() {
        return pet;
    }

    public String getMotivo() {
        return motivo;
    }

    public StatusAdocao getStatus() {
        return status;
    }

    public String getJustificativaStatus() {
        return justificativaStatus;
    }
}
