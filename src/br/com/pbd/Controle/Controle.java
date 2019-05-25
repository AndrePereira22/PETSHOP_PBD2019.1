/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Cliente;
import br.com.pbd.Modelo.Dados;
import br.com.pbd.Modelo.Fornecedor;
import br.com.pbd.Modelo.Funcionario;
import br.com.pbd.Modelo.Login;
import br.com.pbd.Modelo.Profissional;
import br.com.pbd.Modelo.Render;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

/**
 *
 * @author Andre-Coude
 */
public class Controle extends MouseAdapter implements ActionListener {

    private final TelaPrincipal tPrincipal;
    private List<Cliente> clientes;
    private List<Funcionario> funcionarios;
    private List<Profissional> profissionais;
    private List<Fornecedor> fornecedores;
    private final JButton btnExcluir, btnEditar;
    private final Icon editar, excluir;
    private Cliente cliente;
    private Funcionario funcionario;
    private Fornecedor fornecedor;
    private Profissional profissional;
    private Boolean salvar_editar;

    public Controle(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;

        clientes = new ArrayList<Cliente>();
        funcionarios = new ArrayList<Funcionario>();
        profissionais = new ArrayList<Profissional>();
        fornecedores = new ArrayList<Fornecedor>();
        salvar_editar = true;

        editar = new ImageIcon(getClass().getResource("/br/com/pbd/resource/edit.png"));
        excluir = new ImageIcon(getClass().getResource("/br/com/pbd/resource/lixo.png"));

        btnEditar = new JButton(editar);
        btnEditar.setName("editar");
        btnEditar.setBorder(null);
        btnEditar.setContentAreaFilled(false);

        btnExcluir = new JButton(excluir);
        btnExcluir.setName("excluir");
        btnExcluir.setBorder(null);
        btnExcluir.setContentAreaFilled(false);

        tPrincipal.getcFuncionario().getBtnSalvar().addActionListener(this); 
        tPrincipal.getcFuncionario().getBtnNovoFuncionario().addActionListener(this);
        tPrincipal.getcFuncionario().getTabelaFuncionarios().addMouseListener(this);
        
        tPrincipal.getcCliente().getBtnSalvar().addActionListener(this);
        tPrincipal.getcCliente().getBtnCadastrarCliente().addActionListener(this);
        tPrincipal.getcCliente().getTabelaCliente().addMouseListener(this);
        
        tPrincipal.getcFornecedor().getBtnSalvar().addActionListener(this);
        tPrincipal.getcFornecedor().getBtnNovoFornecedor().addActionListener(this);
        tPrincipal.getcFornecedor().getTabelaFornecedor().addMouseListener(this);
        
        tPrincipal.getcProfissioanl().getBtnSalvar().addActionListener(this);
        tPrincipal.getcProfissioanl().getBtnNovoProfissional().addActionListener(this);
        tPrincipal.getcProfissioanl().getTabelaProfissionais().addMouseListener(this);
        
        tPrincipal.getBtnClientes().addActionListener(this);
        tPrincipal.getCadastros().getBtnProfissional().addActionListener(this);
        tPrincipal.getCadastros().getBtnFornecedor().addActionListener(this);
        tPrincipal.getCadastros().getBtnFuncionario().addActionListener(this);
        
        
      
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tPrincipal.getcCliente().getTabelaCliente()) {

            int ro = retornaIndice(tPrincipal.getcCliente().getTabelaCliente(), e);
            tPrincipal.getcCliente().getPainelItens().setSelectedComponent(tPrincipal.getcCliente().getPainelCadastro());
            tPrincipal.getcCliente().getPainelCadastro().setEnabled(true);
            cliente = clientes.get(ro);
            preencherDadosCliente(cliente);
        }
        if (e.getSource() == tPrincipal.getcFuncionario().getTabelaFuncionarios()) {

            int ro = retornaIndice(tPrincipal.getcFuncionario().getTabelaFuncionarios(), e);
            tPrincipal.getcFuncionario().getPainelItens().setSelectedComponent(tPrincipal.getcFuncionario().getPainelCadastro());
            tPrincipal.getcFuncionario().getPainelCadastro().setEnabled(true);
            funcionario = funcionarios.get(ro);
            preencherDadosFuncionario(funcionario);
        }
        if (e.getSource() == tPrincipal.getcProfissioanl().getTabelaProfissionais()) {

            int ro = retornaIndice(tPrincipal.getcProfissioanl().getTabelaProfissionais(), e);
            tPrincipal.getcProfissioanl().getPainelItens().setSelectedComponent(tPrincipal.getcProfissioanl().getPainelCadastro());
            tPrincipal.getcProfissioanl().getPainelCadastro().setEnabled(true);
            profissional = profissionais.get(ro);
            preencherDadosProfissional(profissional);
        }
        if (e.getSource() == tPrincipal.getcFornecedor().getTabelaFornecedor()) {

            int ro = retornaIndice(tPrincipal.getcFornecedor().getTabelaFornecedor(), e);
            tPrincipal.getcFornecedor().getPainelItens().setSelectedComponent(tPrincipal.getcFornecedor().getPainelCadastro());
            tPrincipal.getcFornecedor().getPainelCadastro().setEnabled(true);
            fornecedor = fornecedores.get(ro);
            preencherDadosFornecedor(fornecedor);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tPrincipal.getBtnClientes()) {
            listarClientes();
        }
        if (e.getSource() == tPrincipal.getcCliente().getBtnSalvar()) {
            if (salvar_editar) {
                cadastrarCliente();
            } else {
                editarCliente(cliente);
            }
        }

