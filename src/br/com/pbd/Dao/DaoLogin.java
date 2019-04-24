/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.Especie;
import br.com.pbd.Modelo.Login;
import br.com.pbd.Modelo.Raca;
import br.com.pbd.sql.SQLConexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Andre-Coude
 */
public class DaoLogin {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    

    public Object verificarLogin( Login login,String s) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT obj FROM "+ s+" obj where obj.login.senha =:senha and obj.login.usuario=:usuario");
            query.setParameter("senha", login.getSenha());
            query.setParameter("usuario", login.getUsuario());
        } catch (IllegalStateException e) {
        }
        return query.getSingleResult();
    }

        
}
