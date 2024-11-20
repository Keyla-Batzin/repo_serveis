
/* */
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    private static final char[] charset = "abcdefABCDEF1234567890!".toCharArray();
    public int npass = 0;  // Contador para las pruebas de fuerza bruta

    public String getSHA512AmbSalt(String pw, String salt) {
        String passwordGenerada = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(pw.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            passwordGenerada = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return passwordGenerada;
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String algoritmo = "PBKDF2WithHmacSHA1";
        int derivedKeyLength = 512;
        int iterations = 4096;

        KeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), iterations, derivedKeyLength);
        SecretKeyFactory f = SecretKeyFactory.getInstance(algoritmo);
        byte[] hash = f.generateSecret(spec).getEncoded();

        // Convertir byte[] a hexadecimal usando HexFormat
        HexFormat hexFormat = HexFormat.of();
        String hashHex = hexFormat.formatHex(hash);
        return hashHex;
    }

    // Método de fuerza bruta
    public String forcaBruta(String alg, String hash, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        char[] password = new char[6];  // Longitud máxima de la contraseña
        npass = 0;  // Reiniciar el contador de contraseñas probadas

        // Bucle anidado para generar todas las combinaciones de contraseñas de 1 a 6 caracteres
        for (int i1 = 0; i1 < charset.length; i1++) {
            password[0] = charset[i1];
            String generatedHash = generarHash(new String(password, 0, 1), salt, alg); //Transformamos a String el char[] (como un subtring que coge una longitud)
            npass++;
            if (generatedHash.equals(hash)) {
                return new String(password, 0, 1);  // Contraseña encontrada
            }

            for (int i2 = 0; i2 < charset.length; i2++) {
                password[1] = charset[i2];
                generatedHash = generarHash(new String(password, 0, 2), salt, alg);
                npass++;
                if (generatedHash.equals(hash)) {
                    return new String(password, 0, 2);
                }

                for (int i3 = 0; i3 < charset.length; i3++) {
                    password[2] = charset[i3];
                    generatedHash = generarHash(new String(password, 0, 3), salt, alg);
                    npass++;
                    if (generatedHash.equals(hash)) {
                        return new String(password, 0, 3);
                    }

                    for (int i4 = 0; i4 < charset.length; i4++) {
                        password[3] = charset[i4];
                        generatedHash = generarHash(new String(password, 0, 4), salt, alg);
                        npass++;
                        if (generatedHash.equals(hash)) {
                            return new String(password, 0, 4);
                        }

                        for (int i5 = 0; i5 < charset.length; i5++) {
                            password[4] = charset[i5];
                            generatedHash = generarHash(new String(password, 0, 5), salt, alg);
                            npass++;
                            if (generatedHash.equals(hash)) {
                                return new String(password, 0, 5);
                            }

                            for (int i6 = 0; i6 < charset.length; i6++) {
                                password[5] = charset[i6];
                                generatedHash = generarHash(new String(password, 0, 6), salt, alg);
                                npass++;
                                if (generatedHash.equals(hash)) {
                                    return new String(password, 0, 6);  // Contraseña encontrada
                                }
                            }
                        }
                    }
                }
            }
        }

        return null;  // Si no se encuentra la contraseña
    }

    // Método que decide cuál algoritmo usar según el valor de `alg`
    private String generarHash(String password, String salt, String alg) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if ("SHA-512".equals(alg)) {
            return getSHA512AmbSalt(password, salt);  // Usar SHA-512
        } else if ("PBKDF2".equals(alg)) {
            return getPBKDF2AmbSalt(password, salt);  // Usar PBKDF2
        }
        return null;  // En caso de que el algoritmo no sea reconocido
    }
    
    

    // Método para obtener el intervalo de tiempo entre dos puntos
    public String getInterval(long t1, long t2) {
        long diff = t2 - t1;
        long seconds = diff / 1000;
        long milliseconds = diff % 1000;
        return String.format("%d.%03d seconds", seconds, milliseconds);
    }
}
