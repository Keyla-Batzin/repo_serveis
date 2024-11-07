package iticbcn.xifratge;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class ClauPublica {
    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator key = KeyPairGenerator.getInstance("RSA");

        // Especificamos el tamaño de la llave en bits
        key.initialize(2048);

        // Generamos el par de llaves que se almacenan en el objeto KeyPair
        KeyPair keyPair = key.generateKeyPair();
        // Obtenemos ambas llaves por separado
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        return keyPair;
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        byte[] x = new byte[0];
        return x;
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey ClauPrivada) {
        return new String();
    }
}
