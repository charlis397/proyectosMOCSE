/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import bd.Alta;
import bd.Consulta;
import java.sql.ResultSet;
import vista.VistaAgregarProducto;

/**
 *
 * @author charlis
 */
public class Proveedor {
    private String nombre = "";
    private String direccion = "";
    private String telefono = "";
    private String correo = "";
    private String descripcion = "";

    public Proveedor(String nombre, String direccion, String telefono, String correo, String descripcion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.descripcion = descripcion;
    }
    
    public Proveedor(String nombre){
        this.nombre = nombre;
    }
    
    public Proveedor(){   }
    
    
    
    
    public void alta(){
        Alta alta = new Alta();
        alta.proveedor(this);
    }
    
    public ResultSet consulta(VistaAgregarProducto vista){
        Consulta consulta = new Consulta();
        return consulta.proveedores(vista);
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    

    
}
