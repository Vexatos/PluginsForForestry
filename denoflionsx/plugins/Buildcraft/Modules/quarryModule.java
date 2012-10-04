package denoflionsx.plugins.Buildcraft.Modules;

import denoflionsx.denLib.denLib;
import denoflionsx.core.PfFModuleTemplate;

public class quarryModule extends PfFModuleTemplate {

    //This file raises the max speed of the buildcraft quarry.
    /*
     * Speed is determined by the amount of energy buffered. So, lets increase
     * the energy buffer!
     */
    protected static int max;
    protected static String c = "buildcraft.factory.TileQuarry";
    protected static String f = "MAX_ENERGY";

    public quarryModule(String name, String parent) {
        super(name, parent);
    }

    @Override
    public void doSetup() {
        max = Integer.valueOf(this.config.getOption("QuarryMaxBuffer"));
        denLib.ReflectionHelper.setStaticInt(c, f, max);
    }

    @Override
    public void defaults() {
        this.config.addDefault("QuarryMaxBuffer=" + 7000);
    }

    @Override
    public void recipes() {
    }
}
