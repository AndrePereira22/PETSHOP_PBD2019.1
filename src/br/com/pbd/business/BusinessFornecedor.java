/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Dao.DaoFornecedor;
import br.com.pbd.Dao.DaoProduto;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Fornecedor;
import br.com.pbd.Modelo.Produto;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public class BusinessFornecedor implements IBusinessFornecedor {

    @Override
    public void salvar(Fornecedor fornecedor) {

        new GenericDao<Fornecedor>().salvar_ou_atualizar(fornecedor);
    }

    @Override
    public List<Fornecedor> getAll() {

        return (new GenericDao<Fornecedor>().getAll(Fornecedor.class));
    }

    @Override
    public boolean remover(Fornecedor fornecedor) {

        List<Produto> lista = new DaoProduto().listarProdutoFornecedor(fornecedor);

        if (lista.isEmpty()) {
            new GenericDao<Fornecedor>().remover(Fornecedor.class, fornecedor.getId());
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<Fornecedor> busca(String nome) {

        return (new DaoFornecedor().Busca(nome));
    }

}
