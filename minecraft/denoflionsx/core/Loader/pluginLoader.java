package denoflionsx.core.Loader;

import cpw.mods.fml.common.registry.GameRegistry;
import denoflionsx.API.Events.EventItemInitialized;
import denoflionsx.API.Events.EventPluginLoaded;
import denoflionsx.Machine.PfFMachineBlock;
import denoflionsx.Machine.PfFMachineItemBlock;
import denoflionsx.core.PfFPluginTemplate;
import denoflionsx.core.core;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.items.Containers.Containers;
import denoflionsx.items.Containers.InfusionBar;
import denoflionsx.items.CraftingTools.ItemBlacksmithHammer;
import denoflionsx.items.CraftingTools.ItemIronRing;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;

public class pluginLoader extends PfFPluginTemplate {
    
    // This really isn't a plugin.
    // It is for triggering the first event that makes the event load system work.
    // I also moved the Universal item setup calls in here.

    public static PfFMachineBlock block;
    
    public pluginLoader(String name, String parent) {
        super(name, parent);
    }

    @Override
    public void onWorldLoaded() {
        // Overriding this to prevent a useless config file from generating.
    }

    @Override
    public void itemInitialized(EventItemInitialized event) {
        if (event.getName().equals("infusionbar")) {
            InfusionBar.recipe();
        }
    }

    @Override
    public void pluginLoaded(EventPluginLoaded event) {
    }

    @Override
    public void doSetup() {
        Containers.Container.register();
        ItemBlacksmithHammer.BlacksmithHammer();
        ItemIronRing.IronRing();
        block = new PfFMachineBlock(core.PfFCore.config.getOptionInt("Machine_BlockID"),Material.clay);
        FMLWrapper.MODE.FML.registerBlockWithItem(block, PfFMachineItemBlock.class);
        FMLWrapper.MODE.FML.addSmelt(new ItemStack(Block.dirt), new ItemStack(block,1,0));
        FMLWrapper.MODE.FML.addNameItemStack("Incubator", new ItemStack(block,1,0));
    }

    @Override
    public boolean init() {
        // Loader cannot be disabled.
        return true;
    }
}
