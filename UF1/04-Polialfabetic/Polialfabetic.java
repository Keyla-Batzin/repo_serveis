/* Xifratge que generara 1 alfabet diferent per cada lletra del missatge amb una llavor clau */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Polialfabetic {
    public static final char[] MAY = "ABCÇDEFGHIJKLMÑOPQRSTUVWXYZÀÈÉÍÒÓÚÄËÏÖÜ".toCharArray();
    public static final char[] RANDOM = new char[MAY.length];
    public static final int clau = 9;
    public static final Random intRandom = new Random();

    public static void initRandom(int num) {
        intRandom.setSeed(clau);
    }

    // Metode que generar la permutacio del alfabet
    public static char[] permutaAlfabet(char[] alfabet, char[] alfaRandom) {
        ArrayList<Character> lista = anadirLetras(alfabet);
        Collections.shuffle(lista, intRandom);
        for (int i = 0; i < alfaRandom.length; i++) {
            char letra = lista.get(i);
            alfaRandom[i] = letra;
        }
        return alfaRandom;
    }

    // Metode que xifrara
    public static String xifraPoliAlfa(String cadena) {
        return procesa(cadena, true);
    }

    // Metode que desxifrara
    public static String desxifraPoliAlfa(String cadena) {
        return procesa(cadena, false);
    }

    public static String procesa(String cadena, boolean x) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            permutaAlfabet(MAY, RANDOM);
            char l = cadena.charAt(i); // Letra
            if (!Character.isLetter(l)) {
                resultado.append(l);
            } else {
                if (Character.isLowerCase(l)) {
                    l = Character.toUpperCase(l);
                    l = verifica(x, l);
                    l = Character.toLowerCase(l);
                    resultado.append(l);
                } else if (Character.isUpperCase(l)) {
                    l = verifica(x, l);
                    resultado.append(l);
                }
            }
        }
        return resultado.toString();
    }

    // Metodo que añade las letras del alfabeto MAY a un ArrayList<Character>
    public static ArrayList<Character> anadirLetras(char[] alfabeto) {
        ArrayList<Character> list = new ArrayList<Character>();
        for (int i = 0; i < alfabeto.length; i++) {
            list.add(alfabeto[i]);
        }
        return list;
    }

    // Busca la posicion en el alfabeto MAY y despues busca la misma posicion el el
    // alfabeto Random
    public static char posicion(char letra, char[] dicionario, char[] otroDicionario) {
        int posicion = 0;
        for (int i = 0; i < dicionario.length; i++) {
            if (letra == dicionario[i]) {
                posicion = i;
                break;
            }
            
        }
        return otroDicionario[posicion];
    }

    public static char verifica(boolean x, char l) {
        return (x) ? posicion(l, MAY, RANDOM) : posicion(l, RANDOM, MAY);
    }

    public static void main(String[] args) {
        String msgs[] = { "Test 01 àrbotre, coixí, Perímetre",
                "Test 02 Taüll, DÍA, año",
                "Test 03 Peça, Orrius, Bòvila" };
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clau);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clau);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }
}