        if (e.getSource() == tPrincipal.getcCliente().getBtnCadastrarCliente()) {
            salvar_editar = true;
            tPrincipal.getcCliente().limparComponentes();
        }

        if (e.getSource() == tPrincipal.getcFuncionario().getBtnSalvar()) {
            if (salvar_editar) {
                cadastrarFuncionario();
            } else {
                editarFuncionario(funcionario);
            }
        }

        if (e.getSource() == tPrincipal.getCadastros().getBtnFuncionario()) {
            listarFuncionarios();
        }

        if (e.getSource() == tPrincipal.getcFuncionario().getBtnNovoFuncionario()) {
            salvar_editar = true;
            tPrincipal.getcFuncionario().limparComponentes();
        }

        if (e.getSource() == tPrincipal.getCadastros().getBtnFornecedor()) {
            listarFornecedores();
        }
        if (e.getSource() == tPrincipal.getcFornecedor().getBtnSalvar()) {

            if (salvar_editar) {
                cadastrarFornecedor();
            } else {
                editarFornecedor(fornecedor);
            }
        }
        if (e.getSource() == tPrincipal.getcFornecedor().getBtnNovoFornecedor()) {
            salvar_editar = true;
            tPrincipal.getcFornecedor().limparComponentes();
        }

        if (e.getSource() == tPrincipal.getCadastros().getBtnProfissional()) {
            listarProfissionais();
        }

