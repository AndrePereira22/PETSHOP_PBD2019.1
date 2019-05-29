/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.Profissional;
import br.com.pbd.sql.SQLConexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Andre-Coude
 */
public class DaoProfissional {
     private static final EntityManager manager = SQLConexao.getEntityManager();

        
    public List<Profissional> Busca(String nome) {
            Query query = null;
            try {
                query = manager.createQuery("SELECT  pro FROM Profissional pro where pro.nome like '%" + nome + "%' or pro.cpf like '%" + nome + "%' or pro.rg like '%" + nome + "%' ");

            } catch (IllegalStateException e) {
                System.out.println("erro ao buscar Fornecedor");
            }
            return query.getResultList();
        }
}
