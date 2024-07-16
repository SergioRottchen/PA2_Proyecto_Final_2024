/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //estadeoCosto refleja si esta pagado el pedido o no
    private boolean estadoCosto;
    private String descripcion;

    private LocalDate fechaPedido;
    @ManyToOne
    @JoinColumn(name = "cliente_Remitente_id")
    private Cliente clienteRemitente;
    @ManyToOne
    @JoinColumn(name = "cliente_Destinatario_id")
    private Cliente clientedestinatario;
    @ManyToOne
    @JoinColumn(name = "estado_pedido_id")
    private EstadoDelPedido estado;
    @ManyToOne
    @JoinColumn(name = "Documento_Transporte_id")
    private DocumentoTransporte unDocumento;
    @OneToMany(mappedBy = "pedido")
    private List<Paquete> paquetes;
    @OneToOne
    @JoinColumn(name = "direccionOrigen_id")
    private Direccion origen;
    @OneToOne
    @JoinColumn(name = "direccionDestino_id")
    private Direccion destino;
    @OneToOne
    @JoinColumn(name = "almacen_id")
    private Almacen almacenOrigen;
    @OneToOne
    @JoinColumn(name = "almacen_id")
    private Almacen almacenDestino;

    public Almacen getAlmacenDestino() {
        return almacenDestino;
    }

    public void setAlmacenDestino(Almacen almacenDestino) {
        this.almacenDestino = almacenDestino;
    }

    public Almacen getAlmacenOrigen() {
        return almacenOrigen;
    }

    public void setAlmacenOrigen(Almacen almacenOrigen) {
        this.almacenOrigen = almacenOrigen;
    }

    public Pedido() {
    }

    public Pedido(int id, boolean estadoCosto, String descripcion, LocalDate fechaPedido, Cliente clienteRemitente, Cliente clientedestinatario, EstadoDelPedido estado, DocumentoTransporte unDocumento, List<Paquete> paquetes, Direccion origen, Direccion destino, Almacen almacenOrigen, Almacen almacenDestino) {
        this.id = id;

        this.estadoCosto = estadoCosto;
        this.descripcion = descripcion;
        this.fechaPedido = fechaPedido;
        this.clienteRemitente = clienteRemitente;
        this.clientedestinatario = clientedestinatario;
        this.estado = estado;
        this.unDocumento = unDocumento;
        this.paquetes = paquetes;
        this.origen = origen;
        this.destino = destino;
        this.almacenOrigen = almacenOrigen;
        this.almacenDestino = almacenDestino;
    }

    public Pedido(boolean estadoCosto, String descripcion, LocalDate localDate, Cliente idRemitente, Cliente idDestinatario, EstadoDelPedido idEstadoPedido, Almacen idAlmacenOrigen, Almacen idAlmacenDestino) {

        this.estadoCosto = estadoCosto;
        this.descripcion = descripcion;
        this.fechaPedido = localDate;
        this.clienteRemitente = idRemitente;
        this.clientedestinatario = idDestinatario;
        this.estado = idEstadoPedido;
        this.almacenOrigen = idAlmacenOrigen;
        this.almacenDestino = idAlmacenDestino;
    }

    public boolean isEstadoCosto() {
        return estadoCosto;
    }

    public void setEstadoCosto(boolean estadoCosto) {
        this.estadoCosto = estadoCosto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Direccion getOrigen() {
        return origen;
    }

    public void setOrigen(Direccion origen) {
        this.origen = origen;
    }

    public Direccion getDestino() {
        return destino;
    }

    public void setDestino(Direccion destino) {
        this.destino = destino;
    }

    public Cliente getClienteRemitente() {
        return clienteRemitente;
    }

    public void setClienteRemitente(Cliente clienteRemitente) {
        this.clienteRemitente = clienteRemitente;
    }

    public Cliente getClientedestinatario() {
        return clientedestinatario;
    }

    public void setClientedestinatario(Cliente clientedestinatario) {
        this.clientedestinatario = clientedestinatario;
    }

    public DocumentoTransporte getUnDocumento() {
        return unDocumento;
    }

    public void setUnDocumento(DocumentoTransporte unDocumento) {
        this.unDocumento = unDocumento;
    }

    public DocumentoTransporte getDocumentoTrasporte() {
        return unDocumento;
    }

    public void setDocumentoTrasporte(DocumentoTransporte documentoTrasporte) {
        this.unDocumento = documentoTrasporte;
    }

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Cliente getCliente() {
        return clienteRemitente;
    }

    public void setCliente(Cliente cliente) {
        this.clienteRemitente = cliente;
    }

    public EstadoDelPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoDelPedido estado) {
        this.estado = estado;
    }

}
