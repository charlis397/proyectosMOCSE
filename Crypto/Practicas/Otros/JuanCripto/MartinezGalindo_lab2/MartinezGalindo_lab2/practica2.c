/*
	Definicion de las funciones utilizadas
*/
#include "practica2.h"


int gcd(int a, int b) //calcula el maximo comun divisor de dos numeros
{
    if (a == 0)
        return b;
    return gcd(b%a, a);
}

int inverso(int x,int m) //calcula el inverso de un numero
{
	int i;
	for(i=1; i<m; i++)
	{
		if( (x*i)%m ==1)
			return i;
	}
}

//creamos una matriz de dimensiones filas X columnas
matriz crearMatriz(int filas, int columnas)
{
	int i,j;
	matriz m;
	m = (int **)malloc(filas*sizeof(int*)); //creamos el espacio en memoria
	
	for (i=0;i<filas;i++) 
		m[i] = (int*)malloc(columnas*sizeof(int));
	
	for(i=0; i<filas; i++)	//incializamos todos los elementos de la matriz con 0
	{
		for(j=0; j<columnas; j++)
			m[i][j] = 0;
	}
	return m;
}

//mandamos a imprimir la matriz
void imprimeMatriz(matriz m, int filas, int columnas) 
{
	int i,j;
	for(i=0; i<filas; i++)
	{
		for(j=0; j<columnas; j++)
		{
			printf("%d ",m[i][j]);
		}
		printf("\n");
	}
}

//Multiplicacion de dos matrices dadas con sus dimensiones correspondientes
matriz multiplicarMatrices(matriz a, int filas_a, int columnas_a, matriz b, int filas_b, int columnas_b)
{	
	int i,j,k;
	matriz m = crearMatriz(filas_a,columnas_b); //matriz resultante

	for(i=0; i<filas_a; i++)
	{
		for(j=0; j<columnas_b; j++)
		{
			for(k=0; k< filas_b; k++)
				m[i][j]= m[i][j]+a[i][k]*b[k][j];
		}
	}
	return m;
}

//Multiplicacion de una matricez con un escalar
matriz multiplicarMatrizConEscalar(int esc, matriz m, int filas, int columnas)
{
	int i,j;
	matriz Mult = crearMatriz(filas,columnas);	//matriz resultante
	for(i=0; i<filas; i++)
	{
		for(j=0; j<columnas; j++)
			Mult[i][j] = m[i][j]*esc;	//multiplicamos todos los elementos de la matriz por el escalar
	}

	return Mult;

}

//Transpuesta de una matriz
matriz matrizTranspuesta (matriz a, int filas, int columnas)
{
	int i,j;
	matriz m = crearMatriz(columnas,filas); //matriz resultante

	for (i = 0; i < filas; i++)
	{
		for(j = 0 ; j < columnas ; j++ )
			m[j][i] = a[i][j];	//volteamos los elementos de la matriz
	}
	return m;
      
}

//Adjunta de una matriz
matriz matrizAdjunta(matriz a,int dim)
{
	matriz adjunta = crearMatriz(dim,dim);	//matriz resultante
	
	if(dim == 2)	//Para cuando la matriz solo es de 2 dimensiones
	{
		adjunta[0][0] = a[1][1];
		adjunta[0][1] = -a[0][1];
		adjunta[1][0] = -a[1][0];
		adjunta[1][1] = a[0][0];
	}
	else if(dim == 3)//Para cuando la matriz es de 3 dimensiones
	{
		adjunta[0][0] = det(a[1][1],a[1][2],a[2][1],a[2][2]);
		adjunta[0][1] = -det(a[1][0],a[1][2],a[2][0],a[2][2]);
		adjunta[0][2] = det(a[1][0],a[1][1],a[2][0],a[2][1]);

		adjunta[1][0] = -det(a[0][1],a[0][2],a[2][1],a[2][2]);
		adjunta[1][1] = det(a[0][0],a[0][2],a[2][0],a[2][2]);
		adjunta[1][2] = -det(a[0][0],a[0][1],a[2][0],a[2][1]);

		adjunta[2][0] = det(a[0][1],a[0][2],a[1][1],a[1][2]);
		adjunta[2][1] = -det(a[0][0],a[0][2],a[1][0],a[1][2]);
		adjunta[2][2] = det(a[0][0],a[0][1],a[1][0],a[1][1]);
	}
	
	return adjunta;
}

