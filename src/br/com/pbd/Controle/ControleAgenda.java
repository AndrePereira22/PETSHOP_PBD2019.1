/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Animal;
import br.com.pbd.Modelo.Pagamento;
import br.com.pbd.Modelo.Profissional;
import br.com.pbd.Modelo.Servico;
import br.com.pbd.Modelo.Agenda;
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

    public ControleAgenda(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;

        profissionais = new ArrayList<Profissional>();
        animais = new ArrayList<Animal>();
        servicos = new ArrayList<Servico>();

        preencherProfissionais(tPrincipal.getAgenda().getComboProfissional());

        tPrincipal.getBtnAgenda().addActionListener(this);
        tPrincipal.getAgenda().getBtnAddEdit().addActionListener(this);
        tPrincipal.getAgenarServico().getBtnSalvar().addActionListener(this);
        tPrincipal.getAgenarServico().getComboAnimal().addActionListener(this);
        tPrincipal.getAgenarServico().getBtnCancelar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tPrincipal.getBtnAgenda()) {
            preencherProfissionais(tPrincipal.getAgenda().getComboProfissional());
        }

        if (e.getSource() == tPrincipal.getAgenarServico().getBtnCancelar()) {
            preencherProfissionais(tPrincipal.getAgenda().getComboProfissional());
            tPrincipal.getAgenda().setVisible(true);

        }

        if (e.getSource() == tPrincipal.getAgenda().getBtnAddEdit()) {

            preencherAnimais();
            preencherServicos();
            tPrincipal.getAgenda().setVisible(false);
            int indice = tPrincipal.getAgenda().getComboProfissional().getSelectedIndex();
            profissional = profissionais.get(indice);
            tPrincipal.getAgenarServico().getComboProfissional().setEnabled(false);
            tPrincipal.getAgenarServico().getComboProfissional().addItem(profissional.getNome());

            tPrincipal.getAgenarServico().setVisible(true);

        }
        if (e.getSource() == tPrincipal.getAgenarServico().getBtnSalvar()) {

            salvarServico();
        }
        if (e.getSource() == tPrincipal.getAgenarServico().getComboAnimal() && tPrincipal.getAgenarServico().isVisible()) {
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

    private void salvarServico() {

        Agenda agenda = new Agenda();

        int indiceAnimal = tPrincipal.getAgenarServico().getComboAnimal().getSelectedIndex();
        int indiceServico = tPrincipal.getAgenarServico().getComboAnimal().getSelectedIndex();
        Animal animal = animais.get(indiceAnimal);
        Servico servico = servicos.get(indiceServico);

        Date d = new Date(System.currentTimeMillis());
        java.sql.Date data = ConverterData(tPrincipal.getAgenarServico().getData().getDate());

        Pagamento pagamento = new Pagamento();
        pagamento.setValortotal(0.0);
        pagamento.setNumeroparcelas(0);
        pagamento.setStatus(false);
        pagamento.setData(ConverterData(d));

        agenda.setData(data);
        agenda.setAnotacao(tPrincipal.getAgenarServico().getAreaObservacao().getText());
        agenda.setHorario(ConverterTime(tPrincipal.getAgenarServico().getComboHorario().getSelectedItem().toString()));

        agenda.setPagamento(pagamento);
        agenda.setAnimal(animal);
        agenda.setProfissional(profissional);
        agenda.setServico(servico);

        new GenericDao<Agenda>().salvar_ou_atualizar(agenda);

    }

    private void preencherDadosAnimal() {
        int indice = tPrincipal.getAgenarServico().getComboAnimal().getSelectedIndex();
        animal = animais.get(indice);
        tPrincipal.getAgenarServico().getAreaNotasAnimal().setText(animal.getObservacao());
        tPrincipal.getAgenarServico().getTxtRaca().setText(animal.getRaca().getNome());
        tPrincipal.getAgenarServico().getTxtDono().setText(animal.getCliente().getNome());
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
