/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.Especie;
import br.com.pbd.sql.SQLConexao;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Andre-Coude
 */
public class DaoEspecie {

    private static final EntityManager manager = SQLConexao.getEntityManager();

     public Especie  buscarEspecie(String nome) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT  especie FROM Especie especie where especie.nome like '%"+ nome +"%'");
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar especie");
        }
        return (Especie)query.getSingleResult();
    }
}
