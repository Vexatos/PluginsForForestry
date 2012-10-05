package denoflionsx.plugins.FarmCraftory.Crops;

import denoflionsx.Enums.EnumModIDs;
import denoflionsx.denLib.denLib;
import denoflionsx.plugins.FarmCraftory.Crops.EnumCrops;
import denoflionsx.plugins.FarmCraftory.Crops.EnumCrops.PlantObject;
import forestry.api.cultivation.ICropEntity;
import forestry.api.cultivation.ICropProvider;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class cropHarvestProvider implements ICropProvider {

    public static final String theClass = EnumModIDs.MODS.FARMCRAFTORY.gettheClass();
    public static final String theField = "singleHarvestID";
    public static final String theOtherField = "multiHarvestID";
    public static final int singleHarvestID = denLib.ReflectionHelper.getStaticInt(theClass, theField);
    public static final int multiHarvestID = denLib.ReflectionHelper.getStaticInt(theClass, theOtherField);
    public Block tile;
    public PlantObject po;

    public cropHarvestProvider(PlantObject po) {
        this.tile = Block.blocksList[singleHarvestID];
        this.po = po;
    }

    @Override
    public boolean doPlant(ItemStack germling, World world, int x, int y, int z) {
        int var6 = world.getBlockId(x, y, z);

        if (var6 != 0) {
            return false;
        } else {
            int var7 = world.getBlockId(x, y - 1, z);

            if (var7 != Block.tilledField.blockID) {
                return false;
            } else {
                for (EnumCrops.SINGLE s : EnumCrops.SINGLE.values()){
                    if (s.getPlant().equals(this.po)){
                        setSingle();
                    }
                }
                for (EnumCrops.MULTI s : EnumCrops.MULTI.values()){
                    if (s.getPlant().equals(this.po)){
                        setMulti();
                    }
                }
                world.setBlockAndMetadataWithNotify(x, y, z, this.tile.blockID, 0);
                CropTileEntityHelper.getHelperType(world.getBlockTileEntity(x, y, z)).setCropID(world, x, y, z, po.getId());
                return true;
            }
        }
    }
    
    private void setSingle(){
        this.tile = Block.blocksList[singleHarvestID];
    }
    
    private void setMulti(){
        this.tile = Block.blocksList[multiHarvestID];
    }

    @Override
    public ICropEntity getCrop(World world, int x, int y, int z) {
        return new cropHarvest(world, x, y, z);
    }

    @Override
    public ItemStack[] getWindfall() {
        return this.po.asArray();
    }

    @Override
    public boolean isCrop(World world, int x, int y, int z) {
        return singleHarvestID == world.getBlockId(x, y, z) || multiHarvestID == world.getBlockId(x, y, z);
    }

    @Override
    public boolean isGermling(ItemStack germling) {
        return germling.itemID == this.po.getSeed().itemID;
    }
}
