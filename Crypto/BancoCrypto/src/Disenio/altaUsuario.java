
package Disenio;

//import BD.baseDatos;
import Controladores.controlAltaUsuario;
import Fuentes.Fuentes;
import static Fuentes.Fuentes.titulo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Carlos
 */
public class altaUsuario extends JFrame  {  //implements FocusListener,ActionListener,Fuentes
        
    private JTextField nombre;
    private JTextField appPaterno;
    private JTextField appMaterno;
    private JTextField direccion;
    private JTextField telefono;
    private JTextField email;
    private JTextField aux;
    private JPasswordField contrasenia;
    private JButton regresar;
    private JButton registrarse;
    //private JButton siguiente;
    private JLabel nuevoUsuario;
    private JLabel Labelnombre;
    private JLabel LabelappPaterno;
    private JLabel LabelappMaterno;
    private JLabel Labeldireccion;
    private JLabel Labeltelefono;
    private JLabel Labelemail;
    private JLabel Labelcontrasenia;
    
            
    private final int ancho = 400;
    private final int altura = 380;
    
    private controlAltaUsuario controlador;

    
    public altaUsuario(){
        super();
        controlador = new controlAltaUsuario(this);
        configurarVentana(ancho,altura);
        inicializarComponentes("fondo_login.png");
    }
    
