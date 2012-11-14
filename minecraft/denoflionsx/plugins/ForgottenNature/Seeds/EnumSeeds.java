package denoflionsx.plugins.ForgottenNature.Seeds;

import denoflionsx.denLib.denLib;
import denoflionsx.plugins.ForgottenNature.Crops.EnumCrops;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

public class EnumSeeds {
    
    public static final int newCropSeedsID = denLib.ReflectionHelper.getStaticInt(EnumCrops.clazzpath, "newCropSeedsID");
    public static final Item seed = Item.itemsList[newCropSeedsID];
    
    public static enum SEEDS{
        COTTON(2);
        
        private int meta;

        private SEEDS(int meta) {
            this.meta = meta;
        }

        public int getMeta() {
            return meta;
        }
        
        public ItemStack getItemStack(){
            return new ItemStack(seed,1,meta);
        }
        
    }
    
}
