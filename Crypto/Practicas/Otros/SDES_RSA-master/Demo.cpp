#include <bits/stdc++.h>
#include "RSA.h"
#include "S-DES.h"
#include <sys/stat.h>
using namespace std;

void alta();
void borrar();
void actualizar();
void agregar_llave();
void Cifrar();
void Decifrar();
void fecha(char *,bool);
void RSA(char *, char *, char *);

int main(){
	
	//puts("Ingresa el nombre del archivo que desear cifrar y el nombre del archivo donde deseas guardar el texto cifrado\n");
	int op;
	while(1){
		welcome();
		puts("Seleccione una opcion:");
		puts("1.-Dar de alta usuarios, borrar, actualizar y datos del key ring");
		puts("2.-Cifrar o Decifrar un archivo");
		puts("3.-Agregar llaves a un usuario");
		puts("4.-Salir\n");
		scanf("%d",&op);
		switch(op){
			case 1:
				int op2;
				system("clear");
				puts("1.-Dar de alta");
				puts("2.-Borrar");
				puts("3.-Actualizar");
				scanf("%d",&op2);
				system("clear");
				switch(op2){
					case 1:
						alta();
						break;
					case 2:
						borrar();
						break;
					case 3:
						actualizar();
						break;
				}
				break;
			
			case 2:
				int op3;
				system("clear");
				puts("1.-Cifrar");
				puts("2.-Decifrar");
				scanf("%d",&op3);
				system("clear");
				switch(op3){
					case 1:
						Cifrar();
						break;
					case 2:
						Decifrar();
						break;
				}
				break;
			case 3:
				system("clear");
				agregar_llave();
				break;
			case 4:
				puts("Hasta luego!");
				return 0;
			default:
				puts("Por favor digite una opcion correcta");
				getchar();
				getchar();
				system("clear");
				break;
		}
	}
}

void agregar_llave(){
	char f[30],nom[30],publica[20],n[20],privada[20],linea[400],cad[300];
	puts("Ingrese su nombre:");
	scanf("%s",nom);
	
	FILE *ficherokeyring;
	int i=0;
	bool flag = false;
	ficherokeyring = fopen("keyring.txt", "rb");
	
	while (!feof(ficherokeyring)){
		 fgets(linea,400,ficherokeyring);
		 char linea2[400];
		 strcpy(linea2,linea);
		 char * name=strtok(linea,"\t");
		 if(!strcmp(name,nom)){
			 flag = true;
			 break;
		 }
	}
	fclose(ficherokeyring);
	if(flag){
		fecha(f,false);
		//calculamos la llave publica la privada y la n para ese usuario
		RSA(publica,privada,n);
		//agregamos los datos al key ring
		printf("Querido %s\nSu llave publica es: %s\nSu llave privada es (Guardela bien): %s\nSu n es: %s\nSu llave expirara el: %s\n",nom,publica,privada,n,f);
		sprintf(cad,"%s\t%s\t%s\t%s\n",nom,f,publica,n);
		FILE *ficherokey;
		ficherokey = fopen("keyring.txt", "a");
		fputs (cad,ficherokey);
		fclose(ficherokey);
	}
	else
		puts("El usuario aun no esta creado. Vuelva a intentarlo porfavor");
	puts("Presione enter para continuar...\n");	
	getchar();
   getchar();
 	system("clear");
}

