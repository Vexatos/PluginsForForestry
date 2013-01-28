package denoflionsx.PluginsforForestry.gfx;

public class PfFGfxPackage {
    
    public static String getPackage(){
        return "/" + PfFGfxPackage.class.getPackage().getName().replace(".", "/");
    }
    
}
