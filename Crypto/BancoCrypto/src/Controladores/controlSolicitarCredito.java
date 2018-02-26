/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

//import BD.baseDatos;
import Disenio.credito;
import Disenio.principal;
import Disenio.solicitarCredito;
import bancocliente.PrBancoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class controlSolicitarCredito implements ActionListener,ItemListener {

    private final int ancho = 400;
    private final int altura = 300;
    private final String fondoPanel = "fondo_login.png";
    
    private solicitarCredito solicitarCredito;
    PrBancoCliente cliente= new PrBancoCliente();
    //private static baseDatos con = new baseDatos();
    //private credito menu = new credito(fondoPanel,ancho,altura);
    private credito menu;

    
    Calendar fecha = Calendar.getInstance();
    
    public controlSolicitarCredito(solicitarCredito solicitarCredito){
        this.solicitarCredito = solicitarCredito;
    }
    
    float adeudo = 0;
        
        
        int anualidad = 0;
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == solicitarCredito.getSiguiente()){
            int dia = fecha.get(Calendar.DAY_OF_MONTH);
            String correo = solicitarCredito.getCorreo().getText(); 
            String adeudoAnterior = cliente.getAdeudoCredito(correo);
            String interes = solicitarCredito.getInteres().getText();
            String fecha_corte = String.valueOf(dia);
            String noCuenta = cliente.getNoCuenta();
            String adeudoActual = String.valueOf(adeudo + Integer.parseInt(adeudoAnterior));
            if(Float.parseFloat(adeudoAnterior) <= 0){
                
                cliente.updateAdeudo(String.valueOf(adeudo), String.valueOf(anualidad), interes, fecha_corte,correo);
            }
           
            if(Float.parseFloat(adeudoActual) <= 100000){
            String valido = cliente.updateAdeudo(String.valueOf(adeudo), String.valueOf(anualidad), interes, fecha_corte,correo);
            if( "si".equals(valido)){
            solicitarCredito.setVisible(false);
            
                JOptionPane.showMessageDialog(null, "Operación Exitosa", "Registro Exitoso", JOptionPane.WARNING_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "Error en la Operación", "Error", JOptionPane.ERROR_MESSAGE);
            }
            }
            else{
                JOptionPane.showMessageDialog(null, "Excede del credito permitido de $100,000.00 MX", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(ae.getSource() == solicitarCredito.getSalir()){
            solicitarCredito.dispose();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
          float interes = Float.parseFloat(solicitarCredito.getInteres().getText());
            solicitarCredito.setInteres(String.valueOf(interes));
            String correo =solicitarCredito.getCorreo().getText(); 
            String adeudoAnterior = cliente.getAdeudoCredito(correo);
            
           
            if(solicitarCredito.getAdeudo().getSelectedItem().equals("$5,000.00 MXN")){
                adeudo = (5000 * interes) + 5000;
            }
            if(solicitarCredito.getAdeudo().getSelectedItem().equals("$10,000.00 MXN")){
                adeudo = (10000 * interes) + 10000;
            }
            if(solicitarCredito.getAdeudo().getSelectedItem().equals("$25,000.00 MXN")){
                adeudo = (25000 * interes) + 25000;
            }
            if(solicitarCredito.getAdeudo().getSelectedItem().equals("$50,000.00 MXN")){
                adeudo = (50000 * interes) + 50000;
            }
            if(solicitarCredito.getAdeudo().getSelectedItem().equals("$100,000.00 MXN")){
                adeudo = (100000 * interes) + 100000;
            }
            
            if(Float.parseFloat(adeudoAnterior) == 0){
            if(solicitarCredito.getAnualidad().getSelectedItem().equals("6 meses")){
            anualidad = 6;
            interes = (float) 0.08;
            solicitarCredito.setInteres("0.08");
            
            }
            if(solicitarCredito.getAnualidad().getSelectedItem().equals("12 meses") ){
            anualidad = 12;
            interes = (float) 0.10;
            solicitarCredito.setInteres("0.10");
            
            }
            if(solicitarCredito.getAnualidad().getSelectedItem().equals("18 meses") ){
            anualidad = 18;
            interes = (float) 0.13;
            solicitarCredito.setInteres("0.13");
            }
            if(solicitarCredito.getAnualidad().getSelectedItem().equals("24 meses")){
                anualidad = 24;
                interes = (float) 0.16;
                solicitarCredito.setInteres("0.16");
            }
            if(solicitarCredito.getAnualidad().getSelectedItem().equals("48 meses")){
                anualidad = 48;
                interes = (float) 0.21;
                solicitarCredito.setInteres("0.21");
            }
            }
    }
    
}
