package UF1.Activitat_01;
/* 
 *Programa que es un codificador de xifrat de Cesar 13
 * Si se entra en texto("Hola") tiene que permanecer la may. y el min. del texto
 */

public class Rot13 {
    public static final char[] may = {'A','B','C','D'};
    public static final char[] min = {'a','b','c','d'};

    public static void main(String[] args) {
        
    }

    public static char[] xifraRot13(char[] lista){
        // Recorres la lista con un for
        for(int i = 0; i < lista.length; i++){
            //Sumas 13 posiciones
            for(int y = 0; y < 13; y++){
                lista[i]++;
            }
        }
        return lista;
    }

    public static char[] desxifraRot13(char[] lista){
        // Recorres la lista con un for
        for(int i = 0; i < lista.length; i++){
            //Restas 13 posiciones
            for(int y = 13; y > 0; y--){
                lista[i]--;
            }
        }
        return lista;
    }

    public static char[] pasaAChar(String texto){
        char[] cadena = new char[texto.length()];
        for(int i=0; i < texto.length(); i++){
            cadena[i] = texto.charAt(i);
        }
        return cadena;
    }

    public static String charToString(char[] cadena){
        String texto = "";
        for(int i=0; i < cadena.length; i++){
            texto += cadena[i];
        }
        return texto;
    }
}
