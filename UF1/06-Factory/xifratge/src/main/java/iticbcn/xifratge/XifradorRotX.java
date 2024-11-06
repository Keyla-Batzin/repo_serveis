/* Version mas compleja y con complementos del cifrado de Cesar */

package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    private String msg = "";
    private int rot = 0;

    public final char[] MAY = "ABCÇDEFGHIJKLMÑOPQRSTUVWXYZÀÈÉÍÒÓÚÄËÏÖÜ".toCharArray();
    public final char[] MIN = "abcçdefghijklmñopqrstuvwxyzàèéíòóúäëïöü".toCharArray();

    // Método que cifra x posiciones
    public String xifraRotX(String msg, int x) {
        return rotaString(msg, true, x);
    }

    // Método que descifra x posiciones
    public String desxifraRotX(String msg, int x) {
        return rotaString(msg, false, x);
    }

    // Método que prueba todas las combinaciones posibles de rotación
    public void forcaBrutaRotX(String msg) {
        for (int i = 1; i <= 15; i++) { // Corregido para probar desde rotación 1 hasta 15
            System.out.println("Rotación +" + i + ": " + rotaString(msg, true, i));
        }
    }

    // Método genérico para cifrar o descifrar
    public String rotaString(String msg, boolean cifrar, int num) {
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
    public char rotaChar(char letra, char[] alfabeto, boolean cifrar, int num) {
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

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            // Intentar convertir la clave en un número para usarla como desplazamiento (rotación)
            rot = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau ha de ser un número per a la rotació.");
        }

        // Aplicar el cifrado de rotación
        String xifrat = xifraRotX(msg, rot);

        // Retornar el mensaje cifrado como un TextXifrat
        return new TextXifrat(xifrat.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            // Intentar convertir la clave en un número para usarla como desplazamiento (rotación)
            rot = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau ha de ser un número per a la rotació.");
        }

        // Convertir el texto cifrado a cadena de texto
        String msgCifrat = new String(xifrat.getBytes());

        // Aplicar el descifrado de rotación y retornar el resultado
        return desxifraRotX(msgCifrat, rot);
    }
}
