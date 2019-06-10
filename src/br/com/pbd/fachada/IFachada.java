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
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public interface IFachada {

    public void salvar(Agenda agenda);

    public List<Agenda> getAllAgenda();

    public void ativarDesativar(Agenda agenda);

    public List<Agenda> buscaAgenda(Profissional pro, Date data);

    public void salvar(Animal animal);

    public List<Animal> getAllAnimal();

    public void ativarDesativar(Animal animal);

    public List<Animal> buscaAnimal(String nome);

    public void salvar(Cliente cliente);

    public List<Cliente> getAll();

    public void ativarDesativar(Cliente cliente);

    public List<Cliente> buscaCliente(String nome);

}
