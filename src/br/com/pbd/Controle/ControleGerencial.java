/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.DaoContasApagar;
import br.com.pbd.Dao.DaoLoja;
import br.com.pbd.Dao.DaoProduto;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.ContaAPagar;
import br.com.pbd.Modelo.EntradaEstoque;
import br.com.pbd.Modelo.GrupoProduto;
import br.com.pbd.Modelo.Loja;
import br.com.pbd.Modelo.Produto;
import br.com.pbd.Modelo.Render;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
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
public class ControleGerencial extends MouseAdapter implements ActionListener {

    private final TelaPrincipal tPrincipal;
    private Loja loja;
    private List<GrupoProduto> grupos;
    private List<Produto> produtos;
    private Produto produto;

    public ControleGerencial(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;

        tPrincipal.getBtnGerencia().addActionListener(this);
        tPrincipal.getGerencia().getBtnEditar().addActionListener(this);
        tPrincipal.getGerencia().getBtnEditar().addActionListener(this);
        tPrincipal.getcLoja().getBtnSalvar().addActionListener(this);
        tPrincipal.getcLoja().getBtnCancelar().addActionListener(this);
        tPrincipal.getGerencia().getComboGrupo().addActionListener(this);
        tPrincipal.getGerencia().getTabelaProdutos().addMouseListener(this);
        tPrincipal.geteProdutos().getBtnSalvar().addActionListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tPrincipal.getGerencia().getTabelaProdutos()) {
            int ro = retornaIndice(tPrincipal.getGerencia().getTabelaProdutos(), e);

            produto = produtos.get(ro);
            tPrincipal.getGerencia().setVisible(false);
            tPrincipal.geteProdutos().prencherProduto(produto);
            tPrincipal.geteProdutos().setVisible(true);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tPrincipal.getBtnGerencia()) {
            buscarLoja();
            preencherGrupo();

        }

        if (e.getSource() == tPrincipal.geteProdutos().getBtnSalvar()) {
            salvarEntradaProdutos();

        }
        if (e.getSource() == tPrincipal.getGerencia().getComboGrupo()) {
            buscaDeProdutos();
        }

        if (e.getSource() == tPrincipal.getGerencia().getBtnEditar()) {

            if (loja != null) {
                tPrincipal.getcLoja().preencherDados(loja);
            }
        }
        if (e.getSource() == tPrincipal.getcLoja().getBtnSalvar()) {

            editarLoja(loja);

        }
        if (e.getSource() == tPrincipal.getcLoja().getBtnCancelar()) {
            tPrincipal.getcLoja().setVisible(false);
        }

    }

    private void listarProdutos(List<Produto> lista) {

        tPrincipal.getGerencia().getTabelaProdutos().setDefaultRenderer(Object.class, new Render());

        int i = 0;
        try {
            String[] colunas = new String[]{"CODIGO", "NOME", "FABRICANTE", "VALOR DE VENDA", "FORNECEDOR", "ESTOQUE ", "NOVA ENTRADA "};
            Object[][] dados = new Object[lista.size()][7];
            for (Produto a : lista) {
                dados[i][0] = a.getCodigo();
                dados[i][1] = a.getDescricao();
                dados[i][2] = a.getFabricante();
                dados[i][3] = a.getValorvenda();
                dados[i][4] = a.getFornecedor().getNomefantasia();
                dados[i][5] = a.getQuantidae_estoque();
                dados[i][6] = tPrincipal.getGerencia().getBtnAdicionar();

                i++;
            }

            DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tPrincipal.getGerencia().getTabelaProdutos().setModel(dataModel);
        } catch (Exception ex) {

        }

    }

    private void preencherGrupo() {
        grupos = new GenericDao<GrupoProduto>().getAll(GrupoProduto.class);
        tPrincipal.getGerencia().getComboGrupo().removeAllItems();
        grupos.forEach((c) -> {
            tPrincipal.getGerencia().getComboGrupo().addItem(c.getDescricao());
        });
        buscaDeProdutos();

    }

    public void buscaDeProdutos() {
        int indice = tPrincipal.getGerencia().getComboGrupo().getSelectedIndex();

        if (indice >= 0) {
            GrupoProduto grupo = grupos.get(indice);
            produtos = new DaoProduto().listarProduto(grupo);
            listarProdutos(produtos);
        }

    }

    private void editarLoja(Loja loja) {

        loja.getDados().setBairro(tPrincipal.getcLoja().getTxtBairro().getText());
        loja.getDados().setCelular(tPrincipal.getcLoja().getTxtCelular().getText());
        loja.getDados().setTelefone(tPrincipal.getcLoja().getTxtTelefone().getText());
        loja.getDados().setCep(tPrincipal.getcLoja().getTxtCep().getText());
        loja.getDados().setCidade(tPrincipal.getcLoja().getTxtCidade().getText());
        loja.getDados().setEmail(tPrincipal.getcLoja().getTxtEmail().getText());

        String numb = tPrincipal.getcLoja().getTxtNumero().getText() + " "
                + tPrincipal.getcLoja().getTxtComplemento().getText();

        loja.getDados().setNumero(numb);
        loja.getDados().setRua(tPrincipal.getcLoja().getTxtRua().getText());
        loja.getDados().setUf(tPrincipal.getcLoja().getComboUf().getSelectedItem().toString());

        loja.setCnpj(tPrincipal.getcLoja().getTxtCnpjj().getText());
        loja.setNomefantasia(tPrincipal.getcLoja().getTxtNomeFantazia().getText());
        loja.setRazaosocial(tPrincipal.getcLoja().getTxtRazaoSociall().getText());

        try {

            new GenericDao<Loja>().salvar_ou_atualizar(loja);
            JOptionPane.showMessageDialog(null, "Edição concluida!");
            tPrincipal.getcLoja().setVisible(false);
            tPrincipal.getGerencia().setVisible(true);
            tPrincipal.getGerencia().preencherDados(loja);
        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    private void salvarEntradaProdutos() {

        EntradaEstoque entrada = new EntradaEstoque();

        Date data = ConverterData(new Date(System.currentTimeMillis()));

        int quantidade = 0;

        try {

            quantidade = Integer.parseInt(tPrincipal.geteProdutos().getTxtQuatidade().getText());
            entrada.setData(data);
            entrada.setQuantidade(quantidade);
            entrada.setProduto(produto);

            new GenericDao<EntradaEstoque>().salvar_ou_atualizar(entrada);
            JOptionPane.showMessageDialog(null, "Entrada cadastrada!");
            tPrincipal.geteProdutos().setVisible(false);
            tPrincipal.getGerencia().setVisible(true);

            int atual = quantidade + produto.getQuantidae_estoque();
            produto.setQuantidae_estoque(atual);
            new GenericDao<Produto>().salvar_ou_atualizar(produto);
            buscaDeProdutos();

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
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
                ro = tabela.getSelectedRow();
                if (boton.getName().equals("adicionar")) {

                }
            }
        }
        return ro;
    }

    public java.sql.Date ConverterData(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public void buscarLoja() {

        try {
            loja = new DaoLoja().buscaUltimoLoja();
        } catch (NoResultException n) {
        }

    }

}
