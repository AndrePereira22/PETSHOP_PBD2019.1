/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Modelo;

import java.sql.Date;
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
@SequenceGenerator(name = "prof_seq", sequenceName = "prof_seq", initialValue = 1, allocationSize = 1)
@Table(name = "profissional")
public class Profissional implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prof_seq")
    private Long id;

    @Column
    private String tipo;
    @Column
    private String nome;
    @Column
    private Date nascimento;
    @Column
    private String sexo;
    @Column
    private String crmv;

    @OneToOne(cascade = CascadeType.ALL)
    private Dados dados;

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
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
     * @return the crmv
     */
    public String getCrmv() {
        return crmv;
    }

    /**
     * @param crmv the crmv to set
     */
    public void setCrmv(String crmv) {
        this.crmv = crmv;
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

}
