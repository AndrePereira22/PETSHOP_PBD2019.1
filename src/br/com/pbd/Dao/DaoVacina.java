/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.Vacina;
import br.com.pbd.sql.SQLConexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Andre-Coude
 */
public class DaoVacina {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public List<Vacina> buscarVacina(String nome) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT v FROM Vacina v where v.nome like '%" + nome + "%'or v.codigo like '%" + nome + "%'");
          
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar Raças");
        }
        return query.getResultList();
    }
   
}
