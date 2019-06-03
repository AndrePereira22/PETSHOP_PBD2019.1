/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.DaoCaixa;
import br.com.pbd.Dao.DaoFinanceiro;
import br.com.pbd.Dao.DaoLoja;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Caixa;
import br.com.pbd.Modelo.Loja;
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

    private final TelaPrincipal tPrincipal;
    private Caixa caixa;
    private Loja loja;

    public ControleFinanceiro(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void abrirCaixa() {

        if (loja != null) {
            
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
                caixa.setData(data);
                caixa.setStatus(Boolean.TRUE);
                caixa.setLoja(loja);
                if (caixaAnterior != null) {
                    caixa.setValorabertura(caixaAnterior.getValorfechamento());
                    caixa.setValorfechamento(caixaAnterior.getValorfechamento());
                } else {
                    caixa.setValorabertura(0.0);
                    caixa.setValorfechamento(0.0);
                }
                new GenericDao<Caixa>().salvar_ou_atualizar(caixa);

            } else {
                caixa.setStatus(Boolean.TRUE);
                new GenericDao<Caixa>().salvar_ou_atualizar(caixa);
            }
        }
    }
    public void buscarLoja(){
        
         try {
            loja = new DaoLoja().buscaUltimoLoja();
        } catch (NoResultException n) {
        }
    
    }

}
