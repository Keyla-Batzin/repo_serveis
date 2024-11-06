package iticbcn.xifratge;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV]; // Inicializa el IV con tamaño fijo
    private static final String CLAU = "ClaveSecreta1234"; // Clave de 16 caracteres

    public static byte[] xifraAES(String msg, String clau) throws Exception {
        // Obtenir els bytes de l’String
        byte[] msgEnBytes = msg.getBytes("UTF-8"); // Array de bytes del String msg

        // Genera IvParameterSpec (Aquí simulamos que el IV es fijo, pero debería
        // generarse aleatoriamente)
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Genera hash de la clau usando SHA-256 y toma los primeros 16 bytes para la
        // clau
        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauBytes = Arrays.copyOf(sha.digest(clau.getBytes("UTF-8")), 16); // Solo los primeros 16 bytes
        SecretKeySpec sks = new SecretKeySpec(clauBytes, ALGORISME_XIFRAT);

        // Encrypt.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, sks, ivParameterSpec);

        // Combinar IV i part xifrada (en este caso no lo combinamos en la salida, ya
        // que el IV es fijo).
        byte[] encriptado = cipher.doFinal(msgEnBytes);

        // return msgxifrat
        return encriptado;
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {

        // Extreure l'IV (en este caso no es necesario extraerlo porque es fijo)
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Genera hash de la clau usando SHA-256 y toma los primeros 16 bytes para la
        // clau
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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] xifratBytes = xifraAES(msg, clau);
            return new TextXifrat(xifratBytes);
        } catch (Exception e) {
            throw new ClauNoSuportada("Error en el proceso de cifrado: " + e.getMessage());
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            byte[] xifratBytes = xifrat.getBytes(); // Obtener el array de bytes desde TextXifrat
            return desxifraAES(xifratBytes, clau);
        } catch (Exception e) {
            throw new ClauNoSuportada("Error en el proceso de descifrado: " + e.getMessage());
        }
    }

}
