/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.principal;

import br.com.pbd.Dao.GenericDao;
import br.com.pbd.Modelo.Caixa;
import br.com.pbd.Modelo.Cliente;
import br.com.pbd.Modelo.Dados;
import br.com.pbd.Modelo.Fornecedor;
import br.com.pbd.Modelo.GrupoProduto;
import br.com.pbd.Modelo.Pagamento;
import br.com.pbd.Modelo.Produto;
import br.com.pbd.Modelo.Profissional;
import br.com.pbd.view.TelaLogin;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import static org.hibernate.criterion.Restrictions.ne;

/**
 *
 * @author Andre-Coude
 */
public class Main {

    public static void main(String[] s) {

      // TelaLogin l = new TelaLogin();
       
//        Dados d = new Dados();
//        d.setBairro("centro");
//        d.setCelular("94584758");
//        d.setCidade("triunfo");
//        d.setCep("84758475");
//        d.setEmail("wweWbmail.@tgmail.com");
//        d.setNumero("34 A");
//        d.setRua("sadjkfj");
//        d.setTelefone("387438");
//        d.setUf("PE");
//        
//        Profissional p=  new Profissional();
//        
//        p.setDados(d);
//        p.setCrmv("arar");
//        p.setNome("Veterinario");
//        p.setSexo("macho");
//        p.setTipo("animal");
//        p.setNascimento(new Date(Calendar.DATE));
        

        List<Pagamento> lis = new ArrayList<Pagamento>();
 
        Pagamento p = new Pagamento();
        p.setNumeroparcelas(0);
        p.setStatus(Boolean.TRUE);
        p.setValortotal(100.1);
        p.setData(new Date(Calendar.DATE));
        lis.add(p);

      Caixa c = new Caixa();
      c.setData(new Date(Calendar.DATE));
      c.setLucrodia(100.0);
      c.setStatus(true);
      c.setValorabertura(0.0);
      c.setValorfechamento(100.0);
      c.setPagamentos(lis);
      
      
      p.setCaixa(c);
        
        
     new GenericDao<Caixa>().salvar_ou_atualizar(c);
                        
 
    }
}
