package denoflionsx.plugins;

import buildcraft.api.liquids.LiquidStack;
import forestry.api.apiculture.FlowerManager;
import forestry.api.core.ItemInterface;
import forestry.api.cultivation.CropProviders;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import denoflionsx.core.core;
import denoflionsx.plugins.Redpower.cropFlaxProvider;

public class pluginRedPowerWorld extends pluginBase {

    protected String theClass = "RedPowerWorld";

    public pluginRedPowerWorld() {
        this.mod = "mod_RedPowerCore";
        this.name = "pluginRedPowerWorld";
        if (core.isBukkit){
            theClass = "net.minecraft.server." + theClass;
        }
        this.register();
    }

    @Override
    protected boolean init() {

        if (!detect()) {
            hooked = false;
            core.print(mod + " not found!");
            return hooked;
        }

        try {

            this.addItem(theClass,"itemSeeds","Flax Seeds",0);
            this.addBlock(theClass,"blockPlants","Indigo Flower",0);
            this.addBlock(theClass,"blockCrops","Flax",0);

            hooked = true;

        } catch (Exception ex) {

            ex.printStackTrace();
            hooked = false;

        } finally {
        }

        return hooked;
    }

    @Override
    protected void recipes() {
        FlowerManager.plainFlowers.add(this.blocks.get("Indigo Flower"));
        RecipeManagers.squeezerManager.addRecipe(10, new ItemStack[]{this.items.get("Flax Seeds")}, new LiquidStack(ItemInterface.getItem("liquidSeedOil").itemID, 100));
        CropProviders.cerealCrops.add(new cropFlaxProvider(Item.silk, this.items.get("Flax Seeds").getItem(), this.getBlock("Flax"),4, new int[]{3,3}));
    }

    @Override
    public void register() {
        if (!loaded) {
            if (loaded = init()) {
                recipes();
                core.print(getName() + " Loaded!");
            }
        }
    }

    @Override
    protected void defaults() {
        
    }
    
    
}
