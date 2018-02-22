/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import modelo.Empleado;
import vista.VistaAgregarEmpleado;
import vista.VistaAgregarProducto;

/**
 *
 * @author charlis
 */
public class Consulta {
    
    private static Conexion conexion;
    private static Statement st;
    private PreparedStatement ps;
    private ResultSet rs;
    private String cadena = "";
    
    public ResultSet cargos(VistaAgregarEmpleado vista){
        conexion = new Conexion();
        Connection con = conexion.getConexion();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM cargo");
            rs = ps.executeQuery();  
            vista.getCargo().removeAllItems();
            while (rs.next()) {
                    System.out.println(""+rs.getString(1));
                    
                    vista.getCargo().addItem(rs.getString(2));
                }
            ps.close();
            con.close();
        }catch(HeadlessException | SQLException e){  
            JOptionPane.showMessageDialog(null, "Error al Consultar", "", JOptionPane.PLAIN_MESSAGE);
            System.err.println(""+e);
        }
        return rs;
    }
    
    
    public ResultSet proveedores(VistaAgregarProducto vista){
        conexion = new Conexion();
        Connection con = conexion.getConexion();
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM proveedor");
            rs = ps.executeQuery();  
            vista.getProveedores().removeAllItems();
            while (rs.next()) {
                    System.out.println(""+rs.getString(1));
                    
                    vista.getProveedores().addItem(rs.getString(2));
                }
            ps.close();
            con.close();
        }catch(HeadlessException | SQLException e){  
            JOptionPane.showMessageDialog(null, "Error al Consultar", "", JOptionPane.PLAIN_MESSAGE);
            System.err.println(""+e);
        }
        return rs;
    }
    
    public boolean empleado(Empleado empleado){
        boolean correcto = false;
        conexion = new Conexion();
        Connection con = conexion.getConexion();
        System.out.println("LLEGUE AQUI");
        try{
            System.out.println("LLEGUE TAMBOR");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM empleado WHERE codigo LIKE '"+empleado.getCodigo()+"'");
            rs = ps.executeQuery();
            

            rs.next();
            cadena = rs.getString(1);
            System.out.println("sout "+ cadena);
            ps.close();
            con.close();

            correcto = true;
            ps.close();
            con.close();
            
        }catch(HeadlessException | SQLException e){  
            //JOptionPane.showMessageDialog(null, "Error al Consulta dsafddasfr", "", JOptionPane.PLAIN_MESSAGE);
            System.err.println(""+e);
            correcto = false;
        }
        return correcto;
    }
    
    public Object[][] productos()
    {
        
        conexion = new Conexion();
        Connection cone = conexion.getConexion();
       
        int i=0,in=0;
        Object[][] datos=new Object[i=this.rows()][4];   
        try {
                
                PreparedStatement user = cone.prepareStatement("select p.nombre, pr.nombre,p.precio,p.descripcion from producto p, proveedor pr, proveedor_producto pp WHERE pp.idproveedor = pr.idproveedor AND pp.idproducto = p.idproducto;");
                rs = user.executeQuery();
                while (rs.next()) 
                    { 
                        
                          
                           datos[in][0]=rs.getString(1);
                           datos[in][1]=rs.getString(2);
                           datos[in][2]=rs.getString(3);
                           datos[in][3]=rs.getString(4);
                           
                           in++;
                           
                    }
                    rs.close();
                    user.close();
             } catch (SQLException e) {
                 System.err.println(""+e);
            }
     return datos;   
    }
    
    
    public int rows()
    {
        conexion = new Conexion();
        Connection con = conexion.getConexion();
        int i=0;
        try{
            PreparedStatement ps = con.prepareStatement("select * from producto");                                          
            rs = ps.executeQuery();
            while (rs.next())
               {
                   
                  i++;
                   
               }
            ps.close();
            rs.close();
            con.close();
        }catch(SQLException e){  
            System.err.println(""+e);
        }
        return i;
    }
    
}
