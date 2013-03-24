package denoflionsx.PluginsforForestry.Integration;

import denoflionsx.PluginsforForestry.Integration.AEIntegration.AEIntegration;
import denoflionsx.PluginsforForestry.Integration.XycraftIntegration.XyIntegration;
import java.util.ArrayList;

public class IntegrationModules {

    public static ArrayList<IIntegrationModule> externalModules = new ArrayList();
    //
    public static IIntegrationModule Xycraft;
    public static IIntegrationModule AE;
    public static IIntegrationModule MFR;

    static {
        Xycraft = new XyIntegration();
        AE = new AEIntegration();
//        MFR = new MFRIntegration();
    }
}
