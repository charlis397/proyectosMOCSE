#include <stdio.h>

#include <string.h>

int main(){
	char plaintext[100];
	char letra= 'A',numero[26];
	int i,k,j=0,tam;
	char *a, *b;
	for(i=0; i<26; i++){
		numero[i]=letra++;
	}
	printf("Introduce el mensage en claro");
	scanf("%s",&plaintext);
	tam=sizeof(plaintext);
	for(j=0; j<tam; j++){
	a=&plaintext[j];
	if(a==numero[j]){
		k=numero[j];
	}
	}
	
	printf("%c\n",&a);
	}
	
	
	printf(" %s\n", numero);
}