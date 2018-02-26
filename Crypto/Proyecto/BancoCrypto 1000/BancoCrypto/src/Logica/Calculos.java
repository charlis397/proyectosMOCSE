/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Carlos
 */
public class Calculos {
    
    public int potencia(int numero, int potencia, int espacio){
            
            int i = 0;
            int elevado = numero;
            
            for(i = 1; i<potencia; i++){
                elevado *= numero;
                elevado = elevado % espacio;
            }
            
            return elevado;
        }
    
    public boolean primo (int cardinalidad){
            int i;
            int aux = 0;
            boolean esPrimo;
            for(i = 1; i<( cardinalidad + 1); i++){
                if( cardinalidad %i == 0 ){
                    aux++;
                }
            }
            
            if(aux!=2){
              esPrimo = false;
            }else{
                esPrimo = true;
                }  
            return esPrimo;
        }
    
    public int[] numerosPrimos(int posicion){
      int i = 0,noPrimos, j= 0;
      int divisores = 1;
      int primo = 2;
      noPrimos=100;
      int [] arrPrimos = new int[noPrimos];
      
      while(i < noPrimos){
          if(primo >= divisores) 
            {
		divisores++;
                if(((primo%divisores)==0) && (primo!=divisores)) 
                {
                        primo++;
                        divisores=1;
                    }
                if((primo%divisores)==0 && (primo==divisores)) 
                {
                     arrPrimos[j]= primo;   
                     //System.out.println(primo);
                     primo++;
                     i++;
                     j++;
                    divisores=1;
	//aux = primo;
                }
            }
          else{
              primo++;
          }
          
      }
      return new int[]{arrPrimos[posicion]};
    }
    
    
}
