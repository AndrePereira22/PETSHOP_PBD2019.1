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
import br.com.pbd.Modelo.Render;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andre-Coude
 */
public class ControleAnimal implements ActionListener {

    private TelaPrincipal tPrincipal;
    private List<Especie> especies;
    private List<Raca> racas;
    private List<Cliente> clientes;
    private List<Animal> animais;
    private Animal animal;
    private JButton btnExcluir, btnEditar;
    private Icon editar, excluir;
    private boolean salvar_editar;  /// true = salvar , false=editar

    public ControleAnimal(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;

        especies = new ArrayList<Especie>();
        racas = new ArrayList<Raca>();
        clientes = new ArrayList<Cliente>();
        animais = new ArrayList<Animal>();
        animal = new Animal();
        salvar_editar = true;

        editar = new ImageIcon(getClass().getResource("/br/com/pbd/resource/edit.png"));
        excluir = new ImageIcon(getClass().getResource("/br/com/pbd/resource/eraser.png"));

        btnEditar = new JButton(editar);
        btnEditar.setName("editar");
        btnEditar.setBorder(null);
        btnEditar.setContentAreaFilled(false);

        btnExcluir = new JButton(excluir);
        btnExcluir.setName("excluir");
        btnExcluir.setBorder(null);
        btnExcluir.setContentAreaFilled(false);

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

        tPrincipal.getcAnimal().getTabelaAnimais().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = tPrincipal.getcAnimal().getTabelaAnimais().getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / tPrincipal.getcAnimal().getTabelaAnimais().getRowHeight();

                if (row < tPrincipal.getProdutos().getTabelaItens().getRowCount() && row >= 0 && column < tPrincipal.getcAnimal().getTabelaAnimais().getColumnCount() && column >= 0) {
                    Object value = tPrincipal.getcAnimal().getTabelaAnimais().getValueAt(row, column);
                    if (value instanceof JButton) {
                        ((JButton) value).doClick();
                        JButton boton = (JButton) value;

                        if (boton.getName().equals("editar")) {
                            int ro = tPrincipal.getcAnimal().getTabelaAnimais().getSelectedRow();
                            animal = animais.get(ro);
                            preencherEspecie();
                            preencherClientes();
                            tPrincipal.getcAnimal().getPainelItens().setSelectedComponent(tPrincipal.getcAnimal().getPainelCadastro());
                            tPrincipal.getcAnimal().getPainelCadastro().setEnabled(true);
                            preencherDadosEdicaoAnimal(animal);
                            salvar_editar = false;
                        }
                        if (boton.getName().equals("excluir")) {
                            int ro = tPrincipal.getcAnimal().getTabelaAnimais().getSelectedRow();
                            animal = animais.get(ro);
                            new GenericDao<Animal>().remover(Animal.class, animal.getId());

                        }
                    }
                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tPrincipal.getRaca_especie().getBtnSairR()) {
            tPrincipal.getRaca_especie().setVisible(false);
            tPrincipal.getCadastros().setVisible(true);
        }
        if (e.getSource() == tPrincipal.getRaca_especie().getBtnSairE()) {
            tPrincipal.getRaca_especie().setVisible(false);
            tPrincipal.getCadastros().setVisible(true);
        }

        if (e.getSource() == tPrincipal.getcAnimal().getBtnSalvar()) {

            if (salvar_editar) {
                cadastrarAnimal();
            } else {
                editarAnimal(animal);
            }
            listarTabelaAnimais();
        }
        if (e.getSource() == tPrincipal.getcAnimal().getBtnCadastrarAnimal()) {
            preencherEspecie();
            preencherClientes();
            salvar_editar=true;
        }
        if (e.getSource() == tPrincipal.getcAnimal().getComboEspecie()) {

            if (tPrincipal.getcAnimal().isVisible()) {
                preencherRaca();
            }
        }
        if (e.getSource() == tPrincipal.getCadastros().getBtnAnimal1()) {

            listarTabelaAnimais();
        }
        if (e.getSource() == tPrincipal.getRaca_especie().getBtnSalvarEspecie()) {
            salvarEspecie();
            listarTabelaEspecie();
        }
        if (e.getSource() == tPrincipal.getRaca_especie().getBtnSavlarRaca()) {
            salvarRaca();
            tPrincipal.getRaca_especie().getPainelItens().setEnabled(true);
            listarTabelaRaca();
        }
        if (e.getSource() == tPrincipal.getRaca_especie().getBtnNovaRaca()) {
            preencherComboEspecie();
        }

        if (e.getSource() == tPrincipal.getCadastros().getBtnRaca()) {
            listarTabelaRaca();
        }
        if (e.getSource() == tPrincipal.getCadastros().getBtnEspecie()) {
            listarTabelaEspecie();
            listarTabelaRaca();

        }
        if (e.getSource() == tPrincipal.getCadastros().getBtnRaca()) {
            listarTabelaEspecie();
            listarTabelaRaca();

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
        } catch (NumberFormatException erro) {
        }
        animal.setPesokg(pesokg);

        int indiceRaca = tPrincipal.getcAnimal().getComboRaca().getSelectedIndex();
        int indiceCliente = tPrincipal.getcAnimal().getComboDono().getSelectedIndex();

        try {
            raca = racas.get(indiceRaca);
            cli = clientes.get(indiceCliente);
            animal.setCliente(cli);
            animal.setRaca(raca);

            new GenericDao<Animal>().salvar_ou_atualizar(animal);
            JOptionPane.showMessageDialog(null, "Animal cadastrado!");
            tPrincipal.getcAnimal().getPainelItens().setSelectedComponent(tPrincipal.getcAnimal().getPainelAnimail());
            tPrincipal.getcAnimal().getPainelCadastro().setEnabled(false);

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }

    }

