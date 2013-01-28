package denoflionsx.PluginsforForestry.Items.Plants;

import denoflionsx.PluginsforForestry.API.Interfaces.IPfFSqueezable;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PlantCarrot implements IPfFSqueezable{

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Item.carrot);
    }

    @Override
    public int getLiquidAmount() {
        return CoreTuning.Tuning.carrot_squeezeAmount;
    }

    @Override
    public String getName() {
        return "Carrot";
    }
}
