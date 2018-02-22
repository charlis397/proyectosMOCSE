/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Cargo;
import modelo.Proveedor;
import vista.VistaAgregarCargo;
import vista.VistaAgregarEmpleado;
import vista.VistaAgregarProducto;
import vista.VistaAgregarProveedor;
import vista.VistaOpcionesAgregar;

/**
 *
 * @author charlis
 */
public class ControlOpcionesAgregar implements ActionListener {
    
    private final VistaOpcionesAgregar vista;
    
    public ControlOpcionesAgregar(VistaOpcionesAgregar vista){
        this.vista = vista;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.getEmpleado()){
            VistaAgregarEmpleado formulario = new VistaAgregarEmpleado();
            Cargo cargo = new Cargo();
            
            cargo.consulta(formulario);
            formulario.setVisible(true);
        }
        
        if(e.getSource() == vista.getCargo()){
            VistaAgregarCargo formulario = new VistaAgregarCargo();
            formulario.setVisible(true);
        }
        
        if(e.getSource() == vista.getProveedor()){
            VistaAgregarProveedor formulario = new VistaAgregarProveedor();
            formulario.setVisible(true);
        }
        
        
        if(e.getSource() == vista.getProducto()){
            VistaAgregarProducto formulario = new VistaAgregarProducto();
            Proveedor proveedor = new Proveedor();
            
            proveedor.consulta(formulario);
            formulario.setVisible(true);
        }
        
    }
    
}
