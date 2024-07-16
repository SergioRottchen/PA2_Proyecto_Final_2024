/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.exceptions;

import Modelo.EstadoDelPedido;
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
public class EstadoDelPedidoJpaController implements Serializable {

    public EstadoDelPedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public EstadoDelPedidoJpaController() {
        emf = Persistence.createEntityManagerFactory("pa2_proyectofinal");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadoDelPedido estadoDelPedido) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estadoDelPedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadoDelPedido estadoDelPedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estadoDelPedido = em.merge(estadoDelPedido);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = estadoDelPedido.getId();
                if (findEstadoDelPedido(id) == null) {
                    throw new NonexistentEntityException("The estadoDelPedido with id " + id + " no longer exists.");
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
            EstadoDelPedido estadoDelPedido;
            try {
                estadoDelPedido = em.getReference(EstadoDelPedido.class, id);
                estadoDelPedido.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoDelPedido with id " + id + " no longer exists.", enfe);
            }
            em.remove(estadoDelPedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoDelPedido> findEstadoDelPedidoEntities() {
        return findEstadoDelPedidoEntities(true, -1, -1);
    }

    public List<EstadoDelPedido> findEstadoDelPedidoEntities(int maxResults, int firstResult) {
        return findEstadoDelPedidoEntities(false, maxResults, firstResult);
    }

    private List<EstadoDelPedido> findEstadoDelPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoDelPedido.class));
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

    public EstadoDelPedido findEstadoDelPedido(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoDelPedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoDelPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoDelPedido> rt = cq.from(EstadoDelPedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
