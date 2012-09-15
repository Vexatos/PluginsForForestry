package denoflionsx.plugins.Core;

import denoflionsx.Enums.EnumToolTextures;
import denoflionsx.API.PfFManagers;
import denoflionsx.core.core;
import denoflionsx.PluginsforForestry;
import denoflionsx.plugins.BluesFood.ItemFoods;
import net.minecraft.src.ItemStack;

public class MushroomBag extends ItemFoods {

    public MushroomBag(int par1) {
        super(par1, EnumToolTextures.ToolTextures.MUSHROOMBAG.getIndex(), 6, 0.6f, "mushroombag");
        core.addName("Bag O' Soup");
        PfFManagers.ItemManager.registerItem("mushroombag", new ItemStack(this));
    }

    @Override
    public int getIconFromDamage(int par1) {
        return EnumToolTextures.ToolTextures.MUSHROOMBAG.getIndex();
    }

    @Override
    public String getItemNameIS(ItemStack par1ItemStack) {
        return "item.bago'soup";
    }

    @Override
    public String getTextureFile() {
        return PluginsforForestry.texture;
    }

    @Override
    public int getHealAmount() {
        return 6;
    }

    @Override
    public float getSaturationModifier() {
        return 0.6F;
    }
}
