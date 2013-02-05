package denoflionsx.PluginsforForestry.Integration.ForestryIntegration;

import cpw.mods.fml.common.network.IGuiHandler;
import denoflionsx.PluginsforForestry.Blocks.Farm.BlockFarmPart;
import denoflionsx.PluginsforForestry.Blocks.Farm.ItemBlockFarmPart;
import denoflionsx.PluginsforForestry.Blocks.Farm.TileEntityFarmPart;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.denLib.FMLWrapper;
import forestry.api.core.*;
import forestry.api.cultivation.CropProviders;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommand;
import net.minecraft.world.World;

@PluginInfo(pluginID = "PfF2", name = "Plugins for Forestry 2")
public class PluginPfF implements IPlugin {

    private Block farmblock;
    private final boolean test = true;

    @Override
    public void doInit() {
        CropProviders.cerealCrops.add(new OmniPlantProvider());
        if (test) {
            farmblock = new BlockFarmPart(CoreTuning.Blocks.farmblock, Material.clay);
            FMLWrapper.MODE.FML.registerBlockWithItem(farmblock, ItemBlockFarmPart.class);
            FMLWrapper.MODE.FML.registerTileEntity(TileEntityFarmPart.class, "PfFFarmPart1");
        }
    }

    @Override
    public void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
    }

    @Override
    public ICommand[] getConsoleCommands() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Plugins for Forestry!";
    }

    @Override
    public IOreDictionaryHandler getDictionaryHandler() {
        return null;
    }

    @Override
    public IGuiHandler getGuiHandler() {
        return null;
    }

    @Override
    public IPacketHandler getPacketHandler() {
        return null;
    }

    @Override
    public IPickupHandler getPickupHandler() {
        return null;
    }

    @Override
    public IResupplyHandler getResupplyHandler() {
        return null;
    }

    @Override
    public ISaveEventHandler getSaveEventHandler() {
        return null;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void postInit() {
    }

    @Override
    public void preInit() {
    }
}
