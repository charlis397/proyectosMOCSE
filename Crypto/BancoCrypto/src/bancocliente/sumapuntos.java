/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocliente;

/**
 *
 * @author Samu
 */
public class sumapuntos {
  public static int[] Doblar(int x, int y, int a, int k, int p){
    int rx = x, ry = y, lamda, res;
    int[] salida = new int[2];
    //lamda = (3x2+a)/2y  mod p
    //x1=lamba2 -2x
    //y = lamba(x-x1)-y
    if(y!=0){

      res =(int) ((3*Math.pow(x,2)+a)%2*y);
      if(res != 0){
        res = Inverso(2*y,p);
        lamda = (int) ((3*Math.pow(x,2)+a)*res)%p;
      }
      else{
        lamda =(int) ((3*Math.pow(x,2)+a)/2*y)%p;
      }
      rx = (int) (Math.pow(lamda,2) - 2*x)%p;
      if(rx < 0){
        rx = rx + p;
      }
      ry = ((lamda*(x-rx))-y)%p;
      if(ry < 0){
        ry = ry + p;
      }

      salida[0] = rx;
      salida[1] = ry;

      if(k!=1){
        //System.out.println(rx + "," + ry);
        Doblar(rx,ry,a,k-1,p);
      }
    }
    else{
      salida[0] = x;
      salida[1] = y;
      System.out.println(x + "," + y + "\nPunto al infinito");
    }
    return salida;
  }

  public static int Inverso(int a,int m){
        int c1=1,c2=-1*(m/a);//coeficiente de a y b respectivamente
        int t1=0,t2=1;//coeficientes penultima corrida
        int r=m%a;//residuo, asignamos 1 como condicion de entrada
        int x=a,y=r,c;
        while(r!=0)
        {
        c= x/y;//cociente
        r= x%y;//residuo
        //guardamos valores temporales de los coeficientes
        //multiplicamos los coeficiente por -1*cociente de la division
        c1*=-1*c;
        c2*=-1*c;
        //sumamos la corrida anterior
        c1+=t1;
        c2+=t2;
        //actualizamos corrida anterior
        t1=-1*(c1-t1)/c;
        t2=-1*(c2-t2)/c;
        x=y;
        y=r;
        }
      if(x!=1){//residuo anterior es 1 , son primos relativos y el inverso existe
            System.out.println(""+t2);
      }
      if (t2 < 0){
        t2 = t2+m;
      }
      return t2;
    }    
}
