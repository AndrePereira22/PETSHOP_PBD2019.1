/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Andre-Coude
 */
public class ControleGerencial extends MouseAdapter implements ActionListener {

    TelaPrincipal tPrincipal;

    public ControleGerencial(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;

        tPrincipal.getBtnGerencia().addActionListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tPrincipal.getBtnGerencia()) {
        }

    }

}
