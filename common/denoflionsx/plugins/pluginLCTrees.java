package denoflionsx.plugins;

import net.minecraft.src.Block;
import net.minecraft.src.ModLoader;
import denoflionsx.core.core;
import denoflionsx.denLib.Config.Config;
import denoflionsx.plugins.LCTrees.cropLCTreesProvider;
import forestry.api.cultivation.CropProviders;

public class pluginLCTrees extends pluginBase {

    public pluginLCTrees() {
        this.mod = "mod_LCTrees";
        this.name = "pluginLCTrees";
        this.config = new Config("pluginLCTrees.cfg");
        this.register();
    }

    @Override
    protected void defaults() {
        
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    protected boolean init() {
        if (!detect()) {
            return this.hooked;
        }
        this.addBlock(mod,"newSap","Cherry Sapling",1);
        this.addBlock(mod,"newLogs","Cherry Log",1);
        
        return this.hooked;
    }

    @Override
    protected void recipes() {
        
    }

    @Override
    public void register() {
        super.register();
    }
}
