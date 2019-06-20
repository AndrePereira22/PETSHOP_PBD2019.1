/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Dao.DaoServico;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Servico;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public class BusinessServicos implements IBusinessServicos {

    @Override
    public void salvar(Servico servico) {

        new GenericDao<Servico>().salvar_ou_atualizar(servico);
    }

    @Override
    public List<Servico> getAll() {
        return (new GenericDao<Servico>().getAll(Servico.class));
    }

    @Override
    public void ativarDesativar(Servico servico) {

        new GenericDao<Servico>().salvar_ou_atualizar(servico);
    }

    @Override
    public List<Servico> busca(String nome) {

        return new DaoServico().Busca(nome);

    }

}
