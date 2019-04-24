/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.DaoRaca;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Animal;
import br.com.pbd.Modelo.Cliente;
import br.com.pbd.Modelo.Especie;
import br.com.pbd.Modelo.Raca;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public class ControleAnimal implements ActionListener {

    TelaPrincipal tPrincipal;
    List<Especie> especies;
    List<Raca> racas;
    List<Cliente> clientes;

    public ControleAnimal(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;

        especies = new ArrayList<Especie>();
        racas = new ArrayList<Raca>();
        clientes = new ArrayList<Cliente>();

        tPrincipal.getcAnimal().getBtnSalvar().addActionListener(this);
        tPrincipal.getcAnimal().getComboEspecie().addActionListener(this);
        preencherEspecie();
        preencherClientes();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tPrincipal.getcAnimal().getBtnSalvar()) {
            cadastrarAnimal();
        }
        if (e.getSource() == tPrincipal.getcAnimal().getComboEspecie()) {

            if (tPrincipal.getcAnimal().isVisible()) {
                preencherRaca();
            }
        }
    }

    public void cadastrarAnimal() {

        Animal animal = new Animal();

        animal.setApelido(tPrincipal.getcAnimal().getTxtaApelido().getText());
        animal.setNome(tPrincipal.getcAnimal().getTxtNome().getText());
        animal.setCor(tPrincipal.getcAnimal().getTxtCor().getText());
        animal.setObservacao(tPrincipal.getcAnimal().getAreaObservacao().getText());
        animal.setSexo(tPrincipal.getcAnimal().getComboSexo().getSelectedItem().toString());
        animal.setObservacao(tPrincipal.getcAnimal().getAreaObservacao().getText());
        java.sql.Date nascimento = ConverterData(tPrincipal.getcAnimal().getNascimento().getDate());
        animal.setNascimento(nascimento);

        String peso = tPrincipal.getcAnimal().getTxtPeso().getText();
        Double pesokg = 0.0;
        try {
            pesokg = Double.parseDouble(peso);
        } catch (Exception erro) {
        }
        animal.setPesokg(pesokg);

        int indiceRaca = tPrincipal.getcAnimal().getComboRaca().getSelectedIndex();
        int indiceCliente = tPrincipal.getcAnimal().getComboDono().getSelectedIndex();
        Raca raca = racas.get(indiceRaca);
        Cliente cli = clientes.get(indiceCliente);

        animal.setRaca(raca);
        animal.setCliente(cli);

        new GenericDao<Animal>().salvar_ou_atualizar(animal);
        
        tPrincipal.getcAnimal().setVisible(false);

    }

    public java.sql.Date ConverterData(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public final void preencherEspecie() {
        especies = new GenericDao<Especie>().getAll(Especie.class);

        tPrincipal.getcAnimal().getComboEspecie().removeAllItems();
        especies.forEach((e) -> {
            tPrincipal.getcAnimal().getComboEspecie().addItem(e.getNome());
        });

    }

    public final void preencherRaca() {

        int indice = tPrincipal.getcAnimal().getComboEspecie().getSelectedIndex();

        Especie e = especies.get(indice);

        racas = new DaoRaca().usandoID(e);

        tPrincipal.getcAnimal().getComboRaca().removeAllItems();
        racas.forEach((ra) -> {
            tPrincipal.getcAnimal().getComboRaca().addItem(ra.getNome());
        });
    }

    public final void preencherClientes() {

        clientes = new GenericDao<Cliente>().getAll(Cliente.class);
        tPrincipal.getcAnimal().getComboDono().removeAllItems();
        clientes.forEach((c) -> {
            tPrincipal.getcAnimal().getComboDono().addItem(c.getNome());
        });
    }
}
