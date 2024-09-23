package UF1.Activitat_01;
/* 
 *Programa que es un codificador de xifrat de Cesar 13
 * Si se entra en texto("Hola") tiene que permanecer la may. y el min. del texto
 * Se reccorre el array y despues se compara con los arryas para saber si es mayuscula o minuscula
 */

public class Rot13 {
    public static final char[] may = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z','À','È','É','Í','Ò','Ó','Ú'};
    public static final char[] min = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z','à','è','é','í','ò','ó','ú'};

    public static void main(String[] args) {
        // Ejemplo de prueba
        String text = "Hola";
        xifraRot13(pasaAChar(text));
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

    /* Pasar un String a un array de char */
    public static char[] pasaAChar(String texto){
        char[] cadena = new char[texto.length()];
        for(int i=0; i < texto.length(); i++){
            cadena[i] = texto.charAt(i);
        }
        return cadena;
    }

    /* Pasar de un array de char a un String */
    public static String charToString(char[] cadena){
        String texto = "";
        for(int i=0; i < cadena.length; i++){
            texto += cadena[i];
        }
        return texto;
    }
}
