#include <stdio.h>
#include <stdlib.h>

#define N 3
#define MAX 100
#define MOD 26

int calcularDeterminante(int[N][N] );
void calcularInversa(int[N][N] , int[N][N]);
int sonCoprimos(int , int );
void imprimirArchivo(char* , int[N][N] , int[N][N]);
int calcularInversoModular(int, int); 

int main(int argc, char* argv[])
{
	char band = 1, *archivoSalida = argv[1];
	int llave[N][N], antiLlave[N][N];
	short i , j;
	int det;
	srand( time(NULL));	

	while( band != 0 )
	{
		//Creación de valores aleatorios para la llave
		for( i = 0 ; i < N ; i++ )
		{
			for( j = 0 ; j < N ; j++ )
			{
				llave [i][j] = rand()%MAX;
			}
		}
		
		det = calcularDeterminante(llave);

		//Convertimos el determinante a su congruente modular
		if ( det >= 0 )	{ det = det%MOD; }
		else		{ det = MOD - (-det)%MOD; }
		
		//Si no es cero y es coprimo
		if( (det != 0) && sonCoprimos( MOD, det ) )
			{ band = 0; }
	}

	calcularInversa(llave, antiLlave);
	imprimirArchivo(archivoSalida, llave, antiLlave);
	
	return 0;
}

//Calcula un determinante de tamaño 3x3
int calcularDeterminante(int llave[N][N])
{
	int i , acum = 0;

	for( i = 0 ; i < N ; i++ )
	{
		acum += llave[0][i] * llave[1][(i+1)%N] * llave[2][(i+2)%N];
	}

	for( i = N-1 ; i >= 0 ; i-- )
	{
		acum -= llave[0][i] * llave[1][(i+2)%N] * llave[2][(i+1)%N];
	}

	return acum;
}

//Determina si dos números son coprimos
int sonCoprimos(int a, int b)
{
	int c = a/b, r = a%b;

	while( r != 0 )
	{
		c = a/b;
		r = a % b;
		a = b;
		b = r;
	}
	
	if( a == 1 )
		{ return 1; }
	else
		{ return 0; }
}

//Calcula la inversa del cifrado Hill de una matriz 3x3
void calcularInversa(int llave[N][N] , int antiLlave[N][N])
{
	int i , j , det = calcularDeterminante(llave);

	//Convertimos el determinante a su congruente modular
	if ( det >= 0 )	{ det = det%MOD; }
	else		{ det = MOD - (-det)%MOD; }


	int invDet = calcularInversoModular( det , MOD );
	int intermedia[N][N];

	//Calculo de los cofactores de una matriz 3x3
	intermedia[0][0] = llave[1][1]*llave[2][2] - llave[1][2]*llave[2][1];
	intermedia[0][1] = llave[1][2]*llave[2][0] - llave[1][0]*llave[2][2];
	intermedia[0][2] = llave[1][0]*llave[2][1] - llave[1][1]*llave[2][0];

	intermedia[1][0] = llave[2][1]*llave[0][2] - llave[2][2]*llave[0][1];
	intermedia[1][1] = llave[2][2]*llave[0][0] - llave[2][0]*llave[0][2];
	intermedia[1][2] = llave[2][0]*llave[0][1] - llave[2][1]*llave[0][0];

	intermedia[2][0] = llave[0][1]*llave[1][2] - llave[0][2]*llave[1][1];
	intermedia[2][1] = llave[0][2]*llave[1][0] - llave[0][0]*llave[1][2];
	intermedia[2][2] = llave[0][0]*llave[1][1] - llave[0][1]*llave[1][0];

	//Calculamos la transpuesta
	for( i = 0 ; i < N ; i++ )
	{
		for( j = 0 ; j < N ; j++ )
		{
			antiLlave[i][j] = intermedia[j][i] * invDet;

			if ( antiLlave[i][j] >= 0 )	{ antiLlave[i][j] = antiLlave[i][j]%MOD; }
			else				{ antiLlave[i][j] = MOD - (-antiLlave[i][j])%MOD; }
		}
	}
}

//calcula el inverso modular de un número
int calcularInversoModular(int a, int m)
{
	//coeficiente de a y b respectivamente
	int c1 = 1;
	int c2 = -1*(m/a); 
	
	//coeficientes penultima corrida
	int t1 = 0;
	int t2 = 1;
	
	//residuo, asignamos 1 como condicion de entrada 
	int r = m%a;
	int x = a , y = r , c;
	
	while( r != 0 )
	{
		//cociente
		c = x / y;
		//residuo
		r = x % y;
		//guardamos valores temporales de los coeficientes
		//multiplicamos los coeficiente por -1*cociente de la division
		c1 *= -1*c;
		c2 *= -1*c;
		
		//sumamos la corrida anterior
		c1 += t1;
		c2 += t2;
		
		//actualizamos corrida anterior
		t1 = -1*( c1-t1 )/c;
		t2 = -1*( c2-t2 )/c;
		x = y;
		y = r;
	}

	//Si el residuo anterior es 1, son primos relativos y el inverso existe
	if( x == 1 )
	{
		//Si el inverso es negativo, regresamos ese número más el módulo
		if( t2 >= 0 )
		{
			return t2;
		}
		else
		{
			return t2 + MOD;
		}
	}
	else
		{ return -1; }
}

//Imprime en un nombre de archivo dado la llave y la antillave
void imprimirArchivo(char* archivoSalida, int llave[N][N], int antiLlave[N][N])
{
	int i, j;
	FILE *fp;
	
	printf("%s\n", archivoSalida);
	
 	fp = fopen ( archivoSalida , "w+" );
 	
	for( i = 0 ; i < N ; i++ )
	{
		for( j = 0 ; j < N ; j++ )
		{
			fprintf(fp,"%d\t", llave [i][j]);
		}
	}
	
	fprintf(fp,"\n");
	
	for( i = 0 ; i < N ; i++ )
	{
		for( j = 0 ; j < N ; j++ )
		{
			fprintf(fp,"%d\t", antiLlave [i][j]);
		}
	}
	
	fclose ( fp );
}


