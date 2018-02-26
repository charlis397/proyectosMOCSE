#include "hill.h"
#include <stdio.h>
#include <stdlib.h>

int *crear_matriz()
{
	int *matriz_creada=(int*)malloc(3*3*sizeof(int));
	return matriz_creada;
}

void guardar_valor(int *matriza, int i, int j, int valor){
	int k;
	k=i*3+j;
	matriza[k]=valor;
}

int det_matriz(int *matriz){
	int det, modulo;
	det=(((matriz[0]*matriz[4]*matriz[8])+(matriz[3]*matriz[7]*matriz[2])+(matriz[6]*matriz[1]*matriz[5]))-((matriz[6]*matriz[4]*matriz[2])	
	+(matriz[0]*matriz[7]*matriz[5])+(matriz[3]*matriz[1]*matriz[8])));
	if(det>0)
	{
		modulo = det%26;
		return modulo;
	}
	else
	{
		modulo = det%26;
		modulo = 26+modulo;
		return modulo;
	}
}

int tiene_inversa(int *matriz){
	int i,det;
	int Z[]={1,3,5,7,9,11,15,17,19,21,23,25};
	det=det_matriz(matriz);
	for(i=0;i<12;i++){
		if(det==Z[i])
			return 1;
	}
	return 0;
}

int *matriz_cof(int *matriz)
{
	int *matriz_cofactor= (int*) malloc(3*3*sizeof(int));
	matriz_cofactor[0]=((matriz[4]*matriz[8])-(matriz[7]*matriz[5]));
	matriz_cofactor[1]=(-((matriz[3]*matriz[8])-(matriz[6]*matriz[5])));
	matriz_cofactor[2]=((matriz[3]*matriz[7])-(matriz[6]*matriz[4]));
	matriz_cofactor[3]=(-((matriz[1]*matriz[8])-(matriz[7]*matriz[2])));
	matriz_cofactor[4]=((matriz[0]*matriz[8])-(matriz[6]*matriz[2]));
	matriz_cofactor[5]=(-((matriz[0]*matriz[7])-(matriz[6]*matriz[1])));
	matriz_cofactor[6]=((matriz[1]*matriz[5])-(matriz[4]*matriz[2]));
	matriz_cofactor[7]=(-((matriz[0]*matriz[5])-(matriz[3]*matriz[2])));
	matriz_cofactor[8]=((matriz[0]*matriz[4])-(matriz[3]*matriz[1]));
	return matriz_cofactor;
}

int *matriz_cof_traspuesta(int *matriz_cof)
{
	int i, j, kt, kc;
	int *matriz_cofactor_traspuesta= (int*) malloc(3*3*sizeof(int));
	for(i = 0; i < 3; i++)
	{
		for(j = 0; j < 3; j++)
		{
			kt = j*3 +i;
			kc = i*3 +j;
			matriz_cofactor_traspuesta[kt] = matriz_cof[kc];
		}
	}
	return matriz_cofactor_traspuesta;
}

int inverso_multiplicativo(int *matriz)
{
	int determinante, inverso_multi, incremento = 0, com_inv;	
	determinante=det_matriz(matriz);
	int Z[12]={1,3,5,7,9,11,15,17,19,21,23,25};
	do
	{
		com_inv = Z[incremento] * determinante;
		com_inv = com_inv%26;
		inverso_multi = Z[incremento];
		incremento++;
	}while(com_inv != 1);
	return inverso_multi;
}

int *matriz_inv(int *matrix_cof_tras, int add_inverse)
{
	int *matriz_inverse=(int*)malloc(3*3*sizeof(int));
	int l;
	for(l=0; l<9; l++){
		matriz_inverse[l]=((matrix_cof_tras[l]*add_inverse)%26);
		if(matriz_inverse[l] < 0)
		{
			matriz_inverse[l] = 26+matriz_inverse[l];
		}
	}
	return matriz_inverse;
}

int *inversa(int *matriz)
{
	int l, inv_mult;
	int *matriz_cofactores= (int*) malloc(3*3*sizeof(int));
	int *matriz_cofactores_traspuesta= (int*) malloc(3*3*sizeof(int));
	int *matriz_inversa=(int*)malloc(3*3*sizeof(int));
	matriz_cofactores = matriz_cof(matriz);

	matriz_cofactores_traspuesta = matriz_cof_traspuesta(matriz_cofactores);

	inv_mult = inverso_multiplicativo(matriz);
	
	matriz_inversa = matriz_inv(matriz_cofactores_traspuesta, inv_mult);

	return matriz_inversa;
}

int *matriztexto(char *plaintext)
{
	int tam,i,j,k,l,valor;
	int *matrizplaintext;
	tam=sizeof(plaintext);
	char plaintext[tam], alphabet[26]={A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z};
	for(i=0;i<tam;i++)
	{
		for(j=0;j<26;j++)
		{
			if(plaintext[i]==alphabet[j])
			{
				for(k=0;k<3;k++)
				{
					for(l=0;l<3;l++)
					{
						valor=j;
						matrizplaintext=crear_matriz;
						matrizplaintext=guardar_valor(matrizplaintext,k,l,valor);
					}
				}
			}
		}
	}
	return matrizplaintext;	
}


