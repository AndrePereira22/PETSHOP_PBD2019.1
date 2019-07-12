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
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.AgendaProfissional;
import br.com.pbd.Modelo.Animal;
import br.com.pbd.Modelo.Especie;
import br.com.pbd.Modelo.Raca;
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
    public boolean remover(Animal animal) {

        List<AgendaProfissional> lista = new DaoAgenda().buscaAgendaAnimal(animal);
        if (lista.isEmpty()) {
            new GenericDao<Animal>().remover(Animal.class, animal.getId());
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<Animal> getAll() {
        return (new GenericDao<Animal>().getAll(Animal.class));
    }

    @Override
    public List<Animal> busca(String nome) {

        return (new DaoAnimal().Busca(nome));

    }

    @Override
    public void salvarRaca(Raca raca) {

        new GenericDao<Raca>().salvar_ou_atualizar(raca);
    }

    @Override
    public List<Raca> getAllRaca() {

        return (new GenericDao<Raca>().getAll(Raca.class));
    }

    @Override
    public void ativarDesativar(Raca raca) {

        new GenericDao<Raca>().salvar_ou_atualizar(raca);

    }

    @Override
    public List<Raca> buscaRaca(Especie especie) {
        return (new DaoRaca().buscaPorEspecie(especie));
    }

    @Override
    public List<Raca> buscaRaca(String nome) {
        return new DaoRaca().Busca(nome);
    }

    @Override
    public void salvarEspecie(Especie especie) {

        new GenericDao<Especie>().salvar_ou_atualizar(especie);
    }

    @Override
    public List<Especie> getAllEspecie() {

        return new GenericDao<Especie>().getAll(Especie.class);
    }

    @Override
    public void ativarDesativar(Especie especie) {

        new GenericDao<Especie>().salvar_ou_atualizar(especie);
    }

    @Override
    public List<Especie> buscaEspecie(String nome) {
        return (new DaoEspecie().Busca(nome));
    }

    @Override
    public Especie buscar(String nome) {
        return (new DaoEspecie().Buscar(nome));
    }

}
