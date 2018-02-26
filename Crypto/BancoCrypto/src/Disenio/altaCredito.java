/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Disenio;

import Controladores.controlAltaCredito;
import Controladores.controlAltaCuenta;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Carlos
 */
public class altaCredito extends JFrame {
    
    private controlAltaCredito controlador;
    private JLabel mensaje;
    private JLabel mensaje2;
    private JButton siguiente;
    private JComboBox adeudo;
    private JComboBox anualidad;
    private JTextField interes;
    private JTextField correo;
    private JLabel LabelInteres;
    
    private int ancho = 0;
    private int alto = 0;
    private String imagen = "";
    
    public altaCredito(String imagen, int ancho, int alto){
     
        super();
        controlador = new controlAltaCredito(this);
        this.imagen = imagen;
        this.ancho = ancho;
        this.alto = alto;
        configurarVentana(ancho,alto);
        inicializarComponentes(imagen);
    
        
    }
    
     private void configurarVentana(int ancho,int altura){
              
        this.setTitle("Alta Credito");
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
        siguiente = new JButton();
        adeudo = new JComboBox();
        anualidad = new JComboBox();
        interes = new JTextField();
        correo = new JTextField();
        LabelInteres = new JLabel();
        
        mensaje.setText("El banco proporciona a sus clientes un credito de hasta $100,000");
        mensaje.setBounds(10,5,380,25);
        mensaje.setForeground(Color.WHITE);
        mensaje2.setText(" Si no desea contraer un adeudo con el banco seleccione   $0" );
        mensaje2.setBounds(20,25,380,25);
        mensaje2.setForeground(Color.WHITE);
        adeudo.addItem("$0.00 MXN");
        adeudo.addItem("$5,000.00 MXN");
        adeudo.addItem("$10,000.00 MXN");
        adeudo.addItem("$25,000.00 MXN");
        adeudo.addItem("$50,000.00 MXN");
        adeudo.addItem("$100,000.00 MXN");
        adeudo.addItemListener(controlador);
        adeudo.setBounds(60, 80, 130, 25);
        
        
        anualidad.addItem("6 meses");
        anualidad.addItem("12 meses");
        anualidad.addItem("18 meses");
        anualidad.addItem("24 meses");
        anualidad.addItem("48 meses");
        anualidad.addItem("0 meses");
        anualidad.addItemListener(controlador);
        anualidad.setBounds(230, 80, 130, 25);
        
        LabelInteres.setText("% interes");
        LabelInteres.setBounds(170, 170, 70, 25);
        LabelInteres.setForeground(Color.WHITE);
        interes.setBounds(170, 150, 60 , 21);
        interes.setText("8");
        interes.setHorizontalAlignment(JTextField.CENTER);
        interes.setEditable(false);
        
        siguiente.setBounds(280, 220, 50, 25);
        siguiente.setText(">>");
        siguiente.addActionListener(controlador);
        
        
        this.add(mensaje);
        this.add(mensaje2);
        this.add(siguiente);
        this.add(adeudo);
        this.add(anualidad);
        this.add(interes);
        this.add(LabelInteres);
        
        
        
        this.add( fondo , BorderLayout.CENTER); 
        fondo.repaint();
    }

    public JLabel getMensaje() {
        return mensaje;
    }

    public JLabel getMensaje2() {
        return mensaje2;
    }

    public JButton getSiguiente() {
        return siguiente;
    }
    
    public JComboBox getAdeudo(){
        return adeudo;
    }
    
    public JComboBox getAnualidad(){
        return anualidad;
    }
    public JTextField getInteres(){
        return interes;
    }
    public void setInteres(String Interes){
        getInteres().setText(Interes);
    }
    public JTextField getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
       getCorreo().setText(correo);
    }
}
