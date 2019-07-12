/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Modelo.Servico;
import br.com.pbd.Modelo.Vacina;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public interface IBusinessVacina {

    public void salvar(Vacina vacina);

    public List<Vacina> getAll();

    public void excluir(Vacina vacina);

    public List<Vacina> busca(String nome);

}
