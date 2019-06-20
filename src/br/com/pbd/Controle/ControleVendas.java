
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.DaoCaixa;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Caixa;
import br.com.pbd.Modelo.Cliente;
import br.com.pbd.Modelo.ItemVenda;
import br.com.pbd.Modelo.Pagamento;
import br.com.pbd.Modelo.Parcela;
import br.com.pbd.Modelo.Produto;
import br.com.pbd.Modelo.Render;
import br.com.pbd.Modelo.Venda;
import br.com.pbd.fachada.Fachada;
import br.com.pbd.view.DiaClientes;
import br.com.pbd.view.DiaPagamento;
import br.com.pbd.view.DiaProdutos;
import br.com.pbd.view.DiaQuantidade;
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
import javax.persistence.NoResultException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andre-Coude
 */
public class ControleVendas extends MouseAdapter implements ActionListener, KeyListener {

    private final Fachada fachada;
    private final TelaPrincipal tPrincipal;
    private Venda venda;
    private Pagamento pagamento;
    private Cliente cliente;
    private List<ItemVenda> itens;
    private List<Cliente> clientes;
    private List<Produto> produtos;
    private List<Parcela> parcelas;
    private int totalItens;
    private Double ValorTotal, ValorFinal;
    private Produto produto;
    private final Calendar calendario;
    private int opcao, indiceTemp;
    private final int edicao = 2, exclusao = 3, adicao = 4;
    private HashMap<Integer, Boolean> keyEventos;
    private DiaQuantidade diaQuantidade;
    private DiaPagamento diaPagamento;
    private DiaProdutos diaProduto;
    private DiaClientes diaClientes;

    public ControleVendas(TelaPrincipal tPrincipal, Fachada fachada) {
        this.tPrincipal = tPrincipal;
        this.fachada = fachada;

        totalItens = 0;
        ValorTotal = 0.0;
        calendario = new GregorianCalendar();
        parcelas = new ArrayList<Parcela>();
        keyEventos = new HashMap<Integer, Boolean>();
        diaQuantidade = new DiaQuantidade(tPrincipal, true);
        diaPagamento = new DiaPagamento(tPrincipal, true);
        diaProduto = new DiaProdutos(tPrincipal, true);
        diaClientes = new DiaClientes(tPrincipal, true);
        adicionarEventos();
    }

