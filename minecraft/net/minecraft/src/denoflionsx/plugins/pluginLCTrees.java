package net.minecraft.src.denoflionsx.plugins;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.Config.Config;
import net.minecraft.src.denoflionsx.plugins.LCTrees.cropLCTreesProvider;
import forestry.api.cultivation.CropProviders;

public class pluginLCTrees extends pluginBase {

    public pluginLCTrees() {
        this.mod = "mod_LCTrees";
        this.name = "pluginLCTrees";
        this.config = new Config("pluginLCTrees.cfg");
        this.isBeta = true;
        this.register();
    }

    @Override
    protected void defaults() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    protected boolean init() {
        if (!detect()) {
            core.print(this.mod + " not found!");
            return this.hooked;
        }
        this.addBlock(mod,"newSap","Cherry Sapling",1);
        this.addBlock(mod,"newLogs","Cherry Log",1);
        if (this.isBeta) {
            ModLoader.addRecipe(this.blocks.get("Cherry Sapling"), "D", Character.valueOf('D'), Block.dirt);
            ModLoader.addRecipe(this.blocks.get("Cherry Log"),"S",Character.valueOf('S'),this.blocks.get("Cherry Sapling"));
            CropProviders.arborealCrops.add(new cropLCTreesProvider(this.getBlock("Cherry Sapling"),null,new int[]{this.blocks.get("Cherry Sapling").itemID},this.blocks.get("Cherry Sapling").getItemDamage()));
        }
        
        return this.hooked;
    }

    @Override
    protected void recipes() {
        
    }

    @Override
    public void register() {
        super.register();
    }
}