void alta(){
	char f[30],nom[30],publica[20],n[20],privada[20],cad[300];
	//fecha de caducidad de la llave
	fecha(f,false);
	//nombre del propietario de la llave publica
	puts("Ingrese su nombre:");
	scanf("%s",nom);
	//calculamos la llave publica la privada y la n para ese usuario
	RSA(publica,privada,n);
	//agregamos los datos al key ring
	printf("Querid@ %s\nSu llave publica es: %s\nSu llave privada es (Guardela bien): %s\nSu n es: %s\nSu llave expirara el: %s\n(Puede crear mas llaves despues si asi lo desea).\n\n",nom,publica,privada,n,f);
	sprintf(cad,"%s\t%s\t%s\t%s\n",nom,f,publica,n);
	FILE *ficherokey;
   ficherokey = fopen("keyring.txt", "a");
	fputs (cad,ficherokey);
   fclose(ficherokey);
   puts("Presione enter para continuar...\n");	
   getchar();
   getchar();
 	system("clear");
}
void fecha(char * s,bool act){
	 struct tm *tiempo;
	 int dia;
	 int mes;
	 int anio;
	 time_t fecha_sistema;
	 time(&fecha_sistema);
	 tiempo=localtime(&fecha_sistema);
	 
	 anio=tiempo->tm_year + 1900;
	 if(act)
		 mes=(tiempo->tm_mon + 1)%13;
	 else
	 	mes=(tiempo->tm_mon + 2)%13;
	 dia=tiempo->tm_mday;
	 sprintf(s,"%d/%d/%d",dia,mes,anio);
}


void RSA(char * publica, char * privada, char * nn){
	//pasaremos a calcular su llave publica y privada con rsa
	lli e,d;
	int p,q;
	srand(time(NULL));
	lli m = 12;
	//
	// Criba de eratóstenes y generacion de primos mediante el arreglo de primos generado
	//
	int *primos = criba();
	int num_primos = numero_primos();
	p = primos[rand()%num_primos];
	q = primos[rand()%num_primos];
	
	//
	// Calculamos la fi de n y el producto de p*q como n
	//
	lli n = p*q;
	int fi_n = (p-1)*(q-1);
	
	//
	// Buscamos un e que satisfaga mcd(e,fi_n);
	//
	e = (rand()% (fi_n-1)) +1 ;
	while(__gcd((int)e,fi_n)!=1)
		e = (rand()% (fi_n-1)) +1;
	
	//
	// Calculamos el Extendido de Euclides bajo fi de n y nuestra e
	//
	
	extendedEuclides(e,fi_n);
	
	//
	// Asignamos a la variable d (llave Privada) el valor de cualquier que haya resultado inverso de e ya sea x o y 
	// NOTA: La funcion devuelve_x() solo devolvera un valor valido de x si ya se mando llamar a extendedEuclides();
	//
	d = devuelve_x();
	if(d<0)
		d = regresa_a_N(fi_n,d);//Si acaso el i.m.m. es negativo lo regresamos a Zn
	sprintf(publica,"%lld",e);
	sprintf(privada,"%lld",d);
	sprintf(nn,"%lld",n);
}


void borrar(){
	char nombre[40],linea[400];
	bool flag = false;
	puts("Ingresa el nombre:");
	scanf("%s",nombre);
	FILE *ficherokeyring, *ficheronuevo;
	int i=0;
	ficherokeyring = fopen("keyring.txt", "rb");
	ficheronuevo = fopen("nuevo.txt", "wb");
	while (feof(ficherokeyring)==0){
		 fgets(linea,400,ficherokeyring);
		 char linea2[400];
		 strcpy(linea2,linea);
		 char * name=strtok(linea,"\t");
		 if(strcmp(name,nombre) && (strlen(linea2)>20))
			 fprintf(ficheronuevo,"%s",linea2);
	    if(!strcmp(name,nombre))
	       flag = true;
	}
	fclose(ficherokeyring);
	fclose(ficheronuevo);
	remove("keyring.txt");
	chmod("nuevo.txt", S_IRWXU|S_IRGRP|S_IXGRP|S_IROTH);
	rename("nuevo.txt","keyring.txt");
	chmod("keyring.txt", S_IRWXU|S_IRGRP|S_IXGRP|S_IROTH);
	(flag) ? puts("Usuario borrado correctamente!"):puts("El usuario no existe.");
	puts("Presione enter para continuar...\n");
	getchar();
   getchar();
 	system("clear");
}


