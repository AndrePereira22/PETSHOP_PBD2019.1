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
import java.sql.Date;

/**
 *
 * @author Andre-Coude
 */
public class DaoAgenda {

    private static final EntityManager manager = SQLConexao.getEntityManager();

   
    

    public List<Agenda> buscaAgenda(Profissional pro, Date data) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT  agenda FROM Agenda agenda where agenda.profissional =:obj and agenda.data =:date");
            query.setParameter("obj", pro);
            query.setParameter("date", data);
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar servicos");
        }
        return query.getResultList();
    }

    public List<Agenda> buscarAgendas(Date data) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT  agenda FROM Agenda agenda where agenda.data =:date");
            query.setParameter("date", data);
        } catch (IllegalStateException e) {
            query.setParameter("date", data);
            System.out.println("erro ao buscar agendas");
        }
        return query.getResultList();
    }

}
