/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Dao.DaoCaixa;
import br.com.pbd.Dao.DaoLoja;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Caixa;
import br.com.pbd.Modelo.Loja;
import br.com.pbd.Modelo.Venda;

/**
 *
 * @author Andre-Coude
 */
public class BusinessGeral extends GenericDao<Caixa> implements IBusinessGeral {

    @Override
    public void salvar(Loja loja) {
        new GenericDao<Loja>().salvar_ou_atualizar(loja);
    }

    @Override
    public Loja buscarLoja() {
        return new DaoLoja().buscaUltimoLoja();
    }

    @Override
    public void salvar(Caixa caixa) {
       salvar_ou_atualizar(caixa);
    }

    @Override
    public Caixa buscaCaixa() {
        return new DaoCaixa().buscaUltimoCaixa();
    }

    @Override
    public void salvar(Venda venda) {
        new GenericDao<Venda>().salvar_ou_atualizar(venda);
    }

}
