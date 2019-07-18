/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.DaoView;

import br.com.pbd.Visao.ViewProduto;
import br.com.pbd.sql.SQLConexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andre-Coude
 */
public class DaoViewProduto {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public List<ViewProduto> getAllView() {

        manager.getTransaction().begin();
        TypedQuery<ViewProduto> vp = (TypedQuery<ViewProduto>) manager.createNativeQuery("select * from public.view_produto", ViewProduto.class);
        manager.getTransaction().commit();

        return vp.getResultList();
    }

    public List<ViewProduto> Busca(String nome) {

        manager.getTransaction().begin();
        TypedQuery<ViewProduto> vp = (TypedQuery<ViewProduto>) manager.createNativeQuery("select * from view_produto  where nome like '%" + nome + "%'",
                ViewProduto.class);

        manager.getTransaction().commit();

        return vp.getResultList();
    }

    public void criar() {

        manager.getTransaction().begin();
        TypedQuery<ViewProduto> vp = (TypedQuery<ViewProduto>) manager.createNativeQuery("CREATE OR REPLACE VIEW  view_produtos\n"
                + "\n"
                + "AS SELECT  p.id, p.codigo,p.nome,p.fabricante, p.quantidade_estoque,p.valor_venda,f.nome_fantasia\n"
                + "FROM Produto p, Fornecedor f\n"
                + "\n"
                + "WHERE  p.fornecedor_id =f.id", ViewProduto.class);

        manager.getTransaction().commit();

    }
}
