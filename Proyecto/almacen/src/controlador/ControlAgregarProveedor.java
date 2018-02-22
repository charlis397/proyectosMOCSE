/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import modelo.Cargo;
import modelo.Empleado;
import modelo.Proveedor;
import vista.VistaAgregarEmpleado;
import vista.VistaAgregarProveedor;

/**
 *
 * @author charlis
 */
public class ControlAgregarProveedor implements ActionListener{
    private final VistaAgregarProveedor vista;
    private Proveedor proveedor;
    
    public ControlAgregarProveedor(VistaAgregarProveedor vista){
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if(e.getSource() == vista.getAgregar()){
            String nombre = vista.getNombre().getText();
            String direccion = vista.getDireccion().getText();
            String telefono = vista.getTelefono().getText();
            String correo = vista.getCorreo().getText();
            String descripcion = vista.getDetalles().getText();
            
            
            proveedor = new Proveedor(nombre, direccion, telefono, correo, descripcion);
            proveedor.alta();
        }
        
    }
    
}
