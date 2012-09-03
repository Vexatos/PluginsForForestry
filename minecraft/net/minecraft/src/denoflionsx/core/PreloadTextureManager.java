package net.minecraft.src.denoflionsx.core;

import net.minecraft.src.denoflionsx.plugins.BluesFood.ItemFoods;
import net.minecraft.src.denoflionsx.plugins.BluesFood.MachineOven;
import net.minecraft.src.forge.MinecraftForgeClient;
import net.minecraft.src.mod_PluginsforForestry;

public class PreloadTextureManager {

    public static void Preload() {
        SPRITESHEETS.values();
    }

    public static enum SPRITESHEETS {

        DEFAULT(mod_PluginsforForestry.texture),
        FOOD(ItemFoods.spritesheet),
        OVEN_GUI(MachineOven.gui);

        private SPRITESHEETS(String sheet){
            MinecraftForgeClient.preloadTexture(sheet);
        }
    }
}