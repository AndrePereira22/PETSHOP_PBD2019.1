/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.AgendaAnimal;
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
public class DaoAgendaAnimal {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public List<AgendaAnimal> buscaAgenda(Animal animal) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT a FROM AgendaAnimal a where a.animal =:obj");
            query.setParameter("obj",animal);
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar servicos");
        }
        return query.getResultList();
    }

   

}
