#include <stdio.h>
#include <stdlib.h>
int main(){ 
	FILE* fichero; 
	char arreglo[50]; 
	char lee,iv[50],xorf[50]; 
	int i,j,longitud,NoBloques,k,l,m,h,o; 
	
	fichero = fopen("Chuviduvi.txt", "r");
	
	printf("Tamanio Permutacion: ");
	scanf("%d",&longitud);
	
	//Vecor IV
	srand(time(NULL));
	for(h=0;h<longitud;h++){
		iv[h]=rand()%26;
		printf("%c",iv[h]+64);
	}
	printf("\n");
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
	//IMPRIMIMOS LOS DATOS
	printf("\n\nTotal de caracteres:%d\n",i);
	printf("Bloques Necesarios:%d\n",NoBloques);
	printf("Contenido del arreglo:\n"); 
	for(j = 0; j < i; j++) {
	printf("arreglo[%d]%c\n", j,arreglo[j]); 
	}
	//xor entre vectores
	/*for(o=0;0<longitud;o++){
		if(o==0){
		xorf[o]=(iv[o]%5);
		}
	}*/
	printf("%c",'x'^'y');
	return 0; 
	
} 
