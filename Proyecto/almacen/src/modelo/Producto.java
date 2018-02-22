/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import bd.Alta;

/**
 *
 * @author charlis
 */
public class Producto {
    
    private String nombre = "";
    private float precio = 0;
    private String descripcion = "";
    private String imagen = "";

    public Producto(String nombre, float precio,String descripcion, String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Producto(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
    public void alta(Proveedor proveedor){
        Alta alta = new Alta();
        alta.producto(this);
        alta.productoProveedor(this, proveedor);
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }
    
    
    
    
    
    
}
