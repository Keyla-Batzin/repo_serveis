package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {

    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator key = KeyPairGenerator.getInstance("RSA");
        // Especificamos el tamaño de la llave en bits
        key.initialize(2048);
        // Generamos el par de llaves que se almacenan en el objeto KeyPair
        KeyPair keyPair = key.generateKeyPair();
        return keyPair;
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        byte[] xifrat =  cipher.doFinal(msg.getBytes());
        return xifrat;
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
        byte[] desxifratByte = cipher.doFinal(msgXifrat);
        return new String(desxifratByte);
    }
}
