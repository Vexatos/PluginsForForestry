package net.minecraft.server.denoflionsx.denLib.block_templates;

import net.minecraft.server.denoflionsx.core.core;
import forge.ITextureProvider;
import java.util.HashMap;
import net.minecraft.server.Block;
import net.minecraft.server.Material;
import net.minecraft.server.ModLoader;

public class multiBlock extends Block implements ITextureProvider
{
    protected HashMap nameMap = new HashMap();
    protected HashMap textureMap = new HashMap();

    public multiBlock(int var1, Material var2, String var3)
    {
        super(var1, var2);
        this.c(1.5F);
        this.a(var3);
    }

    public void add(String var1, String var2, int var3, Integer[] var4)
    {
        this.nameMap.put(Integer.valueOf(var3), var2);
        this.textureMap.put(Integer.valueOf(var3), var4);

        if (core.isClient())
        {
            ModLoader.addLocalization((String)this.nameMap.get(Integer.valueOf(var3)) + ".name", var2);
        }
    }

    public void add(String var1, String var2, int var3, Integer var4)
    {
        Integer[] var5 = new Integer[] {var4, var4, var4, var4, var4, var4};
        this.add(var1, var2, var3, var5);
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int a(int var1, int var2)
    {
        Integer[] var3 = (Integer[])this.textureMap.get(Integer.valueOf(var2));
        Integer var4 = var3[var1];
        return var4.intValue();
    }

    public String getTextureFile()
    {
        return "/denoflionsx/spritesheet.png";
    }
}
