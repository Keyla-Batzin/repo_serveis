package UF1.Activitat_01;

/*
 * Programa que es un codificador y descodificador de cifrado César 13 (ROT13).
 */

public class Rot13 {
    // Arrays con las letras del alfabeto
    public static final char[] may = {'A','B','C','Ç','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z','À','È','É','Í','Ò','Ó','Ú'};
    public static final char[] min = {'a','b','c','ç','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z','à','è','é','í','ò','ó','ú'};

    public static void main(String[] args) {
        /*
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();
        */
        
        String textoPrueba = "Ñ";

        // Cifrar
        String cifrado = xifraRot13(pasaAChar(textoPrueba));
        System.out.println("Texto cifrado: " + cifrado);
        
        // Descifrar
        String descifrado = desxifraRot13(pasaAChar(cifrado));
        System.out.println("Texto descifrado: " + descifrado);

        /*
        sc.close();
        */
    }

    // Función para cifrar usando ROT13
    public static String xifraRot13(char[] lista) {
        
        // Recorre la lista de caracteres y cifra
        for (int i = 0; i < lista.length; i++) {
            char letraActual = lista[i];

            // Comprobar si es mayúscula
            for (int y = 0; y < may.length; y++) {
                if (letraActual == may[y]) {
                    // Aplica la rotación de 13 posiciones para mayúsculas
                    lista[i] = may[(y + 13) % may.length];
                    break; // No es necesario seguir buscando después de encontrar la letra
                }
            }

            // Comprobar si es minúscula
            for (int y = 0; y < min.length; y++) {
                if (letraActual == min[y]) {
                    // Aplica la rotación de 13 posiciones para minúsculas
                    lista[i] = min[(y + 13) % min.length];
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

            // Comprobar si es mayúscula
            for (int y = 0; y < may.length; y++) {
                if (letraActual == may[y]) {
                    // Aplica la rotación inversa de 13 posiciones para mayúsculas
                    lista[i] = may[(y - 13) % may.length];
                    break;
                }
            }

            // Comprobar si es minúscula
            for (int y = 0; y < min.length; y++) {
                if (letraActual == min[y]) {
                    // Aplica la rotación inversa de 13 posiciones para minúsculas
                    lista[i] = min[(y - 13) % min.length];
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
