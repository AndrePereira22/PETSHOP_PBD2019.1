/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Modelo.Caixa;
import br.com.pbd.Modelo.Loja;
import br.com.pbd.Modelo.Venda;

/**
 *
 * @author Andre-Coude
 */
public interface IBusinessGeral {
    
     public void salvar(Loja loja);

    public Loja buscarLoja();

    public void salvar(Caixa caixa);

    public Caixa buscaCaixa();
    
    public void salvar(Venda venda);
}
