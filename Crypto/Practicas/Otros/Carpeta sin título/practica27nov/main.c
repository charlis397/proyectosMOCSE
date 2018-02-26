#include<stdio.h>
#include<stdlib.h>
#include"metodo.h"

int main()
{
	int primo, entero_a, entero_x;
	int a_modulo;
	
	printf("Ingrese el valor a: \n");
	scanf("%d", &entero_a);
	printf("Ingrese el valor x: \n");
	scanf("%d", &entero_x);
	printf("Ingrese el valor primo: \n");
	scanf("%d", &primo);
	
	a_modulo = powmod(entero_a, entero_x, primo);
	printf("%d \n", a_modulo);

}
