/*
	Practica 2 Ejercicio 1
	
	Integrantes:
		Martínez Galindo Angélica
		Montaño Castañeda Daniel
	Grupo:
		3CV2
	Fecha:
		29 de Febrero del 2016
	Compilación:
		(Windows) gcc practica2_1.c practica2.c -o practica2_1
	Ejecución:
		(Windows) practica2_1

*/

#include "practica2.h"

int main()
{
	int opcion,salir=0,m;
	char texto_plano[20];
	char texto_cifrado[20];
	char texto_llaves[20];
	char texto_descifrado[20];

	while(salir == 0) //mientras el usuario no seleccione la opcion de salir se mostrara el siguiente menú
	{
		printf("\n Elija una de las siguientes opciones: ");
		printf("\n  1) Generar de llaves");
		printf("\n  2) Cifrar");
		printf("\n  3) Descifrar");
		printf("\n  4) Salir \n");
		scanf("%d",&opcion);

		if(opcion == 1)
		{
			printf("\n Escriba la dimension de la matriz (2 o 3) \n");
			scanf("%d",&m);
			if(m==2 || m==3)
				generarLlave(m); //generamos una llave de numeros aleatorios de la dimension "m"
			else
			{
				printf("\n Solo de permiten matrices de 2 o 3 dimensiones \n");
				exit(0);
			}

		}
		else if(opcion==2)
		{
			printf("\n Escriba el archivo que contiene el texto a cifrar: ");
			scanf("%s",texto_plano);
			printf("\n Escriba el archivo que contiene la llave: ");
			scanf("%s",texto_llaves);
			printf("\n Escriba el archivo donde se guardara el texto cifrado: ");
			scanf("%s",texto_cifrado);

			cifrar(texto_plano,texto_llaves,texto_cifrado);
		}
		else if(opcion==3)
		{
			printf("\n Escriba el archivo que contiene el texto a descifrar: ");
			scanf("%s",texto_plano);
			printf("\n Escriba el archivo que contiene la llave: ");
			scanf("%s",texto_llaves);
			printf("\n Escriba el archivo donde se guardara el texto descifrado: ");
			scanf("%s",texto_descifrado);

			descifrar(texto_plano,texto_llaves,texto_descifrado);
		}
		else if(opcion==4)
		{
			salir++;
		}
		else
		{
			printf("\n Elija una de las opciones validas \n");
			exit(0);
		}
	
	}
	

	return 0;

}

