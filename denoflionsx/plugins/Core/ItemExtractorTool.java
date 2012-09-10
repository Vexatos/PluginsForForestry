package denoflionsx.plugins.Core;

import denoflionsx.API.PfFManagers;
import denoflionsx.core.ItemIDManager;
import denoflionsx.items.CustomTool;
import net.minecraft.src.*;

public class ItemExtractorTool extends CustomTool {

    public static ItemIDManager ID;
    public static boolean playerMode = true;
    public static int ConfigMaxDamage = 100;

    public ItemExtractorTool(int id) {
        super(id, EnumToolTextures.ToolTextures.EXTRACTOR, "Extractor Tool", ConfigMaxDamage);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        int id = world.getBlockId(x, y, z);
        int meta = world.getBlockMetadata(x, y, z);
        if (PfFManagers.ExtractorTargetManager.isValidTarget(id, meta)) {
            ItemStack I = new ItemStack(Block.blocksList[id], 1, meta);
            world.setBlockAndMetadataWithUpdate(x, y, z, 0, 0, true);
            world.setBlockTileEntity(x, y, z, null);
            if (!playerMode) {
                EntityItem i = new EntityItem(world, x, y, z, I);
                world.spawnEntityInWorld(i);
            } else {
                player.dropPlayerItem(I);
            }
            stack.damageItem(1, player);
        }
        return false;
    }
}
