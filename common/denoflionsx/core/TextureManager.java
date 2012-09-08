package denoflionsx.core;

import denoflionsx.mod_PluginsforForestry;
import denoflionsx.plugins.BluesFood.ItemFoods;
import denoflionsx.plugins.BluesFood.MachineOven;

public class TextureManager {

    public static void Preload() {
        SPRITESHEETS.values();
    }

    public static enum SPRITESHEETS {

        DEFAULT(mod_PluginsforForestry.texture),
        FOOD(ItemFoods.spritesheet),
        OVEN_GUI(MachineOven.gui);

        private SPRITESHEETS(String sheet){
            mod_PluginsforForestry.proxy.preloadTexture(sheet);
        }
    }
}