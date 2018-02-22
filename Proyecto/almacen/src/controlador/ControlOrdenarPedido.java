/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import modelo.Empleado;
import modelo.OrdenCompra;
import modelo.Pedido;
import modelo.Producto;
import modelo.Proveedor;
import vista.VistaConsultaProductos;
import vista.VistaOrdenarPedido;

/**
 *
 * @author charlis
 */
public class ControlOrdenarPedido implements ActionListener{
    private final VistaOrdenarPedido vista;

    
    public ControlOrdenarPedido(VistaOrdenarPedido vista){
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.getNuevoPedido()){
            VistaConsultaProductos productos = new VistaConsultaProductos();
            productos.setVisible(true);
            productos.setVistaPedido(vista);
        }
        
        if(e.getSource() == vista.getFinalizarPedido()){
            String codigo = JOptionPane.showInputDialog(null, "Ingrese su codigo de accesso", "Identificacion", JOptionPane.PLAIN_MESSAGE);
            Empleado empleado = new Empleado(codigo);
            System.out.println(" estado " + empleado.consultaExistencia());
            if(empleado.consultaExistencia() == false){
                JOptionPane.showMessageDialog(null, "Código Incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if(empleado.consultaExistencia() == true){
            
                int rows = vista.getTablaPedido().getRowCount();

                Calendar fecha = new GregorianCalendar();

                int anio = fecha.get(Calendar.YEAR);
                int mes = fecha.get(Calendar.MONTH) + 1;
                int dia = fecha.get(Calendar.DAY_OF_MONTH);

                String fechaPedido = anio + "-" + mes + "-" + dia;

                System.out.println("" + anio + mes + dia);

                String nombreProducto;
                String nombreProveedor;
                int cantidad = 0;
                float total = Float.parseFloat(vista.getTotal().getText());


                Pedido pedido = new Pedido(fechaPedido, empleado, "","", total);
                pedido.alta();



                for (int i = 0; i < rows; i++) {
                    nombreProducto = String.valueOf(vista.getModelo().getValueAt(i,0));
                    nombreProveedor = String.valueOf(vista.getModelo().getValueAt(i,1));
                    cantidad = Integer.parseInt((vista.getModelo().getValueAt(i,3)).toString());
                    total += Float.parseFloat((vista.getModelo().getValueAt(i,4)).toString());


                    Producto producto = new Producto(nombreProducto);
                    Proveedor proveedor = new Proveedor(nombreProveedor);

                    OrdenCompra compra = new OrdenCompra(producto, proveedor, cantidad, pedido);
                    System.out.println("+ " + pedido);
                    compra.alta();

                    System.out.println(producto + "     " + nombreProveedor +"     " + cantidad + "          " + total);
                
                }
            }

        }
          
        
    }

    
    

}
