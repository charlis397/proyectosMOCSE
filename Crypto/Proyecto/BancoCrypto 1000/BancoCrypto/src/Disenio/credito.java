/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Disenio;

import Controladores.controlCredito;
import static Fuentes.Fuentes.*;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Carlos
 */
public class credito extends JPanel {
    
    private JTextField usuario;
    private JTextField numeroCuenta;
    private JTextField saldo;
    private JTextField anualidad;
    private JTextField interes;
    private JTextField fechaCorte;
    private JLabel JLBienvenida;
    private JLabel JLusuario;
    private JLabel JLescom;
    private JLabel JLnumeroCuenta;
    private JLabel JLsaldo;
    private JLabel JLanualidad;
    private JLabel JLinteres;
    private JLabel JLfechaCorte;
    private JButton nuevoCredito;
    
    private JTextField correo;
    
    private controlCredito controlador;
    
    
    private int ancho = 0;
    private int alto = 0;
    private String imagen = "";
    
    public credito(String imagen, int ancho, int alto){
        super();
        controlador = new controlCredito(this);
        this.ancho = ancho;
        this.alto = alto;
        this.imagen = imagen;
        
        this.setBounds(140, 25, ancho, alto);
        this.setLayout(null); 
        
        inicializarComponentes(imagen);
        
    }

    
    private void inicializarComponentes(String imFondo){
        
        //ImageIcon iconESCOM = new ImageIcon(getClass().getResource("/Imagenes/escom.png"));
        fondoLogin fondo = new fondoLogin(imFondo,ancho,alto); 
        
        
        usuario = new JTextField();
        numeroCuenta = new JTextField();
        saldo = new JTextField();
        anualidad = new JTextField();
        interes = new JTextField();
        fechaCorte = new JTextField();
        JLusuario = new JLabel();
        JLBienvenida = new JLabel();
        JLnumeroCuenta = new JLabel();
        JLsaldo = new JLabel();
        JLanualidad = new JLabel();
        JLinteres = new JLabel();
        JLfechaCorte = new JLabel();
        nuevoCredito = new JButton();
        correo = new JTextField();
        
        
        JLBienvenida.setText("¡Bienvenido! ");
        JLBienvenida.setBounds(100, 30, 150, 21);
        JLBienvenida.setFont(Arial);
        
      
        JLusuario.setBounds(210,30,200,21);
        JLusuario.setFont(Arial);
        
        JLnumeroCuenta.setText("Cuenta de Credito: ");
        JLnumeroCuenta.setBounds(80,90,120,25);
        
        numeroCuenta.setBounds(220, 93, 90, 21);
        numeroCuenta.setEditable(false);
        numeroCuenta.setHorizontalAlignment(JTextField.CENTER);
        
        JLsaldo.setText("Tienes un adeudo de: ");
        JLsaldo.setBounds(80, 140,150, 25);
        
        saldo.setBounds(220, 143, 90, 21);
        saldo.setEditable(false);
        saldo.setHorizontalAlignment(JTextField.CENTER);
        
        
        JLfechaCorte.setBounds(70, 310, 300, 25);
        
        nuevoCredito.setText("Solicitar Credito");
        nuevoCredito.setBounds(120, 220, 150, 21);
        nuevoCredito.addActionListener(controlador);
        
        
        this.add(JLBienvenida);
        this.add(JLusuario);
        this.add(JLnumeroCuenta);
        this.add(numeroCuenta);
        this.add(JLsaldo);
        this.add(JLfechaCorte);
        this.add(saldo);
        this.add(nuevoCredito);
        
        
        
        
        
        this.add( fondo , BorderLayout.CENTER); 
        fondo.repaint();
    }

    public JButton getNuevoCredito() {
        return nuevoCredito;
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

    public JLabel getJLfechaCorte() {
        return JLfechaCorte;
    }
    
    public void setJLfechaCorte(String fechaCorte){
        getJLfechaCorte().setText(fechaCorte);
    }
    
    public JTextField getCorreo(){
        return correo;
    }
    public void setCorreo(String correo){
        getCorreo().setText(correo);
    }
    
}
