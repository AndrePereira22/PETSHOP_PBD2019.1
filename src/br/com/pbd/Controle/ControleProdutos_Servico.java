/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Fornecedor;
import br.com.pbd.Modelo.GrupoProduto;
import br.com.pbd.Modelo.Produto;
import br.com.pbd.Modelo.Render;
import br.com.pbd.Modelo.Servico;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andre-Coude
 */
public class ControleProdutos_Servico extends MouseAdapter implements ActionListener {

    TelaPrincipal tPrincipal;
    List<GrupoProduto> grupos;
    private GrupoProduto grupo;
    List<Fornecedor> fornecedores;
    List<Produto> produtos;
    private Produto produto;
    List<Servico> servicos;
    private Servico servico;
    private JButton btnExcluir, btnEditar;
    private Icon editar, excluir;
    private Boolean salvar_editar;

    public ControleProdutos_Servico(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;

        tPrincipal.getServico_Produto().getTabelaServicos().addMouseListener(this);
        tPrincipal.getServico_Produto().getTabelaProdutos().addMouseListener(this);
        tPrincipal.getcGrupo().getTabelaGrupos().addMouseListener(this);
        tPrincipal.getServico_Produto().getBtnNovoProduto().addActionListener(this);
        tPrincipal.getServico_Produto().getBtnNovoServico().addActionListener(this);
        tPrincipal.getcProdutos().getBtnSalvarProduto().addActionListener(this);
        tPrincipal.getcServicos().getBtnSalvarServico().addActionListener(this);
        tPrincipal.getBtnProdutos_serv().addActionListener(this);
        tPrincipal.getCadastros().getBtnGrupo().addActionListener(this);
        tPrincipal.getcGrupo().getBtnSalvarNovoGrupo().addActionListener(this);
        tPrincipal.getcGrupo().getBtnNovaGrupo().addActionListener(this);

        grupos = new ArrayList<GrupoProduto>();
        fornecedores = new ArrayList<Fornecedor>();
        produtos = new ArrayList<Produto>();
        produto = new Produto();
        grupo = new GrupoProduto();
        servico = new Servico();
        salvar_editar = true;

        editar = new ImageIcon(getClass().getResource("/br/com/pbd/resource/edit.png"));
        excluir = new ImageIcon(getClass().getResource("/br/com/pbd/resource/lixo.png"));

        btnEditar = new JButton(editar);
        btnEditar.setName("editar");
        btnEditar.setBorder(null);
        btnEditar.setContentAreaFilled(false);

        btnExcluir = new JButton(excluir);
        btnExcluir.setName("excluir");
        btnExcluir.setBorder(null);
        btnExcluir.setContentAreaFilled(false);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == tPrincipal.getServico_Produto().getTabelaProdutos()) {

            int ro = retornaIndice(tPrincipal.getServico_Produto().getTabelaProdutos(), e);
            tPrincipal.getServico_Produto().setVisible(false);
            tPrincipal.getcProdutos().setVisible(true);
            produto = produtos.get(ro);
            preencherDadosEdicaoProduto(produto);
        }
        if (e.getSource() == tPrincipal.getServico_Produto().getTabelaServicos()) {

            int ro = retornaIndice(tPrincipal.getServico_Produto().getTabelaServicos(), e);
            tPrincipal.getServico_Produto().setVisible(false);
            tPrincipal.getcServicos().setVisible(true);
            servico = servicos.get(ro);
            preencherDadosEdicaoServico(servico);
        }
        if (e.getSource() == tPrincipal.getcGrupo().getTabelaGrupos()) {

            int ro = retornaIndice(tPrincipal.getcGrupo().getTabelaGrupos(), e);
            tPrincipal.getcGrupo().ativarComponentes(true);
            grupo = grupos.get(ro);
            preencherDadosEdicaoGrupo(grupo);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tPrincipal.getcGrupo().getBtnNovaGrupo()) {
            salvar_editar = true;
        }
        if (e.getSource() == tPrincipal.getCadastros().getBtnGrupo()) {
            listarGrupos();
        }
        if (e.getSource() == tPrincipal.getcGrupo().getBtnSalvarNovoGrupo()) {
            if (salvar_editar) {
                salvarGrupoProduto();
            } else {
                editarGrupoProduto(grupo);
            }

        }

