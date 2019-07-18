/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Modelo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Andre-Coude
 */
@Entity
@SequenceGenerator(name = "animal_seq", sequenceName = "animal_seq", initialValue = 1, allocationSize = 1)
@Table(name = "animal")
public class Animal implements EntidadeBase {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_seq")
    private Long id;

    @Column(name = "apelido", length = 10, nullable = true)
    private String apelido;
    @Column(name = "nome", length = 20, nullable = false)
    private String nome;
    @Column(name = "sexo", length = 40, nullable = false)
    private String sexo;
    @Column(name = "cor", length = 40, nullable = false)
    private String cor;
    @Column(name = "nascimento", columnDefinition = "DATE DEFAULT CURRENT_DATE", nullable = true)
    private Date nascimento;
    @Column(name = "peso_kg", precision = 3, scale = 2, nullable = false)
    private Double pesokg;
    @Column(name = "observacao", length = 20, nullable = true)
    private String observacao;

    @OneToOne
    private Raca raca;

    @ManyToOne
    private Cliente cliente;
    
     @Column(name = "imagem", nullable = false)
    private byte[] imagem;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "animal")
    @JoinColumn(name = "animal_id")
    private List<AgendaAnimal> agendas;

    @Override
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        Long oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    /**
     * @return the apelido
     */
    public String getApelido() {
        return apelido;
    }

    /**
     * @param apelido the apelido to set
     */
    public void setApelido(String apelido) {
        String oldApelido = this.apelido;
        this.apelido = apelido;
        changeSupport.firePropertyChange("apelido", oldApelido, apelido);
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
        String oldNome = this.nome;
        this.nome = nome;
        changeSupport.firePropertyChange("nome", oldNome, nome);
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        String oldSexo = this.sexo;
        this.sexo = sexo;
        changeSupport.firePropertyChange("sexo", oldSexo, sexo);
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        String oldCor = this.cor;
        this.cor = cor;
        changeSupport.firePropertyChange("cor", oldCor, cor);
    }

    /**
     * @return the nascimento
     */
    public Date getNascimento() {
        return nascimento;
    }

    /**
     * @param nascimento the nascimento to set
     */
    public void setNascimento(Date nascimento) {
        Date oldNascimento = this.nascimento;
        this.nascimento = nascimento;
        changeSupport.firePropertyChange("nascimento", oldNascimento, nascimento);
    }

    /**
     * @return the pesokg
     */
    public Double getPesokg() {
        return pesokg;
    }

    /**
     * @param pesokg the pesokg to set
     */
    public void setPesokg(Double pesokg) {
        Double oldPesokg = this.pesokg;
        this.pesokg = pesokg;
        changeSupport.firePropertyChange("pesokg", oldPesokg, pesokg);
    }

    /**
     * @return the Cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param Cliente the Cliente to set
     */
    public void setCliente(Cliente Cliente) {
        Cliente oldCliente = this.cliente;
        this.cliente = Cliente;
        changeSupport.firePropertyChange("cliente", oldCliente, Cliente);
    }

    /**
     * @return the raca
     */
    public Raca getRaca() {
        return raca;
    }

    /**
     * @param raca the raca to set
     */
    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        String oldObservacao = this.observacao;
        this.observacao = observacao;
        changeSupport.firePropertyChange("observacao", oldObservacao, observacao);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    /**
     * @return the agendas
     */
    public List<AgendaAnimal> getAgendas() {
        return agendas;
    }

    /**
     * @param agendas the agendas to set
     */
    public void setAgendas(List<AgendaAnimal> agendas) {
        this.agendas = agendas;
    }

    /**
     * @return the imagem
     */
    public byte[] getImagem() {
        return imagem;
    }

    /**
     * @param imagem the imagem to set
     */
    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    
}
