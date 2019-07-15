/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.AgendaProfissional;
import br.com.pbd.Modelo.Animal;
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

   
    

    public List<AgendaProfissional> buscaAgenda(Profissional pro, Date data) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT  agenda FROM AgendaProfissional agenda where agenda.profissional =:obj and agenda.data =:date");
            query.setParameter("obj", pro);
            query.setParameter("date", data);
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar servicos");
        }
        return query.getResultList();
    }

    public List<AgendaProfissional> buscarAgendas(Date data) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT  agenda FROM AgendaProfissional agenda where agenda.data =:date");
            query.setParameter("date", data);
        } catch (IllegalStateException e) {
            query.setParameter("date", data);
            System.out.println("erro ao buscar agendas");
        }
        return query.getResultList();
    }
    public List<AgendaProfissional> buscaAgendaProfissional(Profissional pro) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT  agenda FROM AgendaProfissional agenda where agenda.profissional =:obj");
            query.setParameter("obj", pro);
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar servicos");
        }
        return query.getResultList();
    }
     public List<AgendaProfissional> buscaAgendaAnimal(Animal pro) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT  agenda FROM Agenda agenda where agenda.animal =:obj");
            query.setParameter("obj", pro);
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar servicos");
        }
        return query.getResultList();
    }
     public AgendaProfissional  bucarPorId(int id) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT p FROM AgendaProfissional p where p.id=:obj");
            query.setParameter("obj", id);
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar produtos");
        }
        return (AgendaProfissional) query.getSingleResult();
    }

}
