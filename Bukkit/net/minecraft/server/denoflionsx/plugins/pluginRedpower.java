package net.minecraft.server.denoflionsx.plugins;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.denLib.denLib;
import forestry.api.fuels.EngineCopperFuel;
import forestry.api.fuels.FuelManager;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ModLoader;

public class pluginRedpower extends pluginBase
{
    protected String theClass = "RedPowerBase";

    public pluginRedpower()
    {
        this.mod = "mod_RedPowerCore";
        this.name = "pluginRedpower";
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
                this.addItem("Nikolite", denLib.getItemStack(this.theClass, "itemNikolite"));
                this.addItem(this.theClass, "itemDyeIndigo", "Indigo Dye", 0);
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
        FuelManager.copperEngineFuel.put(Integer.valueOf(((ItemStack)this.items.get("Nikolite")).id), new EngineCopperFuel((ItemStack)this.items.get("Nikolite"), 1, 5000));
        ModLoader.addRecipe(new ItemStack(Item.INK_SACK, 1, 4), new Object[] {"XXX", "XIX", "XXX", 'I', this.items.get("Indigo Dye")});
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
