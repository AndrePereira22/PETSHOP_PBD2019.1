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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Andre-Coude
 */
@Entity
@SequenceGenerator(name = "parcela_seq", sequenceName = "parcela_seq", initialValue = 1, allocationSize = 1)
@Table(name = "parcela")
public class Parcela implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parcela_seq")
    private Long id;

    @Column(name = "numero", precision = 2, scale = 0, nullable = false)
    private int numero;
    @Column(name = "valor", precision = 3, scale = 2, nullable = false)
    private Double valor;
    @Column(name = "status", insertable = false, nullable = false)
    private Boolean status;
    @Column(name = "datavencimento", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = false)
    private Date datavencimento;

    @ManyToOne
    private Pagamento pagamento;

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
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
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
     * @return the datavencimento
     */
    public Date getDatavencimento() {
        return datavencimento;
    }

    /**
     * @param datavencimento the datavencimento to set
     */
    public void setDatavencimento(Date datavencimento) {
        this.datavencimento = datavencimento;
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

}
