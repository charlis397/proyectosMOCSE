/*
	Martinez Bautista Raquel Ariadna
	Sanchez Cuna Eduardo Odin
	Elizalde Flores Berenice
	Urban Reyes Adan
*/
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <unistd.h>
#include "list.h"

unsigned int powerModule(unsigned int base,unsigned int exponent, unsigned int module){
	unsigned int result=1;
	for(;exponent>0;exponent--){
		 result*=base;
		 result%=module;
	}
	return result;
}
unsigned char findX(unsigned int primeNumber,unsigned int generator,unsigned int a){
	unsigned int x=0,result=1;
	while(a!=result){
		result=powerModule(generator,x,primeNumber);
		x++;
	}
	return x-1;
}
unsigned int *replicateUI(unsigned int ui){
	unsigned int *replicate=(unsigned int *)malloc(sizeof(unsigned int));
	*replicate=ui;
	return replicate;
}
short repeatAElement(List list){
	Node *auxiliary=NULL;
	while(list.head!=NULL){
		auxiliary=list.head->next;
		while(auxiliary!=NULL){
			if(*((unsigned int *)list.head->data) == *((unsigned int *)auxiliary->data)){
				return 1;
			}
			auxiliary=auxiliary->next;
		}
		list.head=list.head->next;
	}
	return 0;
}
unsigned int getLast(List list){
	while(list.head->next!=NULL){
		list.head=list.head->next;
	}
	return *((unsigned int *)list.head->data);
}
void printListUI(List list){
	while(list.head!=NULL){
		printf("|%u|->",*((unsigned int *)list.head->data));
		list.head=list.head->next;
	}
	printf("NULL\n");
}

unsigned int *getNextGenerator(unsigned int primeNumber,unsigned int *generator){
//	if(isPrime(primeNumber)==0){
//		return NULL;
//	}
	if(generator==NULL){
		generator=(unsigned int *)malloc(sizeof(unsigned int));
		*generator=1;
	}
	List resultModules;
	addNodeAtEndOfList(&resultModules,(void *)replicateUI(1));
	printListUI(resultModules);
	while(resultModules.lenght!=primeNumber){
		resultModules.head=NULL;
//		clearList(&resultModules);
		addNodeAtEndOfList(&resultModules,(void *)replicateUI(1));
		*generator=(*generator)+1;	
		printListUI(resultModules);
		//while(repeatAElement(resultModules)==0){
		//	addNodeAtEndOfList(&resultModules,(void *)replicateUI((unsigned int)((getLast(resultModules) * (*generator))%primeNumber)));
		//}
		printListUI(resultModules);
	}
	return generator;
}
int findgenerator(int p)
{
	int x = 1;
	int r = 1;
	int i = 0;
	int incremento = 0;
	int generador = 2;
		for(x = 1; x < p; x++)
		{
			r = 1;
			for(i = 0; i < x; i++)
			{
				r = (r*generador)%p;
			}
			incremento++;
			if(r == 1)
			{
				if(incremento != (p-1))
				{
					generador++;
				}
				else
				{
					return generador;
				}
			}
		}
	return 0;
}
int main(int ari,char **arc){
	short option;
	unsigned int a,x,primeNumber;
	do{
		printf("\n\t\t\tElija una option\n\t1 potencia de a^x modulo m\n\t2 encuentra x\n\t3 generador minimo\n\t-1 salir\n");
		scanf("%hd",&option);
		switch(option){
			case 1://execute as ./a.out 1 a x primeNumber
				printf("ingrese un valor entero positivo para a: \n");
				scanf("%u",&a);
				printf("ingrese un valor entero positivo para x: \n");
				scanf("%u",&x);
				printf("ingrese un valor entero positivo para m: \n");
				scanf("%u",&primeNumber);
				printf("%u^%u module(%u)=%u\n",a,x,primeNumber,powerModule(a,x,primeNumber));
			break;
			case 2://execute as ./a.out 2 primenumber generator a
				printf("ingrese un valor entero positivo para el numero primo: \n");
				scanf("%u",&primeNumber);
				printf("ingrese un valor entero positivo para el generador: \n");
				scanf("%u",&x);
				printf("ingrese un valor entero positivo para a: \n");
				scanf("%u",&a);
				printf("x=%u\n",findX(primeNumber,x,a));
			break;
			case 3://./a.out 3 primeNumber
				printf("ingrese un valor entero positivo para el numero primo: \n");
				scanf("%u",&primeNumber);	
				printf("primer generador = %d\n",findgenerator(primeNumber));	
			break;
			case -1:
				printf("asta pronto :D\n");
			break;	
			default:
				printf("%hd no es una opcion valida\n",option);
		}
	}while(option!=-1);



/*
	short option=(short)atoi(arc[1]);
	//unsigned int *generator=NULL;
	switch(option){
		case 1://execute as ./a.out 1 a x primeNumber
			printf("%u^%u module(%u)=%u\n",atoi(arc[2]),atoi(arc[3]),atoi(arc[4]),powerModule(atoi(arc[2]),atoi(arc[3]),atoi(arc[4])));
		break;
		case 2://execute as ./a.out 2 primenumber generator a
			printf("x=%u\n",findX(atoi(arc[2]),atoi(arc[3]),atoi(arc[4])));
		break;
		case 3://./a.out 3 primeNumber
			//printf("primer generador = %u\n",*(getNextGenerator(atoi(arc[2]),generator)));	
			printf("primer generador = %d\n",findgenerator(atoi(arc[2])));	
		break;
		default:
			printf("%hd no es una opcion valida\n",option);
	}
*/
	return 0;
}
