package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;

public class XifradorMonoalfabetic implements Xifrador {
    private String mgs = "";
    public final char[] MAY = "ABCÇDEFGHIJKLMÑOPQRSTUVWXYZÀÈÉÍÒÓÚÄËÏÖÜ".toCharArray();
    public final char[] RANDOM = new char[MAY.length];

    // Método que genera la permutación del alfabeto
    public char[] permutaAlfabet(char[] alfabet, char[] alfaRandom) {
        ArrayList<Character> lista = anadirLetras(alfabet);
        Collections.shuffle(lista);
        for (int i = 0; i < alfaRandom.length; i++) {
            char letra = lista.get(i);
            alfaRandom[i] = letra;
        }
        return alfaRandom;
    }

    // Método que cifra
    public String xifraMonoAlfa(String cadena, String clau) throws ClauNoSuportada {
        if (clau == null) {
            throw new ClauNoSuportada("La clau no pot ser null");
        }
        return procesa(cadena, true);
    }

    // Método que descifra
    public String desxifraMonoAlfa(String cadena, String clau) throws ClauNoSuportada {
        if (clau == null) {
            throw new ClauNoSuportada("La clau no pot ser null");
        }
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

    // Método que añade las letras del alfabeto MAY a un ArrayList<Character>
    public ArrayList<Character> anadirLetras(char[] alfabeto) {
        ArrayList<Character> list = new ArrayList<Character>();
        for (int i = 0; i < alfabeto.length; i++) {
            list.add(alfabeto[i]);
        }
        return list;
    }

    // Busca la posición en el alfabeto MAY y luego busca la misma posición en el
    // alfabeto RANDOM
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
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau == null) {
            throw new ClauNoSuportada("La clau no pot ser null");
        }
        String xifrat = xifraMonoAlfa(msg, clau);
        return new TextXifrat(xifrat.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau == null) {
            throw new ClauNoSuportada("La clau no pot ser null");
        }
        String msgDesxifrat = desxifraMonoAlfa(xifrat.toString(), clau);
        return msgDesxifrat;
    }

}
