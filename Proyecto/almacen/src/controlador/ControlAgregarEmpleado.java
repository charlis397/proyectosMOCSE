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
import vista.VistaAgregarEmpleado;

/**
 *
 * @author charlis
 */
public class ControlAgregarEmpleado implements ActionListener,ItemListener{
    private final VistaAgregarEmpleado vista;
    private Empleado empleado;
    
    public ControlAgregarEmpleado(VistaAgregarEmpleado vista){
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
        if(e.getSource() == vista.getAgregar()){
            String nombre = vista.getNombre().getText();
            String codigo = vista.getCodigo().getText();
            String cadenaCargo = vista.getCargo().getSelectedItem().toString();
            
            Cargo cargo = new Cargo(cadenaCargo);
            
            empleado = new Empleado(nombre, codigo, cargo);
            empleado.alta();
        }
        
        if(e.getSource() == vista.getSalir()){
            vista.dispose();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        
        if(e.getSource() == vista.getCargo()){
            
        }
        
        
    }
    
}
