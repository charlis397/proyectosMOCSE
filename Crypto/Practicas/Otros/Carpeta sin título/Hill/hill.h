int *crear_matriz(); //Crear matriz con memoria dinamica
void guardar_valor(int *matriza, int i, int j, int valor); //Guardar Valor en matriz
int det_matriz(int *matriz); //Determinante de matriz
int tiene_inversa(int *matriz); //Determinar si tiene inversa
int *matriz_cof(int *matriz); //Conseguir matriz cofactores
int *inversa(int *matriz); //Matriz inversa
int *matriz_cof_traspuesta(int *matriz_cof); //Matriz cofactores traspuesta
int inverso_multiplicativo(int *matriz); //Determinar inverso multiplicativo
int *matriz_inv(int *matrix_cof_tras, int add_inverse); //Modulo de matriz inversa
int *matriztexto(char *plaintext);//matriz del texto en claro
