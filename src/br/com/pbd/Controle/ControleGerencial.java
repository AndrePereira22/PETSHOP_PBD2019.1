/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Controle;

import br.com.pbd.Dao.DaoLoja;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.EntradaEstoque;
import br.com.pbd.Modelo.Funcionario;
import br.com.pbd.Modelo.GrupoProduto;
import br.com.pbd.Modelo.Loja;
import br.com.pbd.Modelo.Produto;
import br.com.pbd.Modelo.Profissional;
import br.com.pbd.Modelo.Render;
import br.com.pbd.Modelo.Vacina;
import br.com.pbd.fachada.Fachada;
import br.com.pbd.view.DiaLogin;
import br.com.pbd.view.TelaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.List;
import java.util.Random;
import javax.persistence.NoResultException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Andre-Coude
 */
public class ControleGerencial extends MouseAdapter implements ActionListener {

    private final Fachada fachada;
    private final TelaPrincipal tPrincipal;
    private Loja loja;
    private Vacina vacina;
    private List<GrupoProduto> grupos;
    private List<Produto> produtos;
    private List<Profissional> profissionais;
    private List<Funcionario> funcionarios;
    private Produto produto;
    private final int reset = 1, edicao = 2, adicionar = 3;
    private int opcao = 0;
    private Funcionario funcionario;
    private Profissional profissional;
    private final DiaLogin diaSenha;
    private final String FUNCIONARIO = "FUNCIONARIO", PROFISSIONAL = "PROFISSIONAL";
    private List<Vacina> vacinas;

    public ControleGerencial(TelaPrincipal tPrincipal, Fachada fachada) {
        this.tPrincipal = tPrincipal;
        this.fachada = fachada;
        diaSenha = new DiaLogin(tPrincipal, true);

        tPrincipal.getBtnGerencia().addActionListener(this);
        tPrincipal.getGerencia().getBtnEditar().addActionListener(this);
        tPrincipal.getGerencia().getBtnEditar().addActionListener(this);
        tPrincipal.getcLoja().getBtnSalvar().addActionListener(this);
        tPrincipal.getcLoja().getBtnCancelar().addActionListener(this);
        tPrincipal.getGerencia().getComboGrupo().addActionListener(this);
        tPrincipal.getGerencia().getTabelaProdutos().addMouseListener(this);
        tPrincipal.getGerencia().getTabelaUsuarios().addMouseListener(this);
        tPrincipal.getGerencia().getComboUsuario().addActionListener(this);
        tPrincipal.geteProdutos().getBtnSalvar().addActionListener(this);
        tPrincipal.getCadastros().getBtnVacinas().addActionListener(this);
        tPrincipal.getVacina().getBtnSalvarVacina().addActionListener(this);
        tPrincipal.getVacina().getTabelaVacinas().addMouseListener(this);
        diaSenha.getBtnOk().addActionListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tPrincipal.getGerencia().getTabelaProdutos()) {
            int ro = retornaIndice(tPrincipal.getGerencia().getTabelaProdutos(), e);
            try {
                produto = produtos.get(ro);
                tPrincipal.getGerencia().setVisible(false);
                tPrincipal.geteProdutos().prencherProduto(produto);
                tPrincipal.geteProdutos().setVisible(true);
            } catch (java.lang.NullPointerException x) {
            }

        }
        if (e.getSource() == tPrincipal.getVacina().getTabelaVacinas()) {
            int ro = retornaIndice(tPrincipal.getVacina().getTabelaVacinas(), e);
            try {
                vacina = vacinas.get(ro);
                tPrincipal.getVacina().limparComponentes(true);
                preencherVacina(vacina);
            } catch (java.lang.NullPointerException x) {
            }

        }
        if (e.getSource() == tPrincipal.getGerencia().getTabelaUsuarios()) {
            int ro = retornaIndice(tPrincipal.getGerencia().getTabelaUsuarios(), e);

            String user = tPrincipal.getGerencia().getComboUsuario().getSelectedItem().toString();
            if (user.equals(FUNCIONARIO)) {
                controleSenhaFu(ro);
            } else if (user.equals(PROFISSIONAL)) {
                controleSenhaPro(ro);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == tPrincipal.getCadastros().getBtnVacinas()) {

            vacinas = fachada.getAllVacina();
            listarVacinas(vacinas);
        }
        if (e.getSource() == tPrincipal.getVacina().getBtnSalvarVacina()) {
            if (opcao == edicao) {
                editarVacina(vacina);
            } else {
                salvarVacina();
            }
        }

        if (e.getSource() == tPrincipal.getBtnGerencia()) {
            buscarLoja();
            preencherGrupo();
            escolherUsuario();

        }
        if (e.getSource() == diaSenha.getBtnOk()) {

            String senha = new String(diaSenha.getTxtSenha().getPassword());
            String Confirmsenha = new String(diaSenha.getTxtConfimarSenha().getPassword());
            String user = tPrincipal.getGerencia().getComboUsuario().getSelectedItem().toString();
            String senhaHex = "";
            try {
                // criptografar senha
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte messageDigest[] = md.digest(senha.getBytes("UTF-8"));
                StringBuilder ab = new StringBuilder();

                for (byte b : messageDigest) {
                    ab.append(String.format("%02X", 0xFF & b));

                }
                senhaHex = ab.toString();
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            }
            if (user.equals(FUNCIONARIO)) {
                funcionario.getLogin().setSenha(senhaHex);
                if (senha.equals(Confirmsenha)) {
                    fachada.salvar(funcionario);
                    diaSenha.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Senha Atualizada");
                } else {
                    JOptionPane.showMessageDialog(null, "Senhas diferentes");
                }

            } else if (user.equals(PROFISSIONAL)) {
                profissional.getLogin().setSenha(senhaHex);
                if (senha.equals(Confirmsenha)) {
                    fachada.salvar(profissional);
                    diaSenha.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Senha Atualizada");
                } else {
                    JOptionPane.showMessageDialog(null, "Senhas diferentes");
                }

            }

        }
        if (e.getSource() == tPrincipal.getGerencia().getComboUsuario()) {
            escolherUsuario();

        }

        if (e.getSource() == tPrincipal.geteProdutos().getBtnSalvar()) {
            salvarEntradaProdutos();

        }
        if (e.getSource() == tPrincipal.getGerencia().getComboGrupo()) {
            buscaDeProdutos();
        }

        if (e.getSource() == tPrincipal.getGerencia().getBtnEditar()) {

            if (loja != null) {
                tPrincipal.getcLoja().preencherDados(loja);
            }
        }
        if (e.getSource() == tPrincipal.getcLoja().getBtnSalvar()) {

            editarLoja(loja);

        }
        if (e.getSource() == tPrincipal.getcLoja().getBtnCancelar()) {
            tPrincipal.getcLoja().setVisible(false);
        }

    }

