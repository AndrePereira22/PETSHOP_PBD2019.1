/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Modelo.Profissional;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public interface IBusinessProfissional {
    
    public void salvar(Profissional profissional);

    public List<Profissional> getAll();

    public boolean remover(Profissional profissional);

    public List<Profissional> busca(String nome);
    
}
