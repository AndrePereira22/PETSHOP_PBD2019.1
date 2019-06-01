/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.Especie;
import br.com.pbd.Modelo.Raca;
import br.com.pbd.sql.SQLConexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Andre-Coude
 */
public class DaoRaca {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public List<Raca> usandoID(Especie especie) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT  raca FROM Raca raca where raca.especie =:obj");
            query.setParameter("obj", especie);
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar Raças");
        }
        return query.getResultList();
    }
    public List<Raca> Busca(String nome) {
            Query query = null;
            try {
                query = manager.createQuery("SELECT  raca FROM Raca raca where raca.nome like '%" + nome + "%' ");

            } catch (IllegalStateException e) {
                System.out.println("erro ao buscar racas");
            }
            return query.getResultList();
        }

}
