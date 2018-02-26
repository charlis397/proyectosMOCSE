//Caballero Huesca Carlos Eduardo
//Martínez García Brando Josué
#include "fun.h"

void extendidoEuclides(int a, int b){
	
	int tieneinversa,inversoMultiplicativo,mod;
	int q=0,r=0,i;
	int x1=0,x2=1,y1=1,y2=0;
	int x=0, y=0, d=0;
	int resultado[3]={20,15,1};
				//d,x,y
	mod=b;
	tieneinversa = mcd(a,b);
	
	if(tieneinversa==1){
		
	
	if(b!=0){
	
		while(b>0){
			
			q=a/b;
			r=a-(q*b);
			x=x2-(q*x1);
			y=y2-(q*y1);
			
			a=b;
			b=r;
			x2=x1;
			x1=x;
			y2=y1;
			y1=y;
			
		}
			
		resultado[0]=a;
		resultado[1]=x2;
		resultado[2]=y2;
	}
	
	else{
		resultado[0]=a;	//1
		resultado[1]=1;	//20
		resultado[2]=0;	//13
	}
	

	while(resultado[1]<0){
		resultado[1]=resultado[1]+mod;
		}

	for(i=0;i<3;i++){
		printf("%d  ",resultado[i]);
	}
	
	printf("\nEl inverso Multiplicativo es %d",resultado[1]);
	

	}
	else{
	printf("no  tiene inversa");
	}
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