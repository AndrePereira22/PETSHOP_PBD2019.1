/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.Caixa;
import br.com.pbd.sql.SQLConexao;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andre-Coude
 */
public class DaoCaixa {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public Caixa  buscaUltimoCaixa() {

        Query query = null;
        try {
            query = manager.createQuery("SELECT caixa FROM Caixa caixa  ORDER BY caixa.id desc");
            query.setMaxResults(1);
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar servicos");
        }
        return (Caixa) query.getSingleResult();

    }
    public void Busca(Long id) {

        manager.getTransaction().begin();
        TypedQuery<Double> vp = (TypedQuery<Double>)
                manager.createNativeQuery("select lucro("+id+")",Double.class);

        manager.getTransaction().commit();

    }
}
