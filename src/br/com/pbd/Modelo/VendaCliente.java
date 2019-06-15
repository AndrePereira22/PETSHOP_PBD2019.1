
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Modelo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Andre-Coude
 */

@Table(name = "venda_cliente")
 @Embeddable
public class VendaCliente  {
    
   
    @Column(name="venda_id")
    private int venda_id;
    @Column(name="clientes_id")
    private int cliente_id;

    /**
     * @return the venda_id
     */
    public int getVenda_id() {
        return venda_id;
    }

    /**
     * @param venda_id the venda_id to set
     */
    public void setVenda_id(int venda_id) {
        this.venda_id = venda_id;
    }

    /**
     * @return the cliente_id
     */
    public int getCliente_id() {
        return cliente_id;
    }

    /**
     * @param cliente_id the cliente_id to set
     */
    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    

    
}
