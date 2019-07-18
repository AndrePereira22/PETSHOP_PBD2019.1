package br.com.pbd.principal;

import br.com.pbd.Controle.ControleAnimal;
import br.com.pbd.Controle.Controle;
import br.com.pbd.Controle.ControleAgenda;
import br.com.pbd.Controle.ControleFinanceiro;
import br.com.pbd.Controle.ControleGerencial;
import br.com.pbd.Controle.ControleLogin;
import br.com.pbd.Controle.ControlPro_Serv;
import br.com.pbd.Controle.ControleRelatorio;
import br.com.pbd.Controle.ControleVendas;
import br.com.pbd.Modelo.SalvarDadosRequiridos;
import br.com.pbd.fachada.Fachada;
import br.com.pbd.view.TelaLogin;
import br.com.pbd.view.TelaPrincipal;

public class Main {

    public static void main(String[] s) {

        SalvarDadosRequiridos.salvarDadosRequiridos();
        SalvarDadosRequiridos.procurarLoja();
        SalvarDadosRequiridos.procurarAdm();

        Fachada fachada = Fachada.getInstance();
        TelaPrincipal tPrincipal = new TelaPrincipal();
        Controle controle = new Controle(tPrincipal, fachada);
        ControleAnimal cAnimal = new ControleAnimal(tPrincipal, fachada);
        ControleAgenda cAgenda = new ControleAgenda(tPrincipal, fachada);
        ControlPro_Serv cProduto_Servico = new ControlPro_Serv(tPrincipal, fachada);
        ControleVendas cVendas = new ControleVendas(tPrincipal, fachada);
        ControleGerencial cGerencial = new ControleGerencial(tPrincipal, fachada);
        TelaLogin tLogin = new TelaLogin();
        ControleLogin cLogin = new ControleLogin(tLogin, tPrincipal,fachada);
        ControleFinanceiro cFinanceiro = new ControleFinanceiro(tPrincipal, fachada);
        ControleRelatorio cRelatorio = new ControleRelatorio(tPrincipal,fachada);
        cFinanceiro.abrirCaixa();
        
    }
}
