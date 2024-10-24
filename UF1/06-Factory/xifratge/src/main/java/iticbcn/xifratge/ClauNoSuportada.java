package iticbcn.xifratge;

public class ClauNoSuportada extends Exception {
    // Constructor por defecto
    public ClauNoSuportada() {
        super();
    }

    // Constructor que acepta un mensaje
    public ClauNoSuportada(String msg) {
        super(msg);
    }
}
