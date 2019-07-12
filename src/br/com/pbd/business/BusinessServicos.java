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
public class BusinessServicos extends GenericDao<Servico> implements IBusinessServicos {
    
    @Override
    public void salvar(Servico servico) {
        salvar_ou_atualizar(servico);
    }
    
    @Override
    public List<Servico> getAll() {
        return (getAll(Servico.class));
    }
    
    @Override
    public void ativarDesativar(Servico servico) {
        
        salvar_ou_atualizar(servico);
    }
    
    @Override
    public List<Servico> busca(String nome) {
        
        return new DaoServico().Busca(nome);
        
    }
    
    @Override
    public List<Servico> buscarAtivos(Boolean ativo) {
        return new DaoServico().BuscarAtivos(ativo);
    }
    
}
