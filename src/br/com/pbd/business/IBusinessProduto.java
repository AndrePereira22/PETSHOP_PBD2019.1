/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Modelo.GrupoProduto;
import br.com.pbd.Modelo.Produto;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public interface IBusinessProduto {

    public void salvar(Produto produto);

    public List<Produto> getAll();

    public void excluir(Produto produto);

    public List<Produto> buscaGrupo(GrupoProduto grupo);

    public List<Produto> busca(String nome);

    public void salvar(GrupoProduto grupo);

    public List<GrupoProduto> getAllGrupo();

    public void excluir(GrupoProduto grupo);

    public List<GrupoProduto> buscaGrupo(String nome);

}
