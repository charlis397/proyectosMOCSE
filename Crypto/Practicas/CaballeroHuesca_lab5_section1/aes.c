#include "fun.h"


int main(int argc, char* argv[]){
	
	char *fileResultado = argv[1];
	
	
	int op,i,j,k,h;
	int longitud,rondas;
	unsigned char mClave[4][4]; //matriz 4x4 clave
	unsigned char llave[16];	
	unsigned char mClaveExp[176];
	
	printf("Seleccione el tamnio de la llave\n");
	printf("1.- 128 \n2.-192\n3.-256\n");
	scanf("%d",&op);
	
	srand( time(NULL));
	
	switch (op){
		case 1:
		//****************GENERA LLAVE**************************
			longitud=16;
			rondas=44;
			llave[longitud];
			mClave[4][4]; 
			
			for(i=0;i<longitud;i++){
			llave[i]=rand();
			printf("%02x    ",llave[i]);
			}
		//****************MATRIZ ESTADO**************************
			for ( i = 0; i < 4; i++){
				for ( j = 0; j < 4; j++){
            	mClave[j][i] = llave[i*4 + j];		// convierte clave a cifrar en una matriz 4X4
							
            }	
    	}
		printf("\n\n\n\n");
		
			for ( i = 0; i < 4; i++){
				for ( j = 0; j < 4; j++){
            	printf("%02x	",mClave[i][j] ); // IMPRIME una matriz 4X4
            }
			printf("\n");
    	}
		
		//**********************IMPRIME LAS SUBCLAVES EN UN ARCHIVO*********************
		
		
		expandirClave(fileResultado,mClave, mClaveExp);
		break;
		
		
		
		
		
		
		case 2:
		//****************GENERA LLAVE**************************
			longitud=24;
			rondas=44;
			llave[longitud];
			mClave[6][4]; 
			
			for(i=0;i<longitud;i++){
			llave[i]=rand();
			printf("%02x    ",llave[i]);
			}
		//****************MATRIZ ESTADO**************************
			for ( i = 0; i < 4; i++){
				for ( j = 0; j < 6; j++){
            	mClave[j][i] = llave[i*6 + j];		// convierte clave a cifrar en una matriz 4X4
							
            }	
    	}
		printf("\n\n\n\n");
		
			for ( i = 0; i < 6; i++){
				for ( j = 0; j < 4; j++){
            	printf("%02x	",mClave[i][j]); // IMPRIME una matriz 6X4
				
            }
			
			printf("\n");
    	}
		
		//**********************IMPRIME LAS SUBCLAVES EN UN ARCHIVO*********************
		
		
		//expandirClave(fileResultado,mClave, mClaveExp);
		break;
		
		
		
		
		
		
		
		
		case 3:
		//GENERA LLAVE
			longitud=32;
			llave[longitud];
			
			for(i=0;i<longitud;i++){
			llave[i]=rand();
			printf("%02x    ",llave[i]);
			}		
			
		break;
		
		default:
			printf("Opcion ninguna\n");
		break;
	}
	
	
	
	return 0;
}

