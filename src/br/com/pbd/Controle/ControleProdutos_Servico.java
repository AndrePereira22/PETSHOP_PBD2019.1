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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andre-Coude
 */
public class ControleProdutos_Servico implements ActionListener {

    TelaPrincipal tPrincipal;
    List<GrupoProduto> gProdutos;
    List<Fornecedor> fornecedores;
    List<Produto> produtos;
    List<Servico> servicos;
    private JButton btnExcluir, btnEditar;
    private Icon editar, excluir;

    public ControleProdutos_Servico(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;
        tPrincipal.getServico_Produto().getBtnNovoProduto().addActionListener(this);
        tPrincipal.getcProdutos().getBtnSalvar1().addActionListener(this);
        tPrincipal.getcServicos().getBtnSalvar1().addActionListener(this);
        tPrincipal.getcProdutos().getBtnNovoGrupo().addActionListener(this);
        tPrincipal.getcProdutos().getSalvarGrupo().addActionListener(this);
        tPrincipal.getBtnProdutos_serv().addActionListener(this);

        gProdutos = new ArrayList<GrupoProduto>();
        fornecedores = new ArrayList<Fornecedor>();
        produtos = new ArrayList<Produto>();

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

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tPrincipal.getServico_Produto().getBtnNovoProduto()) {
            preencherFornecedores();
            preencherGrupo();
        }
        if (e.getSource() == tPrincipal.getcProdutos().getBtnSalvar1()) {
            salvarProduto();
        }
        if (e.getSource() == tPrincipal.getcServicos().getBtnSalvar1()) {
            salvarServico();

        }
        if (e.getSource() == tPrincipal.getBtnProdutos_serv()) {
            listarProdutos();
            listarServicos();
        }
        if (e.getSource() == tPrincipal.getcProdutos().getBtnNovoGrupo()) {

            tPrincipal.getcProdutos().getComboGrupoProduto().removeAllItems();
            tPrincipal.getcProdutos().getPainelGrupoProduto().setVisible(true);
        }
        if (e.getSource() == tPrincipal.getcProdutos().getSalvarGrupo()) {

            salvarGrupoProduto();
            preencherGrupo();

            tPrincipal.getcProdutos().getPainelGrupoProduto().setVisible(false);
        }

    }

    private void salvarProduto() {
        Produto produto = new Produto();
        produto.setDescricao(tPrincipal.getcProdutos().getTxtDescricao().getText());
        produto.setFabricante(tPrincipal.getcProdutos().getTxtaFabricante().getText());

        int intdiceF = tPrincipal.getcProdutos().getComboFornecedor().getSelectedIndex();
        int intdiceG = tPrincipal.getcProdutos().getComboGrupoProduto().getSelectedIndex();
        Fornecedor forn = fornecedores.get(intdiceF);
        GrupoProduto grupo = gProdutos.get(intdiceG);

        produto.setFornecedor(forn);
        produto.setGproduto(grupo);

        String vCompra = tPrincipal.getcProdutos().getTxtValorCompra().getText();
        String vVenda = tPrincipal.getcProdutos().getTxtValorVenda().getText();

        Double valorCompra = 0.0, valorVenda = 0.0;
        try {
            valorCompra = Double.parseDouble(vCompra);
            valorVenda = Double.parseDouble(vVenda);
        } catch (NumberFormatException erro) {

        }
        produto.setValorvenda(valorVenda);
        produto.setValorcompra(valorCompra);

        try {
            new GenericDao<Produto>().salvar_ou_atualizar(produto);

            JOptionPane.showMessageDialog(null, "Produto cadastrado!");
            tPrincipal.getcProdutos().setVisible(false);
            tPrincipal.getServico_Produto().setVisible(true);
            listarProdutos();

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

    private void salvarServico() {

        Servico servico = new Servico();
        servico.setDescricao(tPrincipal.getcServicos().getTxtDescricao().getText());

        String valor = tPrincipal.getcServicos().getTxtaValor().getText();

        Double valorServico = 0.0;
        try {
            valorServico = Double.parseDouble(valor);
        } catch (NumberFormatException erro) {
        }

        servico.setValor(valorServico);
        servico.setDuracao(ConverterTime(tPrincipal.getcServicos().getComboDuracao().getSelectedItem().toString()));

        try {
            new GenericDao<Servico>().salvar_ou_atualizar(servico);

            JOptionPane.showMessageDialog(null, "Serviço cadastrado!");
            tPrincipal.getcServicos().setVisible(false);
            tPrincipal.getServico_Produto().setVisible(true);
            listarServicos();

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    private void salvarGrupoProduto() {
        GrupoProduto grupo = new GrupoProduto();
        grupo.setDescricao(tPrincipal.getcProdutos().getTxtDescricaoGrupo().getText());

        new GenericDao<GrupoProduto>().salvar_ou_atualizar(grupo);

    }

    private void preencherFornecedores() {
        fornecedores = new GenericDao<Fornecedor>().getAll(Fornecedor.class);
        tPrincipal.getAgenarServico().getComboServico().removeAllItems();
        fornecedores.forEach((c) -> {
            tPrincipal.getcProdutos().getComboFornecedor().addItem(c.getNomefantasia());
        });

    }

    private void preencherGrupo() {
        gProdutos = new GenericDao<GrupoProduto>().getAll(GrupoProduto.class);
        tPrincipal.getAgenarServico().getComboServico().removeAllItems();
        gProdutos.forEach((c) -> {
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

}
