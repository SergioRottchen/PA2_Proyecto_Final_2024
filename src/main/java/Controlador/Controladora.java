/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Almacen;
import Modelo.Cliente;
import Modelo.Direccion;
import Modelo.EstadoDelPedido;
import Modelo.Pedido;
import Modelo.Proveedor;
import Modelo.Rol;
import Modelo.Usuario;
import com.mycompany.pa2_proyecto_final_2023.Persistencia.exceptions.ControladoraPersistencia;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author sirgi
 */
public class Controladora {

    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public List traerUsuarios() {
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();
        return listaUsuarios;
    }
    public List traerClientes(){
        List<Cliente> listaClientes = controlPersis.traerClientes();
        return listaClientes;
    }
    public Usuario validarUsuario(String nombre, String contrasenia) {
        Usuario user = null;

        //String mensaje="";
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();
        for (Usuario usu : listaUsuarios) {
            if (usu.getNombreUsuario().equals(nombre)) {
                if (usu.getContrasenia().equals(contrasenia)) {
                    // mensaje ="Usuario y contraseña correctos. Bienvenido/a!";   
                    user = usu;
                    return user;
                } else {
                    //mensaje ="Contraseña incorrecta";   
                    user = null;
                    return user;
                }
            } else {
                //mensaje ="Usuario no encontrado";
                user = null;
                return user;
            }
        }
        return user;
    }

    public List<Rol> TraerRoles() {

        return controlPersis.traerRoles();
    }

    public void crearUsuario(String usuario, String contra, String rolRecibido, Almacen dir) {
        Usuario usu = new Usuario();
        usu.setNombreUsuario(usuario);
        usu.setContrasenia(contra);
        Rol rolEncontrado = new Rol();
        rolEncontrado = this.TraerRol(rolRecibido);
        try {
            if (rolEncontrado != null) {
                usu.setUnRol(rolEncontrado);

            }
        } catch (Exception e) {
            System.err.println("En el metodo crear usuario no se encontro el rol seleccionado");
        }
        usu.setAlmacen(dir);
        controlPersis.crearUsuario(usu);

    }

    private Rol TraerRol(String rolRecibido) {
        List<Rol> listaRoles = controlPersis.traerRoles();
        for (Rol rol : listaRoles) {
            if (rol.getNombreRol().equals(rolRecibido)) {
                return rol;
            }

        }
        return null;
    }
    private Almacen TraerAlmacen(String almacenRecibido) {
        List<Almacen> listaAlmacenes = controlPersis.traerAlmacenes();
        for (Almacen almacen : listaAlmacenes) {
            if (almacen.getNombre().equals(almacenRecibido)) {
                return almacen;
            }

        }
        return null;
    }

    public void borrarUsuario(int id_usuario) {
        controlPersis.borrarUsuario(id_usuario);
    }

    public Usuario traerUsuario(int id_usuario) {

        return controlPersis.traerUsuario(id_usuario);
    }

