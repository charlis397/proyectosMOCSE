/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

//import BD.baseDatos;
import Disenio.altaUsuario;
import Disenio.credito;
import Disenio.solicitarCredito;
import bancocliente.PrBancoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class controlCredito implements ActionListener {
    private final int ancho = 400;
    private final int altura = 300;
    private final String fondoPanel = "fondo_login.png";
    private credito credito;
    PrBancoCliente cliente=new PrBancoCliente();
   // private static baseDatos con = new baseDatos();
    solicitarCredito solicitar = new solicitarCredito(fondoPanel, ancho, ancho);
    
    public controlCredito(credito credito){
        this.credito = credito;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
         if(ae.getSource() == credito.getNuevoCredito()){
            if(ae.getSource() == credito.getNuevoCredito()){
                  
                  String correo = credito.getCorreo().getText();
                  String interes = cliente.getInteres(correo);
                  String adeudo = cliente.getAdeudoCredito(correo);
                  if(Float.parseFloat(adeudo)>0){
                  
                      solicitar.setVisible(true);
                      solicitar.setCorreo(correo);
                      solicitar.setInteres(interes);
                      solicitar.getAnualidad().setVisible(false);
                  }
                  else{
                      solicitar.setVisible(true);
                      solicitar.setCorreo(correo);
                      
                  }
            }
           
        }
    }
    }
    

