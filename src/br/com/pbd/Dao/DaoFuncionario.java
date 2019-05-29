/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Dao;

import br.com.pbd.Modelo.Funcionario;
import br.com.pbd.sql.SQLConexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Andre-Coude
 */
public class DaoFuncionario {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public List<Funcionario> Busca(String nome) {
        Query query = null;
        try {
            query = manager.createQuery("SELECT  funcionario FROM Funcionario funcionario where funcionario.nome like '%" + nome + "%' or funcionario.cpf like '%" + nome + "%' or funcionario.rg like '%" + nome + "%' ");

        } catch (IllegalStateException e) {
            System.out.println("erro ao buscar Funcionario");
        }
        return query.getResultList();
    }

}
