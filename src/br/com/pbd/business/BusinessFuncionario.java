/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Dao.DaoAnimal;
import br.com.pbd.Dao.DaoCliente;
import br.com.pbd.Dao.DaoFuncionario;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Animal;
import br.com.pbd.Modelo.Cliente;
import br.com.pbd.Modelo.Funcionario;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public class BusinessFuncionario implements IBusinessFuncionario {

    @Override
    public void salvar(Funcionario funcionario) {

        new GenericDao<Funcionario>().salvar_ou_atualizar(funcionario);
    }

    @Override
    public List<Funcionario> getAll() {

        return (new GenericDao<Funcionario>().getAll(Funcionario.class));
    }

    @Override
    public void remover(Funcionario funcionario) {
        new GenericDao<Funcionario>().remover(Funcionario.class,funcionario.getId());
    }

    @Override
    public List<Funcionario> busca(String nome) {

        return (new DaoFuncionario().Busca(nome));
    }

}