void actualizar(){
	char nombre[40],linea[400];
	bool flag = false;
	puts("Ingresa el nombre:");
	scanf("%s",nombre);
	FILE *ficherokeyring, *ficheronuevo;
	int i=0;
	ficherokeyring = fopen("keyring.txt", "rb");
	ficheronuevo = fopen("nuevo.txt", "wb");
	while (feof(ficherokeyring)==0){
		 fgets(linea,400,ficherokeyring);
		 char linea2[400];
		 strcpy(linea2,linea);
		 char * name=strtok(linea,"\t");
		 if(strcmp(name,nombre)==0 && (strlen(linea2)>20)){
	    	 char f[30],publica[20],n[20],privada[20],cad[300];
		    fecha(f,false);
		    RSA(publica,privada,n);
		    sprintf(cad,"%s\t%s\t%s\t%s\n",nombre,f,publica,n);
		    printf("Querido %s sus nuevos datos son\nSu llave publica es: %s\nSu llave privada es (Guardela bien): %s\nSu n es: %s\nSu llave expirara el: %s\n",nombre,publica,privada,n,f);
	       fprintf(ficheronuevo,"%s",cad);
	    }
		 else if( (strlen(linea2)>20) )
			 fprintf(ficheronuevo,"%s",linea2);
		 if(strcmp(name,nombre))
		 	 flag = true;
	}
	fclose(ficherokeyring);
	fclose(ficheronuevo);
	remove("keyring.txt");
	chmod("nuevo.txt", S_IRWXU|S_IRGRP|S_IXGRP|S_IROTH);
	rename("nuevo.txt","keyring.txt");
	chmod("keyring.txt", S_IRWXU|S_IRGRP|S_IXGRP|S_IROTH);
	(flag) ? puts("El usuario no existe aun. Intentelo de nuevo"):puts("Usuario borrado con exito!");
	puts("Presione enter para continuar...\n");
	getchar();
   getchar();
 	system("clear");
}


void Cifrar(){
	char plaintext[50],ciphertext[50],nombre[50],linea[400];
	int publica,n;
	bool bandera=false;
	puts("Ingrese el nombre del destinatario");
	scanf("%s",nombre);
	
	FILE *ficherokeyring;
	ficherokeyring = fopen("keyring.txt", "rb");
	
	while (feof(ficherokeyring)==0){
		 fgets(linea,400,ficherokeyring);	
		 char linea2[400];
		 strcpy(linea2,linea);
		 char * name=strtok(linea,"\t");
		 if(strcmp(name,nombre)==0){
		 	  int i=2;
		 	  bandera=true;
		     while (name != NULL){
			    name = strtok (NULL, "\t");
				 if(i==2){
				 	char f[30];
				 	fecha(f,true);
				 	if(!strcmp(f,name)){
				 		puts("Lo sentimos tu llave a caducado genera una nueva");
				 		bandera=false;
				 		break;
				 	}
				 }
				 if(i==3)
				 	publica = atoi(name);
				 if(i==4)
				 	n = atoi(name);
				 i++;
			  }
			  break;
	    }
	    
	}
	fclose(ficherokeyring);
	
	if(bandera){
		puts("Ingrese el nombre del archivo que desea cifrar");
		scanf("%s",plaintext);
		puts("Ingrese el nombre del archivo donde desea guardar el texto cifrado");
		scanf("%s",ciphertext);
		
		int k = genera_k();
		int IV = genera_iv();
		int k1,k2;
		generar_llaves(k,&k1,&k2);
		int llave = cifrarsa(k,publica,n);
		int ivv = cifrarsa(IV,publica,n);	
		FILE *ficheroplaintext, *ficherociphertext;
		int i=0;
		ficheroplaintext = fopen(plaintext, "rb");
		ficherociphertext = fopen(ciphertext, "wb");
		fprintf(ficherociphertext,"%d\n",llave);
		fprintf(ficherociphertext,"%d\n",ivv);
		int p = fgetc(ficheroplaintext);
		int c = cifrar(p^IV,k1,k2);
		fprintf(ficherociphertext,"%c",c);
		while (feof(ficheroplaintext)==0){
		    p=fgetc(ficheroplaintext);
			 c=cifrar(p^c,k1,k2);
			 fprintf(ficherociphertext,"%c",c);
		}
		fclose(ficheroplaintext);
		fclose(ficherociphertext);
		puts("Listo presiona enter para continuar");
	}
	else
	   printf("Lo sentimos pero su usuario no está dado de alta porfavor creelo primero\n");
	puts("Presione enter para continuar...\n");	
	getchar();
   getchar();
 	system("clear");
}

