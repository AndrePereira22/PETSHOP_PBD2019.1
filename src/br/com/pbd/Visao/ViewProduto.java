/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Visao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import jdk.nashorn.internal.ir.annotations.Immutable;
/**
 *
 * @author Andre-Coude
 */

@Immutable
@Entity

public class ViewProduto {
    
    @Id
    @Column(name = "id",nullable=false)
    private int id;
    
    @Column(name = "codigo",nullable=false)
    private int codigo;
    
    @Column(name = "nome",nullable=false)
    private String nome;
    
    @Column(name = "fabricante",nullable=false)
    private String fabricante;
    @Column(name = "nome_fantasia",nullable=false)
    private String nome_fantasia;
    
    @Column(name = "quantidade_estoque",nullable=false)
    private int quantidade_estoque;
    
    @Column(name = "valor_venda",nullable=false)
    private Double valor_venda;

    public ViewProduto() {
    }

    public ViewProduto(int codigo, String nome, String fabricante, int quantidade_estoque, Double valor_venda) {
        this.codigo = codigo;
        this.nome = nome;
        this.fabricante = fabricante;
        this.quantidade_estoque = quantidade_estoque;
        this.valor_venda = valor_venda;
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
     * @return the quantidade_estoque
     */
    public int getQuantidade_estoque() {
        return quantidade_estoque;
    }

    /**
     * @param quantidade_estoque the quantidade_estoque to set
     */
    public void setQuantidade_estoque(int quantidade_estoque) {
        this.quantidade_estoque = quantidade_estoque;
    }

    /**
     * @return the valor_venda
     */
    public Double getValor_venda() {
        return valor_venda;
    }

    /**
     * @param valor_venda the valor_venda to set
     */
    public void setValor_venda(Double valor_venda) {
        this.valor_venda = valor_venda;
    }

    /**
     * @return the fabricante
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * @param fabricante the fabricante to set
     */
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
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
     * @return the nome_fantasia
     */
    public String getNome_fantasia() {
        return nome_fantasia;
    }

    /**
     * @param nome_fantasia the nome_fantasia to set
     */
    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
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


}
