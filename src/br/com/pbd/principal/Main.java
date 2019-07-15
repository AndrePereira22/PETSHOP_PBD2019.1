package br.com.pbd.principal;

import br.com.pbd.Controle.ControleAnimal;
import br.com.pbd.Controle.Controle;
import br.com.pbd.Controle.ControleAgenda;
import br.com.pbd.Controle.ControleFinanceiro;
import br.com.pbd.Controle.ControleGerencial;
import br.com.pbd.Controle.ControleLogin;
import br.com.pbd.Controle.ControlPro_Serv;
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
        ControleLogin cLogin = new ControleLogin(tLogin, tPrincipal);
        ControleFinanceiro cFinanceiro = new ControleFinanceiro(tPrincipal, fachada);
        cFinanceiro.abrirCaixa();
        
        
        
// public void exibirRelatorioFuncionario() throws SQLException, JRException {
//
//     
//       Conection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PBD", "postgres", "123");
//        InputStream fonte = ControleRelatorios.class.getResourceAsStream("/br/com/pbd/RelatoriosView/RelatorioFuncionario.jasper");
//      Map<String, Object>   map = new HashMap<String, Object>();
//        map.put("D", principal.getRelatorios().getRelatorioFuncionario().getCalendarioIn().getDate());
//        map.put("DataFinal", principal.getRelatorios().getRelatorioFuncionario().getCalendarioFin().getDate());
//        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fonte);
//        jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
//        JasperViewer jrviewer = new JasperViewer(jasperPrint, false);
//        jrviewer.setVisible(true);
//        jrviewer.toFront();
//
//    }



    }
}
