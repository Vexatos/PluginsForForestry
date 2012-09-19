package denoflionsx.plugins;

import denoflionsx.Enums.EnumModIDs;
import denoflionsx.plugins.Pam.EnumPamCrops;

public class pluginPam extends pluginBase{

    public pluginPam() {
        this.name = "pluginPam";
        this.mod = EnumModIDs.MODS.PAM.getID();
        register();
    }

    @Override
    protected void defaults() {
        
    }

    @Override
    protected boolean init() {
        if (!detect()){
            return false;
        }
        EnumPamCrops.EXPLODY.values();
        EnumPamCrops.NONEXPLODY.values();
        return true;
    }

    @Override
    protected void recipes() {
        
    }
     
}
