/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

//import BD.baseDatos;
import Disenio.altaAhorro;
import Disenio.altaCredito;
import Disenio.altaCuenta;
import Disenio.altaUsuario;
import bancocliente.PrBancoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import static java.lang.Integer.parseInt;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class controlAltaCredito implements ActionListener,ItemListener{
    
    private final int ancho = 400;
    private final int altura = 300;
    private final String fondoPanel = "fondo_login.png";
    private altaCredito credito;
    Calendar fecha = Calendar.getInstance();
    private altaAhorro ahorro = new altaAhorro(fondoPanel,ancho,altura);
    private PrBancoCliente cliente= new PrBancoCliente();
   // private static baseDatos con = new baseDatos();
    
    
        public controlAltaCredito(altaCredito credito){
        this.credito = credito;
         }
        
        int anio = fecha.get(Calendar.YEAR)+1;
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        
        String fecha_corte = String.valueOf(dia);
        
        float adeudo = 0,interes = 0;
        int anualidad = 0;
        
       

    @Override
    public void itemStateChanged(ItemEvent ie) {
        
        
        
            if(credito.getAdeudo().getSelectedItem().equals("$0.00 MXN")){
                adeudo = 0;
            }
            if(credito.getAdeudo().getSelectedItem().equals("$5,000.00 MXN")){
                adeudo = (5000 * interes) + 5000;
            }
            if(credito.getAdeudo().getSelectedItem().equals("$10,000.00 MXN")){
                adeudo = (10000 * interes) + 10000;
            }
            if(credito.getAdeudo().getSelectedItem().equals("$25,000.00 MXN")){
                adeudo = (25000 * interes) + 25000;
            }
            if(credito.getAdeudo().getSelectedItem().equals("$50,000.00 MXN")){
                adeudo = (50000 * interes) + 50000;
            }
            if(credito.getAdeudo().getSelectedItem().equals("$100,000.00 MXN")){
                adeudo = (100000 * interes) + 100000;
            }
        
        
        if(credito.getAnualidad().getSelectedItem().equals("6 meses")){
            anualidad = 6;
            interes = (float) 0.08;
            credito.setInteres("8");
            
        }
        if(credito.getAnualidad().getSelectedItem().equals("12 meses") ){
            anualidad = 12;
            interes = (float) 0.10;
            credito.setInteres("10");
            
        }
        if(credito.getAnualidad().getSelectedItem().equals("18 meses") ){
            anualidad = 18;
            interes = (float) 0.13;
            credito.setInteres("13");
        }
        if(credito.getAnualidad().getSelectedItem().equals("24 meses")){
                anualidad = 24;
                interes = (float) 0.16;
                credito.setInteres("16");
            }
        if(credito.getAnualidad().getSelectedItem().equals("48 meses")){
                anualidad = 48;
                interes = (float) 0.21;
                credito.setInteres("21");
            }
        if(credito.getAnualidad().getSelectedItem().equals("0 meses")){
                anualidad = 0;
                interes = (float) 0;
                credito.setInteres("0");
            }
        
        
        }
    
            
    @Override
    public void actionPerformed(ActionEvent ae) {
     
        
        if(ae.getSource() == credito.getSiguiente()){
            
            int noCuenta = Integer.parseInt(cliente.getNoCuenta());
            //int noCuenta = Integer.parseInt(con.getNoCuenta());
            
            String valido = cliente.altaCredito(adeudo,anualidad,interes,fecha_corte,noCuenta);
            //boolean valido = con.altaCredito(adeudo, anualidad, interes, fecha_corte, noCuenta);
           
             if("no".equals(valido)){
                JOptionPane.showMessageDialog(null, "Error al Registrar su cuentra de credito", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            else{
                  ahorro.setVisible(true);
                  credito.setVisible(false);
                  ahorro.setNoCuenta(String.valueOf(noCuenta));

            }
           
        }
    }
}
    
    

