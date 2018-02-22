/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Cargo;
import modelo.Empleado;
import modelo.Producto;
import modelo.Proveedor;
import vista.VistaAgregarProducto;

/**
 *
 * @author charlis
 */
public class ControlAgregarProducto implements ActionListener{
    private final VistaAgregarProducto vista;
    private Producto producto;
    
    public ControlAgregarProducto(VistaAgregarProducto vista){
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if(e.getSource() == vista.getAgregar()){
            String nombre = vista.getNombre().getText();
            String nombreProveedor = vista.getProveedores().getSelectedItem().toString();
            float precio = Float.parseFloat(vista.getPrecio().getText());
            String descripcion = vista.getDescripcion().getText();
            String imagen = "";
            
            Proveedor proveedor = new Proveedor(nombreProveedor);
            
            producto = new Producto(nombre, precio, descripcion, imagen);
            producto.alta(proveedor);
            
        }
    }
    
}
