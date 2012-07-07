package net.minecraft.server.denoflionsx.plugins;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.plugins.Redpower.cropFlaxProvider;
import forestry.api.apiculture.FlowerManager;
import forestry.api.core.ItemInterface;
import forestry.api.cultivation.CropProviders;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;

public class pluginRedPowerWorld extends pluginBase
{
    protected String theClass = "RedPowerWorld";

    public pluginRedPowerWorld()
    {
        this.mod = "mod_RedPowerCore";
        this.name = "pluginRedPowerWorld";
        this.isLate = true;

        if (core.isBukkit)
        {
            this.theClass = "net.minecraft.server." + this.theClass;
        }

        this.register();
    }

    protected boolean init()
    {
        if (!this.detect())
        {
            this.hooked = false;
            core.print(this.mod + " not found!");
            return this.hooked;
        }
        else
        {
            try
            {
                this.addItem(this.theClass, "itemSeeds", "Flax Seeds", 0);
                this.addBlock(this.theClass, "blockPlants", "Indigo Flower", 0);
                this.addBlock(this.theClass, "blockCrops", "Flax", 0);
                this.hooked = true;
            }
            catch (Exception var5)
            {
                var5.printStackTrace();
                this.hooked = false;
            }
            finally
            {
                ;
            }

            return this.hooked;
        }
    }

    protected void recipes()
    {
        FlowerManager.plainFlowers.add(this.blocks.get("Indigo Flower"));
        RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[] {(ItemStack)this.items.get("Flax Seeds")}, new LiquidStack(ItemInterface.getItem("liquidSeedOil").id, 100));
        CropProviders.cerealCrops.add(new cropFlaxProvider(Item.STRING, ((ItemStack)this.items.get("Flax Seeds")).getItem(), this.getBlock("Flax"), 4, new int[] {3, 3}));
    }

    public void register()
    {
        if (!this.loaded && (this.loaded = this.init()))
        {
            this.recipes();
            core.print(this.getName() + " Loaded!");
        }
    }

    protected void defaults()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
