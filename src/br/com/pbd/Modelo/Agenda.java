/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Modelo;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Andre-Coude
 */
@Entity
@SequenceGenerator(name = "servA_seq", sequenceName = "servA_seq", initialValue = 1, allocationSize = 1)

@Table(name = "servico_agendado")
public class Agenda implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servA_seq")
    private Long id;

    @OneToOne
    private Animal animal;
    @OneToOne
    private Servico servico;

    @OneToOne
    private Profissional profissional;

    @OneToOne(cascade = CascadeType.ALL)
    private Pagamento pagamento;

    @Column
    private Time horario;
    @Column
    private String anotacao;
    @Column
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
     * @return the profissional
     */
    public Profissional getProfissional() {
        return profissional;
    }

    /**
     * @param profissional the profissional to set
     */
    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    /**
     * @return the pagamento
     */
    public Pagamento getPagamento() {
        return pagamento;
    }

    /**
     * @param pagamento the pagamento to set
     */
    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    /**
     * @return the horario
     */
    public Time getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(Time horario) {
        this.horario = horario;
    }

    /**
     * @return the anotacao
     */
    public String getAnotacao() {
        return anotacao;
    }

    /**
     * @param anotacao the anotacao to set
     */
    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
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
     * @return the servico
     */
    public Servico getServico() {
        return servico;
    }

    /**
     * @param servico the servico to set
     */
    public void setServico(Servico servico) {
        this.servico = servico;
    }

}
