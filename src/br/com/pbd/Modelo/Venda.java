
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Modelo;

import java.sql.Time;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Andre-Coude
 */
@Entity
@SequenceGenerator(name = "venda_seq", sequenceName = "venda_seq", initialValue = 1, allocationSize = 1)
@Table(name = "venda")
public class Venda implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_seq")
    private Long id;

    @Column(name = "valor_total", precision = 6, scale = 2, nullable = false)
    private Double valortotal;
    @Column(name = "horario", nullable = false)
    private Time hora;

    @OneToOne(cascade = CascadeType.ALL)
    private Pagamento pagamento;

    
    @OneToOne
    private Caixa caixa;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda")
    @JoinColumn(name = "venda_id")
    private List<ItemVenda> itens;

    @OneToMany
    private List<Cliente> clientes;

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
     * @return the hora
     */
    public Time getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(Time hora) {
        this.hora = hora;
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

    /**
     * @return the itens
     */
    public List<ItemVenda> getItens() {
        return itens;
    }

    /**
     * @param itens the itens to set
     */
    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    /**
     * @return the clientes
     */
    public List<Cliente> getClientes() {
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
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
