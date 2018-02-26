/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Disenio;

import Controladores.controlAltaUsuario;
import Controladores.controlPrincipal;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Carlos
 */
public class principal extends JFrame  {
    
    private controlPrincipal controlador;
    private JButton buttonCredito;
    private JButton buttonAhorro;
    private JButton buttonSalir;
    private JLabel escom;
    private String correo="";
    
    private final int ancho = 500;
    private final int altura = 400;

    public principal(){
        super();
        controlador = new controlPrincipal(this);
        configurarVentana(ancho,altura);
        inicializarComponentes("fondo_login.png","fondo_login.png");
    }
    
    private void configurarVentana(int ancho,int altura){
          
        this.setTitle("Menu Principal");
        this.setSize(ancho, altura);
        this.setLocationRelativeTo(null);  
        this.setLayout(null);  
        this.setResizable(false); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void inicializarComponentes(String imFondo, String fondoPanel) {
       
        fondoLogin p = new fondoLogin(imFondo,ancho,altura);
       // credito credito = new credito(fondoPanel,ancho,altura);
       // bienvenido bienvenido = new bienvenido(fondoPanel,ancho,altura);
        ImageIcon iconESCOM = new ImageIcon(getClass().getResource("/Imagenes/escom.png"));
        buttonAhorro = new JButton();
        buttonCredito = new JButton();
        buttonSalir = new JButton();
        escom = new JLabel();
        
        escom.setIcon(iconESCOM);
        escom.setBounds(200,50,300,250);
        buttonAhorro.setText("AHORRO");
        buttonAhorro.setBounds(20, 40, 100, 25);
        buttonAhorro.addActionListener(controlador);
        
        buttonCredito.setText("CREDITO");
        buttonCredito.setBounds(20, 80, 100, 25);
        buttonCredito.addActionListener(controlador);
        
        buttonSalir.setText("SALIR");
        buttonSalir.setBounds(20, 320, 100, 25);
        buttonSalir.addActionListener(controlador);
        
        
        this.add(buttonSalir);
        this.add(buttonAhorro);
        this.add(buttonCredito);
        this.add(escom);
       // this.add( credito , BorderLayout.CENTER); 
        this.add( p , BorderLayout.CENTER); 
       // this.add(bienvenido, BorderLayout.CENTER);
        
       // credito.repaint();
        p.repaint();
        //bienvenido.repaint();
    }
    
    public JButton getJBAhorro(){
        return buttonAhorro;
    }
    public JButton getJBCredito(){
        return buttonCredito;
    }
    public JButton getJBSalir(){
        return buttonSalir;
    }
    
    public void setCorreo( String correo ){
         this.correo = correo;
    }
    
    public String getCorreo(){
        return correo;
    }
    
   
    
    
}
    

