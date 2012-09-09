package denoflionsx.plugins;

import denoflionsx.core.core;

public class pluginAdvancedPowerSystems extends pluginBase {
    
    // This is pretty much complete junk now.

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
