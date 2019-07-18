/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.DaoView.DaoViewProduto;
import br.com.pbd.Dao.DaoProduto;
import br.com.pbd.Modelo.Fornecedor;
import br.com.pbd.Modelo.GrupoProduto;
import br.com.pbd.Modelo.Produto;
import br.com.pbd.Modelo.Render;
import br.com.pbd.Modelo.Servico;
import br.com.pbd.Visao.ViewProduto;
import br.com.pbd.fachada.Fachada;
import br.com.pbd.view.DiaMensagem;
import br.com.pbd.view.DiaOpcao;
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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andre-Coude
 */
public class ControlPro_Serv extends MouseAdapter implements ActionListener {

    private final Fachada fachada;
    private final TelaPrincipal tPrincipal;
    List<GrupoProduto> grupos;
    private GrupoProduto grupo;
    List<Fornecedor> fornecedores;
    private List<ViewProduto> viewprodutos;
    private Produto produto;
    private List<Servico> servicos;
    private Servico servico;
    private int escolha;
    private final int salvar = 1, edicao = 2, exclusao = 3;
    private final DiaMensagem mens;

    public ControlPro_Serv(TelaPrincipal tPrincipal, Fachada fachada) {
        this.tPrincipal = tPrincipal;
        this.fachada = fachada;
        this.mens = new DiaMensagem(tPrincipal, true);

        grupos = new ArrayList<GrupoProduto>();
        fornecedores = new ArrayList<Fornecedor>();
        viewprodutos = new ArrayList<ViewProduto>();
        produto = new Produto();
        grupo = new GrupoProduto();
        servico = new Servico();

        adicionarEventos();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == tPrincipal.getServico_Produto().getTabelaProdutos()) {

            int ro = retornaIndice(tPrincipal.getServico_Produto().getTabelaProdutos(), e);

            if (escolha == edicao) {
                produto = new DaoProduto().bucarPorId(viewprodutos.get(ro).getId());

                tPrincipal.getServico_Produto().setVisible(false);
                tPrincipal.getcProdutos().setVisible(true);
                tPrincipal.getcProdutos().preencherDados(produto);

            } else if (escolha == exclusao) {
                if (fachada.excluir(produto)) {
                    viewprodutos = new DaoViewProduto().getAllView();
                    mens.getLblMens().setText("EXCLUSAO FINALIZADA!");
                    mens.setVisible(true);
                    listarProdutos();
                } else {
                    mens.getLblMens().setText("EXCLUSÃO NAO PERMITIDA");
                    mens.setVisible(true);
                }
            }
        }
        if (e.getSource() == tPrincipal.getServico_Produto().getTabelaServicos()) {

            int ro = retornaIndice(tPrincipal.getServico_Produto().getTabelaServicos(), e);
            try {
                servico = servicos.get(ro);
                if (escolha == edicao) {
                    tPrincipal.getServico_Produto().setVisible(false);
                    tPrincipal.getcServicos().setVisible(true);
                    tPrincipal.getcServicos().preencherDados(servico);
                } else if (escolha == exclusao) {

                    fachada.ativarDesativar(servico);
                    servicos = fachada.getAllServico();
                    mens.mostrarExclusao();
                    listarServicos(servicos);

                }
            } catch (ArrayIndexOutOfBoundsException x) {
            }

        }
        if (e.getSource() == tPrincipal.getcGrupo().getTabelaGrupos()) {

            int ro = retornaIndice(tPrincipal.getcGrupo().getTabelaGrupos(), e);
            tPrincipal.getcGrupo().ativarComponentes(true);
            grupo = grupos.get(ro);
            tPrincipal.getcGrupo().preencherDados(grupo);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tPrincipal.getcGrupo().getBtnNovaGrupo()) {
            escolha = salvar;
        }
        if (e.getSource() == tPrincipal.getCadastros().getBtnGrupo()) {
            listarGrupos();
        }
        if (e.getSource() == tPrincipal.getcGrupo().getBtnSalvarNovoGrupo()) {
            if (escolha == salvar) {
                salvarGrupoProduto();
            } else if (escolha == edicao) {
                editarGrupoProduto(grupo);
            }
        }

