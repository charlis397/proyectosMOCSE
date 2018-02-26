#include "fun.h"

int main(int argc, char* argv[]){
	
	int i, j, lon;
	char h, *archivoSalida = argv[1];
	
	FILE *fp;
	FILE *fp1;
	printf("%s\n", archivoSalida);
	
	srand(time(NULL));
	
	printf("Ingrese el tamanio de la permutacion\n");
	scanf("%d",&lon);
	
	char IV[lon];

	
	//	Creamos vector IV
	for(i=0;i<lon;i++){
		
		IV[i]=(rand()%26)+65;
		printf("%c",IV[i]);
		
	}
	printf("\n");
	
	//	Imprimir Archivo
	
	fp = fopen ( archivoSalida , "w+" );


	for(j=0;j<lon;j++){

		fprintf(fp,"%c",IV[j]);
	}
	
	fclose(fp);
	
	
	//imprimirArchivo(archivoSalida, IV);
	//	Leer Archivo
	
	fp1=fopen("Chuviduvi.txt","r");
	while(feof(fp1)==0){
			h=getc(fp1);
			printf("%c",h);
		}
	fclose(fp1);
	
}