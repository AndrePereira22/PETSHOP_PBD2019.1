/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.business;

import br.com.pbd.Dao.DaoAnimal;
import br.com.pbd.Dao.DaoCliente;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Animal;
import br.com.pbd.Modelo.Cliente;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public class BusinessCliente extends GenericDao<Cliente> implements  IBusinessCliente {
 
    @Override
    public void salvar(Cliente cliente) {

       salvar_ou_atualizar(cliente);
    }

    @Override
    public boolean ativarDesativar(Cliente cliente) {

        List<Animal> animais = new DaoAnimal().buscaPorCliente(cliente);
        if (animais.isEmpty()) {
            remover(Cliente.class, cliente.getId());
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<Cliente> getAll() {
        return (getAll(Cliente.class));
    }

    @Override
    public List<Cliente> busca(String nome) {

        return (new DaoCliente().Busca(nome));

    }

}
