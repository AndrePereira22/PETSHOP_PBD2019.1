/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.Animal;
import br.com.pbd.Modelo.Cliente;
import br.com.pbd.Modelo.Especie;
import br.com.pbd.Modelo.ItemVenda;
import br.com.pbd.Modelo.Produto;
import br.com.pbd.Modelo.Raca;
import br.com.pbd.sql.SQLConexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Andre-Coude
 */
public class DaoVenda {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public List<ItemVenda> buscaItens(Produto produto) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT  item FROM ItemVenda item where item.produto =:obj");
            query.setParameter("obj", produto);
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar animais");
        }
        return query.getResultList();
    }
   

}
