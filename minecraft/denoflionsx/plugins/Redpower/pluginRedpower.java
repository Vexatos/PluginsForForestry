package denoflionsx.plugins.Redpower;

import denoflionsx.Old.pluginBase;
import denoflionsx.core.core;
import denoflionsx.denLib.denLib;
import forestry.api.fuels.EngineCopperFuel;
import forestry.api.fuels.FuelManager;

public class pluginRedpower extends pluginBase {

    protected String theClass = "RedPowerBase";

    public pluginRedpower() {
        this.mod = "mod_RedPowerCore";
        this.name = "pluginRedpower";
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
            this.addItem("Nikolite",denLib.getItemStack(theClass, "itemNikolite"));
            this.addItem(theClass, "itemDyeIndigo", "Indigo Dye", 0);
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
        FuelManager.copperEngineFuel.put(this.items.get("Nikolite").itemID, new EngineCopperFuel(this.items.get("Nikolite"), 1, 5000));
    }

    @Override
    protected void defaults() {
        
    }
}
