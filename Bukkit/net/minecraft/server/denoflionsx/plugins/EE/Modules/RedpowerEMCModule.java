package net.minecraft.server.denoflionsx.plugins.EE.Modules;

import net.minecraft.server.denoflionsx.core.core;
import net.minecraft.server.denoflionsx.denLib.Config.Config;
import net.minecraft.server.denoflionsx.plugins.baseModule;
import net.minecraft.server.denoflionsx.plugins.pluginBase;
import java.util.HashMap;

public class RedpowerEMCModule extends baseModule
{
    private HashMap values = new HashMap();
    private Config recipes;
    private String Machine;

    public RedpowerEMCModule(pluginBase var1)
    {
        super(var1);
        this.recipes = new Config(this.parent.getName() + "_RedpowerEMCValues.cfg");
    }

    protected void defaults() {}

    protected void init()
    {
        this.Machine = "RedPowerMachine";

        if (core.isBukkit)
        {
            this.Machine = core.BukkitShift(this.Machine);
        }
    }

    protected void recipes() {}
}