        if (e.getSource() == tPrincipal.getServico_Produto().getBtnNovoProduto()) {
            tPrincipal.getcProdutos().limparComponentes();
            preencherFornecedores();
            preencherGrupo();
            escolha = salvar;
        }
        if (e.getSource() == tPrincipal.getServico_Produto().getBtnNovoServico()) {
            tPrincipal.getcServicos().limparComponentes();
            escolha = salvar;

        }
        if (e.getSource() == tPrincipal.getcProdutos().getBtnSalvar()) {
            if (escolha == salvar) {
                salvarProduto();
            } else if (escolha == edicao) {
                editarProduto(produto);
            }
        }
        if (e.getSource() == tPrincipal.getcServicos().getBtnSalvar()) {
            if (escolha == salvar) {
                salvarServico();
            } else if (escolha == edicao) {
                editarServico(servico);
            }

        }
        if (e.getSource() == tPrincipal.getBtnProdutos_serv()) {
            listarProdutos();
            preencherFornecedores();
            preencherGrupo();
            servicos = fachada.getAllServico();
            listarServicos(servicos);
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
        String quantidade = tPrincipal.getcProdutos().getTxtQuantidade().getText();

        Double valorCompra = 0.0, valorVenda = 0.0;
        Random gerador = new Random();
        int codigo = gerador.nextInt() * (1000);
        if (codigo < 1) {
            codigo = codigo * -1;
        }
        produto.setCodigo(codigo);

        try {
            forn = fornecedores.get(intdiceF);
            grupo = grupos.get(intdiceG);
            produto.setFornecedor(forn);
            produto.setGproduto(grupo);
            produto.setQuantidae_estoque(Integer.parseInt(quantidade));

            valorCompra = Double.parseDouble(vCompra);
            valorVenda = Double.parseDouble(vVenda);
            produto.setValorvenda(valorVenda);
            produto.setValorcompra(valorCompra);

            fachada.salvar(produto);
            mens.mostrarConfirmacao();
            tPrincipal.getcProdutos().setVisible(false);
            tPrincipal.getServico_Produto().setVisible(true);
            listarProdutos();
        } catch (java.lang.IllegalStateException | javax.persistence.RollbackException | NumberFormatException | ArrayIndexOutOfBoundsException roll) {
            mens.mostrarCamposInvalidos();
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
            fachada.salvar(servico);
            mens.mostrarConfirmacao();
            tPrincipal.getcServicos().setVisible(false);
            tPrincipal.getServico_Produto().setVisible(true);
            servicos = fachada.getAllServico();
            listarServicos(servicos);

        } catch (java.lang.IllegalStateException | javax.persistence.RollbackException | NumberFormatException n) {
            mens.mostrarCamposInvalidos();
        }
    }

    private void salvarGrupoProduto() {
        GrupoProduto grupo = new GrupoProduto();
        grupo.setDescricao(tPrincipal.getcGrupo().getTxtDescricao().getText());

        try {
            fachada.salvar(grupo);
            mens.mostrarConfirmacao();
            tPrincipal.getcGrupo().ativarComponentes(false);
            tPrincipal.getcGrupo().limparComponentes();
            listarGrupos();

        } catch (java.lang.IllegalStateException | javax.persistence.RollbackException n) {
            mens.mostrarCamposInvalidos();
        }

    }

