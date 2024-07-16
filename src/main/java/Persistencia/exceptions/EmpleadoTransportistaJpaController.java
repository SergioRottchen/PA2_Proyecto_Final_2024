/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.exceptions;

import Modelo.EmpleadoTransportista;
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
public class EmpleadoTransportistaJpaController implements Serializable {

    public EmpleadoTransportistaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
     public EmpleadoTransportistaJpaController() {
        emf = Persistence.createEntityManagerFactory("pa2_proyectofinal");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EmpleadoTransportista empleadoTransportista) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(empleadoTransportista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EmpleadoTransportista empleadoTransportista) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            empleadoTransportista = em.merge(empleadoTransportista);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = empleadoTransportista.getId();
                if (findEmpleadoTransportista(id) == null) {
                    throw new NonexistentEntityException("The empleadoTransportista with id " + id + " no longer exists.");
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
            EmpleadoTransportista empleadoTransportista;
            try {
                empleadoTransportista = em.getReference(EmpleadoTransportista.class, id);
                empleadoTransportista.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleadoTransportista with id " + id + " no longer exists.", enfe);
            }
            em.remove(empleadoTransportista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EmpleadoTransportista> findEmpleadoTransportistaEntities() {
        return findEmpleadoTransportistaEntities(true, -1, -1);
    }

    public List<EmpleadoTransportista> findEmpleadoTransportistaEntities(int maxResults, int firstResult) {
        return findEmpleadoTransportistaEntities(false, maxResults, firstResult);
    }

    private List<EmpleadoTransportista> findEmpleadoTransportistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EmpleadoTransportista.class));
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

    public EmpleadoTransportista findEmpleadoTransportista(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EmpleadoTransportista.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoTransportistaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EmpleadoTransportista> rt = cq.from(EmpleadoTransportista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
