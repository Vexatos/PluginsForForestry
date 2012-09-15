package denoflionsx.API;

import denoflionsx.API.Interfaces.*;

public class PfFManagers {
    
    // These managers are created during @Init.
    // Wait until @PostInit to use them.
    
    // This manager contains all PfF ItemStacks.
    public static IPfFItemManager ItemManager;
    
    // This manager contains valid targets for the Extractor tool.
    public static IPfFExtractorManager ExtractorTargetManager;
    
    // This manager contains a translation of my color enum. So the API doesn't break if I add new things to the enum.
    // On load the enum will be transformed into a PfFColor object and added to the manager.
    public static IPfFColorManager ColorManager;
    
    // This manager contains liquids and mappings for the wooden bucket and barrel.
    public static IPfFContainerManager ContainerManager;
    
    // This manager contains bonus items dropped by killing things with the butcher knife.
    public static IPfFButcherKnifeManager ButcherKnifeManager;
    
}
