/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.fachada;

import br.com.pbd.Modelo.AgendaProfissional;
import br.com.pbd.Modelo.Animal;
import br.com.pbd.Modelo.Caixa;
import br.com.pbd.Modelo.Cliente;
import br.com.pbd.Modelo.Especie;
import br.com.pbd.Modelo.Fornecedor;
import br.com.pbd.Modelo.Funcionario;
import br.com.pbd.Modelo.GrupoProduto;
import br.com.pbd.Modelo.Loja;
import br.com.pbd.Modelo.Produto;
import br.com.pbd.Modelo.Profissional;
import br.com.pbd.Modelo.Raca;
import br.com.pbd.Modelo.Servico;
import br.com.pbd.Modelo.Venda;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public interface IFachada {

    public void salvar(AgendaProfissional agenda);

    public List<AgendaProfissional> getAllAgenda();

    public void ativarDesativar(AgendaProfissional agenda);

    public List<AgendaProfissional> buscaAgenda(Profissional pro, Date data);

    public void salvar(Animal animal);

    public List<Animal> getAllAnimal();

    public boolean remover(Animal animal);

    public List<Animal> buscaAnimal(String nome);

    public void salvar(Cliente cliente);

    public List<Cliente> getAllCliente();

    public boolean removerCliente(Cliente cliente);

    public List<Cliente> buscaCliente(String nome);

    public void salvar(Funcionario funcionario);

    public List<Funcionario> getAllFuncionario();

    public void remover(Funcionario funcionario);

    public List<Funcionario> buscaFuncionario(String nome);

    public void salvar(Profissional profissional);

    public List<Profissional> getAllProfissionals();

    public boolean remover(Profissional profissional);

    public List<Profissional> buscaProfissionals(String nome);

    public void salvar(Fornecedor fornecedor);

    public List<Fornecedor> getAllFornecedor();

    public boolean removerFornecedor(Fornecedor fornecedor);

    public List<Fornecedor> buscaFornecedors(String nome);

    public void salvar(Servico servico);

    public List<Servico> getAllServico();

    public void ativarDesativar(Servico servico);

    public List<Servico> buscaServico(String nome);

    public List<Servico> buscarAtivos(Boolean ativo);

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

    public void salvar(Produto produto);

    public List<Produto> getAllProduto();

    public boolean excluir(Produto produto);

    public List<Produto> buscaGrupo(GrupoProduto grupo);

    public List<Produto> buscaProduto(String nome);

    public void salvar(GrupoProduto grupo);

    public List<GrupoProduto> getAllGrupo();

    public void excluir(GrupoProduto grupo);

    public List<GrupoProduto> buscaGrupo(String nome);

    public void salvar(Loja loja);

    public Loja buscarLoja();

    public void salvar(Caixa caixa);

    public Caixa buscaCaixa();

    public void salvar(Venda venda);
}
