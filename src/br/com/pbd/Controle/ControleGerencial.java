/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.DaoLoja;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Loja;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;

/**
 *
 * @author Andre-Coude
 */
public class ControleGerencial extends MouseAdapter implements ActionListener {

    private final TelaPrincipal tPrincipal;
    private Loja loja;

    public ControleGerencial(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;

        tPrincipal.getBtnGerencia().addActionListener(this);
        tPrincipal.getGerencia().getBtnEditar().addActionListener(this);
        tPrincipal.getGerencia().getBtnEditar().addActionListener(this);
        tPrincipal.getcLoja().getBtnSalvar().addActionListener(this);
        tPrincipal.getcLoja().getBtnCancelar().addActionListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tPrincipal.getBtnGerencia()) {
            buscarLoja();
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

    public void buscarLoja() {

        try {
            loja = new DaoLoja().buscaUltimoLoja();
            tPrincipal.getGerencia().preencherDados(loja);
        } catch (NoResultException n) {
        }

    }

}
