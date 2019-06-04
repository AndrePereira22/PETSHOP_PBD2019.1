/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Dao.DaoAgenda;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Agenda;
import br.com.pbd.Modelo.Profissional;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public class BusinessAgenda implements IBusinessAgenda {

    @Override
    public void salvar(Agenda agenda) {

        new GenericDao<Agenda>().salvar_ou_atualizar(agenda);
    }

    @Override
    public List<Agenda> getAll() {
        return (new GenericDao<Agenda>().getAll(Agenda.class));
    }

    @Override
    public void ativarDesativar(Agenda agenda) {

        new GenericDao<Agenda>().salvar_ou_atualizar(agenda);
    }

    @Override
    public List<Agenda> busca(Profissional pro,Date data) {
    
    return (new DaoAgenda().buscaAgenda(pro, data));
    
    }

}
