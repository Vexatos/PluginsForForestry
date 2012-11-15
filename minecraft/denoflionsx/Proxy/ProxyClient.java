/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package denoflionsx.Proxy;

import cpw.mods.fml.client.FMLTextureFX;
import denoflionsx.PluginsforForestry;
import denoflionsx.core.core;
import denoflionsx.plugins.Forestry.Utility.LiquidFXHook;
import java.io.File;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.MinecraftForgeClient;

public class ProxyClient extends Proxy {

    public static ArrayList<FMLTextureFX> fx = new ArrayList();

    @Override
    public FMLTextureFX addLiquidFX(int rMin, int rMax, int gMin, int gMax, int bMin, int bMax, int iconIndex, String textureFile) {
        return LiquidFXHook.addTexture(rMin, rMax, gMin, gMax, bMin, bMax, iconIndex, textureFile);
    }

    @Override
    public void bindTexture(String texture) {
        ForgeHooksClient.bindTexture(texture, 0);
    }

    @Override
    public void registerGUIElements() {
        //PfFGUIElementManager.addElement("Incubator", new GUIElementIncubator());
    }

    @Override
    public void registerTileEntites() {
        super.registerTileEntites();
    }

    @Override
    public boolean isClient() {
        return true;
    }

    @Override
    public void registerRender() {
    }

    @Override
    public void registerFX() {
        try {
            for (FMLTextureFX f : fx) {
                ModLoader.getMinecraftInstance().renderEngine.registerTextureFX(f);
            }
        } catch (Exception ex) {
            core.print("An error has occurred in ProxyClient.registerFX. Attempting to prevent the crash...");
        }
    }

    @Override
    public boolean isServer() {
        return false;
    }

    @Override
    public void openGUI(PluginsforForestry p, EntityPlayer pl, World world, int id, int x, int y, int z) {
        super.openGUI(p, pl, world, id, x, y, z);
    }

    @Override
    public void preloadTexture(String texture) {
        MinecraftForgeClient.preloadTexture(texture);
    }

    @Override
    public void registerAchievements() {
    }

    @Override
    public String getConfigDir() {
        return Minecraft.getMinecraftDir() + File.separator + "config" + File.separator + "denoflionsx" + File.separator;
    }
}
