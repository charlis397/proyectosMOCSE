/*
	Practica 1
	Ejercicio 2
*/

#include <iostream>
#include <fstream>

using namespace std;

bool isInAlphabet(char c, char alphabet[2][26],int tam) //checamos si el caracter "c" se encuentra en el arreglo
{
	for(int i=0; i<tam; i++)
	{
		if(c == alphabet[1][i])
			return true;
	}
	
	return false;
}

int row(char c, char alphabet[2][26], int col) //devuelve el renglon en donde se encuentra el caractet "c"
{
	for(int i=0; i<26; i++)
	{
		if(c == alphabet[col][i])
			return i;
	}
}

string encrypt(string s, char alphabet[2][26])
{
	string s_enc;
	for(int i=0; i<s.size(); i++)		
	{
		if(s.at(i) != ' ' )
			s_enc.push_back(alphabet[1][row(s[i],alphabet,0)]);
		else
			s_enc.push_back(' ');
	}
	return s_enc;
}

string decrypt(string s, char alphabet[2][26])
{
	string s_dec;
	for(int i=0; i<s.size(); i++)		
	{
		if(s.at(i) != ' ' )
			s_dec.push_back(alphabet[0][row(s[i],alphabet,1)]);
		else
			s_dec.push_back(' ');
	}
	return s_dec;
}


int main()
{
	string s,s1,key_word,ciphertext;
	char alphabet_c [2][26];
	int a,b,m=26,i;

   
	cout << "Introduce la cadena a cifrar: ";
	getline(cin, s);
	cout << "Introduce la llave: " << endl;
	cin >> key_word;

	for(int i=0;i<s.size();i++) //pasamos a mayusculas nuestro texto a cifrar
		s[i]=toupper(s[i]);
	
	for(int i=0;i<key_word.size();i++) //pasamos a mayusculas nuestra key word
		key_word[i]=toupper(key_word[i]);
	
	for(int alph=0; alph< 26; alph++)
		alphabet_c[0][alph] = 65+alph;		//llenamos nuestra primera columna con el alfabeto

	for( i=0; i<key_word.size() ; i++)		//agregamos en la segunda columna la key word
		alphabet_c[1][i] = key_word[i];
	
	int tam= i;
	for(int sc=0; sc<m; sc++)	//generamos el alfabeto para nuestro cifrado
	{
		if(!isInAlphabet(sc+65,alphabet_c,tam))//si el caracter no se encuentra dentro de nuestro arreglo lo agregamos
		{
			alphabet_c[1][tam] = (65+sc);
			tam++;
		}
	}
	
	cout << "Texto plano: " << s << endl;
	ciphertext = encrypt(s,alphabet_c);
	cout << "Texto cifrado: " << ciphertext << endl;
	cout << "Texto descifrado: " << decrypt(ciphertext,alphabet_c) << endl;
	

   return 0;
}