    ;

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == diaProduto.getTabelaItens()) {

            int ro = retornaIndice(diaProduto.getTabelaItens(), e);
            if (opcao == adicao) {
                produto = produtos.get(ro);
                diaQuantidade.setVisible(true);

            }
        }
        if (e.getSource() == tPrincipal.getVendas().getTabelaItens()) {

            int indiceTemp = retornaIndice(tPrincipal.getVendas().getTabelaItens(), e);
            if (opcao == edicao) {
                diaQuantidade.setVisible(true);

            } else if (opcao == exclusao) {
                itens.remove(indiceTemp);
                listarProdutosAdicionados(itens);
            }
        }
        if (e.getSource() == diaClientes.getTabelaClientes()) {

            int ro = retornaIndice(diaClientes.getTabelaClientes(), e);
            if (opcao == adicao) {
                cliente = clientes.get(ro);
                tPrincipal.getVendas().getTxtClienteVenda().setText(cliente.getNome());
                diaClientes.setVisible(false);
                tPrincipal.getVendas().setVisible(true);

            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tPrincipal.getBtnVendas()) {
            venda = new Venda();
            itens = new ArrayList<ItemVenda>();
            produtos = new ArrayList<Produto>();
            totalItens = 0;
            produto = new Produto();

        }
        if (e.getSource() == tPrincipal.getVendas().getBtnPesquisar()) {

            ValorTotal = 0.0;
            totalItens = 0;
            produtos = fachada.getAllProduto();
            listarProdutos(produtos);
        }
        if (e.getSource() == tPrincipal.getVendas().getBtnClientes()) {

            clientes = fachada.getAllCliente();
            listarClientes(clientes);

        }
        if (e.getSource() == tPrincipal.getVendas().getBtnFinalizarVenda()) {
            if (itens == null || itens.isEmpty()) {
                JOptionPane.showMessageDialog(null, "ADICIONE PRODUTOS !");

            } else {

                if (!tPrincipal.getVendas().getTxtDesconto().getText().equals("")) {
                    ValorFinal = ValorTotal - Double.parseDouble(tPrincipal.getVendas().getTxtDesconto().getText());
                } else {
                    ValorFinal = ValorTotal;
                }
                diaPagamento.getTxtTotalPagar().setText(ValorFinal + "");
                diaPagamento.setVisible(true);

            }
        }
        if (e.getSource() == diaQuantidade.getBtnOk()) {

            if (opcao == adicao) {
                adicionarItemVenda();
                listarProdutosAdicionados(itens);

            } else if (opcao == edicao) {

                editarQuantidade();
            }

        }
        if (e.getSource() == diaPagamento.getBtnFinalizar()) {
            escolherPagamento();
            if (venda == null) {
                venda = new Venda();
            }
            venda.setHora(new Time(Integer.MIN_VALUE));
            venda.setValortotal(ValorFinal);
            venda.setPagamento(pagamento);
            pagamento.setParcelas(parcelas);
            venda.setCaixa(buscarCaixa());
            venda.setClientes(clientes);

            try {
                venda.setItens(itens);
                new GenericDao<Venda>().salvar_ou_atualizar(venda);
                saidaDeProdutos(itens);
                zerarValores();
                tPrincipal.getVendas().limparComponentes();

            } catch (java.lang.IllegalStateException n) {
                JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
            } catch (javax.persistence.RollbackException roll) {
                JOptionPane.showMessageDialog(null, roll.getCause());
            }

        }
    }

    public void escolherPagamento() {

        pagamento = new Pagamento();
        if (diaPagamento.getRadioAvista().isSelected()) {

            pagamento.setNumeroparcelas(0);
            pagamento.setStatus(true);
            pagamento.setValortotal(ValorTotal);
            pagamento.setData(ConverterData(new Date()));
            pagamento.setForma_pagamento(diaPagamento.getComboFormaPagamento().getSelectedItem().toString());

        } else {
            int numero = Integer.parseInt(diaPagamento.getTxtParcelas().getText());
            pagamento.setNumeroparcelas(numero);
            pagamento.setStatus(true);
            pagamento.setValortotal(ValorTotal);
            pagamento.setData(ConverterData(new Date()));
            pagamento.setForma_pagamento(diaPagamento.getComboFormaPagamento().getSelectedItem().toString());
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
        diaPagamento.setVisible(false);
    }

    public void adicionarItemVenda() {

        int numero = 0;
        try {
            String n = diaQuantidade.getTxtQuantidade().getText();
            numero = Integer.parseInt(n);

        } catch (NumberFormatException | java.lang.NullPointerException erro) {
        }
        if (numero < produto.getQuantidae_estoque()) {
            if (itens == null) {
                itens = new ArrayList<ItemVenda>();
            }

            ItemVenda item = new ItemVenda();
            item.setQuantidade(numero);
            item.setProduto(produto);
            item.setVenda(venda);
            itens.add(item);
            diaQuantidade.setVisible(false);
            diaProduto.setVisible(false);
            tPrincipal.getVendas().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "NAO HA ESSA QUANTIDADE EM ESTOQUE");
        }

    }

    private void listarProdutos(List<Produto> produtos) {
        diaProduto.getTabelaItens().setDefaultRenderer(Object.class, new Render());

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
            diaProduto.getTabelaItens().setModel(dataModel);

            diaProduto.setVisible(true);

        } catch (Exception ex) {

        }

    }

    private void listarProdutosAdicionados(List<ItemVenda> itens) {
        tPrincipal.getVendas().getTabelaItens().setDefaultRenderer(Object.class, new Render());
        ValorTotal = 0.0;
        totalItens = 0;
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
            tPrincipal.getVendas().getTxtTotalItens().setText(totalItens + "");
            tPrincipal.getVendas().getTxtValorTotal().setText(ValorTotal + "");
        } catch (Exception ex) {

        }

    }

    private void listarClientes(List<Cliente> itens) {
        diaClientes.getTabelaClientes().setDefaultRenderer(Object.class, new Render());

        int i = 0;
        try {
            String[] colunas = new String[]{"Nome", "CPF", "NASCIMENTO", "SEXO", "ESCOLHER"};
            Object[][] dados = new Object[itens.size()][5];
            for (Cliente item : itens) {
                dados[i][0] = item.getNome();
                dados[i][1] = item.getCpf();
                dados[i][2] = item.getNascimento();
                dados[i][3] = item.getSexo();
                dados[i][4] = tPrincipal.getBtnAdicionar();

                i++;
            }

            DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            diaClientes.getTabelaClientes().setModel(dataModel);
            diaClientes.setVisible(true);
        } catch (Exception ex) {

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
                    opcao = edicao;
                } else if (boton.getName().equals("excluir")) {
                    opcao = exclusao;
                } else if (boton.getName().equals("adicionar")) {
                    opcao = adicao;
                } else {
                    opcao = 0;
                }
            }
        }
        return ro;
    }

    // eventos de teclas
    @Override
    public void keyPressed(KeyEvent e) {
        keyEventos.put(e.getKeyCode(), true);

        if (e.getSource() == diaClientes.getTxtPesquisa()) {
            clientes = fachada.buscaCliente(diaClientes.getTxtPesquisa().getText());
            listarClientes(clientes);
        }
        if (e.getSource() == diaProduto.getTxtPesquisa()) {
            String busca = diaProduto.getTxtPesquisa().getText();
            try {

            } catch (NumberFormatException x) {
            }
            produtos = fachada.buscaProduto(busca);
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

    public void zerarValores() {
        totalItens = 0;
        ValorTotal = 0.0;
        parcelas = new ArrayList<Parcela>();
        itens = new ArrayList<ItemVenda>();
        cliente = null;
        venda = null;

        String[] colunas = new String[]{"Nome", "FABRICANTE", "VALOR DE VENDA", "QUANTIDADE", "EDITAR", "REMOVER"};
        Object[][] dados = new Object[0][6];

        DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tPrincipal.getVendas().getTabelaItens().setModel(dataModel);

    }

    public void saidaDeProdutos(List<ItemVenda> itens) {

        itens.forEach((item) -> {
            int quantidade = item.getQuantidade();
            int quantidadeEstoque = item.getProduto().getQuantidae_estoque();
            Produto produto = item.getProduto();
            produto.setQuantidae_estoque(quantidadeEstoque - quantidade);

            fachada.salvar(produto);

        });
    }

    public void adicionarEventos() {

        tPrincipal.getBtnVendas().addActionListener(this);
        tPrincipal.getVendas().getBtnPesquisar().addActionListener(this);
        tPrincipal.getVendas().getBtnClientes().addActionListener(this);
        tPrincipal.getVendas().getBtnFinalizarVenda().addActionListener(this);
        tPrincipal.getVendas().getTabelaItens().addMouseListener(this);
        diaQuantidade.getBtnOk().addActionListener(this);
        diaPagamento.getBtnFinalizar().addActionListener(this);
        diaClientes.getTabelaClientes().addMouseListener(this);
        diaClientes.getTxtPesquisa().addKeyListener(this);
        diaProduto.getTxtPesquisa().addKeyListener(this);
        diaProduto.getTabelaItens().addMouseListener(this);
    }

    public void editarQuantidade() {

        int numero = 0;
        try {
            String n = diaQuantidade.getTxtQuantidade().getText();
            numero = Integer.parseInt(n);

        } catch (NumberFormatException | java.lang.NullPointerException erro) {
        }
        if (numero < itens.get(indiceTemp).getProduto().getQuantidae_estoque()) {
            itens.get(indiceTemp).setQuantidade(numero);
        } else {
            JOptionPane.showMessageDialog(null, "numero unsuficiente no estoque");
        }
        listarProdutosAdicionados(itens);
        diaQuantidade.setVisible(false);
    }

    public Caixa buscarCaixa() {
        Caixa caixa = null;
        try {
            caixa = new DaoCaixa().buscaUltimoCaixa();
            return caixa;
        } catch (NoResultException n) {
        }
        return null;
    }

}
