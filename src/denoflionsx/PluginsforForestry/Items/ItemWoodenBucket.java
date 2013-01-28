package denoflionsx.PluginsforForestry.Items;

import denoflionsx.LiquidRoundup.API.LRManagers;
import denoflionsx.PluginsforForestry.API.Enums.EnumAnimals;
import denoflionsx.PluginsforForestry.Config.CoreTuning;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.denLib.FMLWrapper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;

public class ItemWoodenBucket extends PfFBase {

    public static ItemStack bucket;
    public final String cTexture = "/denoflionsx/PluginsforForestry/gfx/containers/woodenbucket.png";
    public final String oTexture = "/denoflionsx/PluginsforForestry/gfx/containers/overlays/woodenbucket_overlay.png";
    private int isFull;

    public ItemWoodenBucket(int par1) {
        super(par1);
        this.add("Wooden Bucket", 0, 31);
        this.setMaxStackSize(1);
    }

    public final void createRecipe() {
        FMLWrapper.MODE.FML.addRecipe(new ItemStack(this), new Object[]{"WXW", "XWX", "XXX", Character.valueOf('W'), Block.wood});
        FMLWrapper.MODE.FML.addRecipe(new ItemStack(this), new Object[]{"XXX", "WXW", "XWX", Character.valueOf('W'), Block.wood});
        for (ItemStack i : PfF.Core.logs) {
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(this), new Object[]{"WXW", "XWX", "XXX", Character.valueOf('W'), i});
            FMLWrapper.MODE.FML.addRecipe(new ItemStack(this), new Object[]{"XXX", "WXW", "XWX", Character.valueOf('W'), i});
        }
        PfF.Core.logs = null;
    }

    public ItemWoodenBucket() {
        this(CoreTuning.Items.woodenbucket);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (bucket == null) {
            ItemWoodenBucket.bucket = LRManagers.Liquids.getContainer("Water Wooden Bucket");
        }
        if (par2World.isRemote) {
            return par1ItemStack;
        }
        MovingObjectPosition var12 = this.getMovingObjectPositionFromPlayer(par2World, par3EntityPlayer, true);
        if (var12 == null) {
            return par1ItemStack;
        }
        int var13 = var12.blockX;
        int var14 = var12.blockY;
        int var15 = var12.blockZ;
        if (par1ItemStack.isItemEqual(new ItemStack(this, 1, 0))) {
            if (par2World.getBlockMaterial(var13, var14, var15) == Material.water && par2World.getBlockMetadata(var13, var14, var15) == 0) {
                par2World.setBlockWithNotify(var13, var14, var15, 0);
                if (par1ItemStack.stackSize > 1) {
                    par1ItemStack.stackSize--;
                    if (!par3EntityPlayer.inventory.addItemStackToInventory(ItemWoodenBucket.bucket.copy())) {
                        par3EntityPlayer.dropPlayerItemWithRandomChoice(ItemWoodenBucket.bucket.copy(), false);
                        return par1ItemStack;
                    }
                    return par1ItemStack;
                }
                return ItemWoodenBucket.bucket.copy();
            }
        }
        return par1ItemStack;
    }

    public boolean tryPlaceContainedLiquid(World par1World, double par2, double par4, double par6, int par8, int par9, int par10) {
        if (this.isFull <= 0) {
            return false;
        } else if (!par1World.isAirBlock(par8, par9, par10) && par1World.getBlockMaterial(par8, par9, par10).isSolid()) {
            return false;
        } else {
            if (par1World.provider.isHellWorld && this.isFull == Block.waterMoving.blockID) {
                par1World.playSoundEffect(par2 + 0.5D, par4 + 0.5D, par6 + 0.5D, "random.fizz", 0.5F, 2.6F + (par1World.rand.nextFloat() - par1World.rand.nextFloat()) * 0.8F);

                for (int var11 = 0; var11 < 8; ++var11) {
                    par1World.spawnParticle("largesmoke", (double) par8 + Math.random(), (double) par9 + Math.random(), (double) par10 + Math.random(), 0.0D, 0.0D, 0.0D);
                }
            } else {
                par1World.setBlockAndMetadataWithNotify(par8, par9, par10, this.isFull, 0);
            }

            return true;
        }
    }

//    @Override
//    public boolean itemInteractionForEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving) {
//        if (EnumAnimals.getAnimalType(par2EntityLiving).equals(EnumAnimals.ANIMALS.COW)){
//            ItemStack filled = LiquidContainerRegistry.fillLiquidContainer(LiquidDictionary.getLiquid("Milk", 1000), par1ItemStack).copy();
//            if (filled != null){
//                par1ItemStack = filled;
//            }
//        }
//        return super.itemInteractionForEntity(par1ItemStack, par2EntityLiving);
//    }
    
    
}
