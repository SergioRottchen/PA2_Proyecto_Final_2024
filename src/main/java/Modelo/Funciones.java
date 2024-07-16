/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.List;

/**
 *
 * @author sirgi
 */
public class Funciones {

    public int calcularUltimoPedido(List<Pedido> p) {
        // Supongamos que tienes una lista de objetos con un campo 'id'
        int ultimoID;

        // Verificar si la lista no está vacía
        if (!p.isEmpty()) {
            // Obtener el último objeto en la lista
            Pedido ultimoObjeto = p.get(p.size() - 1);

            // Obtener el ID del último objeto
            ultimoID = ultimoObjeto.getId() + 1;

        } else {
            ultimoID = 1;
        }
        return ultimoID;
    }
    public boolean calcularEstadoDeCosto(String s){
        if(s=="Pago"){
            return true;
        }else{
            return false;
        }
        
        
    }
}
