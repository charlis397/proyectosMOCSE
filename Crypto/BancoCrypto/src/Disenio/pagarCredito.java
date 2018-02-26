/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Disenio;

import Controladores.controlPagarCredito;
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
public class pagarCredito extends JFrame {
    
    private JLabel JLcuentaTransferir;
        private JTextField cuentaTransferir;
        private JButton depositar;
        private JLabel JLmonto;
        private JTextField monto;
        private JTextField fecha_hora;
        private JLabel JLconcepto;
        private JTextField concepto;
        private JTextField correo;
        private JButton salir;
        private controlPagarCredito controlador;
        private int ancho = 0;
        private int alto = 0;
        private String imagen = "";
    
        public pagarCredito(String imagen, int ancho, int alto){
     
         super();
        controlador = new controlPagarCredito(this);
        this.imagen = imagen;
        this.ancho = ancho;
        this.alto = alto;
        configurarVentana(ancho,alto);
        inicializarComponentes(imagen);
        }
        
    private void configurarVentana(int ancho,int altura){
              
        this.setTitle("Pagar Credito");
        this.setSize(ancho, altura);
        this.setLocationRelativeTo(null);  
        this.setLayout(null);  
        this.setResizable(false); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void inicializarComponentes(String imFondo){
        fondoLogin fondo = new fondoLogin(imFondo,ancho,alto); 
        
        JLcuentaTransferir = new JLabel();
        JLmonto = new JLabel();
        JLconcepto = new JLabel();
        cuentaTransferir = new JTextField();
        monto = new JTextField ();
        fecha_hora = new JTextField();
        concepto = new JTextField();
        depositar = new JButton();
        correo = new JTextField();
        salir = new JButton();
        
        
        JLcuentaTransferir.setText("Número de Cuenta: ");
        JLcuentaTransferir.setBounds(40, 20, 120, 25);
        cuentaTransferir.setBounds(160, 23, 100, 21);
        cuentaTransferir.setHorizontalAlignment(JTextField.CENTER);
        cuentaTransferir.setEditable(false);
        JLmonto.setText("Monto:           $");
        JLmonto.setBounds(40, 60, 180, 21);
        monto.setBounds(160, 60, 50, 21);
        monto.setHorizontalAlignment(JTextField.CENTER);
        monto.addKeyListener(controlador);
        depositar.setText("Depositar");
        depositar.setBounds(140, 110, 100, 22);
        depositar.addActionListener(controlador);
        
        salir.setText("Salir");
        salir.setBounds(30, 110, 70, 22);
        salir.addActionListener(controlador);
        
        this.add(JLcuentaTransferir);
        this.add(cuentaTransferir);
        this.add(JLmonto);
        this.add(monto);
        this.add(depositar);
        this.add(salir);
        
        this.add( fondo , BorderLayout.CENTER); 
        fondo.repaint();
}

    public JTextField getCuentaTransferir() {
        return cuentaTransferir;
    }
    
    public void setCuentaTransferir(String credito){
        getCuentaTransferir().setText(credito);
    }
    
    public JTextField getCorreo(){
        return correo;
    }
    
    public JTextField getMonto(){
        return monto;
    }
    
    public void setCorreo(String correo){
        getCorreo().setText(correo);
    }

    public JButton getDepositar() {
        return depositar;
    }

    public JButton getSalir() {
        return salir;
    }
    
    public JTextField getFechaHora(){
        return fecha_hora;
    }
    
    public void setFechaHora(String fecha_hora){
        getFechaHora().setText(fecha_hora);
    }
    
}
