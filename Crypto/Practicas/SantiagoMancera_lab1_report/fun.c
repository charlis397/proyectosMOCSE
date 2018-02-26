#include "fun.h"


int mcd (int a, int b){
	int c;
	while (a!=b)
	{
		if (a>b)
			a=a-b;
		else
			b=b-a;
	}
	c=a;
	return c;
}

int inversoMultiplicativoModular(int a, int modulo){	
	int residuo,x,y,cociente;
	int coeficienteA = 1;
	int coeficienteB = -1*(modulo/a); 
	int corrida = 0;
	int corrida2 = 1;
	
	residuo = modulo%a;
	x = a;
	y = residuo; 
	while( residuo != 0 ){
		cociente = x / y;
		residuo = x % y;
		coeficienteA *= -1*cociente;
		coeficienteB *= -1*cociente;
		coeficienteA += corrida;
		coeficienteB += corrida2;
		corrida = -1*( coeficienteA-corrida )/cociente;
		corrida2 = -1*( coeficienteB-corrida2 )/cociente;
		x = y;
		y = residuo;
	}
	if( x == 1 ){
		if( corrida2 >= 0 )
		{return corrida2;}
		else
		{return corrida2 + MOD;}	
	}
	else
		{ return -1; }
	
}
