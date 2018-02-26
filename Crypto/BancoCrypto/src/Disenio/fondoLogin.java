/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Disenio;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Carlos
 */
public class fondoLogin extends JPanel {
    
    String imagen = ""; 
    int largo = 0;
    int ancho = 0;

    public fondoLogin(String imagen,int largo, int ancho){ 
        
           this.imagen = imagen; 
           this.largo = largo;
           this.ancho = ancho;
           this.setSize(largo,ancho); 
        }
    
    @Override 
    public void paint(Graphics g){ 
        Dimension tamanio = getSize(); 
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/Imagenes/" + imagen)); 
        g.drawImage(imagenFondo.getImage(),0,0,tamanio.width, tamanio.height, null); 
        setOpaque(false); 
        super.paintComponent(g); 
}
}
