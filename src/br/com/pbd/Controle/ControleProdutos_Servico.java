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

/**
 *
 * @author Andre-Coude
 */
public class ControleProdutos_Servico implements ActionListener {

    TelaPrincipal tPrincipal;
    List<GrupoProduto> gProdutos;
    List<Fornecedor> fornecedores;

    public ControleProdutos_Servico(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;
        tPrincipal.getServico_Produto().getBtnNovoProduto().addActionListener(this);
        tPrincipal.getcProdutos().getBtnSalvarProduto().addActionListener(this);
        tPrincipal.getcServicos().getBtnSalvar().addActionListener(this);
        tPrincipal.getcProdutos().getBtnNovoGrupo().addActionListener(this);
        tPrincipal.getcProdutos().getSalvarGrupo().addActionListener(this);
        
        gProdutos = new ArrayList<GrupoProduto>();
        fornecedores = new ArrayList<Fornecedor>();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tPrincipal.getServico_Produto().getBtnNovoProduto()) {
            preencherFornecedores();
            preencherGrupo();
        }
        if (e.getSource() == tPrincipal.getcProdutos().getBtnSalvarProduto()) {
            salvarProduto();
        }
        if (e.getSource() == tPrincipal.getcServicos().getBtnSalvar()) {
            salvarServico();
        }
        if (e.getSource() == tPrincipal.getcProdutos().getBtnNovoGrupo()) {
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

        new GenericDao<Produto>().salvar_ou_atualizar(produto);

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
        new GenericDao<Servico>().salvar_ou_atualizar(servico);
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
