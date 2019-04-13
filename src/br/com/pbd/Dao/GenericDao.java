
package br.com.pbd.Dao;

import br.com.pbd.Modelo.EntidadeBase;
import br.com.pbd.sql.SQLConexao;
import javax.persistence.EntityManager;

/**
 *
 * @author Andre-Coude
 */
public class GenericDao<Entidade extends EntidadeBase> {

        private static EntityManager manager = SQLConexao.getEntityManager();
    
        public Entidade BuscaPorId(Class<Entidade> clazz, Long id) {
            return manager.find(clazz, id);
        }
    
        public void salvar_ou_atualizar(Entidade obj) {
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
    
        public void remover(Class<Entidade> clazz, Long id) {
            Entidade t = BuscaPorId(clazz, id);
            try {
                manager.getTransaction().begin();
                manager.remove(t);
                manager.getTransaction().commit();
            } catch (Exception e) {
                manager.getTransaction().rollback();
            }
        }
}
