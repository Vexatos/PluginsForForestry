package denoflionsx.LiquidRoundup.Proxy;

import denoflionsx.LiquidRoundup.LiquidRoundup;
import denoflionsx.denLib.Interfaces.IDenProxy;
import denoflionsx.denLib.Mod.denLibMod;
import denoflionsx.denLib.denLib;
import java.awt.Color;
import java.io.File;
import net.minecraft.item.ItemStack;

public class Proxy implements IDenProxy, ILRProxy{

    @Override
    public String getTexturePack() {
        return "Default";
    }

    @Override
    public void registerContainerTextureFromTexturePack(String overlayPath, String pack, int index) {
        
    }

    @Override
    public void save() {
        LiquidRoundup.Core.config.save();
    }

    @Override
    public void registerFX() {
        
    }

    @Override
    public void addTextureFX(int rMin, int rMax, int gMin, int gMax, int bMin, int bMax, int iconIndex, String textureFile) {
        
    }

    @Override
    public String createNewSpriteSheet(String name) {
        return this.preloadTextures("/denoflionsx/LiquidRoundup/gfx/" + name);
    }
    
    @Override
    public String getModsDir() {
        return "./" + File.separator + "mods";
    }

    @Override
    public String ExtractItemStackSprite(ItemStack item) {
        return "";
    }
    
    @Override
    public Color figureOutSpriteColor(String path) {
        return new Color(255,255,255);
    }
    
    @Override
    public String createGenericLiquidTexture(String name, int r, int g, int b) {
        return "";
    }

    @Override
    public void registerLiquidTexture(String overlayPath, int index) {
        
    }

    @Override
    public void registerContainerTexture(String overlayPath, int index) {
        
    }

    @Override
    public String getConfigDir() {
        return denLibMod.proxy.getConfigDir() + "LiquidRoundup" + File.separator;
    }

    @Override
    public String preloadTextures(String texture) {
        return denLibMod.proxy.preloadTextures(texture);
    }

    @Override
    public void print(String msg) {
        denLib.print("[LiquidRoundup]: " + msg);
    }

    @Override
    public void sendMessageToPlayer(String msg) {
        denLibMod.proxy.sendMessageToPlayer(msg);
    }
}
