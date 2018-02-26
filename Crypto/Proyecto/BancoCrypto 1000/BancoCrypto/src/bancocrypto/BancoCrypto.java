/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocrypto;
import Controladores.controLogin;
import Disenio.Login;
import Disenio.altaUsuario;
import Disenio.credito;
import Disenio.principal;
import Logica.Calculos;
import Logica.Cuenta;
import Logica.coordenada;
import Logica.puntos;
import bancocliente.PrBancoCliente;
import java.util.Random;
import javax.swing.JOptionPane;
/**
 *
 * @author Carlos
 */
public class BancoCrypto {

    public static void main(String[] args) {
//        Random rn = new Random();
//        Calculos operacion = new Calculos();
//        int posicion = rn.nextInt(9)+1;
//        int[] espacio = operacion.numerosPrimos(posicion);
//        puntos banco = new puntos(0,0,espacio[0]);      
//        coordenada punto =banco.obtenerPunto();
//        System.out.println("El punto en el main es ("+ punto.getX()+"," + punto.getY()+")");
   
          //principal banco = new principal();
          PrBancoCliente cliente = new PrBancoCliente();
          Login banco = new Login();
          banco.setVisible(true);
    
      
    }
}
    

