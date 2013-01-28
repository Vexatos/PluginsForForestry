package denoflionsx.PluginsforForestry.Items.Plants;

import denoflionsx.PluginsforForestry.API.Interfaces.IPfFSqueezable;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class PlantMelonBlock implements IPfFSqueezable{

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Block.melon);
    }

    @Override
    public int getLiquidAmount() {
        return CoreTuning.Tuning.melonBlock_squeezeAmount;
    }

    @Override
    public String getName() {
        return "Melon";
    } 
}