    private void listarProdutos(List<Produto> lista) {

        tPrincipal.getGerencia().getTabelaProdutos().setDefaultRenderer(Object.class, new Render());

        int i = 0;
        try {
            String[] colunas = new String[]{"CODIGO", "NOME", "FABRICANTE", "VALOR DE VENDA", "FORNECEDOR", "ESTOQUE ", "NOVA ENTRADA "};
            Object[][] dados = new Object[lista.size()][7];
            for (Produto a : lista) {
                dados[i][0] = a.getCodigo();
                dados[i][1] = a.getDescricao();
                dados[i][2] = a.getFabricante();
                dados[i][3] = a.getValorvenda();
                dados[i][4] = a.getFornecedor().getNomefantasia();
                dados[i][5] = a.getQuantidae_estoque();
                dados[i][6] = tPrincipal.getGerencia().getBtnAdicionar();

                i++;
            }

            DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tPrincipal.getGerencia().getTabelaProdutos().setModel(dataModel);
        } catch (Exception ex) {

        }

    }

    private void preencherGrupo() {
        grupos = fachada.getAllGrupo();
        tPrincipal.getGerencia().getComboGrupo().removeAllItems();
        grupos.forEach((c) -> {
            tPrincipal.getGerencia().getComboGrupo().addItem(c.getDescricao());
        });
        buscaDeProdutos();

    }

