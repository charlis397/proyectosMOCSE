#include <stdio.h>
#include "shiftcipher.h"

/*
Sánchez Cuna Eduardo odin
Martinez Bautista Raquel
*/

int main()
{
	FILE *plain;
	char *plaintext;
	int key;
	
	plain = fopen("text2.txt", "r");
	
	printf("Ingrese su llave: \n");
	scanf("%d", &key);
	
	printf("Texto a encriptar: \n");
	while(feof(plain) == 0)
	{
		fgets(plaintext, 100, plain);
		printf("%s", plaintext);
	}
	
	encriptar(plaintext, key);
	
}