        if (e.getSource() == tPrincipal.getcProfissioanl().getBtnSalvar()) {

            if (salvar_editar) {
                cadastrarProfissional();
            } else {
                editarProfissional(profissional);
            }
        }
        if (e.getSource() == tPrincipal.getcProfissioanl().getBtnNovoProfissional()) {
            salvar_editar = true;
            tPrincipal.getcProfissioanl().limparComponentes();

        }

    }

    public void cadastrarCliente() {

        Dados dados = new Dados();
        dados.setBairro(tPrincipal.getcCliente().getTxtBairro().getText());
        dados.setCelular(tPrincipal.getcCliente().getTxtCelular().getText());
        dados.setTelefone(tPrincipal.getcCliente().getTxtTelefone().getText());
        dados.setCep(tPrincipal.getcCliente().getTxtCep().getText());
        dados.setCidade(tPrincipal.getcCliente().getTxtCidade().getText());
        dados.setEmail(tPrincipal.getcCliente().getTxtEmail().getText());

        String numb = tPrincipal.getcCliente().getTxtNumero().getText() + " "
                + tPrincipal.getcCliente().getTxtComplemento().getText();

        dados.setNumero(numb);
        dados.setRua(tPrincipal.getcCliente().getTxtRua().getText());
        dados.setUf(tPrincipal.getcCliente().getComboUf().getSelectedItem().toString());

        cliente = new Cliente();

        cliente.setNome(tPrincipal.getcCliente().getTxtNome().getText());
        cliente.setCpf(tPrincipal.getcCliente().getTxtCpf().getText());
        cliente.setSexo(tPrincipal.getcCliente().getComboSexo().getSelectedItem().toString());

        // conversao de jdatechooser para sql date
        java.sql.Date nascimento = ConverterData(tPrincipal.getcCliente().getNascimento().getDate());

        cliente.setNascimento(nascimento);
        cliente.setRg(tPrincipal.getcCliente().getTxtRg().getText());
        cliente.setDados(dados);

        try {
            new GenericDao<Cliente>().salvar_ou_atualizar(cliente);

            JOptionPane.showMessageDialog(null, "Cliente cadastrado!");
            tPrincipal.getcCliente().getPainelItens().setSelectedComponent(tPrincipal.getcCliente().getPainelCliente());
            tPrincipal.getcCliente().getPainelCadastro().setEnabled(false);
            listarClientes();
            salvar_editar = true;
        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }

    }

    public void cadastrarFuncionario() {

        Login login = new Login();
        String senha = new String(tPrincipal.getcFuncionario().getTxtSenha().getPassword());

        login.setUsuario(tPrincipal.getcFuncionario().getTxtUsuario().getText());

        Dados dados = new Dados();
        dados.setBairro(tPrincipal.getcFuncionario().getTxtBairro().getText());
        dados.setCelular(tPrincipal.getcFuncionario().getTxtCelular().getText());
        dados.setTelefone(tPrincipal.getcFuncionario().getTxtTelefone().getText());
        dados.setCep(tPrincipal.getcFuncionario().getTxtCep().getText());
        dados.setCidade(tPrincipal.getcFuncionario().getTxtCidade().getText());
        dados.setEmail(tPrincipal.getcFuncionario().getTxtEmail().getText());

        String numb = tPrincipal.getcFuncionario().getTxtNumero().getText() + " "
                + tPrincipal.getcFuncionario().getTxtComplemento().getText();

        dados.setNumero(numb);
        dados.setRua(tPrincipal.getcFuncionario().getTxtRua().getText());
        dados.setUf(tPrincipal.getcFuncionario().getComboUf().getSelectedItem().toString());

        funcionario = new Funcionario();

        funcionario.setNome(tPrincipal.getcFuncionario().getTxtNome().getText());
        funcionario.setCpf(tPrincipal.getcFuncionario().getTxtCpf().getText());
        funcionario.setSexo(tPrincipal.getcFuncionario().getComboSexo().getSelectedItem().toString());
        funcionario.setTipo(tPrincipal.getcFuncionario().getComboTipo().getSelectedItem().toString());

        // conversao de jdatechooser para sql date
        java.sql.Date nascimento = ConverterData(tPrincipal.getcFuncionario().getNascimento().getDate());

        funcionario.setNascimento(nascimento);
        funcionario.setRg(tPrincipal.getcFuncionario().getTxtRg().getText());

        try {
            // criptografar senha
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = md.digest(senha.getBytes("UTF-8"));
            StringBuilder ab = new StringBuilder();

            for (byte b : messageDigest) {
                ab.append(String.format("%02X", 0xFF & b));

            }
            String senhaHex = ab.toString();

            login.setSenha(senhaHex);

            funcionario.setDados(dados);
            funcionario.setLogin(login);

            new GenericDao<Funcionario>().salvar_ou_atualizar(funcionario);

            JOptionPane.showMessageDialog(null, "Funcionario cadastrado!");
            tPrincipal.getcFuncionario().getPainelItens().setSelectedComponent(tPrincipal.getcFuncionario().getPainelFuncionario());
            tPrincipal.getcFuncionario().getPainelCadastro().setEnabled(false);
            listarFuncionarios();
            salvar_editar = true;
        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cadastrarFornecedor() {

        Dados dados = new Dados();
        dados.setBairro(tPrincipal.getcFornecedor().getTxtBairro().getText());
        dados.setCelular(tPrincipal.getcFornecedor().getTxtCelular().getText());
        dados.setTelefone(tPrincipal.getcFornecedor().getTxtTelefone().getText());
        dados.setCep(tPrincipal.getcFornecedor().getTxtCep().getText());
        dados.setCidade(tPrincipal.getcFornecedor().getTxtCidade().getText());
        dados.setEmail(tPrincipal.getcFornecedor().getTxtEmail().getText());

        String numb = tPrincipal.getcFornecedor().getTxtNumero().getText() + " "
                + tPrincipal.getcFornecedor().getTxtComplemento().getText();

        dados.setNumero(numb);
        dados.setRua(tPrincipal.getcFornecedor().getTxtRua().getText());
        dados.setUf(tPrincipal.getcFornecedor().getComboUf().getSelectedItem().toString());

        fornecedor = new Fornecedor();

        fornecedor.setDados(dados);
        fornecedor.setCnpj(tPrincipal.getcFornecedor().getTxtCnpjj().getText());
        fornecedor.setNomefantasia(tPrincipal.getcFornecedor().getTxtNomeFantazia().getText());
        fornecedor.setRazaosocial(tPrincipal.getcFornecedor().getTxtRazaoSociall().getText());

        try {
            new GenericDao<Fornecedor>().salvar_ou_atualizar(fornecedor);
            JOptionPane.showMessageDialog(null, "Fornecedor cadastrado!");
            tPrincipal.getcFornecedor().getPainelItens().setSelectedComponent(tPrincipal.getcFornecedor().getPainelFornecedor());
            tPrincipal.getcFornecedor().getPainelCadastro().setEnabled(false);
            listarFornecedores();
            salvar_editar = true;
        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    private void cadastrarProfissional() {

        Login login = new Login();
        String senha = new String(tPrincipal.getcProfissioanl().getTxtSenha().getPassword());
        login.setUsuario(tPrincipal.getcProfissioanl().getTxtUsuario().getText());

        Dados dados = new Dados();
        dados.setBairro(tPrincipal.getcProfissioanl().getTxtBairro().getText());
        dados.setCelular(tPrincipal.getcProfissioanl().getTxtCelular().getText());
        dados.setTelefone(tPrincipal.getcProfissioanl().getTxtTelefone().getText());
        dados.setCep(tPrincipal.getcProfissioanl().getTxtCep().getText());
        dados.setCidade(tPrincipal.getcProfissioanl().getTxtCidade().getText());
        dados.setEmail(tPrincipal.getcProfissioanl().getTxtEmail().getText());

        String numb = tPrincipal.getcProfissioanl().getTxtNumero().getText() + " "
                + tPrincipal.getcProfissioanl().getTxtComplemento().getText();

        dados.setNumero(numb);
        dados.setRua(tPrincipal.getcProfissioanl().getTxtRua().getText());
        dados.setUf(tPrincipal.getcProfissioanl().getComboUf().getSelectedItem().toString());

        profissional = new Profissional();
        profissional.setCrmv(tPrincipal.getcProfissioanl().getComboCrmv().getSelectedItem().toString());
        profissional.setCpf(tPrincipal.getcProfissioanl().getTxtCpf().getText());
        profissional.setRg(tPrincipal.getcProfissioanl().getTxtRg().getText());
        profissional.setNome(tPrincipal.getcProfissioanl().getTxtNome().getText());
        profissional.setSexo(tPrincipal.getcProfissioanl().getComboSexo().getSelectedItem().toString());
        profissional.setTipo(tPrincipal.getcProfissioanl().getComboEspecialidade().getSelectedItem().toString());
        java.sql.Date nascimento = ConverterData(tPrincipal.getcProfissioanl().getNascimento().getDate());

        profissional.setNascimento(nascimento);

        try {

            // criptografar senha
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = md.digest(senha.getBytes("UTF-8"));
            StringBuilder ab = new StringBuilder();

            for (byte b : messageDigest) {
                ab.append(String.format("%02X", 0xFF & b));

            }
            String senhaHex = ab.toString();

            login.setSenha(senhaHex);

            profissional.setDados(dados);
            profissional.setLogin(login);

            new GenericDao<Profissional>().salvar_ou_atualizar(profissional);

            JOptionPane.showMessageDialog(null, "Profissional cadastrado!");
            tPrincipal.getcProfissioanl().getPainelItens().setSelectedComponent(tPrincipal.getcProfissioanl().getPainelProfissional());
            tPrincipal.getcProfissioanl().getPainelCadastro().setEnabled(false);
            listarProfissionais();
            salvar_editar = true;
        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listarClientes() {

        clientes = new GenericDao<Cliente>().getAll(Cliente.class);

        if (!clientes.isEmpty()) {

            tPrincipal.getcCliente().getTabelaCliente().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"NOME", "SEXO", "DATA DE NASCIMENTO", "CPF", "CELULAR", "EDITAR", "EXCLUIR"};
                Object[][] dados = new Object[clientes.size()][7];
                for (Cliente a : clientes) {
                    dados[i][0] = a.getNome();
                    dados[i][1] = a.getSexo();
                    dados[i][2] = a.getNascimento();
                    dados[i][3] = a.getCpf();
                    dados[i][4] = a.getDados().getCelular();
                    dados[i][5] = btnEditar;
                    dados[i][6] = btnExcluir;

                    i++;
                }

                DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tPrincipal.getcCliente().getTabelaCliente().setModel(dataModel);
            } catch (Exception ex) {

            }
        }
    }

    public void listarFuncionarios() {

        funcionarios = new GenericDao<Funcionario>().getAll(Funcionario.class);
        if (!funcionarios.isEmpty()) {
            tPrincipal.getcFuncionario().getTabelaFuncionarios().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"NOME", "SEXO", "DATA DE NASCIMENTO", "CPF", "FUNÇÃO", "CELULAR", "EDITAR", "EXCLUIR"};
                Object[][] dados = new Object[funcionarios.size()][8];
                for (Funcionario a : funcionarios) {
                    dados[i][0] = a.getNome();
                    dados[i][1] = a.getSexo();
                    dados[i][2] = a.getNascimento();
                    dados[i][3] = a.getCpf();
                    dados[i][4] = a.getTipo();
                    dados[i][5] = a.getDados().getCelular();
                    dados[i][6] = btnEditar;
                    dados[i][7] = btnExcluir;

                    i++;
                }

                DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tPrincipal.getcFuncionario().getTabelaFuncionarios().setModel(dataModel);
            } catch (Exception ex) {

            }

        }
    }

    public void listarProfissionais() {

        profissionais = new GenericDao<Profissional>().getAll(Profissional.class);
        if (!profissionais.isEmpty()) {
            tPrincipal.getcProfissioanl().getTabelaProfissionais().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"NOME", "SEXO", "DATA DE NASCIMENTO", "CPF", "FUNÇÃO", "CELULAR", "EDITAR", "EXCLUIR"};
                Object[][] dados = new Object[profissionais.size()][9];
                for (Profissional a : profissionais) {
                    dados[i][0] = a.getNome();
                    dados[i][1] = a.getSexo();
                    dados[i][2] = a.getNascimento();
                    dados[i][3] = a.getCpf();
                    dados[i][4] = a.getTipo();
                    dados[i][5] = a.getDados().getCelular();
                    dados[i][6] = btnEditar;
                    dados[i][7] = btnExcluir;

                    i++;
                }

                DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tPrincipal.getcProfissioanl().getTabelaProfissionais().setModel(dataModel);
            } catch (Exception ex) {

            }

        }
    }

    public void listarFornecedores() {

        fornecedores = new GenericDao<Fornecedor>().getAll(Fornecedor.class);
        if (!fornecedores.isEmpty()) {
            tPrincipal.getcFornecedor().getTabelaFornecedor().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"NOME", "RAZÃO SOCIAL", "CNPJ", "CIDADE", "CONTATO", "EDITAR", "EXCLUIR"};
                Object[][] dados = new Object[fornecedores.size()][7];
                for (Fornecedor a : fornecedores) {
                    dados[i][0] = a.getNomefantasia();
                    dados[i][1] = a.getRazaosocial();
                    dados[i][2] = a.getCnpj();
                    dados[i][3] = a.getDados().getCidade();
                    dados[i][4] = a.getDados().getCelular();
                    dados[i][5] = btnEditar;
                    dados[i][6] = btnExcluir;

                    i++;
                }

                DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tPrincipal.getcFornecedor().getTabelaFornecedor().setModel(dataModel);
            } catch (Exception ex) {

            }

        }
    }

    public void editarCliente(Cliente cliente) {

        cliente.getDados().setBairro(tPrincipal.getcCliente().getTxtBairro().getText());
        cliente.getDados().setCelular(tPrincipal.getcCliente().getTxtCelular().getText());
        cliente.getDados().setTelefone(tPrincipal.getcCliente().getTxtTelefone().getText());
        cliente.getDados().setCep(tPrincipal.getcCliente().getTxtCep().getText());
        cliente.getDados().setCidade(tPrincipal.getcCliente().getTxtCidade().getText());
        cliente.getDados().setEmail(tPrincipal.getcCliente().getTxtEmail().getText());

        String numb = tPrincipal.getcCliente().getTxtNumero().getText() + " "
                + tPrincipal.getcCliente().getTxtComplemento().getText();

        cliente.getDados().setNumero(numb);
        cliente.getDados().setRua(tPrincipal.getcFuncionario().getTxtRua().getText());
        cliente.getDados().setUf(tPrincipal.getcFuncionario().getComboUf().getSelectedItem().toString());

        cliente.setNome(tPrincipal.getcCliente().getTxtNome().getText());
        cliente.setCpf(tPrincipal.getcCliente().getTxtCpf().getText());
        cliente.setSexo(tPrincipal.getcCliente().getComboSexo().getSelectedItem().toString());

        // conversao de jdatechooser para sql date
        java.sql.Date nascimento = ConverterData(tPrincipal.getcCliente().getNascimento().getDate());

        cliente.setNascimento(nascimento);
        cliente.setRg(tPrincipal.getcCliente().getTxtRg().getText());

        try {
            new GenericDao<Cliente>().salvar_ou_atualizar(cliente);

            JOptionPane.showMessageDialog(null, "Edição concluida!");
            tPrincipal.getcCliente().getPainelItens().setSelectedComponent(tPrincipal.getcCliente().getPainelCliente());
            tPrincipal.getcCliente().getPainelCadastro().setEnabled(false);
            listarClientes();
            salvar_editar = true;
        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }

    }

    public void editarFuncionario(Funcionario funcionario) {

//        Login login = new Login();
//        String senha = new String(tPrincipal.getcFuncionario().getTxtSenha().getPassword());
//        login.setSenha(senha);
//        login.setUsuario(tPrincipal.getcFuncionario().getTxtUsuario().getText());
        funcionario.getDados().setBairro(tPrincipal.getcFuncionario().getTxtBairro().getText());
        funcionario.getDados().setCelular(tPrincipal.getcFuncionario().getTxtCelular().getText());
        funcionario.getDados().setTelefone(tPrincipal.getcFuncionario().getTxtTelefone().getText());
        funcionario.getDados().setCep(tPrincipal.getcFuncionario().getTxtCep().getText());
        funcionario.getDados().setCidade(tPrincipal.getcFuncionario().getTxtCidade().getText());
        funcionario.getDados().setEmail(tPrincipal.getcFuncionario().getTxtEmail().getText());

        funcionario.getDados().setNumero(tPrincipal.getcFuncionario().getTxtNumero().getText());
        funcionario.getDados().setRua(tPrincipal.getcFuncionario().getTxtRua().getText());
        funcionario.getDados().setUf(tPrincipal.getcFuncionario().getComboUf().getSelectedItem().toString());

        funcionario.setNome(tPrincipal.getcFuncionario().getTxtNome().getText());
        funcionario.setCpf(tPrincipal.getcFuncionario().getTxtCpf().getText());
        funcionario.setSexo(tPrincipal.getcFuncionario().getComboSexo().getSelectedItem().toString());
        funcionario.setTipo(tPrincipal.getcFuncionario().getComboTipo().getSelectedItem().toString());

        // conversao de jdatechooser para sql date
        java.sql.Date nascimento = ConverterData(tPrincipal.getcFuncionario().getNascimento().getDate());

        funcionario.setNascimento(nascimento);
        funcionario.setRg(tPrincipal.getcFuncionario().getTxtRg().getText());

        try {
            new GenericDao<Funcionario>().salvar_ou_atualizar(funcionario);

            JOptionPane.showMessageDialog(null, "Ediçao concluida!");
            tPrincipal.getcFuncionario().getPainelItens().setSelectedComponent(tPrincipal.getcFuncionario().getPainelFuncionario());
            tPrincipal.getcFuncionario().getPainelCadastro().setEnabled(false);
            listarFuncionarios();
            salvar_editar = true;
        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    private void editarFornecedor(Fornecedor fornecedor) {

        fornecedor.getDados().setBairro(tPrincipal.getcFornecedor().getTxtBairro().getText());
        fornecedor.getDados().setCelular(tPrincipal.getcFornecedor().getTxtCelular().getText());
        fornecedor.getDados().setTelefone(tPrincipal.getcFornecedor().getTxtTelefone().getText());
        fornecedor.getDados().setCep(tPrincipal.getcFornecedor().getTxtCep().getText());
        fornecedor.getDados().setCidade(tPrincipal.getcFornecedor().getTxtCidade().getText());
        fornecedor.getDados().setEmail(tPrincipal.getcFornecedor().getTxtEmail().getText());

        String numb = tPrincipal.getcFornecedor().getTxtNumero().getText() + " "
                + tPrincipal.getcFornecedor().getTxtComplemento().getText();

        fornecedor.getDados().setNumero(numb);
        fornecedor.getDados().setRua(tPrincipal.getcFornecedor().getTxtRua().getText());
        fornecedor.getDados().setUf(tPrincipal.getcFornecedor().getComboUf().getSelectedItem().toString());

        fornecedor.setCnpj(tPrincipal.getcFornecedor().getTxtCnpjj().getText());
        fornecedor.setNomefantasia(tPrincipal.getcFornecedor().getTxtNomeFantazia().getText());
        fornecedor.setRazaosocial(tPrincipal.getcFornecedor().getTxtRazaoSociall().getText());

        try {
            new GenericDao<Fornecedor>().salvar_ou_atualizar(fornecedor);
            JOptionPane.showMessageDialog(null, "Edição Concluida!");
            tPrincipal.getcFornecedor().getPainelItens().setSelectedComponent(tPrincipal.getcFornecedor().getPainelFornecedor());
            tPrincipal.getcFornecedor().getPainelCadastro().setEnabled(false);
            listarFornecedores();
        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    private void editarProfissional(Profissional profissional) {

        profissional.getDados().setBairro(tPrincipal.getcProfissioanl().getTxtBairro().getText());
        profissional.getDados().setCelular(tPrincipal.getcProfissioanl().getTxtCelular().getText());
        profissional.getDados().setTelefone(tPrincipal.getcProfissioanl().getTxtTelefone().getText());
        profissional.getDados().setCep(tPrincipal.getcProfissioanl().getTxtCep().getText());
        profissional.getDados().setCidade(tPrincipal.getcProfissioanl().getTxtCidade().getText());
        profissional.getDados().setEmail(tPrincipal.getcProfissioanl().getTxtEmail().getText());

        String numb = tPrincipal.getcProfissioanl().getTxtNumero().getText() + " "
                + tPrincipal.getcProfissioanl().getTxtComplemento().getText();

        profissional.getDados().setNumero(numb);
        profissional.getDados().setRua(tPrincipal.getcProfissioanl().getTxtRua().getText());
        profissional.getDados().setUf(tPrincipal.getcProfissioanl().getComboUf().getSelectedItem().toString());

        profissional.setCrmv(tPrincipal.getcProfissioanl().getComboCrmv().getSelectedItem().toString());
        profissional.setCpf(tPrincipal.getcProfissioanl().getTxtCpf().getText());
        profissional.setRg(tPrincipal.getcProfissioanl().getTxtRg().getText());
        profissional.setNome(tPrincipal.getcProfissioanl().getTxtNome().getText());
        profissional.setSexo(tPrincipal.getcProfissioanl().getComboSexo().getSelectedItem().toString());
        profissional.setTipo(tPrincipal.getcProfissioanl().getComboEspecialidade().getSelectedItem().toString());
        java.sql.Date nascimento = ConverterData(tPrincipal.getcProfissioanl().getNascimento().getDate());

        profissional.setNascimento(nascimento);

        try {
            new GenericDao<Profissional>().salvar_ou_atualizar(profissional);

            JOptionPane.showMessageDialog(null, "Edição concluida!");
            tPrincipal.getcProfissioanl().getPainelItens().setSelectedComponent(tPrincipal.getcProfissioanl().getPainelProfissional());
            tPrincipal.getcProfissioanl().getPainelCadastro().setEnabled(false);
            listarProfissionais();
            salvar_editar = true;
        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    public java.sql.Date ConverterData(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    private void preencherDadosFuncionario(Funcionario funcionario) {

        tPrincipal.getcFuncionario().getTxtBairro().setText(funcionario.getDados().getBairro());
        tPrincipal.getcFuncionario().getTxtCelular().setText(funcionario.getDados().getCelular());
        tPrincipal.getcFuncionario().getTxtTelefone().setText(funcionario.getDados().getTelefone());
        tPrincipal.getcFuncionario().getTxtCep().setText(funcionario.getDados().getCep());
        tPrincipal.getcFuncionario().getTxtCidade().setText(funcionario.getDados().getCidade());
        tPrincipal.getcFuncionario().getTxtEmail().setText(funcionario.getDados().getEmail());

        tPrincipal.getcFuncionario().getTxtNumero().setText(funcionario.getDados().getNumero());

        tPrincipal.getcFuncionario().getTxtRua().setText(funcionario.getDados().getRua());

        tPrincipal.getcFuncionario().getTxtNome().setText(funcionario.getNome());
        tPrincipal.getcFuncionario().getTxtCpf().setText(funcionario.getCpf());

        tPrincipal.getcFuncionario().getNascimento().setDate(funcionario.getNascimento());

        tPrincipal.getcFuncionario().getTxtRg().setText(funcionario.getRg());

        for (int c = 0; c < tPrincipal.getcFuncionario().getComboSexo().getItemCount(); c++) {

            if (tPrincipal.getcFuncionario().getComboSexo().getItemAt(c).equals(funcionario.getSexo())) {
                tPrincipal.getcFuncionario().getComboSexo().setSelectedItem(tPrincipal.getcCliente().getComboSexo().getItemAt(c));
            }
        }
        for (int c = 0; c < tPrincipal.getcFuncionario().getComboUf().getItemCount(); c++) {

            if (tPrincipal.getcFuncionario().getComboUf().getItemAt(c).equals(funcionario.getDados().getUf())) {
                tPrincipal.getcFuncionario().getComboUf().setSelectedItem(tPrincipal.getcFuncionario().getComboUf().getItemAt(c));
            }
        }

    }

    private void preencherDadosCliente(Cliente cliente) {

        tPrincipal.getcCliente().getTxtBairro().setText(cliente.getDados().getBairro());
        tPrincipal.getcCliente().getTxtCelular().setText(cliente.getDados().getCelular());
        tPrincipal.getcCliente().getTxtTelefone().setText(cliente.getDados().getTelefone());
        tPrincipal.getcCliente().getTxtCep().setText(cliente.getDados().getCep());
        tPrincipal.getcCliente().getTxtCidade().setText(cliente.getDados().getCidade());
        tPrincipal.getcCliente().getTxtEmail().setText(cliente.getDados().getEmail());

        tPrincipal.getcCliente().getTxtNumero().setText(cliente.getDados().getNumero());

        tPrincipal.getcCliente().getTxtRua().setText(cliente.getDados().getRua());

        tPrincipal.getcCliente().getTxtNome().setText(cliente.getNome());
        tPrincipal.getcCliente().getTxtCpf().setText(cliente.getCpf());

        tPrincipal.getcCliente().getNascimento().setDate(cliente.getNascimento());

        tPrincipal.getcCliente().getTxtRg().setText(cliente.getRg());

        for (int c = 0; c < tPrincipal.getcCliente().getComboSexo().getItemCount(); c++) {

            if (tPrincipal.getcCliente().getComboSexo().getItemAt(c).equals(cliente.getSexo())) {
                tPrincipal.getcCliente().getComboSexo().setSelectedItem(tPrincipal.getcCliente().getComboSexo().getItemAt(c));
            }
        }
        for (int c = 0; c < tPrincipal.getcFuncionario().getComboUf().getItemCount(); c++) {

            if (tPrincipal.getcFuncionario().getComboUf().getItemAt(c).equals(cliente.getDados().getUf())) {
                tPrincipal.getcFuncionario().getComboUf().setSelectedItem(tPrincipal.getcFuncionario().getComboUf().getItemAt(c));
            }
        }

    }

    private void preencherDadosProfissional(Profissional profissional) {

        tPrincipal.getcProfissioanl().getTxtBairro().setText(profissional.getDados().getBairro());
        tPrincipal.getcProfissioanl().getTxtCelular().setText(profissional.getDados().getCelular());
        tPrincipal.getcProfissioanl().getTxtTelefone().setText(profissional.getDados().getTelefone());
        tPrincipal.getcProfissioanl().getTxtCep().setText(profissional.getDados().getCep());
        tPrincipal.getcProfissioanl().getTxtCidade().setText(profissional.getDados().getCidade());
        tPrincipal.getcProfissioanl().getTxtEmail().setText(profissional.getDados().getEmail());

        tPrincipal.getcProfissioanl().getTxtNumero().setText(profissional.getDados().getNumero());

        tPrincipal.getcProfissioanl().getTxtRua().setText(profissional.getDados().getRua());

        tPrincipal.getcProfissioanl().getTxtNome().setText(profissional.getNome());
        tPrincipal.getcProfissioanl().getTxtCpf().setText(profissional.getCpf());

        tPrincipal.getcProfissioanl().getNascimento().setDate(profissional.getNascimento());

        tPrincipal.getcProfissioanl().getTxtRg().setText(profissional.getRg());

        for (int c = 0; c < tPrincipal.getcFuncionario().getComboSexo().getItemCount(); c++) {

            if (tPrincipal.getcProfissioanl().getComboSexo().getItemAt(c).equals(profissional.getSexo())) {
                tPrincipal.getcProfissioanl().getComboSexo().setSelectedItem(tPrincipal.getcProfissioanl().getComboSexo().getItemAt(c));
            }
        }
        for (int c = 0; c < tPrincipal.getcProfissioanl().getComboUf().getItemCount(); c++) {

            if (tPrincipal.getcProfissioanl().getComboUf().getItemAt(c).equals(profissional.getDados().getUf())) {
                tPrincipal.getcProfissioanl().getComboUf().setSelectedItem(tPrincipal.getcProfissioanl().getComboUf().getItemAt(c));
            }
        }

    }

    private void preencherDadosFornecedor(Fornecedor fornecedor) {

        tPrincipal.getcFornecedor().getTxtBairro().setText(fornecedor.getDados().getBairro());
        tPrincipal.getcFornecedor().getTxtCelular().setText(fornecedor.getDados().getCelular());
        tPrincipal.getcFornecedor().getTxtTelefone().setText(fornecedor.getDados().getTelefone());
        tPrincipal.getcFornecedor().getTxtCep().setText(fornecedor.getDados().getCep());
        tPrincipal.getcFornecedor().getTxtCidade().setText(fornecedor.getDados().getCidade());
        tPrincipal.getcFornecedor().getTxtEmail().setText(fornecedor.getDados().getEmail());

        tPrincipal.getcFornecedor().getTxtNumero().setText(fornecedor.getDados().getNumero());

        tPrincipal.getcFornecedor().getTxtRua().setText(fornecedor.getDados().getRua());

        tPrincipal.getcFornecedor().getTxtNomeFantazia().setText(fornecedor.getNomefantasia());
        tPrincipal.getcFornecedor().getTxtCnpjj().setText(fornecedor.getCnpj());
        tPrincipal.getcFornecedor().getTxtRazaoSociall().setText(fornecedor.getRazaosocial());

        for (int c = 0; c < tPrincipal.getcFornecedor().getComboUf().getItemCount(); c++) {

            if (tPrincipal.getcFornecedor().getComboUf().getItemAt(c).equals(fornecedor.getDados().getUf())) {
                tPrincipal.getcFornecedor().getComboUf().setSelectedItem(tPrincipal.getcFornecedor().getComboUf().getItemAt(c));
            }
        }

    }

    private int retornaIndice(JTable tabela, MouseEvent e) {
        int ro = 0;
        int column = tabela.getColumnModel().getColumnIndexAtX(e.getX());
        int row = e.getY() / tabela.getRowHeight();

        if (row < tabela.getRowCount() && row >= 0 && column < tabela.getColumnCount() && column >= 0) {
            Object value = tabela.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;
                if (boton.getName().equals("editar")) {
                    ro = tabela.getSelectedRow();
                    salvar_editar = false;
                }
            }
        }
        return ro;
    }

}
