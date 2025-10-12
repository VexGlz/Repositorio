/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package error.biblioteca_260305;

import error.biblioteca_260305.exceptions.NonexistentEntityException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.Serializable;
import jakarta.persistence.Query;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author Erick
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Credencial credencial = usuario.getCredencial();
            if (credencial != null) {
                credencial = em.getReference(credencial.getClass(), credencial.getId_credencial());
                usuario.setCredencial(credencial);
            }
            em.persist(usuario);
            if (credencial != null) {
                Usuario oldUsuarioOfCredencial = credencial.getUsuario();
                if (oldUsuarioOfCredencial != null) {
                    oldUsuarioOfCredencial.setCredencial(null);
                    oldUsuarioOfCredencial = em.merge(oldUsuarioOfCredencial);
                }
                credencial.setUsuario(usuario);
                credencial = em.merge(credencial);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId_usuario());
            Credencial credencialOld = persistentUsuario.getCredencial();
            Credencial credencialNew = usuario.getCredencial();
            if (credencialNew != null) {
                credencialNew = em.getReference(credencialNew.getClass(), credencialNew.getId_credencial());
                usuario.setCredencial(credencialNew);
            }
            usuario = em.merge(usuario);
            if (credencialOld != null && !credencialOld.equals(credencialNew)) {
                credencialOld.setUsuario(null);
                credencialOld = em.merge(credencialOld);
            }
            if (credencialNew != null && !credencialNew.equals(credencialOld)) {
                Usuario oldUsuarioOfCredencial = credencialNew.getUsuario();
                if (oldUsuarioOfCredencial != null) {
                    oldUsuarioOfCredencial.setCredencial(null);
                    oldUsuarioOfCredencial = em.merge(oldUsuarioOfCredencial);
                }
                credencialNew.setUsuario(usuario);
                credencialNew = em.merge(credencialNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = usuario.getId_usuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId_usuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Credencial credencial = usuario.getCredencial();
            if (credencial != null) {
                credencial.setUsuario(null);
                credencial = em.merge(credencial);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
