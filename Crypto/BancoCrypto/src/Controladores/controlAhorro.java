/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

//import BD.baseDatos;
import Disenio.ahorro;
import Disenio.pagarCredito;
import Disenio.transferencia;
import bancocliente.PrBancoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Carlos
 */
public class controlAhorro implements ActionListener {
    private ahorro ahorro;
    private final int ancho = 400;
    private final int altura = 200;
    private final String fondoPanel = "fondo_login.png";
    
    private transferencia transferir = new transferencia(fondoPanel,ancho,altura);
    private pagarCredito pagarCredito = new pagarCredito(fondoPanel,ancho,altura);
    PrBancoCliente cliente = new PrBancoCliente();
    //private static baseDatos con = new baseDatos();
    
    public controlAhorro(ahorro ahorro){
        this.ahorro = ahorro;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String correo = ahorro.getCorreo().getText();
        String credito = cliente.getNoCredito(correo);
        if(ae.getSource() == ahorro.getTransferir()){
            transferir.setVisible(true);
            transferir.setCorreo(correo);
            
        }
        if(ae.getSource() == ahorro.getPagarCredito()){
            pagarCredito.setVisible(true);
            pagarCredito.setCuentaTransferir(credito);
            pagarCredito.setCorreo(correo);
        }
    }
    
    
}
