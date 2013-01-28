package denoflionsx.PluginsforForestry_PluginTweaks.Proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class PfFTweakProxy implements IPfFTweakProxy{

    @Override
    public void changeBlockLight(Block block, float light) {
        block.setLightValue(light);
    }

    @Override
    public void changeMaxStack(Item item, int max) {
        item.setMaxStackSize(max);
    }

}
