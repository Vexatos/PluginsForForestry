package denoflionsx.plugins;

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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected boolean init() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void recipes() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
