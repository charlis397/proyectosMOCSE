/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocliente;
import java.io.*;

import static java.lang.System.*;
/**
 *
 * @author Samu
 */
public class AES {
    private final int Nb = 4;
    private int Nk;
    private int Nr;
    private int[] schedule;
    private byte[][] state;
    private static byte[] input;
    private static byte[] key;


    public void AES_inicio(byte[] key) {
        int keyBits = key.length * 8;
        if (keyBits != 128 && keyBits != 192 && keyBits != 256) {
            throw new RuntimeException("Invalid AES key size (" + keyBits + " bits)");
        }
        Nk = keyBits >>> 5;
        Nr = Nk + 6;
        out.println("\n\tKey of 192 bits");
        out.println("\nKey Length: " + Nk + " words");
        out.println("Rounds    : " + Nr);
        AES.key = key;
        schedule = new int[Nb * (Nr + 1)];
        keyExpansion();
    }

    private int RotWord(int i) {
        return Integer.rotateLeft(i, 8);
    }

    private int SubWord(int i) {
        return ((utils.SBox.sub((i >>> 24))) << 24) |
                ((utils.SBox.sub((i >>> 16) & 0xff) & 0xff) << 16) |
                ((utils.SBox.sub((i >>> 8) & 0xff) & 0xff) << 8) |
                ((utils.SBox.sub((i) & 0xff) & 0xff));
    }

    private void keyExpansion() {

        int temp;
        int[] rcon = new int[11];
        for (int i = 0, k = 0; i < Nk; i++, k += 4) {
            schedule[i] =
                    ((key[k]) << 24) |
                            ((key[k + 1] & 0xff) << 16) |
                            ((key[k + 2] & 0xff) << 8) |
                            ((key[k + 3] & 0xff));
        }

        rcon[1] = (byte) 0x01 << 24;
        rcon[2] = (byte) 0x02 << 24;
        rcon[3] = (byte) 0x04 << 24;
        rcon[4] = (byte) 0x08 << 24;
        rcon[5] = (byte) 0x10 << 24;
        rcon[6] = (byte) 0x20 << 24;
        rcon[7] = (byte) 0x40 << 24;
        rcon[8] = (byte) 0x80 << 24;
        rcon[9] = (byte) 0x1b << 24;
        rcon[10] = (byte) 0x36 << 24;

        for (int i = Nk; i < Nb * (Nr + 1); i++) {
            temp = schedule[i - 1];
            if (i % Nk == 0) {
                temp = SubWord(RotWord(temp));
                temp ^= rcon[i / Nk];
            } else if (Nk > 6 && i % Nk == 4) {
                temp = SubWord(temp);
            }
            schedule[i] = schedule[i - Nk] ^ temp;  
        }
    }

    private void AddRoundKey(int round) {
        for (int c = 0; c < Nb; c++) {
            int w = schedule[4 * round + c];
            state[0][c] ^= (w >>> 24) & 0xff;
            state[1][c] ^= (w >>> 16) & 0xff;
            state[2][c] ^= (w >>> 8) & 0xff;
            state[3][c] ^= (w) & 0xff;
        }
    }

