/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Modelo.Animal;
import br.com.pbd.Modelo.Especie;
import br.com.pbd.Modelo.Raca;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public interface IBusinessAnimal {

    public void salvar(Animal animal);

    public List<Animal> getAll();

    public void ativarDesativar(Animal animal);

    public List<Animal> busca(String nome);

    public void salvarRaca(Raca raca);

    public List<Raca> getAllRaca();

    public void ativarDesativar(Raca raca);

    public List<Raca> buscaRaca(Especie especie);

    public List<Raca> buscaRaca(String nome);

    public void salvarEspecie(Especie especie);

    public List<Especie> getAllEspecie();

    public void ativarDesativar(Especie especie);

    public List<Especie> buscaEspecie(String nome);

    public Especie buscar(String nome);

}
