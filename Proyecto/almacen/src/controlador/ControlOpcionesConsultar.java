/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaOpcionesConsultar;

/**
 *
 * @author charlis
 */
public class ControlOpcionesConsultar implements ActionListener {
    
    private final VistaOpcionesConsultar vista;
    
    public ControlOpcionesConsultar(VistaOpcionesConsultar vista){
        this.vista = vista;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.getCargo()){
            System.out.println("JEJEJE");
        }
    }
    
}