    private void SubBytes() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++)
                state[j][i] = utils.SBox.sub(state[j][i]);
        }
    }


    private void InvSubBytes() {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                state[j][i] = utils.SBox.invSub(state[j][i]);
    }

    private void ShiftRows() {
        byte temp;
        for (int i = 0; i < 4; i++)
            for (int k = 0; k < i; k++) {
                temp = state[i][0];
                System.arraycopy(state[i], 1, state[i], 0, 3);
                state[i][3] = temp;
            }
    }

    private void InvShiftRows() {
        byte temp;
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < i; k++) {
                temp = state[i][3];
                System.arraycopy(state[i], 0, state[i], 1, 3);
                state[i][0] = temp;
            }
        }
    }

    private void MixColumns() {
        byte[] tempCol;
        for (int j = 0; j < 4; j++) {
            tempCol = new byte[]{state[0][j], state[1][j], state[2][j], state[3][j]};
            state[0][j] = (byte) (utils.gmul2(tempCol[0]) ^ utils.gmul3(tempCol[1]) ^ tempCol[2] ^ tempCol[3]);
            state[1][j] = (byte) (tempCol[0] ^ utils.gmul2(tempCol[1]) ^ utils.gmul3(tempCol[2]) ^ tempCol[3]);
            state[2][j] = (byte) (tempCol[0] ^ tempCol[1] ^ utils.gmul2(tempCol[2]) ^ utils.gmul3(tempCol[3]));
            state[3][j] = (byte) (utils.gmul3(tempCol[0]) ^ tempCol[1] ^ tempCol[2] ^ utils.gmul2(tempCol[3]));
        }
    }

    private void InvMixColumns() {
        byte[] tempCol;
        for (int j = 0; j < 4; j++) {
            tempCol = new byte[]{state[0][j], state[1][j], state[2][j], state[3][j]};
            state[0][j] = (byte) (utils.gmul14(tempCol[0]) ^ utils.gmul11(tempCol[1]) ^ utils.gmul13(tempCol[2]) ^ utils.gmul9(tempCol[3]));
            state[1][j] = (byte) (utils.gmul9(tempCol[0]) ^ utils.gmul14(tempCol[1]) ^ utils.gmul11(tempCol[2]) ^ utils.gmul13(tempCol[3]));
            state[2][j] = (byte) (utils.gmul13(tempCol[0]) ^ utils.gmul9(tempCol[1]) ^ utils.gmul14(tempCol[2]) ^ utils.gmul11(tempCol[3]));
            state[3][j] = (byte) (utils.gmul11(tempCol[0]) ^ utils.gmul13(tempCol[1]) ^ utils.gmul9(tempCol[2]) ^ utils.gmul14(tempCol[3]));
        }
    }

    private void encrypt(byte[] input) {
        this.state = new byte[4][Nb];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < Nb; j++) {
                this.state[j][i] = input[4 * i + j];
            }
        }
/*        out.print("\nPlaintext : ");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                out.print(String.format("%c", state[j][i]));
            }
        }
*/        //printState("Initial State");
        AddRoundKey(0);
        for (int round = 1; round < Nr; round++) {
            SubBytes();
            ShiftRows();
            MixColumns();
            AddRoundKey(round);
        }
        SubBytes();
        ShiftRows();
        AddRoundKey(Nr);
    }

    private void decrypt(byte[] input) {
        this.state = new byte[4][Nb];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < Nb; j++) {
                this.state[j][i] = input[4 * i + j];
            }
        }