//Sacamos modulo 26 a ada uno de los elementos de la matriz
matriz matrizModulo(matriz a, int filas, int columnas, int m)
{
	matriz mod = crearMatriz(filas,columnas);
	int i,j;
	
	for (i = 0; i < filas; i++)
	{
		for(j = 0 ; j < columnas ; j++ )
		{
			mod[i][j] = a[i][j] % m;
			if(mod[i][j] < 0) //en caso que tengamos un numero negativo
			{
				while(mod[i][j]<0)//si tenemos algun numero negativo le sumamos m hasta que se vuelva positivo
					mod[i][j] = mod[i][j]+m;
			}
		}
	}
	
	return mod;
}

//Calculamos la inversa de una matriz dada
matriz matrizInversa(matriz m,int dim,int determinante)
{
	int i,j;
	matriz inversa = crearMatriz(dim,dim);
	matriz adj = matrizAdjunta(m,dim);
	matriz trans =  matrizTranspuesta (adj,dim,dim);
	int inverso_det = inverso(determinante,26); //sacamos el inverso del determinante
	if(dim>2)
		inversa = multiplicarMatrizConEscalar(inverso_det,trans,dim,dim);//inversa de una matriz 2x2
	else
		inversa = multiplicarMatrizConEscalar(inverso_det,adj,dim,dim);//inversa de una matriz 3x3
	
	inversa = matrizModulo(inversa,dim,dim,26);
	
	return inversa;
}

//Determinante de elementos concretos de una matriz
int det(int a, int b, int c, int d)
{
	return (a*d-b*c);
}

//Determinante de una matriz de dimensiones cualesquiera
int determintanteMatriz(matriz a,int n) 
{
  	int det=0, p, h, k, i, j;
  	matriz temp = crearMatriz(n,n);//creamos una matriz temporal
	
  	if(n==1) 
    	return a[0][0];	//determinante de una matriz 1x1 
  	else if(n==2)
    	det=(a[0][0]*a[1][1]-a[0][1]*a[1][0]); //determinante de una matriz 2x2
  	else 
  	{
    	for(p=0;p<n;p++) 		//determinante de una matriz mayor de 2
    	{
	      	h = 0;
	      	k = 0;
		    for(i=1;i<n;i++) 
		    {
		        for( j=0;j<n;j++) 
		        {
		          	if(j==p) 
		           	 continue;
		          
		          	temp[h][k] = a[i][j];
		          	k++;
		          	if(k==n-1) 
		          	{
		           		h++;
		            	k = 0;
		          	}
		        }
      		}
      		det=det+a[0][p]*pow(-1,p)*determintanteMatriz(temp,n-1);	//funcion recursiva
    	}
  }

    free(temp);
 	return det;
}


//Funcion de cifrado
void cifrar(char* texto_plano,char* texto_llaves,char* texto_cifrado)
{
	
	FILE *entrada;
	FILE *llave;
	char ch;
	int caracter,i = 0,j=0;
	int dim,det,m=26,bandera = 0;
	matriz K,P,C,CM;
	

	entrada = fopen(texto_plano,"r+");
	llave = fopen(texto_llaves,"r+");

	dim = numeroDimensiones(texto_llaves);
	K = leerMatriz(texto_llaves);
	det = determintanteMatriz(K,dim);
	printf("\nK = \n");
	imprimeMatriz(K,dim,dim);
	
	if(det < 0)
	{
		while(det<0)
			det = det+m;
	}	
	

	if(entrada == NULL || llave == NULL)
	{
		printf("ERROR en apertura de archivos\n");
		exit(0);
	}
	else
	{	
		P = crearMatriz(dim,1);
		while(feof(entrada) == 0) //mientras haya elementos de nuestro texto a cifrar
		{
			if(j<dim)
			{
				P[j][0] = fgetc(entrada)-65;	//vamos metiendo los caracteres en sus equivalentes numeros a la matriz
				j++;
				
			}
			else
			{
				printf("\nP = \n");
				imprimeMatriz(P,dim,1);
				C = multiplicarMatrices(K,dim,dim,P,dim,1);	//multiplicacion K*P
				printf("\nC = \n");
				imprimeMatriz(C,dim,1);
				CM = matrizModulo(C,dim,1,m);//sacamos el modulo de (K*P) mod 26
				printf("\nCM = \n");
				imprimeMatriz(CM,dim,1);
				escribirMatriz(texto_cifrado,CM,dim,1);	//escribimos en el archivo
				P = crearMatriz(dim,1);
				j=0;
			}
		}
		
	}
	
	free(K);	//liberamos de la memoria
	free(C);
	free(P);
	
	fclose(entrada);
	fclose(llave);
	
}

