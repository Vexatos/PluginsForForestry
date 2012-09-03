package net.minecraft.src.denoflionsx.OldUnusedCode;

import java.util.Random;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;

public class waxBlock extends multiBlock{
    
    public static int id;

    public waxBlock(int par1, Material par2Material, String name) {
        super(par1, par2Material, name);
        id = par1;
        ModLoader.registerBlock(this, waxBlockItem.class);
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3) {
        return waxSlab.id;
    }

    @Override
    public int quantityDropped(Random par1Random) {
        return 2;
    }
    
    
    
    
}
