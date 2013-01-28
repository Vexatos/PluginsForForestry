package denoflionsx.PluginsforForestry.Integration.MFRIntegration;

import denoflionsx.PluginsforForestry.Blocks.Plants.TileEntityOmniPlant;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.HarvestType;
import powercrystals.minefactoryreloaded.api.IFactoryHarvestable;

public class HarvestableOmniplant implements IFactoryHarvestable{

    @Override
    public boolean canBeHarvested(World world, Map<String, Boolean> harvesterSettings, int x, int y, int z) {
        TileEntityOmniPlant t = (TileEntityOmniPlant) world.getBlockTileEntity(x, y, z);
        if (t.getGrowthStage() == TileEntityOmniPlant.getFinalStage()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<ItemStack> getDrops(World world, Random rand, Map<String, Boolean> harvesterSettings, int x, int y, int z) {
        return new ArrayList();
    }

    @Override
    public HarvestType getHarvestType() {
        return HarvestType.Normal;
    }

    @Override
    public int getSourceId() {
        return CoreTuning.Blocks.omniplant;
    }

    @Override
    public void postHarvest(World world, int x, int y, int z) {
        
    }

    @Override
    public void preHarvest(World world, int x, int y, int z) {
        
    }
    
    
    
}
