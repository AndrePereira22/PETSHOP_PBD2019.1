package br.com.pbd.Modelo;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "vacina_seq", sequenceName = "vacina_seq", initialValue = 1, allocationSize = 1)
@Table(name = "vacina")
public class Vacina implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vacina_seq")
    private Long id;

    @Column(name = "codigo", precision = 8, scale = 0, nullable = false)
    private int codigo;
    @Column(name = "descricao", length = 20, nullable = false)
    private String nome;
    @Column(name = "periodo", length = 20, nullable = false)
    private String peiodo;
    @Column(name = "doses", precision = 2, scale = 0, nullable = false)
    private int doses;

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
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the peiodo
     */
    public String getPeiodo() {
        return peiodo;
    }

    /**
     * @param peiodo the peiodo to set
     */
    public void setPeiodo(String peiodo) {
        this.peiodo = peiodo;
    }

    /**
     * @return the doses
     */
    public int getDoses() {
        return doses;
    }

    /**
     * @param doses the doses to set
     */
    public void setDoses(int doses) {
        this.doses = doses;
    }

}
