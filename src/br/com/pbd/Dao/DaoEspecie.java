/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.Especie;
import br.com.pbd.sql.SQLConexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Andre-Coude
 */
public class DaoEspecie {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public List<Especie> Busca(String nome) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT  especie FROM Especie especie where especie.nome like '%" + nome + "%'");

        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar cliente");
        }
        return query.getResultList();
    }

    public Especie Buscar(String nome) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT  especie FROM Especie especie where especie.nome=:obj");
            query.setParameter("obj", nome);
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar cliente");
        }
        return (Especie) query.getSingleResult();
    }
}
