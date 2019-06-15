/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Modelo.Cliente;
import br.com.pbd.Modelo.Dados;
import br.com.pbd.Modelo.Fornecedor;
import br.com.pbd.Modelo.Funcionario;
import br.com.pbd.Modelo.Login;
import br.com.pbd.Modelo.Profissional;
import br.com.pbd.Modelo.Render;
import br.com.pbd.fachada.Fachada;
import br.com.pbd.view.DiaMensagem;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

/**
 *
 * @author Andre-Coude
 */
public class Controle extends MouseAdapter implements ActionListener, KeyListener {

    private final Fachada fachada;
    private final TelaPrincipal tPrincipal;
    private List<Cliente> clientes;
    private List<Funcionario> funcionarios;
    private List<Profissional> profissionais;
    private List<Fornecedor> fornecedores;
    private Cliente cliente;
    private Funcionario funcionario;
    private Fornecedor fornecedor;
    private Profissional profissional;
    private int escolha;
    private final int salvar = 1, edicao = 2, exclusao = 3;
    private final HashMap<Integer, Boolean> keyEventos;
    private final DiaMensagem mens;

    public Controle(TelaPrincipal tPrincipal, Fachada fachada) {
        this.tPrincipal = tPrincipal;
        this.fachada = fachada;
        this.clientes = new ArrayList<Cliente>();
        this.funcionarios = new ArrayList<Funcionario>();
        this.profissionais = new ArrayList<Profissional>();
        this.fornecedores = new ArrayList<Fornecedor>();
        this.keyEventos = new HashMap<Integer, Boolean>();
        this.mens = new DiaMensagem(tPrincipal, true);

        adicionarEvents();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tPrincipal.getcCliente().getTabelaCliente()) {

            int ro = retornaIndice(tPrincipal.getcCliente().getTabelaCliente(), e);
            if (escolha == edicao) {
                tPrincipal.getcCliente().getPainelItens().setSelectedComponent(tPrincipal.getcCliente().getPainelCadastro());
                tPrincipal.getcCliente().getPainelCadastro().setEnabled(true);
                cliente = clientes.get(ro);
                tPrincipal.getcCliente().preencherDados(cliente);
                tPrincipal.ativarEdicaoLogin(false);
            } else if (escolha == exclusao) {

            }
        }
        if (e.getSource() == tPrincipal.getcFuncionario().getTabelaFuncionarios()) {

            int ro = retornaIndice(tPrincipal.getcFuncionario().getTabelaFuncionarios(), e);
            if (escolha == edicao) {
                tPrincipal.getcFuncionario().getPainelItens().setSelectedComponent(tPrincipal.getcFuncionario().getPainelCadastro());
                tPrincipal.getcFuncionario().getPainelCadastro().setEnabled(true);
                funcionario = funcionarios.get(ro);
                tPrincipal.ativarEdicaoLogin(false);
                tPrincipal.getcFuncionario().preencherDados(funcionario);
            } else if (escolha == exclusao) {

            }
        }
        if (e.getSource() == tPrincipal.getcProfissioanl().getTabelaProfissionais()) {

            int ro = retornaIndice(tPrincipal.getcProfissioanl().getTabelaProfissionais(), e);
            if (escolha == edicao) {
                tPrincipal.getcProfissioanl().getPainelItens().setSelectedComponent(tPrincipal.getcProfissioanl().getPainelCadastro());
                tPrincipal.getcProfissioanl().getPainelCadastro().setEnabled(true);
                profissional = profissionais.get(ro);
                tPrincipal.ativarEdicaoLogin(false);
                tPrincipal.getcProfissioanl().preencherDados(profissional);
            } else if (escolha == exclusao) {

            }
        }
        if (e.getSource() == tPrincipal.getcFornecedor().getTabelaFornecedor()) {

            int ro = retornaIndice(tPrincipal.getcFornecedor().getTabelaFornecedor(), e);
            if (escolha == edicao) {
                tPrincipal.getcFornecedor().getPainelItens().setSelectedComponent(tPrincipal.getcFornecedor().getPainelCadastro());
                tPrincipal.getcFornecedor().getPainelCadastro().setEnabled(true);
                fornecedor = fornecedores.get(ro);
                tPrincipal.getcFornecedor().preencherDados(fornecedor);
            } else if (escolha == exclusao) {

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tPrincipal.getcCliente().getBtnCadastrarCliente()) {
            escolha = salvar;
            tPrincipal.getcCliente().limparComponentes();
        }
        if (e.getSource() == tPrincipal.getBtnClientes()) {
            clientes = fachada.getAllCliente();
            listarClientes(clientes);
        }

        if (e.getSource() == tPrincipal.getcCliente().getBtnSalvar()) {
            switch (escolha) {
                case salvar:
                    cadastrarCliente();
                    break;
                case edicao:
                    editarCliente(cliente);
                    break;
                default:
                    break;
            }

        }

        if (e.getSource() == tPrincipal.getcFuncionario().getBtnNovoFuncionario()) {
            escolha = salvar;
            tPrincipal.ativarEdicaoLogin(true);
            tPrincipal.getcFuncionario().limparComponentes();
        }
        if (e.getSource() == tPrincipal.getcFuncionario().getBtnSalvar()) {

            switch (escolha) {
                case salvar:
                    cadastrarFuncionario();
                    break;
                case edicao:
                    editarFuncionario(funcionario);
                    break;
                default:
                    break;
            }
        }

        if (e.getSource() == tPrincipal.getCadastros().getBtnFuncionario()) {
            funcionarios = fachada.getAllFuncionario();
            listarFuncionarios(funcionarios);
        }

        if (e.getSource() == tPrincipal.getcFornecedor().getBtnNovoFornecedor()) {
            escolha = salvar;
            tPrincipal.getcFornecedor().limparComponentes();
        }

        if (e.getSource() == tPrincipal.getCadastros().getBtnFornecedor()) {
            fornecedores = fachada.getAllFornecedor();
            listarFornecedores(fornecedores);
        }
        if (e.getSource() == tPrincipal.getcFornecedor().getBtnSalvar()) {

            switch (escolha) {
                case salvar:
                    cadastrarFornecedor();
                    break;
                case edicao:
                    editarFornecedor(fornecedor);
                    break;
                default:
                    break;
            }
        }

        if (e.getSource() == tPrincipal.getCadastros().getBtnProfissional()) {
            profissionais = fachada.getAllProfissionals();
            listarProfissionais(profissionais);
        }

        if (e.getSource() == tPrincipal.getcProfissioanl().getBtnNovoProfissional()) {
            escolha = salvar;
            tPrincipal.ativarEdicaoLogin(true);
            tPrincipal.getcProfissioanl().limparComponentes();

        }

        if (e.getSource() == tPrincipal.getcProfissioanl().getBtnSalvar()) {

            switch (escolha) {
                case salvar:
                    cadastrarProfissional();
                    break;
                case edicao:
                    editarProfissional(profissional);
                    break;
                default:
                    break;
            }

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

        cliente.setRg(tPrincipal.getcCliente().getTxtRg().getText());
        cliente.setDados(dados);

        try {

            java.sql.Date nascimento = ConverterData(tPrincipal.getcCliente().getNascimento().getDate());
            cliente.setNascimento(nascimento);

            fachada.salvar(cliente);

            mens.setLblMens(tPrincipal.getcCliente().getCADASTRO());
            mens.setVisible(true);
            tPrincipal.getcCliente().getPainelItens().setSelectedComponent(tPrincipal.getcCliente().getPainelCliente());
            tPrincipal.getcCliente().getPainelCadastro().setEnabled(false);
            clientes = fachada.getAllCliente();
            listarClientes(clientes);
        } catch (java.lang.IllegalStateException | javax.persistence.RollbackException n) {
            mens.setLblMens(tPrincipal.getCAMPOS());
            mens.setVisible(true);
        } catch (java.lang.NullPointerException roll) {
            mens.setLblMens("SELECIONE A DATA DE NASCIMENTO!");
            mens.setVisible(true);
        }

    }

    public void cadastrarFuncionario() {

        Login login = new Login();
        String senha = new String(tPrincipal.getcFuncionario().getTxtSenha().getPassword());
        String confirmSenha = new String(tPrincipal.getcFuncionario().getTxtConfimarSenha().getPassword());
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

        funcionario.setRg(tPrincipal.getcFuncionario().getTxtRg().getText());

        try {
            java.sql.Date nascimento = ConverterData(tPrincipal.getcFuncionario().getNascimento().getDate());
            funcionario.setNascimento(nascimento);
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

            if (senha.equals(confirmSenha)) {
                fachada.salvar(funcionario);

                mens.setLblMens(tPrincipal.getcFuncionario().getCADASTRO());
                mens.setVisible(true);
                tPrincipal.getcFuncionario().getPainelItens().setSelectedComponent(tPrincipal.getcFuncionario().getPainelFuncionario());
                tPrincipal.getcFuncionario().getPainelCadastro().setEnabled(false);
                funcionarios = fachada.getAllFuncionario();
                listarFuncionarios(funcionarios);
            } else {
                mens.setLblMens(tPrincipal.getSENHA());
                mens.setVisible(true);
            }
        } catch (java.lang.IllegalStateException n) {
            mens.setLblMens(tPrincipal.getCAMPOS());
            mens.setVisible(true);
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            mens.setLblMens("ERRO AO SALVAR LOGIN!");
            mens.setVisible(true);
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
            fachada.salvar(fornecedor);
            JOptionPane.showMessageDialog(null, "Fornecedor cadastrado!");
            tPrincipal.getcFornecedor().getPainelItens().setSelectedComponent(tPrincipal.getcFornecedor().getPainelFornecedor());
            tPrincipal.getcFornecedor().getPainelCadastro().setEnabled(false);
            fornecedores = fachada.getAllFornecedor();;
            listarFornecedores(fornecedores);
        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    private void cadastrarProfissional() {

        Login login = new Login();
        String senha = new String(tPrincipal.getcProfissioanl().getTxtSenha().getPassword());
        String confirmSenha = new String(tPrincipal.getcProfissioanl().getTxtConfimarSenha().getPassword());

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

        try {

            java.sql.Date nascimento = ConverterData(tPrincipal.getcProfissioanl().getNascimento().getDate());

            profissional.setNascimento(nascimento);

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

            if (senha.equals(confirmSenha)) {
                fachada.salvar(profissional);

                JOptionPane.showMessageDialog(null, "Profissional cadastrado!");
                tPrincipal.getcProfissioanl().getPainelItens().setSelectedComponent(tPrincipal.getcProfissioanl().getPainelProfissional());
                tPrincipal.getcProfissioanl().getPainelCadastro().setEnabled(false);
                profissionais = fachada.getAllProfissionals();
                listarProfissionais(profissionais);
            } else {
                mens.setLblMens("SENHAS DIFERENTES!");
                mens.setVisible(true);
            }
        } catch (java.lang.IllegalStateException | javax.persistence.RollbackException n) {
            mens.setLblMens(n.getMessage());
            mens.setVisible(true);
        } catch (java.lang.NullPointerException roll) {
            mens.setLblMens("SELECIONE A DATA DE NASCIMENTO!");
            mens.setVisible(true);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(Controle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listarClientes(List<Cliente> lista) {

        tPrincipal.getcCliente().getTabelaCliente().setDefaultRenderer(Object.class, new Render());
        int i = 0;
        try {
            String[] colunas = new String[]{"NOME", "SEXO", "DATA DE NASCIMENTO", "CPF", "CELULAR", "EDITAR", "EXCLUIR"};
            Object[][] dados = new Object[lista.size()][7];
            for (Cliente a : lista) {
                dados[i][0] = a.getNome();
                dados[i][1] = a.getSexo();
                dados[i][2] = a.getNascimento();
                dados[i][3] = a.getCpf();
                dados[i][4] = a.getDados().getCelular();
                dados[i][5] = tPrincipal.getBtnEditar();
                dados[i][6] = tPrincipal.getBtnExcluir();
                i++;
            }
            DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tPrincipal.getcCliente().getTabelaCliente().setModel(dataModel);
        } catch (Exception ex) {

        }

    }

    public void listarFuncionarios(List<Funcionario> lista) {

        tPrincipal.getcFuncionario().getTabelaFuncionarios().setDefaultRenderer(Object.class, new Render());
        int i = 0;
        try {
            String[] colunas = new String[]{"NOME", "SEXO", "DATA DE NASCIMENTO", "CPF", "FUNÇÃO", "CELULAR", "EDITAR", "EXCLUIR"};
            Object[][] dados = new Object[lista.size()][8];
            for (Funcionario a : lista) {
                dados[i][0] = a.getNome();
                dados[i][1] = a.getSexo();
                dados[i][2] = a.getNascimento();
                dados[i][3] = a.getCpf();
                dados[i][4] = a.getTipo();
                dados[i][5] = a.getDados().getCelular();
                dados[i][6] = tPrincipal.getBtnEditar();
                dados[i][7] = tPrincipal.getBtnExcluir();
                i++;
            }
            DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tPrincipal.getcFuncionario().getTabelaFuncionarios().setModel(dataModel);
        } catch (Exception ex) {

        }

    }

    public void listarProfissionais(List<Profissional> lista) {

        tPrincipal.getcProfissioanl().getTabelaProfissionais().setDefaultRenderer(Object.class, new Render());
        int i = 0;
        try {
            String[] colunas = new String[]{"NOME", "SEXO", "DATA DE NASCIMENTO", "CPF", "FUNÇÃO", "CELULAR", "EDITAR", "EXCLUIR"};
            Object[][] dados = new Object[lista.size()][9];
            for (Profissional a : lista) {
                dados[i][0] = a.getNome();
                dados[i][1] = a.getSexo();
                dados[i][2] = a.getNascimento();
                dados[i][3] = a.getCpf();
                dados[i][4] = a.getTipo();
                dados[i][5] = a.getDados().getCelular();
                dados[i][6] = tPrincipal.getBtnEditar();
                dados[i][7] = tPrincipal.getBtnExcluir();
                i++;
            }
            DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tPrincipal.getcProfissioanl().getTabelaProfissionais().setModel(dataModel);
        } catch (Exception ex) {

        }

    }

    public void listarFornecedores(List<Fornecedor> lista) {

        tPrincipal.getcFornecedor().getTabelaFornecedor().setDefaultRenderer(Object.class, new Render());

        int i = 0;
        try {
            String[] colunas = new String[]{"NOME", "RAZÃO SOCIAL", "CNPJ", "CIDADE", "CONTATO", "EDITAR", "EXCLUIR"};
            Object[][] dados = new Object[lista.size()][7];
            for (Fornecedor a : lista) {
                dados[i][0] = a.getNomefantasia();
                dados[i][1] = a.getRazaosocial();
                dados[i][2] = a.getCnpj();
                dados[i][3] = a.getDados().getCidade();
                dados[i][4] = a.getDados().getCelular();
                dados[i][5] = tPrincipal.getBtnEditar();
                dados[i][6] = tPrincipal.getBtnExcluir();
                i++;
            }
            DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tPrincipal.getcFornecedor().getTabelaFornecedor().setModel(dataModel);
        } catch (Exception ex) {

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
        cliente.setRg(tPrincipal.getcCliente().getTxtRg().getText());

        try {

            try {
                java.sql.Date nascimento = ConverterData(tPrincipal.getcCliente().getNascimento().getDate());
                cliente.setNascimento(nascimento);
            } catch (java.lang.NullPointerException roll) {
            }

            fachada.salvar(cliente);

            mens.setLblMens(tPrincipal.getEDICAO());
            mens.setVisible(true);
            tPrincipal.getcCliente().getPainelItens().setSelectedComponent(tPrincipal.getcCliente().getPainelCliente());
            tPrincipal.getcCliente().getPainelCadastro().setEnabled(false);
            clientes = fachada.getAllCliente();
            listarClientes(clientes);
        } catch (java.lang.IllegalStateException | javax.persistence.RollbackException n) {
            mens.setLblMens(tPrincipal.getCAMPOS());
            mens.setVisible(true);
        }

    }

    public void editarFuncionario(Funcionario funcionario) {

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
        funcionario.setRg(tPrincipal.getcFuncionario().getTxtRg().getText());

        try {

            java.sql.Date nascimento = ConverterData(tPrincipal.getcFuncionario().getNascimento().getDate());
            funcionario.setNascimento(nascimento);
            fachada.salvar(funcionario);

            mens.setLblMens(tPrincipal.getEDICAO());
            mens.setVisible(true);
            tPrincipal.getcFuncionario().getPainelItens().setSelectedComponent(tPrincipal.getcFuncionario().getPainelFuncionario());
            tPrincipal.getcFuncionario().getPainelCadastro().setEnabled(false);
            funcionarios = fachada.getAllFuncionario();
            listarFuncionarios(funcionarios);
        } catch (java.lang.IllegalStateException n) {
            mens.setLblMens(tPrincipal.getCAMPOS());
            mens.setVisible(true);
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    private void editarFornecedor(Fornecedor forn) {

        forn.getDados().setBairro(tPrincipal.getcFornecedor().getTxtBairro().getText());
        forn.getDados().setCelular(tPrincipal.getcFornecedor().getTxtCelular().getText());
        forn.getDados().setTelefone(tPrincipal.getcFornecedor().getTxtTelefone().getText());
        forn.getDados().setCep(tPrincipal.getcFornecedor().getTxtCep().getText());
        forn.getDados().setCidade(tPrincipal.getcFornecedor().getTxtCidade().getText());
        forn.getDados().setEmail(tPrincipal.getcFornecedor().getTxtEmail().getText());

        String numb = tPrincipal.getcFornecedor().getTxtNumero().getText() + " "
                + tPrincipal.getcFornecedor().getTxtComplemento().getText();

        forn.getDados().setNumero(numb);
        forn.getDados().setRua(tPrincipal.getcFornecedor().getTxtRua().getText());
        forn.getDados().setUf(tPrincipal.getcFornecedor().getComboUf().getSelectedItem().toString());

        forn.setCnpj(tPrincipal.getcFornecedor().getTxtCnpjj().getText());
        forn.setNomefantasia(tPrincipal.getcFornecedor().getTxtNomeFantazia().getText());
        forn.setRazaosocial(tPrincipal.getcFornecedor().getTxtRazaoSociall().getText());

        try {
            fachada.salvar(forn);
            JOptionPane.showMessageDialog(null, "Edição Concluida!");
            tPrincipal.getcFornecedor().getPainelItens().setSelectedComponent(tPrincipal.getcFornecedor().getPainelFornecedor());
            tPrincipal.getcFornecedor().getPainelCadastro().setEnabled(false);
            fornecedores = fachada.getAllFornecedor();
            listarFornecedores(fornecedores);
        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    private void editarProfissional(Profissional pro) {

        pro.getDados().setBairro(tPrincipal.getcProfissioanl().getTxtBairro().getText());
        pro.getDados().setCelular(tPrincipal.getcProfissioanl().getTxtCelular().getText());
        pro.getDados().setTelefone(tPrincipal.getcProfissioanl().getTxtTelefone().getText());
        pro.getDados().setCep(tPrincipal.getcProfissioanl().getTxtCep().getText());
        pro.getDados().setCidade(tPrincipal.getcProfissioanl().getTxtCidade().getText());
        pro.getDados().setEmail(tPrincipal.getcProfissioanl().getTxtEmail().getText());

        String numb = tPrincipal.getcProfissioanl().getTxtNumero().getText() + " "
                + tPrincipal.getcProfissioanl().getTxtComplemento().getText();

        pro.getDados().setNumero(numb);
        pro.getDados().setRua(tPrincipal.getcProfissioanl().getTxtRua().getText());
        pro.getDados().setUf(tPrincipal.getcProfissioanl().getComboUf().getSelectedItem().toString());

        pro.setCrmv(tPrincipal.getcProfissioanl().getComboCrmv().getSelectedItem().toString());
        pro.setCpf(tPrincipal.getcProfissioanl().getTxtCpf().getText());
        pro.setRg(tPrincipal.getcProfissioanl().getTxtRg().getText());
        pro.setNome(tPrincipal.getcProfissioanl().getTxtNome().getText());
        pro.setSexo(tPrincipal.getcProfissioanl().getComboSexo().getSelectedItem().toString());
        pro.setTipo(tPrincipal.getcProfissioanl().getComboEspecialidade().getSelectedItem().toString());

        try {
            java.sql.Date nascimento = ConverterData(tPrincipal.getcProfissioanl().getNascimento().getDate());

            pro.setNascimento(nascimento);

            fachada.salvar(pro);

            JOptionPane.showMessageDialog(null, "Edição concluida!");
            tPrincipal.getcProfissioanl().getPainelItens().setSelectedComponent(tPrincipal.getcProfissioanl().getPainelProfissional());
            tPrincipal.getcProfissioanl().getPainelCadastro().setEnabled(false);
            profissionais = fachada.getAllProfissionals();
            listarProfissionais(profissionais);
        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    public java.sql.Date ConverterData(java.util.Date date) {
        return new java.sql.Date(date.getTime());
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
                ro = tabela.getSelectedRow();
                if (boton.getName().equals("editar")) {
                    escolha = edicao;
                } else if (boton.getName().equals("excluir")) {
                    escolha = exclusao;
                } else {
                    escolha = 0;
                }
            }
        }
        return ro;
    }

    // eventos de teclas
    @Override
    public void keyPressed(KeyEvent e) {
        keyEventos.put(e.getKeyCode(), true);

        if (e.getSource() == tPrincipal.getcCliente().getTxtPesquisa()) {
            String nome = tPrincipal.getcCliente().getTxtPesquisa().getText();
            clientes = fachada.buscaCliente(nome);
            listarClientes(clientes);
        }
        if (e.getSource() == tPrincipal.getcFuncionario().getTxtPesquisa()) {
            String nome = tPrincipal.getcFuncionario().getTxtPesquisa().getText();
            funcionarios = fachada.buscaFuncionario(nome);
            listarFuncionarios(funcionarios);
        }
        if (e.getSource() == tPrincipal.getcFornecedor().getTxtPesquisa()) {
            String nome = tPrincipal.getcFornecedor().getTxtPesquisa().getText();
            fornecedores = fachada.buscaFornecedors(nome);
            listarFornecedores(fornecedores);
        }
        if (e.getSource() == tPrincipal.getcProfissioanl().getTxtPesquisa()) {
            String nome = tPrincipal.getcProfissioanl().getTxtPesquisa().getText();
            profissionais = fachada.buscaProfissionals(nome);
            listarProfissionais(profissionais);
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyEventos.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void adicionarEvents() {
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

        tPrincipal.getcCliente().getTxtPesquisa().addKeyListener(this);
        tPrincipal.getcFornecedor().getTxtPesquisa().addKeyListener(this);
        tPrincipal.getcFuncionario().getTxtPesquisa().addKeyListener(this);
        tPrincipal.getcProfissioanl().getTxtPesquisa().addKeyListener(this);
    }

}
