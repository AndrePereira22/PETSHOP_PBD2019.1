/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.DaoAgenda;
import br.com.pbd.Dao.DaoContasApagar;
import br.com.pbd.Dao.DaoFinanceiro;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.AgendaProfissional;
import br.com.pbd.Modelo.Caixa;
import br.com.pbd.Modelo.ContaAPagar;
import br.com.pbd.Modelo.Loja;
import br.com.pbd.Modelo.Render;
import br.com.pbd.fachada.Fachada;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andre-Coude
 */
public class ControleFinanceiro implements ActionListener {

    private final Fachada fachada;
    private final TelaPrincipal tPrincipal;
    private Caixa caixa;
    private Loja loja;
    private List<AgendaProfissional> agendas;

    private List<ContaAPagar> contas;

    public ControleFinanceiro(TelaPrincipal tPrincipal,Fachada fachada) {
        this.tPrincipal = tPrincipal;
        this.fachada = fachada;
        this.abrirCaixa();
        tPrincipal.getcPagar().getBtnSalvarConta().addActionListener(this);
        tPrincipal.getFinancas().getBtnContaApagar().addActionListener(this);
        tPrincipal.getBtnFinanceiro().addActionListener(this);
        tPrincipal.getFinancas().getComboEntrada().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tPrincipal.getFinancas().getBtnContaApagar()) {
            buscarContas();
        }
        if (e.getSource() == tPrincipal.getcPagar().getBtnSalvarConta()) {
            salvarContaApagar();
            tPrincipal.getcPagar().limparComponentes(false);
        }
        if (e.getSource() == tPrincipal.getBtnFinanceiro()) {
            ListarTabela();
        }
        if (e.getSource() == tPrincipal.getFinancas().getComboEntrada()) {
            ListarTabela();
        }

    }

    public void abrirCaixa() {

        Date data = new Date(System.currentTimeMillis());
        Caixa caixaAnterior = null;
        try {
            caixaAnterior = fachada.buscaCaixa();
        } catch (NoResultException n) {
            System.out.println("Caixa anterior nao encontrado");
        }
        try {
            caixa = new DaoFinanceiro().buscarCaixa(data);
        } catch (NoResultException n) {
            System.out.println("Caixa do dia nao encontrado");
        }

        if (caixa == null) {
            caixa = new Caixa();
            caixa.setData(data);
            caixa.setStatus(Boolean.TRUE);
            caixa.setLoja(loja);
            if (caixaAnterior != null) {
                caixa.setValorabertura(caixaAnterior.getValorfechamento());
                caixa.setValorfechamento(caixaAnterior.getValorfechamento());
            } else {
                caixa.setValorabertura(0.0);
                caixa.setValorfechamento(0.0);
            }
            new GenericDao<Caixa>().salvar_ou_atualizar(caixa);

        } else {
            caixa.setStatus(Boolean.TRUE);
            new GenericDao<Caixa>().salvar_ou_atualizar(caixa);
        }

    }

    private void salvarContaApagar() {

        ContaAPagar contaPagar = new ContaAPagar();

        contaPagar.setDescricao(tPrincipal.getcPagar().getTxtDescricao().getText());

        Double valor = 0.0;
        contaPagar.setStatus(false);

        try {
            valor = Double.parseDouble(tPrincipal.getcPagar().getTxtValor().getText());
            java.sql.Date vencimento = ConverterData(tPrincipal.getcPagar().getVencimento().getDate());

            contaPagar.setData(vencimento);
            contaPagar.setValortotal(valor);

            new GenericDao<ContaAPagar>().salvar_ou_atualizar(contaPagar);
            JOptionPane.showMessageDialog(null, "Conta cadastrada!");
            buscarContas();

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    private void listarContasApagar(List<ContaAPagar> lista) {

        tPrincipal.getcPagar().getTabelaContasApagar().setDefaultRenderer(Object.class, new Render());

        int i = 0;
        try {
            String[] colunas = new String[]{"DESCRICAO", "VALOR", "DATA DE VENCIMENTO", "PAGAR "};
            Object[][] dados = new Object[lista.size()][4];
            for (ContaAPagar a : lista) {
                dados[i][0] = a.getDescricao();
                dados[i][1] = a.getValortotal();
                dados[i][2] = a.getData();
                dados[i][3] = tPrincipal.getGerencia().getBtnAdicionar();

                i++;
            }

            DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tPrincipal.getcPagar().getTabelaContasApagar().setModel(dataModel);
        } catch (Exception ex) {

        }
    }

    private void listarServicos(List<AgendaProfissional> lista) {

        tPrincipal.getFinancas().getTabelaVendas().setDefaultRenderer(Object.class, new Render());

        int i = 0;
        String status = "";
        try {
            String[] colunas = new String[]{"VALOR TOTAL", "PAGAMENTO", "CLIENTE", "ANIMAL", "PROFISSIONAL "};
            Object[][] dados = new Object[lista.size()][5];
            for (AgendaProfissional a : lista) {
                if (a.getPagamento().getStatus()) {
                    status = "PAGO";
                } else {
                    status = "PENDENTE";
                }

                dados[i][0] = a.getPagamento().getValortotal();
                dados[i][1] = status;
                dados[i][2] = a.getAnimal().getCliente().getNome();
                dados[i][3] = a.getAnimal().getNome();
                dados[i][4] = a.getProfissional().getNome();

                i++;
            }

            DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tPrincipal.getFinancas().getTabelaVendas().setModel(dataModel);
        } catch (Exception ex) {

        }
    }

    public void buscarContas() {

        contas = new DaoContasApagar().buscaContas();
        listarContasApagar(contas);
    }

    public java.sql.Date ConverterData(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public void ListarTabela() {
        String tipo = tPrincipal.getFinancas().getComboEntrada().getSelectedItem().toString();

        if (tipo.equals("SERVICOS")) {
            java.util.Date data = tPrincipal.getFinancas().getCalendario().getDate();
            agendas = new DaoAgenda().buscarAgendas(ConverterData(data));
            listarServicos(agendas);

        }
        if (tipo.equals("VENDAS")) {

        }
    }

}
