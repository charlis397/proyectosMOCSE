#include<stdio.h>
#include<stdlib.h>


int main(int argc, char* argv[]){
		char h;
		int i = 1,N;
	FILE *fp1;
		fp1=fopen("Archivo.txt","r");
		while(feof(fp1)==0){
			h=getc(fp1);
			printf("%c",h);
		}
	fclose(fp1);
	
	
	  
  while ( i < (N+1-i) ) {
    swap(&P[i], &P[N+1-i]);
    i ++;
  }
}