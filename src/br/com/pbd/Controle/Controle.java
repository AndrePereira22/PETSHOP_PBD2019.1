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
import br.com.pbd.view.TelaLogin;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Andre-Coude
 */
public class Controle implements ActionListener {

    TelaPrincipal tPrincipal;

    public Controle(TelaPrincipal tPrincipal) {
        this.tPrincipal = tPrincipal;

        tPrincipal.getcFuncionario().getBtnSalvar().addActionListener(this);
        tPrincipal.getcCliente().getBtnSalvar().addActionListener(this);
        tPrincipal.getcFornecedor().getBtnSalvar().addActionListener(this);
        tPrincipal.getcProfissioanl().getBtnSalvar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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

        new GenericDao<Cliente>().salvar_ou_atualizar(cliente);
        tPrincipal.getcCliente().setVisible(false);
    }

    public void cadastrarFuncionario() {

        Login login = new Login();
        login.setSenha(tPrincipal.getcFuncionario().getTxtSenha().getPassword().toString());
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

        new GenericDao<Funcionario>().salvar_ou_atualizar(func);
            tPrincipal.getcFuncionario().setVisible(false);
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
        fornecedor.setCnpj(tPrincipal.getcFornecedor().getTxtCnpj().getText());
        fornecedor.setNomefantasia(tPrincipal.getcFornecedor().getTxtNomeFantasia().getText());
        fornecedor.setRazaosocial(tPrincipal.getcFornecedor().getTxtNome().getText());

        new GenericDao<Fornecedor>().salvar_ou_atualizar(fornecedor);
        tPrincipal.getcFornecedor().setVisible(false);
    }

    private void cadastrarProfissional() {

        
        Login login = new Login();
        login.setSenha(tPrincipal.getcProfissioanl().getTxtSenha().getPassword().toString());
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
        new GenericDao<Profissional>().salvar_ou_atualizar(profissional);
        tPrincipal.getcProfissioanl().setVisible(false);
    }

    public java.sql.Date ConverterData(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }
}
