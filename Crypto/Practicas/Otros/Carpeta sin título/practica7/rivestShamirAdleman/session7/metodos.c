/*
Martinez Bautista Raquel Ariadna
Sánchez Cuna Eduardo Odin
*/
#include<stdio.h>
#include<stdlib.h>
#include"metodos.h"
#include<sys/types.h>
#include<sys/stat.h>
#include<fcntl.h>
#include<math.h>

int euclides_algorithm(int a, int b)
{
	int u = a, v = b, x1 = 1, y1 = 0, x2 = 0, y2 = 1, x = 0, y = 0, r = 0, q = 0;
	while(u != 0)
	{
		q = v/u;
		r = v-(q*u);
		x = x2 - (q*x1);
		y = y2 - (q*y1);
		v = u;
		u = r;
		x2 = x1;
		x1 = x;
		y2 = y1;
		y1 = y;
	}
	int d = v;
	x = x2;
	y = y2;
	return x;
}

int public_key_RSA(int tam_bit)
{
	int descriptor = open("primos.txt", O_RDONLY);
	char c;
	int azar = 0;
	char arreglo[6542];
	int nbl = 1;
	while(nbl != 0)
	{
		nbl = read(descriptor, &c, sizeof(char));
		arreglo[azar] = c;
		azar++;
	}	
	char valor_aleatorio1, valor_aleatorio2;
	int inverso = 0;
	int random1 = rand()%6542;
	int random2 = rand()%6542;
	valor_aleatorio1 = arreglo[random1];
	valor_aleatorio2 = arreglo[random2];
	printf("%c, %c", valor_aleatorio1, valor_aleatorio2);
	//inverso = euclides_algorithm(valor_aleatorio1, valor_aleatorio2);
}
