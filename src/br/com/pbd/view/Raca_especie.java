/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.view;

/**
 *
 * @author Glenda Alves de Lima
 */
public class Raca_especie extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaCadastroCliente
     */
    public Raca_especie() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        painelItens = new javax.swing.JTabbedPane();
        painelRaca = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaRacas = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        painelNC = new javax.swing.JPanel();
        painelCadastroRaca = new javax.swing.JPanel();
        btnSavlarRaca = new javax.swing.JButton();
        btnCancelarRaca = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtRaca = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAreaRaca = new javax.swing.JTextArea();
        comboEspecie = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnNovaRaca = new javax.swing.JButton();
        btnSairR = new javax.swing.JButton();
        painelEspecie = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaEspecie = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        painelCadastroEspecie = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtEspecie = new javax.swing.JTextField();
        btnSalvarEspecie = new javax.swing.JButton();
        btnCancelarEspecie = new javax.swing.JButton();
        btnNovaEspecie = new javax.swing.JButton();
        btnSairE = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1024, 600));

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(1024, 600));

        painelItens.setBackground(new java.awt.Color(255, 255, 255));
        painelItens.setFont(new java.awt.Font("Californian FB", 0, 18)); // NOI18N
        painelItens.setPreferredSize(new java.awt.Dimension(900, 481));

        painelRaca.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tabelaRacas.setBackground(new java.awt.Color(204, 204, 255));
        tabelaRacas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        tabelaRacas.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        tabelaRacas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "DESCRIÇÃO", "NOME", "ESPECIE"
            }
        ));
        tabelaRacas.setRowHeight(40);
        jScrollPane1.setViewportView(tabelaRacas);

        jButton1.setText("OK");

        jLabel1.setText("PESQUISAR :");

        painelNC.setBorder(javax.swing.BorderFactory.createTitledBorder("Novo Cadastro"));
        painelNC.setMinimumSize(new java.awt.Dimension(365, 334));

        painelCadastroRaca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnSavlarRaca.setText("SALVAR");
        btnSavlarRaca.setEnabled(false);

        btnCancelarRaca.setText("CANCELAR");
        btnCancelarRaca.setEnabled(false);
        btnCancelarRaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarRacaActionPerformed(evt);
            }
        });

        jLabel4.setText("NOME :");

        txtRaca.setEnabled(false);

        jLabel5.setText("DESCRIÇÃO :");

        txtAreaRaca.setColumns(20);
        txtAreaRaca.setRows(5);
        txtAreaRaca.setEnabled(false);
        jScrollPane3.setViewportView(txtAreaRaca);

        comboEspecie.setEnabled(false);
        comboEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEspecieActionPerformed(evt);
            }
        });

        jLabel6.setText("ESPECIE :");

        javax.swing.GroupLayout painelCadastroRacaLayout = new javax.swing.GroupLayout(painelCadastroRaca);
        painelCadastroRaca.setLayout(painelCadastroRacaLayout);
        painelCadastroRacaLayout.setHorizontalGroup(
            painelCadastroRacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCadastroRacaLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(btnSavlarRaca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelarRaca)
                .addGap(58, 58, 58))
            .addGroup(painelCadastroRacaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(painelCadastroRacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRaca, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        painelCadastroRacaLayout.setVerticalGroup(
            painelCadastroRacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelCadastroRacaLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(painelCadastroRacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSavlarRaca)
                    .addComponent(btnCancelarRaca))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelNCLayout = new javax.swing.GroupLayout(painelNC);
        painelNC.setLayout(painelNCLayout);
        painelNCLayout.setHorizontalGroup(
            painelNCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelNCLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelCadastroRaca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        painelNCLayout.setVerticalGroup(
            painelNCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelNCLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(painelCadastroRaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        btnNovaRaca.setText("NOVA RAÇA");

        btnSairR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/voltar.png"))); // NOI18N
        btnSairR.setText("SAIR");
        btnSairR.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/voltar.1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelNC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnNovaRaca, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(btnSairR)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(painelNC, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNovaRaca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSairR)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout painelRacaLayout = new javax.swing.GroupLayout(painelRaca);
        painelRaca.setLayout(painelRacaLayout);
        painelRacaLayout.setHorizontalGroup(
            painelRacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelRacaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        painelRacaLayout.setVerticalGroup(
            painelRacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelRacaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        painelItens.addTab("RAÇAS", painelRaca);

        painelEspecie.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tabelaEspecie.setBackground(new java.awt.Color(204, 204, 255));
        tabelaEspecie.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        tabelaEspecie.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        tabelaEspecie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "NOME"
            }
        ));
        tabelaEspecie.setRowHeight(40);
        jScrollPane2.setViewportView(tabelaEspecie);

        jButton2.setText("OK");

        jLabel2.setText("PESQUISAR :");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Novo Cadastro"));
        jPanel3.setMinimumSize(new java.awt.Dimension(340, 303));

        painelCadastroEspecie.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("NOME DA ESPECIE");

        txtEspecie.setEnabled(false);

        btnSalvarEspecie.setText("SALVAR");
        btnSalvarEspecie.setEnabled(false);

        btnCancelarEspecie.setText("CANCELAR");
        btnCancelarEspecie.setEnabled(false);
        btnCancelarEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarEspecieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout painelCadastroEspecieLayout = new javax.swing.GroupLayout(painelCadastroEspecie);
        painelCadastroEspecie.setLayout(painelCadastroEspecieLayout);
        painelCadastroEspecieLayout.setHorizontalGroup(
            painelCadastroEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCadastroEspecieLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(painelCadastroEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addGroup(painelCadastroEspecieLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(btnSalvarEspecie)
                        .addGap(41, 41, 41)
                        .addComponent(btnCancelarEspecie))
                    .addComponent(txtEspecie))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        painelCadastroEspecieLayout.setVerticalGroup(
            painelCadastroEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCadastroEspecieLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addGroup(painelCadastroEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarEspecie)
                    .addComponent(btnCancelarEspecie))
                .addGap(63, 63, 63))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelCadastroEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelCadastroEspecie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnNovaEspecie.setText("NOVA ESPECIE");

        btnSairE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/voltar.png"))); // NOI18N
        btnSairE.setText("SAIR");
        btnSairE.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/voltar.1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnNovaEspecie)
                        .addGap(99, 99, 99)
                        .addComponent(btnSairE)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnNovaEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSairE))
                        .addGap(7, 7, 7))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(59, 59, 59))
        );

        javax.swing.GroupLayout painelEspecieLayout = new javax.swing.GroupLayout(painelEspecie);
        painelEspecie.setLayout(painelEspecieLayout);
        painelEspecieLayout.setHorizontalGroup(
            painelEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEspecieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        painelEspecieLayout.setVerticalGroup(
            painelEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelEspecieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        painelItens.addTab("ESPECIES", painelEspecie);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(painelItens, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(painelItens, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1016, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEspecieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEspecieActionPerformed

    private void btnCancelarEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarEspecieActionPerformed
        // TODO add your handling code here:
        AtivarComponenteEspecie(false);
    }//GEN-LAST:event_btnCancelarEspecieActionPerformed

    private void btnCancelarRacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarRacaActionPerformed
        // TODO add your handling code here:
        AtivarComponenteRaca(false);
    }//GEN-LAST:event_btnCancelarRacaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelarEspecie;
    private javax.swing.JButton btnCancelarRaca;
    private javax.swing.JButton btnNovaEspecie;
    private javax.swing.JButton btnNovaRaca;
    private javax.swing.JButton btnSairE;
    private javax.swing.JButton btnSairR;
    private javax.swing.JButton btnSalvarEspecie;
    private javax.swing.JButton btnSavlarRaca;
    private javax.swing.JComboBox<String> comboEspecie;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel painelCadastroEspecie;
    private javax.swing.JPanel painelCadastroRaca;
    private javax.swing.JPanel painelEspecie;
    private javax.swing.JTabbedPane painelItens;
    private javax.swing.JPanel painelNC;
    private javax.swing.JPanel painelRaca;
    private javax.swing.JTable tabelaEspecie;
    private javax.swing.JTable tabelaRacas;
    private javax.swing.JTextArea txtAreaRaca;
    private javax.swing.JTextField txtEspecie;
    private javax.swing.JTextField txtRaca;
    // End of variables declaration//GEN-END:variables

    
  
  public void AtivarComponenteRaca(Boolean status){
        getTxtAreaRaca().setEnabled(status);
        getTxtRaca().setEnabled(status);
        getComboEspecie().setEnabled(status);
        getBtnCancelarRaca().setEnabled(status);
        getBtnSavlarRaca().setEnabled(status);
  }
 public void AtivarComponenteEspecie(Boolean status){
        getTxtEspecie().setEnabled(status);
        getBtnSalvarEspecie().setEnabled(status);
        getBtnCancelarEspecie().setEnabled(status);
  }    

    /**
     * @return the btnCancelarEspecie
     */
    public javax.swing.JButton getBtnCancelarEspecie() {
        return btnCancelarEspecie;
    }

    /**
     * @return the btnCancelarRaca
     */
    public javax.swing.JButton getBtnCancelarRaca() {
        return btnCancelarRaca;
    }

    /**
     * @return the btnNovaEspecie
     */
    public javax.swing.JButton getBtnNovaEspecie() {
        return btnNovaEspecie;
    }

    /**
     * @return the btnNovaRaca
     */
    public javax.swing.JButton getBtnNovaRaca() {
        return btnNovaRaca;
    }

    /**
     * @return the btnSairE
     */
    public javax.swing.JButton getBtnSairE() {
        return btnSairE;
    }

    /**
     * @return the btnSairR
     */
    public javax.swing.JButton getBtnSairR() {
        return btnSairR;
    }

    /**
     * @return the btnSalvarEspecie
     */
    public javax.swing.JButton getBtnSalvarEspecie() {
        return btnSalvarEspecie;
    }

    /**
     * @return the btnSavlarRaca
     */
    public javax.swing.JButton getBtnSavlarRaca() {
        return btnSavlarRaca;
    }

    /**
     * @return the comboEspecie
     */
    public javax.swing.JComboBox<String> getComboEspecie() {
        return comboEspecie;
    }

    /**
     * @return the painelCadastroEspecie
     */
    public javax.swing.JPanel getPainelCadastroEspecie() {
        return painelCadastroEspecie;
    }

    /**
     * @return the painelCadastroRaca
     */
    public javax.swing.JPanel getPainelCadastroRaca() {
        return painelCadastroRaca;
    }

    /**
     * @return the painelEspecie
     */
    public javax.swing.JPanel getPainelEspecie() {
        return painelEspecie;
    }

    /**
     * @return the painelItens
     */
    public javax.swing.JTabbedPane getPainelItens() {
        return painelItens;
    }

    /**
     * @return the painelNC
     */
    public javax.swing.JPanel getPainelNC() {
        return painelNC;
    }

    /**
     * @return the painelRaca
     */
    public javax.swing.JPanel getPainelRaca() {
        return painelRaca;
    }

    /**
     * @return the tabelaEspecie
     */
    public javax.swing.JTable getTabelaEspecie() {
        return tabelaEspecie;
    }

    /**
     * @return the tabelaRacas
     */
    public javax.swing.JTable getTabelaRacas() {
        return tabelaRacas;
    }

    /**
     * @return the txtAreaRaca
     */
    public javax.swing.JTextArea getTxtAreaRaca() {
        return txtAreaRaca;
    }

    /**
     * @return the txtEspecie
     */
    public javax.swing.JTextField getTxtEspecie() {
        return txtEspecie;
    }

    /**
     * @return the txtRaca
     */
    public javax.swing.JTextField getTxtRaca() {
        return txtRaca;
    }

    
}