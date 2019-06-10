/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.fachada;

import br.com.pbd.Modelo.Agenda;
import br.com.pbd.Modelo.Animal;
import br.com.pbd.Modelo.Cliente;
import br.com.pbd.Modelo.Profissional;
import br.com.pbd.business.BusinessAgenda;
import br.com.pbd.business.BusinessAnimal;
import br.com.pbd.business.BusinessCliente;
import br.com.pbd.business.IBusinessAgenda;
import br.com.pbd.business.IBusinessAnimal;
import br.com.pbd.business.IBusinessCliente;
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
    private IBusinessCliente bCliente;

    public static Fachada getInstance() {
        if (instance == null) {
            instance = new Fachada();
        }
        return instance;
    }

    private Fachada() {

        this.bAgenda = new BusinessAgenda();
        this.bAnimal = new BusinessAnimal();
        this.bCliente = new BusinessCliente();
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
    public List<Agenda> buscaAgenda(Profissional pro, Date data) {
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
    public List<Animal> buscaAnimal(String nome) {
        return this.bAnimal.busca(nome
        );
    }

    @Override
    public void salvar(Cliente cliente) {
        this.bCliente.salvar(cliente);
    }

    @Override
    public List<Cliente> getAll() {
        return this.bCliente.getAll();
    }

    @Override
    public void ativarDesativar(Cliente cliente) {
        this.bCliente.salvar(cliente);
    }

    @Override
    public List<Cliente> buscaCliente(String nome) {
        return this.bCliente.busca(nome);
    }

}
