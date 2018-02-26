/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

//import BD.baseDatos;
import Disenio.Login;
import Disenio.altaAhorro;
import Disenio.altaCredito;
import bancocliente.PrBancoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class controlAltaAhorro implements ActionListener{

     
    
    private altaAhorro ahorro;
    private Login login = new Login();
    
  //  private static baseDatos con = new baseDatos();
    private PrBancoCliente cliente= new PrBancoCliente();
    
     public controlAltaAhorro(altaAhorro alta){
        this.ahorro = alta;
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource() == ahorro.getSiguiente()){
            String noCuenta= cliente.getNoCuenta();
            String saldo = ahorro.getSaldo().getText();
            System.out.println(saldo+"    "+noCuenta);
            String valido= cliente.altaAhorro(saldo, noCuenta);
            
             if("no".equals(valido)){
                JOptionPane.showMessageDialog(null, "Error al Registrar su cuenta de ahorro", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            else{
                  JOptionPane.showMessageDialog(null, "Succesfully", "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
                  login.setVisible(true);
                  ahorro.dispose();
         
                 }
        }
        }
      
    
}
    

