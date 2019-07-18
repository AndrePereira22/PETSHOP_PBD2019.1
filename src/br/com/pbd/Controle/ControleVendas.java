
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.DaoCliente;
import br.com.pbd.DaoView.DaoViewProduto;
import br.com.pbd.Dao.DaoProduto;
import br.com.pbd.DaoView.DaoViewCliente;
import br.com.pbd.Modelo.Caixa;
import br.com.pbd.Modelo.Cliente;
import br.com.pbd.Modelo.ItemVenda;
import br.com.pbd.Modelo.Pagamento;
import br.com.pbd.Modelo.Parcela;
import br.com.pbd.Modelo.Produto;
import br.com.pbd.Modelo.Render;
import br.com.pbd.Modelo.Venda;
import br.com.pbd.Visao.ViewCliente;
import br.com.pbd.Visao.ViewProduto;
import br.com.pbd.fachada.Fachada;
import br.com.pbd.view.DiaClientes;
import br.com.pbd.view.DiaMensagem;
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
    private Caixa caixa;
    private List<ItemVenda> itens;
    private List<ViewCliente> viewclientes;
    private List<Cliente> clientes;
    private List<ViewProduto> viewProdutos;
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
    private final DiaMensagem mens;

    public ControleVendas(TelaPrincipal tPrincipal, Fachada fachada) {
        this.tPrincipal = tPrincipal;
        this.fachada = fachada;
        this.mens = new DiaMensagem(tPrincipal, true);

        totalItens = 0;
        ValorTotal = 0.0;
        calendario = new GregorianCalendar();
        parcelas = new ArrayList<Parcela>();
        clientes = new ArrayList<Cliente>();
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
                int id = viewProdutos.get(ro).getId();
                produto = new DaoProduto().bucarPorId(id);
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
                if (!clientes.isEmpty()) {
                    clientes.clear();
                }
                cliente = new DaoCliente().bucarPorId(viewclientes.get(ro).getId());
                clientes.add(cliente);
                tPrincipal.getVendas().getTxtClienteVenda().setText(cliente.getNome());
                diaClientes.setVisible(false);
                tPrincipal.getVendas().setVisible(true);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tPrincipal.getBtnVendas()) {
           // if (caixa.getStatus()) {
                venda = new Venda();
                itens = new ArrayList<ItemVenda>();
                viewProdutos = new ArrayList<ViewProduto>();
                totalItens = 0;
                produto = new Produto();
//            } else {
//                mens.mostrarCaixa();
//            }

        }
        if (e.getSource() == tPrincipal.getVendas().getBtnPesquisar()) {

            ValorTotal = 0.0;
            totalItens = 0;
            viewProdutos = new DaoViewProduto().getAllView();
            listarProdutos(viewProdutos);
        }
        if (e.getSource() == tPrincipal.getVendas().getBtnClientes()) {

            viewclientes = new DaoViewCliente().getAllView();
            listarClientes(viewclientes);

        }
        if (e.getSource() == tPrincipal.getVendas().getBtnFinalizarVenda()) {
            if (itens == null || itens.isEmpty()) {
                mens.mostrarAddProduto();

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
            venda.setValortotal(ValorFinal);
            venda.setPagamento(pagamento);
            pagamento.setParcelas(parcelas);
            venda.setCaixa(buscarCaixa());
            venda.setClientes(clientes);

            try {
                venda.setItens(itens);
                fachada.salvar(venda);
                
                //exibir(venda.getid);
                saidaDeProdutos(itens);
                zerarValores();
                tPrincipal.getVendas().limparComponentes();

            } catch (java.lang.IllegalStateException n) {
                mens.mostrarCamposInvalidos();
            } catch (javax.persistence.RollbackException roll) {
                mens.mostrarCamposInvalidos();
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
        boolean existe = false;
        try {
            String n = diaQuantidade.getTxtQuantidade().getText();
            numero = Integer.parseInt(n);

        } catch (NumberFormatException | java.lang.NullPointerException erro) {
        }
        if (numero < produto.getQuantidae_estoque() && numero > 0) {
            if (itens == null) {
                itens = new ArrayList<ItemVenda>();
            }
            if (itens.isEmpty()) {
                ItemVenda item = new ItemVenda();
                item.setQuantidade(numero);
                item.setProduto(produto);
                item.setVenda(venda);
                itens.add(item);
            } else {
                for (int i = 0; i < itens.size(); i++) {
                    if (itens.get(i).getProduto() == produto) {
                        existe = true;
                        int q = itens.get(i).getQuantidade() + numero;
                        itens.get(i).setQuantidade(q);
                    }

                }
                if (!existe) {
                    ItemVenda item = new ItemVenda();
                    item.setQuantidade(numero);
                    item.setProduto(produto);
                    item.setVenda(venda);
                    itens.add(item);
                }
                existe = false;
            }

            diaQuantidade.setVisible(false);
            diaProduto.setVisible(false);
            tPrincipal.getVendas().setVisible(true);
        } else {
            mens.mostrarSemEstoque();
        }

    }

    private void listarProdutos(List<ViewProduto> lista) {
        diaProduto.getTabelaItens().setDefaultRenderer(Object.class, new Render());

        int i = 0;
        try {
            String[] colunas = new String[]{"CODIG0", "Nome", "FABRICANTE", "VALOR DE VENDA", "ESTOQUE", "ADICIONAR"};
            Object[][] dados = new Object[lista.size()][6];
            for (ViewProduto a : lista) {
                dados[i][0] = a.getCodigo();
                dados[i][1] = a.getNome();
                dados[i][2] = a.getFabricante();
                dados[i][3] = a.getValor_venda() + "";
                dados[i][4] = a.getQuantidade_estoque();
                dados[i][5] = tPrincipal.getVendas().getBtnAdicionarProduto();

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

    private void listarClientes(List<ViewCliente> itens) {
        diaClientes.getTabelaClientes().setDefaultRenderer(Object.class, new Render());

        int i = 0;
        try {
            String[] colunas = new String[]{"Nome", "CPF", "NASCIMENTO", "SEXO", "ESCOLHER"};
            Object[][] dados = new Object[itens.size()][5];
            for (ViewCliente item : itens) {
                dados[i][0] = item.getNome();
                dados[i][1] = item.getCpf();
                dados[i][2] = item.getNascimento();
                dados[i][3] = item.getSexo();
                dados[i][4] = tPrincipal.getVendas().getBtnAdd();

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
            viewclientes = new DaoViewCliente().Busca(diaClientes.getTxtPesquisa().getText());
            listarClientes(viewclientes);
        }

        if (e.getSource() == diaProduto.getTxtPesquisa()) {
            String busca = diaProduto.getTxtPesquisa().getText();
            try {

            } catch (NumberFormatException x) {
            }
            viewProdutos = new DaoViewProduto().Busca(busca);
            listarProdutos(viewProdutos);

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
            int novo = quantidadeEstoque - quantidade;
            item.getProduto().setQuantidae_estoque(novo);
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
            mens.mostrarSemEstoque();
        }
        listarProdutosAdicionados(itens);
        diaQuantidade.setVisible(false);
    }

    public Caixa buscarCaixa() {
        caixa = null;
        try {
            caixa = fachada.buscaCaixa();
            return caixa;
        } catch (NoResultException n) {
        }
        return null;
    }

}
