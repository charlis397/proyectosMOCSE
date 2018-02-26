/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


//import BD.baseDatos;
import Disenio.Login;
import Disenio.altaCuenta;
import Disenio.altaUsuario;
import Disenio.credito;
import Fuentes.Fuentes;
import bancocliente.PrBancoCliente;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;



/**
 *
 * @author Carlos
 */
public class controlAltaUsuario implements FocusListener,ActionListener,Fuentes,KeyListener{
 
    private final int ancho = 400;
    private final int altura = 300;
    private final String fondoPanel = "fondo_login.png";
    
    
    private altaUsuario alta;
    private Login login = new Login();
    private altaCuenta cuenta = new altaCuenta(fondoPanel,ancho,altura);
    //private static baseDatos con = new baseDatos();
    PrBancoCliente nuevousuario= new PrBancoCliente();
    
    
    public controlAltaUsuario(altaUsuario alta){
        this.alta = alta;
    }
    


        ImageIcon iconOK = new ImageIcon(getClass().getResource("/Imagenes/palomita.png"));
    
    @Override
    public void focusGained(FocusEvent fe) { 
        
        if(fe.getSource() == alta.getNombre()){
           alta.setNombre("");
           alta.setJLNombre(true);
           alta.getNombre().setForeground(Color.black);
           
        }
        if(fe.getSource() == alta.getAppPaterno()){
           alta.setAppPaterno("");
           alta.setJLAppPaterno(true);
           alta.getAppPaterno().setForeground(Color.black);
        }
        if(fe.getSource() == alta.getAppMaterno()){
           alta.setAppMaterno("");
           alta.setJLAppMaterno(true);
           alta.getAppMaterno().setForeground(Color.black);
        }
        if(fe.getSource() == alta.getDireccion()){
           alta.setDireccion("");
           alta.setJLDireccion(true);
           alta.getDireccion().setForeground(Color.black);
        }
        if(fe.getSource() == alta.getTelefono()){
           alta.setTelefono("");
           alta.setJLTelefono(true);
           alta.getTelefono().setForeground(Color.black);
        }
        if(fe.getSource() == alta.getEmail()){
           alta.setEmail("");
           alta.setJLEmail(true);
           alta.getEmail().setForeground(Color.black);
        }
        if(fe.getSource() == alta.getContrasenia()){
            alta.setContrasenia("");
            alta.setJLContrasenia(true);
            alta.getContrasenia().setForeground(Color.black);
        }
        
    }

    @Override
    public void focusLost(FocusEvent fe) {   }
    
        
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        String nombre = alta.getNombre().getText();
        String appPaterno = alta.getAppPaterno().getText();
        String appMaterno = alta.getAppMaterno().getText();
        String direccion  = alta.getDireccion().getText();
        String telefono = alta.getTelefono().getText();
        String email = alta.getEmail().getText();
        String contrasenia = String.valueOf(alta.getContrasenia().getPassword());
   
        if(ae.getSource() == alta.getJBRegresar()) {  
           login.setVisible(true);
           alta.dispose();
        }
        if(ae.getSource() == alta.getJBRegistrarse()){
            if(nombre.length() != 0  && appPaterno.length() !=0 && appMaterno.length() != 0 && direccion.length() != 0 && telefono.length() != 0 && email.length() != 0 && contrasenia.length() !=0){   
  
       
           String valido = nuevousuario.Registrarse(nombre, appPaterno, appMaterno, direccion, telefono, email, contrasenia);
  
          //boolean valido = con.DarAltaUsuario(nombre, appPaterno, appMaterno, direccion, telefono, email, contrasenia);
             
            if("no".equals(valido)){
                JOptionPane.showMessageDialog(null, "Error al Registrar al USUARIO", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            else{
                JOptionPane.showMessageDialog(null, "Usuario Registrado con EXITO", "Exito", JOptionPane.DEFAULT_OPTION, iconOK);
            
               String noCuenta = String.valueOf(Integer.parseInt(nuevousuario.getNoCuenta())+1);
               cuenta.setVisible(true);
               cuenta.setNoCuenta(noCuenta);
               cuenta.setCorreo(email);
               alta.setVisible(false);
              }
            }
        }      
        else{
             JOptionPane.showMessageDialog(null, "Ingrese datos por favor", "ERROR", JOptionPane.DEFAULT_OPTION);
         }
            
        }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        char c = ke.getKeyChar();
        if(ke.getSource() == alta.getNombre()){
            alta.getNombre().getText();
            if((c<'a' || c>'z') && (c<'A' || c>'Z') && (c<' ' || c>' ')){
            ke.consume();
            }
        }
        
        if(ke.getSource() == alta.getAppPaterno()){
            alta.getAppPaterno().getText();
            if((c<'a' || c>'z') && (c<'A' || c>'Z') && (c<' ' || c>' ')){
            ke.consume();

            }
        }
        if(ke.getSource() == alta.getAppMaterno()){
            alta.getAppMaterno().getText();
            if((c<'a' || c>'z') && (c<'A' || c>'Z') && (c<' ' || c>' ')){
            ke.consume();
 
            }
         }
        if(ke.getSource() == alta.getDireccion()){
            alta.getDireccion().getText();
            if((c<'a' || c>'z') && (c<'A' || c>'Z') && (c<' ' || c>' ') && (c<'.' || c>'.') && (c<'#' || c>'#')&& (c<'0' || c>'9')){
            ke.consume();
            
            }
         }
        if(ke.getSource() == alta.getTelefono()){
            alta.getTelefono().getText();
            if((c<'0' || c>'9')){
            ke.consume();

            }
         }
        if(ke.getSource() == alta.getEmail()){
            alta.getEmail().getText();
            if((c<'a' || c>'z') && (c<'A' || c>'Z') && (c<'0' || c>'9') && (c<'.' || c>'.') && (c<'@' || c>'@')&& (c<'-' || c>'-')&& (c<'_' || c>'_')){
            ke.consume();
           
            }
         }
            if(ke.getSource() == alta.getContrasenia()){
            String.valueOf(alta.getContrasenia().getPassword());
            if((c<'a' || c>'z') && (c<'A' || c>'Z') && (c<'0' || c>'9')){
            ke.consume();
           
            }
         }
        
        }
    @Override
    public void keyPressed(KeyEvent ke) {}
    @Override
    public void keyReleased(KeyEvent ke) {}

}

