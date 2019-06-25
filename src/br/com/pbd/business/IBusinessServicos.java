/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Modelo.Servico;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public interface IBusinessServicos {
    
     public void salvar(Servico servico);

    public List<Servico> getAll();

    public void ativarDesativar(Servico servico);

    public List<Servico> busca(String nome);
    public List<Servico> buscarAtivos(Boolean ativo);
    
}
