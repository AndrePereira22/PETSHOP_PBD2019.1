/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.Cliente;
import br.com.pbd.Modelo.Fornecedor;
import br.com.pbd.Modelo.Funcionario;
import br.com.pbd.Modelo.Produto;
import br.com.pbd.sql.SQLConexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Andre-Coude
 */
public class DaoFornecedor {
     private static final EntityManager manager = SQLConexao.getEntityManager();

    public List<Fornecedor> Busca(String nome) {
            Query query = null;
            try {
                query = manager.createQuery("SELECT for FROM Fornecedor for where for.nomefantasia like '%" + nome + "%'");

            } catch (IllegalStateException e) {
                System.out.println("erro ao buscar Fornecedor");
            }
            return query.getResultList();
        }
}
