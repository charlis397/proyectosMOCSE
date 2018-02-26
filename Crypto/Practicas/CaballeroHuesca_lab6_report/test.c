#include "fun.h"

int main(int argc, char* argv[]){
	
					     //4 3 2 1 0 9 8 7 6 5 4 3 2 1 0
	int polinomioMX[15] = {0,0,0,0,0,0,1,0,0,0,1,1,0,1,1};		//x8 + x4 + x3 + x + 1
	int a,b,xor,polinomioA[N],i,Resultado[15],polinomioB[N],polinomioFinal[N];
	int aux =0,final,decimalMX,polinomioMXaux[9],decimalResultado =0;
	int posicionMX=0,posicionResultado=0,diferencia = 0, decimalMXaux=0,finalaux=0,decimalFinalaux=0,decimalMXFijo=0;;
	a = 2;
	b = 4;
	//printf("x7\tx6\tx5\tx4\tx3\tx2\tx\t1:\n");
	//DecimalBinario(a,polinomioA);
	//DecimalBinario(b,polinomioB);
	
	for(i=0;i<N;i++){
		
		if(polinomioA[7-i]==1){
			
			aux^=(b<<i);
			
		}
	}
	

	printf("\n\n");

	final = 12307;
	//invertirVector(final,Resultado);
	printf("x14\tx13\tx12\tx11\tx10\tx9\tx8\tx7\tx6\tx5\tx4\tx3\tx2\t\tx\t1\n");
	DecimalBinarioAux(final,Resultado);

	decimalMX = BinarioDecimalMX(polinomioMX);
	decimalMXFijo = BinarioDecimalMX(polinomioMX);
	
	//invertirVector(decimalMX,polinomioMX);
	
	printf("primer numero Resultado : %d",Resultado[0]);
	
	printf("\nprimer numero mx : %d\n",polinomioMX[0]);
	
	decimalResultado = BinarioDecimal(Resultado);
	
	printf("FINAL ANTES CICLO: %d\n",final);
	printf("\nMX ANTES CICLO: %d\n",decimalMX);
	
	finalaux = final;
	
	while(finalaux > decimalMX ){
		
		
		posicionMX= posicionPrimerUno(polinomioMX);
		posicionResultado= posicionPrimerUno(Resultado);
		diferencia = (posicionMX - posicionResultado);
			
		decimalFinalaux = decimalMX << diferencia;

		
	printf("\nPOSMX %d y  POSRESULTADO%d\n", posicionMX, posicionResultado);
	printf("aqui LA DIFERENCIA %d\n",diferencia);
	printf("\n****SOY FINAL EN EL CICLO %d\n",final);
	printf("\n****SOY MX EN EL  CICLO %d\n",decimalMX);
	printf("\n****SOY DECIMALFINALAUX %d\n",decimalFinalaux);
	
		finalaux = decimalFinalaux^final;
	
	// if(final > decimalMXFijo){
		
		// final = final^decimalMXFijo;
									// }
			
			
		printf("x14\tx13\tx12\tx11\tx10\tx9\tx8\tx7\tx6\tx5\tx4\tx3\tx2\t\tx\t1\n");	
		DecimalBinarioAux(finalaux,Resultado);
		
		finalaux = BinarioDecimal(Resultado);
		
	}
	

	printf("x7\tx6\tx5\tx4\tx3\tx2\tx\t1\n");
		DecimalBinarioAux(finalaux,Resultado);
	
	
	printf("\n****SOY FINALAUX DESPUES CICLO %d\n",finalaux);
	printf("\n****SOY FINAL DESPUES CICLO %d\n",final);
	printf("\n****SOY MX DESPUES DEL CICLO %d\n",decimalMX);
	// printf("\n+++++%d\n",diferencia);
	
	
	//system("pause");
	return 0;
	
}