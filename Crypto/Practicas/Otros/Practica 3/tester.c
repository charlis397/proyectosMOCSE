#include <stdio.h>
#include <stdlib.h>

char *fileK;
char *fileC;
char *fileP;

int main(int argc, char* argv[]){
	FILE* fichero; 
	fileK = argv[1];
	fileP = argv[2];
	fileC = argv[3];
	char arreglo[50],aux[50]; 
	char lee; 
	int i,j,longitud,NoBloques,k,l,m,n,p,q; 
	
	fichero = fopen("Chuviduvi.txt", "r");
	
	printf("Tamanio Permutacion: ");
	scanf("%d",&longitud);
	
	//GUARDA contenido en un ARREGLO y CUENTA sus caracteres
	
	i = 0; 
	
	while( fscanf(fichero, "%c", &lee) != EOF ){ 
	
	arreglo[i] = lee; 
	i++; 
	
	} 
	
	fclose( fichero ); 
	
	//CALCULA el número de BLOQUES necesarios
	
	NoBloques=i/longitud ;	//Donde i: Número de Caracteres
	
	if(i%longitud!=0){
		NoBloques++;
	}
	
	
	//DIVIDIMOS palabra en BLOQUES
	m=0;
	//m=longitud-(i-(longitud%i));
	m=longitud-(i%longitud);
	printf("Numero de Huecos %d\n",m);
		
	for(k=0;k<i;k++){
		if(k%longitud==0){
			printf("\n");
		}
		printf("%c",arreglo[k]);
	
	}
		if(k%longitud!=0){	//2**n
		for(l=0;l<m;l++){
		printf("a");
		}
		}
		
		
		/*
	for(n=0;n<NoBloques;n++){
			for(p=0;p<longitud;p++){
				printf("%c\n",arreglo[n]);
			}
			
		}
		/*
		for(q=0;q<i;q++){
			printf("%c",aux[q]);
		}
		*/
	//IMPRIMIMOS LOS DATOS
	printf("\n\nTotal de caracteres:%d\n",i);
	printf("Bloques Necesarios:%d\n",NoBloques);
	printf("Contenido del arreglo:\n"); 
	for(j = 0; j < i; j++) {
	printf("arreglo[%d]%c\n", j,arreglo[j]); 
	}
	return 0; 
	
} 