/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.AgendaProfissional;
import br.com.pbd.Modelo.ContaAPagar;
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
public class DaoContasApagar {
     private static final EntityManager manager = SQLConexao.getEntityManager();

  
      public List<ContaAPagar>  buscaContas() {
        Query query = null;
        Boolean b = false;
        try {
            query = manager.createQuery("SELECT conta FROM  ContaAPagar conta where conta.status =:obj ");
   query.setParameter("obj", b );
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar contas");
        }
        return query.getResultList();
    }
}
