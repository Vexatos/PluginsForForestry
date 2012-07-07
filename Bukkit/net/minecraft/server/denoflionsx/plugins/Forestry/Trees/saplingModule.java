package net.minecraft.server.denoflionsx.plugins.Forestry.Trees;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.plugins.baseModule;
import net.minecraft.server.denoflionsx.plugins.pluginBase;
import net.minecraft.server.denoflionsx.plugins.BetterFarming.cropCustomProvider;
import forestry.api.cultivation.CropProviders;
import net.minecraft.server.Block;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ModLoader;

public class saplingModule extends baseModule
{
    public static BlockUniversalSapling sap;

    public saplingModule(pluginBase var1)
    {
        super(var1);
    }

    public void init()
    {
        sap = new BlockUniversalSapling(135);
        ModLoader.registerBlock(sap);
        ModLoader.addName(sap, "Universal Sapling");
        ModLoader.addRecipe(new ItemStack(sap, 1, 2), new Object[] {"XXX", "DXX", "XXX", 'D', Block.DIRT});
        universalTreeGenerator.add(0, Block.LOG, Block.LEAVES, Block.SAPLING, 0, 0, 0);
        universalTreeGenerator.add(1, Block.LOG, Block.LEAVES, Block.SAPLING, 1, 1, 1);
        universalTreeGenerator.add(2, Block.LOG, Block.LEAVES, Block.SAPLING, 2, 0, 2);
        universalTreeGenerator.add(3, Block.LOG, Block.LEAVES, Block.SAPLING, 3, 3, 3);
        CropProviders.arborealCrops.add(new cropCustomProvider(sap.id, new ItemStack(Item.APPLE, 1, 0), new int[] {sap.id}, 2, ((universalTreeData)universalTreeGenerator.Trees.get(Integer.valueOf(2))).getLog().id, ((universalTreeData)universalTreeGenerator.Trees.get(Integer.valueOf(2))).getLogMeta()));
        core.print("Sapling Module Loaded!");
    }

    protected void defaults()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected void recipes() {}
}
