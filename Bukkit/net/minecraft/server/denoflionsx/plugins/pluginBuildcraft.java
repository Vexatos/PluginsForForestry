package net.minecraft.server.denoflionsx.plugins;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.denLib.denLib;
import net.minecraft.server.denoflionsx.denLib.Config.Config;
import net.minecraft.server.denoflionsx.plugins.Buildcraft.TankManager;
import net.minecraft.server.denoflionsx.plugins.Buildcraft.goldGear;
import net.minecraft.server.denoflionsx.plugins.Buildcraft.Modules.milkModule;
import net.minecraft.server.denoflionsx.plugins.Buildcraft.Modules.quarryModule;
import forestry.api.fuels.EngineBronzeFuel;
import forestry.api.fuels.FuelManager;
import net.minecraft.server.ItemStack;

public class pluginBuildcraft extends pluginBase
{
    public pluginBuildcraft()
    {
        this.name = "pluginBuildcraft";
        this.mod = "mod_BuildCraftCore";
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    public void register()
    {
        if (!this.loaded)
        {
            this.defaults();
            milkModule.load(this);
            quarryModule.load(this);
            this.runConfig();

            if (this.loaded = this.init())
            {
                this.recipes();
                core.print(this.name + " loaded!");
            }
        }
    }

    protected boolean detect()
    {
        return denLib.detect("mod_BuildCraftCore") && denLib.detect("mod_BuildCraftTransport") && denLib.detect("mod_BuildCraftCore") && denLib.detect("mod_BuildCraftTransport");
    }

    protected void recipes()
    {
        if (denLib.convertToBoolean(this.config.getOption("FuelInBiogas")))
        {
            FuelManager.bronzeEngineFuel.put(Integer.valueOf(((ItemStack)this.items.get("Fuel")).id), new EngineBronzeFuel((ItemStack)this.items.get("Fuel"), Integer.valueOf(this.config.getOption("FuelMJt")).intValue(), Integer.valueOf(this.config.getOption("FuelBurnTime")).intValue(), 1));
        }

        if (denLib.convertToBoolean(this.config.getOption("OilInBiogas")))
        {
            FuelManager.bronzeEngineFuel.put(Integer.valueOf(((ItemStack)this.blocks.get("Oil")).id), new EngineBronzeFuel((ItemStack)this.blocks.get("Oil"), Integer.valueOf(this.config.getOption("OilMJt")).intValue(), Integer.valueOf(this.config.getOption("FuelBurnTime")).intValue(), 1));
        }

        this.registerModules();
    }

    protected boolean init()
    {
        TankManager.setup();
        goldGear.setup();
        String var1 = "BuildCraftEnergy";

        if (core.isBukkit)
        {
            var1 = "net.minecraft.server." + var1;
        }

        this.addBlock(var1, "oilStill", "Oil", 0);
        this.addItem(var1, "fuel", "Fuel", 0);
        this.hooked = true;
        return this.hooked;
    }

    protected void defaults()
    {
        this.config.addDefault("[Buildcraft Options]");
        this.config.addDefault("FuelInBiogas=true");
        this.config.addDefault("FuelMJt=5");
        this.config.addDefault("FuelBurnTime=50000");
        this.config.addDefault("OilInBiogas=true");
        this.config.addDefault("OilMJt=2");
        this.config.addDefault("OilBurntime=10000");
    }
}
