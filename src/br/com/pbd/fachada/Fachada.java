/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.fachada;

import br.com.pbd.Modelo.AgendaAnimal;
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
import br.com.pbd.Modelo.Vacina;
import br.com.pbd.Modelo.Venda;
import br.com.pbd.business.BusinessAgenda;
import br.com.pbd.business.BusinessAgendaAnimal;
import br.com.pbd.business.BusinessAnimal;
import br.com.pbd.business.BusinessCliente;
import br.com.pbd.business.BusinessFornecedor;
import br.com.pbd.business.BusinessFuncionario;
import br.com.pbd.business.BusinessGeral;
import br.com.pbd.business.BusinessProduto;
import br.com.pbd.business.BusinessProfissional;
import br.com.pbd.business.BusinessServicos;
import br.com.pbd.business.BusinessVacina;
import br.com.pbd.business.IBusinessAgenda;
import br.com.pbd.business.IBusinessAgendaAnimal;
import br.com.pbd.business.IBusinessAnimal;
import br.com.pbd.business.IBusinessCliente;
import br.com.pbd.business.IBusinessFornecedor;
import br.com.pbd.business.IBusinessFuncionario;
import br.com.pbd.business.IBusinessGeral;
import br.com.pbd.business.IBusinessProduto;
import br.com.pbd.business.IBusinessProfissional;
import java.sql.Date;
import java.util.List;
import br.com.pbd.business.IBusinessServicos;
import br.com.pbd.business.IBusinessVacina;

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
    private final IBusinessServicos bServico;
    private final IBusinessProduto bProduto;
    private final IBusinessGeral bGeral;
    private final IBusinessAgendaAnimal bAgendaAnimal;
    private final IBusinessVacina bVacina;

    public static Fachada getInstance() {
        if (instance == null) {
            instance = new Fachada();
        }
        return instance;
    }

    private Fachada() {

        this.bAnimal = new BusinessAnimal();
        this.bCliente = new BusinessCliente();
        this.bFuncionario = new BusinessFuncionario();
        this.bFornecedor = new BusinessFornecedor();
        this.bProfissional = new BusinessProfissional();
        this.bServico = new BusinessServicos();
        this.bAgenda = new BusinessAgenda();
        this.bProduto = new BusinessProduto();
        this.bGeral = new BusinessGeral();
        this.bAgendaAnimal = new BusinessAgendaAnimal();
        this.bVacina = new BusinessVacina();

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
    public boolean remover(Animal animal) {
        return this.bAnimal.remover(animal);
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
    public boolean removerCliente(Cliente cliente) {
        return (this.bCliente.ativarDesativar(cliente));
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
    public void remover(Funcionario funcionario) {
        this.bFuncionario.remover(funcionario);
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
    public boolean remover(Profissional profissional) {
        return (this.bProfissional.remover(profissional));
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
    public boolean removerFornecedor(Fornecedor fornecedor) {
        return (this.bFornecedor.remover(fornecedor));
    }

    @Override
    public List<Fornecedor> buscaFornecedors(String nome) {
        return this.bFornecedor.busca(nome);
    }

    @Override
    public void salvar(Servico servico) {
        this.bServico.salvar(servico);

    }

    @Override
    public List<Servico> getAllServico() {
        return this.bServico.getAll();
    }

    @Override
    public void ativarDesativar(Servico servico) {
        this.bServico.ativarDesativar(servico);

    }

    @Override
    public List<Servico> buscaServico(String nome) {
        return this.bServico.busca(nome);
    }

    @Override
    public List<Servico> buscarAtivos(Boolean ativo) {
        return this.bServico.buscarAtivos(ativo);
    }

    @Override
    public void salvar(AgendaProfissional agenda) {
        this.bAgenda.salvar(agenda);

    }

    @Override
    public List<AgendaProfissional> getAllAgenda() {
        return this.bAgenda.getAll();
    }

    @Override
   public void excluirAgendaProfissional(Long id){
        this.bAgenda.excluir(id);

    }

    @Override
    public List<AgendaProfissional> buscaAgenda(Profissional pro, Date data) {
        return this.bAgenda.busca(pro, data);
    }

    @Override
    public void salvarRaca(Raca raca) {
        this.bAnimal.salvarRaca(raca);
    }

    @Override
    public List<Raca> getAllRaca() {
        return this.bAnimal.getAllRaca();
    }

    @Override
    public void ativarDesativar(Raca raca) {
        this.bAnimal.ativarDesativar(raca);
    }

    @Override
    public List<Raca> buscaRaca(String nome) {
        return this.bAnimal.buscaRaca(nome);
    }

    @Override
    public List<Raca> buscaRaca(Especie especie) {
        return this.bAnimal.buscaRaca(especie);
    }

    @Override
    public void salvarEspecie(Especie especie) {
        this.bAnimal.salvarEspecie(especie);
    }

    @Override
    public List<Especie> getAllEspecie() {
        return this.bAnimal.getAllEspecie();
    }

    @Override
    public void ativarDesativar(Especie especie) {
        this.bAnimal.ativarDesativar(especie);
    }

    @Override
    public List<Especie> buscaEspecie(String nome) {
        return this.bAnimal.buscaEspecie(nome);
    }

    @Override
    public Especie buscar(String nome) {
        return this.bAnimal.buscar(nome);
    }

    @Override
    public void salvar(Produto produto) {
        this.bProduto.salvar(produto);
    }

    @Override
    public List<Produto> getAllProduto() {
        return this.bProduto.getAll();
    }

    @Override
    public boolean excluir(Produto produto) {
        return this.bProduto.excluir(produto);
    }

    @Override
    public List<Produto> buscaGrupo(GrupoProduto grupo) {
        return this.bProduto.buscaGrupo(grupo);
    }

    @Override
    public List<Produto> buscaProduto(String nome) {
        return this.bProduto.busca(nome);

    }

    @Override
    public void salvar(GrupoProduto grupo) {
        this.bProduto.salvar(grupo);
    }

    @Override
    public List<GrupoProduto> getAllGrupo() {
        return this.bProduto.getAllGrupo();
    }

    @Override
    public void excluir(GrupoProduto grupo) {
        this.bProduto.excluir(grupo);
    }

    @Override
    public List<GrupoProduto> buscaGrupo(String nome) {
        return this.bProduto.buscaGrupo(nome);
    }

    @Override
    public void salvar(Loja loja) {
        this.bGeral.salvar(loja);
    }

    @Override
    public Loja buscarLoja() {
        return this.bGeral.buscarLoja();
    }

    @Override
    public void salvar(Caixa caixa) {
        this.bGeral.salvar(caixa);
    }

    @Override
    public Caixa buscaCaixa() {
        return this.bGeral.buscaCaixa();
    }

    @Override
    public void salvar(Venda venda) {
        this.bGeral.salvar(venda);
    }

    @Override
    public void salvar(AgendaAnimal agenda) {
        this.bAgendaAnimal.salvar(agenda);
    }

    @Override
    public List<AgendaAnimal> getAll() {
        return this.bAgendaAnimal.getAll();
    }

    @Override
    public void ativarDesativar(AgendaAnimal agenda) {
        this.bAgendaAnimal.salvar(agenda);
    }

    @Override
    public List<AgendaAnimal> busca(Animal animal) {
        return this.bAgendaAnimal.busca(animal);
    }

    @Override
    public void salvar(Vacina vacina) {
        this.bVacina.salvar(vacina);
    }

    @Override
    public void excluir(Vacina vacina) {
        this.bVacina.excluir(vacina);
    }

    @Override
    public List<Vacina> busca(String nome) {
        return this.bVacina.busca(nome);
    }

    @Override
    public List<Vacina> getAllVacina() {
        return this.bVacina.getAll();
    }

}
