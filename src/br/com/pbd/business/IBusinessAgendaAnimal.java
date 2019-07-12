/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Modelo.AgendaAnimal;
import br.com.pbd.Modelo.Animal;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public interface IBusinessAgendaAnimal {
    
     public void salvar(AgendaAnimal agenda);

    public List<AgendaAnimal> getAll();

    public void ativarDesativar(AgendaAnimal agenda);

    public List<AgendaAnimal> busca(Animal animal);
    
}
