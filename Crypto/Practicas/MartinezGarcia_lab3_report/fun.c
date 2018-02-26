/*CARLOS EDUARDO CABALLERO HUESCA
 BRANDO JOSUE MARTINEZ GARCIA
 Group:3CV1
*/

#include "fun.h"

void generarLLave(int longitud, char archivoLlave[]){ 	/*Parametros para generar la llave, Longitud y el archivo
															donde se va guardar la llave*/
	FILE *llave;									
	int permutacion[longitud],i,j,aux,d;
	
	srand(time(NULL));
	
	for (i=0;i<longitud;i++){							//Permutacion de la llave
		aux=1 + rand() % longitud;						
		d= 0;
		for (j=0; j<=i;j++){
			if (aux == permutacion[j]){					//Comparamos si aux es igual a la posicion en j , si es así
				d=1;									//es porque tenemos duplicados
			}
		}
		if (d==1){										//Si tenemos duplicados decrementamos i en 1
			i--;
		}
		else{
			permutacion[i] = aux;						//A la permutacion en la posición i le asignamos el valor de aux
		}
	}
	
	llave = fopen(archivoLlave,"w");					//Escribimos en el archivo el resultado
	for(i=0;i<longitud;i++){
		fprintf(llave,"%d ",permutacion[i]);		
	}
	
	fclose(llave);
	printf("\nSe genero con EXITO la llave en el archivo: %s\n\n",archivoLlave);
}

void leerTextoClaro(char textoClaro[]){ //GUARDA contenido en un ARREGLO y CUENTA sus caracteres
	
	FILE *texto;
	int i=0,j;
	char arreglo[500],lee; 
	
	texto = fopen(textoClaro, "r");		//Abrimos el archivo de textoClaro y lo leemos
	
	while( fscanf(texto, "%c", &lee) != EOF ){ 	//Guardamos en un arreglos su contenido y contamos los caracteres que hay.
	arreglo[i] = lee; 
	i++; 
	} 
	
	fclose( texto );
	
	printf("El mensaje del texto en plano es:\n");
	for(j=0;j<i;j++) {
	printf("%c",arreglo[j]); 
	}
	
	}

int totalCaracteres(char textoClaro[]){
	FILE *texto;
	int i=0;
	char arreglo[500],lee; 
	
	texto = fopen(textoClaro, "r");
	
	while( fscanf(texto, "%c", &lee) != EOF ){ 
	i++; 
	} 
	
	fclose( texto );
	
	return i;
}
	
int numeroBloques(int i, int longitud){
	int NoBloques;
	
	NoBloques = i/longitud;				//Determinamos el número de bloques a usar.
	
	if(i%longitud!=0){					//Si el total de caracteres modulo longitud es diferente de 0
		NoBloques++;					// Incremento en 1 a NoBloques
	}
	
	return NoBloques;
}

void divisionPorBloques(int longitud,int i,char textoClaro[]){
	
	FILE *Bloques;
	FILE *texto;
	Bloques = fopen("Bloques.txt","w");
	int huecos =0,j,k,b=0;
	char arreglo[500],aux[500],lee;
	
	texto = fopen(textoClaro, "r");
	while( fscanf(texto, "%c", &lee) != EOF ){ 
	aux[b] = lee; 
	b++; 
	} 
	
	
	
	huecos=longitud-(i%longitud);			//Determinamos los espacios en blanco que se tienen despues de dividir
											//el mensaje en bloques
	for(j=0;j<i;j++){
		if(j%longitud==0){
			printf("\n");
		}
		printf("%c",aux[j]);				//Separamos por bloques dependiendo de la cantidad de caracteres
		
	
	}
		if(j%longitud!=0){	//2**n
		for(k=0;k<huecos;k++){				//En los espacios en blanco restante colocamos una X
		printf("x");
		}
		}
		
	
	fclose(Bloques);
	fclose( texto );
		
}