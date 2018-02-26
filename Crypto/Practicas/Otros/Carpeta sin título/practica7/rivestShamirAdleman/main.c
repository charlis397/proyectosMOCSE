/*
	program by
	Elizalde Flores Berenice 
	Martinez Bautista Raquel Ariadna
	Sánchez Cuna Eduardo Odin
	Urban Reyes Adan
*/
#include <stdio.h>
#include <math.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <time.h>
#include <fcntl.h>
#include "list.h"
void printListUI(List list){
	while(list.head!=NULL){
		printf("|%u|->",*((unsigned int *)list.head->data));
		list.head=list.head->next;
	}
	printf("NULL\n");
}
void *replyUI(unsigned int ui){
	unsigned int *reply=(unsigned int *)malloc(sizeof(unsigned int));
	*reply=ui;
	return (void *)reply;
}
List getListWithUI(unsigned int start,unsigned int end){
	List listUI;
	bzero(&listUI,sizeof(List));
	for(;start<=end;start++){
		addNodeToLast(&listUI,getNode(replyUI(start),NULL,NULL));
	}
	return listUI;
}
List sieveEratostenes(unsigned int end){
	List primeNumbers=getListWithUI(2,end);
	Node *index=primeNumbers.head,*temporarily=NULL,*auxiliary=NULL;
	unsigned int number,top=(unsigned int)sqrt((double)end);
	for(;top>0;top--){	
		auxiliary=index;
		number=*((unsigned int *)auxiliary->data);
		auxiliary=auxiliary->next;
		while(auxiliary!=NULL){
			if((*((unsigned int *)auxiliary->data))%number == 0){
				temporarily=auxiliary->next;
				removeNode(&primeNumbers,&auxiliary);
				auxiliary=temporarily;
				continue;
			}
			auxiliary=auxiliary->next;
		}
		index=index->next;
	}
	return primeNumbers;
}
unsigned int euclides(unsigned int x,unsigned int y){//this function return greatest common divisor
   unsigned int remainderOld=y,remainderCurrent=x%y;
   if(y>x){
      remainderOld=x;
   }
   while(remainderCurrent!=0){
      remainderOld=remainderCurrent;
//    printf("%u = %u*%u+%u\n",x,y,x/y,remainderCurrent);
      remainderCurrent=y%remainderOld;
      y=remainderOld;
   }
   return remainderOld;//this is a grates common devicer
}
int extendedEuclides(unsigned int a, unsigned int b){
   int u=a,v=b;
   unsigned char flag=0;
   if(a<b){
      flag=1;
      u=a;
      a=b;
      b=u;
   }
   //v=a,u=b;
   v=b,u=a;
   int x1=1,y1=0,x2=0,y2=1;
   int q,r;
   int *result=(int *)malloc(sizeof(int)*3);//x=[0], y=[1] y d=[2] estan aki 
// int i=1;
   while(u!=0){
   // printf("iteracion %d\n",i);
   // printf("\tu=%d|v=%d|q=%d,r=%d|x1=%d|x2=%d|y1=%d|y2=%d|x=%d,y=%d\n",u,v,q,r,x1,x2,y1,y2,result[0],result[1]);   
      q=v/u;
      r=v-q*u;
      result[0]=x2-q*x1;
      result[1]=y2-q*y1;
      v=u;
      u=r;
      x2=x1;
      x1=result[0];
      y2=y1;
      y1=result[1];
   // printf("\tu=%d|v=%d|q=%d,r=%d|x1=%d|x2=%d|y1=%d|y2=%d|x=%d,y=%d\n",u,v,q,r,x1,x2,y1,y2,result[0],result[1]);   

   }
   result[2]=v;
   result[0]=x2;
   result[1]=y2;
   if(result[0]<0){
      result[0]=b-(abs(result[0])%b);
   }
   if(result[1]<0){
      result[1]=a-(abs(result[1])%a);
   }
   //printf("x=%d,y=%d,d=%d\n",result[0],result[1],result[2]);
   if(flag==0)
      return result[0];
   return (result[1]);
}
unsigned int getPublicKey(unsigned int fiEuler){
	unsigned int e=2;
	while(euclides(2,fiEuler)!=1){
		e++;
	}
	return e;
}
unsigned int getPrivateKey(unsigned int publicKey,unsigned int fiEuler){
	return extendedEuclides(publicKey,fiEuler);	
}
char *convertUIC(unsigned int ui){
	char *c=(char *)malloc(sizeof(char)*11);
	sprintf(c,"%u\n",ui);
	return c;
}
void savePrimesNumbers(char *pathFile,List primeNumbers){
	int descriptor=open(pathFile,O_WRONLY | O_CREAT,0666);
	char *c;
	while(primeNumbers.head!=NULL){
		c=convertUIC(*((unsigned int *)primeNumbers.head->data));
		write(descriptor,c,sizeof(char)*strlen(c));
		primeNumbers.head=primeNumbers.head->next;
	}
	close(descriptor);
}
char *getDate(int expiration){
	time_t date=time(NULL);
	struct tm *tl=localtime(&date);
	char *output=(char *)malloc(sizeof(char)*128);
	tl->tm_mon+=expiration;
	tl->tm_mon%=12;
	strftime(output,128,"%d-%m-%y : %H:%M:%S\n",tl);
	return output;
}
void saveCertificate(char *pathFile,char *name,unsigned int publicKey){
	int descriptor=open(pathFile,O_WRONLY | O_CREAT,0666);
	char *auxiliary,*date=getDate(0);
	write(descriptor,name,sizeof(char)*strlen(name));
	write(descriptor,"\n",sizeof(char));
	auxiliary=convertUIC(publicKey);
	write(descriptor,auxiliary,sizeof(char)*strlen(auxiliary));
	write(descriptor,date,sizeof(char)*strlen(date));
	date=getDate(4);
	write(descriptor,date,sizeof(char)*strlen(date));
	
	close(descriptor);
}
unsigned char isTurnedOnBit(unsigned int ui,unsigned char position){//i1tbouiop=is 1 the bit of unsigned int on position, ui=unsigned int
        unsigned int auxiliary=1;
        auxiliary<<=(position-1);
        if(ui&auxiliary){
                return 1;
        }
        return 0;
}
void filtrarList(List *list,unsigned char sb){
	unsigned int number=pow(2,sb);
	Node *auxiliary=list->head,*temporarily;
	while(auxiliary!=NULL){
		if((*(unsigned int*)auxiliary->data)>number || !isTurnedOnBit( *((unsigned int *)auxiliary->data),sb )){
			if(auxiliary->previus==NULL){
				list->head=list->head->next;
			}
			temporarily=auxiliary->next;
			removeNode(list,&auxiliary);
			auxiliary=temporarily;
			continue;
			
		}
		auxiliary=auxiliary->next;
	}
}
List loadPrimeNumbers(char *pathFile){
	List primeNumbers;	
	int descriptor=open(pathFile,O_RDONLY,0666);
	unsigned int prime;
	ssize_t br=1;
	while((br=read(descriptor,&prime,sizeof(unsigned int)))>0){
		addNodeToLast(&primeNumbers,getNode(replyUI(prime),NULL,NULL));
	}
	close(descriptor);
	return primeNumbers;
}
int main(int ari,char **arc){
	short option;
	unsigned int n;
	List lse;
	char *name=(char *)malloc(sizeof(char)*137);
	do{
		printf("\t\t\tIngrese una opcion\n\t1. Criba de Eratosthenes\n\t2. prime numbers\n\t3.\n\t4.Generar Certificado\n\t-1. salir\n");
		scanf("%hd",&option);
		switch(option){
			case 1:
				printf("ingrese n (mayor que 2)\n");
				scanf("%u",&n);
				lse=sieveEratostenes(n);
				printListUI(lse);
			break;
			case 2:
				lse=sieveEratostenes(65536);
				savePrimesNumbers("primos.txt",lse);
			break;
			case 3:
				
			break;
			case 4:
				getchar();
				printf("escriba su nombre completo: \n");
				scanf("%[a-zA-Z ]",name);
				saveCertificate("certificate.txt",name,13711);
			break;
		}
	}while(option!=-1);
	return 0;
}
