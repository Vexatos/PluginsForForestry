package denoflionsx.API;

import denoflionsx.API.Interfaces.*;

public class PfFManagers {
    
    // These managers are created during @PreInit.
    
    // This manager contains all PfF ItemStacks.
    public static IPfFItemManager ItemManager;
    
    // This manager contains valid targets for the Extractor tool.
    public static IPfFExtractorManager ExtractorTargetManager;
    
    // This manager contains a translation of my color enum. So the API doesn't break if I add new things to the enum.
    // On load the enum will be transformed into PfFColor objects and added to the manager.
    public static IPfFColorManager ColorManager;
    
    // This manager contains liquids and mappings for the wooden bucket and barrel.
    // Other future containers will reference this to get their mappings.
    public static IPfFContainerManager ContainerManager;
    
    // This manager contains bonus items dropped by killing things with the butcher knife.
    public static IPfFButcherKnifeManager ButcherKnifeManager;
    
    // This manager wraps the Forestry Fermenter.
    // If you register an item here it will have all possible recipes created for it.
    // This only works for situations where any liquid can be combined with an item. Special cases like Sugary Peat still need to be done manually.
    public static IPfFFermenterManager FermenterManager;
    
}
