package br.com.pbd.Dao;

import br.com.pbd.Modelo.EntidadeBase;
import br.com.pbd.sql.SQLConexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Andre-Coude
 * @param <entidade>
 */
public class GenericDao<entidade extends EntidadeBase> {

    private static final EntityManager manager = SQLConexao.getEntityManager();

    public entidade BuscaPorId(Class<entidade> clazz, Long id) {
        return manager.find(clazz, id);
    }

    public void salvar_ou_atualizar(entidade obj) {
        try {
            manager.getTransaction().begin();
            if (obj.getId() == null) {
                manager.persist(obj);
            } else {
                manager.merge(obj);
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
    }

    public void remover(Class<entidade> clazz, Long id) {
        entidade t = BuscaPorId(clazz, id);
        try {
            manager.getTransaction().begin();
            manager.remove(t);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
    }

    /**
     *
     * @param <entidade>
     * @param clazz
     * @return
     */
    public <entidade extends EntidadeBase> List<entidade> getAll(Class<entidade> clazz) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<entidade> cq = cb.createQuery(clazz);
        return manager.createQuery(cq).getResultList();
        
    }
    
   }
