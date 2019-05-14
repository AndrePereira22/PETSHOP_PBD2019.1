/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.DaoLogin;
import br.com.pbd.Modelo.Funcionario;
import br.com.pbd.Modelo.Login;
import br.com.pbd.view.TelaLogin;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;

/**
 *
 * @author Andre-Coude
 */
public class ControleLogin implements ActionListener {

    private TelaLogin tLogin;
    private TelaPrincipal tPrincipal;
    private static Funcionario funcionario; 

    
    public ControleLogin(TelaLogin tLogin, TelaPrincipal tPrincipal) {
        this.tLogin = tLogin;
        this.tPrincipal = tPrincipal;

        tLogin.getBtnAcessar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tLogin.getBtnAcessar()) {

            Login login = new Login();

            String usuario = new String(tLogin.getLogin().getText());
            String senha = new String(tLogin.getSenha().getPassword());
            login.setSenha(senha);
            login.setUsuario(usuario);

            if (senha.equals("admin") && usuario.equals("admin")) {

                tLogin.dispose();
                tPrincipal.setVisible(true);

            } else {

                Object o = null;
                try {
                    o = new DaoLogin().verificarLogin(login, "Funcionario");
                } catch (NoResultException n) {
                }

                if (o != null) {
                    funcionario = ((Funcionario) o);
                    tLogin.dispose();
                    tPrincipal.setVisible(true);

                } else {
                    try {
                        o = new DaoLogin().verificarLogin(login, "Profissional");
                    } catch (NoResultException n) {
                    }
                    if (o != null) {
                        tLogin.dispose();
                        tPrincipal.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario nao encontrado!");
                    }
                }
            }
        }
    }
    public static Funcionario getFuncionario() {
        return funcionario;
    }
}
