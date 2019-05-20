/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.Produto;
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

    public List<Produto>  listarProduto(String nome) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT  produto FROM Produto produto where produto.descricao like '%"+ nome +"%'");
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar servicos");
        }
        return query.getResultList();
    }
}
