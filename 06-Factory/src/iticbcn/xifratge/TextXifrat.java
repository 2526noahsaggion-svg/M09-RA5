package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;

public class TextXifrat {
    private byte[] dades;
    public TextXifrat(byte[] dades) {
        this.dades = dades;
    }

    public byte[] getBytes() {
        return dades;
    }

    @Override
    public String toString() {
        if (dades == null) return "null";
        return new String(this.dades, StandardCharsets.UTF_8);
    }
}
