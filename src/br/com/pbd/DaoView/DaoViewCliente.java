/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.DaoView;

import br.com.pbd.Visao.ViewCliente;
import br.com.pbd.Visao.ViewProduto;
import br.com.pbd.sql.SQLConexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andre-Coude
 */
public class DaoViewCliente {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public List<ViewCliente> getAllView() {

        manager.getTransaction().begin();
        TypedQuery<ViewCliente> vp = (TypedQuery<ViewCliente>) manager.createNativeQuery("select * from public.view_cliente", ViewCliente.class);
        manager.getTransaction().commit();

        return vp.getResultList();
    }

    public List<ViewCliente> Busca(String nome) {

        manager.getTransaction().begin();
        TypedQuery<ViewCliente> vp = (TypedQuery<ViewCliente>) manager.createNativeQuery("select * from view_cliente  where nome like '%" + nome + "%'"
                + "or cpf like '%" + nome + "%'",
                ViewCliente.class);

        manager.getTransaction().commit();

        return vp.getResultList();
    }

    public void criar() {

        manager.getTransaction().begin();
        TypedQuery<ViewCliente> vp = (TypedQuery<ViewCliente>) manager.createNativeQuery("CREATE OR REPLACE VIEW public.view_cliente AS \n"
                + " SELECT c.id,c.rg,c.cpf,c.nascimento,c.nome,c.sexo,\n"
                + "    d.celular,d.rua\n"
                + "   FROM cliente c, dados d\n"
                + "  WHERE c.dados_id = d.id;", ViewCliente.class);

        manager.getTransaction().commit();

    }
}
