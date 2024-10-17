
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "ClaveSecreta1234";

    public static byte[] xifraAES(String msg, String clau) throws Exception {
        // Obtenir els bytes de l’String
        byte[] msgEnBytes = msg.getBytes("UTF-8"); // Array de bytes del String msg

        // Genera IvParameterSpec
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Genera hash
        MessageDigest hash = MessageDigest.getInstance(ALGORISME_HASH); // Creamos un hash
        byte[] clauBytes = Arrays.copyOf(hash.digest(clau.getBytes("UTF-8")),MIDA_IV); // Genera un array de bytes de tamaño MIDA_IV a partir del hash de la cadena clau codificada en UTF-8
        SecretKeySpec sks = new SecretKeySpec(clauBytes, ALGORISME_XIFRAT); // Crea una clave secreta para el algoritmo de cifrado especificado usando byte[] clauBytes.

        // Encrypt.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, sks, ivParameterSpec);

        // Combinar IV i part xifrada.
       

        // return iv+msgxifrat
        return ;
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {

        // Extreure l'IV.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);

        // Extreure la part xifrada.
        SecretKeySpec sks = new SecretKeySpec(CLAU.getBytes("UTF-8"), ALGORISME_XIFRAT);

        // Fer hash de la clau
        cipher.init(Cipher.DECRYPT_MODE ,sks, new IvParameterSpec(iv));

        // Desxifrar.
        byte[] descifrado = cipher.doFinal(bIvIMsgXifrat);

        // return String desxifrat
        return descifrado.toString();
    }

    public static void main(String[] args) {
        String msgs[] = { "Lorem ipsum dicet",
                "Hola Andrés cómo está tu cuñado",
                "Àgora ïlla Ôtto" };

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                        + e.getLocalizedMessage());
            }
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }

}
