#include "fun.h"

int main(int argc, char* argv[]){
	
	int polinomioA[N],polinomioB[N],polinomioResultante[15];
	int i,decimalA,decimalB,decimalResultante,decimalmx,decimalAUX;
	
	int polinomioMX[15] = {0,0,0,0,0,0,1,0,0,0,1,1,0,1,1};	//x8 + x4 + x3 + x + 1
	
	crearPolinomio(polinomioA);
	crearPolinomio(polinomioB);
	
	printf("\n\n\n");
	
	//imprimirPolinomio(polinomioA);
	
	printf("\n");
	
	//imprimirPolinomio(polinomioB);

	printf("\n");
	
	//imprimirMX(polinomioMX);
	printf("\n\n\n");
	
	
	decimalA = BinarioDecimal(polinomioA);
	decimalB = BinarioDecimal(polinomioB);
	decimalmx = BinarioDecimal(polinomioMX);
	
	//printf("Decimal A:%d\nDecimal B:%d\n%d",decimalA,decimalB,decimalmx);
	//printf("\n");
	
	decimalResultante = multiplicacionPolinomio(polinomioA,decimalB);
	//printf("\n****:%d\n",decimalResultante);
	printf("x7\tx6\tx5\tx4\tx3\tx2\tx\t1:\n");
	DecimalBinario(decimalResultante,polinomioResultante);
	
	//printf("\nDecimal Resultante:%d\n",decimalResultante);

	return 0;
}