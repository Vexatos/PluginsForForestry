package net.minecraft.server.denoflionsx.plugins;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.denLib.Config.Config;
import net.minecraft.server.denoflionsx.plugins.LCTrees.cropLCTreesProvider;
import forestry.api.cultivation.CropProviders;
import net.minecraft.server.Block;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ModLoader;

public class pluginLCTrees extends pluginBase
{
    public pluginLCTrees()
    {
        this.mod = "mod_LCTrees";
        this.name = "pluginLCTrees";
        this.config = new Config("pluginLCTrees.cfg");
        this.isBeta = true;
        this.register();
    }

    protected void defaults() {}

    public String getName()
    {
        return super.getName();
    }

    protected boolean init()
    {
        if (!this.detect())
        {
            core.print(this.mod + " not found!");
            return this.hooked;
        }
        else
        {
            this.addBlock(this.mod, "newSap", "Cherry Sapling", 1);
            this.addBlock(this.mod, "newLogs", "Cherry Log", 1);

            if (this.isBeta)
            {
                ModLoader.addRecipe((ItemStack)this.blocks.get("Cherry Sapling"), new Object[] {"D", 'D', Block.DIRT});
                ModLoader.addRecipe((ItemStack)this.blocks.get("Cherry Log"), new Object[] {"S", 'S', this.blocks.get("Cherry Sapling")});
                CropProviders.arborealCrops.add(new cropLCTreesProvider(this.getBlock("Cherry Sapling"), (ItemStack)null, new int[] {((ItemStack)this.blocks.get("Cherry Sapling")).id}, ((ItemStack)this.blocks.get("Cherry Sapling")).getData()));
            }

            return this.hooked;
        }
    }

    protected void recipes() {}

    public void register()
    {
        super.register();
    }
}