      private void configurarVentana(int ancho,int altura){
              
        this.setTitle("Alta de Usuario");
        this.setSize(ancho, altura);
        this.setLocationRelativeTo(null);  
        this.setLayout(null);  
        this.setResizable(false); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void inicializarComponentes(String imFondo) {
        
        fondoLogin fondo = new fondoLogin(imFondo,ancho,altura); 
        
        ImageIcon iconRegresar = new ImageIcon(getClass().getResource("/Imagenes/regresar.jpg"));
        
        nombre = new JTextField();
        appPaterno = new JTextField();
        appMaterno = new JTextField();
        direccion = new JTextField();
        telefono = new JTextField();
        email = new JTextField();
        aux = new JTextField();
        contrasenia = new JPasswordField();
        registrarse = new JButton();
        regresar = new JButton();
        //siguiente = new JButton();
        nuevoUsuario = new JLabel();
        Labelnombre = new JLabel();
        LabelappPaterno = new JLabel();
        LabelappMaterno = new JLabel();
        Labeldireccion = new JLabel();
        Labeltelefono = new JLabel();
        Labelemail = new JLabel();
        Labelcontrasenia = new JLabel();
        
        
        
        
        nuevoUsuario.setText("Nuevo Usuario");
        nuevoUsuario.setHorizontalAlignment(JLabel.CENTER);
        nuevoUsuario.setFont(titulo);
        nuevoUsuario.setForeground(Color.YELLOW);
        nuevoUsuario.setBounds(75, 5, 250, 50);
        
        nombre.setText("Nombre(s)");
        nombre.setForeground(Color.gray);
        nombre.setHorizontalAlignment(JTextField.CENTER);
        nombre.setBounds(15, 70, 110, 21);
        nombre.addFocusListener(controlador);
        nombre.addKeyListener(controlador);
        
        Labelnombre.setText("Nombre(s)");
        Labelnombre.setVisible(false);
        Labelnombre.setHorizontalAlignment(JLabel.CENTER);
        Labelnombre.setForeground(Color.WHITE);
        Labelnombre.setBounds(15,90,110,21);
        
        appPaterno.setText("Apellido Paterno");
        appPaterno.setForeground(Color.GRAY);
        appPaterno.setHorizontalAlignment(JTextField.CENTER);
        appPaterno.setBounds(145, 70, 110, 21);
        appPaterno.addFocusListener(controlador);
        appPaterno.addKeyListener(controlador);
        
        LabelappPaterno.setText("Apellido Paterno");
        LabelappPaterno.setVisible(false);
        LabelappPaterno.setHorizontalAlignment(JLabel.CENTER);
        LabelappPaterno.setForeground(Color.WHITE);
        LabelappPaterno.setBounds(145, 90, 110, 21);

        
        appMaterno.setText("Apellido Materno");
        appMaterno.setForeground(Color.GRAY);
        appMaterno.setHorizontalAlignment(JTextField.CENTER);
        appMaterno.setBounds(275, 70, 110, 21);
        appMaterno.addFocusListener(controlador);
        appMaterno.addKeyListener(controlador);
        
        LabelappMaterno.setText("Apellido Materno");
        LabelappMaterno.setVisible(false);
        LabelappMaterno.setHorizontalAlignment(JLabel.CENTER);
        LabelappMaterno.setForeground(Color.WHITE);
        LabelappMaterno.setBounds(275, 90, 110, 21);
        
        direccion.setText("Dirección");
        direccion.setForeground(Color.GRAY);
        direccion.setHorizontalAlignment(JTextField.CENTER);
        direccion.setBounds(15, 125, 230, 22);
        direccion.addFocusListener(controlador);
        direccion.addKeyListener(controlador);
        
        Labeldireccion.setText("Dirección");
        Labeldireccion.setVisible(false);
        Labeldireccion.setHorizontalAlignment(JLabel.CENTER);
        Labeldireccion.setForeground(Color.WHITE);
        Labeldireccion.setBounds(15, 145, 230, 22);
        
        telefono.setText("Telefono");
        telefono.setForeground(Color.GRAY);
        telefono.setHorizontalAlignment(JTextField.CENTER);
        telefono.setBounds(270, 125, 100, 22);
        telefono.addFocusListener(controlador);
        telefono.addKeyListener(controlador);
        
        Labeltelefono.setText("Telefono");
        Labeltelefono.setVisible(false);
        Labeltelefono.setHorizontalAlignment(JLabel.CENTER);
        Labeltelefono.setForeground(Color.WHITE);
        Labeltelefono.setBounds(270, 145, 100, 22);
        
        email.setText("Correo Electronico");
        email.setForeground(Color.GRAY);
        email.setHorizontalAlignment(JTextField.CENTER);
        email.setBounds(15, 180, 300, 22);
        email.addFocusListener(controlador);
        email.addKeyListener(controlador);
        
        Labelemail.setText("Correo Electronico");
        Labelemail.setVisible(false);
        Labelemail.setHorizontalAlignment(JLabel.CENTER);
        Labelemail.setForeground(Color.WHITE);
        Labelemail.setBounds(15, 200, 300, 22);
        
        contrasenia.setText("Contraseña");
        contrasenia.setForeground(Color.GRAY);
        contrasenia.setHorizontalAlignment(JTextField.CENTER);
        contrasenia.setBounds(15, 230, 190, 22);
        contrasenia.addFocusListener(controlador);
        contrasenia.addKeyListener(controlador);
        
        Labelcontrasenia.setText("Contraseña");
        Labelcontrasenia.setVisible(false);
        Labelcontrasenia.setHorizontalAlignment(JLabel.CENTER);
        Labelcontrasenia.setForeground(Color.WHITE);
        Labelcontrasenia.setBounds(15, 250, 190, 22);
        
        registrarse.setText("Registrarse");
        registrarse.setBounds(125,295,125,25);
        registrarse.addActionListener(controlador);
        
        regresar.setIcon(iconRegresar);
        regresar.setBounds(20, 315, 30, 30);
        regresar.addActionListener(controlador);
        
        this.add(nuevoUsuario);
        this.add(aux);
        this.add(nombre);
        this.add(Labelnombre);
        this.add(appPaterno);
        this.add(LabelappPaterno);
        this.add(appMaterno);
        this.add(LabelappMaterno);
        this.add(direccion);
        this.add(Labeldireccion);
        this.add(telefono);
        this.add(Labeltelefono);
        this.add(email);
        this.add(Labelemail);
        this.add(contrasenia);
        this.add(Labelcontrasenia);
        this.add(registrarse);
        this.add(regresar);
      
        
       
     
        this.add( fondo , BorderLayout.CENTER); 
        fondo.repaint();
    }
  
    
    public JTextField getNombre(){ 
        return nombre;
    }
    public JTextField getAppPaterno(){
        return appPaterno;
    }
    public JTextField getAppMaterno(){
        return appMaterno;
    }
    public JTextField getDireccion(){
        return direccion;
    }
    public JTextField getTelefono(){
        return telefono;
    }
    public JTextField getEmail(){
        return email;
    }
    public JPasswordField getContrasenia(){
        return contrasenia;
    }
    public JLabel getJLNombre(){
        return Labelnombre;
    }
    public JLabel getJLAppPaterno(){
        return LabelappPaterno;
    }
    public JLabel getJLAppMaterno(){
        return LabelappMaterno;
    }
    public JLabel getJLDireccion(){
        return Labeldireccion;
    }
    public JLabel getJLTelefono(){
        return Labeltelefono;
    }
    public JLabel getJLEmail(){
        return Labelemail;
    }
    public JLabel getJLContrasenia(){
        return Labelcontrasenia;
    }
    public JLabel getJLTitulo(){
        return nuevoUsuario;
    }
    public JButton getJBRegistrarse(){
        return registrarse;
    }
    public JButton getJBRegresar(){
        return regresar;
    }
    
    
    
    
    public void setNombre(String cadena){
        getNombre().setText(cadena);
    }
    public void setAppPaterno(String cadena){
        getAppPaterno().setText(cadena);
    }
    public void setAppMaterno(String cadena){
        getAppMaterno().setText(cadena);
    }
    public void setDireccion(String cadena){
        getDireccion().setText(cadena);
    }
    public void setTelefono(String cadena){
        getTelefono().setText(cadena);
    }
    public void setEmail(String cadena){
        getEmail().setText(cadena);
    }
    public void setContrasenia(String cadena){
        getContrasenia().setText(cadena);
    }
    public void setJLNombre(boolean visibilidad){
        getJLNombre().setVisible(visibilidad);
    }
    public void setJLAppPaterno(boolean visibilidad){
        getJLAppPaterno().setVisible(visibilidad);
    }
    public void setJLAppMaterno(boolean visibilidad){
        getJLAppMaterno().setVisible(visibilidad);
    }
    public void setJLDireccion(boolean visibilidad){
        getJLDireccion().setVisible(visibilidad);
    }
    public void setJLTelefono(boolean visibilidad){
        getJLTelefono().setVisible(visibilidad);
    }
    public void setJLEmail(boolean visibilidad){
        getJLEmail().setVisible(visibilidad);
    }
    public void setJLContrasenia(boolean visibilidad){
        getJLContrasenia().setVisible(visibilidad);
    }
    public void setJLTitulo(Font font){
        getJLTitulo().setFont(font);
    }
}


    
    
    
