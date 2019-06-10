/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.DaoLogin;
import br.com.pbd.Modelo.Funcionario;
import br.com.pbd.Modelo.Login;
import br.com.pbd.Modelo.Profissional;
import br.com.pbd.view.DiaMensagem;
import br.com.pbd.view.DiaSair;
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

/**
 *
 * @author Andre-Coude
 */
public class ControleLogin implements ActionListener, KeyListener {

    private final TelaLogin tLogin;
    private final TelaPrincipal tPrincipal;
    private static Funcionario funcionario;
    private static Profissional profissional;
    private final HashMap<Integer, Boolean> keyEventos;
    private Object objeto;
    private final DiaMensagem mensagem;
    private final DiaSair sair;

    public ControleLogin(TelaLogin tLogin, TelaPrincipal tPrincipal) {
        this.tLogin = tLogin;
        this.tPrincipal = tPrincipal;
        keyEventos = new HashMap<Integer, Boolean>();
        mensagem = new DiaMensagem(this.tLogin, true);
        sair = new DiaSair(this.tPrincipal, true);

        tLogin.getBtnAcessar().addActionListener(this);
        tLogin.getSenha().addKeyListener(this);
        tLogin.getLogin().addKeyListener(this);
        mensagem.getBtnOk().addKeyListener(this);
        tPrincipal.getBtnSair().addActionListener(this);
        sair.getBtnLogoof().addActionListener(this);
        sair.getBtnSair().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tLogin.getBtnAcessar()) {
            logar();
        }
        if (e.getSource() == tPrincipal.getBtnSair()) {
            sair.setVisible(true);
        }
        if (e.getSource() == sair.getBtnLogoof()) {
            sair.setVisible(false);
            tPrincipal.setVisible(false);
            tLogin.limparCampos();
            tLogin.setVisible(true);
        }
        if (e.getSource() == sair.getBtnSair()) {
            System.exit(0);
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

        objeto = null;

        if(acessoAdmi(login)){
            
        }else if (acessoFunc(login)){

        }else{
            acessoProfi(login);
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyEventos.put(e.getKeyCode(), true);

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            if (e.getSource() == mensagem.getBtnOk()) {
                mensagem.setVisible(false);
            } else {
                logar();
            }

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

    public boolean acessoAdmi(Login login) {

        try {
            objeto = new DaoLogin().verificarLogin(login, "Administrador");
            if (objeto != null) {

                tPrincipal.getjLabel2().setText("ADMINISTRADOR");
                tPrincipal.getjLabel2().setIcon(tLogin.getPro());
                tPrincipal.getBtnVendas().setEnabled(false);
                tPrincipal.getBtnClientes().setEnabled(true);
                tPrincipal.getBtnGerencia().setEnabled(true);
                tPrincipal.getBtnFinanceiro().setEnabled(true);
                tPrincipal.getBtnCadastros().setEnabled(true);
                tPrincipal.getBtnProdutos_serv().setEnabled(true);
                tPrincipal.getBtnAgenda().setEnabled(true);
                tLogin.dispose();
                tPrincipal.setVisible(true);
                return true;
            }

        } catch (NoResultException n) {
            return false;
        }
        return false;
    }

    public boolean acessoFunc(Login login) {
        try {
            objeto = new DaoLogin().verificarLogin(login, "Funcionario");
            if (objeto != null) {
                funcionario = ((Funcionario) objeto);

                tPrincipal.getjLabel2().setText(funcionario.getNome());
                tPrincipal.getjLabel2().setIcon(tLogin.getUser());
                tPrincipal.getBtnVendas().setEnabled(true);
                tPrincipal.getBtnClientes().setEnabled(true);
                tPrincipal.getBtnGerencia().setEnabled(false);
                tPrincipal.getBtnFinanceiro().setEnabled(true);
                tPrincipal.getBtnCadastros().setEnabled(true);
                tPrincipal.getBtnProdutos_serv().setEnabled(true);
                tPrincipal.getBtnAgenda().setEnabled(true);
                tLogin.dispose();
                tPrincipal.setVisible(true);
                return true;
            }
        } catch (NoResultException n) {
            return false;
        }
        return false;
    }

    public void acessoProfi(Login login) {

        try {
            objeto = new DaoLogin().verificarLogin(login, "Profissional");
            if (objeto != null) {
                profissional = ((Profissional) objeto);
                tPrincipal.getjLabel2().setText(profissional.getNome());
                tPrincipal.getjLabel2().setIcon(tLogin.getPro());
                tPrincipal.getBtnVendas().setEnabled(false);
                tPrincipal.getBtnClientes().setEnabled(false);
                tPrincipal.getBtnGerencia().setEnabled(false);
                tPrincipal.getBtnFinanceiro().setEnabled(false);
                tPrincipal.getBtnCadastros().setEnabled(false);
                tPrincipal.getBtnProdutos_serv().setEnabled(false);
                tPrincipal.getBtnAgenda().setEnabled(true);
                tLogin.dispose();
                tPrincipal.setVisible(true);
            }
        } catch (NoResultException n) {
            mensagemErro();
        }

    }

    public void mensagemErro() {

        mensagem.getLblMens().setText("Usuario ou senha invalidos!");
        mensagem.setVisible(true);

    }

    public static Funcionario getFuncionario() {
        return funcionario;
    }

}
