#include<stdio.h>
#include<time.h>
#include<math.h>
#define Noprimos 1000

void primor( int arreglo[Noprimos]);
int exponente(int,int);

int main()
{
 

 int i=0,aux,j=0; 
 long c;
 int primo=2;
 int NoDivisores=1; 
 int arreglo[Noprimos];
 int primero,segundo;

	srand(time(NULL)); 

 
	primor(arreglo);

	primero = arreglo[rand()%Noprimos];
	segundo = arreglo[rand()%Noprimos];
 

 
 
 printf("El primer numero primo es =%d\n",primero);
 printf("El primer numero primo es =%d\n",segundo);
 //c=exponente(5,100000);
 c=(long long)pow(5,1000000)%10;
 printf("%d",c);
 

}

int exponente(int a, int b){
	
	if(b==0)
		return 1;
	else
		return ((a*exponente(a,b-1))%10);
}

void primor(int arreglo[Noprimos]){

 int i=0,aux,j=0; 
 int primo=2;
 int NoDivisores=1; 
 //int arreglo[Noprimos];
	
while(i<Noprimos) 
 {
	if(primo>=NoDivisores) 
	{
		NoDivisores++;
   
   if(((primo%NoDivisores)==0) && (primo!=NoDivisores)) 
   {
    primo++;
    NoDivisores=1;
   }
   
   if((primo%NoDivisores)==0 && (primo==NoDivisores)) 
   {
	arreglo[j]= primo;   
 
    primo++;
    i++;
	j++;
    NoDivisores=1;
	//aux = primo;
   } 

  }
  else {
	  primo++; 
  }
  
 }
 // printf("\n");
 
 // FILE* fichero; 
 // fichero = fopen("numerosPrimos.txt", "w+");

	
 // for(i=0;i<Noprimos;i++){
	 // printf("%d	",arreglo[i]);
	 // fprintf(fichero,"%d ",arreglo[i]);
 // }
 
 // fclose(fichero);	
	
	
	//return arreglo[numero];
	
}