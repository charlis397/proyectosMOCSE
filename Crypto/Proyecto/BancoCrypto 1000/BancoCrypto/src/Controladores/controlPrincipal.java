/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

//import BD.baseDatos;
import Disenio.Login;
import Disenio.ahorro;
import Disenio.credito;
import Disenio.principal;
import bancocliente.PrBancoCliente;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Carlos
 */
public class controlPrincipal implements ActionListener {
    
    private principal menu;
    private credito credi;
   
    
    private final int ancho = 500;
    private final int altura = 400;
    private final String fondoPanel = "fondo_login.png";
    private PrBancoCliente cliente = new PrBancoCliente();
   // private static baseDatos con = new baseDatos();
    private Login login = new Login();
    
    
    credito credito = new credito(fondoPanel,ancho,altura);
    ahorro ahorro = new ahorro(fondoPanel,altura,ancho);
    
    public controlPrincipal(principal menu){
        this.menu = menu;  
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
         String correo = menu.getCorreo();
         String nombre = "";
         String noCuenta = "";
         String saldo ="";
         String adeudo="";
         String fecha_corte = "";
         if(ae.getSource() == menu.getJBCredito()){
             
             
             menu.add( credito , BorderLayout.CENTER);
             credito.repaint();
             nombre = cliente.getNombre(correo);//con.getNombre(correo);
             noCuenta = cliente.getNoCredito(correo);//con.getNoCredito(correo);
             adeudo = cliente.getAdeudoCredito(correo);//con.getAdeudoCredito(correo);
             fecha_corte = cliente.getFechaCorte(correo);//con.getFechaCorte(correo);
             credito.setJLUsuario(nombre);
             credito.setNumeroCuenta(noCuenta);
             credito.setSaldo(adeudo);
             credito.setJLfechaCorte("Tu fecha de corte son los días  " +fecha_corte+ "  de cada mes");
             credito.setCorreo(correo);
             
             credito.getNuevoCredito().setVisible(true);
             ahorro.getTransferir().setVisible(false);
             ahorro.getPagarCredito().setVisible(false);
             
         }
         
         
        if(ae.getSource() == menu.getJBAhorro()){
             menu.add( ahorro , BorderLayout.CENTER);
             ahorro.repaint();
             nombre = cliente.getNombre(correo);//con.getNombre(correo);
             noCuenta = cliente.getNoAhorro(correo);//con.getNoAhorro(correo);
             saldo = cliente.getSaldoAhorro(correo);//con.getSaldoAhorro(correo);
             ahorro.setJLUsuario(nombre);
             ahorro.setNumeroCuenta(noCuenta);
             ahorro.setSaldo(saldo);
             ahorro.setCorreo(correo);
             ahorro.getTransferir().setVisible(true);
             ahorro.getPagarCredito().setVisible(true);
             credito.getNuevoCredito().setVisible(false);
             
             
         }
         
        if(ae.getSource() == menu.getJBSalir()){
             login.setVisible(true);
             menu.dispose();
         }
             
    }
    
    
    
}
