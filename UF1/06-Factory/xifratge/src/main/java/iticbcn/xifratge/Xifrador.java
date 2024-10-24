package iticbcn.xifratge;

/* Interfície on s'implementaran tots els mètodes de xifratge */
public interface Xifrador {
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada;
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada;
}
