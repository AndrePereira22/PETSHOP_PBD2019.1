/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Modelo;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Andre-Coude
 */
@Entity
@SequenceGenerator(name = "agendaA_seq", sequenceName = "agendaA_seq", initialValue = 1, allocationSize = 1)
@Table(name = "agenda_animal")
public class AgendaAnimal implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agendaA_seq")
    private Long id;

    @ManyToOne(optional = false)
    private Animal animal;
    @OneToOne(optional = false)
    private Vacina vacina;

    @Column(name = "numero_dose", precision = 3, scale = 0, nullable = false)
    private int numero_dose;

    @Column(name = "data", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
    private Date data;

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
     * @return the animal
     */
    public Animal getAnimal() {
        return animal;
    }

    /**
     * @param animal the animal to set
     */
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the vacina
     */
    public Vacina getVacina() {
        return vacina;
    }

    /**
     * @param vacina the vacina to set
     */
    public void setVacina(Vacina vacina) {
        this.vacina = vacina;
    }

    /**
     * @return the numero_dose
     */
    public int getNumero_dose() {
        return numero_dose;
    }

    /**
     * @param numero_dose the numero_dose to set
     */
    public void setNumero_dose(int numero_dose) {
        this.numero_dose = numero_dose;
    }

}
