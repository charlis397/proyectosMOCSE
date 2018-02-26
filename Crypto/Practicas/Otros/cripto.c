#include<stdio.h>
#include<stdlib.h>


int main(int argc, char* argv[]){
	int i, j, l;
	char *archivoSalida = argv[1];
	FILE *fp;
		printf("%s\n", archivoSalida);
	
	
	printf("Ingrese el tamanio de la permutacion\n");
	scanf("%d",&l);
	char c[l];
	srand(time(NULL));
	for(i=0;i<l;i++){
		
		c[i]=(rand()%26)+65;
		printf("%c",c[i]);
	}
	
////DASJFKSDDK

	fp = fopen ( archivoSalida , "w+" );


	for(j=0;j<l;j++){

		fprintf(fp,"%c",c[j]);
	}
	
	fclose(fp);
		
		//ddsfdds
}
