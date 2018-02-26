/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Disenio;

import Controladores.controlAltaAhorro;
import Controladores.controlAltaCredito;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Carlos
 */
public class altaAhorro extends JFrame {
    
    private controlAltaAhorro controlador;
    private JLabel mensaje;
    private JLabel mensaje2;
    private JLabel mensaje3;
    private JTextField saldo;
    private JTextField noCuenta;
    private JButton siguiente;
    
    private int ancho = 0;
    private int alto = 0;
    private String imagen = "";
    
    public altaAhorro(String imagen, int ancho, int alto){
  
        super();
        controlador = new controlAltaAhorro(this);
        this.imagen = imagen;
        this.ancho = ancho;
        this.alto = alto;
        configurarVentana(ancho,alto);
        inicializarComponentes(imagen);
    
        
    }
    
     private void configurarVentana(int ancho,int altura){
              
        this.setTitle("Alta Ahorro");
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
        mensaje3 = new JLabel();
        saldo = new JTextField();
        noCuenta = new JTextField();
        siguiente = new JButton();
        
        
        mensaje.setText("El banco proporciona a sus clientes una cuenta de ahorro");
        mensaje.setBounds(30,15,380,25);
        mensaje.setForeground(Color.WHITE);
        mensaje2.setText(" Todas las cuentas de ahorro inician con   $0" );
        mensaje2.setBounds(60,35,380,25);
        mensaje2.setForeground(Color.WHITE);
        mensaje3.setText("Su saldo es de: $");
        mensaje3.setForeground(Color.WHITE);
        
        saldo.setText("0.00");
        saldo.setBounds(240, 110, 50, 21);
        saldo.setEditable(false);
        saldo.setHorizontalAlignment(JTextField.CENTER);
        
        siguiente.setBounds(230, 220, 100, 25);
        siguiente.setText("Finalizar");
        siguiente.addActionListener(controlador);
        
        
        this.add(mensaje);
        this.add(mensaje2);
        this.add(mensaje3);
        this.add(saldo);
        this.add(siguiente);

        
        
        
        
        this.add( fondo , BorderLayout.CENTER); 
        fondo.repaint();
    }

    public JButton getSiguiente() {
        return siguiente;
    }
    
    public JTextField getSaldo() {
        return saldo;
    }
    


    public JTextField getNoCuenta() {
        return noCuenta;
    }
    
    public void setNoCuenta(String idUsuario){
        getNoCuenta().setText(idUsuario);
    }
    
    
    
    
}
