/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Disenio;

import Controladores.controlAltaCuenta;
import Controladores.controlAltaUsuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Calendar;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Carlos
 */
public class altaCuenta extends JFrame {
    
    private JLabel mensaje;
    private JLabel mensaje2;
    private JLabel JLNIP;
    private JLabel JLNoCuenta;
    private JTextField NIP;
    private JTextField NoCuenta;
    private JTextField correo;
    private JButton siguiente;
    
    
    
    Random rng = new Random();
    
    private int noNIP = rng.nextInt(9000) + 1000;
    private controlAltaCuenta controlador;
    
    
    
    private int ancho = 0;
    private int alto = 0;
    private String imagen = "";
    
    public altaCuenta(String imagen, int ancho, int alto){
     
        super();
        controlador = new controlAltaCuenta(this);
        this.imagen = imagen;
        this.ancho = ancho;
        this.alto = alto;
        configurarVentana(ancho,alto);
        inicializarComponentes(imagen);
    
        
    }
    
     private void configurarVentana(int ancho,int altura){
              
        this.setTitle("Alta Cuenta");
        this.setSize(ancho, altura);
        this.setLocationRelativeTo(null);  
        this.setLayout(null);  
        this.setResizable(false); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void inicializarComponentes(String imFondo){
        
        fondoLogin fondo = new fondoLogin(imFondo,ancho,alto); 
        
        mensaje = new JLabel();
        mensaje2 = new JLabel();
        JLNIP = new JLabel();
        JLNoCuenta = new JLabel();
        NIP = new JTextField();
        NoCuenta = new JTextField();
        correo = new JTextField();
        siguiente = new JButton();
        
        mensaje.setText("Bienvenido se ha registrado satisfactoriamente");
        mensaje.setBounds(60,10,380,75);
        mensaje.setForeground(Color.WHITE);
        mensaje2.setText(" A continuación le mostramos los datos de su nueva cuenta" );
        mensaje2.setBounds(30,30,380,75);
        mensaje2.setForeground(Color.WHITE);
        
        NIP.setBounds(85, 110, 70, 21);
        NIP.setText(String.valueOf(noNIP));
        NIP.setHorizontalAlignment(JTextField.CENTER);
        NIP.setEditable(false);
        
        JLNIP.setText("NIP");
        JLNIP.setBounds(110, 140, 120, 25);
        
        NoCuenta.setBounds(185, 110, 120, 21);
        NoCuenta.setHorizontalAlignment(JTextField.CENTER);
        NoCuenta.setEditable(false);
        
        JLNoCuenta.setText("Número de Cuenta");
        JLNoCuenta.setBounds(190, 140, 120, 25);
        
        siguiente.setBounds(280, 220, 50, 25);
        siguiente.setText(">>");
        siguiente.addActionListener(controlador);
        
       System.out.println(noNIP);
        
        this.add(mensaje);
        this.add(mensaje2);
        this.add(JLNIP);
        this.add(JLNoCuenta);
        this.add(NIP);
        this.add(NoCuenta);
        this.add(siguiente);
        
        
        this.add( fondo , BorderLayout.CENTER); 
        fondo.repaint();
    }

    public JLabel getJLNIP() {
        return JLNIP;
    }

    public JTextField getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        getNIP().setText(NIP);
    }

    public JTextField getNoCuenta() {
        return NoCuenta;
    }

    public void setNoCuenta(String NoCuenta) {
       getNoCuenta().setText(NoCuenta);
    }

    public JButton getSiguiente() {
        return siguiente;
    }
    public JTextField getCorreo(){
        return correo;
    }
    public void setCorreo(String email){
        getCorreo().setText(email);
    }

    
    
}
