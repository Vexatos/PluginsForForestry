package denoflionsx.PluginsforForestry_PluginForestry.gfx;

public class ForestryGfxPackage {
    
    public static String getPackage(){
        return "/" + ForestryGfxPackage.class.getPackage().getName().replace(".", "/");
    }
    
}
