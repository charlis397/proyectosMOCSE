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
public class OrdenCompra {
    private final Producto producto;
    private final Proveedor proveedor;
    private final int cantidad;
    private final Pedido pedido;

    public OrdenCompra(Producto producto, Proveedor proveedor,int cantidad, Pedido pedido) {
        this.producto = producto;
        this.proveedor = proveedor;
        this.cantidad = cantidad;
        this.pedido = pedido;
    }
    
    
    public void alta(){
        Alta alta = new Alta();
        alta.ordenCompra(this);
    }

    public Producto getProducto() {
        return producto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Pedido getPedido() {
        return pedido;
    }
    
    
    
    
}
