#include<stdio.h>
#include<stdlib.h>

/*int metodo_ax_mod_p(int a, int x, int p)
{
	
}*/

int powmod(int a, int x, int m)
{
	int r = 1;
	int i = 0;
	for(i = 0; i < x; i++)
	{
		r = (r*a)%m;
	}
	return r;
}
