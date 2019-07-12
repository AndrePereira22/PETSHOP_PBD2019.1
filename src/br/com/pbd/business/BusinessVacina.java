/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Dao.DaoAgenda;
import br.com.pbd.Dao.DaoAnimal;
import br.com.pbd.Dao.DaoEspecie;
import br.com.pbd.Dao.DaoRaca;
import br.com.pbd.Dao.DaoVacina;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.AgendaProfissional;
import br.com.pbd.Modelo.Animal;
import br.com.pbd.Modelo.Especie;
import br.com.pbd.Modelo.Raca;
import br.com.pbd.Modelo.Vacina;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public class BusinessVacina extends GenericDao<Vacina> implements IBusinessVacina {

    @Override
    public void salvar(Vacina vacina) {
        salvar_ou_atualizar(vacina);
    }

    @Override
    public List<Vacina> getAll() {
        return (getAll(Vacina.class));
    }

    @Override
    public List<Vacina> busca(String nome) {

        return (new DaoVacina().buscarVacina(nome));

    }

    @Override
    public void excluir(Vacina vacina) {
        remover(Vacina.class, vacina.getId());

    }

}
