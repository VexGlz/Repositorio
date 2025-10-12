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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Erick
 */
public class AutorJpaController implements Serializable {

    public AutorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Autor autor) {
        if (autor.getLibros() == null) {
            autor.setLibros(new ArrayList<Libro>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Libro> attachedLibros = new ArrayList<Libro>();
            for (Libro librosLibroToAttach : autor.getLibros()) {
                librosLibroToAttach = em.getReference(librosLibroToAttach.getClass(), librosLibroToAttach.getId_libro());
                attachedLibros.add(librosLibroToAttach);
            }
            autor.setLibros(attachedLibros);
            em.persist(autor);
            for (Libro librosLibro : autor.getLibros()) {
                Autor oldAutorOfLibrosLibro = librosLibro.getAutor();
                librosLibro.setAutor(autor);
                librosLibro = em.merge(librosLibro);
                if (oldAutorOfLibrosLibro != null) {
                    oldAutorOfLibrosLibro.getLibros().remove(librosLibro);
                    oldAutorOfLibrosLibro = em.merge(oldAutorOfLibrosLibro);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Autor autor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autor persistentAutor = em.find(Autor.class, autor.getId_autor());
            List<Libro> librosOld = persistentAutor.getLibros();
            List<Libro> librosNew = autor.getLibros();
            List<Libro> attachedLibrosNew = new ArrayList<Libro>();
            for (Libro librosNewLibroToAttach : librosNew) {
                librosNewLibroToAttach = em.getReference(librosNewLibroToAttach.getClass(), librosNewLibroToAttach.getId_libro());
                attachedLibrosNew.add(librosNewLibroToAttach);
            }
            librosNew = attachedLibrosNew;
            autor.setLibros(librosNew);
            autor = em.merge(autor);
            for (Libro librosOldLibro : librosOld) {
                if (!librosNew.contains(librosOldLibro)) {
                    librosOldLibro.setAutor(null);
                    librosOldLibro = em.merge(librosOldLibro);
                }
            }
            for (Libro librosNewLibro : librosNew) {
                if (!librosOld.contains(librosNewLibro)) {
                    Autor oldAutorOfLibrosNewLibro = librosNewLibro.getAutor();
                    librosNewLibro.setAutor(autor);
                    librosNewLibro = em.merge(librosNewLibro);
                    if (oldAutorOfLibrosNewLibro != null && !oldAutorOfLibrosNewLibro.equals(autor)) {
                        oldAutorOfLibrosNewLibro.getLibros().remove(librosNewLibro);
                        oldAutorOfLibrosNewLibro = em.merge(oldAutorOfLibrosNewLibro);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = autor.getId_autor();
                if (findAutor(id) == null) {
                    throw new NonexistentEntityException("The autor with id " + id + " no longer exists.");
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
            Autor autor;
            try {
                autor = em.getReference(Autor.class, id);
                autor.getId_autor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autor with id " + id + " no longer exists.", enfe);
            }
            List<Libro> libros = autor.getLibros();
            for (Libro librosLibro : libros) {
                librosLibro.setAutor(null);
                librosLibro = em.merge(librosLibro);
            }
            em.remove(autor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Autor> findAutorEntities() {
        return findAutorEntities(true, -1, -1);
    }

    public List<Autor> findAutorEntities(int maxResults, int firstResult) {
        return findAutorEntities(false, maxResults, firstResult);
    }

    private List<Autor> findAutorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Autor.class));
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

    public Autor findAutor(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Autor.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Autor> rt = cq.from(Autor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
