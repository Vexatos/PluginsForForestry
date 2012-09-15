package denoflionsx.API.Objects;

public class PfFColor {

    private String name;
    private int r;
    private int b;
    private int g;

    public PfFColor(String name, int r, int g, int b) {
        this.name = name;
        this.r = r;
        this.b = b;
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public int getG() {
        return g;
    }

    public String getName() {
        return name;
    }

    public int getR() {
        return r;
    }

    public int convertRBG() {
        int p1 = r * 256 * 256;
        int p2 = g * 256;
        int p3 = p1 + p2 + b;
        return p3;
    }
}
