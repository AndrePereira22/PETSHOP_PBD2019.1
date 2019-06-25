/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.Modelo;

import br.com.pbd.Dao.DaoAdministrador;
import br.com.pbd.Dao.DaoLoja;
import br.com.pbd.Dao.GenericDao;
import br.com.pbd.fachada.Fachada;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;

/**
 *
 * @author Andre-Coude
 */
public class SalvarDadosRequiridos {

    static Fachada fachada = Fachada.getInstance();

    public static void procurarLoja() {

        Loja loja = null;
        try {
            loja = fachada.buscarLoja();
        } catch (NoResultException n) {
            salvarLoja();
        }
    }

    public static void procurarAdm() {

        Administrador adm = null;
        try {
            adm = new DaoAdministrador().busca();
        } catch (NoResultException n) {
            salvarADM();
        }
    }

    public static void salvarDadosRequiridos() {

        List<Especie> list = fachada.getAllEspecie();
        if (list.isEmpty()) {

            List<String> lista = new ArrayList<String>();

            lista.add("AVE");
            lista.add("FELINA");
            lista.add("CANINA");
            lista.add("REPTIL");
            lista.add("ROEDOR");

            for (int i = 0; i < lista.size(); i++) {

                Especie r = new Especie();
                r.setNome(lista.get(i));
                list.add(r);
                fachada.salvarEspecie(r);
            }
            salvarRacasCanina();
            salvarRacasRoedor();
        }
    }

    public static void salvarRacasCanina() {

        Especie e = fachada.buscar("CANINA");
        if (e != null) {

            List<Raca> list = new ArrayList<Raca>();

            List<String> lista = new ArrayList<String>();

            lista.add("Affenpinscher");
            lista.add("Afghan Hound	");
            lista.add("Airedale Terrier");
            lista.add("Akbash Dog");
            lista.add("Akita Americano");
            lista.add("Akita Inu");
            lista.add("American Pitbull Terrier");
            lista.add("Australian Cattle");
            lista.add("Australian Shepherd");
            lista.add("Barbet");
            lista.add("Basenji");
            lista.add("Basset Fulvo	");
            lista.add("Petit ");
            lista.add("Basset Griffon");
            lista.add("Basset Hound");
            lista.add("Beagle");
            lista.add("Bearded Collie");
            lista.add("Bernese Mountain Dog");
            lista.add("Bichon Frisé	");
            lista.add("Bichon Havanês");
            lista.add("Bloodhound");
            lista.add("Border Collie");
            lista.add("Borzoi");
            lista.add("Bouvier de Flandres");
            lista.add("Boston Terrier");
            lista.add("Braco Alemão");
            lista.add("Braco Italiano");
            lista.add("Briard");
            lista.add("Bulldog Campeiro");
            lista.add("Buldogue Francês");
            lista.add("Bulldog");
            lista.add("Bulldog Inglês");
            lista.add("Bullmastiff");
            lista.add("Bull Terrier");
            lista.add("Cairn Terrier");
            lista.add("Cane Corso Italiano");
            lista.add("Cão da Serra da Estrela");
            lista.add("Cão D'Água Português");
            lista.add("Cão Fila de São Miguel");
            lista.add("Cavalier King Charles");
            lista.add("Chihuahua");
            lista.add("Chow Chow");
            lista.add("Cimarrón Uruguayo");
            lista.add("Clumber Spaniel");
            lista.add("Cocker Americano");
            lista.add("Cocker Inglês");
            lista.add("Collie");
            lista.add("Coton de Tuléar");
            lista.add("Cristado Chinês");
            lista.add("Cuvac");
            lista.add("Dachshund");
            lista.add("Dálmata");
            lista.add("Dobermann");
            lista.add("Dogue Alemão");
            lista.add("Dogue de Bordeaux");
            lista.add("Dogo Argentino");
            lista.add("Fila Brasileiro");
            lista.add("Fox Paulistinha");
            lista.add("Golden Retriever");
            lista.add("Greyhound");
            lista.add("Griffon de Bruxelas");
            lista.add("Husky Siberiano");
            lista.add("Jack Russell Terrier");
            lista.add("Kuvasz");
            lista.add("Labrador");
            lista.add("Leão da Rodésia");
            lista.add("Lhasa Apso");
            lista.add("Lulu da Pomerânia");
            lista.add("malamute do alasca");
            lista.add("Maltês");
            lista.add("Mastiff");
            lista.add("Old English Sheepdog");
            lista.add("Pastor Alemão ");
            lista.add("Pastor Australiano");
            lista.add("Pastor Belga");
            lista.add("pastor branco suíço ");
            lista.add("pastor de shetland");
            lista.add("Pastor de Shetland");
            lista.add("Pastor Maremano Abruzês");
            lista.add("Pequinês");
            lista.add("pinscher");
            lista.add("Pit Bull ");
            lista.add("Pointer Inglês");
            lista.add("Poodle");
            lista.add("Pug");
            lista.add("Rottweiler");
            lista.add("Samoieda");
            lista.add("São Bernardo");
            lista.add("Schnauzer Miniatura");
            lista.add("Setter Irlandês");

            lista.add("Shar Pei");
            lista.add("Shiba Inu");
            lista.add("Shih Tzu");
            lista.add("spitz japones");
            lista.add("Staffordshire Bull Terrier");

            lista.add("Terra-Nova");
            lista.add("Weimaraner");

            lista.add("Welsh Corgi Cardigan");
            lista.add("Welsh Corgi Pembroke");
            lista.add("West Highland White Terrier");
            lista.add("Whippet");

            for (int i = 0; i < lista.size(); i++) {

                Raca r = new Raca();
                r.setNome(lista.get(i));
                r.setDescricao("...");
                r.setEspecie(e);
                list.add(r);

            }
            e.setRacas(list);

            fachada.salvarEspecie(e);
        }

    }

