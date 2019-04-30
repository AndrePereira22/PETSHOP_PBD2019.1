/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.DaoCaixa;
import br.com.pbd.Dao.DaoFinanceiro;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Caixa;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.persistence.NoResultException;

/**
 *
 * @author Andre-Coude
 */
public class ControleFinanceiro implements ActionListener {

    TelaPrincipal tPrincipal;
    private Caixa caixa;

    public ControleFinanceiro(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;

        abrirCaixa();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

//        if (e.getSource() == tPrincipal.getcFuncionario().getBtnSalvar()) {
//            abrirCaixa();
//        }
    }

    private void abrirCaixa() {

        Date data = new Date(System.currentTimeMillis());
        Caixa caixaAnterior = null;
        try {
            caixaAnterior = new DaoCaixa().buscaUltimoCaixa();
        } catch (NoResultException n) {
            System.out.println("Caixa anterior nao encontrado");
        }
        try {
            caixa = new DaoFinanceiro().buscarCaixa(data);
        } catch (NoResultException n) {
            System.out.println("Caixa do dia nao encontrado");
        }

        if (caixa == null) {
            caixa = new Caixa();
            getCaixa().setData(data);
            getCaixa().setStatus(Boolean.TRUE);
            getCaixa().setLucrodia(0.0);
            if (caixaAnterior != null) {
                caixa.setValorabertura(caixaAnterior.getValorfechamento());
                caixa.setValorfechamento(caixaAnterior.getValorfechamento());
            } else {
                getCaixa().setValorabertura(0.0);
                getCaixa().setValorfechamento(0.0);
            }
            new GenericDao<Caixa>().salvar_ou_atualizar(caixa);
            
        } else {
            caixa.setStatus(Boolean.TRUE);
            new GenericDao<Caixa>().salvar_ou_atualizar(caixa);
        }

    }

    /**
     * @return the caixa
     */
    public Caixa getCaixa() {
        return caixa;
    }

}
