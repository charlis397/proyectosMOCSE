#include<bits/stdc++.h>
using namespace std;
int invp10[]={9,8,7,6,5,4,3,2,1,0};
int invip[]={7,6,5,4,3,2,1,0};
int invp4[]={3,2,1,0};
int invpermexp[]={3,2,1,0};
int s0[4][4]={ {1,0,3,2},
				   {3,2,1,0},
				   {0,2,1,3},
				   {3,1,3,1}
};
int s1[4][4]={ {0,1,2,3},
				   {2,0,1,3},
				   {3,0,1,0},
				   {2,1,0,3}
};
void generar_llaves(int,int *,int *);
int genera_k();
int genera_iv();
int p10(int);
int p8(int);
int ip(int);
int ipinv(int);
int perm_exp(int);
int p4(int);
int scajas(int);
int p8(int);
int cifrar(int,int,int);
int decifrar(int,int,int);
int corr_circ_izq(int);
int calcula_a(int);
int calcula_b(int);

void generar_llaves(int k,int * k1,int * k2){
	k = p10(k);
	k = corr_circ_izq(k);
	*k1 = p8(k);
	k = corr_circ_izq(k);
	k = corr_circ_izq(k);
	*k2=p8(k);
}


/*
	k tiene este orden
	1 2 3 4 5 6 7 8 9 10
	pero aqui en realidad esta indexado desde 0 entonces
	0 1 2 3 4 5 6 7 8 9
	pero en los lenguajes de programacion el 0 representa el bit menos significativo y 9 el mas significativo por lo tanto
	el orden correcto sera
	9 8 7 6 5 4 3 2 1 0
	la P10 es
	2 4 1 6 3 9 0 8 7 5

*/
int p10(int k){
	int temp=0;
	if(k & (1<<invp10[5]))
		temp|=1<<0;
	if(k & (1<<invp10[7]))
		temp|=1<<1;
	if(k & (1<<invp10[8]))
		temp|=1<<2;
	if(k & (1<<invp10[0]))
		temp|=1<<3;
	if(k & (1<<invp10[9]))
		temp|=1<<4;
	if(k & (1<<invp10[3]))
		temp|=1<<5;
	if(k & (1<<invp10[6]))
		temp|=1<<6;
	if(k & (1<<invp10[1]))
		temp|=1<<7;
	if(k & (1<<invp10[4]))
		temp|=1<<8;
	if(k & (1<<invp10[2]))
		temp|=1<<9;
	return temp;
}


/*
	k tiene este orden
	1 2 3 4 5 6 7 8 9 10
	pero aqui en realidad esta indexado desde 0 entonces
	0 1 2 3 4 5 6 7 8 9
	pero en los lenguajes de programacion el 0 representa el bit menos significativo y 9 el mas significativo por lo tanto
	el orden correcto sera
	9 8 7 6 5 4 3 2 1 0
	la P8 es
	5 2 6 3 7 4 9 8

*/
int p8(int k){
	int temp=0;
	if(k & (1<<invp10[8]))
		temp|=1<<0;
	if(k & (1<<invp10[9]))
		temp|=1<<1;
	if(k & (1<<invp10[4]))
		temp|=1<<2;
	if(k & (1<<invp10[7]))
		temp|=1<<3;
	if(k & (1<<invp10[3]))
		temp|=1<<4;
	if(k & (1<<invp10[6]))
		temp|=1<<5;
	if(k & (1<<invp10[2]))
		temp|=1<<6;
	if(k & (1<<invp10[5]))
		temp|=1<<7;
	return temp;
}


	
int corr_circ_izq(int x){
	int temp=0;
	if(x & (1<<9))
		temp|=1<<5;
	if(x & (1<<8))
		temp|=1<<9;
	if(x & (1<<7))
		temp|=1<<8;
	if(x & (1<<6))
		temp|=1<<7;
	if(x & (1<<5))
		temp|=1<<6;
	if(x & (1<<4))
		temp|=1<<0;
	if(x & (1<<3))
		temp|=1<<4;
	if(x & (1<<2))
		temp|=1<<3;
	if(x & (1<<1))
		temp|=1<<2;
	if(x & (1<<0))
		temp|=1<<1;
	return temp;
}


int cifrar(int m,int k1,int k2){
	m = ip(m);
	int r = perm_exp(m);
	r^=k1;
	r=scajas(r);
	r = p4(r);
	int l = m>>4;
	l = r^l;
	m &= 15;
	//Hasta este punto terminamos con la ronda 1
	m |= l<<4;
	//comenzamos con la ronda 2, intercanbiando l,r
	m <<= 4;
	m |= l;
	r = perm_exp(m);
	r ^= k2;
	r = scajas(r);
	r = p4(r);
	l = m>>4;
	l = r^l;
	//se conserva el lado derecho y se la agrega la parte izquierda
	m &= 15;
	m |= l<<4;
	int c = ipinv(m);
	return c;	
}


