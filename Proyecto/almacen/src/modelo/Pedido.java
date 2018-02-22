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
public class Pedido {
    private final String fechaPedido;
    private final Empleado empleado;
    private final String fechaEntrega;
    private final String detalles;
    private final float total;

    public Pedido(String fechaPedido, Empleado empleado, String fechaEntrega, String detalles, float total) {
        this.fechaPedido = fechaPedido;
        this.empleado = empleado;
        this.fechaEntrega = fechaEntrega;
        this.detalles = detalles;
        this.total = total;
    }
    
    
    public void alta(){
        Alta alta = new Alta();
        alta.pedido(this);
    }
    
    

    public String getFechaPedido() {
        return fechaPedido;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public String getDetalles() {
        return detalles;
    }

    public float getTotal() {
        return total;
    }
    
    
    
    
    
}
