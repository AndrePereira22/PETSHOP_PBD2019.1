package br.com.pbd.principal;

import br.com.pbd.Controle.ControleAnimal;
import br.com.pbd.Controle.Controle;
import br.com.pbd.Controle.ControleAgenda;
import br.com.pbd.Controle.ControleFinanceiro;
import br.com.pbd.Controle.ControleLogin;
import br.com.pbd.Controle.ControleProdutos_Servico;
import br.com.pbd.view.TelaLogin;
import br.com.pbd.view.TelaPrincipal;

public class Main {

    public static void main(String[] s) {

        TelaLogin tLogin = new TelaLogin();
        TelaPrincipal tPrincipal = new TelaPrincipal();

        ControleLogin cLogin = new ControleLogin(tLogin, tPrincipal);
        Controle controle = new Controle(tPrincipal);
        ControleAnimal cAnimal = new ControleAnimal(tPrincipal);
        ControleAgenda cAgenda = new ControleAgenda(tPrincipal);
        ControleFinanceiro cFinanceiro = new ControleFinanceiro(tPrincipal);
        ControleProdutos_Servico cProduto_Servico = new ControleProdutos_Servico(tPrincipal);

    }

}
