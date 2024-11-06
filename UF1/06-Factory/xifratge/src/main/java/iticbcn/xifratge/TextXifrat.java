package iticbcn.xifratge;

public class TextXifrat {
    private byte[] text;

    public TextXifrat(byte[] text) {
        this.text = text;
    }

    public byte[] getBytes() {
        return this.text;
    }

    @Override
    public String toString() {
        return new String(this.text);
    }
}
