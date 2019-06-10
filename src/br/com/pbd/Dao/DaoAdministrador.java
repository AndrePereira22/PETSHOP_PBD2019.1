/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.Administrador;
import br.com.pbd.sql.SQLConexao;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Andre-Coude
 */
public class DaoAdministrador {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public Administrador  busca() {

        Query query = null;
        try {
            query = manager.createQuery("SELECT adm FROM Administrador adm  ORDER BY adm.id desc");
            query.setMaxResults(1);
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar ADM");
        }
        return (Administrador) query.getSingleResult();

    }
}
