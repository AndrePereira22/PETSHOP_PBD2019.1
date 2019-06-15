/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.fachada;

import br.com.pbd.Modelo.Agenda;
import br.com.pbd.Modelo.Animal;
import br.com.pbd.Modelo.Cliente;
import br.com.pbd.Modelo.Fornecedor;
import br.com.pbd.Modelo.Funcionario;
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

    public List<Cliente> getAllCliente();

    public void ativarDesativar(Cliente cliente);

    public List<Cliente> buscaCliente(String nome);

    public void salvar(Funcionario funcionario);

    public List<Funcionario> getAllFuncionario();

    public void ativarDesativar(Funcionario funcionario);

    public List<Funcionario> buscaFuncionario(String nome);

        public void salvar(Profissional profissional);

    public List<Profissional> getAllProfissionals();

    public void ativarDesativar(Profissional profissional);

    public List<Profissional> buscaProfissionals(String nome);
    
    public void salvar(Fornecedor fornecedor);

    public List<Fornecedor> getAllFornecedor();

    public void ativarDesativar(Fornecedor fornecedor);

    public List<Fornecedor> buscaFornecedors(String nome);
}
