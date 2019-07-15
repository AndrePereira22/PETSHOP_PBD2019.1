/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Visao;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import jdk.nashorn.internal.ir.annotations.Immutable;
/**
 *
 * @author Andre-Coude
 */

@Immutable
@Entity
public class ViewCliente {
    
    @Id
    @Column(name = "id",nullable=false)
    private int id;
    
   @Column(name = "rg",nullable=false)
    private String rg;
    
    @Column(name = "cpf",nullable=false)
    private String cpf;
    
    @Column(name = "nascimento",nullable=false)
    private Date nascimento;
    
    @Column(name = "nome",nullable=false)
    private String nome;
    
    @Column(name = "sexo",nullable=false)
    private String sexo;
    
       @Column(name = "celular",nullable=false)
    private String celular;
       
          @Column(name = "rua",nullable=false)
    private String rua;

    public ViewCliente() {
    }

    public ViewCliente(int id, String rg, String cpf, Date nascimento, String nome, String sexo, String celular, String rua) {
        this.id = id;
        this.rg = rg;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.nome = nome;
        this.sexo = sexo;
        this.celular = celular;
        this.rua = rua;
    }

   


    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
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
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the rua
     */
    public String getRua() {
        return rua;
    }

    /**
     * @param rua the rua to set
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

   
   
}
