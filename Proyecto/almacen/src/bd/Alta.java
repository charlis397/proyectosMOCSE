/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Cargo;
import modelo.Empleado;
import modelo.OrdenCompra;
import modelo.Pedido;
import modelo.Producto;
import modelo.Proveedor;

/**
 *
 * @author charlis
 */
public class Alta {
    
    private static Conexion conexion;
    private static Statement st;
    boolean correcto = false;
    private String SQL = "";
    
    
    public boolean empleado(Empleado empleado, Cargo cargo){
        conexion = new Conexion();
        Connection con = conexion.getConexion();

        try{
            
            SQL = "INSERT INTO empleado VALUES(null,'"+empleado.getNombre()+"','"+empleado.getCodigo()+"',(SELECT idcargo FROM cargo WHERE nombre LIKE '"+empleado.getCargo().getNombre()+"'))";
            System.out.println(SQL);
            //INSERT INTO empleado VALUES(null,"Carlos Eduarod Caballero","06720",(SELECT idcargo FROM cargo WHERE nombre LIKE  "Almacen"));

            st = con.createStatement();
            st.executeUpdate(SQL);
           
            correcto = true;
            JOptionPane.showMessageDialog(null, "Exito al Registrar", "", JOptionPane.PLAIN_MESSAGE);
            con.close();
        }
        catch(SQLException ex){ 
            correcto = false;
            System.err.println("" + ex);
            JOptionPane.showMessageDialog(null, "Error al Insertar los Datos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return correcto;
    }
    
    
    public boolean cargo(Cargo cargo){
        conexion = new Conexion();
        Connection con = conexion.getConexion();

        try{
            
            SQL = "INSERT INTO cargo VALUES(null,'"+cargo.getNombre()+"')";
            System.out.println(SQL);
            st = con.createStatement();
            st.executeUpdate(SQL);
           
            correcto = true;
            JOptionPane.showMessageDialog(null, "Exito al Registrar", "", JOptionPane.PLAIN_MESSAGE);
            con.close();
        }
        catch(SQLException ex){ 
            correcto = false;
            JOptionPane.showMessageDialog(null, "Error al Insertar los Datos", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
        return correcto;
    }
    
    
    public boolean proveedor(Proveedor proveedor){
        conexion = new Conexion();
        Connection con = conexion.getConexion();

        try{
            
            SQL = "INSERT INTO proveedor VALUES(null,'"+proveedor.getNombre()+"','"+proveedor.getDireccion()+"','"+proveedor.getTelefono()+"','"+proveedor.getCorreo()+"','"+proveedor.getDescripcion()+"')";
            System.out.println(SQL);
            st = con.createStatement();
            st.executeUpdate(SQL);
           
            correcto = true;
            JOptionPane.showMessageDialog(null, "Exito al Registrar", "", JOptionPane.PLAIN_MESSAGE);
            con.close();
        }
        catch(SQLException ex){ 
            correcto = false;
            JOptionPane.showMessageDialog(null, "Error al Insertar los Datos", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
        return correcto;
    }
    
    
    
    public boolean producto(Producto producto){
        conexion = new Conexion();
        Connection con = conexion.getConexion();

        try{
            
            SQL = "INSERT INTO producto VALUES(null,'"+producto.getNombre()+"','"+producto.getPrecio()+"','"+producto.getDescripcion()+"','"+producto.getImagen()+"')";
            System.out.println(SQL);
            st = con.createStatement();
            st.executeUpdate(SQL);
           
            correcto = true;
            //JOptionPane.showMessageDialog(null, "Exito al Registrar", "", JOptionPane.PLAIN_MESSAGE);
            con.close();
        }
        catch(SQLException ex){ 
            correcto = false;
            //JOptionPane.showMessageDialog(null, "Error al Insertar los Datos", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
        return correcto;
    }
    
    public boolean pedido(Pedido pedido){
        conexion = new Conexion();
        Connection con = conexion.getConexion();
        
       
        String fechaEntrega = pedido.getFechaEntrega();
        if("".equals(pedido.getFechaEntrega())){
            fechaEntrega = null;
        }
        else{
            fechaEntrega = "'"+fechaEntrega+"'";
        }
        
        
        try{
            
            SQL = "INSERT INTO pedido VALUES(null,'"+pedido.getFechaPedido()+"',"+fechaEntrega+",(SELECT idempleado FROM empleado WHERE codigo LIKE '"+pedido.getEmpleado().getCodigo()+"'),'"+pedido.getDetalles()+"','"+pedido.getTotal()+"')";
            System.out.println(SQL);
            st = con.createStatement();
            st.executeUpdate(SQL);
           
            correcto = true;
            JOptionPane.showMessageDialog(null, "Pedido Realizado", "", JOptionPane.PLAIN_MESSAGE);
            con.close();
        }
        catch(SQLException ex){ 
            correcto = false;
            JOptionPane.showMessageDialog(null, "Error al Insertar los Datos", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
        return correcto;
    }
    
    
    
    public boolean productoProveedor(Producto producto, Proveedor proveedor){
        conexion = new Conexion();
        Connection con = conexion.getConexion();
        
        try{
            
            SQL = "INSERT INTO proveedor_producto VALUES((SELECT idproveedor FROM proveedor WHERE nombre LIKE '"+proveedor.getNombre()+"'),(SELECT idproducto FROM producto WHERE nombre LIKE '"+producto.getNombre()+"'))";
            System.out.println(SQL);
            st = con.createStatement();
            st.executeUpdate(SQL);
           
            correcto = true;
            JOptionPane.showMessageDialog(null, "Exito al Registrar", "", JOptionPane.PLAIN_MESSAGE);
            con.close();
        }
        catch(SQLException ex){ 
            correcto = false;
            JOptionPane.showMessageDialog(null, "Error al Insertar los Datos", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
        return correcto;
    }
    
    
    public boolean ordenCompra(OrdenCompra compra){
        conexion = new Conexion();
        Connection con = conexion.getConexion();
        
        try{
            
            SQL = "INSERT INTO ordenCompra VALUES(null,(SELECT idproducto FROM producto WHERE nombre LIKE '"+compra.getProducto().getNombre()+"'),(SELECT idproveedor FROM proveedor WHERE nombre LIKE '"+compra.getProveedor().getNombre()+"'), '"+compra.getCantidad()+"',(SELECT MAX(idpedido) from pedido))";
            System.out.println(SQL);
            st = con.createStatement();
            st.executeUpdate(SQL);
           
            correcto = true;
            //JOptionPane.showMessageDialog(null, "Exito al Registrar", "", JOptionPane.PLAIN_MESSAGE);
            con.close();
        }
        catch(SQLException ex){ 
            correcto = false;
            JOptionPane.showMessageDialog(null, "Error al Insertar los Datos", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex);
        }
        return correcto;
    }
    
}