    public void salvarEspecie() {

        Especie especie = new Especie();
        especie.setNome(tPrincipal.getRaca_especie().getTxtEspecie().getText());

        try {
            new GenericDao<Especie>().salvar_ou_atualizar(especie);
            JOptionPane.showMessageDialog(null, "Especie cadastrado!");
            tPrincipal.getRaca_especie().AtivarComponenteEspecie(false);
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

        Especie especie = especies.get(indice);

        raca.setEspecie(especie);

        try {
            new GenericDao<Raca>().salvar_ou_atualizar(raca);
            JOptionPane.showMessageDialog(null, "Raça cadastrada!");
            tPrincipal.getRaca_especie().AtivarComponenteRaca(false);
            preencherRaca();

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }

    }

    public void listarTabelaAnimais() {

        animais = new GenericDao<Animal>().getAll(Animal.class);

        if (!animais.isEmpty()) {

            tPrincipal.getcAnimal().getTabelaAnimais().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"NOME", "APELIDO", "DATA DE NASCIMENTO", "SEXO", "COR", "RAÇA", "DONO", "EDITAR", "EXCLUIR"};
                Object[][] dados = new Object[animais.size()][9];
                for (Animal a : animais) {
                    dados[i][0] = a.getNome();
                    dados[i][1] = a.getApelido();
                    dados[i][2] = a.getNascimento();
                    dados[i][3] = a.getSexo();
                    dados[i][4] = a.getCor();
                    dados[i][5] = a.getRaca().getNome();
                    dados[i][6] = a.getCliente().getNome();
                    dados[i][7] = btnEditar;
                    dados[i][8] = btnExcluir;

                    i++;
                }

                DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                // TableColumnModel columnModel = tPrincipal.getcAnimal().getTabelaAnimais().getColumnModel();
                tPrincipal.getcAnimal().getTabelaAnimais().setModel(dataModel);
            } catch (Exception ex) {

            }
        }
    }

    public void listarTabelaEspecie() {

        especies = new GenericDao<Especie>().getAll(Especie.class);

        if (!especies.isEmpty()) {

            tPrincipal.getRaca_especie().getTabelaEspecie().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"NOME", "EDITAR", "EXCLUIR"};
                Object[][] dados = new Object[especies.size()][3];
                for (Especie a : especies) {
                    dados[i][0] = a.getNome();
                    dados[i][1] = btnEditar;
                    dados[i][2] = btnExcluir;

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

    public void listarTabelaRaca() {

        racas = new GenericDao<Raca>().getAll(Raca.class);

        if (!racas.isEmpty()) {

            tPrincipal.getRaca_especie().getTabelaRacas().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"NOME", "DESCRIÇÃO", "ESPECIE", "EDITAR", "EXCLUIR"};
                Object[][] dados = new Object[racas.size()][5];
                for (Raca a : racas) {
                    dados[i][0] = a.getNome();
                    dados[i][1] = a.getDescricao();
                    dados[i][2] = a.getEspecie().getNome();
                    dados[i][3] = btnEditar;
                    dados[i][4] = btnExcluir;

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

        try {
            int indice = tPrincipal.getcAnimal().getComboEspecie().getSelectedIndex();

            Especie e = especies.get(indice);

            racas = new DaoRaca().usandoID(e);

            tPrincipal.getcAnimal().getComboRaca().removeAllItems();
            racas.forEach((ra) -> {
                tPrincipal.getcAnimal().getComboRaca().addItem(ra.getNome());
            });
        } catch (ArrayIndexOutOfBoundsException e) {
        }

    }

    public final void preencherClientes() {

        clientes = new GenericDao<Cliente>().getAll(Cliente.class);
        tPrincipal.getcAnimal().getComboDono().removeAllItems();
        clientes.forEach((c) -> {
            tPrincipal.getcAnimal().getComboDono().addItem(c.getNome());
        });
    }

    public final void preencherComboEspecie() {

        especies = new GenericDao<Especie>().getAll(Especie.class);
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
        } catch (NumberFormatException erro) {
        }
        animal.setPesokg(pesokg);

        int indiceRaca = tPrincipal.getcAnimal().getComboRaca().getSelectedIndex();
        int indiceCliente = tPrincipal.getcAnimal().getComboDono().getSelectedIndex();

        try {
            if (indiceRaca > -1) {
                raca = racas.get(indiceRaca);
                animal.setRaca(raca);
            }

            cli = clientes.get(indiceCliente);
            animal.setCliente(cli);

            new GenericDao<Animal>().salvar_ou_atualizar(animal);
            JOptionPane.showMessageDialog(null, "Edição finalizada!");
            tPrincipal.getcAnimal().getPainelItens().setSelectedComponent(tPrincipal.getcAnimal().getPainelAnimail());
            tPrincipal.getcAnimal().getPainelCadastro().setEnabled(false);
            salvar_editar = true;

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }

    }

    public void preencherDadosEdicaoAnimal(Animal animal) {

        tPrincipal.getcAnimal().getTxtaApelido().setText(animal.getApelido());
        tPrincipal.getcAnimal().getTxtNome().setText(animal.getNome());
        tPrincipal.getcAnimal().getTxtCor().setText(animal.getCor());
        tPrincipal.getcAnimal().getAreaObservacao().setText(animal.getObservacao());
        tPrincipal.getcAnimal().getAreaObservacao().setText(animal.getObservacao());
        tPrincipal.getcAnimal().getNascimento().setDate(animal.getNascimento());
        tPrincipal.getcAnimal().getTxtPeso().setText(animal.getPesokg() + "");

        for (int c = 0; c < tPrincipal.getcAnimal().getComboDono().getItemCount(); c++) {

            if (tPrincipal.getcAnimal().getComboDono().getItemAt(c).equals(animal.getCliente().getNome())) {
                tPrincipal.getcAnimal().getComboDono().setSelectedItem(tPrincipal.getcAnimal().getComboDono().getItemAt(c));
            }
        }

        for (int c = 0; c < tPrincipal.getcAnimal().getComboSexo().getItemCount(); c++) {

            if (tPrincipal.getcAnimal().getComboSexo().getItemAt(c).equals(animal.getSexo())) {
                tPrincipal.getcAnimal().getComboSexo().setSelectedItem(tPrincipal.getcAnimal().getComboSexo().getItemAt(c));
            }
        }
    }
}
