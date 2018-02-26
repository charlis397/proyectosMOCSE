/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancocliente;

/**
 * @author Samu
 */
public class Hash {
    
    private String llave;
    private String iv;

    public Hash(String punto_key, String punto_iv ) {
      llave=Hash.sha256(punto_key,24);
      iv=Hash.sha256(punto_iv,16);
    }

    public String getLlave() {
        return llave;
    }
    
    public String getIv() {
        return iv;
    }      
  
    public static String getHash(String txt, String hashType, int tam){
        try{
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {

                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().substring(0,tam);
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String sha256(String txt, int tamano){
        return Hash.getHash(txt, "SHA-256", tamano);
    }
}
