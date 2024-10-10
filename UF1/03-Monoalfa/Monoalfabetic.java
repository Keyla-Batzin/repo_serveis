/* Programa que cifrara y desfirara teniendo como referencia un alfabeto aleatorio generado por un modulo */

public class Monoalfabetic {
    public static final char[] MAY = "ABCÇDEFGHIJKLMÑOPQRSTUVWXYZÀÈÉÍÒÓÚÄËÏÖÜ".toCharArray();
    public static final char[] MIN = "abcçdefghijklmñopqrstuvwxyzàèéíòóúäëïöü".toCharArray();

    // Método que cifra x posiciones
    public static String xifraRotX(String msg, int x) {
        return rotaString(msg, true, x);
    }

    // Método que descifra x posiciones
    public static String desxifraRotX(String msg, int x) {
        return rotaString(msg, false, x);
    }

    // Método que prueba todas las combinaciones posibles de rotación
    public static void forcaBrutaRotX(String msg) {
        for (int i = 1; i <= 15; i++) { // Corregido para probar desde rotación 1 hasta 15
            System.out.println("Rotación +" + i + ": " + rotaString(msg, true, i));
        }
    }

    // Método genérico para cifrar o descifrar
    public static String rotaString(String msg, boolean cifrar, int num) {
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) {
            char letra = msg.charAt(i);
            if (Character.isUpperCase(letra)) {
                letra = rotaChar(letra, MAY, cifrar, num);
            } else if (Character.isLowerCase(letra)) {
                letra = rotaChar(letra, MIN, cifrar, num);
            }
            resultado.append(letra); // Añadir directamente la letra (rotada o no)
        }

        return resultado.toString();
    }

    // Método auxiliar para rotar una letra dentro del array de MAY o MIN
    public static char rotaChar(char letra, char[] alfabeto, boolean cifrar, int num) {
        for (int y = 0; y < alfabeto.length; y++) {
            if (letra == alfabeto[y]) {
                int nuevaPos;
                if (cifrar) {
                    nuevaPos = (y + num) % alfabeto.length;
                } else {
                    nuevaPos = (y - num + alfabeto.length) % alfabeto.length;
                }
                return alfabeto[nuevaPos];
            }
        }
        return letra; // Si no es una letra del alfabeto, devolver la letra original
    }

    public static void main(String[] args) {
        String text = "Hola me llamo Keyla :D";

        // Cifrar con rotación 5
        String cifrado = xifraRotX(text, 5);
        System.out.println("Texto cifrado: " + cifrado);

        // Descifrar con rotación 5
        String descifrado = desxifraRotX(cifrado, 5);
        System.out.println("Texto descifrado: " + descifrado);

        // Fuerza bruta para probar todas las rotaciones
        System.out.println("\nPrueba de fuerza bruta:");
        forcaBrutaRotX(text);
    }
}