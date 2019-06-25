/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Dao.DaoGrupoProduto;
import br.com.pbd.Dao.DaoProduto;
import br.com.pbd.Dao.DaoVenda;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.GrupoProduto;
import br.com.pbd.Modelo.ItemVenda;
import br.com.pbd.Modelo.Produto;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public class BusinessProduto implements IBusinessProduto {

    @Override
    public void salvar(Produto produto) {

        new GenericDao<Produto>().salvar_ou_atualizar(produto);
    }

    @Override
    public List<Produto> getAll() {

        return new GenericDao<Produto>().getAll(Produto.class);
    }

    @Override
    public boolean excluir(Produto produto) {

        List<ItemVenda> itens = new DaoVenda().buscaItens(produto);
        if (itens.isEmpty()) {
            new GenericDao<Produto>().remover(Produto.class, produto.getId());
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<Produto> buscaGrupo(GrupoProduto grupo) {
        return new DaoProduto().listarProduto(grupo);
    }

    @Override
    public List<Produto> busca(String nome) {
        return new DaoProduto().Busca(nome);
    }

    @Override
    public void salvar(GrupoProduto grupo) {
        new GenericDao<GrupoProduto>().salvar_ou_atualizar(grupo);
    }

    @Override
    public List<GrupoProduto> getAllGrupo() {

        return new GenericDao<GrupoProduto>().getAll(GrupoProduto.class);
    }

    @Override
    public void excluir(GrupoProduto grupo) {
        new GenericDao<GrupoProduto>().remover(GrupoProduto.class, grupo.getId());
    }

    @Override
    public List<GrupoProduto> buscaGrupo(String nome) {
        return new DaoGrupoProduto().Busca(nome);
    }

}