    private void listarFuncionarios(List<Funcionario> lista) {

        tPrincipal.getGerencia().getTabelaUsuarios().setDefaultRenderer(Object.class, new Render());

        int i = 0;
        try {
            String[] colunas = new String[]{"NOME", "FUNÇÃO", "EDITAR", "RESET"};
            Object[][] dados = new Object[lista.size()][4];
            for (Funcionario a : lista) {
                dados[i][0] = a.getNome();
                dados[i][1] = a.getTipo();
                dados[i][2] = tPrincipal.getGerencia().getBtnEdicao();
                dados[i][3] = tPrincipal.getGerencia().getBtnReset();
                i++;
            }

            DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tPrincipal.getGerencia().getTabelaUsuarios().setModel(dataModel);
        } catch (Exception ex) {

        }
    }

    private void listarProfissionais(List<Profissional> lista) {

        tPrincipal.getGerencia().getTabelaUsuarios().setDefaultRenderer(Object.class, new Render());

        int i = 0;
        try {
            String[] colunas = new String[]{"NOME", "FUNÇÃO", "EDITAR", "RESET"};
            Object[][] dados = new Object[lista.size()][4];
            for (Profissional a : lista) {
                dados[i][0] = a.getNome();
                dados[i][1] = a.getTipo();
                dados[i][2] = tPrincipal.getGerencia().getBtnEdicao();
                dados[i][3] = tPrincipal.getGerencia().getBtnReset();
                i++;
            }

            DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tPrincipal.getGerencia().getTabelaUsuarios().setModel(dataModel);
        } catch (Exception ex) {

        }
    }

    private void listarVacinas(List<Vacina> lista) {

        tPrincipal.getVacina().getTabelaVacinas().setDefaultRenderer(Object.class, new Render());

        int i = 0;
        try {
            String[] colunas = new String[]{"CODIGO", "NOME", "NUMERO DE DOSES", "PEIODO", "EDITAR", "EXCLUIR"};
            Object[][] dados = new Object[lista.size()][6];
            for (Vacina a : lista) {
                dados[i][0] = a.getCodigo();
                dados[i][1] = a.getNome();
                dados[i][2] = a.getDoses();
                dados[i][3] = a.getPeiodo();
                dados[i][4] = tPrincipal.getBtnEditar();
                dados[i][5] = tPrincipal.getBtnExcluir();
                i++;
            }

            DefaultTableModel dataModel = new DefaultTableModel(dados, colunas) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tPrincipal.getVacina().getTabelaVacinas().setModel(dataModel);
        } catch (Exception ex) {

        }
    }

    public void buscaDeProdutos() {
        int indice = tPrincipal.getGerencia().getComboGrupo().getSelectedIndex();

        if (indice >= 0) {
            GrupoProduto grupo = grupos.get(indice);
            produtos = fachada.buscaGrupo(grupo);
            listarProdutos(produtos);
        }

    }

    public void preencherVacina(Vacina vacina) {

        tPrincipal.getVacina().getTxtDescricao().setText(vacina.getNome());
        tPrincipal.getVacina().getTxtDoses().setText(vacina.getDoses() + "");
        tPrincipal.getVacina().getTxtPeriodo().setText(vacina.getPeiodo());

    }

