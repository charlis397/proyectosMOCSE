
#include <iostream>
#include <stdlib.h>
#include <io.h>

using namespace std;

int menu();

int main() 
{
menu();
return 0;
}


int codifix(int modo)
{
FILE *file; 
FILE *cript;
bool open = false;

char abc[] = " \n0123456789abcdefghijklmnñopqrstuvwxyzíóáúéïöäüABCDEFGHIJKLMNÑOPQRSTUVWXYZÍÓÁÚÉÏÖÄÜË¿?!¡.;,:-_()><&·$\\|@#~€¬/*-+=\"\b"; 
char endline[] = "\n"; 
char nada[] = "";

char filename[30];
char password[30];
char car[2]; 
char ccod[1]; 


int tabc = sizeof(abc) -2; 
int tcn, pcn, ccn = 0; 
int p=0;


cout << "Funcion codificar/decodificar iniciada" << endl << endl;



cout << "Introducir nombre del archivo (30 car max) [no acepta espacios]" << endl;
cin >> filename;
cout << endl << endl;


if(access(filename,0) == 0) 
{
open = true;
}
else {cout << "Arhivo (" << filename << ") no existe" << endl << endl;} 

if(open == true)
{



cout << "Introduce la contrasenya por favor [sin espacios]: ";
cin >> password;
cout << endl;



file = fopen(filename,"r+"); 
if(modo==0)
cript = fopen("codificado.txt","w");
if(modo==1) 
cript = fopen("decodificado.txt","w");


while(!feof(file))
{
fgets(car,2,file); 
if(password[p] == nada[0]) p =0; 
if(p==sizeof(password)) p=0;

for(int i = 0;i<=tabc;i++) 
{
if(abc[i] == car[0])
tcn = i;
}

for(int t = 0;t<=tabc;t++)
{
if(abc[t] == password[p])
pcn = t;
}

if(modo==0) 
{
ccn = tcn+pcn; 
if(ccn > tabc) ccn = ccn - tabc; 
} 

if(modo==1)
{
ccn = tcn-pcn; 
if(ccn < 0) ccn = ccn + tabc;
} 
strcpy(ccod,nada);
ccod[0] = abc[ccn];
ccod[1] = nada[0];
cout << ccod << "  =  " << abc[ccn] << "   " << tcn+pcn << endl;
fputs(ccod,cript); 

p++; 
} // fin while
fclose(file);
fclose(cript); 
}// fin open == true

cout << endl << "Funcion codificar/decodificar termidad con exito" << endl;

} 




int menu() 
{
bool salir=false;
bool select=false;
char item[1];
char items[] = "xcd";
while(salir==false)
{
select = false;


cout << endl << endl;
cout << "Menu" << endl;
cout << "c --> Codificar" << endl;
cout << "d --> Decodificar" << endl;
cout << "x --> Salir" << endl << endl;

cin >> item; 

cout << endl << endl;

if(item[0] == items[0])
{
salir = true;
select = true;
}
if(item[0] == items[1]) 
{
select = true;
codifix(0); 
}
if(item[0] == items[2]) 
{
select = true;
codifix(1);
}
if(select == false)
{
system("cls");
cout << "Esta opcion no existe" << endl << endl;
}
}
}