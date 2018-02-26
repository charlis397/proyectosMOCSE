/*
----Martínez Bautista Raquel Ariadna
----Sánchez Cuna Eduardo Odin
*/
#include <stdio.h>
#include <stdlib.h>
#include "modulo.h"

int main()
{
	int a = 0, b = 0, n = 0, sum_mod = 0, res_mod = 0, mul_mod = 0, inv_add = 0, inv_mul = 0;
	printf("Ingrese valores \n");
	printf("Ingrese valor a: \n");
	scanf("%d", &a);
	printf("Ingrese valor b: \n");
	scanf("%d", &b);
	printf("Ingrese valor n: \n");
	scanf("%d", &n);
	sum_mod = suma_modulo(a, b, n);
	
	res_mod = resta_modulo(a, b, n);
	
	mul_mod = multiplicacion_modulo(a, b, n);
	
	inv_add = additive_inverse(a, n);
	
	inv_mul = multiplicative_inverse(a, n);
	
	printf("Suma modulo:              %d\n", sum_mod);
	printf("Resta modulo:             %d\n", res_mod);
	printf("Multiplicacion modulo:    %d\n", mul_mod);
	printf("Inverso aditivo a:        %d\n", inv_add);
	printf("Inverso multiplicativo a: %d\n", inv_mul);
}
