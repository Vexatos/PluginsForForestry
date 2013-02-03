package denoflionsx.PluginsforForestry.Integration;

import denoflionsx.PluginsforForestry.Integration.AEIntegration.AEIntegration;
import denoflionsx.PluginsforForestry.Integration.XycraftIntegration.XyIntegration;

public class IntegrationModules {
    
    public static IIntegrationModule Xycraft;
    public static IIntegrationModule AE;
    
    static{
        Xycraft = new XyIntegration();
        AE = new AEIntegration();
    }
    
}
