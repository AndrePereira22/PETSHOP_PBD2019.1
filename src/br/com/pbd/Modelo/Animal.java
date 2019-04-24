/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Modelo;

import java.sql.Date;
import javax.persistence.*;

/**
 *
 * @author Andre-Coude
 */
@Entity
@SequenceGenerator(name="animal_seq",sequenceName="animal_seq", initialValue=1,allocationSize=1)
@Table(name = "animal")
public class Animal implements EntidadeBase {
    
     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="animal_seq")
     private Long id;
       
     @Column 
     private String apelido;
     @Column
     private String nome;
     @Column
     private String sexo;
     @Column
     private String cor;
     @Column
     private Date nascimento ;
     @Column
     private Double pesokg;
     @Column
     private String observacao;
     
     
     @OneToOne
     private Raca raca;
     
     @ManyToOne
     private Cliente cliente;
     
    

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
     * @return the apelido
     */
    public String getApelido() {
        return apelido;
    }

    /**
     * @param apelido the apelido to set
     */
    public void setApelido(String apelido) {
        this.apelido = apelido;
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
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
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
     * @return the pesokg
     */
    public Double getPesokg() {
        return pesokg;
    }

    /**
     * @param pesokg the pesokg to set
     */
    public void setPesokg(Double pesokg) {
        this.pesokg = pesokg;
    }

    

    /**
     * @return the Cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param Cliente the Cliente to set
     */
    public void setCliente(Cliente Cliente) {
        this.cliente = Cliente;
    }

    /**
     * @return the raca
     */
    public Raca getRaca() {
        return raca;
    }

    /**
     * @param raca the raca to set
     */
    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

   
    
   
    
    
}
