package net.minecraft.src.denoflionsx.OldUnusedCode;

import java.util.HashMap;
import net.minecraft.src.*;

public class multiBlockItem extends ItemBlock {

    public static HashMap<Integer, String> names = new HashMap();

    public multiBlockItem(int par1) {
        super(par1);
        this.hasSubtypes = true;
        names.put(0, "Test 1");
        names.put(1, "Test 2");
    }

    @Override
    public String getItemNameIS(ItemStack par1ItemStack) {
        int meta = par1ItemStack.getItemDamage();
        String name2 = names.get(meta);
        return name2;
    }

    @Override public String getTextureFile() {return mod_PluginsforForestry.texture;}

    @Override
    public int getMetadata(int par1) {
        return par1;
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7) {
        return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7);
    }
}
