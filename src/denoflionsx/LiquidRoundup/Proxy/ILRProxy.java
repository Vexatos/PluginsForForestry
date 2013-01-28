package denoflionsx.LiquidRoundup.Proxy;

import java.awt.Color;
import net.minecraft.item.ItemStack;

public interface ILRProxy {
    
    public String getTexturePack();
    
    public String ExtractItemStackSprite(ItemStack item);
    
    public void registerLiquidTexture(String overlayPath, int index);
    
    public void registerContainerTextureFromTexturePack(String overlayPath, String pack, int index);
    
    public void registerContainerTexture(String overlayPath, int index);
    
    public String createGenericLiquidTexture(String name, int r, int g, int b);
    
    public Color figureOutSpriteColor(String path);
    
    public String getModsDir();
    
    public String createNewSpriteSheet(String name);
    
    public void addTextureFX(int rMin, int rMax, int gMin, int gMax, int bMin, int bMax, int iconIndex, String textureFile);
    
    public void registerFX();
    
    public void save();
    
}
