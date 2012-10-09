package denoflionsx.items;

import denoflionsx.API.PfFManagers;
import denoflionsx.core.core;
import denoflionsx.denLib.denLib;
import denoflionsx.PluginsforForestry;
import denoflionsx.Enums.EnumToolTextures;
import net.minecraft.src.Block;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemTool;

public class CustomTool extends ItemTool{
    
    private String itemName;

    public CustomTool(int id, EnumToolTextures.ToolTextures texture, String name, int dura) {
        super(id, texture.getIndex(), EnumToolMaterial.IRON, new Block[]{});
        this.setItemName(denLib.toLowerCaseNoSpaces(name));
        this.setMaxStackSize(1);
        this.setMaxDamage(dura);
        this.iconIndex = texture.getIndex();
        core.addName(name);
        this.itemName = "item." + denLib.toLowerCaseNoSpaces(name);
        PfFManagers.ItemManager.registerItem(denLib.toLowerCaseNoSpaces(name), this);
    }

    @Override
    public boolean isFull3D() {
        return true;
    }

    @Override
    public int getIconFromDamage(int par1) {
        return this.iconIndex;
    }

    @Override
    public String getItemNameIS(ItemStack par1ItemStack) {
        return this.itemName;
    }

    @Override
    public String getTextureFile() {
        return PluginsforForestry.texture;
    }

}