    public static void salvarRacasRoedor() {
        Especie e = fachada.buscar("ROEDOR");
        if (e != null) {

            List<Raca> list = new ArrayList<Raca>();

            List<String> lista = new ArrayList<String>();

            lista.add("Chinchila");
            lista.add("Gerbil");
            lista.add("Rato");
            lista.add("Hamster");
            lista.add("Porquinho da Índia");
            lista.add("Coelho");
            lista.add("Cobaia");

            for (int i = 0; i < lista.size(); i++) {

                Raca r = new Raca();
                r.setNome(lista.get(i));
                r.setDescricao("...");
                r.setEspecie(e);
                list.add(r);

            }
            e.setRacas(list);

            fachada.salvarEspecie(e);
        }

    }

    public static void salvarLoja() {

        Dados dados = new Dados();
        dados.setBairro("...");
        dados.setCelular("...");
        dados.setTelefone("...");
        dados.setCep("...");
        dados.setCidade("...");
        dados.setEmail("...");

        dados.setNumero("...");
        dados.setRua("...");
        dados.setUf("..");

        Loja loja = new Loja();

        loja.setDados(dados);
        loja.setCnpj("00.000.000/0000.00");
        loja.setNomefantasia("PETSHOP CONTROL");
        loja.setRazaosocial("CUIDADO E DEDICAÇÃO ANIMAL");

        try {

            new GenericDao<Loja>().salvar_ou_atualizar(loja);

        } catch (java.lang.IllegalStateException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        }
    }

    public static void salvarADM() {

        Administrador adm = new Administrador();
        Login login = new Login();
        String senha = "admin";
        String usuario = "admin";

        try {
            MessageDigest md;
            md = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = md.digest(senha.getBytes("UTF-8"));
            StringBuilder ab = new StringBuilder();
            for (byte b : messageDigest) {
                ab.append(String.format("%02X", 0xFF & b));
            }
            String senhaHex = ab.toString();
            login.setSenha(senhaHex);
            login.setUsuario(usuario);
            adm.setLogin(login);

            new GenericDao<Administrador>().salvar_ou_atualizar(adm);

        } catch (java.lang.IllegalStateException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        } catch (javax.persistence.RollbackException roll) {
            JOptionPane.showMessageDialog(null, roll.getCause());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SalvarDadosRequiridos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SalvarDadosRequiridos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
