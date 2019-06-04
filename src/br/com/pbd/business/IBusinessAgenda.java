/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Modelo.Agenda;
import br.com.pbd.Modelo.Profissional;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public interface IBusinessAgenda {
    
     public void salvar(Agenda agenda);

    public List<Agenda> getAll();

    public void ativarDesativar(Agenda agenda);

    public List<Agenda> busca(Profissional pro,Date data);
    
}
