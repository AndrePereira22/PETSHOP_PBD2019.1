/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Animal;
import br.com.pbd.Modelo.Cliente;
import br.com.pbd.Modelo.Pagamento;
import br.com.pbd.Modelo.Profissional;
import br.com.pbd.Modelo.Servico;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Andre-Coude
 */
public class ControleAgenda implements ActionListener {

    TelaPrincipal tPrincipal;
    List<Profissional> profissionais;
    Profissional profissional;
    Animal animal;
    List<Animal> animais;

    public ControleAgenda(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;

        profissionais = new ArrayList<Profissional>();
        animais = new ArrayList<Animal>();

        preencherProfissionais(tPrincipal.getAgenda().getComboProfissional());

        tPrincipal.getBtnAgenda().addActionListener(this);
        tPrincipal.getAgenda().getBtnAddEdit().addActionListener(this);
        tPrincipal.getcServico().getBtnSalvar().addActionListener(this);
        tPrincipal.getcServico().getComboAnimal().addActionListener(this);
        tPrincipal.getcServico().getBtnCancelar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tPrincipal.getBtnAgenda()) {
            preencherProfissionais(tPrincipal.getAgenda().getComboProfissional());
        }

        if (e.getSource() == tPrincipal.getcServico().getBtnCancelar()) {
            preencherProfissionais(tPrincipal.getAgenda().getComboProfissional());
            tPrincipal.getAgenda().setVisible(true);

        }

        if (e.getSource() == tPrincipal.getAgenda().getBtnAddEdit()) {

            preencherAnimais();
            tPrincipal.getAgenda().setVisible(false);
            int indice = tPrincipal.getAgenda().getComboProfissional().getSelectedIndex();
            profissional = profissionais.get(indice);
            tPrincipal.getcServico().getComboProfissional().setEnabled(false);
            tPrincipal.getcServico().getComboProfissional().addItem(profissional.getNome());

            tPrincipal.getcServico().setVisible(true);

        }
        if (e.getSource() == tPrincipal.getcServico().getBtnSalvar()) {

            salvarServico();
        }
        if (e.getSource() == tPrincipal.getcServico().getComboAnimal() && tPrincipal.getcServico().isVisible()) {
            preencherDadosAnimal();
        }
    }

    private void preencherProfissionais(JComboBox combo) {
        profissionais = new GenericDao<Profissional>().getAll(Profissional.class);

        tPrincipal.getAgenda().getComboProfissional().removeAllItems();
        profissionais.forEach((p) -> {
            combo.addItem(p.getNome());
        });
    }

    public final void preencherAnimais() {

        animais = new GenericDao<Animal>().getAll(Animal.class);
        tPrincipal.getcServico().getComboAnimal().removeAllItems();
        animais.forEach((c) -> {
            tPrincipal.getcServico().getComboAnimal().addItem(c.getNome());
        });
    }

    private void salvarServico() {

        int indice = tPrincipal.getcServico().getComboAnimal().getSelectedIndex();
        Animal animal = animais.get(indice);
        java.sql.Date data = ConverterData(tPrincipal.getcServico().getData().getDate());

        Servico servico = new Servico();

        Date d = new Date(System.currentTimeMillis());

        Pagamento pagamento = new Pagamento();
        pagamento.setValortotal(0.0);
        pagamento.setNumeroparcelas(0);
        pagamento.setStatus(false);
        pagamento.setData(ConverterData(d));
        // pagamento.setCaixa();

        servico.setAnimal(animal);
        servico.setData(data);
        servico.setAnotacao(tPrincipal.getcServico().getAreaObservacao().getText());
        servico.setDescricao(tPrincipal.getcServico().getComboServico().getSelectedItem().toString());
        servico.setProfissional(profissional);
        servico.setHorario(new Time(Integer.MAX_VALUE));
        servico.setValor(10.0);
        servico.setPagamento(pagamento);

        new GenericDao<Servico>().salvar_ou_atualizar(servico);

    }

    private void preencherDadosAnimal() {
        int indice = tPrincipal.getcServico().getComboAnimal().getSelectedIndex();
        animal = animais.get(indice);
        tPrincipal.getcServico().getAreaNotasAnimal().setText(animal.getObservacao());
        tPrincipal.getcServico().getTxtRaca().setText(animal.getRaca().getNome());
        tPrincipal.getcServico().getTxtDono().setText(animal.getCliente().getNome());
    }

    public java.sql.Date ConverterData(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }
}
