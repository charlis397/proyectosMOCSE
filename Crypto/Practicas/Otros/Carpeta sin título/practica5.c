#include <stdio.h>
int multiplica (int x, int n){
	return x<<n;
}
int determina (int x, int i){
	x>>=i;
	return x&1;
}
int extract(int x, int p, int n){
	x>>=p;
	x&=((1<<n)-1);
	return x;
}

int invert(int x, int p, int n){
	return  x ^ (((1<<n)-1) <<p);
}

int add(int p, int q){
	return p^q;
}

int multiply(p,q,m,n){
	int i, res=0, mod=0, aux=0;
	if(p&1){
		res=q;
		printf("res0 = %d\n",res);}
	mod = invert(m,n,1);
	aux=q;
	for(i=1;i<n;i++)
	{
		printf("mod = %d\n",mod);
		aux=multiplica(aux,1);
		if(aux>>n)
		{
			printf("entra %d\n",i);
			aux=invert(aux,n,1);
			aux=add(aux,mod);
		}
		printf("aux%d = %d\n",i,aux);
		

		if(determina(p,i)){//(p>>i)&1
			printf(" = %d\n",(p>>i)&1);
			res=add(res,aux);
			printf("res%d = %d\n",i,res);
		}
	}
	return res;
}

main (void){
	int a=multiplica (5,1);
	printf("%d\n", a);
	a=determina(15,2);
	printf("%d\n", a);
	a=extract(101,2,8);
	printf("%d\n", a);
	a=invert(101,4,6);
	printf("%d\n", a);
	a=add(7,7);
	printf("%d\n", a);
//	a=multiply(7,5,11,3); //=6
	a=multiply(9,13,19,4); //=
	printf("%d\n", a);
	
}