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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DocumentoTransporte implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDate fechaEmision;
    private String contenido;
    @OneToMany(mappedBy = "unDocumento")
    private List<Pedido> pedidos;
    @OneToOne
    @JoinColumn(name = "unEmpleado_id")
    private EmpleadoTransportista empleado;
    public DocumentoTransporte() {
    }

    public DocumentoTransporte(int id, LocalDate fechaEmision, String contenido, List<Pedido> pedidos, EmpleadoTransportista empleado) {
        this.id = id;
        this.fechaEmision = fechaEmision;
        this.contenido = contenido;
        this.pedidos = pedidos;
        this.empleado = empleado;
    }

    public EmpleadoTransportista getEmpleado() {
        return empleado;
    }

    public void setEmpleado(EmpleadoTransportista empleado) {
        this.empleado = empleado;
    }

    

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

}
