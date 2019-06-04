/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Dao.DaoAnimal;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Animal;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public class BusinessAnimal implements IBusinessAnimal {

    @Override
    public void salvar(Animal animal) {
        new GenericDao<Animal>().salvar_ou_atualizar(animal);
    }

    @Override
    public void ativarDesativar(Animal animal) {
        new GenericDao<Animal>().salvar_ou_atualizar(animal);
    }

    @Override
    public List<Animal> getAll() {
        return (new GenericDao<Animal>().getAll(Animal.class));
    }

    @Override
    public List<Animal> busca(String nome) {

        return (new DaoAnimal().Busca(nome));

    }

}
