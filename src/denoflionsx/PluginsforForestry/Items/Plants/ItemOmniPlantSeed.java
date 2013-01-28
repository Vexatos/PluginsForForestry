package denoflionsx.PluginsforForestry.Items.Plants;

import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.LiquidRoundup.Utils.StackUtils;
import denoflionsx.PluginsforForestry.API.Objects.OmniPlantProduct;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Blocks.Plants.TileEntityOmniPlant;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.PluginsforForestry.Items.PfFBase;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry.Utils.ForestryLiquids;
import denoflionsx.denLib.EnumTextColor;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

public class ItemOmniPlantSeed extends PfFBase implements IPlantable {

    private HashMap<Integer, String> seedMap = new HashMap();

    public ItemOmniPlantSeed(int par1) {
        super(par1);
    }

    public void recipe(LiquidStack oil) {
        for (OmniPlantProduct p : PfFManagers.OmniPlant.getProductList()) {
            if (p != null) {
                APIWrappers.forestry.carpenter.addRecipe(5, StackUtils.getNewStack(oil, 1000), null, StackUtils.getNewStack(p.getSeed(), 32), new Object[]{"PXX", "XXX", "XXX", Character.valueOf('P'), p.getProduce()});
                APIWrappers.TE.transposer.addFillRecipe(150, p.getProduce(), StackUtils.getNewStack(p.getSeed(), 32), StackUtils.getNewStack(oil, 1000), false);
                LiquidContainerData[] d = LiquidContainerRegistry.getRegisteredLiquidContainerData();
                for (LiquidContainerData q : d) {
                    try {
                        if (q.stillLiquid.isLiquidEqual(ForestryLiquids.BIOMASS.getStack())) {
                            if (!q.container.getItemName().equals("item.barrel") && q.container.itemID != Item.bucketEmpty.itemID) {
                                ItemStack z = LiquidContainerRegistry.fillLiquidContainer(oil, q.container);
                                if (z != null) {
                                    APIWrappers.forestry.carpenter.addRecipe(5, StackUtils.getNewStack(new LiquidStack(ForestryLiquids.SEEDOIL.getStack().itemID, 1), 500), null, z, new Object[]{"BXX", "XXX", "XXX", Character.valueOf('B'), q.filled});
                                }
                            } else {
                                //APIWrappers.forestry.carpenter.addRecipe((5 * 10), StackUtils.getNewStack(new LiquidStack(ForestryLiquids.SEEDOIL.getStack().itemID, 1), (500 * 10)), null, LiquidContainerRegistry.fillLiquidContainer(oil, q.container), new Object[]{"BXX", "XXX", "XXX", Character.valueOf('B'), q.filled});
                            }
                        }
                    } catch (Exception ex) {
                        PfF.Proxy.print("Error in Omniplant seed recipes. Trying to prevent the crash...");
                    }
                }
            }
        }
        PfF.Proxy.print("Created Omniplant Seed recipes!");
    }

    @Override
    public int getPlantID(World world, int x, int y, int z) {
        return CoreTuning.Blocks.omniplant;
    }

    @Override
    public int getPlantMetadata(World world, int x, int y, int z) {
        return 0;
    }

    @Override
    public EnumPlantType getPlantType(World world, int x, int y, int z) {
        return EnumPlantType.Crop;
    }

    public ItemOmniPlantSeed() {
        this(CoreTuning.Items.seed);
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (!par3World.isRemote) {
            int b = par3World.getBlockId(par4, par5, par6);
            if (b == Block.tilledField.blockID) {
                par3World.setBlockAndMetadataWithNotify(par4, par5 + 1, par6, CoreTuning.Blocks.omniplant, 0);
                par3World.setBlockTileEntity(par4, par5 + 1, par6, new TileEntityOmniPlant(PfFManagers.OmniPlant.getPlantByInternalID(par1ItemStack.getItemDamage()).getProduce()));
                PfF.Proxy.print("Omniplant planted at " + par4 + "," + par5 + "," + par6 + ". Target is " + PfFManagers.OmniPlant.getPlantByInternalID(par1ItemStack.getItemDamage()).getProduce().getItemName());
                par1ItemStack.stackSize--;
            }
        }
        return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
    }

    public void addProduct(ItemStack product, int internalID) {
        this.add("OmniPlant Seed", internalID, 51);
        seedMap.put(internalID, product.getDisplayName());
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("Product: " + EnumTextColor.DARK_GREEN.ColorString(seedMap.get(par1ItemStack.getItemDamage())));
    }
}
