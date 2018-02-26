/*
	Program By
	Elizalde Flores Berenice 
        Martinez Bautista Raquel Ariadna
        Sánchez Cuna Eduardo Odin
        Urban Reyes Adan
*/
#include<stdio.h>
#include<stdlib.h>
#include"metodos.h"

int main()
{
	int bit_leng = 0, llave_publica = 0;
	
	printf("Ingrese el tamaño de bits: \n");
	scanf("%d", &bit_leng);
	
	llave_publica = public_key_RSA(bit_leng);
	
}
