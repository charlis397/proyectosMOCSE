/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Disenio;

import Controladores.controlAhorro;
import static Fuentes.Fuentes.Arial;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Carlos
 */
public class ahorro extends JPanel{
    
    private JLabel JLsaldo;
    private JTextField saldo;
    private JButton pagarCredito;
    private JButton transferir;
    private JLabel JLBienvenida;
    private JLabel JLusuario;
    private JLabel JLnumeroCuenta;
    private JTextField numeroCuenta;
    private JTextField correo;
    
    private controlAhorro controlador;
    
    private int ancho = 0;
    private int alto = 0;
    private String fondo ="";
    
    public ahorro(String fondo,int ancho,int alto){
        super();
        controlador = new controlAhorro(this);
        this.ancho = ancho;
        this.alto = alto;
        this.fondo = fondo;
        this.setBounds(140, 25, ancho, alto);
        this.setLayout(null); 
        inicializarComponentes(fondo);
        
    }
    
    private void inicializarComponentes(String imFondo){
   
        
        fondoLogin fondo = new fondoLogin(imFondo,alto,ancho);
        
        
        
        JLsaldo = new JLabel();
        saldo = new JTextField();
        pagarCredito = new JButton();
        transferir = new JButton();
        JLBienvenida = new JLabel();
        JLusuario = new JLabel();
        JLnumeroCuenta = new JLabel();
        numeroCuenta = new JTextField();
        correo = new JTextField();
        
        
        JLBienvenida.setText("¡Bienvenido! ");
        JLBienvenida.setBounds(100, 30, 150, 21);
        JLBienvenida.setFont(Arial);
        
      
        JLusuario.setBounds(210,30,200,21);
        JLusuario.setFont(Arial);
        
        JLnumeroCuenta.setText("Cuenta de Ahorro ");
        JLnumeroCuenta.setBounds(80,90,120,25);
        
        
        numeroCuenta.setBounds(220, 93, 90, 21);
        numeroCuenta.setEditable(false);
        numeroCuenta.setHorizontalAlignment(JTextField.CENTER);
        
        JLsaldo.setText("Su saldo es de: ");
        JLsaldo.setBounds(80, 140,150, 25);
        
        
        saldo.setBounds(220, 143, 90, 21);
        saldo.setEditable(false);
        saldo.setHorizontalAlignment(JTextField.CENTER);
        
        pagarCredito.setText("Pagar Credito");
        pagarCredito.setBounds(60, 241, 115, 21);
        pagarCredito.addActionListener(controlador);
        
        transferir.setText("Transferencia");
        transferir.setBounds(210, 241, 115, 21);
        transferir.addActionListener(controlador);
        
        this.add(JLsaldo);
        this.add(saldo);
        this.add(JLBienvenida);
        this.add(JLusuario);
        this.add(JLnumeroCuenta);
        this.add(numeroCuenta);
        this.add(pagarCredito);
        this.add(transferir);
        
        this.add( fondo , BorderLayout.CENTER); 
        fondo.repaint();
    }

    public JButton getPagarCredito() {
        return pagarCredito;
    }

    public JButton getTransferir() {
        return transferir;
    }
    
     public JLabel getJLUsuario(){
        return JLusuario;
    }
    
    public void setJLUsuario(String nombre){
        getJLUsuario().setText(nombre);
    }

    public JTextField getNumeroCuenta() {
        return numeroCuenta;
    }
    
    public void setNumeroCuenta(String noCuenta){
        getNumeroCuenta().setText(noCuenta);
    }

    public JTextField getSaldo() {
        return saldo;
    }
    
    public void setSaldo(String saldo){
        getSaldo().setText(saldo);
    }
    
    public JTextField getCorreo(){
        return correo;
    }
    
    public void setCorreo(String correo){
        getCorreo().setText(correo);
    }
    
    
}
