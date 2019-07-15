/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Visao;

import java.sql.Date;
import java.sql.Time;
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
public class ViewAgenda {

    @Id
    @Column(name = "id", nullable = false)
    private int id;


    @Column(name = "horario", nullable = false)
    private Time horario;
    
    @Column(name = "descricao", nullable = false)
    private String descricao;
    
    @Column(name = "data", nullable = false)
    private Date data;

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
     * @return the horario
     */
    public Time getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(Time horario) {
        this.horario = horario;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
    
    

  

}
