/*
	Practica 1
	Ejercicio 1
*/
#include <stdio.h>
#include <stdlib.h>

int inverso(int x,int m)
{
	int i;
	for(i=1; i<m; i++)
	{
		if( (x*i)%m ==1)
			return i;
	}
}

char encriptar(char aux,int m,int a,int b){//funcion que encripta un caracter dados los parametros de cifrado
	char letra_c;
		if(aux<65||aux>90){
			letra_c=aux;
		}
		else{
			letra_c=((a*(aux-65)+b)%m)+65;
		}
		return letra_c;
}

char desencriptar(char aux,int m,int a,int b){//funcion que desencripta un caracter dados los parametros de cifrado
	char letra_c;
		if(aux<65||aux>90){//Si se recibe un caracter que no pertenezca al abecedario que no lo modifique
			letra_c=aux;
		}
		else{
			letra_c = (inverso(a,m)*((aux-65)-b))%m;
			if(letra_c<0){
				letra_c=letra_c+26+65;
			}
			else{
				letra_c=letra_c+65;
			}
		}
		return letra_c;
}

int gcd(int x,int y){
	int c;
	while(x!=0){
		c=x;
		x=y%x;
		y=c;
	}
	return y;
}

int main()
{
	FILE *entrada;//archivo de entrada
	FILE *salida;//archivo de salida
	char caracter,caracter_c;//auxiliar para caracteres
	int a,b,m,opc;//parametros de cifrado y opcion de cifrar o descifrar
	m=26;
	printf("Quieres cifrar(1) o descifrar(*): ");
	scanf("%d",&opc);
	printf("\nIntroduce a:");
	scanf("%d",&a);
	printf("\nIntroduce b:");
	scanf("%d",&b);
	
	if(gcd(a,m)!=1){ //si no son coprimos
		printf("\nValor de 'a' no valido.");
		exit(0);
	}
	
	entrada = fopen("entrada.txt","r");
	salida = fopen("salida.txt","w");
	
	if (entrada == NULL){
		printf("\nError de apertura del archivo. \n\n");
        }
        else{
	    	while ((caracter=fgetc(entrada))!=EOF)
	    	{
			
        		if(opc==1){
        			caracter_c=encriptar(caracter,m,a,b);
        			printf("%c", fputc(caracter_c, salida));
        		}
        		else{
        			caracter_c=desencriptar(caracter,m,a,b);
        			printf("%c", fputc(caracter_c, salida));
        		}
	    	}
        }
        fclose(entrada);
        fclose(salida);
	return 0;
}
