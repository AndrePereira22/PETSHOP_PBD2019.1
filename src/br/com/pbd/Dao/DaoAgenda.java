/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.Agenda;
import br.com.pbd.Modelo.Profissional;
import br.com.pbd.sql.SQLConexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Andre-Coude
 */
public class DaoAgenda {
     private static final EntityManager manager = SQLConexao.getEntityManager();

    public List<Agenda>  buscaAgenda(Profissional pro) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT  agenda FROM Agenda agenda where agenda.profissional =:obj");
            query.setParameter("obj", pro);
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar servicos");
        }
        return query.getResultList();
    }
}
