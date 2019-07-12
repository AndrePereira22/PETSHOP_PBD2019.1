/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Dao.DaoAgenda;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.AgendaProfissional;
import br.com.pbd.Modelo.Profissional;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public class BusinessAgenda extends GenericDao<AgendaProfissional> implements IBusinessAgenda {

    @Override
    public void salvar(AgendaProfissional agenda) {

        salvar_ou_atualizar(agenda);
    }

    @Override
    public List<AgendaProfissional> getAll() {
        return (getAll(AgendaProfissional.class));
    }

    @Override
    public void ativarDesativar(AgendaProfissional agenda) {

        salvar_ou_atualizar(agenda);
    }

    @Override
    public List<AgendaProfissional> busca(Profissional pro,Date data) {
    
    return (new DaoAgenda().buscaAgenda(pro, data));
    
    }

}
