
package br.com.pbd.Modelo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name="especie_seq",sequenceName="especie_seq", initialValue=1,allocationSize=1)
@Table(name = "especie")
public class Especie implements EntidadeBase  {

     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="especie_seq")
     private Long id;
       
     @Column 
     private String nome;
     
     @OneToMany( cascade = CascadeType.ALL, mappedBy = "especie")
     @JoinColumn(name="especie_id")
     private List<Raca> racas;
  
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
     * @return the racas
     */
    public List<Raca> getRacas() {
        return racas;
    }

    /**
     * @param racas the racas to set
     */
    public void setRacas(List<Raca> racas) {
        this.racas = racas;
    }

   
    
}
