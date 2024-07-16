/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Paquete implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long costo;
    private String descripcion;
    private double peso;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dimension_id", referencedColumnName = "id")
    private Dimension tamano;
    @ManyToOne
    @JoinColumn(name = "almacen_id")
    private Almacen ubicacion;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido unPedido;

    public Paquete() {
    }

    public Paquete(int id, long costo, String descripcion, double peso, Dimension tamano, Almacen ubicacion, Pedido unPedido) {
        this.id = id;
        this.costo = costo;
        this.descripcion = descripcion;
        this.peso = peso;
        this.tamano = tamano;
        this.ubicacion = ubicacion;
        this.unPedido = unPedido;
    }

    public long getCosto() {
        return costo;
    }

    public void setCosto(long costo) {
        this.costo = costo;
    }

    

    public Pedido getUnPedido() {
        return unPedido;
    }

    public void setUnPedido(Pedido unPedido) {
        this.unPedido = unPedido;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Dimension getTamano() {
        return tamano;
    }

    public void setTamano(Dimension tamano) {
        this.tamano = tamano;
    }

    public Almacen getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Almacen ubicacion) {
        this.ubicacion = ubicacion;
    }

}
