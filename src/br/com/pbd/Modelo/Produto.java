package br.com.pbd.Modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq", initialValue = 1, allocationSize = 1)
@Table(name = "produto")
public class Produto implements EntidadeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
    private Long id;

    @Column(name = "codigo", precision = 8, scale = 0, nullable = false)
    private int codigo;
    @Column(name = "nome", length = 20, nullable = false)
    private String nome;
    @Column(name = "fabricante", length = 20, nullable = false)
    private String fabricante;
    @Column(name = "valor_compra", precision = 4, scale = 2, nullable = false)
    private Double valorcompra;
    @Column(name = "valor_venda", precision = 4, scale = 2, nullable = false)
    private Double valorvenda;
    @Column(name = "quantidae_estoque", precision = 2, scale = 0, nullable = false)
    private int quantidae_estoque;

    @OneToOne
    private GrupoProduto gproduto;

    @ManyToOne
    private Fornecedor fornecedor;

    @Override
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return getNome();
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.setNome(descricao);
    }

    /**
     * @return the fabricante
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * @param fabricante the fabricante to set
     */
    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    /**
     * @return the valorcompra
     */
    public Double getValorcompra() {
        return valorcompra;
    }

    /**
     * @param valorcompra the valorcompra to set
     */
    public void setValorcompra(Double valorcompra) {
        this.valorcompra = valorcompra;
    }

    /**
     * @return the valorvenda
     */
    public Double getValorvenda() {
        return valorvenda;
    }

    /**
     * @param valorvenda the valorvenda to set
     */
    public void setValorvenda(Double valorvenda) {
        this.valorvenda = valorvenda;
    }

    /**
     * @return the gproduto
     */
    public GrupoProduto getGproduto() {
        return gproduto;
    }

    /**
     * @param gproduto the gproduto to set
     */
    public void setGproduto(GrupoProduto gproduto) {
        this.gproduto = gproduto;
    }

    /**
     * @return the fornecedor
     */
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the quantidae_estoque
     */
    public int getQuantidae_estoque() {
        return quantidae_estoque;
    }

    /**
     * @param quantidae_estoque the quantidae_estoque to set
     */
    public void setQuantidae_estoque(int quantidae_estoque) {
        this.quantidae_estoque = quantidae_estoque;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

}
