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
        StringBuilder resultado = new StringBuilder();
        for(int i=0; i < cadena.length(); i++){
            char letraAct = cadena.charAt(i);
            if(Character.isLowerCase(letraAct)){
                letraAct = Character.toUpperCase(letraAct);
                letraAct = posicion(letraAct);
                letraAct = Character.toLowerCase(letraAct);
                resultado.append(letraAct);
            }else if(Character.isUpperCase(letraAct)){
                letraAct = posicion(letraAct);
                resultado.append(letraAct);
            }else{
                resultado.append(letraAct);
            }
        }
        return resultado.toString();
    }

    //Metode que desxifrara
    public static String desxifraMonoAlfa(String cadena){
        StringBuilder resultado = new StringBuilder();
        for(int i=0; i < cadena.length(); i++){
            char letraAct = cadena.charAt(i);
            if(Character.isLowerCase(letraAct)){
                letraAct = Character.toUpperCase(letraAct);
                letraAct = desPosicion(letraAct);
                letraAct = Character.toLowerCase(letraAct);
                resultado.append(letraAct);
            }else if(Character.isUpperCase(letraAct)){
                letraAct = desPosicion(letraAct);
                resultado.append(letraAct);
            }else{
                resultado.append(letraAct);
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

    //Es para saber que letra ira en el cifrado
    // Busca la posicion en el alfabeto MAY y despues busca la misma posicion el el alfabeto Random
    public static char posicion(char letra){
        int posicion = 0;
        for(int i=0; i < MAY.length; i++){
            if(letra == MAY[i]){
                posicion = i;
            }
        }
        return RANDOM[posicion];
    }

    //Metodo para descifrar.
    public static char desPosicion(char letra){
        int posicion = 0;
        for(int i=0; i < RANDOM.length; i++){
            if(letra == RANDOM[i]){
                posicion = i;
            }
        }
        return MAY[posicion];
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