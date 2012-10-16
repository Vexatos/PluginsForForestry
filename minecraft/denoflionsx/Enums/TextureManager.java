package denoflionsx.Enums;

import denoflionsx.Machine.Client.PfFMachineModel;
import denoflionsx.PluginsforForestry;
import denoflionsx.plugins.BlueSilkWorm.Machine.TileEntityIncubator;
import denoflionsx.plugins.BluesFood.Items.ItemFoods;

public class TextureManager {

    public static void Preload() {
        SPRITESHEETS.values();
    }

    public static enum SPRITESHEETS {

        DEFAULT(PluginsforForestry.texture),
        FOOD(ItemFoods.spritesheet),
        BARREL(PfFMachineModel.texture),
        INCUBATOR(TileEntityIncubator.guitexture);

        private SPRITESHEETS(String sheet){
            PluginsforForestry.proxy.preloadTexture(sheet);
        }
    }
}