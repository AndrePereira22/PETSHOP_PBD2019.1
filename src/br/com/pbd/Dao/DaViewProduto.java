/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.ViewProdutos;
import br.com.pbd.sql.SQLConexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andre-Coude
 */
public class DaViewProduto {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public List<ViewProdutos> Busca(String nome) {

        manager.getTransaction().begin();
	TypedQuery<ViewProdutos> vp = (TypedQuery<ViewProdutos>) manager.createNativeQuery("select * from public.viuuprodutos",ViewProdutos.class);
	manager.getTransaction().commit();
       
        return vp.getResultList();
    }

    public List<ViewProdutos> Buscaa(String nome) {

        manager.getTransaction().begin();
	TypedQuery<ViewProdutos> vp = (TypedQuery<ViewProdutos>)
                manager.createNativeQuery("select * from viuuprodutos  where nome ='" + nome +"'" ,ViewProdutos.class);
	
        
        manager.getTransaction().commit();
       
        return vp.getResultList();
    }
}
