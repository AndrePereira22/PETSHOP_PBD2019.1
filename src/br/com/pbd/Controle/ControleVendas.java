/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.ItemVenda;
import br.com.pbd.Modelo.Produto;
import br.com.pbd.Modelo.Render;
import br.com.pbd.Modelo.Venda;
import br.com.pbd.view.Quantidadee;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andre-Coude
 */
public class ControleVendas implements ActionListener {

    TelaPrincipal tPrincipal;
    Venda venda;
    List<ItemVenda> itens;
    List<Produto> produtos;
     Quantidadee quantidade;

    public ControleVendas(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;

        tPrincipal.getBtnVendas().addActionListener(this);
        tPrincipal.getVendas().getBtnProdutos().addActionListener(this);

        produtos = new ArrayList<Produto>();

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

                           
                            quantidade.setVisible(true);
                        }
                    }
                }
            }

        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tPrincipal.getBtnVendas()) {
            venda = new Venda();
            itens = new ArrayList<ItemVenda>();
        }
        if (e.getSource() == tPrincipal.getVendas().getBtnProdutos()) {
            tPrincipal.getProdutos().setVisible(true);

            if (tPrincipal.getVendas().getTxtPesquisarProdutos().getText().equals("")) {
                produtos = new GenericDao<Produto>().getAll(Produto.class);
                listarProdutos(produtos);
            } else {

            }

        }

    }

    public void adicionarItemVenda() {
        ItemVenda item = new ItemVenda();

        itens.add(item);

    }

    private void listarProdutos(List<Produto> produtos) {
        if (!produtos.isEmpty()) {
            tPrincipal.getProdutos().getTabelaItens().setDefaultRenderer(Object.class, new Render());

            ImageIcon icone = new ImageIcon("br/com/pbd/resource/plus.png");
            JButton btn1 = new JButton("Modificar");
            btn1.setName("adicionar");
            btn1.setIcon(icone);

            int i = 0;
            try {
                String[] colunas = new String[]{"Nome", "FABRICANTE", "VALOR DE VENDA", "ADICIONAR"};
                Object[][] dados = new Object[produtos.size()][4];
                for (Produto a : produtos) {
                    dados[i][0] = a.getDescricao();
                    dados[i][1] = a.getFabricante();
                    dados[i][2] = a.getValorvenda();
                    dados[i][3] = btn1;

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

}
