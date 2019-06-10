/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Modelo.Animal;
import br.com.pbd.Modelo.Cliente;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public interface IBusinessCliente {
    
    public void salvar(Cliente cliente);

    public List<Cliente> getAll();

    public void ativarDesativar(Cliente cliente);

    public List<Cliente> busca(String nome);
    
}
