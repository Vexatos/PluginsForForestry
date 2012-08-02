package net.minecraft.src.denoflionsx.denLib;

public class Colors {

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

        WHITE, PINK(255, 95, 194), BROWN(42, 26, 15), LIGHTBROWN(132, 107, 89),
        SALMON(247, 158, 145), ORANGE(252, 208, 132), LIME(201, 255, 55);
        private int color = 0;

        private Values(int red, int green, int blue) {
            color = convertRBG(red, green, blue);
        }

        private Values() {
            // This makes white.
            color = convertRBG(255, 255, 255);
        }

        public int getColor() {
            return color;
        }
    }
}
