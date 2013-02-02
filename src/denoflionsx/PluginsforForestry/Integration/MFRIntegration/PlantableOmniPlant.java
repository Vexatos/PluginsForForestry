package denoflionsx.PluginsforForestry.Integration.MFRIntegration;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.IFactoryPlantable;

public class PlantableOmniPlant implements IFactoryPlantable{

    @Override
    public boolean canBePlantedHere(World world, int x, int y, int z, ItemStack stack) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getPlantedBlockId(World world, int x, int y, int z, ItemStack stack) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getPlantedBlockMetadata(World world, int x, int y, int z, ItemStack stack) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getSourceId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void postPlant(World world, int x, int y, int z, ItemStack stack) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void prePlant(World world, int x, int y, int z, ItemStack stack) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
}
