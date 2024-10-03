package UF1.Activitat_03;

/* Programa que cifrara y desfirara teniendo como referencia un alfabeto aleatorio generado por un modulo */

import java.util.ArrayList;
import java.util.Collections;

public class Monoalfabetic {
    public static final char[] MAY = "ABCÇDEFGHIJKLMÑOPQRSTUVWXYZÀÈÉÍÒÓÚÄËÏÖÜ".toCharArray();
    public static final char[] RANDOM = new char[MAY.length];

    // Metode que generar la permutacio del alfabet
    public static char[] permutaAlfabet(char[] alfabet, char[] alfaRandom){
        ArrayList<Character> lista = anadirLetras(alfabet);
        Collections.shuffle(lista);
        for(int i=0; i < alfaRandom.length; i++){
            char letra = lista.get(i);
            alfaRandom[i] = letra;
        }
        return alfaRandom;
    }

    //Metode que xifrara
    public static String xifraMonoAlfa(String cadena){
        return procesa(cadena, true);
    }

    //Metode que desxifrara
    public static String desxifraMonoAlfa(String cadena){
        return procesa(cadena, false);
    }

    public static String procesa(String cadena, boolean x){
        StringBuilder resultado = new StringBuilder();
        for(int i=0; i < cadena.length(); i++){
            char l = cadena.charAt(i); //Letra
            if(!Character.isLetter(l)){
                resultado.append(l);
            }else{
                if(Character.isLowerCase(l)){
                    l = Character.toUpperCase(l);
                    l = verifica(x, l);
                    l = Character.toLowerCase(l);
                    resultado.append(l);
                }else if(Character.isUpperCase(l)){
                    l = verifica(x, l);
                    resultado.append(l);
                }
            }
        }
        return resultado.toString();
    }

    //Metodo que añade las letras del alfabeto MAY a un ArrayList<Character>
    public static ArrayList<Character> anadirLetras(char[] alfabeto){
        ArrayList<Character> list = new ArrayList<Character>();
        for(int i=0; i < alfabeto.length; i++){
            list.add(alfabeto[i]);
        }
        return list;
    }

    // Busca la posicion en el alfabeto MAY y despues busca la misma posicion el el alfabeto Random
    public static char posicion(char letra, char[] dicionario, char[] otroDicionario){
        int posicion = 0;
        for(int i=0; i < dicionario.length; i++){
            if(letra == dicionario[i]){
                posicion = i;
            }
        }
        return otroDicionario[posicion];
    }

    public static char verifica(boolean x, char l){
        return (x) ? posicion(l,MAY,RANDOM) : posicion(l,RANDOM,MAY);
    }

    public static void main(String[] args) {
        permutaAlfabet(MAY, RANDOM);
        String prueba1 = "Hola me llamo Keyla";
        System.out.println("Texto normal: " + prueba1);
        String cifrado = xifraMonoAlfa(prueba1);
        System.out.println("Texto cifrado: " + cifrado);
        String descifrado = desxifraMonoAlfa(cifrado);
        System.out.println("Texto descifrado: " + descifrado);
    }
}