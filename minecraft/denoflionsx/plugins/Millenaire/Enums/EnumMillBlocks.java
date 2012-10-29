package denoflionsx.plugins.Millenaire.Enums;

import denoflionsx.Enums.EnumModIDs;
import denoflionsx.denLib.denLib;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;

public enum EnumMillBlocks {
    
    MUD_BRICK("stone_decoration",1),
    WET_BRICK("earth_decoration",0);
    
    private int meta;
    private Block block;
    private EnumMillBlocks(String field, int meta){
        this.meta = meta;
        this.block = denLib.getBlock(EnumModIDs.MODS.millenaire.gettheClass(), field);
    }
    
    public ItemStack getBlock(){
        return new ItemStack(block,1,meta);
    }
    
}
