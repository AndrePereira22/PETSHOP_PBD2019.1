package br.com.pbd.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class TelaPrincipal extends javax.swing.JFrame implements ActionListener, KeyListener {

    private final CadastroAnimal cAnimal = new CadastroAnimal();
    private final Cadastros cadastros = new Cadastros();
    private final CadastroCliente cCliente = new CadastroCliente();
    private final CadastroFuncionario cFuncionario = new CadastroFuncionario();
    private final CadastroFornecedor cFornecedor = new CadastroFornecedor();
    private final CadastroProfissional cProfissioanl = new CadastroProfissional();
    private final CadastroServicos cServicos = new CadastroServicos();
    private final CadastroProdutos cProdutos = new CadastroProdutos();
    private final AgendarServico agenarServico = new AgendarServico();
    private final Servico_Produto servico_Produto = new Servico_Produto();
    private final CadastroLoja cLoja = new CadastroLoja();
    private final Raca_especie raca_especie = new Raca_especie();
    private final AgendaProfissional agenda = new AgendaProfissional();
    private final Vendas vendas = new Vendas();
    private final Gerencia gerencia = new Gerencia();
    private final Financeiro financas = new Financeiro();
    private final EntradaProdutos eProdutos = new EntradaProdutos();
    private final ContasAPagar cPagar = new ContasAPagar();
    private final Vacina vacina = new Vacina();
    private final ContasAreceber cReceber = new ContasAreceber();
    private final AgendaAnimal agendaAnimal = new AgendaAnimal();
    private final AgendarVacina aVacina = new AgendarVacina();

    private final CadastroGrupo cGrupo = new CadastroGrupo();
    private HashMap<Integer, Boolean> keyEventos;
    private int start;
    private final JButton btnExcluir, btnEditar, btnAdicionar,btnAgendaAnimal;
    private final Icon editar, excluir, adicionar,iconAgenda;
    private final String EDICAO, CAMPOS, SENHA, CADASTRO;

    public TelaPrincipal() {
        initComponents();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension tamTela = kit.getScreenSize();
        this.setSize(tamTela);
        this.setExtendedState(MAXIMIZED_BOTH);

        this.EDICAO = "EDIÇÃO FINALIZADA!";
        this.CAMPOS = "HÁ CAMPOS OBRIGATORIOS VAZIOS!";
        this.SENHA = "SENHAS DIFERENTES!";
        this.CADASTRO = "CADASTRO CONCLUIDO!";

        ajustarInternalFrame(cAnimal);
        ajustarInternalFrame(cadastros);
        ajustarInternalFrame(cCliente);
        ajustarInternalFrame(cFuncionario);
        ajustarInternalFrame(cFornecedor);
        ajustarInternalFrame(cProfissioanl);
        ajustarInternalFrame(cServicos);
        ajustarInternalFrame(cProdutos);
        ajustarInternalFrame(agenda);
        ajustarInternalFrame(financas);
        ajustarInternalFrame(vendas);
        ajustarInternalFrame(gerencia);
        ajustarInternalFrame(agenarServico);
        ajustarInternalFrame(servico_Produto);
        ajustarInternalFrame(raca_especie);
        ajustarInternalFrame(eProdutos);
        ajustarInternalFrame(cPagar);
        ajustarInternalFrame(cReceber);
        ajustarInternalFrame(cGrupo);
        ajustarInternalFrame(cLoja);
        ajustarInternalFrame(vacina);
        ajustarInternalFrame(agendaAnimal);
        ajustarInternalFrame(aVacina);

        start = 0;

        keyEventos = new HashMap<Integer, Boolean>();
        editar = new ImageIcon(getClass().getResource("/br/com/pbd/resource/edit.png"));
        excluir = new ImageIcon(getClass().getResource("/br/com/pbd/resource/lixo.png"));
        adicionar = new ImageIcon(getClass().getResource("/br/com/pbd/resource/edit.png"));
        iconAgenda = new ImageIcon(getClass().getResource("/br/com/pbd/resource/edit.png"));

        btnEditar = new JButton(editar);
        btnEditar.setName("editar");
        btnEditar.setBorder(null);
        btnEditar.setContentAreaFilled(false);

        btnExcluir = new JButton(excluir);
        btnExcluir.setName("excluir");
        btnExcluir.setBorder(null);
        btnExcluir.setContentAreaFilled(false);

        btnAdicionar = new JButton(adicionar);
        btnAdicionar.setName("adicionar");
        btnAdicionar.setBorder(null);
        btnAdicionar.setContentAreaFilled(false);
        
        btnAgendaAnimal = new JButton(iconAgenda);
        btnAgendaAnimal.setName("agenda");
        btnAgendaAnimal.setBorder(null);
        btnAgendaAnimal.setContentAreaFilled(false);

        adicionaEventos();

        this.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        painelMenu = new javax.swing.JPanel();
        btnClientes = new javax.swing.JButton();
        btnAgenda = new javax.swing.JButton();
        btnVendas = new javax.swing.JButton();
        btnGerencia = new javax.swing.JButton();
        btnCadastros = new javax.swing.JButton();
        btnProdutos_serv = new javax.swing.JButton();
        btnFinanceiro = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        paineArea = new javax.swing.JPanel();
        painelDesktop = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        painelMenu.setBackground(new java.awt.Color(255, 255, 255));
        painelMenu.setPreferredSize(new java.awt.Dimension(1024, 600));
        painelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Clínica Botão 001.png"))); // NOI18N
        btnClientes.setBorder(null);
        btnClientes.setBorderPainted(false);
        btnClientes.setContentAreaFilled(false);
        btnClientes.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Clínica Botão 001.1.png"))); // NOI18N
        btnClientes.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Clínica Botão 001.1.png"))); // NOI18N
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        painelMenu.add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 280, 75));

        btnAgenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Clínica Botão 002.png"))); // NOI18N
        btnAgenda.setBorder(null);
        btnAgenda.setBorderPainted(false);
        btnAgenda.setContentAreaFilled(false);
        btnAgenda.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Clínica Botão 002.1.png"))); // NOI18N
        btnAgenda.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Clínica Botão 002.1.png"))); // NOI18N
        btnAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendaActionPerformed(evt);
            }
        });
        painelMenu.add(btnAgenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 280, 75));

        btnVendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Clínica Botão 003.png"))); // NOI18N
        btnVendas.setBorder(null);
        btnVendas.setBorderPainted(false);
        btnVendas.setContentAreaFilled(false);
        btnVendas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Clínica Botão 003.1.png"))); // NOI18N
        btnVendas.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Clínica Botão 003.1.png"))); // NOI18N
        btnVendas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendasActionPerformed(evt);
            }
        });
        painelMenu.add(btnVendas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 280, 75));

        btnGerencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Gerencia  004.png"))); // NOI18N
        btnGerencia.setToolTipText("");
        btnGerencia.setBorder(null);
        btnGerencia.setBorderPainted(false);
        btnGerencia.setContentAreaFilled(false);
        btnGerencia.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Gerencia  004.1.png"))); // NOI18N
        btnGerencia.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Gerencia  004.1.png"))); // NOI18N
        btnGerencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciaActionPerformed(evt);
            }
        });
        painelMenu.add(btnGerencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 280, 75));

        btnCadastros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Clínica Botão 005.png"))); // NOI18N
        btnCadastros.setBorder(null);
        btnCadastros.setBorderPainted(false);
        btnCadastros.setContentAreaFilled(false);
        btnCadastros.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Clínica Botão 005.1.png"))); // NOI18N
        btnCadastros.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Clínica Botão 005.1.png"))); // NOI18N
        btnCadastros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrosActionPerformed(evt);
            }
        });
        painelMenu.add(btnCadastros, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 280, 75));

        btnProdutos_serv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Clínica Botão 006.png"))); // NOI18N
        btnProdutos_serv.setBorder(null);
        btnProdutos_serv.setBorderPainted(false);
        btnProdutos_serv.setContentAreaFilled(false);
        btnProdutos_serv.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Clínica Botão 006.1.png"))); // NOI18N
        btnProdutos_serv.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Clínica Botão 006.1.png"))); // NOI18N
        btnProdutos_serv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdutos_servActionPerformed(evt);
            }
        });
        painelMenu.add(btnProdutos_serv, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 280, 75));

        btnFinanceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Clínica Botão 007.png"))); // NOI18N
        btnFinanceiro.setBorder(null);
        btnFinanceiro.setBorderPainted(false);
        btnFinanceiro.setContentAreaFilled(false);
        btnFinanceiro.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Clínica Botão 007.1.png"))); // NOI18N
        btnFinanceiro.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/Layout App Clínica Botão 007.1.png"))); // NOI18N
        btnFinanceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinanceiroActionPerformed(evt);
            }
        });
        painelMenu.add(btnFinanceiro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, 280, 75));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/exit.png"))); // NOI18N
        btnSair.setAlignmentX(20.0F);
        btnSair.setAlignmentY(30.0F);
        btnSair.setBorderPainted(false);
        btnSair.setContentAreaFilled(false);
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        painelMenu.add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 480, 120, 70));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/proIcon.png"))); // NOI18N
        jLabel2.setText("USER");
        painelMenu.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 300, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/fundoo1.png"))); // NOI18N
        jLabel1.setToolTipText("");
        painelMenu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 680));

        paineArea.setBackground(new java.awt.Color(255, 255, 255));
        paineArea.setPreferredSize(new java.awt.Dimension(1024, 600));
        paineArea.setLayout(null);

        painelDesktop.setPreferredSize(new java.awt.Dimension(1024, 600));

        javax.swing.GroupLayout painelDesktopLayout = new javax.swing.GroupLayout(painelDesktop);
        painelDesktop.setLayout(painelDesktopLayout);
        painelDesktopLayout.setHorizontalGroup(
            painelDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1410, Short.MAX_VALUE)
        );
        painelDesktopLayout.setVerticalGroup(
            painelDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );

        paineArea.add(painelDesktop);
        painelDesktop.setBounds(-10, 0, 1410, 680);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(painelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 1410, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(paineArea, javax.swing.GroupLayout.PREFERRED_SIZE, 1420, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(painelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(paineArea, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1401, 680);
    }// </editor-fold>//GEN-END:initComponents

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        // TODO add your handling code here:
        getPainelMenu().setVisible(false);
        cCliente.setVisible(true);
    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnVendasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendasActionPerformed
        // TODO add your handling code here:
        getPainelMenu().setVisible(false);
        getVendas().setVisible(true);

    }//GEN-LAST:event_btnVendasActionPerformed

    private void btnCadastrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrosActionPerformed
        // TODO add your handling code here:
        getPainelMenu().setVisible(false);
        getCadastros().setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_btnCadastrosActionPerformed

    private void btnAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendaActionPerformed

        getPainelMenu().setVisible(false);
        getAgenda().setVisible(rootPaneCheckingEnabled);


    }//GEN-LAST:event_btnAgendaActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed

        // System.exit(WIDTH);
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnProdutos_servActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdutos_servActionPerformed
        getPainelMenu().setVisible(false);
        getServico_Produto().setVisible(true);
    }//GEN-LAST:event_btnProdutos_servActionPerformed

    private void btnFinanceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinanceiroActionPerformed
        getPainelMenu().setVisible(false);
        getFinancas().setVisible(true);
    }//GEN-LAST:event_btnFinanceiroActionPerformed

    private void btnGerenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciaActionPerformed
        getPainelMenu().setVisible(false);
        getGerencia().setVisible(true);
    }//GEN-LAST:event_btnGerenciaActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgenda;
    private javax.swing.JButton btnCadastros;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnFinanceiro;
    private javax.swing.JButton btnGerencia;
    private javax.swing.JButton btnProdutos_serv;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnVendas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel paineArea;
    private javax.swing.JDesktopPane painelDesktop;
    private javax.swing.JPanel painelMenu;
    // End of variables declaration//GEN-END:variables

    public void ativarBotoesProfissional() {
        getBtnVendas().setEnabled(false);
        getBtnVendas().setSelected(false);
        getBtnClientes().setEnabled(false);
        getBtnClientes().setSelected(false);
        getBtnGerencia().setEnabled(false);
        getBtnGerencia().setSelected(false);
        getBtnFinanceiro().setEnabled(false);
        getBtnFinanceiro().setSelected(false);
        getBtnCadastros().setEnabled(false);
        getBtnCadastros().setSelected(false);
        getBtnProdutos_serv().setEnabled(false);
        getBtnProdutos_serv().setSelected(false);
        getBtnAgenda().setEnabled(true);
        getBtnAgenda().setSelected(false);
        setVisible(true);
    }

    public void ativarBotoesAdm() {
        getBtnVendas().setEnabled(true);
        getBtnVendas().setSelected(false);
        getBtnClientes().setEnabled(true);
        getBtnClientes().setSelected(false);
        getBtnGerencia().setEnabled(true);
        getBtnGerencia().setSelected(false);
        getBtnFinanceiro().setEnabled(true);
        getBtnFinanceiro().setSelected(false);
        getBtnCadastros().setEnabled(true);
        getBtnCadastros().setSelected(false);
        getBtnProdutos_serv().setEnabled(true);
        getBtnProdutos_serv().setSelected(false);
        getBtnAgenda().setEnabled(true);
        getBtnAgenda().setSelected(false);
        setVisible(true);
    }

    public void ativarBotoesFuncionario() {
        getBtnVendas().setEnabled(true);
        getBtnVendas().setSelected(false);
        getBtnClientes().setEnabled(true);
        getBtnClientes().setSelected(false);
        getBtnGerencia().setEnabled(false);
        getBtnGerencia().setSelected(false);
        getBtnFinanceiro().setEnabled(true);
        getBtnFinanceiro().setSelected(false);
        getBtnCadastros().setEnabled(true);
        getBtnCadastros().setSelected(false);
        getBtnProdutos_serv().setEnabled(true);
        getBtnProdutos_serv().setSelected(false);
        getBtnAgenda().setEnabled(true);
        getBtnAgenda().setSelected(false);
        setVisible(true);
    }

    public void ativarEdicaoLogin(Boolean status) {
        getcFuncionario().getPainelLogin().setVisible(status);
        getcProfissioanl().getPainelLogin().setVisible(status);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Eventos da tela Servico e produto
        if (e.getSource() == getServico_Produto().getBtnNovoProduto()) {
            getServico_Produto().setVisible(false);
            getcProdutos().setVisible(true);
        }
        if (e.getSource() == getServico_Produto().getBtnNovoServico()) {
            getServico_Produto().setVisible(false);
            getcServicos().setVisible(true);
        }
        if (e.getSource() == getServico_Produto().getBtnSair()) {
            getServico_Produto().setVisible(false);
            painelMenu.setVisible(true);
            btnProdutos_serv.requestFocus();
            repaint();

        }
        if (e.getSource() == getServico_Produto().getBtnSairServico()) {
            getServico_Produto().setVisible(false);
            painelMenu.setVisible(true);
            repaint();
        }
        // Eventos da tela Cadastros
        if (e.getSource() == getCadastros().getBtnProfissional()) {
            getcProfissioanl().setVisible(true);
        }
        if (e.getSource() == getCadastros().getBtnFornecedor()) {
            getcFornecedor().setVisible(true);
        }
        if (e.getSource() == getCadastros().getBtnAnimal1()) {
            getcAnimal().setVisible(true);
        }

        if (e.getSource() == getCadastros().getBtnFuncionario()) {
            getcFuncionario().setVisible(true);
        }
        if (e.getSource() == getCadastros().getBtnVacinas()) {
            getVacina().setVisible(true);
        }
        if (e.getSource() == getCadastros().getVoltarMenu()) {
            painelMenu.setVisible(true);
            btnCadastros.requestFocus();
            repaint();

        }
        if (e.getSource() == getCadastros().getBtnEspecie()) {
            getRaca_especie().setVisible(true);
            getRaca_especie().getPainelItens().setSelectedComponent(getRaca_especie().getPainelEspecie());
        }
        if (e.getSource() == getCadastros().getBtnRaca()) {
            getRaca_especie().setVisible(true);
            getRaca_especie().getPainelItens().setSelectedComponent(getRaca_especie().getPainelRaca());
        }
        if (e.getSource() == getCadastros().getBtnGrupo()) {
            cGrupo.setVisible(true);
        }

        /////////////////////////////////////////////////////
        if (e.getSource() == getAgenda().getBtnSair()) {
            getAgenda().setVisible(false);
            painelMenu.setVisible(true);
            btnAgenda.requestFocus();
            repaint();
        }
        if (e.getSource() == cServicos.getBtnCancelar()) {
            servico_Produto.setVisible(true);
        }

        if (e.getSource() == getcProdutos().getBtnCancelar()) {
            servico_Produto.setVisible(true);
        }

// Eventos da tela Cadastro Animal
        if (e.getSource() == getcAnimal().getBtnSair()) {

            getcAnimal().setVisible(false);
            cadastros.setVisible(true);
        }
        if (e.getSource() == getcAnimal().getBtnCadastrarAnimal()) {
            getcAnimal().getPainelItens().setSelectedComponent(getcAnimal().getPainelCadastro());
            getcAnimal().getPainelCadastro().setEnabled(true);
        }
        if (e.getSource() == getcAnimal().getBtnCancelar()) {
            getcAnimal().getPainelItens().setSelectedComponent(getcAnimal().getPainelAnimail());
            getcAnimal().getPainelCadastro().setEnabled(false);
        }
        // Eventos da tela Cadastro de Cliente
        if (e.getSource() == getcCliente().getBtnCancelar()) {
            getcCliente().getPainelItens().setSelectedComponent(getcCliente().getPainelCliente());
            getcCliente().getPainelCadastro().setEnabled(false);
        }
        if (e.getSource() == getcCliente().getBtnCadastrarCliente()) {
            getcCliente().getPainelItens().setSelectedComponent(getcCliente().getPainelCadastro());
            getcCliente().getPainelCadastro().setEnabled(true);
        }
        if (e.getSource() == getcCliente().getBtnSair()) {
            getcCliente().setVisible(false);
            painelMenu.setVisible(true);
            btnClientes.requestFocus();
            repaint();

        }
        // Eventos da tela Cadastro de Fornecedor
        if (e.getSource() == getcFornecedor().getBtnCancelar()) {
            getcFornecedor().getPainelItens().setSelectedComponent(getcFornecedor().getPainelFornecedor());
            getcFornecedor().getPainelCadastro().setEnabled(false);
        }
        if (e.getSource() == getcFornecedor().getBtnNovoFornecedor()) {
            getcFornecedor().getPainelItens().setSelectedComponent(getcFornecedor().getPainelCadastro());
            getcFornecedor().getPainelCadastro().setEnabled(true);
        }
        if (e.getSource() == getcFornecedor().getBtnSair()) {
            getcFornecedor().setVisible(false);
            getCadastros().setVisible(true);
        }
        // Eventos da tela Cadastro de Funcionario
        if (e.getSource() == getcFuncionario().getBtnCancelar()) {
            getcFuncionario().getPainelItens().setSelectedComponent(getcFuncionario().getPainelFuncionario());
            getcFuncionario().getPainelCadastro().setEnabled(false);
        }
        if (e.getSource() == getcFuncionario().getBtnNovoFuncionario()) {
            getcFuncionario().getPainelItens().setSelectedComponent(getcFuncionario().getPainelCadastro());
            getcFuncionario().getPainelCadastro().setEnabled(true);
        }
        if (e.getSource() == getcFuncionario().getBtnSair()) {
            getcFuncionario().setVisible(false);
            getCadastros().setVisible(true);
        }

        // Eventos da tela Cadastro de Profissional
        if (e.getSource() == getcProfissioanl().getBtnCancelar()) {
            getcProfissioanl().getPainelItens().setSelectedComponent(getcProfissioanl().getPainelProfissional());
            getcProfissioanl().getPainelCadastro().setEnabled(false);
        }
        if (e.getSource() == getcProfissioanl().getBtnNovoProfissional()) {
            getcProfissioanl().getPainelItens().setSelectedComponent(getcProfissioanl().getPainelCadastro());
            getcProfissioanl().getPainelCadastro().setEnabled(true);
        }
        if (e.getSource() == getcProfissioanl().getBtnSair()) {
            getcProfissioanl().setVisible(false);
            getCadastros().setVisible(true);
        }

        // Eventos da tela Raça e Especie
        if (e.getSource() == getRaca_especie().getBtnNovaEspecie()) {
            getRaca_especie().AtivarComponenteEspecie(true);
            getRaca_especie().getPainelItens().setEnabled(false);

        }
        if (e.getSource() == getRaca_especie().getBtnNovaRaca()) {
            getRaca_especie().AtivarComponenteRaca(true);
            getRaca_especie().getPainelItens().setEnabled(false);

        }
        if (e.getSource() == getRaca_especie().getBtnCancelarRaca()) {
            getRaca_especie().getPainelItens().setEnabled(true);

        }
        if (e.getSource() == getRaca_especie().getBtnCancelarEspecie()) {
            getRaca_especie().getPainelItens().setEnabled(true);

        }

        if (e.getSource() == getVendas().getBtnSair()) {
            getVendas().setVisible(false);
            painelMenu.setVisible(true);
            btnVendas.requestFocus();
            repaint();
        }

        if (e.getSource() == cGrupo.getBtnSair()) {
            cGrupo.setVisible(false);
            cadastros.setVisible(true);
        }

        if (e.getSource() == getGerencia().getBtnEditar()) {
            getcLoja().setVisible(true);
        }
        if (e.getSource() == getGerencia().getBtnSair()) {
            getGerencia().setVisible(false);

            painelMenu.setVisible(true);
            btnGerencia.requestFocus();
            repaint();
        }
        if (e.getSource() == getFinancas().getBtnContaApagar()) {

            getFinancas().setVisible(false);
            getcPagar().setVisible(true);
        }
        if (e.getSource() == getFinancas().getBtnContasAreceber()) {

            getFinancas().setVisible(false);
            getcReceber().setVisible(true);
        }
        if (e.getSource() == getFinancas().getBtnSair()) {

            getFinancas().setVisible(false);
            painelMenu.setVisible(true);
            btnFinanceiro.requestFocus();
            repaint();
        }
        if (e.getSource() == geteProdutos().getBtnCancelar()) {

            geteProdutos().setVisible(false);
            getGerencia().setVisible(true);
        }
        if (e.getSource() == getVacina().getBtnVoltar()) {

            getVacina().setVisible(false);
            getCadastros().setVisible(true);
        }
        if (e.getSource() ==  getAgendaAnimal().getBtnSair()) {
            getAgendaAnimal().setVisible(false);
            getcAnimal().setVisible(true);
            
        }
         if (e.getSource() ==  getAgendaAnimal().getBtnAdicionar()) {
            getAgendaAnimal().setVisible(false);
            getaVacina().setVisible(true);
            
        }
          if (e.getSource() ==  getaVacina().getBtnCancelar()) {
           getaVacina().setVisible(false);
            getAgendaAnimal().setVisible(true);
            
        }
        
       

    }

    public final void adicionaEventos() {
        getCadastros().getVoltarMenu().addActionListener(this);
        getCadastros().getBtnFuncionario().addActionListener(this);
        getCadastros().getBtnAnimal1().addActionListener(this);
        getCadastros().getBtnFornecedor().addActionListener(this);
        getCadastros().getBtnProfissional().addActionListener(this);
        getCadastros().getBtnEspecie().addActionListener(this);
        getCadastros().getBtnRaca().addActionListener(this);
        getCadastros().getBtnGrupo().addActionListener(this);
        getCadastros().getBtnVacinas().addActionListener(this);

        getAgenda().getBtnSair().addActionListener(this);
        getcProdutos().getBtnCancelar().addActionListener(this);
        cServicos.getBtnCancelar().addActionListener(this);
        cGrupo.getBtnSair().addActionListener(this);
        getVacina().getBtnVoltar().addActionListener(this);

        getServico_Produto().getBtnNovoServico().addActionListener(this);
        getServico_Produto().getBtnNovoProduto().addActionListener(this);
        getServico_Produto().getBtnSair().addActionListener(this);
        getServico_Produto().getBtnSairServico().addActionListener(this);

        getcCliente().getBtnCadastrarCliente().addActionListener(this);
        getcCliente().getBtnCancelar().addActionListener(this);
        getcCliente().getBtnSair().addActionListener(this);

        getcAnimal().getBtnSair().addActionListener(this);
        getcAnimal().getBtnCancelar().addActionListener(this);
        getcAnimal().getBtnCadastrarAnimal().addActionListener(this);

        getcFornecedor().getBtnSair().addActionListener(this);
        getcFornecedor().getBtnCancelar().addActionListener(this);
        getcFornecedor().getBtnNovoFornecedor().addActionListener(this);

        getcFuncionario().getBtnSair().addActionListener(this);
        getcFuncionario().getBtnCancelar().addActionListener(this);
        getcFuncionario().getBtnNovoFuncionario().addActionListener(this);

        getcProfissioanl().getBtnSair().addActionListener(this);
        getcProfissioanl().getBtnCancelar().addActionListener(this);
        getcProfissioanl().getBtnNovoProfissional().addActionListener(this);

        getRaca_especie().getBtnNovaEspecie().addActionListener(this);
        getRaca_especie().getBtnNovaRaca().addActionListener(this);
        getRaca_especie().getBtnCancelarRaca().addActionListener(this);
        getRaca_especie().getBtnCancelarEspecie().addActionListener(this);

        getVendas().getBtnProdutos().addActionListener(this);
        getVendas().getBtnClientes().addActionListener(this);
        getVendas().getBtnSair().addActionListener(this);
        getGerencia().getBtnEditar().addActionListener(this);
        getGerencia().getBtnSair().addActionListener(this);
        getFinancas().getBtnContaApagar().addActionListener(this);
        getFinancas().getBtnContasAreceber().addActionListener(this);
        getFinancas().getBtnSair().addActionListener(this);
        geteProdutos().getBtnCancelar().addActionListener(this);
        
        getAgendaAnimal().getBtnSair().addActionListener(this);
         getAgendaAnimal().getBtnAdicionar().addActionListener(this);
         getaVacina().getBtnCancelar().addActionListener(this);

        btnAgenda.addKeyListener(this);
        btnClientes.addKeyListener(this);
        btnCadastros.addKeyListener(this);
        btnVendas.addKeyListener(this);
        btnGerencia.addKeyListener(this);
        btnProdutos_serv.addKeyListener(this);
        btnFinanceiro.addKeyListener(this);
        getBtnGerencia().addKeyListener(this);

    }

    public final void ajustarInternalFrame(JInternalFrame frame) {

        ((BasicInternalFrameUI) frame.getUI()).setNorthPane(null);
        frame.setBorder(null);
        painelDesktop.add(frame);
    }

    public final void mudarVisaoData(Date d) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        getAgenda().getLblDatal().setText(formato.format(d));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyEventos.put(e.getKeyCode(), true);

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (btnFinanceiro.isSelected()) {

                painelMenu.setVisible(false);
                financas.setVisible(true);

            } else if (btnProdutos_serv.isSelected()) {
                painelMenu.setVisible(false);
                servico_Produto.setVisible(true);

            } else if (btnCadastros.isSelected()) {
                painelMenu.setVisible(false);
                cadastros.setVisible(true);

            } else if (btnGerencia.isSelected() && btnGerencia.isEnabled()) {
                painelMenu.setVisible(false);
                gerencia.setVisible(true);

            } else if (btnVendas.isSelected() && btnVendas.isEnabled()) {
                painelMenu.setVisible(false);
                vendas.setVisible(true);

            } else if (btnAgenda.isSelected()) {
                painelMenu.setVisible(false);
                agenda.setVisible(true);

            } else if (btnClientes.isSelected()) {

                painelMenu.setVisible(false);
                cCliente.setVisible(true);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {

            if (btnFinanceiro.isSelected()) {
                btnFinanceiro.setSelected(false);
                btnProdutos_serv.setSelected(true);

            } else if (btnProdutos_serv.isSelected()) {
                btnProdutos_serv.setSelected(false);
                btnCadastros.setSelected(true);

            } else if (btnCadastros.isSelected()) {
                btnCadastros.setSelected(false);
                getBtnGerencia().setSelected(true);

            } else if (getBtnGerencia().isSelected()) {
                getBtnGerencia().setSelected(false);
                btnVendas.setSelected(true);

            } else if (btnVendas.isSelected()) {
                btnVendas.setSelected(false);
                btnAgenda.setSelected(true);

            } else if (btnAgenda.isSelected()) {
                btnAgenda.setSelected(false);
                btnClientes.setSelected(true);

            } else if (btnClientes.isSelected()) {
                btnClientes.setSelected(false);
                btnFinanceiro.setSelected(true);

            }

        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {

            if (btnClientes.isSelected()) {
                btnClientes.setSelected(false);
                btnAgenda.setSelected(true);

            } else if (btnAgenda.isSelected()) {
                btnAgenda.setSelected(false);
                btnVendas.setSelected(true);

            } else if (btnVendas.isSelected()) {
                btnVendas.setSelected(false);
                getBtnGerencia().setSelected(true);

            } else if (getBtnGerencia().isSelected()) {
                getBtnGerencia().setSelected(false);
                btnCadastros.setSelected(true);

            } else if (btnCadastros.isSelected()) {
                btnCadastros.setSelected(false);
                btnProdutos_serv.setSelected(true);

            } else if (btnProdutos_serv.isSelected()) {
                btnProdutos_serv.setSelected(false);
                btnFinanceiro.setSelected(true);

            } else if (btnFinanceiro.isSelected()) {
                btnFinanceiro.setSelected(false);
                btnClientes.setSelected(true);

            }
            if (start == 0) {
                btnClientes.setSelected(true);
                start++;
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e
    ) {
        keyEventos.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e
    ) {
    }

    /**
     * @return the cAnimal
     */
    public CadastroAnimal getcAnimal() {
        return cAnimal;
    }

    /**
     * @return the cadastros
     */
    public Cadastros getCadastros() {
        return cadastros;
    }

    /**
     * @return the cCliente
     */
    public CadastroCliente getcCliente() {
        return cCliente;
    }

    /**
     * @return the cFuncionario
     */
    public CadastroFuncionario getcFuncionario() {
        return cFuncionario;
    }

    /**
     * @return the cFornecedor
     */
    public CadastroFornecedor getcFornecedor() {
        return cFornecedor;
    }

    /**
     * @return the cProfissioanl
     */
    public CadastroProfissional getcProfissioanl() {
        return cProfissioanl;
    }

    /**
     * @return the cServicos
     */
    public CadastroServicos getcServicos() {
        return cServicos;
    }

    /**
     * @return the cProdutos
     */
    public CadastroProdutos getcProdutos() {
        return cProdutos;
    }

    /**
     * @return the agenarServico
     */
    public AgendarServico getAgenarServico() {
        return agenarServico;
    }

    /**
     * @return the servico_Produto
     */
    public Servico_Produto getServico_Produto() {
        return servico_Produto;
    }

    /**
     * @return the agenda
     */
    public AgendaProfissional getAgenda() {
        return agenda;
    }

    /**
     * @return the vendas
     */
    public Vendas getVendas() {
        return vendas;
    }

    /**
     * @return the financas
     */
    public Financeiro getFinancas() {
        return financas;
    }

    /**
     * @return the btnAgenda
     */
    public javax.swing.JButton getBtnAgenda() {
        return btnAgenda;
    }

    /**
     * @return the btnCadastros
     */
    public javax.swing.JButton getBtnCadastros() {
        return btnCadastros;
    }

    /**
     * @return the btnClientes
     */
    public javax.swing.JButton getBtnClientes() {
        return btnClientes;
    }

    /**
     * @return the btnFinanceiro
     */
    public javax.swing.JButton getBtnFinanceiro() {
        return btnFinanceiro;
    }

    /**
     * @return the btnProdutos_serv
     */
    public javax.swing.JButton getBtnProdutos_serv() {
        return btnProdutos_serv;
    }

    /**
     * @return the btnSair
     */
    public javax.swing.JButton getBtnSair() {
        return btnSair;
    }

    /**
     * @return the btnVendas
     */
    public javax.swing.JButton getBtnVendas() {
        return btnVendas;
    }

    /**
     * @return the painelMenu
     */
    public javax.swing.JPanel getPainelMenu() {
        return painelMenu;
    }

    /**
     * @return the raca_especie
     */
    public Raca_especie getRaca_especie() {
        return raca_especie;
    }

    /**
     * @return the cGrupo
     */
    public CadastroGrupo getcGrupo() {
        return cGrupo;
    }

    /**
     * @return the cLoja
     */
    public CadastroLoja getcLoja() {
        return cLoja;
    }

    /**
     * @return the btnGerencia
     */
    public javax.swing.JButton getBtnGerencia() {
        return btnGerencia;
    }

    /**
     * @return the jLabel2
     */
    public javax.swing.JLabel getjLabel2() {
        return jLabel2;
    }

    /**
     * @return the gerencia
     */
    public Gerencia getGerencia() {
        return gerencia;
    }

    /**
     * @return the btnExcluir
     */
    public JButton getBtnExcluir() {
        return btnExcluir;
    }

    /**
     * @return the btnEditar
     */
    public JButton getBtnEditar() {
        return btnEditar;
    }

    /**
     * @return the eProdutos
     */
    public EntradaProdutos geteProdutos() {
        return eProdutos;
    }

    /**
     * @return the cReceber
     */
    public ContasAreceber getcReceber() {
        return cReceber;
    }

    /**
     * @return the EDICAO
     */
    public String getEDICAO() {
        return EDICAO;
    }

    /**
     * @return the CAMPOS
     */
    public String getCAMPOS() {
        return CAMPOS;
    }

    /**
     * @return the SENHA
     */
    public String getSENHA() {
        return SENHA;
    }

    /**
     * @return the btnAdicionar
     */
    public JButton getBtnAdicionar() {
        return btnAdicionar;
    }

    /**
     * @return the CADASTRO
     */
    public String getCADASTRO() {
        return CADASTRO;
    }

    /**
     * @return the cPagar
     */
    public ContasAPagar getcPagar() {
        return cPagar;
    }

    /**
     * @return the vacina
     */
    public Vacina getVacina() {
        return vacina;
    }

    /**
     * @return the agendaAnimal
     */
    public AgendaAnimal getAgendaAnimal() {
        return agendaAnimal;
    }

    /**
     * @return the btnAgendaAnimal
     */
    public JButton getBtnAgendaAnimal() {
        return btnAgendaAnimal;
    }

    /**
     * @return the aVacina
     */
    public AgendarVacina getaVacina() {
        return aVacina;
    }

}
