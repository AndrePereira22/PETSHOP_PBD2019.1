/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.principal;

import br.com.pbd.Controle.ControleAnimal;
import br.com.pbd.Controle.Controle;
import br.com.pbd.Controle.ControleAgenda;
import br.com.pbd.Controle.ControleFinanceiro;
import br.com.pbd.Controle.ControleLogin;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Especie;
import br.com.pbd.Modelo.Raca;
import br.com.pbd.view.TelaLogin;
import br.com.pbd.view.TelaPrincipal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andre-Coude
 */
public class Main {

    public static void main(String[] s) {

       TelaLogin tLogin = new TelaLogin();
        TelaPrincipal tPrincipal = new TelaPrincipal();
      
        ControleLogin cLogin = new ControleLogin(tLogin, tPrincipal);
       Controle controle = new Controle(tPrincipal);
        ControleAnimal cAnimal = new ControleAnimal(tPrincipal);
        ControleAgenda cAgenda = new ControleAgenda(tPrincipal);
       ControleFinanceiro cFinanceiro = new ControleFinanceiro(tPrincipal);
   
    
//Date d = new Date(System.currentTimeMillis());//
	 
 	
	 	 
    }
}
