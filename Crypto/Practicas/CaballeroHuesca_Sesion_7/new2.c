#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#define primo 1000
int main(){
	int i,arreglo[primo],p,q,n,fi,e,j,d,aux=10;
	srand(time(NULL));
	FILE* fichero;
	fichero=fopen("numerosPrimos.txt", "r");
	for(i=0;i<primo;i++){
		fscanf(fichero,"%d",&arreglo[i]);
		printf("--%d",arreglo[i]);
	}
	p=arreglo[rand()%primo];
	q=arreglo[rand()%primo];
	n=p*q;
	fi=(p-1)*(q-1);
	printf("\np:%d q:%d",p,q);
	printf("\nn: %d fi: %d\n",n,fi);
	//d=arreglo[primo-1];
	printf("ultima%d",i);
	e =0;
	while(e!=1){
		printf("\nEstoy en el WHILE");
		
	for(j=0;j<i;j++){
		printf("\nEstoy en el FOR[%d]",j);
		e=mcd(arreglo[j],fi);
		if(e==1){
			 break;
		 }
		aux= arreglo[j+1];
	}
	}
	printf("\n***%d****",aux);
	printf("\n***%d****",e);
	
	
	fclose(fichero);
	
}


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