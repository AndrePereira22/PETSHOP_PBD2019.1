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
@SequenceGenerator(name = "loja_seq", sequenceName = "loja_seq", initialValue = 1, allocationSize = 1)
@Table(name = "loja")
public class Loja implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loja_seq")
    private Long id;

    @Column(name = "nome_fantasia", length = 30, nullable = false)
    private String nomefantasia;
    @Column(name = "razao_social", length = 40, nullable = false)
    private String razaosocial;
    @Column(name = "cnpj", length = 18, nullable = false)
    private String cnpj;

    @OneToOne(cascade = CascadeType.ALL)
    private Dados dados;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loja")
    @JoinColumn(name = "loja_id")
    private List<Caixa> caixas;
    

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
     * @return the caixas
     */
    public List<Caixa> getCaixas() {
        return caixas;
    }

    /**
     * @param caixas the caixas to set
     */
    public void setCaixas(List<Caixa> caixas) {
        this.caixas = caixas;
    }

    
}