/*        out.print("\nCiphertext: ");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                out.print(String.format("%02x", state[j][i]));
            }
        }*/
        AddRoundKey(Nr);
        for (int round = 1; round < Nr; round++) {
            InvShiftRows();
            InvSubBytes();
            AddRoundKey(Nr - round);
            InvMixColumns();
        }
        InvShiftRows();
        InvSubBytes();
        AddRoundKey(0);
    }

    
    
    
    
    public String cifrarCBC(String t_input,String ellave, String eiv) throws UnsupportedEncodingException{

        //char=1 bytes
        //16 char = 128 bits -> input
        //24 char = 192 bits -> key
        //usar getBytes()
        
//        String iv = "asdflkjfhgurtatt";
//        String t_key = "holacomoestaholacomoesta";
        
        Hash hashish = new Hash(ellave,eiv);
        String t_key=hashish.getLlave();
        String iv=hashish.getIv();
        
        System.out.println("Llave: "+t_key);
        System.out.println("IV: "+iv); 
        
        char[] buffer = new char[16];//buffer de caracteres para extraer bloques de 128 bits del plaintext
        byte[] aux = new byte[16];//Auxiliar para XOR

        int tam_input = t_input.length();//Tamano del plaintext 
        int i_input=0;//Indice del plaintext
        int i,j;//Contadores
        
        StringBuilder t_cifrado = new StringBuilder();//Guarda el string de hexadecimales que representan el texto cifrado      
        StringBuilder aux_buffer = new StringBuilder();//Buffer para crear un string de buffer[]

        key=t_key.getBytes("UTF-8");//Se convierte la llave a bytes
        AES_inicio(key);//Se le manda la llave como bytes

        aux=iv.getBytes();//Se convierte el IV a bytes

        //MODO DE OPERACION
        while(i_input<tam_input){//Hasta que se llegue al final del plaintext
              
            for(i=0;i<16;i++){//Bloques de 16 caracteres
                if(i_input==tam_input){
                    for(j=i;j<16;j++){
                        buffer[j]=' ';//Padding con espacios
                    }
                    break;
                }else{
	                buffer[i]=t_input.charAt(i_input);//Extraccion 
	                i_input++;                	
                }
            }
            
            
            for(int ic:buffer){
                aux_buffer.append(String.format("%c",ic));//Conversion de char[] a String
            }
            input=aux_buffer.toString().getBytes("UTF-8");//Conversion de String a byte[]


            for(j=0;j<16;j++){
                input[j]=(byte)(input[j]^aux[j]);//Plaintext XOR IV
            }

            encrypt(input);//CIFRADO

            for (i = 0; i < 4; i++) {//Impresion de texto cifrado, usar stringbuilder para ir concatenando
                for (j = 0; j < 4; j++) {
                    t_cifrado.append(String.format("%02x", state[j][i]));//Guarda el cifrado en t_cifrado como string de hexadecimales. Debido a los caracteres no imprimibles
                    aux[4 * i + j]=state[j][i];//Obtenemos el nuevo IV igual al texto cifrado          
                }
            } 
            aux_buffer.setLength(0);//Reinicia el aux_buffer
        }
        
        //Se manda el texto cifrado como String
        return t_cifrado.toString();
    }
    
    
    public String descifrarCBC(String cifrado,String ellave, String eiv) throws UnsupportedEncodingException{
        System.out.println("Peticion: "+cifrado);
        
//        String iv = "asdflkjfhgurtatt";
//        String t_key = "holacomoestaholacomoesta";
        
        Hash hashish = new Hash(ellave,eiv);
        String t_key=hashish.getLlave();
        String iv=hashish.getIv();
        
        System.out.println("Llave: "+t_key);
        System.out.println("IV: "+iv);        
       
        byte[] aux = new byte[16];//Auxiliar en la extraccion del IV
        byte[] entrada = utils.hexToByte(cifrado);//El descifrado recibe el textocifrado como hexadecimal debido a los caracteres no imprimibles      
        char[] salida = new char[16];//Auxiliar en la obtencion del plaintext
        byte[] in = new byte[16];//Almacena bloques de 16 bytes de texto cifrado para descifrar
        
        int tam_input = (cifrado.length()/2);//S obtiene el tamano del texto cifrado -> /2 debido a que se recibe como hexadecimales de 2 caracteres por hexadecimal
        int i_input=0;//Se inicializa el indice del texto cifrado en cero
        int i,j;//Contadores    

        StringBuilder descifrado = new StringBuilder();//Guarda el string del plaintext
        StringBuilder aux_iv = new StringBuilder();//Guarda los caracteres para XOR    
        
        key=t_key.getBytes("UTF-8");//Se convierte la llave a bytes
        AES_inicio(key);//Se le manda la llave como bytes        
        
        aux_iv.setLength(0);
        for(i=0;i<16;i++){
            aux_iv.append(iv.charAt(i));//Para no modificar el iv original
        }
        
        //MODO DE OPERACION
        while(i_input<tam_input){            
            for(i=0;i<16;i++){//Bloques de 16 bytes
                in[i]=entrada[i_input];
                aux[i]=entrada[i_input];
                i_input++;

            }

            decrypt(in);//DESCIFRADO

            for (i = 0; i < 4; i++) {//Se obtiene el texto descifrado
                for (j = 0; j < 4; j++) {
                    in[4 * i + j]=state[j][i];       
                }
            }

            for(j=0;j<16;j++){
                salida[j]=(char)(in[j]^aux_iv.charAt(j));//descifrado XOR iv
                descifrado.append(String.format("%c",salida[j]));
            }
            
            aux_iv.setLength(0);
            for(i=0;i<16;i++){
                aux_iv.append((char)aux[i]);//nuevo IV texto cifrado
            }
        }

        return descifrado.toString();
    }

    
    
    
    public String cifrarCTR(String t_input,String ellave, String eiv) throws UnsupportedEncodingException{

//        String t_input = "select*from cuenta.ahorro,3-pedro-gonzalo,505440";
//        String t_key = "holacomoestaholacomoesta";
//        String iv = "asdflkjfhgurtatt";
        
        Hash hashish = new Hash(ellave,eiv);
        String t_key=hashish.getLlave();
        String iv=hashish.getIv();
        
        System.out.println("Llave: "+t_key);
        System.out.println("IV: "+iv);      
        
        char[] buffer = new char[16];//buffer de caracteres para extraer bloques de 128 bits del plaintext
        byte[] aux = new byte[16];//Manejo de IV
        
        int tam_input = t_input.length();//Tamano del plaintext 
        int i_input=0;//Indice del plaintext
        int i,j;//Contadores
         

        StringBuilder aux_buffer = new StringBuilder();//Buffer para crear un string de buffer[]
        StringBuilder t_cifrado = new StringBuilder();//Guarda el texto cifrado saliente
        
        key=t_key.getBytes("UTF-8");//Se convierte la llave en bytes
        AES_inicio(key);//Se le manda la llave como bytes        
        
        aux=iv.getBytes();//Se convierte el IV en bytes

        //MODO DE OPERACION
        while(i_input<tam_input){//Hasta que se llegue al final del plaintext
            encrypt(aux);//Se cifra el IV 
            
            for (i = 0; i < 4; i++) {//Se obtiene el IV cifrado
                for (j = 0; j < 4; j++) {
                    aux[4 * i + j]=state[j][i];            
                }
            }

            for(i=0;i<16;i++){//Bloques de 16 caracteres
                
                if(i_input==tam_input){
                    for(j=i;j<16;j++){
                        buffer[j]=' ';//Padding con espacios
                    }
                    break;
                }else{
	                buffer[i]=t_input.charAt(i_input);//Extraccion 
	                i_input++;                	
                }

            }
            
            for(int ic:buffer){
                aux_buffer.append(String.format("%c",ic));//Conversion de char[] a String
            }
            input=aux_buffer.toString().getBytes("UTF-8");//Conversion de String a byte[]
			
            for(j=0;j<16;j++){
                input[j]=(byte)(input[j]^aux[j]);//Plaintext XOR IVcifrado
                t_cifrado.append(String.format("%02x",input[j]));//Guarda el cifrado en t_cifrado como string de hexadecimales. Debido a los caracteres no imprimibles
            }

            for(j=0;j<16;j++)
                aux[j]=aux[j]++;//Suma en uno al IV

            aux_buffer.setLength(0);//Reinicia el aux_buffer


        }

        //Se manda el texto cifrado como String
        return t_cifrado.toString();
    }
    
    
    public String descifrarCTR(String cifrado,String ellave, String eiv) throws UnsupportedEncodingException{
        System.out.println("Peticion: "+cifrado);
        
//        String iv = "asdflkjfhgurtatt";
//        String t_key = "holacomoestaholacomoesta";
        
        Hash hashish = new Hash(ellave,eiv);
        String t_key=hashish.getLlave();
        String iv=hashish.getIv();
        
        System.out.println("Llave: "+t_key);
        System.out.println("IV: "+iv);          

        byte[] aux = new byte[16];//Manejo de IV
        byte[] entrada = utils.hexToByte(cifrado);//El descifrado recibe el textocifrado como hexadecimal debido a los caracteres no imprimibles      
        byte[] in = new byte[16];//Almacena bloques de 16 bytes de texto cifrado para descifrar
        
        int tam_input = (cifrado.length()/2);//S obtiene el tamano del texto cifrado -> /2 debido a que se recibe como hexadecimales de 2 caracteres por hexadecimal
        int i_input=0;//Se inicializa el indice del texto cifrado en cero
        int i,j;//Contadores   

        StringBuilder descifrado = new StringBuilder();//Guarda el texto descifrado
        
        key=t_key.getBytes("UTF-8");//Se convierte la llave en bytes
        AES_inicio(key);//Se le manda la llave como bytes        

        aux=iv.getBytes();//Se convierte el IV en bytes

        //MODO DE OPERACION
        while(i_input<tam_input){
            encrypt(aux);//Se cifra el IV
            
            for (i = 0; i < 4; i++) {//Obtencion del iv cifrado
                for (j = 0; j < 4; j++) {
                    aux[4 * i + j]=state[j][i];            
                }
            }

            for(i=0;i<16;i++){//Bloques de 16 caracteres
                in[i]=entrada[i_input];//Extraccion de 16 bytes de texto cifrado
                i_input++;                  
            }
            
            for(j=0;j<16;j++){
                in[j]=(byte)(in[j]^aux[j]);//cifrado XOR IVcifrado
                descifrado.append(String.format("%c",in[j]));//Guarda el texto descifrado cono caracteres
            }

            for(j=0;j<16;j++)
                aux[j]=aux[j]++;//Suma en uno al IV
        }

        return descifrado.toString();
    }
}