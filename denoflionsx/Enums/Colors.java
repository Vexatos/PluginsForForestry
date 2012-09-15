package denoflionsx.Enums;

public class Colors {
    
    // THIS IS AN OLD FILE THAT WILL BE REMOVED.
    // USE COLORMANAGER INSTEAD!

    // Converts an RGB value into the format minecraft uses for rendering.
    public static int convertRBG(int red, int green, int blue) {
        int p1 = red * 256 * 256;
        int p2 = green * 256;
        int p3 = p1 + p2 + blue;
        return p3;
    }
    
    // This method is for navigating a spritesheet.
    // row is the number of spaces to the right.
    // times is the number of spaces down.
    public static int shiftRow(int row, int times){
        return row + (16 * times);
    }

    public static enum Values {
        // Blue insists Salmon is a color. Looks pink to me.
        // Her response: haters gonna hate.
        
        // I eventually ran out of names for colors and started using liquid names.

        WHITE, PINK(255, 77, 83), BROWN(42, 26, 15), LIGHTBROWN(132, 107, 89),
        SALMON(247, 158, 145), ORANGE(255, 181, 41), LIME(201, 255, 55),
        TAN(212,180,116), OIL(114,109,0),BLACK(0,0,0),PISS(196,192,14),
        GREEN(118,198,16),ORANGE2(223,103,7),LIGHTGREEN(141,196,66),
        HONEY(242,207,67),SEEDOIL(225,225,169),ICE(140,247,247),
        RED(237,65,0),MAUVE(156,31,67);
        private int color = 0;
        private int r = 0;
        private int b = 0;
        private int g = 0;

        private Values(int red, int green, int blue) {
            color = convertRBG(red, green, blue);
            r = red;
            b = blue;
            g = green;
        }

        private Values() {
            // This makes white.
            color = convertRBG(255, 255, 255);
            r = 255;
            b = 255;
            g = 255;
        }

        public int getColor() {
            return color;
        }

        public int getB() {
            return b;
        }

        public int getG() {
            return g;
        }

        public int getR() {
            return r;
        }
        
    }
}
