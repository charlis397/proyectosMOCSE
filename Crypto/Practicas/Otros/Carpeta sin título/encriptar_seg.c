#include <stdio.h>

void encriptarArchivo();
void desencriptarArchivo();

int main()
{
    int opcion;
    
    printf( "Escoja una opcion(1-3):\n" );
    printf( " 1 - Encriptar archivo .txt.\n" );
    printf( " 2 - Desencriptar archivo .txc\n" );
    printf( " 3 - Salir\n" );
    printf( "Opcion: " );
    scanf( "%i", &opcion );
    if( opcion == 1 )
    {
        encriptarArchivo();
    }
    else if( opcion == 2 )
    {
         desencriptarArchivo();
    }
}

void encriptarArchivo()
{
     FILE *encriptame;
     FILE *encriptado;
     char c;
     
     printf( "Importante:\n" );
     printf( " * El archivo a ser encriptado debe llamarse 'encriptame.txt'.\n" );
     printf( " * El archivo 'encriptame.txt' debe estar en la raiz del ejecutable.\n" );
     printf( "Pulse Enter para proceder con el proceso de encriptacion.\n" );
     
     getchar();
     
     encriptame = fopen( "encriptame.txt", "r" );
     encriptado = fopen( "encriptado.txc", "w" );
     
     printf("\n******************************\nCaracteres:\tAscii:\n");
     
     while( !feof( encriptame ) )
     {      c=fgetc( encriptame );
            if (c>0) fputc( c + 3, encriptado );
            printf("%c -> \t\t%d\n",c,c);
     }
     
     printf("******************************\n\n");
     
     fclose( encriptame );
     fclose( encriptado );
     
     printf( "Listo. Pulsa Enter para salir.\n" );
     
     getchar();
}

void desencriptarArchivo()
{
     FILE *encriptado;
     FILE *desencriptado;
     char c;
     
     printf( "Importante:\n" );
     printf( " * El archivo a ser desencriptado debe llamarse 'encriptado.txc'.\n" );
     printf( " * El archivo 'encriptado.txc' debe estar en la raiz del ejecutable.\n" );
     printf( "Pulse Enter para proceder con el proceso de desencriptacion.\n" );
     
     getchar();
     
     encriptado = fopen( "encriptado.txc", "r" );
     desencriptado = fopen( "desencriptado.txt", "w" );
     
     printf("\n******************************\nCaracteres:\tAscii:\n");
     
     while( !feof(encriptado))
     {
            c=fgetc(encriptado);
            if (c>0) fputc( ((int)c) - 3, desencriptado );
            printf("%c -> \t\t%d\n",c,c);
     }
     
     printf("******************************\n\n");
     
     fclose( encriptado );
     fclose( desencriptado );
     
     printf( "Listo. Pulsa Enter para salir.\n" );
     
     getchar();
     
}