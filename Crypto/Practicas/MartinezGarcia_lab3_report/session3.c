/*CARLOS EDUARDO CABALLERO HUESCA
 BRANDO JOSUE MARTINEZ GARCIA
 Group:3CV1
*/
#include "fun.h"

int main(int argc, char* argv[]){
	
	fileLlave = argv[1];
	fileTextoPlano = argv[2];
	fileTextoCifrado = argv[3];
	
	int longitud,i,NoBloques;
	
	
	printf("Ingresa la longitud de los bloques:");
	scanf("%d",&longitud);
	
	generarLLave(longitud,fileLlave);
	leerTextoClaro(fileTextoPlano);
	i=totalCaracteres(fileTextoPlano);
	printf("\n\nTotal de caracteres:%d\n",i);
	NoBloques=numeroBloques(i,longitud);
	printf("\n\nTotal de Bloques a usar:%d\n",NoBloques);
	divisionPorBloques(longitud,i,fileTextoPlano);


return 0;
}