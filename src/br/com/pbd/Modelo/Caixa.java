/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Modelo;

import java.sql.Date;
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
@SequenceGenerator(name = "caixa_seq", sequenceName = "caixa_seq", initialValue = 1, allocationSize = 1)
@Table(name = "caixa")
public class Caixa implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "caixa_seq")
    private Long id;

    @Column(name = "valor_abertura", precision = 6, scale = 2, nullable = false)
    private Double valorabertura;
    @Column(name = "valor_fechamento", precision = 6, scale = 2, nullable = false)
    private Double valorfechamento;
    @Column(name = "status", insertable = true, nullable = false)
    private Boolean status;
    @Column(name = "data", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
    private Date data;

    @OneToOne
    private Loja loja;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "caixa")
    @JoinColumn(name = "caixa_id")
    private List<Venda> vendas;

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
     * @return the valorabertura
     */
    public Double getValorabertura() {
        return valorabertura;
    }

    /**
     * @param valorabertura the valorabertura to set
     */
    public void setValorabertura(Double valorabertura) {
        this.valorabertura = valorabertura;
    }

    /**
     * @return the valorfechamento
     */
    public Double getValorfechamento() {
        return valorfechamento;
    }

    /**
     * @param valorfechamento the valorfechamento to set
     */
    public void setValorfechamento(Double valorfechamento) {
        this.valorfechamento = valorfechamento;
    }

    /**
     * @return the status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Boolean status) {
        this.status = status;
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
     * @return the loja
     */
    public Loja getLoja() {
        return loja;
    }

    /**
     * @param loja the loja to set
     */
    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    /**
     * @return the vendas
     */
    public List<Venda> getVendas() {
        return vendas;
    }

    /**
     * @param vendas the vendas to set
     */
    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

}
