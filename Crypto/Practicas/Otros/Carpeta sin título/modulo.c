/*
----Martínez Bautista Raquel Ariadna
----Sánchez Cuna Eduardo Odin
*/

#include <stdio.h>
#include <stdlib.h>
#include "modulo.h"

int suma_modulo(int valA, int valB, int valN)
{
	int modulo;
	if(valN >= 2)
	{
		modulo = (valA + valB)%valN;
		if(modulo < 0)
		{
			modulo = valN + modulo;
		}
		return modulo;
	}
}

int resta_modulo(int valA, int valB, int valN)
{
	int modulo;
	if(valN >= 2)
	{
		modulo = (valA - valB)%valN;
		if(modulo < 0)
		{
			modulo = valN + modulo;
		}
		return modulo;
	}
}

int multiplicacion_modulo(int valA, int valB, int valN)
{
	int modulo;
	if(valN >= 2)
	{
		modulo = (valA * valB)%valN;
		if(modulo < 0)
		{
			modulo = valN + modulo;
		}
		return modulo;
	}
}

int additive_inverse(int valA, int valN)
{
	int inverso = 0, d = 0;
	if(valN >= 2)
	{
		return valA;
	}
}

int multiplicative_inverse(int valA, int valN)
{
	int u = valA, v = valN, x1 = 1, x2 = 0, y1 = 0, y2 = 1;
	if(valN >= 2)
	{
		int q = 0, r = 0, x = 0, y = 0, d;
		if((valN%valA) != 0 )
		{
			while(u != 0)
			{
				q = v/u;
				r = v-(q*u);
				x = x2 - (q*x1);
				y = y2 - (q*y1);
				v = u;
				u = r;
				x2 = x1;
				x1 = x;
				y2 = y1;
				y1 = y;
			}
			d = v;
			x = x2;
			y = y2;
			if(x < 0)
			{
				x = valN + x;
			}
			if(y<0)
			{	
				y = valN + y;
			}
			return (x);
		}
		else
			return -1;
	}
}


