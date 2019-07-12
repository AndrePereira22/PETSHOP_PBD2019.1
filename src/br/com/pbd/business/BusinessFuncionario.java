/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Dao.DaoFuncionario;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Funcionario;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public class BusinessFuncionario extends GenericDao<Funcionario> implements IBusinessFuncionario {

    @Override
    public void salvar(Funcionario funcionario) {
        salvar_ou_atualizar(funcionario);
    }

    @Override
    public List<Funcionario> getAll() {

        return (getAll(Funcionario.class));
    }

    @Override
    public void remover(Funcionario funcionario) {
        remover(Funcionario.class, funcionario.getId());
    }

    @Override
    public List<Funcionario> busca(String nome) {

        return (new DaoFuncionario().Busca(nome));
    }

}
