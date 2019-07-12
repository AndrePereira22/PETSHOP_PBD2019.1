/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Dao.DaoAgendaAnimal;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.AgendaAnimal;
import br.com.pbd.Modelo.Animal;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public class BusinessAgendaAnimal extends GenericDao<AgendaAnimal> implements IBusinessAgendaAnimal {

    @Override
    public void salvar(AgendaAnimal agenda) {

        salvar_ou_atualizar(agenda);
    }

    @Override
    public List<AgendaAnimal> getAll() {
        return (getAll(AgendaAnimal.class));
    }

    @Override
    public void ativarDesativar(AgendaAnimal agenda) {

        salvar_ou_atualizar(agenda);
    }

    @Override
    public List<AgendaAnimal> busca(Animal animal) {

        return (new DaoAgendaAnimal().buscaAgenda(animal));

    }

}
