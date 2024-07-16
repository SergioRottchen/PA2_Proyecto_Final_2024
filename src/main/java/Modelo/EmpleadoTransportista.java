/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class EmpleadoTransportista extends Cliente implements Serializable {

    private String matriculaVehiculo;
    @OneToOne
    @JoinColumn(name = "almacen_id")
    private Almacen almacen;

    public EmpleadoTransportista() {
    }

    public EmpleadoTransportista(String matriculaVehiculo, Almacen almacen) {
        this.matriculaVehiculo = matriculaVehiculo;
        this.almacen = almacen;
    }

    

    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }

    public void setMatriculaVehiculo(String matriculaVehiculo) {
        this.matriculaVehiculo = matriculaVehiculo;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

}
