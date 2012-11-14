package denoflionsx.plugins.ForgottenNature;

import denoflionsx.Enums.EnumModIDs;
import denoflionsx.core.PfFPluginTemplate;
import denoflionsx.plugins.ForgottenNature.Crops.CropProviderCotton;
import forestry.api.cultivation.CropProviders;

public class pluginForgottenNature extends PfFPluginTemplate{

    public pluginForgottenNature(String name, String parent) {
        super(name, parent);
    }

    public pluginForgottenNature() {
        this("pluginForgottenNature", EnumModIDs.MODS.ForgottenNature.getID());
    }
    
    @Override
    public void defaults() {
        this.config.addDefault("ForestryIntegration=" + "true");
    }

    @Override
    public void doSetup() {
        
    }

    @Override
    public void recipes() {
        if (this.config.getOptionBool("ForestryIntegration")){
            CropProviders.cerealCrops.add(new CropProviderCotton());
        }
    }

}
