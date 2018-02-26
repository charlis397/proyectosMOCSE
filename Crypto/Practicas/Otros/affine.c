/*Lab01_3cv1 - 2.1
Santiago Mancera Arturo Samuel
23/08/16
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
void obt_textC(char *archivo, char *textC);
void descifra(char *textC);


int main(int argc, char *argv[]){
	char origen[50];
	char textoC[100];
	int i;

	strcpy(origen,argv[1]);
	obt_textC(origen,textoC);
	for(i=0;textoC[i]!='\0';i++){
		printf("%c",textoC[i]);
	}
	printf("\n");

	descifra(textoC);

	return 0;
}


void obt_textC(char *archivo, char *textC){
	FILE *origen;
	char c;
	int i=0;

	if((origen=fopen(archivo,"r"))==NULL){
		printf("Error al leer el archivo\n");
		exit(1);
	}

	while(!feof(origen)){
		c=fgetc(origen);
		if(c>=65&&c<=91){
			textC[i]=c;
			i++;
		}else
			break;
	}


	fclose(origen);
	return;
}

void descifra(char *textC){
	int a[]={1,3,5,7,9,11,15,17,19,21,23,25};
	int b=0,i=0,j=0;
	int caracter1,caracter2;

	for(b=0;b<26;b++){
		for(i=0;i<12;i++){
			printf("a=%d  b=%d\n",a[i],b);
			for(j=0;textC[j]!='\0';j++){
				caracter1=(a[i]*(textC[j]-65))-b;
				if(caracter1<0){
					caracter1=26-caracter1;
				}
				caracter2=(caracter1%26);
				printf("%c",caracter2+65);
			}
			printf("\n\n");
		}
	}
	return;
}

/*
a=15 b=9 - c1-171.txt
./a.out c1-171.txt > salida1.txt
*/