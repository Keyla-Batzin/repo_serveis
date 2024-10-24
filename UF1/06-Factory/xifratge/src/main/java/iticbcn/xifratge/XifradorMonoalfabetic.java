/* Programa que cifrara y desfirara teniendo como referencia un alfabeto aleatorio generado por un modulo */

package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;

public class XifradorMonoalfabetic implements Xifrador {
    public final char[] MAY = "ABCÇDEFGHIJKLMÑOPQRSTUVWXYZÀÈÉÍÒÓÚÄËÏÖÜ".toCharArray();
    public final char[] RANDOM = new char[MAY.length];

    // Metode que generar la permutacio del alfabet
    public char[] permutaAlfabet(char[] alfabet, char[] alfaRandom) {
        ArrayList<Character> lista = anadirLetras(alfabet);
        Collections.shuffle(lista);
        for (int i = 0; i < alfaRandom.length; i++) {
            char letra = lista.get(i);
            alfaRandom[i] = letra;
        }
        return alfaRandom;
    }

    // Metode que xifrara
    public String xifraMonoAlfa(String cadena) {
        return procesa(cadena, true);
    }

    // Metode que desxifrara
    public String desxifraMonoAlfa(String cadena) {
        return procesa(cadena, false);
    }

    public String procesa(String cadena, boolean x) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
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
    public ArrayList<Character> anadirLetras(char[] alfabeto) {
        ArrayList<Character> list = new ArrayList<Character>();
        for (int i = 0; i < alfabeto.length; i++) {
            list.add(alfabeto[i]);
        }
        return list;
    }

    // Busca la posicion en el alfabeto MAY y despues busca la misma posicion el el
    // alfabeto Random
    public char posicion(char letra, char[] dicionario, char[] otroDicionario) {
        int posicion = 0;
        for (int i = 0; i < dicionario.length; i++) {
            if (letra == dicionario[i]) {
                posicion = i;
            }
        }
        return otroDicionario[posicion];
    }

    public char verifica(boolean x, char l) {
        return (x) ? posicion(l, MAY, RANDOM) : posicion(l, RANDOM, MAY);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) {
        throw new UnsupportedOperationException("Unimplemented method 'xifra'");
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        throw new UnsupportedOperationException("Unimplemented method 'desxifra'");
    }
}