/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.Animal;
import br.com.pbd.Modelo.Cliente;
import br.com.pbd.Modelo.Especie;
import br.com.pbd.Modelo.Raca;
import br.com.pbd.sql.SQLConexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Andre-Coude
 */
public class DaoAnimal {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public List<Animal> usandoID(Cliente cliente) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT  animal FROM Animal animal where animal.cliente =:obj");
            query.setParameter("obj", cliente);
        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar animais");
        }
        return query.getResultList();
    }
    public List<Animal> Busca(String nome) {
            Query query = null;
            try {
                query = manager.createQuery("SELECT  animal FROM Animal animal where animal.nome like '%" + nome + "%'or animal.apelido like '%" + nome + "%' or animal.cliente.nome like '%" + nome + "%' ");

            } catch (IllegalStateException e) {
                System.out.println("erro ao buscar racas");
            }
            return query.getResultList();
        }

}
