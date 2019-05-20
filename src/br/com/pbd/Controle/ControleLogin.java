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
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                login.setUsuario(usuario);
                String senhaHex="";
                StringBuilder ab = null;

                try {
                    MessageDigest  md = MessageDigest.getInstance("SHA-256");
                     byte messageDigest[] = md.digest(senha.getBytes("UTF-8"));

                 ab = new StringBuilder();

                for (byte b : messageDigest) {
                    ab.append(String.format("%02X", 0xFF & b));

                }
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(ControleLogin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(ControleLogin.class.getName()).log(Level.SEVERE, null, ex);
                }

                senhaHex = ab.toString();


                login.setSenha(senhaHex );


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
