/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Disenio;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Carlos
 */
public class bienvenido extends JPanel {
    
    private JLabel escom;
    
    int ancho = 0;
    int alto = 0;
    String fondo = "";
    
    public bienvenido(String fondo,int ancho,int alto){
        super();
        this.ancho = ancho;
        this.alto = alto;
        this.fondo = fondo;
        
        this.setBounds(150, 0, ancho, alto);
        this.setLayout(null); 
        inicializarComponentes(fondo);
    }
    
    private void inicializarComponentes(String imFondo){
       
        
        fondoLogin fondo = new fondoLogin(imFondo,ancho,alto); 
        
        ImageIcon iconESCOM = new ImageIcon(getClass().getResource("/Imagenes/escom.png"));
        
        escom = new JLabel();
        
        escom.setIcon(iconESCOM);
        escom.setBounds(10, 80, 200, 200);
        
        
        this.add( fondo , BorderLayout.CENTER); 
        fondo.repaint();
        
    }
    
}
