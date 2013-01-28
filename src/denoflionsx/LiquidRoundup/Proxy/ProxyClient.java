package denoflionsx.LiquidRoundup.Proxy;

import cpw.mods.fml.client.FMLTextureFX;
import cpw.mods.fml.client.registry.RenderingRegistry;
import denoflionsx.LiquidRoundup.API.LRManagers;
import denoflionsx.LiquidRoundup.gfx.LiquidFX;
import denoflionsx.LiquidRoundup.gfx.SpriteUtils;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;

public class ProxyClient extends Proxy {

    private static boolean[] inject = new boolean[256];
    private static boolean injectC = false;
    public static ArrayList<FMLTextureFX> fx = new ArrayList();

    static {
        for (int i = 0; i < 256; i++) {
            inject[i] = false;
        }
    }

    @Override
    public String getTexturePack() {
        return Minecraft.getMinecraft().texturePackList.getSelectedTexturePack().getTexturePackFileName();
    }

    @Override
    public void registerFX() {
        for (FMLTextureFX f : fx) {
            ModLoader.getMinecraftInstance().renderEngine.registerTextureFX(f);
        }
    }

    @Override
    public void addTextureFX(int rMin, int rMax, int gMin, int gMax, int bMin, int bMax, int iconIndex, String textureFile) {
        fx.add(LiquidFX.addTexture(rMin, rMax, gMin, gMax, bMin, bMax, iconIndex, textureFile));
    }

    @Override
    public String getModsDir() {
        return Minecraft.getMinecraftDir() + File.separator + "mods";
    }

    @Override
    public String ExtractItemStackSprite(ItemStack item) {
        return SpriteUtils.ExtractSprite(item);
    }

    @Override
    public Color figureOutSpriteColor(String path) {
        return SpriteUtils.FigureOutSpriteColor(path);
    }

    @Override
    public String createGenericLiquidTexture(String name, int r, int g, int b) {
        return SpriteUtils.createNewLiquidSprite(name, r, g, b);
    }

    @Override
    public void registerLiquidTexture(String overlayPath, int index) {
        int sID = LRManagers.Liquids.getCurrentInt();
        String sS = LRManagers.Liquids.getSpritesheetPath(sID);
        if (!inject[sID]) {
            SpriteUtils.addBlankSheetToMap(sS);
            inject[sID] = true;
        }
        RenderingRegistry.addTextureOverride(sS, overlayPath, index);
    }

    @Override
    public void registerContainerTexture(String overlayPath, int index) {
        if (!injectC) {
            String p = "/denoflionsx/LiquidRoundup/gfx/" + "containersheet.png";
            SpriteUtils.addBlankSheetToMap(p);
            injectC = true;
        }
        RenderingRegistry.addTextureOverride("/denoflionsx/LiquidRoundup/gfx/containersheet.png", overlayPath, index);
    }
}
