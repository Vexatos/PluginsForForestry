package net.minecraft.src.denoflionsx.OldUnusedCode;

import java.util.HashMap;
import net.minecraft.src.*;
import net.minecraft.src.denoflionsx.core.core;
import net.minecraft.src.forge.ITextureProvider;

public class multiBlock extends Block implements ITextureProvider {

    protected HashMap<Integer, String> nameMap = new HashMap();
    protected HashMap<Integer, Integer[]> textureMap = new HashMap();

    public multiBlock(int par1, Material par2Material, String name) {
        super(par1, par2Material);
        this.setHardness(1.5F);
        this.setBlockName(name);
    }

    public void add(String name, String DisplayName, int dmg, Integer tex[]) {
        this.nameMap.put(dmg, DisplayName);
        this.textureMap.put(dmg, tex);
        if (core.isClient()) {
            ModLoader.addLocalization(this.nameMap.get(dmg) + ".name", DisplayName);
        }
    }

    public void add(String name, String DisplayName, int dmg, Integer tex) {
        Integer tex2[] = new Integer[]{tex, tex, tex, tex, tex, tex};
        this.add(name, DisplayName, dmg, tex2);
    }

    @Override
    public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
        Integer map[] = this.textureMap.get(par2);
        // Attempted fix for NPE. 
        // I think the crash has something to do with multipaged chests...
        // ...from other mods.
        Integer tex = 0;
        if (map[par1] != null) {
            // Set texture as normal.
            tex = map[par1];
        } else {
            // Attempt to find any valid texture.
            for (Integer i : map) {
                if (i != null) {
                    tex = i;
                    break;
                }
            }
        }
        return tex;
    }

    @Override
    public String getTextureFile() {
        return mod_PluginsforForestry.texture;
    }
}
