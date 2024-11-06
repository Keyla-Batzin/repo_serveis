package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {
    private String msg = "";
    public final char[] MAY = "ABCÇDEFGHIJKLMÑOPQRSTUVWXYZÀÈÉÍÒÓÚÄËÏÖÜ".toCharArray();
    public final char[] RANDOM = new char[MAY.length];
    public long clau = 0; // Clave como long
    public final Random intRandom = new Random();

    public XifradorPolialfabetic(String msg, String clau) throws ClauNoSuportada {
        this.msg = msg;
        // Intentamos convertir la clave a long
        try {
            this.clau = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            // Si la conversión falla, lanzamos una excepción
            throw new ClauNoSuportada("La clau no és vàlida: " + clau);
        }
    }

    // Inicializamos el Random con la clave antes del cifrado y descifrado
    private void initRandom() {
        intRandom.setSeed(clau);  // Establecemos la semilla para la aleatoriedad
    }

    // Método que genera la permutación del alfabeto
    public char[] permutaAlfabet(char[] alfabet, char[] alfaRandom) {
        ArrayList<Character> lista = anadirLetras(alfabet);
        Collections.shuffle(lista, intRandom);  // Aleatorizamos usando la semilla
        for (int i = 0; i < alfaRandom.length; i++) {
            char letra = lista.get(i);
            alfaRandom[i] = letra;
        }
        return alfaRandom;
    }

    // Método que cifra
    public String xifraPoliAlfa(String cadena) {
        initRandom();  // Inicializamos el Random antes de cifrar
        return procesa(cadena, true);
    }

    // Método que descifra
    public String desxifraPoliAlfa(String cadena) {
        initRandom();  // Inicializamos el Random antes de descifrar
        return procesa(cadena, false);
    }

    public String procesa(String cadena, boolean x) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            permutaAlfabet(MAY, RANDOM);  // Generamos un alfabeto aleatorio para cada letra
            char l = cadena.charAt(i);  // Letra
            if (!Character.isLetter(l)) {
                resultado.append(l);  // Si no es letra, la agregamos tal cual
            } else {
                if (Character.isLowerCase(l)) {
                    l = Character.toUpperCase(l);  // Convertimos a mayúscula
                    l = verifica(x, l);  // Aplicamos la permutación
                    l = Character.toLowerCase(l);  // Convertimos de nuevo a minúscula
                    resultado.append(l);
                } else if (Character.isUpperCase(l)) {
                    l = verifica(x, l);  // Aplicamos la permutación
                    resultado.append(l);
                }
            }
        }
        return resultado.toString();
    }

    // Método que añade las letras del alfabeto MAY a un ArrayList<Character>
    public ArrayList<Character> anadirLetras(char[] alfabeto) {
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < alfabeto.length; i++) {
            list.add(alfabeto[i]);
        }
        return list;
    }

    // Busca la posición en el alfabeto MAY y después busca la misma posición en el alfabeto Random
    public char posicion(char letra, char[] dicionario, char[] otroDicionario) {
        int posicion = 0;
        for (int i = 0; i < dicionario.length; i++) {
            if (letra == dicionario[i]) {
                posicion = i;
                break;
            }
        }
        return otroDicionario[posicion];
    }

    public char verifica(boolean x, char l) {
        return (x) ? posicion(l, MAY, RANDOM) : posicion(l, RANDOM, MAY);
    }

}