void Decifrar(){
	char plaintext[50],ciphertext[50],nombre[50],linea[400];
	int publica,n,privada;
	bool bandera=false;
	puts("Ingrese el nombre del destinatario");
	scanf("%s",nombre);
	
	FILE *ficherokeyring;
	ficherokeyring = fopen("keyring.txt", "rb");
	
	while (feof(ficherokeyring)==0){
		 fgets(linea,400,ficherokeyring);	
		 char linea2[400];
		 strcpy(linea2,linea);
		 char * name=strtok(linea,"\t");
		 if(strcmp(name,nombre)==0){
		 	  int i=2;
		 	  bandera=true;
		     while (name != NULL){
			    name = strtok (NULL, "\t");
				 if(i==2){
				 	char f[30];
				 	fecha(f,true);
				 	if(!strcmp(f,name)){
				 		puts("Lo sentimos tu llave a caducado genera una nueva");
				 		bandera=false;
				 		break;
				 	}
				 }
				 if(i==3)
				 	publica=atoi(name);
				 if(i==4)
				 	n=atoi(name);
				 i++;
			  }
			  break;
	    }
	}
	fclose(ficherokeyring);
	if(bandera){
		puts("Ingrese el nombre del archivo que desea decifrar");
		scanf("%s",plaintext);
		puts("Ingrese el nombre del archivo donde desea guardar el texto en claro");
		scanf("%s",ciphertext);
		puts("Ingrese su clave privada");
		scanf("%d",&privada);
		FILE *ficheroplaintext, *ficherociphertext;
		int i=0;
		ficheroplaintext = fopen(plaintext, "rb");
		ficherociphertext = fopen(ciphertext, "wb");
		char key[40],IV[40];
	   fgets(key,400,ficheroplaintext);	
		//Obtenemos del archivo la llave k y la desciframos con la funcion de RSA(c^d mod n)
		int k = atoi(key);
		k = decifrarsa(k,privada,n);
	   fgets(IV,400,ficheroplaintext);
	   //Obtenemos del archivo el vector de inicializacion y la desciframos con la funcion de RSA
		int iv = atoi(IV);
		iv = decifrarsa(iv,privada,n);
		//Generamos nuevamente las llaves k1 y k2
		int k1,k2;
		generar_llaves(k,&k1,&k2);
		//leemos el primer carcter del texto cifrado
		int p = fgetc(ficheroplaintext);
		//ant oara guardar el ultimo p (caracter plaintext obtenido)
		int ant = p;
		//Lo desciframos la primera vez con el vector de inicializacion esto por le modo de operacion
		int c = decifrar(p,k1,k2)^iv;
		fprintf(ficherociphertext,"%c",c);
		//Aplicamos lo mismo para todos los caracteres del archivo
		while (feof(ficheroplaintext)==0){
			 p=fgetc(ficheroplaintext);
			 c=decifrar(p,k1,k2)^ant;
			 ant=p;
			 //Verificamos que en realidad llegue al fin de archivo
			 if((char)c == EOF)
		    	break;
		    
			 fprintf(ficherociphertext,"%c",c);
		}
		fclose(ficheroplaintext);
		fclose(ficherociphertext);
		puts("Listo presiona enter para continuar");
	}
	else
	   printf("Lo sentimos pero su usuario no está dado de alta porfavor creelo primero\n");
	puts("Presione enter para continuar...\n");	
	getchar();
   getchar();
 	system("clear");
}



