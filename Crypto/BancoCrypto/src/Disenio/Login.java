package Disenio;


import Controladores.controLogin;
import Controladores.controlAltaUsuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 *
 * @author Carlos
 */
public class Login extends JFrame {

    public JTextField usuario;
    public JPasswordField contrasenia;
    private JLabel LabelUsuario;
    private JLabel LabelContrasenia;    
    private JLabel registrarse;
    private JButton btnRegistrarse;
    private JButton btnIngresar;
    private JLabel ipn;

    private final int ancho = 400;
    private final int alto = 200;
    
    private final controLogin controlador;

    public Login(){
        super();
        controlador = new controLogin(this);
        configurarVentana(ancho,alto);
        inicializarComponentes("fondo_login.jpg");
    }
    


    private void configurarVentana(int ancho,int altura){

        this.setTitle("Iniciar Sesion");
        this.setSize(ancho, altura);
        this.setLocationRelativeTo(null);  
        this.setLayout(null);  
        this.setResizable(false); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
    }
    
    private void inicializarComponentes(String imFondo){
        
        fondoLogin fondo = new fondoLogin(imFondo,ancho,alto); 
 
        ImageIcon iconIPN = new ImageIcon(getClass().getResource("/Imagenes/logo.png"));
  
        LabelUsuario = new JLabel();
        LabelContrasenia = new JLabel();
        usuario = new JTextField();
        contrasenia = new JPasswordField();
        btnIngresar = new JButton();
        ipn = new JLabel();
        btnRegistrarse = new JButton();
        registrarse = new JLabel();
       

        
        LabelUsuario.setText("Usuario");
        LabelUsuario.setForeground(Color.white);
        LabelUsuario.setBounds(50, 28, 100, 25);
        
        LabelContrasenia.setText("Contraseña");
        LabelContrasenia.setForeground(Color.white);
        LabelContrasenia.setBounds(29, 60, 100, 25);
        
        registrarse.setText("¿Aún no estas registrado?");
        registrarse.setBounds(110,90,150,21);
        registrarse.setForeground(Color.white);
        registrarse.setEnabled(true);
        
        usuario.setBounds(110, 30, 150, 21);
        contrasenia.setBounds(110, 62, 150, 21);
        
        
        btnIngresar.setText("Login");
        btnIngresar.setBounds(145, 98, 70, 25);
        btnIngresar.addActionListener(controlador);
        
        ipn.setIcon(iconIPN);
        ipn.setBounds(280, 15, 500, 100);
        
        
     
        btnRegistrarse.setText("¿Aún no estas registrado?");
        btnRegistrarse.setBounds(85,140,190,21);
        btnRegistrarse.addActionListener(controlador);
        
        
        this.add(LabelUsuario);
        this.add(LabelContrasenia);
        this.add(usuario);
        this.add(contrasenia);
        this.add(btnIngresar);
        //this.add(registrarse);
        this.add(ipn);
        this.add(btnRegistrarse);
        
        
       this.add( fondo , BorderLayout.CENTER); 
       fondo.repaint();
        
    }
    
    
    public JTextField getUsuario(){
        return usuario;
    }
    public JPasswordField getContrasenia(){
        return contrasenia;
    }
    public JButton getJBIngresar(){
        return btnIngresar;
    }
    public JButton getJBRegistrar(){
        return btnRegistrarse;
    }

    
}