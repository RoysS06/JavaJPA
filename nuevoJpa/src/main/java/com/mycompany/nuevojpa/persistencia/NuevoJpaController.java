
package com.mycompany.nuevojpa.persistencia;

import com.mycompany.nuevojpa.logica.Nuevo;
import com.mycompany.nuevojpa.persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class NuevoJpaController implements Serializable {

    public NuevoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public NuevoJpaController(){
        emf = Persistence.createEntityManagerFactory("nuevoJpaPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Nuevo nuevo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nuevo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nuevo nuevo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nuevo = em.merge(nuevo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = nuevo.getId();
                if (findnuevo(id) == null) {
                    throw new NonexistentEntityException("The nuevo with id " + id + " no longer exists.");
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
            Nuevo nuevo;
            try {
                nuevo = em.getReference(Nuevo.class, id);
                nuevo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nuevo with id " + id + " no longer exists.", enfe);
            }
            em.remove(nuevo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nuevo> findnuevoEntities() {
        return findnuevoEntities(true, -1, -1);
    }

    public List<Nuevo> findnuevoEntities(int maxResults, int firstResult) {
        return findnuevoEntities(false, maxResults, firstResult);
    }

    private List<Nuevo> findnuevoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nuevo.class));
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

    public Nuevo findnuevo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nuevo.class, id);
        } finally {
            em.close();
        }
    }

    public int getnuevoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nuevo> rt = cq.from(Nuevo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
