/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.VistaConsultaProductos;

/**
 *
 * @author charlis
 */
public class ControlConsultaProductos implements ActionListener{
    
    private final VistaConsultaProductos vista;
    private float subtotal = 0;
    private float total = 0;
    
    public ControlConsultaProductos(VistaConsultaProductos vista){
        this.vista = vista;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == vista.getAgregar()){
            
            
            int cantidad =Integer.parseInt(JOptionPane.showInputDialog(null, "Cantidad de productos", "Ingrese un entero", JOptionPane.PLAIN_MESSAGE));
            Object [] fila = new Object[6];
           
            fila[0] = vista.getTableModel().getValueAt(vista.getTabla().getSelectedRow(), 0);
            fila[1] = vista.getTableModel().getValueAt(vista.getTabla().getSelectedRow(), 1);
            fila[2] = vista.getTableModel().getValueAt(vista.getTabla().getSelectedRow(), 2);
            fila[3] = cantidad;
            subtotal = Float.parseFloat(fila[2].toString()) * cantidad;
            
            fila[4] = ""+subtotal;
            
            total = subtotal + total;

            vista.getVistaPedido().getModelo().addRow(fila);
            vista.getVistaPedido().getFinalizarPedido().setEnabled(true);
            vista.getVistaPedido().getTotal().setText(String.valueOf(total));

        
            
        }
        
    }

    
    
}
