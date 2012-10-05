package denoflionsx.Proxy;

import cpw.mods.fml.client.FMLTextureFX;
import denoflionsx.PluginsforForestry;
import java.io.File;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;

public class Proxy {

    public void preloadTexture(String texture) {
    }

    public void openGUI(PluginsforForestry p, EntityPlayer pl, World world, int id, int x, int y, int z) {
        pl.openGui(p, id, world, x, y, z);
    }
    
    public void registerRender(){
        
    }

    public boolean isClient() {
        return false;
    }

    public boolean isServer() {
        return !false;
    }

    public FMLTextureFX addLiquidFX(int rMin, int rMax, int gMin, int gMax, int bMin, int bMax, int iconIndex, String textureFile) {
        return null;
    }

    public void registerFX() {

    }
    
    public void registerAchievements(){
        
    }
    
    public String getConfigDir(){
        return "./" + File.separator + "config" + File.separator + "denoflionsx" + File.separator;
    }
}