    public void editarUsuario(Usuario usu, String usuario, String contra, String rolRecibido,String almacen) {
        usu.setNombreUsuario(usuario);
        usu.setContrasenia(contra);
        Rol rolEncontrado = new Rol();
        rolEncontrado = this.TraerRol(rolRecibido);
        try {
            if (rolEncontrado != null) {
                usu.setUnRol(rolEncontrado);

            }
        } catch (Exception e) {
            System.err.println("En el metodo Editar usuario no se encontro el rol seleccionado");
        }
        //traemos el almacen
        Almacen almacenEncontrado = new Almacen();
        almacenEncontrado = this.TraerAlmacen(almacen);
        try {
            if (almacenEncontrado != null) {
                usu.setAlmacen(almacenEncontrado);

            }
        } catch (Exception e) {
            System.err.println("En el metodo Editar usuario no se encontro el almacen seleccionado");
        }
        controlPersis.editarUsuario(usu);
    }
 public void crearCliente(Cliente cli){
  try {
           controlPersis.crearCliente(cli); 
           
        } catch(Exception e){
               System.err.println("Error al intentar crear un nuevo Cliente");
     
            
    }   
 }
    public void crearCliente(String dni, String nombre, String apellido, String telefono, String correo, Direccion direccion) {

        try {
            if(dni.isEmpty()||nombre.isEmpty()||apellido.isEmpty()||telefono.isEmpty()||correo.isEmpty()||direccion==null){
                JOptionPane.showMessageDialog(null, "Uno de los campos esta vacio.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }else{
                Cliente cli = new Cliente(dni, nombre, apellido, telefono, correo, direccion);
            controlPersis.crearCliente(cli);
            }
        } catch(Exception e){
               System.err.println("Error al intentar crear un nuevo Cliente");
     
            
    }

        }

    public Cliente traerCliente(int id_cli) {
        return controlPersis.traerCliente(id_cli);
    }

    public void editarCliente(Cliente cli) {
        controlPersis.editarCliente(cli);
    }

    public void borrarCliente(int id_usuario) {
        controlPersis.borrarCliente(id_usuario);
    }

    public void crearProveedor(String nombre, String servicio, String precio, String telefono, String correo) {
        Proveedor prov = new Proveedor();
       prov.setNombre(nombre);
       prov.setServicio(servicio);
       prov.setPrecio(Double.parseDouble(precio));
       prov.setTelefono(telefono);
       prov.setMail(correo);
     
       try{
          controlPersis.crearProveedor(prov); 
          
       }catch(Exception e){
          
       }
    }

    public void borrarProveedor(int id_usuario) {
        controlPersis.borrarProveedor(id_usuario);
        
    }

    public List<Proveedor> traerProveedores() {
        List<Proveedor> proveedores = controlPersis.traerProveedores();
        return proveedores;
        
    }

    public void editarProveedor(Proveedor prov) {
        controlPersis.editarProveedor(prov);
    }

    public Proveedor traerProveedor(int id_prov) {
        return controlPersis.traerProveedor(id_prov);
    }

    public List<Direccion> traerDirecciones() {
        return controlPersis.traerDirecciones();
    }

    public void borrarDireccion(int id_usuario) {
        controlPersis.borrarDireccion(id_usuario);
    }

    public void crearDireccion(Direccion dir) {
        controlPersis.crearDireccion(dir);
    }

    public Direccion traerDireccion(int id_usuario) {
       return controlPersis.traerDireccion(id_usuario);
    }

    public void editarDireccion(Direccion dir) {
        controlPersis.editarDireccion(dir);
    }

    public List<Almacen> traerAlmacenes() {
        List<Almacen> a = controlPersis.traerAlmacenes();
        return a;
    }

    public Almacen traerAlmacen(int id_usuario) {
        return controlPersis.traerAlmacen(id_usuario);
    }

    public void borrarAlmacen(int id_usuario) {
        controlPersis.borrarAlmacen(  id_usuario  );
    }

    public void crearAlmacen(Almacen alm) {
        controlPersis.crearAlmacen(alm);
    }

    public void editarAlmacen(Almacen alm) {
        controlPersis.editarAlmacen(alm);
    }

    public Direccion traerDireccionIdExistente(Direccion destino) {
        return controlPersis.traerDireccionExistente(destino);

    }

    public void crearPedido(Pedido nuevoPedido) {
        controlPersis.crearPedido(nuevoPedido);
    }

    public List<Pedido> traerPedidos() {
        return controlPersis.traerPedidos();
    }

    public int traerClienteId(String text) {
return controlPersis.traerClienteId(text);    }

    public EstadoDelPedido traerEstadoDelPedido(int id) {
 return controlPersis.traerEstadoDelPedido(id);
    }

    public Cliente traerClientePorDni(String text) {
return controlPersis.traerClientePorDni(text);  }

    public Pedido traerPedido(int ultimoID) {
return controlPersis.traerPedido(ultimoID);
    }

    public void editarPedido(Pedido pedidoCreado) {
controlPersis.editarPedido(pedidoCreado);    }

    public Direccion crearDireccionSiNoExiste(Direccion destino) {
     return   controlPersis.crearDireccionSiNoExiste(destino);
    }
    }

