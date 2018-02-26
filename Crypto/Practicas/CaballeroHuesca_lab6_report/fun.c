#include "fun.h"



void crearPolinomio(int polinomio[N]){
	
	int i;
	printf("Introduce el segundo polinomio con base al siguiente orden: (presionar TAB despues de colocar cada bit)\n");
	printf("x7\tx6\tx5\tx4\tx3\tx2\tx\t1:\n");
	for(i=0;i<N;i++){
	scanf("%d",&polinomio[i]);
	}
	
}


void imprimirPolinomio(int polinomio[N]){
	
	int i;
	printf("x7\tx6\tx5\tx4\tx3\tx2\tx\t1\n");
	for(i=0;i<N;i++){
		printf("%d\t",polinomio[i]);
	}	
	
	
}

void imprimirMX(int polinomio[15]){
	
	int i;
	printf("x14\tx13\tx12\tx11\tx10\tx9\tx8\tx7\tx6\tx5\tx4\tx3\tx2\t\tx\t1\n");
	for(i=0;i<15;i++){
		printf("%d\t",polinomio[i]);
	}	
	
	
}


int BinarioDecimal(int polinomio[N]){
	
	int j,decimal=0;

	for(j=0;j<N;j++){
	
	if(polinomio[j]==0 || polinomio[j]==1){
		
	decimal = polinomio[7-j]*pow(2,j)+decimal;
	}
	else{
		printf("Los coeficientes ingresados estan fuera del espacio GF(2)\n");
	}
	}
		
	return decimal;
}

int BinarioDecimalMX(int polinomio[15]){
	
	int j,decimal=0;

	for(j=0;j<15;j++){
	
	if(polinomio[j]==0 || polinomio[j]==1){
		
	decimal = polinomio[14-j]*pow(2,j)+decimal;
	}
	else{
		printf("Los coeficientes ingresados estan fuera del espacio GF(2)\n");
	}
	}
		
	return decimal;
}


void DecimalBinario(int decimal,int binario[N]){
	
	int aux,i,arrAux[N];
	
	for(i=0;i<N;i++){
	arrAux[i]=0;
	binario[i]=0;
	}
	
	aux = decimal;
	
	for(i=0;decimal!=0;i++){
	arrAux[i] = decimal%2;		//RESIDUO
	decimal = decimal/2;
	}
	
	for(i=0;i<N;i++){
	binario[7-i]=arrAux[i];   	//INVIERTE EL ARREGLO
	}
	
	for(i=0;i<N;i++){
	printf("%d\t",binario[i]);	//IMPRIME 
	}
	printf("\n");
	
	
}

int multiplicacionPolinomio(int polinomio[N],int polinomio2){
	
	int aux=0,i;
	
	for(i=0;i<N;i++){
	
	if(polinomio[7-i]==1){
		
		aux^=(polinomio2<<i);
		
	}
	}
	
	return aux;
	
}

void invertirVector(int decimal,int binario[9]){
	
	int aux,i,arrAux[9];
	
	for(i=0;i<9;i++){
	arrAux[i]=0;
	binario[i]=0;
	}
	
	aux = decimal;
	
	for(i=0;decimal!=0;i++){
	arrAux[i] = decimal%2;		//RESIDUO
	decimal = decimal/2;
	}
	
	for(i=0;i<9;i++){
	binario[i]=arrAux[i];   	
	}
	
	for(i=0;i<9;i++){
	printf("%d\t",binario[i]);	//IMPRIME 
	}
	printf("\n");
	
	
}

int posicionPrimerUno(int polinomio[15]){
	
	int i;
	
		for(i=0;i<15;i++){
			if(polinomio[i] == 1){
				break;
			}
		}
	
	return i;
}

void DecimalBinarioAux(int decimal,int binario[15]){
	
	int aux,i,arrAux[15];
	
	for(i=0;i<15;i++){
	arrAux[i]=0;
	binario[i]=0;
	}
	
	aux = decimal;
	
	for(i=0;decimal!=0;i++){
	arrAux[i] = decimal%2;		//RESIDUO
	decimal = decimal/2;
	}
	
	for(i=0;i<15;i++){
	binario[14-i]=arrAux[i];   	//INVIERTE EL ARREGLO
	}
	
	for(i=0;i<15;i++){
	printf("%d\t",binario[i]);	//IMPRIME 
	}
	printf("\n");
	
	
}