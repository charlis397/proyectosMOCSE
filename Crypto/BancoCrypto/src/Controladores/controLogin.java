/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Disenio.Login;
import Disenio.altaUsuario;
import Disenio.principal;
import bancocliente.PrBancoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class controLogin implements ActionListener{
    
    private final Login login;
    
    PrBancoCliente cliente= new PrBancoCliente();
    public controLogin(Login login){
         this.login = login;   
     }
    
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        String usuarios = login.getUsuario().getText();
        String contrasenia = String.valueOf(login.getContrasenia().getPassword());
        
        principal princ = new principal();
        altaUsuario alta = new altaUsuario();
         
        if(ae.getSource()== login.getJBIngresar()){
            
            System.out.println(usuarios+"   /**   " + contrasenia);
            String resu = cliente.inicioSesion(usuarios, contrasenia);
            System.out.println("\t esto sale \t" + resu);
           // boolean valido = con.inicioSesion(usuarios, contrasenia);
         

            if("no".equals(resu)){
                JOptionPane.showMessageDialog(null, "Usuario o Contraseña INCORECCTA", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            
            else{
                princ.setCorreo(usuarios);
                princ.setVisible(true);
                login.setVisible(false);
            }
            
        }
        
        if(ae.getSource()== login.getJBRegistrar()){
            alta.setVisible(true);
            login.dispose();
        }
    }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
   

        
        
        
            
}
