import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Ejemplo {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];  // Inicializa el IV con tamaño fijo
    private static final String CLAU = "ClaveSecreta1234";  // Clave de 16 caracteres

    public static byte[] xifraAES(String msg, String clau) throws Exception {
        // Obtenir els bytes de l’String
        byte[] msgEnBytes = msg.getBytes("UTF-8"); // Array de bytes del String msg

        // Genera IvParameterSpec (Aquí simulamos que el IV es fijo, pero debería generarse aleatoriamente)
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Genera hash de la clau usando SHA-256 y toma los primeros 16 bytes para la clau
        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauBytes = Arrays.copyOf(sha.digest(clau.getBytes("UTF-8")), 16);  // Solo los primeros 16 bytes
        SecretKeySpec sks = new SecretKeySpec(clauBytes, ALGORISME_XIFRAT);

        // Encrypt.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, sks, ivParameterSpec);

        // Combinar IV i part xifrada (en este caso no lo combinamos en la salida, ya que el IV es fijo).
        byte[] encriptado = cipher.doFinal(msgEnBytes);

        // return msgxifrat
        return encriptado;
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {

        // Extreure l'IV (en este caso no es necesario extraerlo porque es fijo)
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Genera hash de la clau usando SHA-256 y toma los primeros 16 bytes para la clau
        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauBytes = Arrays.copyOf(sha.digest(clau.getBytes("UTF-8")), 16);
        SecretKeySpec sks = new SecretKeySpec(clauBytes, ALGORISME_XIFRAT);

        // Desxifrar.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, sks, ivParameterSpec);

        byte[] descifrado = cipher.doFinal(bIvIMsgXifrat);

        // return String desxifrat
        return new String(descifrado, "UTF-8");
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
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats)); // Nota: puede haber caracteres no imprimibles
            System.out.println("DEC: " + desxifrat);
        }
    }
}
