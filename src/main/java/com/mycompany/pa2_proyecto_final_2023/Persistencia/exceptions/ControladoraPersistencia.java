/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pa2_proyecto_final_2023.Persistencia.exceptions;

import Modelo.Almacen;
import Modelo.Cliente;
import Modelo.Direccion;
import Modelo.EstadoDelPedido;
import Modelo.Pedido;
import Modelo.Proveedor;
import Modelo.Rol;
import Modelo.Usuario;
import Persistencia.exceptions.AlmacenJpaController;
import Persistencia.exceptions.ClienteJpaController;
import Persistencia.exceptions.DireccionJpaController;
import Persistencia.exceptions.EstadoDelPedidoJpaController;
import Persistencia.exceptions.PedidoJpaController;
import Persistencia.exceptions.ProveedorJpaController;
import Persistencia.exceptions.RolJpaController;
import Persistencia.exceptions.UsuarioJpaController;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sirgi
 */
public class ControladoraPersistencia {

    UsuarioJpaController usuJpa = new UsuarioJpaController();
    RolJpaController rolJpa = new RolJpaController();
    ClienteJpaController cliJpa = new ClienteJpaController();
    ProveedorJpaController provJpa = new ProveedorJpaController();
    DireccionJpaController dirJpa = new DireccionJpaController();
    AlmacenJpaController almJpa = new AlmacenJpaController();
    PedidoJpaController pedJpa = new PedidoJpaController();
    EstadoDelPedidoJpaController estPedJpa = new EstadoDelPedidoJpaController();

    public List<Usuario> traerUsuarios() {
        List<Usuario> listaUsuarios = usuJpa.findUsuarioEntities();
        return listaUsuarios;
    }

    public List<Rol> traerRoles() {
        List<Rol> listaRoles = rolJpa.findRolEntities();
        return listaRoles;
    }

    public void crearUsuario(Usuario usu) {
        try {
            usuJpa.create(usu);
            System.out.println("Usuario Creado con exito");
        } catch (Exception e) {
            System.err.println("No se pudo crear el usuario en la BD");
        }
    }

    public void borrarUsuario(int id_usuario) {
        try {
            usuJpa.destroy(id_usuario);
            System.out.println("Usuario Borrado con exito");
        } catch (Exception e) {
            System.err.println("No se pudo borrar el usuario en la BD");
        }

    }

    public Usuario traerUsuario(int id_usuario) {
        return usuJpa.findUsuario(id_usuario);
    }

    public void editarUsuario(Usuario usu) {
        try {
            usuJpa.edit(usu);

        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void crearCliente(Cliente cli) {
        try {
            cliJpa.create(cli);
            JOptionPane.showMessageDialog(null, "Cliente creado con exito.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            System.err.println("No se pudo crear el Cliente en la BD");

        }

    }

    public List<Cliente> traerClientes() {
        List<Cliente> listaClientes = cliJpa.findClienteEntities();
        return listaClientes;

    }

    public Cliente traerCliente(int id_cli) {
        return cliJpa.findCliente(id_cli);
    }

    public void editarCliente(Cliente cli) {
        try {
            cliJpa.edit(cli);

        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarCliente(int id_usuario)  {
        try {
            cliJpa.destroy(id_usuario);

        } catch (Exception e) {
            System.err.println("No se pudo borrar el usuario en la BD");
        }
    }

    public void crearProveedor(Proveedor prov) {
        try {
            provJpa.create(prov);
        } catch (Exception e) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public void borrarProveedor(int id_usuario) {
        try {
            provJpa.destroy(id_usuario);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        
    }}

    public List<Proveedor> traerProveedores() {
        List<Proveedor> listaProveedores = provJpa.findProveedorEntities();
        return listaProveedores;
    }

    public void editarProveedor(Proveedor prov) {
        try {
            provJpa.edit(prov);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public Proveedor traerProveedor(int id_prov) {
        try {
            Proveedor prov = provJpa.findProveedor(id_prov);
            return prov;
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public List<Direccion> traerDirecciones() {
        return dirJpa.findDireccionEntities();
    }

    public void borrarDireccion(int id_usuario) {
        try {
            dirJpa.destroy(id_usuario);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearDireccion(Direccion dir) {
        try {
            dirJpa.create(dir);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Direccion traerDireccion(int id_usuario) {
        try {
            return dirJpa.findDireccion(id_usuario);

        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void editarDireccion(Direccion dir) {
        try {
            dirJpa.edit(dir);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public List<Almacen> traerAlmacenes() {
        try {
            List<Almacen> al = almJpa.findAlmacenEntities();
            return al;
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public Almacen traerAlmacen(int id_usuario) {
        Almacen alm = new Almacen();
        try {
       
            alm = almJpa.findAlmacen(id_usuario);
            return alm;
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);

        }
        return alm;
    }

    public void borrarAlmacen(int id_usuario) {
        try {
            almJpa.destroy(id_usuario);
        }catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void crearAlmacen(Almacen alm) {
        try {
            almJpa.create(alm);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void editarAlmacen(Almacen alm) {
        try {
            almJpa.edit(alm);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Direccion traerDireccionExistente(Direccion destino) {
        List<Direccion> direcciones = dirJpa.findDireccionEntities();

        for (Direccion dir : direcciones) {
            if ((dir.getCalle().equals(destino.getCalle())) && dir.getCiudad().equals(destino.getCiudad()) && dir.getEstado().equals(destino.getEstado()) && dir.getPais().equals(destino.getPais())) {
                return dir;
            } else {
                return null;
            }

        }
        return null;

    }

    public void crearPedido(Pedido nuevoPedido) {
        try {
            pedJpa.create(nuevoPedido);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public List<Pedido> traerPedidos() {
        try {
            return pedJpa.findPedidoEntities(0, 0);
        } catch (Exception ex) {
            return null;
        }

    }

    public int traerClienteId(String text) {
        List<Usuario> usuarios = usuJpa.findUsuarioEntities();
        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(text)) {
                return u.getId();
            } else {
                return 0;
            }
        }
        return 0;
    }

    public EstadoDelPedido traerEstadoDelPedido(int id) {
        return estPedJpa.findEstadoDelPedido(id);

    }

    public Cliente traerClientePorDni(String dni) {
        try {
        List<Cliente> clientes = cliJpa.findClienteEntities();
        for (Cliente c : clientes) {
            if (c.getNombre().equals(dni)) {
                return null;
            } else {
                return c;
            }
        }}catch(Exception e){
            
        }
       
return null;
    }

    public Pedido traerPedido(int ultimoID) {
        try {
            return pedJpa.findPedido(ultimoID);

        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public void editarPedido(Pedido pedidoCreado) {
        try {
            pedJpa.edit(pedidoCreado);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Direccion crearDireccionSiNoExiste(Direccion destino) {
        List<Direccion> listaDeDirecciones = dirJpa.findDireccionEntities();
        for(Direccion dir : listaDeDirecciones){
            if(dir.getCalle().equals(destino.getCalle())&&dir.getCiudad().equals(destino.getCiudad())&&dir.getEstado().equals(destino.getEstado())&&dir.getPais().equals(destino.getPais())){
                return dir;
            }else{
                dirJpa.create(destino);
                return dirJpa.findDireccion(listaDeDirecciones.size()+1);
            }
        }
        return null;
    }

}
