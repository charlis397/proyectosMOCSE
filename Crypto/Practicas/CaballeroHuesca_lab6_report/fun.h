#ifndef FUN_H
#define FUN_H

#include <stdio.h>
#include <stdlib.h>
#include <math.h>


#define N 8


void crearPolinomio(int polinomio[N]);
void imprimirPolinomio(int polinomio[N]);
int BinarioDecimal(int polinomio[N]);
void DecimalBinario(int decimal,int binario[N]);
int multiplicacionPolinomio(int polinomio[N],int polinomio2);
int moduloPolinomio(int polinomio,int mx);
void imprimirMX(int polinomio[9]);
int BinarioDecimalMX(int polinomio[9]);
void invertirVector(int decimal,int binario[N]);
int posicionPrimerUno(int polinomio[8]);


#endif