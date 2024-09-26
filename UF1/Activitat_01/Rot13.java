package UF1.Activitat_01;

/*
 * Programa que es un codificador y descodificador de cifrado César 13 (ROT13).
 */

public class Rot13 {
    // Arrays con las letras del alfabeto
    public static final char[] MAY = {'A','B','C','Ç','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z','À','È','É','Í','Ò','Ó','Ú'};
    public static final char[] MIN = {'a','b','c','ç','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z','à','è','é','í','ò','ó','ú'};

    public static void main(String[] args) {
        String texto = "Hola :D";
        String texto1 = "Ñoquis";
        String texto2 = "ãó";

        // Cifrar
        String cifrado = xifraRot13(pasaAChar(texto));
        String cifrado1 = xifraRot13(pasaAChar(texto1));
        String cifrado2 = xifraRot13(pasaAChar(texto2));
        System.out.println("Texto cifrado: " + cifrado + "\n" + cifrado1 + "\n" + cifrado2);

        // Descifrar
        String descifrado = desxifraRot13(pasaAChar(cifrado));
        String descifrado1 = desxifraRot13(pasaAChar(cifrado1));
        String descifrado2 = desxifraRot13(pasaAChar(cifrado2));
        System.out.println("Texto descifrado: " + descifrado + "\n" + descifrado1 + "\n" + descifrado2);
    }

    // Función para cifrar usando ROT13
    public static String xifraRot13(char[] lista) {
        // Recorre la lista de caracteres y cifra
        for (int i = 0; i < lista.length; i++) {
            char letraActual = lista[i];

            // Comprobar si es MAYúscula
            for (int y = 0; y < MAY.length; y++) {
                if (letraActual == MAY[y]) {
                    lista[i] = MAY[(y + 13) % MAY.length];
                    break;
                }
            }

            // Comprobar si es MINúscula
            for (int y = 0; y < MIN.length; y++) {
                if (letraActual == MIN[y]) {
                    lista[i] = MIN[(y + 13) % MIN.length];
                    break;
                }
            }
        }

        return charToString(lista);
    }

    // Función para descifrar usando ROT13
    public static String desxifraRot13(char[] lista) {
        // Recorre la lista de caracteres y descifra
        for (int i = 0; i < lista.length; i++) {
            char letraActual = lista[i];

            // Comprobar si es MAYúscula
            for (int y = 0; y < MAY.length; y++) {
                if (letraActual == MAY[y]) {
                    int nuevaPos = (y - 13 + MAY.length) % MAY.length;  // Ajuste para evitar valores negativos
                    lista[i] = MAY[nuevaPos];
                    break;
                }
            }

            // Comprobar si es MINúscula
            for (int y = 0; y < MIN.length; y++) {
                if (letraActual == MIN[y]) {
                    int nuevaPos = (y - 13 + MIN.length) % MIN.length;  // Ajuste para evitar valores negativos
                    lista[i] = MIN[nuevaPos];
                    break;
                }
            }
        }

        return charToString(lista);
    }

    /* Pasar un String a un array de char */
    public static char[] pasaAChar(String texto) {
        return texto.toCharArray();
    }

    /* Pasar de un array de char a un String */
    public static String charToString(char[] cadena) {
        return new String(cadena);
    }
}
