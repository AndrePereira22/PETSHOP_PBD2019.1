/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.GrupoProduto;
import br.com.pbd.sql.SQLConexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Andre-Coude
 */
public class DaoGrupoProduto {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public List<GrupoProduto> Busca(String nome) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT grupo FROM GrupoProduto grupo where grupo.nome like '%" + nome + "%'");

        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar grupos");
        }
        return query.getResultList();
    }

}
