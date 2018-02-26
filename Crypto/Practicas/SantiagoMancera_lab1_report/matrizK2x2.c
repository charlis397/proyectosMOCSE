#include "fun.h"

int main(){
		int determinante,tieneinversa,inversoMulti;
		srand(time(NULL));
						//Crear Matriz 2X2
		int MatrizK[2][2]={rand()%26,rand()%26,rand()%26,rand()%26};
						//Imprimir Matriz
		int i,j;
		for (i=0; i<2; i++){ 
			for (j=0; j<2; j++){
				printf(" %d ",MatrizK[i][j]);
			}		
				printf("\n");	
		}
					//Calcular el determinante
		determinante = (MatrizK[0][0]*MatrizK[1][1]) - (MatrizK[1][0]*MatrizK[0][1]);
			//Determinante mod 26 para determinantes negativos
		while(determinante<0){
		determinante = determinante+MOD;	
		}
		//Determinante mod 26 para determinantes positivos
		if(determinante>=MOD){
			determinante = determinante%MOD;
		}
		// Determinar si tiene inversa
		if(determinante!=0&&determinante%2!=0&&determinante%13!=0){
			tieneinversa = mcd(determinante,MOD);
			//Calcular Inverso Multiplicativo Modular
			if(tieneinversa==1){
			inversoMulti=inversoMultiplicativoModular(determinante, MOD);
			printf("Esta Llave si se puede usar\n");
			printf("El determinante de la matriz es %d mod 26\n",determinante);
			printf("Esta matriz si tiene inversa\n");
			printf("El inverso multiplicativo de esta matriz es %d mod 26\n",inversoMulti);	
								}
		}
		else{
	    printf("La matriz llave no se puede usar\n");
		printf("El determinante de la matriz es %d mod 26\n",determinante);
		}
		return 0;
}