package denoflionsx.PluginsforForestry_PluginBlueFood.Items;

import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry_PluginBlueFood.PfFFood;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.denLib.denLib;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemFoodTemplate extends ItemFood {

    public ItemFoodTemplate(int id, int healAmount, float saturationModifier, boolean isWolfsFavoriteMeat) {
        super(id, healAmount, saturationModifier, isWolfsFavoriteMeat);
    }

    public ItemFoodTemplate(int id, int healAmount, float saturationModifier, int icon, String name) {
        this(id, healAmount, saturationModifier, false);
        this.setCreativeTab(PfF.Core.tab);
        this.setTextureFile(PfFFood.Core.spritesheet);
        this.setIconIndex(icon);
        this.setItemName(name);
        FMLWrapper.MODE.FML.addNameItemStack(name, new ItemStack(this));
        PfFManagers.Foods.registerItem(denLib.toLowerCaseNoSpaces(name), new ItemStack(this));
    }
}
