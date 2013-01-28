package denoflionsx.PluginsforForestry.Items.Plants;

import denoflionsx.PluginsforForestry.API.Interfaces.IPfFSqueezable;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class PlantPumpkin implements IPfFSqueezable{

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Block.pumpkin);
    }

    @Override
    public int getLiquidAmount() {
        return CoreTuning.Tuning.pumpkin_squeezeAmount;
    }

    @Override
    public String getName() {
        return "Pumpkin";
    } 
}
