/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

//import BD.baseDatos;
import Disenio.altaCredito;
import Disenio.transferencia;
import bancocliente.PrBancoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import javax.swing.Action;
import javax.swing.JOptionPane;
import org.omg.CORBA.TRANSACTION_MODE;

/**
 *
 * @author Carlos
 */
public class controladorTransferencia implements ActionListener,KeyListener {

    //private static baseDatos con = new baseDatos();
    PrBancoCliente cliente=new PrBancoCliente();
    private transferencia transferencia;
     Calendar fecha = Calendar.getInstance();
    
    public controladorTransferencia(transferencia transferencia){
        this.transferencia = transferencia;
    }
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource() == transferencia.getTransferir()){
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String correo = transferencia.getCorreo().getText();
        String fecha_hora = anio + "-" + mes + "-" + dia + " " + hora + ":" + minuto + ":" + segundo ;
        String monto = transferencia.getMonto().getText();
        String id_Ahorro = transferencia.getCuentaTransferir().getText();
        String concepto = cliente.getNombreUsuarioID(id_Ahorro);
        String saldoActual = cliente.getSaldoAhorro(correo);
         if(Float.parseFloat(monto) >= 0){
        if(Float.parseFloat(saldoActual) >= Float.parseFloat(monto)){
           
        String emisor = cliente.getNoAhorro(correo);
        
        
         
        String valido = cliente.setTransferencia(emisor, monto, fecha_hora, concepto, id_Ahorro);
        
        if("si".equals(valido) ){
            String saldoActualReceptor = cliente.getSaldoIDAhorro(id_Ahorro);
            String montoUpdateReceptor = String.valueOf(Float.parseFloat(saldoActualReceptor) + Float.parseFloat(monto));
            JOptionPane.showMessageDialog(null, "Se realizo una transaccion de $" + monto + " a la cuenta  " + id_Ahorro , "Exito", JOptionPane.DEFAULT_OPTION);
            cliente.updateAhorro(id_Ahorro, montoUpdateReceptor);
            String montoUpdateEmisor = String.valueOf(Float.parseFloat(saldoActual) - Float.parseFloat(monto)); 
            cliente.updateAhorro(emisor, montoUpdateEmisor);
            transferencia.dispose();
           
        }
        else{
            JOptionPane.showMessageDialog(null, "Fallo en la operación", "Error", JOptionPane.DEFAULT_OPTION);
        }
        }
            else{
            JOptionPane.showMessageDialog(null, "No tienes suficientes fondos", "Error", JOptionPane.DEFAULT_OPTION);
        }
        }
         JOptionPane.showMessageDialog(null, "Ingrese cantidades positivas", "Error", JOptionPane.DEFAULT_OPTION);
        }

        if(ae.getSource() == transferencia.getSalir()){
            transferencia.dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        char c = ke.getKeyChar();
       if(ke.getSource() == transferencia.getMonto()){
            transferencia.getMonto().getText();
           if((c<'0' || c>'9')){
            ke.consume();
            }
         }
    }

    @Override
    public void keyPressed(KeyEvent ke) { }

    @Override
    public void keyReleased(KeyEvent ke) {}
    
}
