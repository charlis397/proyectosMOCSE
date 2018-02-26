#include "fun.h"

int main(){
	int determinante,tieneinversa,inversoMulti;
	
	srand(time(NULL));
		
					//Crear Matriz 3X3
		
	int MatrizK[3][3]={rand()%26,rand()%26,rand()%26,rand()%26,rand()%26,rand()%26,rand()%26,rand()%26,rand()%26};
	
							//Imprimir Matriz
	int i,j;
	
	for (i=0; i<3; i++)
	{ 
		for (j=0; j<3; j++)
		{
			printf(" %d ",MatrizK[i][j]);
		}
			
			printf("\n");	
	}
	
	//Calcular el determinante
					
	determinante = (MatrizK[0][0]*MatrizK[1][1]*MatrizK[2][2]+MatrizK[1][0]*MatrizK[2][1]*MatrizK[0][2]+MatrizK[2][0]*MatrizK[0][1]*MatrizK[1][2])
					-(MatrizK[0][2]*MatrizK[1][1]*MatrizK[2][0]+MatrizK[1][2]*MatrizK[2][1]*MatrizK[0][0]+MatrizK[2][2]*MatrizK[0][1]*MatrizK[1][0]);

	
	//Determinante mod 26 para determinantes negativos
		while(determinante<0)
		{
		determinante = determinante+26;	
		}
		
		//Determinante mod 26 para determinantes positivos
		if(determinante>=26)
		{
			determinante = determinante%26;
		}
		
		// Determinar si tiene inversa
		if(determinante!=0&&determinante%2!=0&&determinante%13!=0)
		{
			tieneinversa = mcd(determinante,26);
		
			//Calcular Inverso Multiplicativo Modular
			if(tieneinversa==1){
		
			inversoMulti=inversoMultiplicativoModular(determinante, MOD);
		
			printf("Esta Llave si se puede usar\n");
			printf("El determinante de la matriz es %d mod 26\n",determinante);
			printf("Esta matriz si tiene inversa\n");
			printf("El inverso multiplicativo de esta matriz es %d mod 26\n",inversoMulti);	
								}
		}
		else
		{
	    printf("La matriz llave no se puede usar\n");
		printf("El determinante de la matriz es %d mod 26\n",determinante);
		}

	return 0;
}