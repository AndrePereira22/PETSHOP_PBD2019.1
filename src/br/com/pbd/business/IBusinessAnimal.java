/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Modelo.Animal;
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
    
}
