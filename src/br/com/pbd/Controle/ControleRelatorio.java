/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.fachada.Fachada;
import br.com.pbd.view.DiaRelatoio;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Andre-Coude
 */
public class ControleRelatorio implements ActionListener {

    private final Fachada fachada;
    private final TelaPrincipal tPrincipal;
    private DiaRelatoio diaRelatorio;

    public ControleRelatorio(TelaPrincipal tPrincipal, Fachada fachada) {
        this.tPrincipal = tPrincipal;
        this.fachada = fachada;
        this.diaRelatorio = new DiaRelatoio(tPrincipal, true);
        
        tPrincipal.getFinancas().getBtnRelatorio().addActionListener(this);
        diaRelatorio.getBtnOk().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        
        if (e.getSource() == tPrincipal.getFinancas().getBtnRelatorio()) {
           diaRelatorio.setVisible(true);
        }
        if (e.getSource() ==  diaRelatorio.getBtnOk()) {
            try {
                exibirRelatorioFuncionario();
            } catch (SQLException ex) {
                Logger.getLogger(ControleRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JRException ex) {
                Logger.getLogger(ControleRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void exibirRelatorioFuncionario() throws SQLException, JRException {

        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/petshop", "postgres", "glenda");
        InputStream fonte = ControleRelatorio.class.getResourceAsStream("/br/com/pbd/Relatorio/RelatorioVendas.jasper");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Data", diaRelatorio.getCalendario().getDate());
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fonte);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
        JasperViewer jrviewer = new JasperViewer(jasperPrint, false);
        jrviewer.setVisible(true);
        jrviewer.toFront();

    }
    
    

}
