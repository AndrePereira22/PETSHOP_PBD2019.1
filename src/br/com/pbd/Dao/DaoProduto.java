/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.Fornecedor;
import br.com.pbd.Modelo.GrupoProduto;
import br.com.pbd.Modelo.Produto;
import br.com.pbd.Visao.ViewProduto;
import br.com.pbd.sql.SQLConexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Andre-Coude
 */
public class DaoProduto {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public List<Produto> Busca(String nome) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT produto FROM Produto produto where produto.nome like '%" + nome + "%'");

        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar produtos");
        }
        return query.getResultList();
    }

    public List<Produto> listarProduto(GrupoProduto grupo) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT  produto FROM Produto produto where produto.gproduto=:grupo ");
            query.setParameter("grupo", grupo);
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar produtos");
        }
        return query.getResultList();
    }

    public List<Produto> listarProdutoFornecedor(Fornecedor fornecedor) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT  produto FROM Produto produto where produto.fornecedor=:obj ");
            query.setParameter("obj", fornecedor);
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar produtos");
        }
        return query.getResultList();
    }

    public Produto bucarPorId(int id) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT p FROM Produto p where p.id=:obj");
            query.setParameter("obj", id);
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar produtos");
        }
        return (Produto) query.getSingleResult();
    }
}
