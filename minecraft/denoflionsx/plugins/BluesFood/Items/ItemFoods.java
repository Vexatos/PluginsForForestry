package denoflionsx.plugins.BluesFood.Items;

import denoflionsx.API.PfFManagers;
import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemStack;
import denoflionsx.core.core;
import denoflionsx.denLib.denLib;
import net.minecraft.src.CreativeTabs;

public class ItemFoods extends ItemFood{
    
    public static String spritesheet = "/denoflionsx/food_sheet.png";
    
    private int texture;
    private String name;

    public ItemFoods(int par1, int texture, int par2, float par3, String name) {
        super(par1, par2, par3, false);
        core.addName(name);
        this.name = name;
        this.texture = texture;
        PfFManagers.ItemManager.registerItem(denLib.toLowerCaseNoSpaces(name), this);
        this.setCreativeTab(CreativeTabs.tabFood);
    }

    @Override
    public int getIconFromDamage(int par1) {
        return this.texture;
    }

    @Override
    public String getItemNameIS(ItemStack par1ItemStack) {
        return "item." + denLib.toLowerCaseNoSpaces(name);
    } 
    
    @Override
    public String getTextureFile() {
        return spritesheet;
    }
}
