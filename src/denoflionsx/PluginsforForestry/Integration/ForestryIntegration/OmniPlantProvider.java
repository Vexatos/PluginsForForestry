package denoflionsx.PluginsforForestry.Integration.ForestryIntegration;

import denoflionsx.PluginsforForestry.API.Objects.OmniPlantExternal;
import denoflionsx.PluginsforForestry.API.Objects.OmniPlantProduct;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Blocks.Plants.TileEntityOmniPlant;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.PluginsforForestry.Managers.PfFOmniPlantManager;
import denoflionsx.PluginsforForestry.PfF;
import forestry.api.cultivation.ICropEntity;
import forestry.api.cultivation.ICropProvider;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class OmniPlantProvider implements ICropProvider {

    @Override
    public boolean doPlant(ItemStack germling, World world, int x, int y, int z) {
        int b = world.getBlockId(x, y - 1, z);
        int a = world.getBlockId(x, y, z);
        if (b == Block.tilledField.blockID && a == 0) {
            world.setBlockAndMetadataWithNotify(x, y, z, CoreTuning.Blocks.omniplant, 0);
            world.setBlockTileEntity(x, y, z, new TileEntityOmniPlant());
            TileEntityOmniPlant p = (TileEntityOmniPlant) world.getBlockTileEntity(x, y, z);
            for (OmniPlantExternal e : PfFManagers.OmniPlant.getExternalPlants()){
                if (germling.isItemEqual(e.getSeed())){
                    p.setProduct(e.getProduct());
                    p.setExternalStatus(true);
                    //PfF.Proxy.print("Planted Omniplant in mimic mode!");
                    return true;
                }
            }
            p.setProduct(PfFManagers.OmniPlant.getPlantByInternalID(germling.getItemDamage()).getProduce());
            return true;
        }
        return false;
    }

    @Override
    public ICropEntity getCrop(World world, int x, int y, int z) {
        return new OmniPlantCrop(world, x, y, z);
    }

    @Override
    public ItemStack[] getWindfall() {
        ArrayList<ItemStack> stacks = new ArrayList();
        int i = 0;
        for (OmniPlantProduct p : PfFOmniPlantManager.products) {
            if (p != null) {
                stacks.add(p.getProduce());
            }
        }
        for (OmniPlantExternal e : PfFManagers.OmniPlant.getExternalPlants()){
            if (e != null){
                stacks.add(e.getProduct());
                stacks.add(e.getSeed());
            }
        }
        ItemStack[] w = new ItemStack[stacks.size() + 1];
        for (ItemStack q : stacks){
            w[i] = q;
            i++;
        }
        w[i] = PfFManagers.Items.getItemByTag("omniplantseed");
        return w;
    }

    @Override
    public boolean isCrop(World world, int x, int y, int z) {
        int id = world.getBlockId(x, y, z);
        if (id == CoreTuning.Blocks.omniplant) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isGermling(ItemStack germling) {
        if (germling.itemID == PfFManagers.Items.getItemByTag("omniplantseed").itemID) {
            return true;
        } else {
            for (OmniPlantExternal e : PfFManagers.OmniPlant.getExternalPlants()){
                if (germling.isItemEqual(e.getSeed())){
                    return true;
                }
            }
        }
        return false;
    }
}
