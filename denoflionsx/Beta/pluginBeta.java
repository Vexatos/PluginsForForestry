package denoflionsx.Beta;

import denoflionsx.Machine.PfFMachineBlock;
import denoflionsx.core.PfFPluginTemplate;

public class pluginBeta extends PfFPluginTemplate{
    
    public static int blockid = 0;
    
    PfFMachineBlock block;

    public pluginBeta(String name, String parent) {
        super(name, parent);
    }

    @Override
    public void defaults() {
        this.config.addDefault("Test_BlockID=" + 3333);
        
        this.config.getOptionInt("Test_BlockID");
    }

    @Override
    public void doSetup() {

    }

    @Override
    public void recipes() {
        
    }

}
