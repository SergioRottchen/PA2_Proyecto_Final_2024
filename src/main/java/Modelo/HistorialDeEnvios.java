/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class HistorialDeEnvios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "empleado_transportista_id")
    private EmpleadoTransportista empleadoTransportista;
    private LocalDate FechaEnvio;
    @ManyToOne
    @JoinColumn(name = "estado_envio_id")
    private EstadoDelPedido estado;
    @ManyToOne
    @JoinColumn(name = "documento_transporte_id")
    private DocumentoTransporte documento;

    public HistorialDeEnvios(int id, Pedido pedido, EmpleadoTransportista empleadoTransportista, LocalDate FechaEnvio, EstadoDelPedido estado, DocumentoTransporte documento) {
        this.id = id;
        this.pedido = pedido;
        this.empleadoTransportista = empleadoTransportista;
        this.FechaEnvio = FechaEnvio;
        this.estado = estado;
        this.documento = documento;
    }

    public HistorialDeEnvios() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public EmpleadoTransportista getEmpleadoTransportista() {
        return empleadoTransportista;
    }

    public void setEmpleadoTransportista(EmpleadoTransportista empleadoTransportista) {
        this.empleadoTransportista = empleadoTransportista;
    }

    public LocalDate getFechaEnvio() {
        return FechaEnvio;
    }

    public void setFechaEnvio(LocalDate FechaEnvio) {
        this.FechaEnvio = FechaEnvio;
    }

    public EstadoDelPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoDelPedido estado) {
        this.estado = estado;
    }

    public DocumentoTransporte getDocumento() {
        return documento;
    }

    public void setDocumento(DocumentoTransporte documento) {
        this.documento = documento;
    }

}
