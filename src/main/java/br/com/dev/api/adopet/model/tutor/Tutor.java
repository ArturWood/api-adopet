package br.com.dev.api.adopet.model.tutor;

import br.com.dev.api.adopet.model.adocao.Adocao;
import jakarta.persistence.*;

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
    @OneToMany(mappedBy = "tutor", fetch = FetchType.EAGER)
    private List<Adocao> adocoes;

    public Tutor() {
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
}
