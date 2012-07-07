package net.minecraft.server.denoflionsx.plugins;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.plugins.Buildcraft.BC3.addLiquidBC3;
import forestry.api.core.ItemInterface;
import forestry.api.liquids.LiquidContainer;
import forestry.api.liquids.LiquidManager;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.server.ItemStack;

public class pluginAdvancedPowerSystems extends pluginBase
{
    public pluginAdvancedPowerSystems()
    {
        this.mod = "mod_BuildcraftAPS";
        this.name = "pluginAdvancedPowerSystems";
        this.register();
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
            try
            {
                this.addItem("aps.module_Fusion.module_Fusion", "heavyWater", "Heavy Water", 0);
                pluginCore.metaItem.add("heavywatercap", ((Integer)pluginCore.metaItem.metaMap.get("Heavy Water Capsule")).intValue(), 36, "Heavy Water Capsule");
                pluginCore.metaItem.add("heavywatercan", ((Integer)pluginCore.metaItem.metaMap.get("Heavy Water Can")).intValue(), 20, "Heavy Water Can");
                pluginCore.metaItem.add("heavywatercap_red", ((Integer)pluginCore.metaItem.metaMap.get("Heavy Water Capsule_Red")).intValue(), 52, "Heavy Water Capsule");
                LiquidManager.liquidContainers.add(new LiquidContainer(new LiquidStack(((ItemStack)this.items.get("Heavy Water")).id, 1000), new ItemStack(pluginCore.metaItem, 1, ((Integer)pluginCore.metaItem.metaMap.get("Heavy Water Capsule")).intValue()), ItemInterface.getItem("waxCapsule"), false));
                LiquidManager.liquidContainers.add(new LiquidContainer(new LiquidStack(((ItemStack)this.items.get("Heavy Water")).id, 1000), new ItemStack(pluginCore.metaItem, 1, ((Integer)pluginCore.metaItem.metaMap.get("Heavy Water Can")).intValue()), ItemInterface.getItem("canEmpty"), false));
                LiquidManager.liquidContainers.add(new LiquidContainer(new LiquidStack(((ItemStack)this.items.get("Heavy Water")).id, 1000), new ItemStack(pluginCore.metaItem, 1, ((Integer)pluginCore.metaItem.metaMap.get("Heavy Water Capsule_Red")).intValue()), ItemInterface.getItem("refractoryEmpty"), false));
                addLiquidBC3.add(((ItemStack)this.items.get("Heavy Water")).id, new ItemStack(pluginCore.metaItem, 1, ((Integer)pluginCore.metaItem.metaMap.get("Heavy Water Capsule")).intValue()), ItemInterface.getItem("waxCapsule"));
                addLiquidBC3.add(((ItemStack)this.items.get("Heavy Water")).id, new ItemStack(pluginCore.metaItem, 1, ((Integer)pluginCore.metaItem.metaMap.get("Heavy Water Can")).intValue()), ItemInterface.getItem("canEmpty"));
                addLiquidBC3.add(((ItemStack)this.items.get("Heavy Water")).id, new ItemStack(pluginCore.metaItem, 1, ((Integer)pluginCore.metaItem.metaMap.get("Heavy Water Capsule_Red")).intValue()), ItemInterface.getItem("refractoryEmpty"));
                this.hooked = true;
            }
            catch (Exception var5)
            {
                var5.printStackTrace();
                this.hooked = false;
            }
            finally
            {
                if (this.hooked)
                {
                    core.print(this.getName() + " Loaded!");
                }
            }

            return this.hooked;
        }
    }

    protected void recipes()
    {
        RecipeManagers.bottlerManager.addRecipe(5, new LiquidStack(((ItemStack)this.items.get("Heavy Water")).getItem(), 1000), ItemInterface.getItem("canEmpty"), new ItemStack(pluginCore.metaItem.id, 1, ((Integer)pluginCore.metaItem.metaMap.get("Heavy Water Can")).intValue()));
        RecipeManagers.bottlerManager.addRecipe(5, new LiquidStack(((ItemStack)this.items.get("Heavy Water")).getItem(), 1000), ItemInterface.getItem("waxCapsule"), new ItemStack(pluginCore.metaItem.id, 1, ((Integer)pluginCore.metaItem.metaMap.get("Heavy Water Capsule")).intValue()));
        RecipeManagers.bottlerManager.addRecipe(5, new LiquidStack(((ItemStack)this.items.get("Heavy Water")).getItem(), 1000), ItemInterface.getItem("refractoryEmpty"), new ItemStack(pluginCore.metaItem.id, 1, ((Integer)pluginCore.metaItem.metaMap.get("Heavy Water Capsule_Red")).intValue()));
    }

    public void register()
    {
        if (!this.loaded && (this.loaded = this.init()))
        {
            this.recipes();
        }
    }

    protected void defaults() {}
}
