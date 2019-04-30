/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.DaoAgenda;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Agenda;
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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andre-Coude
 */
public class Controle implements ActionListener {

    TelaPrincipal tPrincipal;
    List<Cliente> clientes;

    public Controle(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;

        clientes = new ArrayList<Cliente>();

        tPrincipal.getcFuncionario().getBtnSalvar().addActionListener(this);
        tPrincipal.getcCliente().getBtnSalvar().addActionListener(this);
        tPrincipal.getcFornecedor().getBtnSalvar().addActionListener(this);
        tPrincipal.getcProfissioanl().getBtnSalvar().addActionListener(this);
        tPrincipal.getBtnClientes().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tPrincipal.getBtnClientes()) {
            listarClientes();
        }
        if (e.getSource() == tPrincipal.getcFuncionario().getBtnSalvar()) {
            cadastrarFuncionario();
        }
        if (e.getSource() == tPrincipal.getcCliente().getBtnSalvar()) {
            cadastrarCliente();
        }
        if (e.getSource() == tPrincipal.getcFornecedor().getBtnSalvar()) {
            cadastrarFornecedor();

        }
        if (e.getSource() == tPrincipal.getcProfissioanl().getBtnSalvar()) {
            cadastrarProfissional();

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
        dados.setRua(tPrincipal.getcFuncionario().getTxtRua().getText());
        dados.setUf(tPrincipal.getcFuncionario().getComboUf().getSelectedItem().toString());

        Cliente cliente = new Cliente();

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
        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }

    }

    public void cadastrarFuncionario() {

        Login login = new Login();
        String senha = new String(tPrincipal.getcFuncionario().getTxtSenha().getPassword());
        login.setSenha(senha);
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

        Funcionario func = new Funcionario();

        func.setNome(tPrincipal.getcFuncionario().getTxtNome().getText());
        func.setCpf(tPrincipal.getcFuncionario().getTxtCpf().getText());
        func.setSexo(tPrincipal.getcFuncionario().getComboSexo().getSelectedItem().toString());
        func.setTipo(tPrincipal.getcFuncionario().getComboTipo().getSelectedItem().toString());

        // conversao de jdatechooser para sql date
        java.sql.Date nascimento = ConverterData(tPrincipal.getcFuncionario().getNascimento().getDate());

        func.setNascimento(nascimento);
        func.setRg(tPrincipal.getcFuncionario().getTxtRg().getText());

        func.setDados(dados);
        func.setLogin(login);

        try {
            new GenericDao<Funcionario>().salvar_ou_atualizar(func);

            JOptionPane.showMessageDialog(null, "Funcionario cadastrado!");
            tPrincipal.getcFuncionario().getPainelItens().setSelectedComponent(tPrincipal.getcFuncionario().getPainelFuncionario());
            tPrincipal.getcFuncionario().getPainelCadastro().setEnabled(false);

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
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

        Fornecedor fornecedor = new Fornecedor();

        fornecedor.setDados(dados);
        fornecedor.setCnpj(tPrincipal.getcFornecedor().getTxtCnpjj().getText());
        fornecedor.setNomefantasia(tPrincipal.getcFornecedor().getTxtNomeFantazia().getText());
        fornecedor.setRazaosocial(tPrincipal.getcFornecedor().getTxtRazaoSociall().getText());

        try {
            new GenericDao<Fornecedor>().salvar_ou_atualizar(fornecedor);
            JOptionPane.showMessageDialog(null, "Fornecedor cadastrado!");
            tPrincipal.getcFornecedor().getPainelItens().setSelectedComponent(tPrincipal.getcFornecedor().getPainelFornecedor());
            tPrincipal.getcFornecedor().getPainelCadastro().setEnabled(false);

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    private void cadastrarProfissional() {

        Login login = new Login();
        String senha = new String(tPrincipal.getcProfissioanl().getTxtSenha().getPassword());
        login.setSenha(senha);
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

        Profissional profissional = new Profissional();

        profissional.setDados(dados);
        profissional.setLogin(login);
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

            JOptionPane.showMessageDialog(null, "Profissional cadastrado!");
            tPrincipal.getcProfissioanl().getPainelItens().setSelectedComponent(tPrincipal.getcProfissioanl().getPainelProfissional());
            tPrincipal.getcProfissioanl().getPainelCadastro().setEnabled(false);

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    public void listarClientes() {

        clientes = new GenericDao<Cliente>().getAll(Cliente.class);

        if (!clientes.isEmpty()) {

            tPrincipal.getcCliente().getTabelaCliente().setDefaultRenderer(Object.class, new Render());

            int i = 0;
            try {
                String[] colunas = new String[]{"NOME", "SEXO", "DATA DE NASCIMENTO", "CPF", "CELULAR"};
                Object[][] dados = new Object[clientes.size()][5];
                for (Cliente a : clientes) {
                    dados[i][0] = a.getNome();
                    dados[i][1] = a.getSexo();
                    dados[i][2] = a.getNascimento();
                    dados[i][3] = a.getCpf();
                    dados[i][4] = a.getDados().getCelular();

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

    public java.sql.Date ConverterData(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }
}
