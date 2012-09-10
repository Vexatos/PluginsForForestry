package denoflionsx.API;

public class PfFManagers {
    
    // These managers are created during @Init.
    // Wait until @PostInit to use them.
    
    // This manager contains all PfF ItemStacks.
    public static IPfFItemRegistry ItemManager;
    
    // This manager contains valid targets for the Extractor tool.
    public static IPfFExtractorTargetRegistry ExtractorTargetManager;
    
}
