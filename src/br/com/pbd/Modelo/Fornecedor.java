/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Andre-Coude
 */
@Entity
@SequenceGenerator(name="fornecedor_seq",sequenceName="fornecedor_seq", initialValue=1,allocationSize=1)
@Table(name = "fornecedor")
public class Fornecedor implements EntidadeBase {
    
     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="fornecedor_seq")
     private Long id;
       
     @Column 
     private String nomefantasia;
     @Column
     private String razaosocial;
     @Column
     private String cnpj ;
   
     @OneToOne(cascade=CascadeType.ALL)
     private Dados dados;
     
     
     @OneToMany( cascade = CascadeType.ALL, mappedBy = "fornecedor")
     @JoinColumn(name="fornecedor_id")
     private List<Produto> produtos;

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
     * @return the nomefantasia
     */
    public String getNomefantasia() {
        return nomefantasia;
    }

    /**
     * @param nomefantasia the nomefantasia to set
     */
    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    /**
     * @return the razaosocial
     */
    public String getRazaosocial() {
        return razaosocial;
    }

    /**
     * @param razaosocial the razaosocial to set
     */
    public void setRazaosocial(String razaosocial) {
        this.razaosocial = razaosocial;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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
     * @return the produtos
     */
    public List<Produto> getProdutos() {
        return produtos;
    }

    /**
     * @param produtos the produtos to set
     */
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
}
