package denoflionsx.plugins.Millenaire.Enums;

import denoflionsx.Enums.EnumModIDs;
import denoflionsx.denLib.denLib;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public enum EnumMillItems {
    
    BRICK_MOLD("brickmould");
    
    private Item item;
    private EnumMillItems(String field){
        this.item = denLib.getItem(EnumModIDs.MODS.millenaire.gettheClass(), field);
    }
    
    public ItemStack getItem(){
        return new ItemStack(this.item,1,0);
    }
    
}
