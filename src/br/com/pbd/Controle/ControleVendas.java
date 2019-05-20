/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.DaoProduto;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.ItemVenda;
import br.com.pbd.Modelo.Pagamento;
import br.com.pbd.Modelo.Parcela;
import br.com.pbd.Modelo.Produto;
import br.com.pbd.Modelo.Render;
import br.com.pbd.Modelo.Venda;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andre-Coude
 */
public class ControleVendas implements ActionListener {

    private final TelaPrincipal tPrincipal;
    private Venda venda;
    private Pagamento pagamento;
    private List<ItemVenda> itens;
    private List<Produto> produtos, produtosAdicionados;
    private final List<Parcela> parcelas;
    private int totalItens;
    private Double ValorTotal, ValorFinal;
    private Produto produto;
    private final JButton btnExcluir, btnAdicionar;
    private final Icon adicionar, excluir;
    private final Calendar calendario;

    public ControleVendas(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;
        totalItens = 0;
        ValorTotal = 0.0;
        calendario = new GregorianCalendar();
        parcelas = new ArrayList<Parcela>();

        adicionar = new ImageIcon(getClass().getResource("/br/com/pbd/resource/edit.png"));
        excluir = new ImageIcon(getClass().getResource("/br/com/pbd/resource/remove1.png"));

        btnAdicionar = new JButton("adicionar");
        btnAdicionar.setName("adicionar");
        btnAdicionar.setBorder(null);
        btnAdicionar.setContentAreaFilled(false);

        btnExcluir = new JButton(excluir);
        btnExcluir.setName("excluir");
        btnExcluir.setBorder(null);
        btnExcluir.setContentAreaFilled(false);

        tPrincipal.getBtnVendas().addActionListener(this);
        tPrincipal.getVendas().getBtnProdutos().addActionListener(this);
        tPrincipal.getVendas().getBtnFinalizarVenda().addActionListener(this);
        tPrincipal.getQuantidade().getBtnConfirmar().addActionListener(this);
        tPrincipal.getPagamento().getBtnFinalizar().addActionListener(this);
        tPrincipal.getProdutos().getTxtPesquisarProdutos().addActionListener(this);

        tPrincipal.getProdutos().getTabelaItens().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = tPrincipal.getProdutos().getTabelaItens().getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / tPrincipal.getProdutos().getTabelaItens().getRowHeight();

                if (row < tPrincipal.getProdutos().getTabelaItens().getRowCount() && row >= 0 && column < tPrincipal.getProdutos().getTabelaItens().getColumnCount() && column >= 0) {
                    Object value = tPrincipal.getProdutos().getTabelaItens().getValueAt(row, column);
                    if (value instanceof JButton) {
                        ((JButton) value).doClick();
                        JButton boton = (JButton) value;

                        if (boton.getName().equals("adicionar")) {
                            int ro = tPrincipal.getProdutos().getTabelaItens().getSelectedRow();
                            tPrincipal.getQuantidade().setVisible(true);
                            produto = produtos.get(ro);

                        }
                    }
                }
            }

        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

//        if (e.getSource() == tPrincipal.getProdutos().getTxtPesquisarProdutos()) {
//           produtos = new GenericDao<Produto>().getAll(Produto.class);
//           listarProdutos(produtos);
//        }
//        
    
