#include <stdio.h>
#include <stdlib.h>
int n[3];
void Ini();

int main()
{
    
    Ini();

	
	printf("%d     ",n[0]);
	printf("%d     ",n[1]);
	printf("%d     ",n[2]);
	
	printf("\n\aEl valor de la suma de tu arreglo es= %d",suma());
    return 0;
}

void Ini()
{
       
       n[0]=2;
       n[1]=3;
       n[2]=14;
 
     
}

int suma()
{
	int sum=0,i=0;
	while(i!=3)
	{
		sum+=n[i];
		i++;
	}
	
	return sum;
	
}