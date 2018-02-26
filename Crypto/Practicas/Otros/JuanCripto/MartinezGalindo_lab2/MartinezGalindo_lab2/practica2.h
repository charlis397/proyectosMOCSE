/*
	Declaramos las funciones a utilizar
*/

#ifndef PRACTICA2_H
#define PRACTICA2_H

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

typedef int** matriz;

matriz crearMatriz(int filas,int columnas);
void imprimeMatriz(matriz m, int filas, int columnas);
matriz multiplicarMatrices(matriz a, int filas_a, int columnas_a, matriz b, int filas_b, int columnas_b);
int determintanteMatriz(matriz a,int n);
matriz matrizTranspuesta (matriz m, int filas, int columnas);
matriz matrizAdjunta(matriz a, int dim);
int determinante(int a, int b, int c, int d);
matriz leerMatriz(char* archivo);
void escribirMatriz(char* nombre_arch,matriz m,int filas,int columnas);
matriz matrizModulo(matriz a, int filas, int columnas, int m);
matriz matrizInversa(matriz m,int dim,int determinante);
matriz multiplicarMatrizConEscalar(int esc, matriz m, int filas, int columnas);
int gcd(int a, int b);
int inverso(int x,int m);
void cifrar(char* texto_plano,char* texto_llaves,char* texto_cifrado);
void descifrar(char* texto_plano,char* texto_llaves,char* texto_descifrado);
void generarLlave(int dimension);
int numeroDimensiones(char* nombre_arch);


#endif
