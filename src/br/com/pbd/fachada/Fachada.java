/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.fachada;

import br.com.pbd.Modelo.Agenda;
import br.com.pbd.Modelo.Animal;
import br.com.pbd.Modelo.Profissional;
import br.com.pbd.business.BusinessAgenda;
import br.com.pbd.business.BusinessAnimal;
import br.com.pbd.business.IBusinessAgenda;
import br.com.pbd.business.IBusinessAnimal;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public class Fachada implements IFachada {

    private static Fachada instance;
    private IBusinessAgenda bAgenda;
    private IBusinessAnimal bAnimal;

    public static Fachada getInstance() {
        if (instance == null) {
            instance = new Fachada();
        }
        return instance;
    }

    private Fachada() {

        this.bAgenda = new BusinessAgenda();
        this.bAnimal = new BusinessAnimal();
    }

    @Override
    public void salvar(Agenda agenda) {
        this.bAgenda.salvar(agenda);
    }

    @Override
    public List<Agenda> getAllAgenda() {
        return this.bAgenda.getAll();
    }

    @Override
    public void ativarDesativar(Agenda agenda) {
        this.bAgenda.ativarDesativar(agenda);
    }

    @Override
    public List<Agenda> busca(Profissional pro, Date data) {
        return this.bAgenda.busca(pro, data);
    }

    @Override
    public void salvar(Animal animal) {
     this.bAnimal.salvar(animal); 
    }

    @Override
    public List<Animal> getAllAnimal() {
    return this.bAnimal.getAll();
    }

    @Override
    public void ativarDesativar(Animal animal) {
     this.bAnimal.ativarDesativar(animal);
    }

    @Override
    public List<Animal> busca(String nome) {
     return this.bAnimal.busca(nome
     );
    }

}
