#include<bits/stdc++.h>
#define lli long long int
#define max_num_primos 4096
using namespace std;
lli x,y;
bitset<max_num_primos>esprimo;
int primos[max_num_primos];
int numero_de_primos;

lli devuelve_x(){
	return x;
}

int numero_primos(){
	return numero_de_primos;
}

lli regresa_a_N(lli n,lli val){
	lli ans;
	while(val<0)
		val+=n;
	return val;
}

lli binary_pow(int a,int n,int m){
	lli x = a%m;
	lli exp = 1;
	while(n>0){
		if(n%2)
			exp = (exp*x)%m;
		x = (x*x)%m;
		n /= 2;
	}
	return exp;
}

bool Rabin_Miller(int p){
	//srand(time(NULL));
	int n = p-1, m, k;
	
	//
	// Dividimos mientras alguna potencia de 2 divida a p-1
	//	
	int pot2 = 1,i = 0;
	while(!(n%(pot2))){
		k = i;
		m = n / pot2;
		pot2 = pow(2,i);
		i++;
	}
	k--;//Restamos uno a la k para que corresponda a la potencia
	
	//
	// Generamos un numero aleaorio menor a p-1
	// y probamos para toda i menor a k
	//	
	lli a;
	for(int i=0;i<k;i++){
		int a_temp = (rand() % n) + 1;
		a = binary_pow(a_temp,pow(2,i),p);
		if(a!=1)
			return false;		
	}
	return true;
}

int* criba(){
	int k = 0;
	for(int i=2;i<max_num_primos;i++){
		if(!esprimo[i]){
			for(int j=i*i;j<max_num_primos;j+=i)
				esprimo[j] = true;
			primos[k] = i;k++;
		}
	}
	numero_de_primos = k;
	int *ar = primos;
	return ar;
}

void extendedEuclides(int a,int b){
	if(b==0){
		x = 1;
		y = 0;
		return;
	}
	extendedEuclides(b,a%b);
 	int x1 = y;
	int y1 = x - (a/b)*y;// A este punto a=b y b = a%b y se va construyendo desde 0 como el residuo llega a cero con euclides
								// y x a 1
	x = x1;
	y = y1;
}

int cifrarsa(int m,int e,int n){
	return 	binary_pow(m,e,n);
}

int decifrarsa(int c,int d,int n){
	return 	binary_pow(c,d,n);
}
