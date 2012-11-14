package denoflionsx.plugins.ForgottenNature.Goods;

import denoflionsx.denLib.denLib;
import denoflionsx.plugins.ForgottenNature.Crops.EnumCrops;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class EnumGoods {
    
    public static final int newCropGoodsID = denLib.ReflectionHelper.getStaticInt(EnumCrops.clazzpath, "newCropGoodsID");
    public static final Item good = Item.itemsList[newCropGoodsID];
    
    public static enum GOOD{
        COTTON(0);
        
        private int meta;

        private GOOD(int meta) {
            this.meta = meta;
        }

        public int getMeta() {
            return meta;
        }
        
        public ItemStack getItemStack(){
            return new ItemStack(good,1,meta);
        }
    }
    
}
