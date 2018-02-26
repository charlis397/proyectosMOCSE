/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocliente;

import java.io.*;
import java.net.*;

/**
 *
 * @author Samu
 */
public class PrBancoCliente {
    String ip_servidor;
    int puerto;
    String texto_cifrado;
    Socket cliente;
    String IV;
    String llave;
    public Socket conexion () throws IOException{
        
        //String ip_servidor= "192.168.0.2";//Ip del servidor
        //this.ip_servidor=ip_servidor;
        ip_servidor="localhost";
        puerto=8000;
        
        //this.puerto=puerto;//Puerto de entrada del servidor
        cliente = new Socket(ip_servidor,puerto);
        System.out.println("Conectado al servidor "+ip_servidor+" en "+puerto);
        return cliente;
    }
    public String inicioSesion(String nombre,String contr){
        //String res;
        String resfin = null;
        try{
            //System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente = conexion();
            getdatos();
            
            System.out.println("Conectado al remoto: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String codigo="uno";
            String codcfr;
            String nombrecfr;
            String contrcfr;
            
            AES aes = new AES();
            codcfr=aes.cifrarCBC(codigo,llave,IV);
            nombrecfr=aes.cifrarCBC(nombre,llave,IV);
            contrcfr= aes.cifrarCBC(contr,llave,IV);
            //System.out.println("codigo  "+codcfr);
            
            salida.writeUTF(codcfr);
            salida.flush();
            salida.writeUTF(nombrecfr);
            System.out.println("nombre  "+nombrecfr);
            salida.flush();
            salida.writeUTF(contrcfr);
            System.out.println("contrs  "+contrcfr);
            
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String res = entrada.readUTF();
            String respdes1=aes.descifrarCTR(res,llave,IV);
            String[] respdes2=respdes1.split(" ");
            StringBuilder resfin2=new StringBuilder();
            for(int i=0;i<respdes2.length;i++){
                resfin2=resfin2.append(respdes2[i]);
            }
            resfin=String.valueOf(resfin2);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
            
        }catch(IOException e){
            e.printStackTrace();
        }
        return resfin;
    }
    public void getdatos() throws IOException{
            Socket cliente =conexion();
            System.out.println("Conectando al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente = new Socket(ip_servidor,puerto);
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
           
            //recibe datos publicos                       ----------------------------------------------->Recibe 1:datos publicos en automatico al iniciar conexion
            String publicos=entrada.readUTF();
            System.out.println("Datos publicos recibidos: "+publicos);           
            
            //Extrae elementos de string
            Procesos extraccion = new Procesos();
            int[] datos=extraccion.extraePublicos(publicos);
            
            //Calcula Y=kP para llave e IV
            int[] datosSumaLlave = new int[5];
            int[] datosSumaIv = new int[5];
            
            datosSumaLlave[0]=datos[0];
            datosSumaLlave[1]=datos[1];
            datosSumaLlave[2]=datos[2];
            datosSumaLlave[3]=datos[3];
            datosSumaLlave[4]=datos[4];

            datosSumaIv[0]=datos[5];
            datosSumaIv[1]=datos[6];
            datosSumaIv[2]=datos[7];
            datosSumaIv[3]=datos[8];
            datosSumaIv[4]=datos[9];  
            
//            System.out.println(Arrays.toString(datosSumaLlave));
//            System.out.println(Arrays.toString(datosSumaIv));
            
            System.out.println("Y's de llave - iv del servidor: ("+datos[10]+","+datos[11]+")-("+datos[12]+","+datos[13]+")");
            
            DiffieHellman DHllave = new DiffieHellman(datosSumaLlave);
            DiffieHellman DHiv = new DiffieHellman(datosSumaIv);
                
            //calcula y manda Y=kP de llave e iv 
            // Yx-k / Yy-k / Yx-iv / Yy-iv /
            String ys=DHllave.doblado()+DHiv.doblado();
            salida.writeUTF(ys);                                    //-------------------------------------------> Envia 2: envía sus datos. Si lo necesitas aquí le agregas un carcter identificador al string de salida para que el servidor sepa que es para guardar llaves
            //Guarda las k's aleatorias usadas para obtener Y's de llave e iv
            int k_llave=DHllave.getK();
            int k_iv=DHiv.getK();
            
           
            //Procesa K para llave e iv con datos publicos recibidos
            sumapuntos finales = new sumapuntos();            
            int[] llavefinal = finales.Doblar(datos[10],datos[11],datosSumaLlave[0],k_llave,datosSumaLlave[2]);
            int[] ivfinal = finales.Doblar(datos[12],datos[13],datosSumaIv[0],k_iv,datosSumaIv[2]);
            
            //Guarda llave e iv como variables globales 
            
            this.llave="("+llavefinal[0]+","+llavefinal[1]+")";
            this.IV="("+ivfinal[0]+","+ivfinal[1]+")";                

            System.out.println("Llave: "+llave);//---------------------------------------------------------------------------->Estos datos ya deberan ser globales y deben ser iguales a los que tenga el servidor
            System.out.println("IV: "+IV);            
            
            //Otras peticiones cifradas----------------------------------------->Aqui ya se mandan otras peticiones
            /*
            AES aes = new AES();
            texto_cifrado=aes.cifrarCBC(peticion,llave,IV);//------------------>Así deben ser los parámetros para todos los cifrados y descifrados ahora
            
            System.out.println("Texto cifrado: "+texto_cifrado);
            
            salida.writeUTF(texto_cifrado);//Salida a Servidor*/
            
            
            
    }
    
    public String Registrarse(String nombre,String aPaterno,String aMaterno,String direccion,String telefono,String email,String contrasenia){
        String resfin = null;
        try{
            //System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            getdatos();
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="dos";
            String codcfr,nombrecfr,aPaternocfr,aMaternocfr,direccioncfr,telefonocfr,emailcfr,contraseniacfr;
            AES aes= new AES();
            
            codcfr=aes.cifrarCBC(cod,llave,IV);
            nombrecfr=aes.cifrarCBC(nombre,llave,IV);
            aPaternocfr=aes.cifrarCBC(aPaterno,llave,IV);
            aMaternocfr=aes.cifrarCBC(aMaterno,llave,IV);
            direccioncfr=aes.cifrarCBC(direccion,llave,IV);
            telefonocfr=aes.cifrarCBC(telefono,llave,IV);
            emailcfr=aes.cifrarCBC(email,llave,IV);
            contraseniacfr=aes.cifrarCBC(contrasenia,llave,IV);
            
            salida.writeUTF(codcfr);
            salida.flush();
            salida.writeUTF(nombrecfr);
            salida.flush();
            salida.writeUTF(aPaternocfr);
            salida.flush();
            salida.writeUTF(aMaternocfr);
            salida.flush();
            salida.writeUTF(direccioncfr);
            salida.flush();
            salida.writeUTF(telefonocfr);
            salida.flush();
            salida.writeUTF(emailcfr);
            salida.flush();
            salida.writeUTF(contraseniacfr);
            
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String res= entrada.readUTF();
            String resdes=aes.descifrarCTR(res,llave,IV);
            String[] auxres= resdes.split(" ");
            StringBuilder resprov= new StringBuilder();
            for(int i=0;i<auxres.length;i++){
                resprov=resprov.append(auxres[i]);
            }
            resfin=String.valueOf(resprov);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return resfin;
    }
    public String getNoCuenta(){
        String res = null;
        try{
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            getdatos();
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="tres";
            AES aes=new AES();
            
            String codcfr=aes.cifrarCBC(cod,llave,IV);
            salida.writeUTF(codcfr);
            salida.flush();
            
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String res1= entrada.readUTF();
            String aux1=aes.descifrarCTR(res1,llave,IV);
            String[] reprov=aux1.split(" ");
            StringBuilder aux=new StringBuilder();
            for(int i=0;i<reprov.length;i++){
                aux=aux.append(reprov[i]);
            }
            res=String.valueOf(aux);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
    public String getIdUsuario(String correo){
        String res = null;
        try{
            getdatos();
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="cuatro";
            String codcfr;
            String correocfr;
            AES aes=new AES();
            
            codcfr=aes.cifrarCBC(cod,llave,IV);
            correocfr=aes.cifrarCBC(correo,llave,IV);
            salida.writeUTF(codcfr);
            salida.flush();
            salida.writeUTF(correocfr);
            
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String res1= entrada.readUTF();
            String aux=aes.descifrarCTR(res1,llave,IV);
            String[] aux2=aux.split(" ");
            StringBuilder aux3= new StringBuilder();
            for(int i=0;i<aux2.length;i++){
                aux3=aux3.append(aux2[i]);
            }
            res=String.valueOf(aux3);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
    public String altaCuenta(int NIP,String fecha_vencimiento,int id_usuario){
        String res = null;
        try{
            getdatos();
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="cinco";
            AES aes= new AES();
            String nip=String.valueOf(NIP);
            String usuario=String.valueOf(id_usuario);
            
            String nipcfr=aes.cifrarCBC(nip,llave,IV);
            String codcfr=aes.cifrarCBC(cod,llave,IV);
            String fecfr=aes.cifrarCBC(fecha_vencimiento,llave,IV);
            String usucfr=aes.cifrarCBC(usuario,llave,IV);
            salida.writeUTF(codcfr);
            salida.flush();
            salida.writeUTF(nipcfr);
            salida.flush();
            salida.writeUTF(fecfr);
            salida.flush();
            salida.writeUTF(usucfr);
            
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String res1= entrada.readUTF();
            String resdc=aes.descifrarCTR(res1,llave,IV);
            String[] aux=resdc.split(" ");
            StringBuilder aux2= new StringBuilder();
            for(int i=0;i<aux.length;i++){
                aux2=aux2.append(aux[i]);
            }
            res=String.valueOf(aux2);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
    public String altaCredito(float adeudo,int anualidad,float interes,String fecha_corte,int nocuenta){
        String res = null;
        try{
            getdatos();
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="seis";
            AES aes=new AES();
            String adeud=Float.toString(adeudo);
            String anu=String.valueOf(anualidad);
            String inter=String.valueOf(interes);
            String cuen=String.valueOf(nocuenta);
            
            String codcfr=aes.cifrarCBC(cod,llave,IV);
            String adecfr=aes.cifrarCBC(adeud,llave,IV);
            String anucfr=aes.cifrarCBC(anu,llave,IV);
            String intercfr=aes.cifrarCBC(inter,llave,IV);
            String fechcfr=aes.cifrarCBC(fecha_corte,llave,IV);
            String cuencfr=aes.cifrarCBC(cuen,llave,IV);
            salida.writeUTF(codcfr);
            salida.flush();
            salida.writeUTF(adecfr);
            salida.flush();
            salida.writeUTF(anucfr);
            salida.flush();
            salida.writeUTF(intercfr);
            salida.flush();
            salida.writeUTF(fechcfr);
            salida.flush();
            salida.writeUTF(cuencfr);
            
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String res1= entrada.readUTF();
            String resdcf=aes.descifrarCTR(res1,llave,IV);
            String[] auxre=resdcf.split(" ");
            StringBuilder auxre2=new StringBuilder();
            for(int i=0;i<auxre.length;i++){
                auxre2=auxre2.append(auxre[i]);
            }
            res=String.valueOf(auxre2);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
    public String altaAhorro(String saldo,String nocuenta){
        String res = null;
        try{
            getdatos();
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="siete";
            AES aes= new AES();
            
            String codcf=aes.cifrarCBC(cod,llave,IV);
            String saldocf=aes.cifrarCBC(saldo,llave,IV);
            String nocuentacf=aes.cifrarCBC(nocuenta,llave,IV);
            salida.writeUTF(codcf);
            salida.flush();
            salida.writeUTF(saldocf);
            salida.flush();
            salida.writeUTF(nocuentacf);
            
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String res1= entrada.readUTF();
            String resdccf=aes.descifrarCTR(res1,llave,IV);
            String[] auxres=resdccf.split(" ");
            StringBuilder auxres2=new StringBuilder();
            for(int i=0;i<auxres.length;i++){
                auxres2=auxres2.append(auxres[i]);
            }
            res=String.valueOf(auxres2);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
    public String getNombre(String correo){
        String res = null;
        try{
            getdatos();
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="ocho";
            AES aes=new AES();
            
           String codcf=aes.cifrarCBC(cod,llave,IV);
           String corrcf=aes.cifrarCBC(correo,llave,IV);
            salida.writeUTF(codcf);
            salida.flush();
            salida.writeUTF(corrcf);
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String res1= entrada.readUTF();
            String redc=aes.descifrarCTR(res1,llave,IV);
            String[] auxres=redc.split(" ");
            StringBuilder auxre2=new StringBuilder();
            for(int i=0;i<auxres.length;i++){
                auxre2=auxre2.append(auxres[i]);
            }
            res=String.valueOf(auxre2);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
    }
    catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
    public String getNoCredito(String correo){
        String res = null;
        try{
            getdatos();
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="nueve";
            AES aes=new AES();
            
            String codcf=aes.cifrarCBC(cod,llave,IV);
            String correocf=aes.cifrarCBC(correo,llave,IV);
            salida.writeUTF(codcf);
            salida.flush();
            salida.writeUTF(correocf);
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String resd= entrada.readUTF();
            String res1=aes.descifrarCTR(resd,llave,IV);
            String[] auxres=res1.split(" ");
            StringBuilder auxres2=new StringBuilder();
            for(int i=0;i<auxres.length;i++){
                auxres2=auxres2.append(auxres[i]);
            }
            res=String.valueOf(auxres2);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
    }
    catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
    public String getAdeudoCredito(String correo){
        String res = null;
        try{
            getdatos();
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="diez";
            AES aes=new AES();
            
            String codcf=aes.cifrarCBC(cod,llave,IV);
            String correocf=aes.cifrarCBC(correo,llave,IV);
            salida.writeUTF(codcf);
            salida.flush();
            salida.writeUTF(correocf);
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String resd= entrada.readUTF();
            String res1=aes.descifrarCTR(resd,llave,IV);
            String[] auxres=res1.split(" ");
            StringBuilder auxres2=new StringBuilder();
            for(int i=0;i<auxres.length;i++){
                auxres2=auxres2.append(auxres[i]);
            }
            res=String.valueOf(auxres2);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
    }
    catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
    public String getFechaCorte(String correo){
        String res = null;
        try{
            getdatos();
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="once";
            AES aes=new AES();
            
            String codcf=aes.cifrarCBC(cod,llave,IV);
            String correocf=aes.cifrarCBC(correo,llave,IV);
            salida.writeUTF(codcf);
            salida.flush();
            salida.writeUTF(correocf);
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String resd= entrada.readUTF();
            String res1=aes.descifrarCTR(resd,llave,IV);
            String[] auxres=res1.split(" ");
            StringBuilder auxres2=new StringBuilder();
            for(int i=0;i<auxres.length;i++){
                auxres2=auxres2.append(auxres[i]);
            }
            res=String.valueOf(auxres2);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
    }
    catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
    public String getNoAhorro(String correo){
        String res = null;
        try{
            getdatos();
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="doce";
            AES aes=new AES();
            
            String codcf=aes.cifrarCBC(cod,llave,IV);
            String correocf=aes.cifrarCBC(correo,llave,IV);
            salida.writeUTF(codcf);
            salida.flush();
            salida.writeUTF(correocf);
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
           String resd= entrada.readUTF();
            String res1=aes.descifrarCTR(resd,llave,IV);
            String[] auxres=res1.split(" ");
            StringBuilder auxres2=new StringBuilder();
            for(int i=0;i<auxres.length;i++){
                auxres2=auxres2.append(auxres[i]);
            }
            res=String.valueOf(auxres2);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
    }
    catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
    public String getSaldoAhorro(String correo){
        String res = null;
        try{
            getdatos();
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="trece";
            AES aes=new AES();
            
            String codcf=aes.cifrarCBC(cod,llave,IV);
            String correocf=aes.cifrarCBC(correo,llave,IV);
            salida.writeUTF(codcf);
            salida.flush();
            salida.writeUTF(correocf);
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String resd= entrada.readUTF();
            String res1=aes.descifrarCTR(resd,llave,IV);
            String[] auxres=res1.split(" ");
            StringBuilder auxres2=new StringBuilder();
            for(int i=0;i<auxres.length;i++){
                auxres2=auxres2.append(auxres[i]);
            }
            res=String.valueOf(auxres2);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
    }
    catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
    public String getInteres(String correo){
        String res = null;
        try{
            getdatos();
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="catorce";
            AES aes=new AES();
            
            String codcf=aes.cifrarCBC(cod,llave,IV);
            String correocf=aes.cifrarCBC(correo,llave,IV);
            salida.writeUTF(codcf);
            salida.flush();
            salida.writeUTF(correocf);
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String resd= entrada.readUTF();
            String res1=aes.descifrarCTR(resd,llave,IV);
            String[] auxres=res1.split(" ");
            StringBuilder auxres2=new StringBuilder();
            for(int i=0;i<auxres.length;i++){
                auxres2=auxres2.append(auxres[i]);
            }
            res=String.valueOf(auxres2);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
    }
    catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
    
    public String updateAdeudo(String adeudo,String anualidad,String interes,String fecha_corte,String correo){
        String res = null;
        try{
            getdatos();
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="quince";
            AES aes=new AES();
            
            String codcf=aes.cifrarCBC(cod,llave,IV);
            String adeudocf=aes.cifrarCBC(adeudo,llave,IV);
            String anucf=aes.cifrarCBC(anualidad,llave,IV);
            String intercf=aes.cifrarCBC(interes,llave,IV);
            String fechcf=aes.cifrarCBC(fecha_corte,llave,IV);
            String correocf=aes.cifrarCBC(correo,llave,IV);
            salida.writeUTF(codcf);
            salida.flush();
            salida.writeUTF(adeudocf);
            salida.flush();
            salida.writeUTF(anucf);
            salida.flush();
            salida.writeUTF(intercf);
            salida.flush();
            salida.writeUTF(fechcf);
            salida.flush();
            salida.writeUTF(correocf);
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String resd= entrada.readUTF();
            String res1=aes.descifrarCTR(resd,llave,IV);
            String[] auxres=res1.split(" ");
            StringBuilder auxres2=new StringBuilder();
            for(int i=0;i<auxres.length;i++){
                auxres2=auxres2.append(auxres[i]);
            }
            res=String.valueOf(auxres2);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
    }
    catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
    
    public String getNombreUsuarioID(String id_Ahorro){
        String res = null;
        try{
            getdatos();
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="dieciseis";
            AES aes=new AES();
            
            String codcf=aes.cifrarCBC(cod,llave,IV);
            String correocf=aes.cifrarCBC(id_Ahorro,llave,IV);
            salida.writeUTF(codcf);
            salida.flush();
            salida.writeUTF(correocf);
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String resd= entrada.readUTF();
            String res1=aes.descifrarCTR(resd,llave,IV);
            String[] auxres=res1.split(" ");
            StringBuilder auxres2=new StringBuilder();
            for(int i=0;i<auxres.length;i++){
                auxres2=auxres2.append(auxres[i]);
            }
            res=String.valueOf(auxres2);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
    }
    catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
    public String setTransferencia(String emisor,String monto,String fecha_hora,String concepto,String id_Ahorro){
        String res = null;
        try{
            getdatos();
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="diecisiete";
            AES aes=new AES();
            
            String codcf=aes.cifrarCBC(cod,llave,IV);
            String emicf=aes.cifrarCBC(emisor,llave,IV);
            String montocf=aes.cifrarCBC(monto,llave,IV);
            String fechacf=aes.cifrarCBC(fecha_hora,llave,IV);
            String concf=aes.cifrarCBC(concepto,llave,IV);
            String idcf=aes.cifrarCBC(id_Ahorro,llave,IV);
            salida.writeUTF(codcf);
            salida.flush();
            salida.writeUTF(emicf);
            salida.flush();
            salida.writeUTF(montocf);
            salida.flush();
            salida.writeUTF(fechacf);
            salida.flush();
            salida.writeUTF(concf);
            salida.flush();
            salida.writeUTF(idcf);
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String resd= entrada.readUTF();
            String res1=aes.descifrarCTR(resd,llave,IV);
            String[] auxres=res1.split(" ");
            StringBuilder auxres2=new StringBuilder();
            for(int i=0;i<auxres.length;i++){
                auxres2=auxres2.append(auxres[i]);
            }
            res=String.valueOf(auxres2);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
    }
    catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
    
     public String getSaldoIDAhorro(String id_Ahorro){
        String res = null;
        try{
            getdatos();
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="dieciocho";
            AES aes=new AES();
            
            String codcf=aes.cifrarCBC(cod,llave,IV);
            String ahorcf=aes.cifrarCBC(id_Ahorro,llave,IV);
            salida.writeUTF(codcf);
            salida.flush();
            salida.writeUTF(ahorcf);
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String resd= entrada.readUTF();
            String res1=aes.descifrarCTR(resd,llave,IV);
            String[] auxres=res1.split(" ");
            StringBuilder auxres2=new StringBuilder();
            for(int i=0;i<auxres.length;i++){
                auxres2=auxres2.append(auxres[i]);
            }
            res=String.valueOf(auxres2);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
    }
        
    catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
     public String updateAhorro(String id_Ahorro,String montoUpdateReceptor){
        String res = null;
        try{
            getdatos();
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="diecinueve";
            AES aes=new AES();
            
            String codcf=aes.cifrarCBC(cod,llave,IV);
            String ahorcf=aes.cifrarCBC(id_Ahorro,llave,IV);
            String moncf=aes.cifrarCBC(montoUpdateReceptor,llave,IV);
            salida.writeUTF(codcf);
            salida.flush();
            salida.writeUTF(ahorcf);
            salida.flush();
            salida.writeUTF(moncf);
            
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String resd= entrada.readUTF();
            String res1=aes.descifrarCTR(resd,llave,IV);
            String[] auxres=res1.split(" ");
            StringBuilder auxres2=new StringBuilder();
            for(int i=0;i<auxres.length;i++){
                auxres2=auxres2.append(auxres[i]);
            }
            res=String.valueOf(auxres2);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
    }
        
    catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
     public String getAnualidad(String correo){
        String res = null;
        try{
            getdatos();
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="veinte";
            AES aes=new AES();
            
            String codcf=aes.cifrarCBC(cod,llave,IV);
            String corrcf=aes.cifrarCBC(correo,llave,IV);
            salida.writeUTF(codcf);
            salida.flush();
            salida.writeUTF(corrcf);
            
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String resd= entrada.readUTF();
            String res1=aes.descifrarCTR(resd,llave,IV);
            String[] auxres=res1.split(" ");
            StringBuilder auxres2=new StringBuilder();
            for(int i=0;i<auxres.length;i++){
                auxres2=auxres2.append(auxres[i]);
            }
            res=String.valueOf(auxres2);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
    }
        
    catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
     public String setPago(String fecha,String monto,String concepto,String id_credito){
        String res = null;
        try{
            getdatos();
            System.out.println("Conectadon al servidor "+ip_servidor+" en "+puerto);
            //Socket cliente =conexion();
            
            System.out.println("Conectado a: "+cliente.getRemoteSocketAddress());
            OutputStream salidaAServidor = cliente.getOutputStream();
            DataOutputStream salida = new DataOutputStream(salidaAServidor);
            String cod="veintiuno";
            AES aes=new AES();
            
            String codcf=aes.cifrarCBC(cod,llave,IV);
            String fechcf=aes.cifrarCBC(fecha,llave,IV);
            String moncf=aes.cifrarCBC(monto,llave,IV);
            String concf=aes.cifrarCBC(concepto,llave,IV);
            String idcf=aes.cifrarCBC(id_credito,llave,IV);
            salida.writeUTF(codcf);
            salida.flush();
            salida.writeUTF(fechcf);
            salida.flush();
            salida.writeUTF(moncf);
            salida.flush();
            salida.writeUTF(concf);
            salida.flush();
            salida.writeUTF(idcf);
            
            InputStream entradaDesdeServidor = cliente.getInputStream();
            DataInputStream entrada = new DataInputStream(entradaDesdeServidor);
            
            //System.out.println("Servidor: "+entrada.readUTF());
            String resd= entrada.readUTF();
            String res1=aes.descifrarCTR(resd,llave,IV);
            String[] auxres=res1.split(" ");
            StringBuilder auxres2=new StringBuilder();
            for(int i=0;i<auxres.length;i++){
                auxres2=auxres2.append(auxres[i]);
            }
            res=String.valueOf(auxres2);
            //cliente.close();
            salidaAServidor.close();
            salida.close();
            entrada.close();
    }
        
    catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
}