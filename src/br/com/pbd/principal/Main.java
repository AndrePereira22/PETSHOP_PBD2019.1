package br.com.pbd.principal;

import br.com.pbd.Controle.ControleAnimal;
import br.com.pbd.Controle.Controle;
import br.com.pbd.Controle.ControleAgenda;
import br.com.pbd.Controle.ControleFinanceiro;
import br.com.pbd.Controle.ControleGerencial;
import br.com.pbd.Controle.ControleLogin;
import br.com.pbd.Controle.ControleProdutos_Servico;
import br.com.pbd.Controle.ControleVendas;
import br.com.pbd.Modelo.SalvarDadosRequiridos;
import br.com.pbd.fachada.Fachada;
import br.com.pbd.view.TelaLogin;
import br.com.pbd.view.TelaPrincipal;

public class Main {

    public static void main(String[] s) {

        SalvarDadosRequiridos.salvarDadosRequiridos();
        SalvarDadosRequiridos.procurarLoja();

        Fachada fachada = Fachada.getInstance();
        TelaPrincipal tPrincipal = new TelaPrincipal();

        Controle controle = new Controle(tPrincipal, fachada);
        ControleAnimal cAnimal = new ControleAnimal(tPrincipal, fachada);
        ControleAgenda cAgenda = new ControleAgenda(tPrincipal, fachada);
        ControleProdutos_Servico cProduto_Servico = new ControleProdutos_Servico(tPrincipal);
        ControleVendas cVendas = new ControleVendas(tPrincipal);
        ControleGerencial cGerencial = new ControleGerencial(tPrincipal);
        TelaLogin tLogin = new TelaLogin();
        ControleLogin cLogin = new ControleLogin(tLogin, tPrincipal);
        ControleFinanceiro cFinanceiro = new ControleFinanceiro(tPrincipal);

    }
}
