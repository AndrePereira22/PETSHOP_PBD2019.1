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
@SequenceGenerator(name="pagamento_seq",sequenceName="pagamento_seq", initialValue=1,allocationSize=1)
@Table(name = "pagamento")
public class Pagamento implements EntidadeBase {
    
     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="pagamento_seq")
     private Long id;
       
     @Column 
     private Double valortotal;
     @Column
     private Boolean status;
     @Column
     private int numeroparcelas;
     @Column
     private Date data ;
     
     
     @ManyToOne
     private Caixa caixa;

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
     * @return the caixa
     */
    public Caixa getCaixa() {
        return caixa;
    }

    /**
     * @param caixa the caixa to set
     */
    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }
    
}
