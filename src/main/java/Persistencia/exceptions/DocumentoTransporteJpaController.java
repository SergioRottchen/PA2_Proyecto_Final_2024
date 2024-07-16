/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.exceptions;

import Modelo.DocumentoTransporte;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Pedido;
import Persistencia.exceptions.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sirgi
 */
public class DocumentoTransporteJpaController implements Serializable {

    public DocumentoTransporteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    public DocumentoTransporteJpaController() {
        emf = Persistence.createEntityManagerFactory("pa2_proyectofinal");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DocumentoTransporte documentoTransporte) {
        if (documentoTransporte.getPedidos() == null) {
            documentoTransporte.setPedidos(new ArrayList<Pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pedido> attachedPedidos = new ArrayList<Pedido>();
            for (Pedido pedidosPedidoToAttach : documentoTransporte.getPedidos()) {
                pedidosPedidoToAttach = em.getReference(pedidosPedidoToAttach.getClass(), pedidosPedidoToAttach.getId());
                attachedPedidos.add(pedidosPedidoToAttach);
            }
            documentoTransporte.setPedidos(attachedPedidos);
            em.persist(documentoTransporte);
            for (Pedido pedidosPedido : documentoTransporte.getPedidos()) {
                DocumentoTransporte oldUnDocumentoOfPedidosPedido = pedidosPedido.getUnDocumento();
                pedidosPedido.setUnDocumento(documentoTransporte);
                pedidosPedido = em.merge(pedidosPedido);
                if (oldUnDocumentoOfPedidosPedido != null) {
                    oldUnDocumentoOfPedidosPedido.getPedidos().remove(pedidosPedido);
                    oldUnDocumentoOfPedidosPedido = em.merge(oldUnDocumentoOfPedidosPedido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DocumentoTransporte documentoTransporte) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DocumentoTransporte persistentDocumentoTransporte = em.find(DocumentoTransporte.class, documentoTransporte.getId());
            List<Pedido> pedidosOld = persistentDocumentoTransporte.getPedidos();
            List<Pedido> pedidosNew = documentoTransporte.getPedidos();
            List<Pedido> attachedPedidosNew = new ArrayList<Pedido>();
            for (Pedido pedidosNewPedidoToAttach : pedidosNew) {
                pedidosNewPedidoToAttach = em.getReference(pedidosNewPedidoToAttach.getClass(), pedidosNewPedidoToAttach.getId());
                attachedPedidosNew.add(pedidosNewPedidoToAttach);
            }
            pedidosNew = attachedPedidosNew;
            documentoTransporte.setPedidos(pedidosNew);
            documentoTransporte = em.merge(documentoTransporte);
            for (Pedido pedidosOldPedido : pedidosOld) {
                if (!pedidosNew.contains(pedidosOldPedido)) {
                    pedidosOldPedido.setUnDocumento(null);
                    pedidosOldPedido = em.merge(pedidosOldPedido);
                }
            }
            for (Pedido pedidosNewPedido : pedidosNew) {
                if (!pedidosOld.contains(pedidosNewPedido)) {
                    DocumentoTransporte oldUnDocumentoOfPedidosNewPedido = pedidosNewPedido.getUnDocumento();
                    pedidosNewPedido.setUnDocumento(documentoTransporte);
                    pedidosNewPedido = em.merge(pedidosNewPedido);
                    if (oldUnDocumentoOfPedidosNewPedido != null && !oldUnDocumentoOfPedidosNewPedido.equals(documentoTransporte)) {
                        oldUnDocumentoOfPedidosNewPedido.getPedidos().remove(pedidosNewPedido);
                        oldUnDocumentoOfPedidosNewPedido = em.merge(oldUnDocumentoOfPedidosNewPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = documentoTransporte.getId();
                if (findDocumentoTransporte(id) == null) {
                    throw new NonexistentEntityException("The documentoTransporte with id " + id + " no longer exists.");
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
            DocumentoTransporte documentoTransporte;
            try {
                documentoTransporte = em.getReference(DocumentoTransporte.class, id);
                documentoTransporte.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The documentoTransporte with id " + id + " no longer exists.", enfe);
            }
            List<Pedido> pedidos = documentoTransporte.getPedidos();
            for (Pedido pedidosPedido : pedidos) {
                pedidosPedido.setUnDocumento(null);
                pedidosPedido = em.merge(pedidosPedido);
            }
            em.remove(documentoTransporte);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DocumentoTransporte> findDocumentoTransporteEntities() {
        return findDocumentoTransporteEntities(true, -1, -1);
    }

    public List<DocumentoTransporte> findDocumentoTransporteEntities(int maxResults, int firstResult) {
        return findDocumentoTransporteEntities(false, maxResults, firstResult);
    }

    private List<DocumentoTransporte> findDocumentoTransporteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DocumentoTransporte.class));
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

    public DocumentoTransporte findDocumentoTransporte(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DocumentoTransporte.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocumentoTransporteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DocumentoTransporte> rt = cq.from(DocumentoTransporte.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
