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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Andre-Coude
 */
@Entity
@SequenceGenerator(name = "pagamento_seq", sequenceName = "pagamento_seq", initialValue = 1, allocationSize = 1)
@Table(name = "pagamento")
public class Pagamento implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pagamento_seq")
    private Long id;

    @Column(name = "valor_total", precision = 3, scale = 2, nullable = false)
    private Double valortotal;
    @Column(name = "status", insertable = true, nullable = false)
    private Boolean status;
    @Column(name = "numero_parcelas", precision = 2, scale = 0, nullable = false)
    private int numeroparcelas;
    @Column(name = "data", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
    private Date data;
    @Column(name = "forma_pagamento", length = 30, nullable = false)
    private String forma_pagamento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pagamento")
    @JoinColumn(name = "pagamento_id")
    private List<Parcela> parcelas;

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
     * @return the valortotal
     */
    public Double getValortotal() {
        return valortotal;
    }

    /**
     * @param valortotal the valortotal to set
     */
    public void setValortotal(Double valortotal) {
        this.valortotal = valortotal;
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
     * @return the numeroparcelas
     */
    public int getNumeroparcelas() {
        return numeroparcelas;
    }

    /**
     * @param numeroparcelas the numeroparcelas to set
     */
    public void setNumeroparcelas(int numeroparcelas) {
        this.numeroparcelas = numeroparcelas;
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
     * @return the parcelas
     */
    public List<Parcela> getParcelas() {
        return parcelas;
    }

    /**
     * @param parcelas the parcelas to set
     */
    public void setParcelas(List<Parcela> parcelas) {
        this.parcelas = parcelas;
    }

    /**
     * @return the forma_pagamento
     */
    public String getForma_pagamento() {
        return forma_pagamento;
    }

    /**
     * @param forma_pagamento the forma_pagamento to set
     */
    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

}
