/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Proveedor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String servicio;
    private double precio;
    private String telefono;
    private String mail;
    @ManyToOne
    @JoinColumn(name = "id_almacen")
    private Almacen unAlmacen;

    public Proveedor() {
    }

    public Proveedor(int id, String nombre, String servicio, double precio, String telefono, String mail, Almacen unAlmacen) {
        this.id = id;
        this.nombre = nombre;
        this.servicio = servicio;
        this.precio = precio;
        this.telefono = telefono;
        this.mail = mail;
        this.unAlmacen = unAlmacen;
    }

    public Almacen getUnAlmacen() {
        return unAlmacen;
    }

    public void setUnAlmacen(Almacen unAlmacen) {
        this.unAlmacen = unAlmacen;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
