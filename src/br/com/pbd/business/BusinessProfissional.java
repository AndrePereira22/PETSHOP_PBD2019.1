/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Dao.DaoAgenda;
import br.com.pbd.Dao.DaoProfissional;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.AgendaProfissional;
import br.com.pbd.Modelo.Profissional;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public class BusinessProfissional extends GenericDao<Profissional> implements IBusinessProfissional {

    @Override
    public void salvar(Profissional profissional) {

        salvar_ou_atualizar(profissional);
    }

    @Override
    public List<Profissional> getAll() {

        return (getAll(Profissional.class));
    }

    @Override
    public boolean remover(Profissional profissional) {
        
     List<AgendaProfissional> lista = new DaoAgenda().buscaAgendaProfissional(profissional);
        if (lista.isEmpty()) {
            remover(Profissional.class, profissional.getId());
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
