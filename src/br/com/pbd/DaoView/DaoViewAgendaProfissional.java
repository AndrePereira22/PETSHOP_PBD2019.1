/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.DaoView;

import br.com.pbd.Modelo.AgendaProfissional;
import br.com.pbd.Modelo.Profissional;
import br.com.pbd.Visao.ViewAgenda;
import br.com.pbd.sql.SQLConexao;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andre-Coude
 */
public class DaoViewAgendaProfissional {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public List<ViewAgenda> getAllView() {

        manager.getTransaction().begin();
        TypedQuery<ViewAgenda> vp = (TypedQuery<ViewAgenda>) manager.createNativeQuery("select * from public.view_agenda_pro", ViewAgenda.class);
        manager.getTransaction().commit();

        return vp.getResultList();
    }

    public List<ViewAgenda> Busca(String nome) {

        manager.getTransaction().begin();
        TypedQuery<ViewAgenda> vp = (TypedQuery<ViewAgenda>) manager.createNativeQuery("select * from view_agenda_pro  where nome like '%" + nome + "%'"
                + "or cpf like '%" + nome + "%'",
                ViewAgenda.class);

        manager.getTransaction().commit();

        return vp.getResultList();
    }
    
    public List<ViewAgenda> buscaAgenda(Profissional pro, Date data) {
         manager.getTransaction().begin();
        TypedQuery<ViewAgenda> vp = (TypedQuery<ViewAgenda>) manager.createNativeQuery("select * from view_agenda_pro  where profissional_id="+pro.getId() +" and "
                + "data='"+data+"'",ViewAgenda.class);

        manager.getTransaction().commit();

        return vp.getResultList();
        
        
    }


}
