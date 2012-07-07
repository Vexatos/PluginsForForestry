package net.minecraft.server.denoflionsx.denLib.block_templates;

import java.util.HashMap;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.ItemBlock;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;

public class multiBlockItem extends ItemBlock
{
    public static HashMap names = new HashMap();

    public multiBlockItem(int var1)
    {
        super(var1);
        this.bT = true;
        names.put(Integer.valueOf(0), "Test 1");
        names.put(Integer.valueOf(1), "Test 2");
    }

    public String a(ItemStack var1)
    {
        int var2 = var1.getData();
        String var3 = (String)names.get(Integer.valueOf(var2));
        return var3;
    }

    public String getTextureFile()
    {
        return "/denoflionsx/spritesheet.png";
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int filterData(int var1)
    {
        return var1;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS !
     */
    public boolean interactWith(ItemStack var1, EntityHuman var2, World var3, int var4, int var5, int var6, int var7)
    {
        return super.interactWith(var1, var2, var3, var4, var5, var6, var7);
    }
}
