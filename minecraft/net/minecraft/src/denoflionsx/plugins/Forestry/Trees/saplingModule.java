package net.minecraft.src.denoflionsx.plugins.Forestry.Trees;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.plugins.BetterFarming.cropCustomProvider;
import net.minecraft.src.denoflionsx.plugins.baseModule;
import net.minecraft.src.denoflionsx.plugins.pluginBase;
import forestry.api.cultivation.CropProviders;
import net.minecraft.src.denoflionsx.core.Crafting;

public class saplingModule extends baseModule {

    public static BlockUniversalSapling sap;

    public saplingModule(pluginBase parent) {
        super(parent);
    }

    @Override
    public void init() {
        sap = new BlockUniversalSapling(135);
        ModLoader.registerBlock(sap);
        ModLoader.addName(sap, "Universal Sapling");
        Crafting.MODE.FML.addRecipe(new ItemStack(sap, 1, 2), new Object[]{
                    "XXX",
                    "DXX",
                    "XXX",
                    Character.valueOf('D'), Block.dirt});
        universalTreeGenerator.add(0, Block.wood, Block.leaves, Block.sapling, 0, 0, 0);
        universalTreeGenerator.add(1, Block.wood, Block.leaves, Block.sapling, 1, 1, 1);
        universalTreeGenerator.add(2, Block.wood, Block.leaves, Block.sapling, 2, 0, 2);
        universalTreeGenerator.add(3, Block.wood, Block.leaves, Block.sapling, 3, 3, 3);
        CropProviders.arborealCrops.add(new cropCustomProvider(sap.blockID, new ItemStack(Item.appleRed, 1, 0), new int[]{sap.blockID}, 2, universalTreeGenerator.Trees.get(2).getLog().blockID, universalTreeGenerator.Trees.get(2).getLogMeta()));
        core.print("Sapling Module Loaded!");
    }

    @Override
    protected void defaults() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void recipes() {
        
    }
    
    
    
}