    public void listarProdutos() {

        viewprodutos = new DaoViewProduto().getAllView();
        if (!viewprodutos.isEmpty()) {
            tPrincipal.getServico_Produto().getTabelaProdutos().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"CODIGO", "NOME", "FABRICANTE", "VALOR DE VENDA", "FORNECEDOR", "EDITAR", "EXCLUIR"};
                Object[][] dados = new Object[viewprodutos.size()][7];
                for (ViewProduto a : viewprodutos) {
                    dados[i][0] = a.getCodigo();
                    dados[i][1] = a.getNome();
                    dados[i][2] = a.getFabricante();
                    dados[i][3] = a.getValor_venda() + "";
                    dados[i][4] = a.getNome_fantasia();
                    dados[i][5] = tPrincipal.getBtnEditar();
                    dados[i][6] = tPrincipal.getBtnExcluir();

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

    public void listarServicos(List<Servico> servicos) {
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
                    dados[i][3] = tPrincipal.getBtnEditar();
                    dados[i][4] = tPrincipal.getBtnExcluir();

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

        grupos = fachada.getAllGrupo();
        if (!grupos.isEmpty()) {
            tPrincipal.getcGrupo().getTabelaGrupos().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"DESCRIÇÃO", "EDITAR", "EXCLUIR"};
                Object[][] dados = new Object[grupos.size()][3];
                for (GrupoProduto a : grupos) {
                    dados[i][0] = a.getDescricao();
                    dados[i][1] = tPrincipal.getBtnEditar();
                    dados[i][2] = tPrincipal.getBtnExcluir();

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
        fornecedores = fachada.getAllFornecedor();
        tPrincipal.getAgenarServico().getComboServico().removeAllItems();
        fornecedores.forEach((c) -> {
            tPrincipal.getcProdutos().getComboFornecedor().addItem(c.getNomefantasia());
        });

    }

    private void preencherGrupo() {
        grupos = fachada.getAllGrupo();
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

            fachada.salvar(servico);
            mens.mostrarEdicao();
            tPrincipal.getServico_Produto().setVisible(true);
            servicos = fachada.buscarAtivos(true);
            listarServicos(servicos);

        } catch (java.lang.IllegalStateException | javax.persistence.RollbackException | NumberFormatException n) {
            mens.mostrarCamposInvalidos();
        }
    }

    private void editarProduto(Produto produto) {

        produto.setDescricao(tPrincipal.getcProdutos().getTxtDescricao().getText());
        produto.setFabricante(tPrincipal.getcProdutos().getTxtaFabricante().getText());

        Fornecedor forn = null;
        GrupoProduto grupo = null;
        try {

        } catch (ArrayIndexOutOfBoundsException erro) {
            mens.mostrarCamposInvalidos();
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

            fachada.salvar(produto);
            mens.mostrarEdicao();
            tPrincipal.getcProdutos().setVisible(false);
            tPrincipal.getServico_Produto().setVisible(true);
            listarProdutos();

        } catch (java.lang.IllegalStateException | javax.persistence.RollbackException | NumberFormatException n) {
            mens.mostrarCamposInvalidos();
        }
    }

    private void editarGrupoProduto(GrupoProduto grupo) {

        grupo.setDescricao(tPrincipal.getcGrupo().getTxtDescricao().getText());

        try {
            fachada.salvar(grupo);
            mens.mostrarEdicao();
            tPrincipal.getcGrupo().ativarComponentes(false);
            tPrincipal.getcGrupo().limparComponentes();
            listarGrupos();

        } catch (java.lang.IllegalStateException | javax.persistence.RollbackException n) {
                 mens.mostrarCamposInvalidos();
        }

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

        tPrincipal.getServico_Produto().getTabelaServicos().addMouseListener(this);
        tPrincipal.getServico_Produto().getTabelaProdutos().addMouseListener(this);
        tPrincipal.getcGrupo().getTabelaGrupos().addMouseListener(this);
        tPrincipal.getServico_Produto().getBtnNovoProduto().addActionListener(this);
        tPrincipal.getServico_Produto().getBtnNovoServico().addActionListener(this);
        tPrincipal.getcProdutos().getBtnSalvar().addActionListener(this);
        tPrincipal.getcServicos().getBtnSalvar().addActionListener(this);
        tPrincipal.getBtnProdutos_serv().addActionListener(this);
        tPrincipal.getCadastros().getBtnGrupo().addActionListener(this);
        tPrincipal.getcGrupo().getBtnSalvarNovoGrupo().addActionListener(this);
        tPrincipal.getcGrupo().getBtnNovaGrupo().addActionListener(this);
        tPrincipal.getServico_Produto().getBtnNovoServico().addActionListener(this);
    }
}
