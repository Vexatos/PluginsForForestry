package denoflionsx.plugins;

import denoflionsx.Old.pluginBase;
import denoflionsx.denLib.Config.Config;

public class pluginForestryAPS extends pluginBase{
    
    // This plugin will be my replacement for APS.

    public pluginForestryAPS() {
        this.name = "pluginForestryAPS";
        this.config = new Config(this.name + ".cfg");
        this.register();
    }

    @Override
    public void register() {
        super.register();
    }

    @Override
    protected void defaults() {
        
    }

    @Override
    protected boolean init() {
        return true;
    }

    @Override
    protected void recipes() {
        
    }
}
