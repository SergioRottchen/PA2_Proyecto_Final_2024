/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.exceptions;

import Modelo.HistorialDeEnvios;
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
public class HistorialDeEnviosJpaController implements Serializable {

    public HistorialDeEnviosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public HistorialDeEnviosJpaController() {
        emf = Persistence.createEntityManagerFactory("pa2_proyectofinal");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HistorialDeEnvios historialDeEnvios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(historialDeEnvios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HistorialDeEnvios historialDeEnvios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            historialDeEnvios = em.merge(historialDeEnvios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = historialDeEnvios.getId();
                if (findHistorialDeEnvios(id) == null) {
                    throw new NonexistentEntityException("The historialDeEnvios with id " + id + " no longer exists.");
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
            HistorialDeEnvios historialDeEnvios;
            try {
                historialDeEnvios = em.getReference(HistorialDeEnvios.class, id);
                historialDeEnvios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historialDeEnvios with id " + id + " no longer exists.", enfe);
            }
            em.remove(historialDeEnvios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HistorialDeEnvios> findHistorialDeEnviosEntities() {
        return findHistorialDeEnviosEntities(true, -1, -1);
    }

    public List<HistorialDeEnvios> findHistorialDeEnviosEntities(int maxResults, int firstResult) {
        return findHistorialDeEnviosEntities(false, maxResults, firstResult);
    }

    private List<HistorialDeEnvios> findHistorialDeEnviosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HistorialDeEnvios.class));
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

    public HistorialDeEnvios findHistorialDeEnvios(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistorialDeEnvios.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialDeEnviosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HistorialDeEnvios> rt = cq.from(HistorialDeEnvios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
