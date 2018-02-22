/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaOpcionesActualizar;

/**
 *
 * @author charlis
 */
public class ControlOpcionesActualizar implements ActionListener {
    
    private final VistaOpcionesActualizar vista;
    
    public ControlOpcionesActualizar(VistaOpcionesActualizar vista){
        this.vista = vista;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.getCargo()){
            System.out.println("JEJEJE");
        }
    }
    
}
