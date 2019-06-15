package br.com.pbd.Modelo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Andre-Coude
 */
@Entity
@SequenceGenerator(name = "cliente_seq", sequenceName = "cliente_seq", initialValue = 1, allocationSize = 1)
@Table(name = "cliente")

public class Cliente implements EntidadeBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
    private Long id;

    @Column(name = "nome", length = 40, nullable = false)
    private String nome;
    @Column(name = "sexo", length = 9, nullable = false)
    private String sexo;
    @Column(name = "nascimento", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
    private Date nascimento;
    @Column(name = "cpf", length = 15, nullable = false,unique=true)
    private String cpf;
    @Column(name = "Rg", length = 9, nullable = false,unique=true)
    private String Rg;

    @OneToOne(cascade = CascadeType.ALL)
    private Dados dados;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    @JoinColumn(name = "clinte_id")
    private List<Animal> animais;

    public Cliente() {
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the nascimento
     */
    public Date getNascimento() {
        return nascimento;
    }

    /**
     * @param nascimento the nascimento to set
     */
    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the Rg
     */
    public String getRg() {
        return Rg;
    }

    /**
     * @param Rg the Rg to set
     */
    public void setRg(String Rg) {
        this.Rg = Rg;
    }

    /**
     * @return the dados
     */
    public Dados getDados() {
        return dados;
    }

    /**
     * @param dados the dados to set
     */
    public void setDados(Dados dados) {
        this.dados = dados;
    }

    /**
     * @return the animais
     */
    public List<Animal> getAnimais() {
        return animais;
    }

    /**
     * @param animais the animais to set
     */
    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }

}
