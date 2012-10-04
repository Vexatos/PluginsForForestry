package denoflionsx.plugins.Buildcraft.Modules;

import denoflionsx.denLib.denLib;
import denoflionsx.Old.baseModule;
import denoflionsx.Old.pluginBase;

public class quarryModule extends baseModule {

    //This file raises the max speed of the buildcraft quarry.
    /*
     * Speed is determined by the amount of energy buffered. So, lets increase
     * the energy buffer!
     */
    protected static int max;
    protected static String c = "buildcraft.factory.TileQuarry";
    protected static String f = "MAX_ENERGY";

    public quarryModule(pluginBase parent) {
        super(parent);
    }

    @Override
    protected void init() {
        if (denLib.convertToBoolean(this.parent.config.getOption("quarryModule_Enabled"))) {
            max = Integer.valueOf(this.parent.config.getOption("QuarryMaxBuffer"));
            denLib.ReflectionHelper.setStaticInt(c, f, max);
        }
    }

    public static void load(pluginBase parent) {
        baseModule b = new quarryModule(parent);
        b.register();
    }

    @Override
    protected void defaults() {
        this.parent.config.addDefault("quarryModule_Enabled=false");
        this.parent.config.addDefault("QuarryMaxBuffer=" + 32767);
    }

    @Override
    protected void recipes() {
    }
}