        if (e.getSource() == tPrincipal.getServico_Produto().getBtnNovoProduto()) {
            preencherFornecedores();
            preencherGrupo();
        }
        if (e.getSource() == tPrincipal.getcProdutos().getBtnSalvarProduto()) {
            if (salvar_editar) {
                salvarProduto();
            } else {
                editarProduto(produto);
            }
        }
        if (e.getSource() == tPrincipal.getcServicos().getBtnSalvarServico()) {
            if (salvar_editar) {
                salvarServico();
            } else {
                editarServico(servico);
            }

        }
        if (e.getSource() == tPrincipal.getBtnProdutos_serv()) {
            listarProdutos();
            listarServicos();
            preencherFornecedores();
            preencherGrupo();
        }

    }

    private void salvarProduto() {

        Produto produto = new Produto();
        produto.setDescricao(tPrincipal.getcProdutos().getTxtDescricao().getText());
        produto.setFabricante(tPrincipal.getcProdutos().getTxtaFabricante().getText());

        int intdiceF = tPrincipal.getcProdutos().getComboFornecedor().getSelectedIndex();
        int intdiceG = tPrincipal.getcProdutos().getComboGrupoProduto().getSelectedIndex();

        Fornecedor forn = null;
        GrupoProduto grupo = null;

        String vCompra = tPrincipal.getcProdutos().getTxtValorCompra().getText();
        String vVenda = tPrincipal.getcProdutos().getTxtValorVenda().getText();

        Double valorCompra = 0.0, valorVenda = 0.0;

        try {
            forn = fornecedores.get(intdiceF);
            grupo = grupos.get(intdiceG);
            produto.setFornecedor(forn);
            produto.setGproduto(grupo);

            valorCompra = Double.parseDouble(vCompra);
            valorVenda = Double.parseDouble(vVenda);
            produto.setValorvenda(valorVenda);
            produto.setValorcompra(valorCompra);

            new GenericDao<Produto>().salvar_ou_atualizar(produto);

            JOptionPane.showMessageDialog(null, "Produto cadastrado!");
            tPrincipal.getcProdutos().setVisible(false);
            tPrincipal.getServico_Produto().setVisible(true);
            listarProdutos();
            salvar_editar = true;
        } catch (java.lang.IllegalStateException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        } catch (NumberFormatException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        } catch (ArrayIndexOutOfBoundsException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }

    }

    private void salvarServico() {

        Servico servico = new Servico();
        servico.setDescricao(tPrincipal.getcServicos().getTxtDescricao().getText());

        String valor = tPrincipal.getcServicos().getTxtaValor().getText();

        Double valorServico = 0.0;

        try {
            valorServico = Double.parseDouble(valor);
            servico.setValor(valorServico);
            servico.setDuracao(ConverterTime(tPrincipal.getcServicos().getComboDuracao().getSelectedItem().toString()));

            new GenericDao<Servico>().salvar_ou_atualizar(servico);

            JOptionPane.showMessageDialog(null, "Serviço cadastrado!");
            tPrincipal.getcServicos().setVisible(false);
            tPrincipal.getServico_Produto().setVisible(true);
            listarServicos();
            salvar_editar = true;

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        } catch (NumberFormatException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    private void salvarGrupoProduto() {
        GrupoProduto grupo = new GrupoProduto();
        grupo.setDescricao(tPrincipal.getcGrupo().getTxtDescricao().getText());

        try {
            new GenericDao<GrupoProduto>().salvar_ou_atualizar(grupo);
            JOptionPane.showMessageDialog(null, "Grupo cadastrado!");
            tPrincipal.getcGrupo().ativarComponentes(false);
            tPrincipal.getcGrupo().limparComponentes();
            listarGrupos();

            salvar_editar = true;

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }

    }

    public void listarProdutos() {

        produtos = new GenericDao<Produto>().getAll(Produto.class);
        if (!produtos.isEmpty()) {
            tPrincipal.getServico_Produto().getTabelaProdutos().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"Nome", "FABRICANTE", "VALOR DE COMPRA", "VALOR DE VENDA", "FORNECEDOR", "GRUPO", "EDITAR", "EXCLUIR"};
                Object[][] dados = new Object[produtos.size()][8];
                for (Produto a : produtos) {
                    dados[i][0] = a.getDescricao();
                    dados[i][1] = a.getFabricante();
                    dados[i][2] = a.getValorcompra();
                    dados[i][3] = a.getValorvenda();
                    dados[i][4] = a.getFornecedor().getNomefantasia();
                    dados[i][5] = a.getGproduto().getDescricao();
                    dados[i][6] = btnEditar;
                    dados[i][7] = btnExcluir;

                    i++;
                }

                DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tPrincipal.getServico_Produto().getTabelaProdutos().setModel(dataModel);
            } catch (Exception ex) {

            }

        }
    }

    public void listarServicos() {

        servicos = new GenericDao<Servico>().getAll(Servico.class);
        if (!servicos.isEmpty()) {
            tPrincipal.getServico_Produto().getTabelaServicos().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"DESCRIÇÃO", "DURAÇAO MEDIA", "VALOR", "EDITAR", "EXCLUIR"};
                Object[][] dados = new Object[servicos.size()][5];
                for (Servico a : servicos) {
                    dados[i][0] = a.getDescricao();
                    dados[i][1] = a.getDuracao();
                    dados[i][2] = a.getValor();
                    dados[i][3] = btnEditar;
                    dados[i][4] = btnExcluir;

                    i++;
                }

                DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tPrincipal.getServico_Produto().getTabelaServicos().setModel(dataModel);
            } catch (Exception ex) {

            }

        }
    }

    public void listarGrupos() {

        grupos = new GenericDao<GrupoProduto>().getAll(GrupoProduto.class);
        if (!grupos.isEmpty()) {
            tPrincipal.getcGrupo().getTabelaGrupos().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"DESCRIÇÃO", "EDITAR", "EXCLUIR"};
                Object[][] dados = new Object[grupos.size()][3];
                for (GrupoProduto a : grupos) {
                    dados[i][0] = a.getDescricao();
                    dados[i][1] = btnEditar;
                    dados[i][2] = btnExcluir;

                    i++;
                }

                DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tPrincipal.getcGrupo().getTabelaGrupos().setModel(dataModel);
            } catch (Exception ex) {

            }

        }
    }

    private void preencherFornecedores() {
        fornecedores = new GenericDao<Fornecedor>().getAll(Fornecedor.class);
        tPrincipal.getAgenarServico().getComboServico().removeAllItems();
        fornecedores.forEach((c) -> {
            tPrincipal.getcProdutos().getComboFornecedor().addItem(c.getNomefantasia());
        });

    }

    private void preencherGrupo() {
        grupos = new GenericDao<GrupoProduto>().getAll(GrupoProduto.class);
        tPrincipal.getAgenarServico().getComboServico().removeAllItems();
        grupos.forEach((c) -> {
            tPrincipal.getcProdutos().getComboGrupoProduto().addItem(c.getDescricao());
        });

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

    private void editarServico(Servico servico) {

        servico.setDescricao(tPrincipal.getcServicos().getTxtDescricao().getText());

        String valor = tPrincipal.getcServicos().getTxtaValor().getText();

        Double valorServico = 0.0;

        servico.setDuracao(ConverterTime(tPrincipal.getcServicos().getComboDuracao().getSelectedItem().toString()));

        try {
            valorServico = Double.parseDouble(valor);
            servico.setValor(valorServico);

            new GenericDao<Servico>().salvar_ou_atualizar(servico);

            JOptionPane.showMessageDialog(null, "Edicao concluida!");
            tPrincipal.getcServicos().setVisible(false);
            tPrincipal.getServico_Produto().setVisible(true);
            listarServicos();
            salvar_editar = true;

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        } catch (NumberFormatException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    private void editarProduto(Produto produto) {

        produto.setDescricao(tPrincipal.getcProdutos().getTxtDescricao().getText());
        produto.setFabricante(tPrincipal.getcProdutos().getTxtaFabricante().getText());

        Fornecedor forn = null;
        GrupoProduto grupo = null;
        try {

        } catch (ArrayIndexOutOfBoundsException erro) {
            JOptionPane.showMessageDialog(null, "ESCOLHA UM GRUPO!");
        }

        String vCompra = tPrincipal.getcProdutos().getTxtValorCompra().getText();
        String vVenda = tPrincipal.getcProdutos().getTxtValorVenda().getText();

        Double valorCompra = 0.0, valorVenda = 0.0;

        try {
            int intdiceF = tPrincipal.getcProdutos().getComboFornecedor().getSelectedIndex();
            int intdiceG = tPrincipal.getcProdutos().getComboGrupoProduto().getSelectedIndex();
            forn = fornecedores.get(intdiceF);
            grupo = grupos.get(intdiceG);

            valorCompra = Double.parseDouble(vCompra);
            valorVenda = Double.parseDouble(vVenda);

            produto.setValorvenda(valorVenda);
            produto.setValorcompra(valorCompra);
            produto.setFornecedor(forn);
            produto.setGproduto(grupo);

            new GenericDao<Produto>().salvar_ou_atualizar(produto);

            JOptionPane.showMessageDialog(null, "Edicao concluida!");
            tPrincipal.getcProdutos().setVisible(false);
            tPrincipal.getServico_Produto().setVisible(true);
            listarProdutos();
            salvar_editar = true;

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        } catch (NumberFormatException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    private void editarGrupoProduto(GrupoProduto grupo) {

        grupo.setDescricao(tPrincipal.getcGrupo().getTxtDescricao().getText());

        try {
            new GenericDao<GrupoProduto>().salvar_ou_atualizar(grupo);
            JOptionPane.showMessageDialog(null, "Edição concluida!");
            tPrincipal.getcGrupo().ativarComponentes(false);
            tPrincipal.getcGrupo().limparComponentes();
            listarGrupos();

            salvar_editar = true;

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }

    }

    public void preencherDadosEdicaoProduto(Produto produto) {

        tPrincipal.getcProdutos().getTxtDescricao().setText(produto.getDescricao());
        tPrincipal.getcProdutos().getTxtaFabricante().setText(produto.getFabricante());
        tPrincipal.getcProdutos().getTxtValorCompra().setText(produto.getValorcompra() + "");
        tPrincipal.getcProdutos().getTxtValorVenda().setText(produto.getValorvenda() + "");

        for (int c = 0; c < tPrincipal.getcProdutos().getComboFornecedor().getItemCount(); c++) {

            if (tPrincipal.getcProdutos().getComboFornecedor().getItemAt(c).equals(produto.getFornecedor().getNomefantasia())) {
                tPrincipal.getcProdutos().getComboFornecedor().setSelectedItem(tPrincipal.getcProdutos().getComboFornecedor().getItemAt(c));
            }
        }
        for (int c = 0; c < tPrincipal.getcProdutos().getComboGrupoProduto().getItemCount(); c++) {

            if (tPrincipal.getcProdutos().getComboGrupoProduto().getItemAt(c).equals(produto.getGproduto().getDescricao())) {
                tPrincipal.getcProdutos().getComboGrupoProduto().setSelectedItem(tPrincipal.getcProdutos().getComboGrupoProduto().getItemAt(c));
            }
        }

    }

    public void preencherDadosEdicaoServico(Servico servico) {
        tPrincipal.getcServicos().getTxtDescricao().setText(servico.getDescricao());

        tPrincipal.getcServicos().getTxtaValor().setText(servico.getValor() + "");

        for (int c = 0; c < tPrincipal.getcServicos().getComboDuracao().getItemCount(); c++) {

            if (tPrincipal.getcServicos().getComboDuracao().getItemAt(c).equals(servico.getDuracao())) {
                tPrincipal.getcServicos().getComboDuracao().setSelectedItem(tPrincipal.getcServicos().getComboDuracao().getItemAt(c));
            }
        }
    }

    public void preencherDadosEdicaoGrupo(GrupoProduto grupo) {
        tPrincipal.getcGrupo().getTxtDescricao().setText(grupo.getDescricao());

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
                    salvar_editar = false;

                }
            }
        }
        return ro;
    }
}
