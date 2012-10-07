package denoflionsx.Enums;

import denoflionsx.Machine.PfFMachineGUI;
import denoflionsx.Machine.PfFMachineTileEntity;
import denoflionsx.PluginsforForestry;
import denoflionsx.plugins.BluesFood.Items.ItemFoods;

public class TextureManager {

    public static void Preload() {
        SPRITESHEETS.values();
    }

    public static enum SPRITESHEETS {

        DEFAULT(PluginsforForestry.texture),
        FOOD(ItemFoods.spritesheet),
        BARREL(PfFMachineTileEntity.texture),
        OVEN(PfFMachineGUI.texture);

        private SPRITESHEETS(String sheet){
            PluginsforForestry.proxy.preloadTexture(sheet);
        }
    }
}