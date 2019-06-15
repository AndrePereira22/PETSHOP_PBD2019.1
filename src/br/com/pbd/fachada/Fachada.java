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
import br.com.pbd.business.BusinessAgenda;
import br.com.pbd.business.BusinessAnimal;
import br.com.pbd.business.BusinessCliente;
import br.com.pbd.business.BusinessFornecedor;
import br.com.pbd.business.BusinessFuncionario;
import br.com.pbd.business.BusinessProfissional;
import br.com.pbd.business.IBusinessAgenda;
import br.com.pbd.business.IBusinessAnimal;
import br.com.pbd.business.IBusinessCliente;
import br.com.pbd.business.IBusinessFornecedor;
import br.com.pbd.business.IBusinessFuncionario;
import br.com.pbd.business.IBusinessProfissional;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public class Fachada implements IFachada {

    private static Fachada instance;
    private final IBusinessAgenda bAgenda;
    private final IBusinessAnimal bAnimal;
    private final IBusinessCliente bCliente;
    private final IBusinessFuncionario bFuncionario;
    private final IBusinessProfissional bProfissional;
    private final IBusinessFornecedor bFornecedor;

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
        this.bFuncionario = new BusinessFuncionario();
        this.bFornecedor = new BusinessFornecedor();
        this.bProfissional = new BusinessProfissional();
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
    public List<Cliente> getAllCliente() {
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

    @Override
    public void salvar(Funcionario funcionario) {
        this.bFuncionario.salvar(funcionario);
    }

    @Override
    public List<Funcionario> getAllFuncionario() {
        return this.bFuncionario.getAll();
    }

    @Override
    public void ativarDesativar(Funcionario funcionario) {
        this.bFuncionario.salvar(funcionario);
    }

    @Override
    public List<Funcionario> buscaFuncionario(String nome) {
        return this.bFuncionario.busca(nome);
    }

    @Override
    public void salvar(Profissional profissional) {
        this.bProfissional.salvar(profissional);
    }

    @Override
    public List<Profissional> getAllProfissionals() {
        return this.bProfissional.getAll();
    }

    @Override
    public void ativarDesativar(Profissional profissional) {
        this.bProfissional.salvar(profissional);
    }

    @Override
    public List<Profissional> buscaProfissionals(String nome) {
        return this.bProfissional.busca(nome);
    }

    @Override
    public void salvar(Fornecedor fornecedor) {
        this.bFornecedor.salvar(fornecedor);
    }

    @Override
    public List<Fornecedor> getAllFornecedor() {
        return this.bFornecedor.getAll();
    }

    @Override
    public void ativarDesativar(Fornecedor fornecedor) {
        this.bFornecedor.salvar(fornecedor);
    }

    @Override
    public List<Fornecedor> buscaFornecedors(String nome) {
        return this.bFornecedor.busca(nome);
    }

}
