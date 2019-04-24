/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.Caixa;
import br.com.pbd.sql.SQLConexao;
import java.sql.Date;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andre-Coude
 */
public class DaoFinanceiro  {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public Caixa buscarCaixa(Date data){
         TypedQuery<Caixa> query= null;
        try { query = manager.createQuery("SELECT obj FROM Caixa obj where obj.data =:data ", Caixa.class);
            query.setParameter("data", data);

        } catch (IllegalStateException e) {
        }
        return query.getSingleResult();
    }
//    public TypedQuery buscarCaixa(Date data) {
//
//        CriteriaBuilder cb = manager.getCriteriaBuilder();
//        CriteriaQuery cq = cb.createQuery(Caixa.class);
//        Root<Caixa> root = cq.from(Caixa.class);
//        EntityType<Caixa> modelo = root.getModel();
//
//         cq.where(cb.equal(root.get(modelo.getSingularAttribute("data", Date.class)), data));
//        return  manager.createQuery(cq);
//    }
}