    private void editarLoja(Loja loja) {
        if (loja == null) {
            loja = new Loja();
        }

        loja.getDados().setBairro(tPrincipal.getcLoja().getTxtBairro().getText());
        loja.getDados().setCelular(tPrincipal.getcLoja().getTxtCelular().getText());
        loja.getDados().setTelefone(tPrincipal.getcLoja().getTxtTelefone().getText());
        loja.getDados().setCep(tPrincipal.getcLoja().getTxtCep().getText());
        loja.getDados().setCidade(tPrincipal.getcLoja().getTxtCidade().getText());
        loja.getDados().setEmail(tPrincipal.getcLoja().getTxtEmail().getText());

        String numb = tPrincipal.getcLoja().getTxtNumero().getText() + " "
                + tPrincipal.getcLoja().getTxtComplemento().getText();

        loja.getDados().setNumero(numb);
        loja.getDados().setRua(tPrincipal.getcLoja().getTxtRua().getText());
        loja.getDados().setUf(tPrincipal.getcLoja().getComboUf().getSelectedItem().toString());

        loja.setCnpj(tPrincipal.getcLoja().getTxtCnpjj().getText());
        loja.setNomefantasia(tPrincipal.getcLoja().getTxtNomeFantazia().getText());
        loja.setRazaosocial(tPrincipal.getcLoja().getTxtRazaoSociall().getText());

        try {
            new GenericDao<Loja>().salvar_ou_atualizar(loja);
            JOptionPane.showMessageDialog(null, "Edição concluida!");
            tPrincipal.getcLoja().setVisible(false);
            tPrincipal.getGerencia().setVisible(true);
            tPrincipal.getGerencia().preencherDados(loja);
        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    private void salvarEntradaProdutos() {

        EntradaEstoque entrada = new EntradaEstoque();

        Date data = ConverterData(new Date(System.currentTimeMillis()));

        int quantidade = 0;

        try {

            quantidade = Integer.parseInt(tPrincipal.geteProdutos().getTxtQuatidade().getText());
            entrada.setData(data);
            entrada.setQuantidade(quantidade);
            entrada.setProduto(produto);

            new GenericDao<EntradaEstoque>().salvar_ou_atualizar(entrada);
            JOptionPane.showMessageDialog(null, "Entrada cadastrada!");
            tPrincipal.geteProdutos().setVisible(false);
            tPrincipal.getGerencia().setVisible(true);

            int atual = quantidade + produto.getQuantidae_estoque();
            produto.setQuantidae_estoque(atual);
            fachada.salvar(produto);
            buscaDeProdutos();

        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    private void salvarVacina() {

        Vacina vacina = new Vacina();

        Random gerador = new Random();
        int doses = 0;
        int codigo = gerador.nextInt() * (1000);
        if (codigo < 1) {
            codigo = codigo * -1;
        }
        vacina.setCodigo(codigo);

        try {

            doses = Integer.parseInt(tPrincipal.getVacina().getTxtDoses().getText());
            vacina.setDoses(doses);
            vacina.setNome(tPrincipal.getVacina().getTxtDescricao().getText());
            vacina.setPeiodo(tPrincipal.getVacina().getTxtPeriodo().getText());

            fachada.salvar(vacina);
            JOptionPane.showMessageDialog(null, "Vacina cadastrada!");
            tPrincipal.getVacina().limparComponentes(false);
            vacinas = fachada.getAllVacina();
            listarVacinas(vacinas);
        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    private void editarVacina(Vacina vacina) {

        int doses = 0;

        try {

            doses = Integer.parseInt(tPrincipal.getVacina().getTxtDoses().getText());
            vacina.setDoses(doses);
            vacina.setNome(tPrincipal.getVacina().getTxtDescricao().getText());
            vacina.setPeiodo(tPrincipal.getVacina().getTxtPeriodo().getText());

            fachada.salvar(vacina);
            JOptionPane.showMessageDialog(null, "Vacina atualizada!");
            tPrincipal.getVacina().limparComponentes(false);
            vacinas = fachada.getAllVacina();
            listarVacinas(vacinas);
        } catch (java.lang.IllegalStateException n) {
            JOptionPane.showMessageDialog(null, "VOCE PRECISA PREENCHER TODOS OS CAMPOS !");
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
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
                ro = tabela.getSelectedRow();
                if (boton.getName().equals("adicionar")) {
                    opcao = adicionar;
                }
                if (boton.getName().equals("edicao")) {
                    opcao = edicao;
                }
                if (boton.getName().equals("reset")) {
                    opcao = reset;
                }
                if (boton.getName().equals("salvar")) {
                    opcao = reset;

                }
                if (boton.getName().equals("editar")) {
                    opcao = edicao;
                }
            }
        }
        return ro;
    }

    public java.sql.Date ConverterData(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public void escolherUsuario() {
        String user = tPrincipal.getGerencia().getComboUsuario().getSelectedItem().toString();
        if (user.equals(FUNCIONARIO)) {
            funcionarios = fachada.getAllFuncionario();
            listarFuncionarios(funcionarios);
        } else if (user.equals(PROFISSIONAL)) {
            profissionais = fachada.getAllProfissionals();
            listarProfissionais(profissionais);

        }

    }

    public void buscarLoja() {

        try {
            loja = new DaoLoja().buscaUltimoLoja();
        } catch (NoResultException n) {
        }

    }

    public void controleSenhaFu(int ro) {

        if (opcao == edicao) {
            funcionario = funcionarios.get(ro);
            diaSenha.setVisible(true);
        } else if (opcao == reset) {

        }
    }

    public void controleSenhaPro(int ro) {

        if (opcao == edicao) {
            profissional = profissionais.get(ro);
            diaSenha.setVisible(true);
        } else if (opcao == reset) {

        }
    }

}
