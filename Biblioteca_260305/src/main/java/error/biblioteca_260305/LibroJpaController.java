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
public class LibroJpaController implements Serializable {

    public LibroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Libro libro) {
        if (libro.getCategorias() == null) {
            libro.setCategorias(new ArrayList<Categoria>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Autor autor = libro.getAutor();
            if (autor != null) {
                autor = em.getReference(autor.getClass(), autor.getId_autor());
                libro.setAutor(autor);
            }
            List<Categoria> attachedCategorias = new ArrayList<Categoria>();
            for (Categoria categoriasCategoriaToAttach : libro.getCategorias()) {
                categoriasCategoriaToAttach = em.getReference(categoriasCategoriaToAttach.getClass(), categoriasCategoriaToAttach.getId_categoria());
                attachedCategorias.add(categoriasCategoriaToAttach);
            }
            libro.setCategorias(attachedCategorias);
            em.persist(libro);
            if (autor != null) {
                autor.getLibros().add(libro);
                autor = em.merge(autor);
            }
            for (Categoria categoriasCategoria : libro.getCategorias()) {
                categoriasCategoria.getLibros().add(libro);
                categoriasCategoria = em.merge(categoriasCategoria);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Libro libro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Libro persistentLibro = em.find(Libro.class, libro.getId_libro());
            Autor autorOld = persistentLibro.getAutor();
            Autor autorNew = libro.getAutor();
            List<Categoria> categoriasOld = persistentLibro.getCategorias();
            List<Categoria> categoriasNew = libro.getCategorias();
            if (autorNew != null) {
                autorNew = em.getReference(autorNew.getClass(), autorNew.getId_autor());
                libro.setAutor(autorNew);
            }
            List<Categoria> attachedCategoriasNew = new ArrayList<Categoria>();
            for (Categoria categoriasNewCategoriaToAttach : categoriasNew) {
                categoriasNewCategoriaToAttach = em.getReference(categoriasNewCategoriaToAttach.getClass(), categoriasNewCategoriaToAttach.getId_categoria());
                attachedCategoriasNew.add(categoriasNewCategoriaToAttach);
            }
            categoriasNew = attachedCategoriasNew;
            libro.setCategorias(categoriasNew);
            libro = em.merge(libro);
            if (autorOld != null && !autorOld.equals(autorNew)) {
                autorOld.getLibros().remove(libro);
                autorOld = em.merge(autorOld);
            }
            if (autorNew != null && !autorNew.equals(autorOld)) {
                autorNew.getLibros().add(libro);
                autorNew = em.merge(autorNew);
            }
            for (Categoria categoriasOldCategoria : categoriasOld) {
                if (!categoriasNew.contains(categoriasOldCategoria)) {
                    categoriasOldCategoria.getLibros().remove(libro);
                    categoriasOldCategoria = em.merge(categoriasOldCategoria);
                }
            }
            for (Categoria categoriasNewCategoria : categoriasNew) {
                if (!categoriasOld.contains(categoriasNewCategoria)) {
                    categoriasNewCategoria.getLibros().add(libro);
                    categoriasNewCategoria = em.merge(categoriasNewCategoria);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = libro.getId_libro();
                if (findLibro(id) == null) {
                    throw new NonexistentEntityException("The libro with id " + id + " no longer exists.");
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
            Libro libro;
            try {
                libro = em.getReference(Libro.class, id);
                libro.getId_libro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The libro with id " + id + " no longer exists.", enfe);
            }
            Autor autor = libro.getAutor();
            if (autor != null) {
                autor.getLibros().remove(libro);
                autor = em.merge(autor);
            }
            List<Categoria> categorias = libro.getCategorias();
            for (Categoria categoriasCategoria : categorias) {
                categoriasCategoria.getLibros().remove(libro);
                categoriasCategoria = em.merge(categoriasCategoria);
            }
            em.remove(libro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Libro> findLibroEntities() {
        return findLibroEntities(true, -1, -1);
    }

    public List<Libro> findLibroEntities(int maxResults, int firstResult) {
        return findLibroEntities(false, maxResults, firstResult);
    }

    private List<Libro> findLibroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Libro.class));
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

    public Libro findLibro(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Libro.class, id);
        } finally {
            em.close();
        }
    }

    public int getLibroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Libro> rt = cq.from(Libro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
