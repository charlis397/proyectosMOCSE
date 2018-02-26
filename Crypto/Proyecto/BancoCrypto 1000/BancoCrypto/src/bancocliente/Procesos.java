/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocliente;

import java.util.Arrays;

/**
 *
 * @author Samu
 */
public class Procesos {
    
   
    
    public int[] extraePublicos(String publicos){
        
        int[] datos = new int[14];
        
        int sig;
        int ult;
        
        //Extrae del string
        //    a-k / b-k / p-k / x-k / y-k / a-iv / b-iv / p-iv / x-iv / y-iv / Yx-k / Yy-k / Yx-iv / Yy-iv /
        sig=publicos.indexOf('/');
        datos[0]=Integer.parseInt(publicos.substring(0,sig));
        
        for(int i=1;i<14;i++){
            ult=sig;
            sig=publicos.indexOf('/',ult+1);
            datos[i]=Integer.parseInt(publicos.substring(ult+1,sig));
            
        }
        
        return datos;
    }
    
    
}
