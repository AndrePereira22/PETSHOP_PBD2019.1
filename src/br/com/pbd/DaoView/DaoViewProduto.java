/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.DaoView;

import br.com.pbd.Visao.ViewProduto;
import br.com.pbd.sql.SQLConexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andre-Coude
 */
public class DaoViewProduto {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public List<ViewProduto> getAllView() {

        manager.getTransaction().begin();
	TypedQuery<ViewProduto> vp = (TypedQuery<ViewProduto>) manager.createNativeQuery("select * from public.view_produtos",ViewProduto.class);
	manager.getTransaction().commit();
       
        return vp.getResultList();
    }

    public List<ViewProduto> Busca(String nome) {

        manager.getTransaction().begin();
	TypedQuery<ViewProduto> vp = (TypedQuery<ViewProduto>)
  manager.createNativeQuery("select * from view_produtos  where nome like '%" + nome + "%'" ,
          ViewProduto.class);
	
        
        manager.getTransaction().commit();
       
        return vp.getResultList();
    }
    
}
