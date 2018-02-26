/*
	Practica 2 Ejercicio 2
	
	Integrantes:
		Martínez Galindo Angélica
		Montaño Castañeda Daniel
	Grupo:
		3CV2
	Fecha:
		29 de Febrero del 2016
	Compilación:
		(Windows) gcc practica2_2.c practica2.c -o practica2_2
	Ejecución:
		(Windows) practica2_2

*/

#include "practica2.h"

int main()
{
	FILE *texto;//archivo de entrada
	FILE *texto_c;//archivo de entrada
	char texto_plano[20];
	char texto_cifrado[20];
	int m=26;
	
	printf("\n Escriba el archivo que contiene el texto a cifrar: ");
	scanf("%s",texto_plano);
	printf("\n Escriba el archivo donde se guardara el texto cifrado: ");
	scanf("%s",texto_cifrado);
	
	texto = fopen(texto_plano,"r");
	texto_c = fopen(texto_cifrado,"r");
	
	if (texto == NULL)
		printf("\nError de apertura del archivo. \n\n");
    if (texto_c == NULL)
		printf("\nError de apertura del archivo. \n\n");
    else
	{
		matriz mensaje=crearMatriz(2,2);
		matriz cipherText=crearMatriz(2,2);
		
		int i,j;
		//Ciclos para el llenado de la matriz mensaje
		for(i=0; i<2; i++)
		{
			for(j=0; j<2; j++)
			{
				mensaje[i][j]=(int)getc(texto)-65;
			}
		}
		//Ciclos para el llenado de la matriz cifrada
		for(i=0; i<2; i++)
		{
			for(j=0; j<2; j++)
			{
				cipherText[i][j]=(int)getc(texto_c)-65;
			}
		}
		mensaje=matrizAdjunta(mensaje,2);
		mensaje=matrizTranspuesta(mensaje,2,2);
		int det=determintanteMatriz(mensaje,2)%m;
		
		if(det<0)
			det=det+m;
		
		int det_i=inverso(det,m);
		mensaje=multiplicarMatrizConEscalar(det_i,mensaje,2,2);
		mensaje=matrizModulo(mensaje,2,2,m);
		matriz K=multiplicarMatrices(mensaje,2,2,cipherText,2,2);
		K=matrizModulo(K,2,2,m);
		printf("\n La llave usada para cifrar este texto es: \n");
		imprimeMatriz(K,2,2);
    }
		
	return 0;
}

