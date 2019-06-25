/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Dao.DaoAgenda;
import br.com.pbd.Dao.DaoProfissional;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Agenda;
import br.com.pbd.Modelo.Profissional;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public class BusinessProfissional implements IBusinessProfissional {

    @Override
    public void salvar(Profissional profissional) {

        new GenericDao<Profissional>().salvar_ou_atualizar(profissional);
    }

    @Override
    public List<Profissional> getAll() {

        return (new GenericDao<Profissional>().getAll(Profissional.class));
    }

    @Override
    public boolean remover(Profissional profissional) {
        
     List<Agenda> lista = new DaoAgenda().buscaAgendaProfissional(profissional);
        if (lista.isEmpty()) {
            new GenericDao<Profissional>().remover(Profissional.class, profissional.getId());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Profissional> busca(String nome) {
        return (new DaoProfissional().Busca(nome));

    }

}
