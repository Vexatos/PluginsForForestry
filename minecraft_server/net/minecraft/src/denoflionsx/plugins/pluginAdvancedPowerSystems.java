package net.minecraft.src.denoflionsx.plugins;

import net.minecraft.src.ItemStack;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.plugins.Buildcraft.BC3.addLiquidBC3;
import forestry.api.core.ItemInterface;
import forestry.api.liquids.LiquidContainer;
import forestry.api.liquids.LiquidManager;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;

public class pluginAdvancedPowerSystems extends pluginBase {

    public pluginAdvancedPowerSystems() {
        this.mod = "mod_BuildcraftAPS";
        this.name = "pluginAdvancedPowerSystems";
        this.register();
    }

    @Override
    protected boolean init() {

        if (!detect()) {
            core.print(mod + " not found!");
            return hooked;
        }
        try {
            this.addItem("aps.module_Fusion.module_Fusion","heavyWater","Heavy Water",0);
            pluginCore.metaItem.add("heavywatercap", pluginCore.metaItem.metaMap.get("Heavy Water Capsule"), 20 + 16, "Heavy Water Capsule");
            pluginCore.metaItem.add("heavywatercan", pluginCore.metaItem.metaMap.get("Heavy Water Can"), 20, "Heavy Water Can");
            pluginCore.metaItem.add("heavywatercap_red", pluginCore.metaItem.metaMap.get("Heavy Water Capsule_Red"), 20 + 16 + 16, "Heavy Water Capsule");
            LiquidManager.liquidContainers.add(new LiquidContainer(new LiquidStack(this.items.get("Heavy Water").itemID, 1000), new ItemStack(pluginCore.metaItem, 1, pluginCore.metaItem.metaMap.get("Heavy Water Capsule")), ItemInterface.getItem("waxCapsule"), false));
            LiquidManager.liquidContainers.add(new LiquidContainer(new LiquidStack(this.items.get("Heavy Water").itemID, 1000), new ItemStack(pluginCore.metaItem, 1, pluginCore.metaItem.metaMap.get("Heavy Water Can")), ItemInterface.getItem("canEmpty"), false));
            LiquidManager.liquidContainers.add(new LiquidContainer(new LiquidStack(this.items.get("Heavy Water").itemID, 1000), new ItemStack(pluginCore.metaItem, 1, pluginCore.metaItem.metaMap.get("Heavy Water Capsule_Red")), ItemInterface.getItem("refractoryEmpty"), false));
            addLiquidBC3.add(this.items.get("Heavy Water").itemID, new ItemStack(pluginCore.metaItem, 1, pluginCore.metaItem.metaMap.get("Heavy Water Capsule")), ItemInterface.getItem("waxCapsule"));
            addLiquidBC3.add(this.items.get("Heavy Water").itemID, new ItemStack(pluginCore.metaItem, 1, pluginCore.metaItem.metaMap.get("Heavy Water Can")),ItemInterface.getItem("canEmpty"));
            addLiquidBC3.add(this.items.get("Heavy Water").itemID, new ItemStack(pluginCore.metaItem, 1, pluginCore.metaItem.metaMap.get("Heavy Water Capsule_Red")),ItemInterface.getItem("refractoryEmpty"));
            hooked = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            hooked = false;
        } finally {
            if (hooked) {
                core.print(getName() + " Loaded!");
            }
        }

        return hooked;
    }

    @Override
    protected void recipes() {

        RecipeManagers.bottlerManager.addRecipe(5, new LiquidStack(this.items.get("Heavy Water").getItem(), 1000), ItemInterface.getItem("canEmpty"), new ItemStack(pluginCore.metaItem.shiftedIndex, 1, pluginCore.metaItem.metaMap.get("Heavy Water Can")));
        RecipeManagers.bottlerManager.addRecipe(5, new LiquidStack(this.items.get("Heavy Water").getItem(), 1000), ItemInterface.getItem("waxCapsule"), new ItemStack(pluginCore.metaItem.shiftedIndex, 1, pluginCore.metaItem.metaMap.get("Heavy Water Capsule")));
        RecipeManagers.bottlerManager.addRecipe(5, new LiquidStack(this.items.get("Heavy Water").getItem(), 1000), ItemInterface.getItem("refractoryEmpty"), new ItemStack(pluginCore.metaItem.shiftedIndex, 1, pluginCore.metaItem.metaMap.get("Heavy Water Capsule_Red")));

    }

    @Override
    public void register() {
        if (!loaded) {
            if (loaded = init()) {
                recipes();
            }
        }
    }

    @Override
    protected void defaults() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
