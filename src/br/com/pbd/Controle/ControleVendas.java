
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.DaoCliente;
import br.com.pbd.Dao.DaoProduto;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Cliente;
import br.com.pbd.Modelo.ItemVenda;
import br.com.pbd.Modelo.Pagamento;
import br.com.pbd.Modelo.Parcela;
import br.com.pbd.Modelo.Produto;
import br.com.pbd.Modelo.Render;
import br.com.pbd.Modelo.Venda;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andre-Coude
 */
public class ControleVendas extends MouseAdapter implements ActionListener, KeyListener {

    private final TelaPrincipal tPrincipal;
    private Venda venda;
    private Pagamento pagamento;
    private Cliente cliente;
    private List<ItemVenda> itens;
    private List<Cliente> clientes;
    private List<Produto> produtos, produtosAdicionados;
    private final List<Parcela> parcelas;
    private int totalItens;
    private Double ValorTotal, ValorFinal;
    private Produto produto;
    private final Calendar calendario;
    private int escolha;
    private final int salvar = 1, edicao = 2, exclusao = 3, adicao = 4;
    private HashMap<Integer, Boolean> keyEventos;

    public ControleVendas(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;
        totalItens = 0;
        ValorTotal = 0.0;
        calendario = new GregorianCalendar();
        parcelas = new ArrayList<Parcela>();
        keyEventos = new HashMap<Integer, Boolean>();
        adicionarEventos();
    };

    

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tPrincipal.getProdutos().getTabelaItens()) {

            int ro = retornaIndice(tPrincipal.getProdutos().getTabelaItens(), e);
            if (escolha == adicao) {
                tPrincipal.getQuantidade().setVisible(true);
                produto = produtos.get(ro);
            }
        }
        if (e.getSource() == tPrincipal.getVendas().getTabelaItens()) {

            int ro = retornaIndice(tPrincipal.getProdutos().getTabelaItens(), e);
            if (escolha == edicao) {
                tPrincipal.getQuantidade().setVisible(true);
                produto = produtos.get(ro);
            } else if (escolha == exclusao) {

            }
        }
        if (e.getSource() == tPrincipal.geteClientes().getTabelaItens()) {

            int ro = retornaIndice(tPrincipal.geteClientes().getTabelaItens(), e);
            if (escolha == adicao) {
                cliente = clientes.get(ro);
                escolherCliente(cliente);
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tPrincipal.getBtnVendas()) {
            venda = new Venda();
            itens = new ArrayList<ItemVenda>();
            produtos = new ArrayList<Produto>();
            produtosAdicionados = new ArrayList<Produto>();
            totalItens = 0;
            produto = new Produto();
        }
        if (e.getSource() == tPrincipal.getVendas().getBtnPesquisar()) {
            tPrincipal.getProdutos().setVisible(true);
            ValorTotal = 0.0;
            totalItens = 0;
            produtos = new DaoProduto().listarProduto(tPrincipal.getVendas().getTxtPesquisarProdutos().getText());
            listarProdutos(produtos);
        }
        if (e.getSource() == tPrincipal.getVendas().getBtnClientes()) {

            clientes = new DaoCliente().Busca(tPrincipal.getVendas().getTxtCliente().getText());
            listarClientes(clientes);
        }
        if (e.getSource() == tPrincipal.getVendas().getBtnFinalizarVenda()) {
            if (produtosAdicionados.isEmpty()) {
                JOptionPane.showMessageDialog(null, "ADICIONE PRODUTOS !");

            } else {

                if (!tPrincipal.getVendas().getTxtDesconto().getText().equals("")) {
                    ValorFinal = ValorTotal - Double.parseDouble(tPrincipal.getVendas().getTxtDesconto().getText());
                } else {
                    ValorFinal = ValorTotal;
                }
                tPrincipal.getPagamento().getTxtTotalPagar().setText(ValorFinal + "");
            }
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
            venda.setClientes(clientes);

            try {
                venda.setItens(itens);
                new GenericDao<Venda>().salvar_ou_atualizar(venda);
                itens.removeAll(itens);
                listarProdutosAdicionados(itens);
                cliente = null;
                tPrincipal.getVendas().limparComponentes();

            } catch (java.lang.IllegalStateException n) {
                JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
            } catch (javax.persistence.RollbackException roll) {
                JOptionPane.showMessageDialog(null, roll.getCause());
            }

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
        if (produtosAdicionados == null) {
            produtosAdicionados = new ArrayList<Produto>();
        }

        produtosAdicionados.add(produto);
        ItemVenda item = new ItemVenda();
        int numero = 0;
        try {
            numero = Integer.parseInt(tPrincipal.getQuantidade().getTxtQuantidade().getText());
            item.setQuantidade(numero);
            item.setProduto(produto);
            item.setVenda(venda);

            itens.add(item);

        } catch (NumberFormatException erro) {
        }

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
                    dados[i][3] = tPrincipal.getVendas().getBtnAdicionarProduto();

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
                String[] colunas = new String[]{"Nome", "FABRICANTE", "VALOR DE VENDA", "QUANTIDADE", "EDITAR", "REMOVER"};
                Object[][] dados = new Object[itens.size()][6];
                for (ItemVenda item : itens) {
                    dados[i][0] = item.getProduto().getDescricao();
                    dados[i][1] = item.getProduto().getFabricante();
                    dados[i][2] = item.getProduto().getValorvenda();
                    dados[i][3] = item.getQuantidade();
                    dados[i][4] = tPrincipal.getVendas().getBtnEditar();
                    dados[i][5] = tPrincipal.getVendas().getBtnExcluir();
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

    private void listarClientes(List<Cliente> itens) {
        if (!itens.isEmpty()) {
            tPrincipal.geteClientes().getTabelaItens().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"Nome", "CPF", "NASCIMENTO", "SEXO", "ESCOLHER"};
                Object[][] dados = new Object[itens.size()][5];
                for (Cliente item : itens) {
                    dados[i][0] = item.getNome();
                    dados[i][1] = item.getCpf();
                    dados[i][2] = item.getNascimento();
                    dados[i][3] = item.getSexo();
                    dados[i][4] = tPrincipal.geteClientes().getBtnAdicionar();

                    i++;
                }

                DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tPrincipal.geteClientes().getTabelaItens().setModel(dataModel);
            } catch (Exception ex) {

            }

        }

    }

    public java.sql.Date ConverterData(java.util.Date date) {
        return new java.sql.Date(date.getTime());
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
                    escolha = edicao;
                } else if (boton.getName().equals("excluir")) {
                    escolha = exclusao;
                } else if (boton.getName().equals("adicionar")) {
                    escolha = adicao;
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
        keyEventos.put(e.getKeyCode(), true);

        if (e.getSource() == tPrincipal.geteClientes().getTxtPesquisarClientes()) {
            clientes = new DaoCliente().Busca(tPrincipal.geteClientes().getTxtPesquisarClientes().getText());
            listarClientes(clientes);
        }
        if (e.getSource() == tPrincipal.getProdutos().getTxtPesquisarProdutos()) {
            produtos = new DaoProduto().listarProduto(tPrincipal.getProdutos().getTxtPesquisarProdutos().getText());
            listarProdutos(produtos);
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyEventos.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public void escolherCliente(Cliente cliente) {

        tPrincipal.geteClientes().setVisible(false);
        tPrincipal.getVendas().setVisible(true);
        tPrincipal.getVendas().getTxtCliente().setText(cliente.getNome());
        clientes = new ArrayList<Cliente>();
        clientes.add(cliente);
    }

    public void adicionarEventos() {

        tPrincipal.getBtnVendas().addActionListener(this);
        tPrincipal.getVendas().getBtnPesquisar().addActionListener(this);
        tPrincipal.getVendas().getBtnClientes().addActionListener(this);
        tPrincipal.getVendas().getBtnFinalizarVenda().addActionListener(this);
        tPrincipal.getQuantidade().getBtnConfirmar().addActionListener(this);
        tPrincipal.getPagamento().getBtnFinalizar().addActionListener(this);
        tPrincipal.getProdutos().getTxtPesquisarProdutos().addActionListener(this);
        tPrincipal.getProdutos().getTabelaItens().addMouseListener(this);
        tPrincipal.getProdutos().getTabelaItens().addMouseListener(this);
        tPrincipal.geteClientes().getTabelaItens().addMouseListener(this);
        tPrincipal.geteClientes().getTxtPesquisarClientes().addKeyListener(this);
        tPrincipal.getProdutos().getTxtPesquisarProdutos().addKeyListener(this);
    }

}
