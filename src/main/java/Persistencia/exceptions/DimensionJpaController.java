/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.exceptions;

import Modelo.Dimension;
import Persistencia.exceptions.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author sirgi
 */
public class DimensionJpaController implements Serializable {

    public DimensionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public DimensionJpaController() {
        emf = Persistence.createEntityManagerFactory("pa2_proyectofinal");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dimension dimension) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dimension);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dimension dimension) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dimension = em.merge(dimension);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = dimension.getId();
                if (findDimension(id) == null) {
                    throw new NonexistentEntityException("The dimension with id " + id + " no longer exists.");
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
            Dimension dimension;
            try {
                dimension = em.getReference(Dimension.class, id);
                dimension.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dimension with id " + id + " no longer exists.", enfe);
            }
            em.remove(dimension);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dimension> findDimensionEntities() {
        return findDimensionEntities(true, -1, -1);
    }

    public List<Dimension> findDimensionEntities(int maxResults, int firstResult) {
        return findDimensionEntities(false, maxResults, firstResult);
    }

    private List<Dimension> findDimensionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dimension.class));
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

    public Dimension findDimension(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dimension.class, id);
        } finally {
            em.close();
        }
    }

    public int getDimensionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dimension> rt = cq.from(Dimension.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
