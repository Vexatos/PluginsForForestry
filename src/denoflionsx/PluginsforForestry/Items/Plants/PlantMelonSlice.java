package denoflionsx.PluginsforForestry.Items.Plants;

import denoflionsx.PluginsforForestry.API.Interfaces.IPfFSqueezable;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PlantMelonSlice implements IPfFSqueezable{

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Item.melon);
    }

    @Override
    public int getLiquidAmount() {
        return CoreTuning.Tuning.melonSlice_squeezeAmount;
    }

    @Override
    public String getName() {
        return "Melon";
    } 
}
