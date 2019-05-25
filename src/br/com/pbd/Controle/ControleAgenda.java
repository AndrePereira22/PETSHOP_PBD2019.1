/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.DaoAgenda;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Animal;
import br.com.pbd.Modelo.Pagamento;
import br.com.pbd.Modelo.Profissional;
import br.com.pbd.Modelo.Servico;
import br.com.pbd.Modelo.Agenda;
import br.com.pbd.Modelo.Render;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
    List<Servico> servicos;
    List<Agenda> agendas;

    public ControleAgenda(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;

        profissionais = new ArrayList<Profissional>();
        animais = new ArrayList<Animal>();
        servicos = new ArrayList<Servico>();
        agendas = new ArrayList<Agenda>();

        tPrincipal.getBtnAgenda().addActionListener(this);
        tPrincipal.getAgenda().getBtnAddEdit().addActionListener(this);
        tPrincipal.getAgenda().getComboProfissional().addActionListener(this);
        tPrincipal.getAgenarServico().getBtnSalvar().addActionListener(this);
        tPrincipal.getAgenarServico().getComboAnimal().addActionListener(this);
        tPrincipal.getAgenarServico().getBtnCancelar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tPrincipal.getBtnAgenda()) {
            preencherProfissionais();
        }

        if (e.getSource() == tPrincipal.getAgenarServico().getBtnCancelar()) {

            tPrincipal.getAgenda().setVisible(true);

        }

        if (e.getSource() == tPrincipal.getAgenda().getComboProfissional()) {
            listarAgenda();

        }

        if (e.getSource() == tPrincipal.getAgenda().getBtnAddEdit()) {

            if (!profissionais.isEmpty()) {

                preencherAnimais();
                preencherServicos();

                tPrincipal.getAgenda().setVisible(false);
                tPrincipal.getAgenarServico().getTxtProfissional().setText(profissional.getNome());

                tPrincipal.getAgenarServico().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "VOCÊ PRECISA CADASTRAR PROFISSIONARIS !");
            }

        }
        if (e.getSource() == tPrincipal.getAgenarServico().getBtnSalvar()) {

            salvarServico();
        }
        if (e.getSource() == tPrincipal.getAgenarServico().getComboAnimal()) {
            preencherDadosAnimal();
        }
    }

    private void salvarServico() {

        Agenda agenda = new Agenda();
        Servico servico = null;
        Animal animal = null;

        int indiceAnimal = tPrincipal.getAgenarServico().getComboAnimal().getSelectedIndex();
        int indiceServico = tPrincipal.getAgenarServico().getComboServico().getSelectedIndex();

        Date d = new Date(System.currentTimeMillis());

        Pagamento pagamento = new Pagamento();
        pagamento.setValortotal(0.0);
        pagamento.setNumeroparcelas(0);
        pagamento.setStatus(false);
        pagamento.setData(ConverterData(d));
        pagamento.setForma_pagamento("DINHEIRO");

        agenda.setAnotacao(tPrincipal.getAgenarServico().getAreaObservacao().getText());
        agenda.setHorario(ConverterTime(tPrincipal.getAgenarServico().getComboHorario().getSelectedItem().toString()));

        agenda.setPagamento(pagamento);

        agenda.setProfissional(profissional);

        try {
            java.sql.Date data = ConverterData(tPrincipal.getAgenarServico().getData().getDate());
            agenda.setData(data);
            animal = animais.get(indiceAnimal);
            servico = servicos.get(indiceServico);
            agenda.setServico(servico);
            agenda.setAnimal(animal);

            new GenericDao<Agenda>().salvar_ou_atualizar(agenda);
            JOptionPane.showMessageDialog(null, "Agendado com Sucesso!");
            tPrincipal.getAgenarServico().setVisible(false);
            tPrincipal.getAgenda().setVisible(true);
            tPrincipal.getAgenda().getComboProfissional();
            listarAgenda();

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");

        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");

        }

    }

    private void preencherDadosAnimal() {
        int indice = tPrincipal.getAgenarServico().getComboAnimal().getSelectedIndex();

        if (indice >= 0) {
            animal = animais.get(indice);
            tPrincipal.getAgenarServico().getAreaNotasAnimal().setText(animal.getObservacao());
            tPrincipal.getAgenarServico().getTxtRaca().setText(animal.getRaca().getNome());
            tPrincipal.getAgenarServico().getTxtDono().setText(animal.getCliente().getNome());
        }
    }

    public final void preencherAnimais() {

        animais = new GenericDao<Animal>().getAll(Animal.class);
        tPrincipal.getAgenarServico().getComboAnimal().removeAllItems();
        animais.forEach((c) -> {
            tPrincipal.getAgenarServico().getComboAnimal().addItem(c.getNome());
        });
    }

    public final void preencherServicos() {

        servicos = new GenericDao<Servico>().getAll(Servico.class);
        tPrincipal.getAgenarServico().getComboServico().removeAllItems();
        servicos.forEach((c) -> {
            tPrincipal.getAgenarServico().getComboServico().addItem(c.getDescricao());
        });
    }

    public void listarAgenda() {

        int indice = tPrincipal.getAgenda().getComboProfissional().getSelectedIndex();

        if (!profissionais.isEmpty()) {

            int i = 0;
            try {
                profissional = profissionais.get(indice);
                agendas = new DaoAgenda().buscaAgenda(profissional);

                tPrincipal.getAgenda().getTabelaAgenda().setDefaultRenderer(Object.class, new Render());

                String[] colunas = new String[]{"HORARIO", "SERVICO"};
                Object[][] dados = new Object[agendas.size()][2];
                for (Agenda a : agendas) {
                    dados[i][0] = a.getHorario();
                    dados[i][1] = a.getServico().getDescricao();

                    i++;
                }

                DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tPrincipal.getAgenda().getTabelaAgenda().setModel(dataModel);
            } catch (Exception ex) {

            }
        }
    }

    private void preencherProfissionais() {

        profissionais = new GenericDao<Profissional>().getAll(Profissional.class);

        if (tPrincipal.getAgenda().getComboProfissional().getSelectedIndex() >= 0) {
            tPrincipal.getAgenda().getComboProfissional().removeAllItems();
        }
        profissionais.forEach((p) -> {
            tPrincipal.getAgenda().getComboProfissional().addItem(p.getNome());
        });
        listarAgenda();
    }

    public java.sql.Date ConverterData(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public java.sql.Time ConverterTime(String relogio) {
        SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
        Date data = null;
        try {
            data = formatador.parse(relogio);
        } catch (ParseException ex) {
            Logger.getLogger(ControleAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Time(data.getTime());

    }
}
