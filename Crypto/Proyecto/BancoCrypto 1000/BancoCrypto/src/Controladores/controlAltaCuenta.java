/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

//import BD.baseDatos;
import Disenio.Login;
import Disenio.altaAhorro;
import Disenio.altaCredito;
import Disenio.altaCuenta;
import Disenio.altaUsuario;
import Disenio.principal;
import bancocliente.PrBancoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static javax.print.attribute.standard.MediaPrintableArea.MM;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos
 */
public class controlAltaCuenta implements ActionListener {
    
    private final int ancho = 400;
    private final int altura = 300;
    private final String fondoPanel = "fondo_login.png";
    
    private altaCuenta alta;
    private altaUsuario usuario;
    Calendar fecha = Calendar.getInstance();
    //private static baseDatos con = new baseDatos();
    private PrBancoCliente cliente = new PrBancoCliente();
    private altaCredito credito = new altaCredito(fondoPanel,ancho,altura);
    
    
    public controlAltaCuenta(altaCuenta alta){
        this.alta = alta;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        String correo = alta.getCorreo().getText();
        
        String noCuenta = alta.getNoCuenta().getText();
        
        int anio = fecha.get(Calendar.YEAR)+1;
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
      
        String fecha_vencimiento = anio + "-" + mes + "-" + dia;
        int  NIP = Integer.parseInt(alta.getNIP().getText());
        int id_usuario = Integer.parseInt(cliente.getIdUsuario(correo));
        //int id_usuario = Integer.parseInt(con.getIdUsuario(correo));
        
        
        if(ae.getSource() == alta.getSiguiente()) {  
           
           
           String valido = cliente.altaCuenta(NIP, fecha_vencimiento,id_usuario);
            //boolean valido = con.altaCuenta(NIP, fecha_vencimiento, id_usuario);
           
           if("no".equals(valido)){
                JOptionPane.showMessageDialog(null, "Error al Registrar al USUARIO", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            else{
                credito.setVisible(true);
                credito.setCorreo(correo);
                alta.setVisible(false);
           
          }
    }
}
}