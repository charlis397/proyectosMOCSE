#include <stdio.h>
#include <stdlib.h>
#include "hill.h"
/*Martínez Baitista Raquel Ariadna
Sánchez Cuna Eduardo Odin*/

int main()
{
	int valor,i,j,val,invers, prueba, value;
	int *matriz;
	char *message = (char*)malloc(3*1*sizeof(char));
	int *matriz_textoclaro = (int*)malloc(3*3*sizeof(int));
	int *matriz_prueba=(int*)malloc(3*3*sizeof(int));
	char *matriz_proof;
	matriz = crear_matriz();
 	/*for(i=0;i<3;i++)
 	{
 		for(j=0;j<3;j++)
 		{
			printf("Ingrese los datos de la matriz: \n");
 			scanf("%d",&val);
 			guardar_valor(matriz,i,j,val);
 		}
	}*/
	invers=tiene_inversa(matriz);
	printf("%d\n",invers);
	matriz_prueba = inversa(matriz);
	for(prueba = 0; prueba < 9; prueba++)
	{
		printf("%d\n", matriz_prueba[prueba]);
	}
	fscanf("mensaje", message);
	printf("%c", message);
	FILE *mensaje;
	mensaje=fopen("plaintext.txt","r");
	matriz_textoclaro=matriztexto(mensaje);
}


