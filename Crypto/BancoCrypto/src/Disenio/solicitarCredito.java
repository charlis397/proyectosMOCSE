/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Disenio;



import Controladores.controlSolicitarCredito;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Carlos
 */
public class solicitarCredito extends JFrame {
    
    private controlSolicitarCredito controlador;
    private JLabel mensaje;
    private JLabel mensaje2;
    private JButton siguiente;
    private JComboBox adeudo;
    private JButton salir;
    private JTextField interes;
    private JTextField correo;
    private JLabel LabelInteres;
    private JComboBox anualidad;
    private int ancho = 0;
    private int alto = 0;
    private String imagen = "";
    
    public solicitarCredito(String imagen, int ancho, int alto){
     
        super();
        controlador = new controlSolicitarCredito(this);
        this.imagen = imagen;
        this.ancho = ancho;
        this.alto = alto;
        configurarVentana(ancho,alto);
        inicializarComponentes(imagen);
    
        
    }
    
     private void configurarVentana(int ancho,int altura){
              
        this.setTitle("Nuevo Credito");
        this.setSize(ancho, altura);
        this.setLocationRelativeTo(null);  
        this.setLayout(null);  
        this.setResizable(false); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void inicializarComponentes(String imFondo){
        fondoLogin fondo = new fondoLogin(imFondo,ancho,alto); 
        
        mensaje = new JLabel();
        salir = new JButton();
        siguiente = new JButton();
        adeudo = new JComboBox();
        mensaje2 = new JLabel();
        interes = new JTextField();
        correo = new JTextField();
        LabelInteres = new JLabel();
        anualidad = new JComboBox();
        
        mensaje.setText("El banco proporciona a sus clientes un credito de hasta $100,000");
        mensaje.setBounds(10,20,380,25);
        
        
        
        
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
        interes.setBounds(170, 150, 60 , 21);
        interes.setText("8");
        interes.setHorizontalAlignment(JTextField.CENTER);
        interes.setEditable(false);
        
        siguiente.setBounds(280, 220, 90, 25);
        siguiente.setText("Finalizar");
        siguiente.addActionListener(controlador);
        
        salir.setBounds(20, 220, 70, 25);
        salir.setText("Salir");
        salir.addActionListener(controlador);
        
        
        this.add(mensaje);
       
        this.add(siguiente);
        this.add(adeudo);
        this.add(anualidad);
        this.add(interes);
        this.add(LabelInteres);
        this.add(salir);
        
        
        
        this.add( fondo , BorderLayout.CENTER); 
        fondo.repaint();
    }

        public JLabel getMensaje() {
        return mensaje;
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
    
    public JButton getSalir(){
        return salir;
    }

    public void setCorreo(String correo) {
       getCorreo().setText(correo);
    }
}
