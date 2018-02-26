/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocliente;

import java.util.Random;

/**
 *
 * @author Samu
 */
public class DiffieHellman {
    int[] datos;
    int k;
    
    public int getK(){
        return k;
    }
    
    public DiffieHellman(int[] datos){
        this.datos=datos;
    }
    
    public int kAleatorio(){
        Random rn = new Random();
        
        int key=rn.nextInt(datos[2]-1);
//        System.out.println(datos[2]);//Imprime primo
        while(key==0){
         key=rn.nextInt(datos[2]-1);   
        }
        
//        System.out.println(key);//Imprime k aleatorio encontrado
        return key;
    }
    
    public String doblado(){
        
        int[] xy;
        
        this.k=kAleatorio();
        String punto;
        
        sumapuntos suma = new sumapuntos();
/*        
        System.out.println(datos[3]);
        System.out.println(datos[4]);
        System.out.println(datos[0]);
        System.out.println(k);
        System.out.println(datos[2]);          
*/        
        xy=suma.Doblar(datos[3],datos[4],datos[0],k,datos[2]);
              
        

        punto=xy[0]+"/"+xy[1]+"/";
//        System.out.println(punto);
//        punto="3/4/";

        return punto;
    }
     
}
