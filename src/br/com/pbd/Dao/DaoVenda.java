/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.Caixa;
import br.com.pbd.sql.SQLConexao;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Andre-Coude
 */
public class DaoVenda {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public Caixa  buscaUltimoCaixa() {

        Query query = null;
        try {
            query = manager.createQuery("SELECT caixa FROM Caixa caixa  ORDER BY caixa.id desc");
            query.setMaxResults(1);
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar servicos");
        }
        return (Caixa) query.getSingleResult();

    }
}