/*
	m tiene este orden
	1 2 3 4 5 6 7 8 
	pero aqui en realidad esta indexado desde 0 entonces
	0 1 2 3 4 5 6 7
	pero en los lenguajes de programacion el 0 representa el bit menos significativo y 9 el mas significativo por lo tanto
	el orden correcto sera
   7 6 5 4 3 2 1 0
	la IP es
	1 5 2 0 3 7 4 6

*/
int ip(int m){
	int temp=0;
	if(m & (1<<invip[6]))
		temp|=1<<0;
	if(m & (1<<invip[4]))
		temp|=1<<1;
	if(m & (1<<invip[7]))
		temp|=1<<2;
	if(m & (1<<invip[3]))
		temp|=1<<3;
	if(m & (1<<invip[0]))
		temp|=1<<4;
	if(m & (1<<invip[2]))
		temp|=1<<5;
	if(m & (1<<invip[5]))
		temp|=1<<6;
	if(m & (1<<invip[1]))
		temp|=1<<7;
	return temp;
}


int perm_exp(int m){
	int temp=0;
	if(m & (1<<invpermexp[0]))
		temp|=1<<0;
	if(m & (1<<invpermexp[3]))
		temp|=1<<1;
	if(m & (1<<invpermexp[2]))
		temp|=1<<2;
	if(m & (1<<invpermexp[1]))
		temp|=1<<3;
	if(m & (1<<invpermexp[2]))
		temp|=1<<4;
	if(m & (1<<invpermexp[1]))
		temp|=1<<5;
	if(m & (1<<invpermexp[0]))
		temp|=1<<6;
	if(m & (1<<invpermexp[3]))
		temp|=1<<7;
	return temp;
}

/*
	r después de ser sometido a la sustitucion con las s cajas tiene este orden
	1 2 3 4
	pero aqui en realidad esta indexado desde 0 entonces
	0 1 2 3
	pero en los lenguajes de programacion el 0 representa el bit menos significativo y 9 el mas significativo por lo tanto
	el orden correcto sera
	3 2 1 0
	la P4 es
	1 3 2 0

*/
int p4(int r){
	int temp=0;
	if(r & (1<<invp4[0]))
		temp|=1<<0;
	if(r & (1<<invp4[2]))
		temp|=1<<1;
	if(r & (1<<invp4[3]))
		temp|=1<<2;
	if(r & (1<<invp4[1]))
		temp|=1<<3;
	return temp;
}


int scajas(int r){
	int temp=0;
	int reng=0,col=0;
	if((r & (1<<7)))
		reng=2;
	if((r & (1<<4)))
		reng+=1;
	if((r & (1<<6)))
		col=2;
	if((r & (1<<5)))
		col+=1;
	temp = s0[reng][col];
	temp<<=2;
	reng = col=0;
	if((r & (1<<3)))
		reng=2;
	if((r & (1<<0)))
		reng+=1;
	if((r & (1<<2)))
		col=2;
	if((r & (1<<1)))
		col+=1;
	temp+=s1[reng][col];
	return temp;
}


/*
	m tiene este orden
	1 2 3 4 5 6 7 8 
	pero aqui en realidad esta indexado desde 0 entonces
	0 1 2 3 4 5 6 7
	pero en los lenguajes de programacion el 0 representa el bit menos significativo y 9 el mas significativo por lo tanto
	el orden correcto sera
   7 6 5 4 3 2 1 0
	la IP inversa es
	3 0 2 4 6 1 7 5

*/
int ipinv(int m){
	int temp=0;
	if(m & (1<<invip[5]))
		temp|=1<<0;
	if(m & (1<<invip[7]))
		temp|=1<<1;
	if(m & (1<<invip[1]))
		temp|=1<<2;
	if(m & (1<<invip[6]))
		temp|=1<<3;
	if(m & (1<<invip[4]))
		temp|=1<<4;
	if(m & (1<<invip[2]))
		temp|=1<<5;
	if(m & (1<<invip[0]))
		temp|=1<<6;
	if(m & (1<<invip[3]))
		temp|=1<<7;
	return temp;
}


int decifrar(int c,int k1,int k2){
	c=ip(c);
	int r=perm_exp(c);
	r^=k2;
	r=scajas(r);
	r=p4(r);
	int l=c>>4;
	l=r^l;
	c&=15;
	//Hasta este punto terminamos con la ronda 1
	c|=l<<4;
	//comenzamos con la ronda 2, intercanbiando l,r
	c<<=4;
	c|=l;
	r=perm_exp(c);
	r^=k1;
	r=scajas(r);
	r=p4(r);
	l=c>>4;
	l=r^l;
	//se conserva el lado derecho y se la agrega la parte izquierda
	c&=15;
	c|=l<<4;
	int m=ipinv(c);
	return m;	
}

void welcome(){
		system("clear");
		printf("\t*************Escuela Superior De Computo*************\n\t**********Instituto Politecnico Nacional*************\n\t********************CRYPTOGRAPHY*********************\n\t************Pofesora:Sandra Diaz Santiago************\n\t**********Adrian de Jesus Fernandez Ramirez**********\n\t************Miguel Angel Mandujano Diaz**************\n\n\n");
}

int genera_k(){
	srand(time(NULL));
	return rand() % (1<<10);
}

int genera_iv(){
	srand(time(NULL));
	return rand() % (1<<8);
}

