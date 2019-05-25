/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.DaoLogin;
import br.com.pbd.Dao.DaoLoja;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Dados;
import br.com.pbd.Modelo.Funcionario;
import br.com.pbd.Modelo.Login;
import br.com.pbd.Modelo.Loja;
import br.com.pbd.view.TelaLogin;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;

/**
 *
 * @author Andre-Coude
 */
public class ControleLogin implements ActionListener, KeyListener {

    private TelaLogin tLogin;
    private TelaPrincipal tPrincipal;
    private static Funcionario funcionario;
    private HashMap<Integer, Boolean> keyEventos;
    private Loja loja;

    public ControleLogin(TelaLogin tLogin, TelaPrincipal tPrincipal) {
        this.tLogin = tLogin;
        this.tPrincipal = tPrincipal;
        keyEventos = new HashMap<Integer, Boolean>();

        tLogin.getBtnAcessar().addActionListener(this);
        tLogin.getSenha().addKeyListener(this);
        tLogin.getLogin().addKeyListener(this);
        tPrincipal.getcLoja().getBtnSalvar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tLogin.getBtnAcessar()) {
            logar();
        }
        if (e.getSource() == tPrincipal.getcLoja().getBtnSalvar()) {
            salvarLoja();
        }

    }

    public void logar() {

        Login login = new Login();

        String usuario = new String(tLogin.getLogin().getText());
        String senha = new String(tLogin.getSenha().getPassword());
        login.setUsuario(usuario);
        String senhaHex = "";
        StringBuilder ab = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
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

        login.setSenha(senhaHex);

        if (senha.equals("admin") && usuario.equals("admin")) {

            tLogin.dispose();
            tPrincipal.setVisible(true);
            verificarCadastroLoja();

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
                verificarCadastroLoja();

            } else {
                try {
                    o = new DaoLogin().verificarLogin(login, "Profissional");
                } catch (NoResultException n) {
                }
                if (o != null) {
                    tLogin.dispose();
                    tPrincipal.setVisible(true);
                    verificarCadastroLoja();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario nao encontrado!");
                }
            }
        }
    }

    public void verificarCadastroLoja() {

        try {
            loja = new DaoLoja().buscaUltimoLoja();

        } catch (javax.persistence.NoResultException n) {
        }

        if (loja == null) {
            tPrincipal.getPainelMenu().setVisible(false);
            tPrincipal.getcLoja().setVisible(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyEventos.put(e.getKeyCode(), true);

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            logar();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            tLogin.getLogin().grabFocus();
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            tLogin.getSenha().grabFocus();
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && tLogin.getLogin().isFocusable()) {
            System.exit(0);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyEventos.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static Funcionario getFuncionario() {
        return funcionario;
    }

    private void salvarLoja() {

        Dados dados = new Dados();
        dados.setBairro(tPrincipal.getcLoja().getTxtBairro().getText());
        dados.setCelular(tPrincipal.getcLoja().getTxtCelular().getText());
        dados.setTelefone(tPrincipal.getcLoja().getTxtTelefone().getText());
        dados.setCep(tPrincipal.getcLoja().getTxtCep().getText());
        dados.setCidade(tPrincipal.getcLoja().getTxtCidade().getText());
        dados.setEmail(tPrincipal.getcLoja().getTxtEmail().getText());

        String numb = tPrincipal.getcLoja().getTxtNumero().getText() + " "
                + tPrincipal.getcLoja().getTxtComplemento().getText();

        dados.setNumero(numb);
        dados.setRua(tPrincipal.getcLoja().getTxtRua().getText());
        dados.setUf(tPrincipal.getcLoja().getComboUf().getSelectedItem().toString());

        Loja loja = new Loja();

        loja.setDados(dados);
        loja.setCnpj(tPrincipal.getcLoja().getTxtCnpjj().getText());
        loja.setNomefantasia(tPrincipal.getcLoja().getTxtNomeFantazia().getText());
        loja.setRazaosocial(tPrincipal.getcLoja().getTxtRazaoSociall().getText());

        try {
            new GenericDao<Loja>().salvar_ou_atualizar(loja);
            JOptionPane.showMessageDialog(null, "Loja cadastrada!");

            tPrincipal.getcLoja().setVisible(false);
            tPrincipal.getPainelMenu().setVisible(true);

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

}
