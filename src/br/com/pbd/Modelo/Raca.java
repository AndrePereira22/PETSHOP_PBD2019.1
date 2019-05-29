
package br.com.pbd.Modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name="raca_seq",sequenceName="raca_seq", initialValue=1,allocationSize=1)
@Table(name = "raca")
public class Raca implements EntidadeBase  {

     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="raca_seq")
     private Long id;
       
     @Column(name = "nome", length = 40, nullable = false)
     private String nome;
    @Column(name = "descricao", length = 30, nullable = true)
     private String descricao;
     
     @ManyToOne
     private Especie especie;
     
  
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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the especie
     */
    public Especie getEspecie() {
        return especie;
    }

    /**
     * @param especie the especie to set
     */
    public void setEspecie(Especie especie) {
        this.especie = especie;
    }
    
    
}
