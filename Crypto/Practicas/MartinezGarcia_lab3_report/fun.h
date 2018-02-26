/*CARLOS EDUARDO CABALLERO HUESCA
 BRANDO JOSUE MARTINEZ GARCIA
 Group:3CV1
*/

#ifndef FUN_H
#define FUN_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <time.h>

char *fileLlave;
char *fileTextoPlano;
char *fileTextoCifrado;

void generarLLave(int longitud, char archivoLlave[]);
void leerTextoClaro(char textoClaro[]);
int totalCaracteres(char textoClaro[]);
int numeroBloques(int i, int longitud);
void divisionPorBloques(int longitud,int i,char textoClaro[]);

#endif