/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Modelo.Funcionario;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public interface IBusinessFuncionario {
    
    public void salvar(Funcionario funcionario);

    public List<Funcionario> getAll();

    public void remover(Funcionario funcionario);

    public List<Funcionario> busca(String nome);
    
}
