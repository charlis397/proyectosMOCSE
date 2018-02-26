/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.Random;

/**
 *
 * @author Carlos
 */
public class puntos {
    int a = 0; 
    int b = 0;
    int espacio = 0;
    Calculos operacion = new Calculos();
    
    public puntos(int a, int b, int espacio){
        this.a = a;
        this.b = b;
        this.espacio = espacio;
        
    }
        
        
        
        public int[] calcularCardinalidad(){
            int det = 0, auxA, auxB,i,j,cardinalidad = 0;
            int x,y;
            
            auxA = operacion.potencia(a, 3,espacio);
            auxB = operacion.potencia(b, 2,espacio);
            
            det = ( 4 * auxA ) + ( 27 * auxB); 
            det = det % espacio;
            
            if( det != 0){
                for(i = 0; i < espacio; i++){
                    y = operacion.potencia(i, 2,espacio);
                    for(j = 0; j < espacio; j++){
                        x = operacion.potencia(j,3,espacio);
                        x += (a * j) + b;
                        x = x % espacio;
                        if(y == x){
                            //System.out.println("(" + j + "," + i + ")");
                            cardinalidad++;
                        }
                    }
                }
            }
           
            return new int[]{++cardinalidad,a,b};
        }
        
        
        
        
        public coordenada[] calcularPuntos(int a, int b, int unPunto,int cardinalidad){
            
            int det = 0, auxA, auxB,i = 0,j = 0;
            int x,y,aux = 0;
            coordenada [] puntos = new coordenada[cardinalidad];
            coordenada punto = null;
            
            
            
            auxA = operacion.potencia(a, 3,espacio);
            auxB = operacion.potencia(b, 2,espacio);
            
            det = ( 4 * auxA ) + ( 27 * auxB); 
            det = det % espacio;
            
            if( det != 0){
                for(i = 0; i < espacio; i++){
                    y = operacion.potencia(i, 2,espacio);
                    for(j = 0; j < espacio; j++){
                        x = operacion.potencia(j,3,espacio);
                        x += (a * j) + b;
                        x = x % espacio;
                        if(y == x){
                            
                          System.out.println("(" + j + "," + i + ")");
                          punto = new coordenada(j,i);
                          puntos[aux] = punto;
                          aux++;
                        }
                    }
                }
            }
           
            return new coordenada[]{puntos[unPunto]};
        }
        
        
        public coordenada obtenerPunto(){
            Random rn = new Random();
            int i=0,x,y;
            int unPunto;
            int a,b;
            boolean seguir = true;
            boolean valido;
            puntos banco = null ;
            coordenada[] punto = new coordenada[0];
            
        
            while(seguir == true){
                a = rn.nextInt(50)+1;
                b = rn.nextInt(50)+1;
             
                banco = new puntos(a,b,espacio);
            
                int valoresPublicos[] = banco.calcularCardinalidad();
                valido = banco.operacion.primo(valoresPublicos[0]);
                unPunto =  rn.nextInt(valoresPublicos[0]);
                
                if(valido == true){
                    System.out.println("y2 = x3 + "+valoresPublicos[1]+"x"+"+ "+valoresPublicos[2]+"  mod " + espacio + "    con cardinalidad " + valoresPublicos[0]);
                    punto = banco.calcularPuntos(valoresPublicos[1], valoresPublicos[2], unPunto,valoresPublicos[0]);
                    System.out.println("este es el punto a obtener:  " + unPunto);
                    x = punto[0].getX();
                    y = punto[0].getY();
                    System.out.println("("+x+","+y+")");
                    seguir =  false;
                }
            }
            
            return punto[0];
        }
    
        
    
}
