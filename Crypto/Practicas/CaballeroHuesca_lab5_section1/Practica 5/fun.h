#ifndef FUN_H
#define FUN_H

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define MOD 26
#define N 4

char* fileResultado;

void generarLLave(int longitud);
void expandirClave(char* archivoSalida,unsigned char (*arrClave)[4], unsigned char *mClaveExp);
//void imprimirLLave(char* archivoSalida,int rondas, unsigned char *mClaveExp );
#endif