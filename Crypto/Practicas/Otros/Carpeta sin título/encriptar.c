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
    else
    {
        exit( 0 );
    }
    
    return 0;
}

void encriptarArchivo()
{
     FILE *encriptame;
     FILE *encriptado;
     
     system( "cls" );
     printf( "Importante:\n" );
     printf( " * El archivo a ser encriptado debe llamarse 'encriptame.txt'.\n" );
     printf( " * El archivo 'encriptame.txt' debe estar en la raiz del ejecutable.\n" );
     printf( "Pulse Enter para proceder con el proceso de encriptacion.\n" );
     
     getchar();
     
     encriptame = fopen( "encriptame.txt", "r" );
     encriptado = fopen( "encriptado.txc", "w" );
     
     while( !feof( encriptame ) )
     {
            fputc( fgetc( encriptame ) + 3, encriptado );
     }
     
     fclose( encriptame );
     fclose( encriptado );
     
     printf( "Listo. Pulsa Enter para salir.\n" );
     
     getchar();
     
     exit( 0 );
}

void desencriptarArchivo()
{
     FILE *encriptado;
     FILE *desencriptado;
     
     system( "cls" );
     printf( "Importante:\n" );
     printf( " * El archivo a ser desencriptado debe llamarse 'encriptado.txc'.\n" );
     printf( " * El archivo 'encriptado.txc' debe estar en la raiz del ejecutable.\n" );
     printf( "Pulse Enter para proceder con el proceso de desencriptacion.\n" );
     
     getchar();
     
     encriptado = fopen( "encriptado.txc", "r" );
     desencriptado = fopen( "desencriptado.txt", "w" );
     
     while( !feof( encriptado ) )
     {
            fputc( fgetc( encriptado ) - 3, desencriptado );
     }
     
     fclose( encriptado );
     fclose( desencriptado );
     
     printf( "Listo. Pulsa Enter para salir.\n" );
     
     getchar();
     
     exit( 0 );
}