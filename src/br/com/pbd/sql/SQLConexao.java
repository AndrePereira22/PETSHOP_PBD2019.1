package br.com.pbd.sql;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Andre-Coude
 */
public class SQLConexao {
     
 private static EntityManagerFactory fabrica =  Persistence.createEntityManagerFactory("PBD");
 
 public static EntityManager getEntityManager(){
     
     
 return fabrica.createEntityManager();
 }
 
}
