/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

//import BD.baseDatos;
import Disenio.pagarCredito;
import bancocliente.PrBancoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class controlPagarCredito implements  ActionListener,KeyListener{

    PrBancoCliente cliente=new PrBancoCliente();
    //private static baseDatos con = new baseDatos();
    pagarCredito pagarCredito;
    Calendar fecha = Calendar.getInstance();
    
    public controlPagarCredito(pagarCredito pagarCredito){
        this.pagarCredito = pagarCredito;
    }   
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource() == pagarCredito.getDepositar()){
            
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String correo = pagarCredito.getCorreo().getText();
         System.out.println(correo);
        String fecha_hora = anio + "-" + mes + "-" + dia + " " + hora + ":" + minuto + ":" + segundo ;
         System.out.println(fecha_hora);
        String monto = pagarCredito.getMonto().getText();
         System.out.println(monto);
        String concepto = "Pago a Tarjeta Credito";
         System.out.println(concepto);
        String id_Credito = cliente.getNoCredito(correo);
         System.out.println(id_Credito);
        String anualidad = cliente.getAnualidad(correo);
         System.out.println(anualidad);
        String interes = cliente.getInteres(correo);
         System.out.println(interes);
        String fecha_corte = cliente.getFechaCorte(correo);
         System.out.println(fecha_corte);
        String id_ahorro = cliente.getNoAhorro(correo);
         System.out.println(id_ahorro);
        String saldoActual = cliente.getSaldoAhorro(correo);
         System.out.println(saldoActual);
        String saldoActualUpdate = String.valueOf(Float.parseFloat(saldoActual) - Float.parseFloat(monto));
         System.out.println(saldoActualUpdate);
        float adeudo = Float.parseFloat(cliente.getAdeudoCredito(correo));
        if(Float.parseFloat(monto) >= 0){
        if(adeudo >0){
        if(Float.parseFloat(saldoActual) >= Float.parseFloat(monto)){
            
            System.out.println("Son los valores que llegan a pagoValido " +fecha_hora + monto+concepto+id_Credito);
        String pagoValido = cliente.setPago(fecha_hora, monto, concepto, id_Credito);
        
            System.out.println("ESTE ES EL PGO EN EL CONTROL " + pagoValido);
            
        
        if("si".equals(pagoValido)){
            
            JOptionPane.showMessageDialog(null, "La operación se realizo con EXITO", "Exito", JOptionPane.DEFAULT_OPTION);
           
            float adeudoActual =  adeudo - Float.parseFloat(monto);
            cliente.updateAhorro(id_ahorro, saldoActualUpdate);
            
            if(adeudoActual > 0){
                cliente.updateAdeudo(String.valueOf(adeudoActual), anualidad, interes, fecha_corte, correo);
            }
            if(adeudoActual == 0){
                JOptionPane.showMessageDialog(null, "Ha pagado totalmente su deuda", "FELICIDADES", JOptionPane.DEFAULT_OPTION);
                cliente.updateAdeudo(String.valueOf(adeudoActual), anualidad, interes, fecha_corte, correo);
            }
            if(adeudoActual < 0){
                float resto = (-1) * adeudoActual;
                JOptionPane.showMessageDialog(null, "Ha pagado totalmente su deuda, su restante de $" + resto +" se vera reflejado en su cuenta de ahorro", "FELICIDADES", JOptionPane.DEFAULT_OPTION);
                int adeudoActuale = 0;
                cliente.updateAdeudo(String.valueOf(adeudoActuale), anualidad, interes, fecha_corte, correo);
                String saldoAhorroActual = cliente.getSaldoAhorro(correo);
              
                String saldoAhorroUpdate = String.valueOf(Float.parseFloat(saldoAhorroActual) + resto );
                cliente.updateAhorro(id_ahorro,saldoAhorroUpdate);
            }
            pagarCredito.dispose();
            
        }
        else{
            JOptionPane.showMessageDialog(null, "Fallo en la operación", "Error", JOptionPane.DEFAULT_OPTION);
        }
        }
        else{
            JOptionPane.showMessageDialog(null, "No tienes fondos suficientes para realizar esta operación", "Error", JOptionPane.DEFAULT_OPTION);
        }
        }
        else{
            JOptionPane.showMessageDialog(null, "No tiene adeudo que pagar", "Error", JOptionPane.DEFAULT_OPTION);
        }
        }
        else{
            JOptionPane.showMessageDialog(null, "Ingrese cantidades positivas", "Error", JOptionPane.DEFAULT_OPTION);
        }
        }
        
        if(ae.getSource() == pagarCredito.getSalir()){
            pagarCredito.dispose();
        }
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        char c = ke.getKeyChar();
       if(ke.getSource() == pagarCredito.getMonto()){
            pagarCredito.getMonto().getText();
           if((c<'0' || c>'9')){
            ke.consume();
            }
         }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        
    }

    @Override
    public void keyReleased(KeyEvent ke) {
      
    }
    
}
