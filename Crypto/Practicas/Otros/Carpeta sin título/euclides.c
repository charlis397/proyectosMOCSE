#include <stdio.h>
#include <stdlib.h>
int main(){
	int mcd,m,n;
	printf("ingrese el valor de m\n");
	scanf("%d",&m);
	printf("ingrese el valor de n\n");
	scanf("%d",&n);
	mcd=MaximoComunDivisor(m,n);
	printf("El Maximo comun divisor es: %d",mcd);
	
}

int MaximoComunDivisor(a,b){
int r,c;
	r = a%b;
	while (r != 0){
		c=b;
		b=r;
		r=c%b;
	}
	return b;
	
}