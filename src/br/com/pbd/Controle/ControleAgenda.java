/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.DaoAgenda;
import br.com.pbd.DaoView.DaoViewAgendaProfissional;
import br.com.pbd.Modelo.Animal;
import br.com.pbd.Modelo.Pagamento;
import br.com.pbd.Modelo.Profissional;
import br.com.pbd.Modelo.Servico;
import br.com.pbd.Modelo.AgendaProfissional;
import br.com.pbd.Modelo.ManipularImagem;
import br.com.pbd.Modelo.Render;
import br.com.pbd.Visao.ViewAgenda;
import br.com.pbd.fachada.Fachada;
import br.com.pbd.view.DiaMensagem;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Andre-Coude
 */
public class ControleAgenda extends MouseAdapter implements ActionListener {

    private final Fachada fachada;
    private final TelaPrincipal tPrincipal;
    List<Profissional> profissionais;
    Profissional profissional;
    private AgendaProfissional agenda;
    Animal animal;
    List<Animal> animais;
    List<Servico> servicos;
    List<ViewAgenda> viewagendas;
    private int escolha;
    private final int salvar = 1, edicao = 2, exclusao = 3;
    private final DiaMensagem mens;

    public ControleAgenda(TelaPrincipal tPrincipal, Fachada fachada) {
        this.tPrincipal = tPrincipal;
        this.fachada = fachada;

        this.mens = new DiaMensagem(tPrincipal, true);

        profissionais = new ArrayList<Profissional>();
        animais = new ArrayList<Animal>();
        servicos = new ArrayList<Servico>();
        viewagendas = new ArrayList<ViewAgenda>();

        adicionarEventos();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tPrincipal.getAgenda().getTabelaAgenda()) {

            int ro = retornaIndice(tPrincipal.getAgenda().getTabelaAgenda(), e);
            agenda = new DaoAgenda().bucarPorId(viewagendas.get(ro).getId());
            if (escolha == edicao) {
                tPrincipal.getAgenarServico().setVisible(true);
                tPrincipal.getAgenarServico().preencherDados(agenda);
            } else if (escolha == exclusao) {
                fachada.excluirAgendaProfissional(agenda.getId());
                listarAgendaGeral();

            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tPrincipal.getBtnAgenda()) {
            if (ControleLogin.getProfissional() == null) {

                preencherProfissionais();
                preencherAnimais();
                preencherServicos();
                listarAgendaGeral();
            } else {
                listaAgendaProfissional(ControleLogin.getProfissional());
            }
        }

        if (e.getSource() == tPrincipal.getAgenarServico().getBtnCancelar()) {
            tPrincipal.getAgenarServico().setVisible(false);
            tPrincipal.getAgenda().setVisible(true);

        }
        if (e.getSource() == tPrincipal.getAgenda().getBtnRelatorio()) {
            if (ControleLogin.getProfissional() == null) {

                Profissional p = profissionais.get(tPrincipal.getAgenda().getComboProfissional().getSelectedIndex());
                try {
                    exibirAgenda(p.getId());
                } catch (SQLException ex) {
                    Logger.getLogger(ControleAgenda.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JRException ex) {
                    Logger.getLogger(ControleAgenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    exibirAgenda(ControleLogin.getProfissional().getId());
                } catch (SQLException ex) {
                    Logger.getLogger(ControleAgenda.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JRException ex) {
                    Logger.getLogger(ControleAgenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        if (e.getSource() == tPrincipal.getAgenda().getComboProfissional()) {
            listarAgendaGeral();

        }

        if (e.getSource() == tPrincipal.getAgenda().getBtnAdicionar()) {

            if (!profissionais.isEmpty()) {
                escolha = salvar;

                preencherAnimais();
                preencherServicos();

                tPrincipal.getAgenda().setVisible(false);
                tPrincipal.getAgenarServico().getTxtProfissional().setText(profissional.getNome());

                tPrincipal.getAgenarServico().setVisible(true);
            } else {
                mens.mostrarCadastrePRO();
            }

        }
        if (e.getSource() == tPrincipal.getAgenarServico().getBtnSalvar()) {

            if (escolha == salvar) {
                salvarServico();
            } else if (escolha == edicao) {
                editarServico(agenda);
            }

        }
        if (e.getSource() == tPrincipal.getAgenarServico().getComboAnimal()) {
            preencherDadosAnimal();
        }
    }

    private void salvarServico() {

        AgendaProfissional agenda = new AgendaProfissional();
        Servico servico = null;
        Animal animal = null;

        int indiceAnimal = tPrincipal.getAgenarServico().getComboAnimal().getSelectedIndex();
        int indiceServico = tPrincipal.getAgenarServico().getComboServico().getSelectedIndex();

        Date d = new Date(System.currentTimeMillis());

        Pagamento pagamento = new Pagamento();

        pagamento.setNumeroparcelas(0);
        pagamento.setStatus(false);
        pagamento.setData(ConverterData(d));
        pagamento.setForma_pagamento("DINHEIRO");

        agenda.setAnotacao(tPrincipal.getAgenarServico().getAreaObservacao().getText());
        agenda.setHorario(ConverterTime(tPrincipal.getAgenarServico().getComboHorario().getSelectedItem().toString()));

        agenda.setProfissional(profissional);

        try {
            java.sql.Date data = ConverterData(tPrincipal.getAgenarServico().getData().getDate());
            agenda.setData(data);
            animal = animais.get(indiceAnimal);
            servico = servicos.get(indiceServico);
            agenda.setServico(servico);
            agenda.setAnimal(animal);

            pagamento.setValortotal(servico.getValor());
            agenda.setPagamento(pagamento);

            fachada.salvar(agenda);
            mens.mostrarAgendado();
            tPrincipal.getAgenarServico().setVisible(false);
            tPrincipal.getAgenda().setVisible(true);
            tPrincipal.getAgenda().getComboProfissional();
            listarAgendaGeral();

        } catch (java.lang.IllegalStateException | javax.persistence.RollbackException | java.lang.NullPointerException n) {
            mens.mostrarCamposInvalidos();
        } catch (ArrayIndexOutOfBoundsException e) {
            mens.mostrarErro();
        }

    }

    private void editarServico(AgendaProfissional agenda) {

        Servico servico = null;

        int indiceAnimal = tPrincipal.getAgenarServico().getComboAnimal().getSelectedIndex();
        int indiceServico = tPrincipal.getAgenarServico().getComboServico().getSelectedIndex();

        Date d = new Date(System.currentTimeMillis());

        agenda.setAnotacao(tPrincipal.getAgenarServico().getAreaObservacao().getText());
        agenda.setHorario(ConverterTime(tPrincipal.getAgenarServico().getComboHorario().getSelectedItem().toString()));

        agenda.setProfissional(profissional);

        try {
            java.sql.Date data = ConverterData(tPrincipal.getAgenarServico().getData().getDate());
            agenda.setData(data);
            animal = animais.get(indiceAnimal);
            servico = servicos.get(indiceServico);
            agenda.setServico(servico);
            agenda.setAnimal(animal);

            fachada.salvar(agenda);
            mens.mostrarEdicao();

            tPrincipal.getAgenarServico().setVisible(false);
            tPrincipal.getAgenda().setVisible(true);
            tPrincipal.getAgenda().getComboProfissional();
            listarAgendaGeral();

        } catch (java.lang.IllegalStateException | javax.persistence.RollbackException | ArrayIndexOutOfBoundsException | java.lang.NullPointerException n) {
            mens.mostrarCamposInvalidos();
        }

    }

    private void preencherDadosAnimal() {
        int indice = tPrincipal.getAgenarServico().getComboAnimal().getSelectedIndex();

        if (indice >= 0) {
            animal = animais.get(indice);
            tPrincipal.getAgenarServico().getAreaNotasAnimal().setText(animal.getObservacao());
            tPrincipal.getAgenarServico().getTxtRaca().setText(animal.getRaca().getNome());
            tPrincipal.getAgenarServico().getTxtDono().setText(animal.getCliente().getNome());
            try {
                ManipularImagem.exibiImagemLabel(animal.getImagem(), tPrincipal.getAgenarServico().getLblImagem());

            } catch (java.lang.NullPointerException e) {
            }
        }
    }

    public final void preencherAnimais() {

        animais = fachada.getAllAnimal();
        tPrincipal.getAgenarServico().getComboAnimal().removeAllItems();
        animais.forEach((c) -> {
            tPrincipal.getAgenarServico().getComboAnimal().addItem(c.getNome());
        });
    }

    public final void preencherServicos() {

        servicos = fachada.getAllServico();
        tPrincipal.getAgenarServico().getComboServico().removeAllItems();
        servicos.forEach((c) -> {
            tPrincipal.getAgenarServico().getComboServico().addItem(c.getDescricao());
        });
    }

    public void listarAgendaGeral() {

        int indice = tPrincipal.getAgenda().getComboProfissional().getSelectedIndex();

        if (!profissionais.isEmpty()) {
            java.sql.Date data = ConverterData(tPrincipal.getAgenda().getCalenario().getDate());

            int i = 0;
            try {
                tPrincipal.mudarVisaoData(data);
                profissional = profissionais.get(indice);
                viewagendas = new DaoViewAgendaProfissional().buscaAgenda(profissional, data);
                tPrincipal.getAgenda().getTabelaAgenda().setDefaultRenderer(Object.class, new Render());

                String[] colunas = new String[]{"HORARIO", "SERVICO", "EDITAR", "EXCLUIR"};
                Object[][] dados = new Object[viewagendas.size()][4];
                for (ViewAgenda a : viewagendas) {
                    dados[i][0] = a.getHorario();
                    dados[i][1] = a.getDescricao();
                    dados[i][2] = tPrincipal.getBtnEditar();
                    dados[i][3] = tPrincipal.getBtnExcluir();

                    i++;
                }

                DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tPrincipal.getAgenda().getTabelaAgenda().setModel(dataModel);
            } catch (java.lang.NullPointerException | java.lang.ArrayIndexOutOfBoundsException ex) {

            }

        }
    }

    public void listaAgendaProfissional(Profissional profissional) {

        java.sql.Date data = ConverterData(tPrincipal.getAgenda().getCalenario().getDate());

        int i = 0;
        try {
            tPrincipal.mudarVisaoData(data);
            tPrincipal.getAgenda().getNomeProfissional().setText(profissional.getNome());
            tPrincipal.getAgenda().getNomeProfissional().setVisible(true);
            viewagendas = new DaoViewAgendaProfissional().buscaAgenda(profissional, data);

            tPrincipal.getAgenda().getTabelaAgenda().setDefaultRenderer(Object.class, new Render());

            String[] colunas = new String[]{"HORARIO", "SERVICO"};
            Object[][] dados = new Object[viewagendas.size()][2];
            for (ViewAgenda a : viewagendas) {
                dados[i][0] = a.getHorario();
                dados[i][1] = a.getDescricao();

                i++;
            }

            DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tPrincipal.getAgenda().getTabelaAgenda().setModel(dataModel);
        } catch (java.lang.NullPointerException ex) {

        } catch (java.lang.ArrayIndexOutOfBoundsException ex) {

        }

    }

    private void preencherProfissionais() {

        profissionais = fachada.getAllProfissionals();

        tPrincipal.getAgenda().getComboProfissional().removeAllItems();

        profissionais.forEach((p) -> {
            tPrincipal.getAgenda().getComboProfissional().addItem(p.getNome());
        });
        listarAgendaGeral();
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

    private int retornaIndice(JTable tabela, MouseEvent e) {
        int ro = 0;
        int column = tabela.getColumnModel().getColumnIndexAtX(e.getX());
        int row = e.getY() / tabela.getRowHeight();

        if (row < tabela.getRowCount() && row >= 0 && column < tabela.getColumnCount() && column >= 0) {
            Object value = tabela.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;
                if (boton.getName().equals("editar")) {
                    ro = tabela.getSelectedRow();
                    escolha = edicao;
                } else if (boton.getName().equals("excluir")) {
                    ro = tabela.getSelectedRow();
                    escolha = exclusao;
                } else {
                    escolha = 0;
                }

            }
        }
        return ro;
    }

    public void adicionarEventos() {

        tPrincipal.getBtnAgenda().addActionListener(this);
        tPrincipal.getAgenda().getBtnAdicionar().addActionListener(this);
        tPrincipal.getAgenda().getComboProfissional().addActionListener(this);
        tPrincipal.getAgenarServico().getBtnSalvar().addActionListener(this);
        tPrincipal.getAgenarServico().getComboAnimal().addActionListener(this);
        tPrincipal.getAgenarServico().getBtnCancelar().addActionListener(this);
        tPrincipal.getAgenda().getTabelaAgenda().addMouseListener(this);
        tPrincipal.getAgenda().getBtnRelatorio().addActionListener(this);

        tPrincipal.getAgenda().getCalenario().getDayChooser().addPropertyChangeListener("day", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (ControleLogin.getProfissional() == null) {
                    listarAgendaGeral();
                } else {
                    listaAgendaProfissional(ControleLogin.getProfissional());
                }
            }
        });
    }

    public void exibirAgenda(Long id) throws SQLException, JRException {

        java.util.Date data = tPrincipal.getAgenda().getCalenario().getDate();

        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/petshop", "postgres", "glenda");
        InputStream fonte = ControleRelatorio.class.getResourceAsStream("/br/com/pbd/Relatorio/AgendaProfissional.jasper");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("data", data);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fonte);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
        JasperViewer jrviewer = new JasperViewer(jasperPrint, false);
        jrviewer.setVisible(true);
        jrviewer.toFront();

    }
}
