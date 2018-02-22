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
import vista.VistaAgregarCargo;
import vista.VistaAgregarEmpleado;

/**
 *
 * @author charlis
 */
public class ControlAgregarCargo implements ActionListener{
    private final VistaAgregarCargo vista;
    private Cargo cargo;
    
    public ControlAgregarCargo(VistaAgregarCargo vista){
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if(e.getSource() == vista.getAgregar()){
            String nombre = vista.getNombre().getText();
            
            cargo = new Cargo(nombre);
            cargo.alta();
        }
        
    }

    
}
