package br.com.dev.api.adopet.model.pet;

import br.com.dev.api.adopet.model.abrigo.Abrigo;
import br.com.dev.api.adopet.model.adocao.Adocao;
import jakarta.persistence.*;

@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoPet tipo;
    private String nome;
    private String raca;
    private Integer idade;
    private String cor;
    private Float peso;
    private Boolean adotado;
    @ManyToOne(fetch = FetchType.LAZY)
    private Abrigo abrigo;
    @OneToOne(mappedBy = "pet", fetch = FetchType.LAZY)
    private Adocao adocao;

    public Pet() {
    }

    public Long getId() {
        return id;
    }

    public TipoPet getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getRaca() {
        return raca;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getCor() {
        return cor;
    }

    public Float getPeso() {
        return peso;
    }

    public Boolean getAdotado() {
        return adotado;
    }

    public Abrigo getAbrigo() {
        return abrigo;
    }

    public Adocao getAdocao() {
        return adocao;
    }
}
