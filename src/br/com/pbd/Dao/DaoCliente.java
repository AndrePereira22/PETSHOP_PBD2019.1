/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.Cliente;
import br.com.pbd.Modelo.Produto;
import br.com.pbd.sql.SQLConexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Andre-Coude
 */
public class DaoCliente {
     private static final EntityManager manager = SQLConexao.getEntityManager();

    public List<Cliente> Busca(String nome) {
            Query query = null;
            try {
                query = manager.createQuery("SELECT  cliente FROM Cliente cliente where cliente.nome like '%" + nome + "%' or cliente.cpf like '%" + nome + "%' or cliente.Rg like '%" + nome + "%' ");

            } catch (IllegalStateException e) {
                System.out.println("erro ao buscar cliente");
            }
            return query.getResultList();
        }
    
    public Cliente bucarPorId(int id) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT p FROM Cliente p where p.id=:obj");
            query.setParameter("obj", id);
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar produtos");
        }
        return (Cliente) query.getSingleResult();
    }
}
