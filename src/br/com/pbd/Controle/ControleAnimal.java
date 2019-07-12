/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Modelo.Animal;
import br.com.pbd.Modelo.Cliente;
import br.com.pbd.Modelo.Especie;
import br.com.pbd.Modelo.AgendaAnimal;
import br.com.pbd.Modelo.Raca;
import br.com.pbd.Modelo.Render;
import br.com.pbd.Modelo.Vacina;
import br.com.pbd.fachada.Fachada;
import br.com.pbd.view.DiaMensagem;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andre-Coude
 */
public class ControleAnimal extends MouseAdapter implements ActionListener, KeyListener {

    private final Fachada fachada;
    private final TelaPrincipal tPrincipal;
    private List<Especie> especies;
    private Especie especie;
    private List<Raca> racas;
    private Raca raca;
    private List<Cliente> clientes;
    private List<Vacina> vacinas;
    private List<Animal> animais;
    private List<AgendaAnimal> agendas;
    private Animal animal;
    private int escolha;
    private final int SALVAR = 1, EDICAO = 2, EXCLUSAO = 3, AGENDA = 4;
    private final DiaMensagem mens;

    public ControleAnimal(TelaPrincipal tPrincipal, Fachada fachada) {
        this.tPrincipal = tPrincipal;
        this.fachada = fachada;
        this.mens = new DiaMensagem(tPrincipal, true);

        especies = new ArrayList<Especie>();
        racas = new ArrayList<Raca>();
        clientes = new ArrayList<Cliente>();
        animais = new ArrayList<Animal>();
        animal = new Animal();
        raca = new Raca();
        especie = new Especie();

        tPrincipal.getcAnimal().getComboEspecie().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preencherRaca();
            }
        });

        adicionarEventos();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tPrincipal.getcAnimal().getTabelaAnimais()) {
            int ro = retornaIndice(tPrincipal.getcAnimal().getTabelaAnimais(), e);

            animal = animais.get(ro);
            if (escolha == EDICAO) {
                preencherEspecie();
                preencherClientes();
                tPrincipal.getcAnimal().getPainelItens().setSelectedComponent(tPrincipal.getcAnimal().getPainelCadastro());
                tPrincipal.getcAnimal().getPainelCadastro().setEnabled(true);
                tPrincipal.getcAnimal().preencherDados(animal);
            } else if (escolha == EXCLUSAO) {
                if (fachada.remover(animal)) {
                    animais = fachada.getAllAnimal();
                    mens.getLblMens().setText("EXCLUSAO FINALIZADA!");
                    mens.setVisible(true);
                    listarTabelaAnimais(animais);
                } else {
                    mens.getLblMens().setText("EXCLUSÃO NAO PERMITIDA");
                    mens.setVisible(true);
                }
            } else if (escolha == AGENDA) {
                tPrincipal.getcAnimal().setVisible(false);
                tPrincipal.getAgendaAnimal().setVisible(true);

                agendas = fachada.busca(animal);
                listarTabelaAgenda(agendas);

            }

        }
        if (e.getSource() == tPrincipal.getRaca_especie().getTabelaRacas()) {
            int ro = retornaIndice(tPrincipal.getRaca_especie().getTabelaRacas(), e);
            raca = racas.get(ro);
            if (escolha == EDICAO) {
                tPrincipal.getRaca_especie().AtivarComponenteRaca(true);
                tPrincipal.getRaca_especie().preencherDadosEdicaoRaca(raca);
            } else if (escolha == EXCLUSAO) {

            }

        }
        if (e.getSource() == tPrincipal.getRaca_especie().getTabelaEspecie()) {
            int ro = retornaIndice(tPrincipal.getRaca_especie().getTabelaEspecie(), e);
            especie = especies.get(ro);
            if (escolha == EDICAO) {
                tPrincipal.getRaca_especie().AtivarComponenteEspecie(true);
                tPrincipal.getRaca_especie().preencherDadosEdicaoEspecie(especie);
            } else if (escolha == EXCLUSAO) {

            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tPrincipal.getAgendaAnimal().getBtnAdicionar()) {
            vacinas = fachada.getAllVacina();
            preencherVacinas(vacinas);

        }
        if (e.getSource() == tPrincipal.getaVacina().getBtnSalvar()) {
            salvarVacinacao();

        }

        if (e.getSource() == tPrincipal.getRaca_especie().getBtnSairR()) {
            tPrincipal.getRaca_especie().setVisible(false);
            tPrincipal.getCadastros().setVisible(true);
        }
        if (e.getSource() == tPrincipal.getRaca_especie().getBtnSairE()) {
            tPrincipal.getRaca_especie().setVisible(false);
            tPrincipal.getCadastros().setVisible(true);
        }

        if (e.getSource() == tPrincipal.getcAnimal().getBtnSalvar()) {

            if (escolha == SALVAR) {
                cadastrarAnimal();
            } else if (escolha == EDICAO) {
                editarAnimal(animal);
            }
            animais = fachada.getAllAnimal();
            listarTabelaAnimais(animais);
        }
        if (e.getSource() == tPrincipal.getcAnimal().getBtnCadastrarAnimal()) {

            tPrincipal.getcAnimal().limparComponentes();
            escolha = SALVAR;
        }

        if (e.getSource() == tPrincipal.getCadastros().getBtnAnimal1()) {
            preencherEspecie();
            preencherClientes();
            animais = fachada.getAllAnimal();
            listarTabelaAnimais(animais);
        }
        if (e.getSource() == tPrincipal.getRaca_especie().getBtnSalvarEspecie()) {
            if (escolha == SALVAR) {
                salvarEspecie();
            } else if (escolha == EDICAO) {
                editarEspecie(especie);
            }

            especies = fachada.getAllEspecie();
            listarTabelaEspecie(especies);
        }
        if (e.getSource() == tPrincipal.getRaca_especie().getBtnSavlarRaca()) {
            if (escolha == SALVAR) {
                salvarRaca();
            } else if (escolha == EDICAO) {
                editarRaca(raca);
            }
            tPrincipal.getRaca_especie().getPainelItens().setEnabled(true);
            racas = fachada.getAllRaca();

            listarTabelaRaca(racas);
        }
        if (e.getSource() == tPrincipal.getRaca_especie().getBtnNovaRaca()) {
            escolha = SALVAR;
            preencherComboEspecie();
        }
        if (e.getSource() == tPrincipal.getRaca_especie().getBtnNovaEspecie()) {
            escolha = SALVAR;

        }

        if (e.getSource() == tPrincipal.getCadastros().getBtnRaca()) {
            racas = fachada.getAllRaca();

            listarTabelaRaca(racas);
            preencherComboEspecie();
        }
        if (e.getSource() == tPrincipal.getCadastros().getBtnEspecie()) {
            racas = fachada.getAllRaca();

            especies = fachada.getAllEspecie();
            listarTabelaEspecie(especies);
            listarTabelaRaca(racas);
        }
        if (e.getSource() == tPrincipal.getCadastros().getBtnRaca()) {
            racas = fachada.getAllRaca();

            especies = fachada.getAllEspecie();
            listarTabelaEspecie(especies);
            listarTabelaRaca(racas);
        }
    }

    public void cadastrarAnimal() {

        animal = new Animal();
        Raca raca = null;
        Cliente cli = null;

        animal.setApelido(tPrincipal.getcAnimal().getTxtaApelido().getText());
        animal.setNome(tPrincipal.getcAnimal().getTxtNome().getText());
        animal.setCor(tPrincipal.getcAnimal().getTxtCor().getText());
        animal.setObservacao(tPrincipal.getcAnimal().getAreaObservacao().getText());
        animal.setSexo(tPrincipal.getcAnimal().getComboSexo().getSelectedItem().toString());
        animal.setObservacao(tPrincipal.getcAnimal().getAreaObservacao().getText());
        animal.setNascimento(ConverterData(tPrincipal.getcAnimal().getNascimento().getDate()));

        String peso = tPrincipal.getcAnimal().getTxtPeso().getText();
        Double pesokg = 0.0;
        try {
            pesokg = Double.parseDouble(peso);
        } catch (NumberFormatException roll) {

        }
        animal.setPesokg(pesokg);

        try {

            int indiceRaca = tPrincipal.getcAnimal().getComboRaca().getSelectedIndex();
            int indiceCliente = tPrincipal.getcAnimal().getComboDono().getSelectedIndex();

            raca = racas.get(indiceRaca);
            cli = clientes.get(indiceCliente);
            animal.setCliente(cli);
            animal.setRaca(raca);

            fachada.salvar(animal);
            JOptionPane.showMessageDialog(null, "Animal cadastrado!");
            tPrincipal.getcAnimal().getPainelItens().setSelectedComponent(tPrincipal.getcAnimal().getPainelAnimail());
            tPrincipal.getcAnimal().getPainelCadastro().setEnabled(false);

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());

        } catch (java.lang.ArrayIndexOutOfBoundsException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());

        }

    }

    public void salvarEspecie() {

        Especie especie = new Especie();
        especie.setNome(tPrincipal.getRaca_especie().getTxtEspecie().getText());

        try {
            fachada.salvarEspecie(especie);
            JOptionPane.showMessageDialog(null, "Especie cadastrado!");
            tPrincipal.getRaca_especie().AtivarComponenteEspecie(false);
            tPrincipal.getRaca_especie().limparComponentes();
            preencherEspecie();

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }

    }

    public void salvarRaca() {

        Raca raca = new Raca();
        raca.setNome(tPrincipal.getRaca_especie().getTxtRaca().getText());
        raca.setDescricao(tPrincipal.getRaca_especie().getTxtAreaRaca().getText());

        int indice = tPrincipal.getRaca_especie().getComboEspecie().getSelectedIndex();

        try {
            Especie especie = especies.get(indice);
            raca.setEspecie(especie);

            fachada.salvarRaca(raca);
            JOptionPane.showMessageDialog(null, "Raça cadastrada!");
            tPrincipal.getRaca_especie().AtivarComponenteRaca(false);
            tPrincipal.getRaca_especie().limparComponentes();
            preencherRaca();

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }

    }

    public void salvarVacinacao() {
        AgendaAnimal agenda = new AgendaAnimal();

        try {
            String dose = tPrincipal.getaVacina().getComboNumeroDose().getSelectedItem().toString();
            int indice = tPrincipal.getaVacina().getComboVacina().getSelectedIndex();
            int numeroDose = Integer.parseInt(dose);
            java.sql.Date data = ConverterData(tPrincipal.getaVacina().getData().getDate());

            Vacina vacina = vacinas.get(indice);

            agenda.setAnimal(animal);
            agenda.setVacina(vacina);
            agenda.setData(data);
            agenda.setNumero_dose(numeroDose);

            fachada.salvar(agenda);
            mens.setLblMens("AGENDADO COM SUCESSO!");
            mens.setVisible(true);
            tPrincipal.getaVacina().setVisible(false);
            tPrincipal.getAgendaAnimal().setVisible(true);
            agendas= fachada.getAll();
            listarTabelaAgenda(agendas);
            

        } catch (java.lang.IllegalStateException | javax.persistence.RollbackException | java.lang.NullPointerException n) {
            mens.setLblMens(tPrincipal.getCAMPOS());
            mens.setVisible(true);
        } catch (ArrayIndexOutOfBoundsException e) {
            mens.setLblMens("OCORREU UM ERRO NO SISTEMA");
            mens.setVisible(true);
        }

    }

    public void listarTabelaAnimais(List<Animal> lista) {

        if (!lista.isEmpty()) {

            tPrincipal.getcAnimal().getTabelaAnimais().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"NOME", "APELIDO", "DATA DE NASCIMENTO", "SEXO", "COR", "RAÇA", "DONO", "VACINAS", "EDITAR", "EXCLUIR"};
                Object[][] dados = new Object[lista.size()][10];
                for (Animal a : lista) {
                    dados[i][0] = a.getNome();
                    dados[i][1] = a.getApelido();
                    dados[i][2] = a.getNascimento();
                    dados[i][3] = a.getSexo();
                    dados[i][4] = a.getCor();
                    dados[i][5] = a.getRaca().getNome();
                    dados[i][6] = a.getCliente().getNome();
                    dados[i][7] = tPrincipal.getBtnAgendaAnimal();
                    dados[i][8] = tPrincipal.getBtnEditar();
                    dados[i][9] = tPrincipal.getBtnExcluir();

                    i++;
                }

                DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tPrincipal.getcAnimal().getTabelaAnimais().setModel(dataModel);
            } catch (Exception ex) {

            }
        }
    }

    public void listarTabelaEspecie(List<Especie> lista) {

        if (!lista.isEmpty()) {

            tPrincipal.getRaca_especie().getTabelaEspecie().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"NOME", "EDITAR", "EXCLUIR"};
                Object[][] dados = new Object[lista.size()][3];
                for (Especie a : lista) {
                    dados[i][0] = a.getNome();
                    dados[i][1] = tPrincipal.getBtnEditar();
                    dados[i][2] = tPrincipal.getBtnExcluir();

                    i++;
                }

                DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tPrincipal.getRaca_especie().getTabelaEspecie().setModel(dataModel);
            } catch (Exception ex) {

            }
        }
    }

    public void listarTabelaRaca(List<Raca> lista) {

        if (!lista.isEmpty()) {

            tPrincipal.getRaca_especie().getTabelaRacas().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"NOME", "ESPECIE", "EDITAR", "EXCLUIR"};
                Object[][] dados = new Object[lista.size()][4];
                for (Raca a : lista) {
                    dados[i][0] = a.getNome();
                    dados[i][1] = a.getEspecie().getNome();
                    dados[i][2] = tPrincipal.getBtnEditar();
                    dados[i][3] = tPrincipal.getBtnExcluir();

                    i++;
                }

                DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tPrincipal.getRaca_especie().getTabelaRacas().setModel(dataModel);
            } catch (Exception ex) {

            }
        }
    }

    public void listarTabelaAgenda(List<AgendaAnimal> lista) {

        if (!lista.isEmpty()) {

            tPrincipal.getAgendaAnimal().getTabelaAgenda().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"NOME", "VACINA", "CODIGO", "NUMEO DA DOSE", "DATA", "EDITAR", "EXCLUIR"};
                Object[][] dados = new Object[lista.size()][7];
                for (AgendaAnimal a : lista) {
                    dados[i][0] = a.getAnimal().getNome();
                    dados[i][1] = a.getVacina().getNome();
                    dados[i][2] = a.getVacina().getCodigo();
                    dados[i][3] = a.getNumero_dose() + "";
                    dados[i][4] = a.getData();
                    dados[i][5] = tPrincipal.getBtnEditar();
                    dados[i][6] = tPrincipal.getBtnExcluir();

                    i++;
                }

                DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tPrincipal.getAgendaAnimal().getTabelaAgenda().setModel(dataModel);
            } catch (Exception ex) {

            }
        }
    }

    public java.sql.Date ConverterData(java.util.Date date) throws NullPointerException {
        return new java.sql.Date(date.getTime());
    }

    public final void preencherEspecie() {
        especies = fachada.getAllEspecie();

        tPrincipal.getcAnimal().getComboEspecie().removeAllItems();
        especies.forEach((e) -> {
            tPrincipal.getcAnimal().getComboEspecie().addItem(e.getNome());
        });

    }

    public final void preencherRaca() {

        try {
            int indice = tPrincipal.getcAnimal().getComboEspecie().getSelectedIndex();

            Especie e = especies.get(indice);

            racas = fachada.buscaRaca(e);

            tPrincipal.getcAnimal().getComboRaca().removeAllItems();
            racas.forEach((ra) -> {
                tPrincipal.getcAnimal().getComboRaca().addItem(ra.getNome());
            });
        } catch (ArrayIndexOutOfBoundsException e) {
        }

    }

    public final void preencherClientes() {

        clientes = fachada.getAllCliente();
        tPrincipal.getcAnimal().getComboDono().removeAllItems();
        clientes.forEach((c) -> {
            tPrincipal.getcAnimal().getComboDono().addItem(c.getNome());
        });
    }

    public final void preencherComboEspecie() {

        especies = fachada.getAllEspecie();
        tPrincipal.getRaca_especie().getComboEspecie().removeAllItems();
        especies.forEach((c) -> {
            tPrincipal.getRaca_especie().getComboEspecie().addItem(c.getNome());
        });
    }

    public void editarAnimal(Animal animal) {

        Raca raca = null;
        Cliente cli = null;

        animal.setApelido(tPrincipal.getcAnimal().getTxtaApelido().getText());
        animal.setNome(tPrincipal.getcAnimal().getTxtNome().getText());
        animal.setCor(tPrincipal.getcAnimal().getTxtCor().getText());
        animal.setObservacao(tPrincipal.getcAnimal().getAreaObservacao().getText());
        animal.setSexo(tPrincipal.getcAnimal().getComboSexo().getSelectedItem().toString());
        animal.setObservacao(tPrincipal.getcAnimal().getAreaObservacao().getText());
        animal.setNascimento(ConverterData(tPrincipal.getcAnimal().getNascimento().getDate()));

        String peso = tPrincipal.getcAnimal().getTxtPeso().getText();
        Double pesokg = 0.0;
        try {
            pesokg = Double.parseDouble(peso);
        } catch (NumberFormatException roll) {

        }
        animal.setPesokg(pesokg);

        int indiceRaca = tPrincipal.getcAnimal().getComboRaca().getSelectedIndex();
        int indiceCliente = tPrincipal.getcAnimal().getComboDono().getSelectedIndex();

        try {
            if (indiceRaca > -1) {
                raca = racas.get(indiceRaca);
                animal.setRaca(raca);
            }
            if (indiceCliente > -1) {
                cli = clientes.get(indiceCliente);
                animal.setCliente(cli);
            }
            fachada.salvar(animal);
            JOptionPane.showMessageDialog(null, "Edição finalizada!");
            tPrincipal.getcAnimal().getPainelItens().setSelectedComponent(tPrincipal.getcAnimal().getPainelAnimail());
            tPrincipal.getcAnimal().getPainelCadastro().setEnabled(false);

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException | ArrayIndexOutOfBoundsException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }

    }

    public void editarRaca(Raca raca) {

        raca.setNome(tPrincipal.getRaca_especie().getTxtRaca().getText());
        raca.setDescricao(tPrincipal.getRaca_especie().getTxtAreaRaca().getText());

        int indice = tPrincipal.getRaca_especie().getComboEspecie().getSelectedIndex();

        try {
            Especie especie = especies.get(indice);
            raca.setEspecie(especie);

            fachada.salvarRaca(raca);
            JOptionPane.showMessageDialog(null, "Ediçao concluida!");
            tPrincipal.getRaca_especie().AtivarComponenteRaca(false);
            tPrincipal.getRaca_especie().limparComponentes();
            preencherRaca();

        } catch (java.lang.IllegalStateException | javax.persistence.RollbackException | ArrayIndexOutOfBoundsException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }

    }

    public void editarEspecie(Especie especie) {

        especie.setNome(tPrincipal.getRaca_especie().getTxtEspecie().getText());

        try {
            fachada.salvarEspecie(especie);
            JOptionPane.showMessageDialog(null, "Edicao concluida!");
            tPrincipal.getRaca_especie().AtivarComponenteEspecie(false);
            tPrincipal.getRaca_especie().limparComponentes();
            preencherEspecie();

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }

    }

    public void preencherVacinas(List<Vacina> vacinas) {
        tPrincipal.getaVacina().getComboVacina().removeAllItems();

        vacinas.forEach((p) -> {
            tPrincipal.getaVacina().getComboVacina().addItem(p.getNome());
        });
        tPrincipal.getaVacina().getTxtAnimal().setText(animal.getNome());
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

                ro = tabela.getSelectedRow();
                if (boton.getName().equals("editar")) {
                    escolha = EDICAO;
                } else if (boton.getName().equals("excluir")) {
                    escolha = EXCLUSAO;
                } else if (boton.getName().equals("agenda")) {
                    escolha = AGENDA;
                } else {
                    escolha = 0;
                }
            }
        }
        return ro;
    }

    // eventos de teclas
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getSource() == tPrincipal.getcAnimal().getTxtPesquisa()) {
            String nome = tPrincipal.getcAnimal().getTxtPesquisa().getText();
            animais = fachada.buscaAnimal(nome);
            listarTabelaAnimais(animais);

        }
        if (e.getSource() == tPrincipal.getRaca_especie().getTxtPesquisaEspecie()) {
            String nome = tPrincipal.getRaca_especie().getTxtPesquisaEspecie().getText();
            especies = fachada.buscaEspecie(nome);
            listarTabelaEspecie(especies);
        }
        if (e.getSource() == tPrincipal.getRaca_especie().getTxtPesquizaRaca()) {
            String nome = tPrincipal.getRaca_especie().getTxtPesquizaRaca().getText();
            racas = fachada.buscaRaca(nome);
            listarTabelaRaca(racas);
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void adicionarEventos() {

        tPrincipal.getcAnimal().getBtnSalvar().addActionListener(this);
        tPrincipal.getcAnimal().getComboEspecie().addActionListener(this);
        tPrincipal.getcAnimal().getBtnCadastrarAnimal().addActionListener(this);
        tPrincipal.getCadastros().getBtnAnimal1().addActionListener(this);
        tPrincipal.getRaca_especie().getBtnSavlarRaca().addActionListener(this);
        tPrincipal.getRaca_especie().getBtnSalvarEspecie().addActionListener(this);
        tPrincipal.getCadastros().getBtnRaca().addActionListener(this);
        tPrincipal.getCadastros().getBtnEspecie().addActionListener(this);
        tPrincipal.getRaca_especie().getBtnNovaRaca().addActionListener(this);
        tPrincipal.getRaca_especie().getBtnSairE().addActionListener(this);
        tPrincipal.getRaca_especie().getBtnSairR().addActionListener(this);
        tPrincipal.getRaca_especie().getBtnNovaEspecie().addActionListener(this);
        tPrincipal.getRaca_especie().getTabelaRacas().addMouseListener(this);
        tPrincipal.getcAnimal().getTabelaAnimais().addMouseListener(this);
        tPrincipal.getcAnimal().getTxtPesquisa().addKeyListener(this);
        tPrincipal.getRaca_especie().getTabelaEspecie().addMouseListener(this);
        tPrincipal.getRaca_especie().getTxtPesquizaRaca().addKeyListener(this);
        tPrincipal.getRaca_especie().getTxtPesquisaEspecie().addKeyListener(this);
        tPrincipal.getAgendaAnimal().getBtnAdicionar().addActionListener(this);
        tPrincipal.getaVacina().getBtnSalvar().addActionListener(this);

    }
}
