/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import vista.VistaAdministrador;
import vista.VistaOpcionesActualizar;
import vista.VistaOpcionesAgregar;
import vista.VistaOpcionesConsultar;
import vista.VistaOpcionesEliminar;
import vista.VistaOrdenarPedido;
import vista.VistaPedidos;

/**
 *
 * @author charlis
 */
public class ControlAdministrador implements ActionListener {
    
    private final VistaAdministrador vista;

    public ControlAdministrador(VistaAdministrador vista) {
        this.vista = vista;
    }
   
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.getAgregar()){
            VistaOpcionesAgregar opciones = new VistaOpcionesAgregar();
            opciones.setVisible(true);
        }
        
        if(e.getSource() == vista.getActualizar()){
            VistaOpcionesActualizar opciones = new VistaOpcionesActualizar();
            opciones.setVisible(true);
        }
        
        if(e.getSource() == vista.getConsultar()){
            VistaPedidos vistaPedidos = new VistaPedidos();
            vistaPedidos.setVisible(true);
        }
        
        if(e.getSource() == vista.getEliminar()){
            VistaOpcionesEliminar opciones = new VistaOpcionesEliminar();
            opciones.setVisible(true);
        }
        
        if(e.getSource() == vista.getOrdenar()){
            VistaOrdenarPedido opciones = new VistaOrdenarPedido();
            opciones.setVisible(true);
        }
        
        
    }
    
}
