/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.view;

import br.com.pbd.Modelo.Animal;
import javax.swing.JTextField;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Andre-Coude
 */
public class CadastroAnimal extends javax.swing.JInternalFrame {

    /**
     * Creates new form CadastroAnimal
     */
    public CadastroAnimal() {
        initComponents();
        AutoCompleteDecorator.decorate(comboDono);
        AutoCompleteDecorator.decorate(comboEspecie);
        AutoCompleteDecorator.decorate(comboRaca);
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
        painelAnimail = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaAnimais = new javax.swing.JTable();
        txtPesquisa = new br.com.pbd.Modelo.JTextFieldHint(new JTextField(), "lupa", " Nome , Apelido ou Dono");
        jLabel16 = new javax.swing.JLabel();
        btnCadastrarAnimal = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        painelCadastro = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        nascimento = new com.toedter.calendar.JDateChooser();
        txtCor = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        comboEspecie = new javax.swing.JComboBox<>();
        txtaApelido = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblApelido = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        comboRaca = new javax.swing.JComboBox<>();
        btnRacas = new javax.swing.JButton();
        txtPeso = new javax.swing.JFormattedTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        comboDono = new javax.swing.JComboBox<>();
        btnDonos = new javax.swing.JButton();
        comboSexo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        OBSERVAÇÃO = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaObservacao = new javax.swing.JTextArea();
        btnCancelar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 255));
        setBorder(null);
        setPreferredSize(new java.awt.Dimension(1034, 610));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        painelItens.setBackground(new java.awt.Color(204, 204, 255));
        painelItens.setEnabled(false);
        painelItens.setFont(new java.awt.Font("Franklin Gothic Heavy", 0, 24)); // NOI18N
        painelItens.setPreferredSize(new java.awt.Dimension(900, 481));

        painelAnimail.setBackground(new java.awt.Color(255, 255, 255));
        painelAnimail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tabelaAnimais.setBackground(new java.awt.Color(204, 204, 255));
        tabelaAnimais.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        tabelaAnimais.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        tabelaAnimais.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOME", "APELIDO", "COR", "RAÇA", "DONO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaAnimais.setAutoscrolls(false);
        tabelaAnimais.setCellSelectionEnabled(true);
        tabelaAnimais.setRowHeight(40);
        jScrollPane2.setViewportView(tabelaAnimais);
        if (tabelaAnimais.getColumnModel().getColumnCount() > 0) {
            tabelaAnimais.getColumnModel().getColumn(0).setResizable(false);
            tabelaAnimais.getColumnModel().getColumn(0).setHeaderValue("Nome");
            tabelaAnimais.getColumnModel().getColumn(1).setResizable(false);
            tabelaAnimais.getColumnModel().getColumn(1).setHeaderValue("Cor");
            tabelaAnimais.getColumnModel().getColumn(2).setResizable(false);
            tabelaAnimais.getColumnModel().getColumn(2).setHeaderValue("Peso");
            tabelaAnimais.getColumnModel().getColumn(3).setResizable(false);
            tabelaAnimais.getColumnModel().getColumn(3).setHeaderValue("Sexo");
            tabelaAnimais.getColumnModel().getColumn(4).setResizable(false);
            tabelaAnimais.getColumnModel().getColumn(4).setHeaderValue("Nascimento");
        }

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("PESQUISAR : ");

        btnCadastrarAnimal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/pets.png"))); // NOI18N
        btnCadastrarAnimal.setText("CADASTRAR ANIMAL");
        btnCadastrarAnimal.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/pets.1.png"))); // NOI18N

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/voltar.png"))); // NOI18N
        btnSair.setText("SAIR");
        btnSair.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/voltar.1.png"))); // NOI18N

        javax.swing.GroupLayout painelAnimailLayout = new javax.swing.GroupLayout(painelAnimail);
        painelAnimail.setLayout(painelAnimailLayout);
        painelAnimailLayout.setHorizontalGroup(
            painelAnimailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelAnimailLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCadastrarAnimal)
                .addGap(43, 43, 43)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
            .addGroup(painelAnimailLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(painelAnimailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(painelAnimailLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        painelAnimailLayout.setVerticalGroup(
            painelAnimailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelAnimailLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(painelAnimailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(painelAnimailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSair)
                    .addComponent(btnCadastrarAnimal))
                .addGap(35, 35, 35))
        );

        painelItens.addTab("ANIMAIS", painelAnimail);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel5.setText("ESPECIE :");

        jLabel3.setText("NASCIMENTO :");

        jLabel4.setText("PESO :");

        jLabel1.setText("COR :");

        lblApelido.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblApelido.setText("APELIDO :");

        lblNome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblNome.setText("NOME COMPLETO :");

        jLabel7.setText("RAÇA :");

        btnRacas.setText("Ok");

        txtPeso.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(comboRaca, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRacas, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNome))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtaApelido, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblApelido))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(lblApelido)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtaApelido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nascimento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRacas)
                        .addComponent(comboRaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel6.setText("DONO :");

        btnDonos.setText("Ok");

        comboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Macho", "Femêa" }));

        jLabel2.setText("SEXO :");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(comboDono, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDonos, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(44, 44, 44))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2))
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboDono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDonos)
                    .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        OBSERVAÇÃO.setBackground(new java.awt.Color(204, 204, 255));
        OBSERVAÇÃO.setBorder(javax.swing.BorderFactory.createTitledBorder("OBSERVAÇÃO"));
        OBSERVAÇÃO.setToolTipText("Observações");
        OBSERVAÇÃO.setName("Observações"); // NOI18N

        areaObservacao.setColumns(20);
        areaObservacao.setRows(5);
        jScrollPane1.setViewportView(areaObservacao);

        javax.swing.GroupLayout OBSERVAÇÃOLayout = new javax.swing.GroupLayout(OBSERVAÇÃO);
        OBSERVAÇÃO.setLayout(OBSERVAÇÃOLayout);
        OBSERVAÇÃOLayout.setHorizontalGroup(
            OBSERVAÇÃOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
        );
        OBSERVAÇÃOLayout.setVerticalGroup(
            OBSERVAÇÃOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OBSERVAÇÃOLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/rejected.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.setBorder(null);
        btnCancelar.setBorderPainted(false);
        btnCancelar.setContentAreaFilled(false);
        btnCancelar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/rejected.1.png"))); // NOI18N
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnSalvar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/approved.png"))); // NOI18N
        btnSalvar.setText("  SALVAR");
        btnSalvar.setBorderPainted(false);
        btnSalvar.setContentAreaFilled(false);
        btnSalvar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/pbd/resource/approved.1.png"))); // NOI18N
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(204, 204, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 211, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 157, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("IDADE : ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout painelCadastroLayout = new javax.swing.GroupLayout(painelCadastro);
        painelCadastro.setLayout(painelCadastroLayout);
        painelCadastroLayout.setHorizontalGroup(
            painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCadastroLayout.createSequentialGroup()
                .addGroup(painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelCadastroLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(painelCadastroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(OBSERVAÇÃO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelCadastroLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, painelCadastroLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        painelCadastroLayout.setVerticalGroup(
            painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(painelCadastroLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(painelCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(painelCadastroLayout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(painelCadastroLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(OBSERVAÇÃO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        painelItens.addTab("CADASTRO", painelCadastro);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelItens, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(painelItens, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel OBSERVAÇÃO;
    private javax.swing.JTextArea areaObservacao;
    private javax.swing.JButton btnCadastrarAnimal;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDonos;
    private javax.swing.JButton btnRacas;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> comboDono;
    private javax.swing.JComboBox<String> comboEspecie;
    private javax.swing.JComboBox<String> comboRaca;
    private javax.swing.JComboBox<String> comboSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblApelido;
    private javax.swing.JLabel lblNome;
    private com.toedter.calendar.JDateChooser nascimento;
    private javax.swing.JPanel painelAnimail;
    private javax.swing.JPanel painelCadastro;
    private javax.swing.JTabbedPane painelItens;
    private javax.swing.JTable tabelaAnimais;
    private javax.swing.JTextField txtCor;
    private javax.swing.JTextField txtNome;
    private javax.swing.JFormattedTextField txtPeso;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtaApelido;
    // End of variables declaration//GEN-END:variables

    public void limparComponentes() {
        txtNome.setText("");
        txtaApelido.setText("");
        txtPeso.setText("");
        txtCor.setText("");
        areaObservacao.setText("");

    }

    public void preencherDados(Animal animal) {

        getTxtaApelido().setText(animal.getApelido());
        getTxtNome().setText(animal.getNome());
        getTxtCor().setText(animal.getCor());
        getAreaObservacao().setText(animal.getObservacao());
        getAreaObservacao().setText(animal.getObservacao());
        getNascimento().setDate(animal.getNascimento());
        getTxtPeso().setText(animal.getPesokg() + "");

        for (int c = 0; c < getComboDono().getItemCount(); c++) {

            if (getComboDono().getItemAt(c).equals(animal.getCliente().getNome())) {
                getComboDono().setSelectedItem(getComboDono().getItemAt(c));
            }
        }

        for (int c = 0; c < getComboSexo().getItemCount(); c++) {

            if (getComboSexo().getItemAt(c).equals(animal.getSexo())) {
                getComboSexo().setSelectedItem(getComboSexo().getItemAt(c));
            }
        }
    }

    /**
     * @return the areaObservacao
     */
    public javax.swing.JTextArea getAreaObservacao() {
        return areaObservacao;
    }

    /**
     * @return the btnCancelar
     */
    public javax.swing.JButton getBtnCancelar() {
        return btnCancelar;
    }

    /**
     * @return the btnDonos
     */
    public javax.swing.JButton getBtnDonos() {
        return btnDonos;
    }

    /**
     * @return the btnRacas
     */
    public javax.swing.JButton getBtnRacas() {
        return btnRacas;
    }

    /**
     * @return the btnSalvar
     */
    public javax.swing.JButton getBtnSalvar() {
        return btnSalvar;
    }

    /**
     * @return the comboDono
     */
    public javax.swing.JComboBox<String> getComboDono() {
        return comboDono;
    }

    /**
     * @return the comboEspecie
     */
    public javax.swing.JComboBox<String> getComboEspecie() {
        return comboEspecie;
    }

    /**
     * @return the comboRaca
     */
    public javax.swing.JComboBox<String> getComboRaca() {
        return comboRaca;
    }

    /**
     * @return the comboSexo
     */
    public javax.swing.JComboBox<String> getComboSexo() {
        return comboSexo;
    }

    /**
     * @return the nascimento
     */
    public com.toedter.calendar.JDateChooser getNascimento() {
        return nascimento;
    }

    /**
     * @return the painelAnimail
     */
    public javax.swing.JPanel getPainelAnimail() {
        return painelAnimail;
    }

    /**
     * @return the painelCadastro
     */
    public javax.swing.JPanel getPainelCadastro() {
        return painelCadastro;
    }

    /**
     * @return the txtCor
     */
    public javax.swing.JTextField getTxtCor() {
        return txtCor;
    }

    /**
     * @return the txtNome
     */
    public javax.swing.JTextField getTxtNome() {
        return txtNome;
    }

    /**
     * @return the txtPeso
     */
    public javax.swing.JTextField getTxtPeso() {
        return txtPeso;
    }

    /**
     * @return the txtaApelido
     */
    public javax.swing.JTextField getTxtaApelido() {
        return txtaApelido;
    }

    /**
     * @return the painelItens
     */
    public javax.swing.JTabbedPane getPainelItens() {
        return painelItens;
    }

    /**
     * @return the btnSair
     */
    public javax.swing.JButton getBtnSair() {
        return btnSair;
    }

    /**
     * @return the btnCadastrarAnimal
     */
    public javax.swing.JButton getBtnCadastrarAnimal() {
        return btnCadastrarAnimal;
    }

    /**
     * @return the OBSERVAÇÃO
     */
    public javax.swing.JPanel getOBSERVAÇÃO() {
        return OBSERVAÇÃO;
    }

    /**
     * @return the tabelaAnimais
     */
    public javax.swing.JTable getTabelaAnimais() {
        return tabelaAnimais;
    }

    /**
     * @return the txtPesquisa
     */
    public javax.swing.JTextField getTxtPesquisa() {
        return txtPesquisa;
    }

}
