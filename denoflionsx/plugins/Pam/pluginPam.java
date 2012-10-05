package denoflionsx.plugins.Pam;

import denoflionsx.core.PfFPluginTemplate;
import denoflionsx.plugins.Pam.Crops.EnumPamCrops;

public class pluginPam extends PfFPluginTemplate{

    public pluginPam(String name, String parent) {
        super(name, parent);
    }

   
    @Override
    public void defaults() {
        
    }

    @Override
    public void doSetup() {
        EnumPamCrops.EXPLODY.values();
        EnumPamCrops.NONEXPLODY.values();
    }

  
    @Override
    public void recipes() {
        
    }
     
}
