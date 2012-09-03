package net.minecraft.src.denoflionsx.GUI;

public class EnumGUITextures {

    public static enum TEXTURES {

        OVEN("oven_food_gui.png");
        private String gui;

        private TEXTURES(String texture) {
            this.gui = "/denoflionsx/" + texture;
        }

        public String getGui() {
            return gui;
        }
    }
}
