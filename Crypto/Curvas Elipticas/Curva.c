
#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define GF 11

int calcularPuntos(int,int,int,int[19][2]);
int potencia(int,int,int);

int main(){
	
	int resultado = 0, puntosResultado = 0;
	int arraux [19][2],i,j;
	
	calcularPuntos(2, 2, GF,arraux);
	
	
	for(i=0; i<19;i++){
	
			printf("[%d][%d]\n",arraux[i][0],arraux[i][1]);
		
	}
	//int arreglo [puntosResultado][puntosResultado];
	
                //(a, b, espacio)
	
	
	
	//resultado = potencia(3,100,GF);
	
	

	return 0;
}



int potencia(int numero, int potencia,int espacio){
	
	int i;
	int elevado = numero;
	
	for(i = 1; i < potencia; i++){
		
		elevado *= numero;
		elevado = elevado % espacio;
	}
	
	return elevado;
	
}







int calcularPuntos(int a, int b, int espacio,int totElem[19][2]){
	
	int det, i, j,contador=0,contadoraux=0;
	int y, x, auxa, auxb,auxx,k,l;
	//int puntos[0][0];
	
	auxa = potencia(a,3,GF);
	auxb = potencia(b,2,GF);
	
	det = ( 4 * auxa ) + ( 27 * auxb);
	det = det % espacio;
	printf("determinante : %d \n\n",det);
	
	if ( det != 0 )
	{
		for( i = 0; i < espacio; i++ )
		{
			y = potencia(i,2,GF);
			
			for( j = 0; j < espacio; j++ )
				{
					x = potencia(j,3,GF); 
					x += (a*j) + b;
					x = x % espacio;
					
					if ( y == x )
					{
						for(k = 0; k < 19; k++){
							//for(l = 0; l<2;l++){
								totElem[k][0] = j;
								totElem[k][1] = i;
							//}
						}
						//arraux[j][i]
						printf(	"(%d , %d)\n",j,i);
						contador++;
					}
					contadoraux++;
				}
		}
		
		
	}
	contador++;
	
	printf("\nTotal :%d\n",contador);	
	printf("\nTotal Puntos :%d\n",contadoraux);	
	
	return contador;
}

// void llenarArreglo(int matriz[19][19],int x,int y){
	// int i,j;
	// for(i = 0;i < 19; i++){
		// for(j = 0;j < 19; j++){
			// matriz[i][j] = {x,y};
		// }
	// }
// }
