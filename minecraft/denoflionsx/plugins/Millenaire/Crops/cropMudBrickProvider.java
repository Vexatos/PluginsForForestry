package denoflionsx.plugins.Millenaire.Crops;

import denoflionsx.plugins.Millenaire.Enums.EnumMillBlocks;
import forestry.api.cultivation.ICropEntity;
import forestry.api.cultivation.ICropProvider;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class cropMudBrickProvider implements ICropProvider{

    @Override
    public boolean doPlant(ItemStack germling, World world, int x, int y, int z) {
        int space = world.getBlockId(x, y, z);
        if (space != 0){
            return false;
        }
        int field = world.getBlockId(x, y -1, z);
        if (field != Block.tilledField.blockID){
            return false;
        }else{
            world.setBlockAndMetadataWithNotify(x, y, z, EnumMillBlocks.WET_BRICK.getBlock().itemID, EnumMillBlocks.WET_BRICK.getBlock().getItemDamage());
            return true;
        }
    }

    @Override
    public ICropEntity getCrop(World world, int x, int y, int z) {
        return new cropMudBrick(world,x,y,z);
    }

    @Override
    public ItemStack[] getWindfall() {
        return new ItemStack[]{EnumMillBlocks.MUD_BRICK.getBlock()};
    }

    @Override
    public boolean isCrop(World world, int x, int y, int z) {
        int id = world.getBlockId(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);
        for (EnumMillBlocks b : EnumMillBlocks.values()){
            if (b.getBlock().itemID == id && b.getBlock().getItemDamage() == meta){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isGermling(ItemStack germling) {
        return germling.isItemEqual(EnumMillBlocks.WET_BRICK.getBlock());
    }

}