        if (e.getSource() == tPrincipal.getBtnVendas()) {
            venda = new Venda();
            itens = new ArrayList<ItemVenda>();
            produtos = new ArrayList<Produto>();
            produtosAdicionados = new ArrayList<Produto>();
            totalItens = 0;
            produto = new Produto();
        }
        if (e.getSource() == tPrincipal.getVendas().getBtnProdutos()) {
            tPrincipal.getProdutos().setVisible(true);
            ValorTotal = 0.0;
            totalItens = 0;

            if (tPrincipal.getVendas().getTxtPesquisarProdutos().getText().equals("")) {
                produtos = new GenericDao<Produto>().getAll(Produto.class);

            } else {
                produtos = new DaoProduto().listarProduto(tPrincipal.getVendas().getTxtPesquisarProdutos().getText());
            }
            listarProdutos(produtos);
        }
        if (e.getSource() == tPrincipal.getVendas().getBtnFinalizarVenda()) {

            if (!tPrincipal.getVendas().getTxtDesconto().getText().equals("")) {
                ValorFinal = ValorTotal - Double.parseDouble(tPrincipal.getVendas().getTxtDesconto().getText());
            } else {
                ValorFinal = ValorTotal;
            }
            tPrincipal.getPagamento().getTxtTotalPagar().setText(ValorFinal + "");

        }
        if (e.getSource() == tPrincipal.getQuantidade().getBtnConfirmar()) {

            adicionarItemVenda();
            listarProdutosAdicionados(itens);
            tPrincipal.getVendas().getTxtTotalItens().setText(totalItens + "");
            tPrincipal.getVendas().getTxtValorTotal().setText(ValorTotal + "");
        }
        if (e.getSource() == tPrincipal.getPagamento().getBtnFinalizar()) {
            escolherPagamento();
            venda.setData(ConverterData(new Date()));
            venda.setHora(new Time(Integer.MIN_VALUE));
            venda.setValortotal(ValorFinal);
            venda.setPagamento(pagamento);
            pagamento.setParcelas(parcelas);
            venda.setFuncionario(ControleLogin.getFuncionario());
            venda.setItens(itens);
            new GenericDao<Venda>().salvar_ou_atualizar(venda);

        }
    }

    public void escolherPagamento() {

        if (tPrincipal.getPagamento().getRadioAvista().isSelected()) {

            pagamento = new Pagamento();
            pagamento.setNumeroparcelas(0);
            pagamento.setStatus(true);
            pagamento.setValortotal(ValorTotal);
            pagamento.setData(ConverterData(new Date()));
            pagamento.setForma_pagamento(tPrincipal.getPagamento().getComboFormaPagamento().getSelectedItem().toString());

        } else {
            pagamento = new Pagamento();
            int numero = Integer.parseInt(tPrincipal.getPagamento().getTxtParcelas().getText());
            pagamento.setNumeroparcelas(numero);
            pagamento.setStatus(true);
            pagamento.setValortotal(ValorTotal);
            pagamento.setData(ConverterData(new Date()));
            pagamento.setForma_pagamento(tPrincipal.getPagamento().getComboFormaPagamento().getSelectedItem().toString());
            Date data = new Date();
            for (int j = 1; j <= numero; j++) {
                Parcela parcela = new Parcela();

                parcela.setNumero(j);

                parcela.setStatus(Boolean.FALSE);

                parcela.setValor(ValorTotal / numero);

                calendario.setTime(data);
                calendario.set(Calendar.MONTH, calendario.get(Calendar.MONTH) + j);
                calendario.set(Calendar.YEAR, calendario.get(Calendar.YEAR));

                parcela.setDatavencimento(ConverterData(calendario.getTime()));

                parcelas.add(parcela);
                parcela.setPagamento(pagamento);

            }

        }

    }

    public void adicionarItemVenda() {

        produtosAdicionados.add(produto);
        ItemVenda item = new ItemVenda();
        int numero = 0;
        try {
            numero = Integer.parseInt(tPrincipal.getQuantidade().getTxtQuantidade().getText());
            item.setQuantidade(numero);

        } catch (NumberFormatException erro) {
        }

        item.setProduto(produto);
        item.setVenda(venda);

        itens.add(item);
    }

    private void listarProdutos(List<Produto> produtos) {
        if (!produtos.isEmpty()) {
            tPrincipal.getProdutos().getTabelaItens().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"Nome", "FABRICANTE", "VALOR DE VENDA", "ADICIONAR"};
                Object[][] dados = new Object[produtos.size()][4];
                for (Produto a : produtos) {
                    dados[i][0] = a.getDescricao();
                    dados[i][1] = a.getFabricante();
                    dados[i][2] = a.getValorvenda();
                    dados[i][3] = btnAdicionar;

                    i++;
                }

                DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tPrincipal.getProdutos().getTabelaItens().setModel(dataModel);
            } catch (Exception ex) {

            }

        }

    }

    private void listarProdutosAdicionados(List<ItemVenda> itens) {
        if (!produtos.isEmpty()) {
            tPrincipal.getVendas().getTabelaItens().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"Nome", "FABRICANTE", "VALOR DE VENDA", "QUANTIDADE", "GRUP0", "REMOVER"};
                Object[][] dados = new Object[itens.size()][6];
                for (ItemVenda item : itens) {
                    dados[i][0] = item.getProduto().getDescricao();
                    dados[i][1] = item.getProduto().getFabricante();
                    dados[i][2] = item.getProduto().getValorvenda();
                    dados[i][3] = item.getQuantidade() + " ITEM(S)";
                    dados[i][4] = item.getProduto().getGproduto().getDescricao();
                    dados[i][5] = btnExcluir;
                    totalItens += item.getQuantidade();
                    ValorTotal += item.getProduto().getValorvenda() * item.getQuantidade();

                    i++;
                }

                DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tPrincipal.getVendas().getTabelaItens().setModel(dataModel);
            } catch (Exception ex) {

            }

        }

    }

    public java.sql.Date ConverterData(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

}