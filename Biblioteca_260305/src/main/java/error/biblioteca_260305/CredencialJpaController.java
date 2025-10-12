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
public class CredencialJpaController implements Serializable {

    public CredencialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Credencial credencial) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = credencial.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getId_usuario());
                credencial.setUsuario(usuario);
            }
            em.persist(credencial);
            if (usuario != null) {
                Credencial oldCredencialOfUsuario = usuario.getCredencial();
                if (oldCredencialOfUsuario != null) {
                    oldCredencialOfUsuario.setUsuario(null);
                    oldCredencialOfUsuario = em.merge(oldCredencialOfUsuario);
                }
                usuario.setCredencial(credencial);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Credencial credencial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Credencial persistentCredencial = em.find(Credencial.class, credencial.getId_credencial());
            Usuario usuarioOld = persistentCredencial.getUsuario();
            Usuario usuarioNew = credencial.getUsuario();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getId_usuario());
                credencial.setUsuario(usuarioNew);
            }
            credencial = em.merge(credencial);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.setCredencial(null);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                Credencial oldCredencialOfUsuario = usuarioNew.getCredencial();
                if (oldCredencialOfUsuario != null) {
                    oldCredencialOfUsuario.setUsuario(null);
                    oldCredencialOfUsuario = em.merge(oldCredencialOfUsuario);
                }
                usuarioNew.setCredencial(credencial);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = credencial.getId_credencial();
                if (findCredencial(id) == null) {
                    throw new NonexistentEntityException("The credencial with id " + id + " no longer exists.");
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
            Credencial credencial;
            try {
                credencial = em.getReference(Credencial.class, id);
                credencial.getId_credencial();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The credencial with id " + id + " no longer exists.", enfe);
            }
            Usuario usuario = credencial.getUsuario();
            if (usuario != null) {
                usuario.setCredencial(null);
                usuario = em.merge(usuario);
            }
            em.remove(credencial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Credencial> findCredencialEntities() {
        return findCredencialEntities(true, -1, -1);
    }

    public List<Credencial> findCredencialEntities(int maxResults, int firstResult) {
        return findCredencialEntities(false, maxResults, firstResult);
    }

    private List<Credencial> findCredencialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Credencial.class));
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

    public Credencial findCredencial(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Credencial.class, id);
        } finally {
            em.close();
        }
    }

    public int getCredencialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Credencial> rt = cq.from(Credencial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
