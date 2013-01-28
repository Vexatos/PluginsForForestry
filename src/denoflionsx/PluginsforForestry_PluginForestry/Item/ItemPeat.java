package denoflionsx.PluginsforForestry_PluginForestry.Item;

import denoflionsx.PluginsforForestry.API.Interfaces.IPfFSqueezable;
import forestry.api.core.ItemInterface;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerRegistry;

public class ItemPeat implements IPfFSqueezable{
    
    public static ItemStack peat;
    
    static{
        peat = ItemInterface.getItem("peat");
        if (peat == null){
            try{
                throw new Exception("Cannot find Forestry Peat!");
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public ItemStack getItemStack() {
        return peat;
    }

    @Override
    public int getLiquidAmount() {
        return (int)(LiquidContainerRegistry.BUCKET_VOLUME * 0.25);
    }

    @Override
    public String getName() {
        return "Peat";
    }
    
    
    
}