//Funcion de cifrado
void descifrar(char* texto_cifrado,char* texto_llaves,char* texto_descifrado)
{
	FILE *entrada;
	FILE *llave;
	char ch;
	int caracter,i = 0,j=0;
	int dim,det,m = 26,bandera = 0;
	matriz K,KI,P,C,CM;
	
	entrada = fopen(texto_cifrado,"r+");
	llave = fopen(texto_llaves,"r+");

	dim = numeroDimensiones(texto_llaves);
	K = leerMatriz(texto_llaves);
	det = determintanteMatriz(K,dim)%m;
	
	if(det < 0)
	{
		while(det<0)
			det = det+m;
	}
	printf("\ndeterminante de K = %d",det);
	KI = matrizInversa(K,dim,det);
	printf("\ninversa de K = \n");
	imprimeMatriz(KI,dim,dim);
	
	if(entrada == NULL || llave == NULL)
	{
		printf("ERROR\n");
		exit(0);
	}
	else
	{	
		P = crearMatriz(dim,1);
		while(feof(entrada) == 0)
		{
			if(j<dim)
			{
				P[j][0] = fgetc(entrada)-65;
				j++;

			}
			else
			{
				printf("\nP = \n");
				imprimeMatriz(P,dim,1);
				C = multiplicarMatrices(KI,dim,dim,P,dim,1);
				printf("\nC = \n");
				imprimeMatriz(C,dim,1);
				CM = matrizModulo(C,dim,1,m);
				printf("\nCM = \n");
				imprimeMatriz(CM,dim,1);
				escribirMatriz(texto_descifrado,CM,dim,1);
				P = crearMatriz(dim,1);
				j=0;
			}
		}
	
	}

	free(K);//liberamos de la memoria
	free(C);
	free(P);

	fclose(entrada);
	fclose(llave);

	
}

//Generamos una llave aleatoria de dimensiones m X m dada por el usuario
void generarLlave(int dimension)
{
	matriz M;
	M = crearMatriz(dimension,dimension);
	FILE *llave;
	llave = fopen("llave.txt","w+"); //dicha llave se guardara en el archivo de texto llave.txt
	int i,j,det,bandera = 0;
	srand (time(NULL));
	
	while(bandera == 0)
	{
		for(i=0; i < dimension; i++)
		{
			for(j=0; j < dimension; j++)
				M[i][j]= rand()%260;	//generamos numeros aleatorios
		}
		
		det = determintanteMatriz(M,dimension);	 
		//validacion de llave	
		if(gcd(det,26)==1 && det%26 !=0) //el determinante de la matriz debe de ser diferente de 0 y el mcd del determinante con 26 debe ser 1
		{
			for(i=0; i < dimension; i++)
			{
				for(j=0; j < dimension; j++)
					fprintf(llave,"%d\n",M[i][j]);	//escribimos en el archivo
			}
			
			bandera++;
		}
	}
	
	fclose(llave);
	
}

//Mandamos a escribir los elementos de la matriz a un archivo de texto 
void escribirMatriz(char* nombre_arch,matriz m,int filas,int columnas)
{
	FILE *arch;
	arch = fopen(nombre_arch,"a+");
	int i,j;

	if(arch == NULL)
		printf("\n Error");
	else
	{
		for(i=0; i < filas; i++)
	    {
			for(j=0; j < columnas; j++)
			{
				fprintf(arch,"%c",65+m[i][j]);	//escribimos en el archivo
				printf("%c",65+m[i][j]);
			}
				
	    }	
	}

	fclose(arch);
}

//Leemos de un archivo de texto los elementos de una matriz y devolvemos dicha matriz
matriz leerMatriz(char* archivo)
{
	FILE *arch;
	int dim,j,i,num;
	arch = fopen(archivo,"r");
	
	dim = numeroDimensiones(archivo);
	matriz K = crearMatriz(dim,dim);
	
	if(arch == NULL)
		printf("\n Error");
	else
	{
		for(i=0; i < dim; i++)
	    {
			for(j=0; j < dim; j++)
			{
				fscanf(arch,"%d",&num);	//leemos y guardamos en num
				K[i][j] = num;
			}
	    }	
	}	
	fclose(arch);
	
	return K;
	
}

//Devolvemos el numero de dimensiones basandonos en el numero de elementos que contenga el archivo
int numeroDimensiones(char* nombre_arch)
{
	FILE *arch;
	arch = fopen(nombre_arch,"r");
	int dim=0;
	char ch;
	
	if(arch == NULL)
		printf("\n Error");
	else
	{
		while ((ch = fgetc(arch)) != EOF) 
	    {
			if(ch == '\n')
				dim++;
	    }
		dim++;
	}

	fclose(arch);

	return sqrt(dim);	//devolvemos la raiz cuadrada de el numero de lineas que se haya leido
}
