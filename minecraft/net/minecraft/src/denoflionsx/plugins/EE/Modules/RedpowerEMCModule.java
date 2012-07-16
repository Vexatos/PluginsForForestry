package net.minecraft.src.denoflionsx.plugins.EE.Modules;

import java.util.HashMap;
import net.minecraft.src.Block;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.denoflionsx.denLib.Config.Config;
import net.minecraft.src.denoflionsx.plugins.baseModule;
import net.minecraft.src.denoflionsx.plugins.pluginBase;
import net.minecraft.src.denoflionsx.plugins.pluginEE;

public class RedpowerEMCModule extends baseModule{
    
    private HashMap<String, Integer> values = new HashMap();
    private Config recipes = new Config(this.parent.getName() + "_RedpowerEMCValues.cfg");
    private String Machine;

    public RedpowerEMCModule(pluginBase parent) {
        super(parent);
    }

    @Override
    protected void defaults() {
        
    }

    @Override
    protected void init() {
        
        Machine = "RedPowerMachine";
        if (core.isBukkit){
            Machine = core.BukkitShift(Machine);
        }
        
        

        
    }

    @Override
    protected void recipes() {
        
    }
    
    
    
}
