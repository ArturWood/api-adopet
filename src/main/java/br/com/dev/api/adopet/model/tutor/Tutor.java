package br.com.dev.api.adopet.model.tutor;

import br.com.dev.api.adopet.dto.tutor.TutorRequestDto;
import br.com.dev.api.adopet.dto.tutor.TutorUpdateDto;
import br.com.dev.api.adopet.model.adocao.Adocao;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tutores")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<Adocao> adocoes;

    public Tutor() {
    }

    public Tutor(TutorRequestDto dto) {
        this.nome = dto.nome();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.adocoes = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public List<Adocao> getAdocoes() {
        return adocoes;
    }

    public void atualizaDados(TutorUpdateDto dto) {
        this.nome = dto.nome() != null ? dto.nome() : this.nome;
        this.telefone = dto.telefone() != null ? dto.telefone() : this.telefone;
        this.email = dto.email() != null ? dto.email() : this.email;
    }
}
