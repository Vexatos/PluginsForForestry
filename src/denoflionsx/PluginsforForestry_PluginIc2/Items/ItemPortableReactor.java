package denoflionsx.PluginsforForestry_PluginIc2.Items;

import denoflionsx.LiquidRoundup.Utils.StackUtils;
import denoflionsx.PluginsforForestry.Annotations.UnderConstruction;
import denoflionsx.PluginsforForestry.Items.PfFBase;
import denoflionsx.PluginsforForestry.PfF;
import denoflionsx.PluginsforForestry_PluginIc2.Config.Ic2Tuning;
import denoflionsx.PluginsforForestry_PluginIc2.PfFIc2;
import denoflionsx.PluginsforForestry_PluginIc2.Utils.ReactorUtils;
import denoflionsx.denLib.FMLWrapper;
import denoflionsx.denLib.denLib;
import ic2.api.IReactor;
import ic2.api.Items;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

@UnderConstruction
public class ItemPortableReactor extends PfFBase {

    private static final String[] valid = new String[]{"ic2.core.block.wiring.TileEntityElectricBatBox", "ic2.core.block.wiring.TileEntityElectricMFE", "ic2.core.block.wiring.TileEntityElectricMFSU"};

    public ItemPortableReactor(int par1) {
        super(par1);
        this.setTextureFile(PfFIc2.Core.spritesheet);
        this.setMaxStackSize(1);
        this.add("Reactor Kit", 0, 1);
        this.add("Reactor Kit", 1, 2);
        this.add("Energy Storage Kit", 2, 3);
        this.add("Energy Storage Kit", 3, 4);
    }

    public final void recipe() {
        FMLWrapper.MODE.FML.addRecipe(new ItemStack(this, 1, 0), new Object[]{"UCU", "IAI", "IMI", Character.valueOf('U'), StackUtils.getNewStack(Items.getItem("uraniumBlock"), 1), Character.valueOf('C'), new ItemStack(Block.chest), Character.valueOf('I'), StackUtils.getNewStack(Items.getItem("refinedIronIngot"), 1), Character.valueOf('A'), StackUtils.getNewStack(Items.getItem("advancedMachine"), 1), Character.valueOf('M'), StackUtils.getNewStack(Items.getItem("machine"), 1)});
        FMLWrapper.MODE.FML.addRecipe(new ItemStack(this, 1, 2), new Object[]{"UCU", "IAI", "IMI", Character.valueOf('U'), StackUtils.getNewStack(new ItemStack(Item.diamond), 1), Character.valueOf('C'), new ItemStack(Block.chest), Character.valueOf('I'), StackUtils.getNewStack(Items.getItem("refinedIronIngot"), 1), Character.valueOf('A'), StackUtils.getNewStack(Items.getItem("advancedMachine"), 1), Character.valueOf('M'), StackUtils.getNewStack(Items.getItem("machine"), 1)});
    }

    public ItemPortableReactor() {
        this(Ic2Tuning.Items.PortableReactor_ItemID);
    }

    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        switch (par1ItemStack.getItemDamage()) {
            case 0:
                par3List.add("Empty");
                break;
            case 1:
                par3List.add("Full");
                break;
            case 2:
                par3List.add("Empty");
                break;
            case 3:
                if (par1ItemStack.stackTagCompound != null) {
                    if (par1ItemStack.stackTagCompound.hasKey("Energy")) {
                        NBTTagCompound tag = par1ItemStack.stackTagCompound.getCompoundTag("Energy");
                        int e = tag.getInteger("energy");
                        String type = tag.getString("id");
                        par3List.add("Storage: " + type);
                        par3List.add("Energy: " + String.valueOf(e));
                    }
                }
                break;
            case 4:
                break;
        }
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (par3World.isRemote) {
            return false;
        }
        switch (par1ItemStack.getItemDamage()) {
            case 0: // State is Empty
                if (par3World.getBlockTileEntity(par4, par5, par6) != null) {
                    TileEntity t = par3World.getBlockTileEntity(par4, par5, par6);
                    if (t instanceof IReactor) {
                        IReactor reactor = (IReactor) t;
                        if (par1ItemStack.stackTagCompound == null) {
                            par1ItemStack.stackTagCompound = new NBTTagCompound();
                        }
                        ReactorUtils.reactorToNBT(reactor, par1ItemStack.stackTagCompound);
                        int id = par3World.getBlockId(par4, par5, par6);
                        int meta = par3World.getBlockMetadata(par4, par5, par6);
                        NBTTagCompound r = new NBTTagCompound();
                        r.setInteger("ID", id);
                        r.setInteger("Meta", meta);
                        par1ItemStack.stackTagCompound.setCompoundTag("Reactor", r);
                        par3World.setBlock(par4, par5, par6, 0);
                        par1ItemStack.setItemDamage(1);
                        PfF.Proxy.sendMessageToPlayer("Stored Reactor Setup!");
                    }
                }
                break;
            case 1:
                if (par1ItemStack.stackTagCompound == null) {
                    return false;
                }
                if (!par1ItemStack.stackTagCompound.hasKey("Reactor")) {
                    return false;
                }
                int id = par3World.getBlockId(par4, par5 + 1, par6);
                if (id == 0) {
                    NBTTagCompound r = par1ItemStack.stackTagCompound.getCompoundTag("Reactor");
                    par3World.setBlockAndMetadataWithNotify(par4, par5 + 1, par6, r.getInteger("ID"), r.getInteger("Meta"));
                } else {
                    return false;
                }
                if (par3World.getBlockTileEntity(par4, par5 + 1, par6) != null) {
                    TileEntity t = par3World.getBlockTileEntity(par4, par5 + 1, par6);
                    if (t instanceof IReactor) {
                        IReactor reactor = (IReactor) t;
                        ReactorUtils.NBTtoReactor(reactor, par1ItemStack.stackTagCompound);
                        par1ItemStack.setItemDamage(0);
                        PfF.Proxy.sendMessageToPlayer("Restored Reactor Setup!");
                        par1ItemStack.stackTagCompound = null;
                    }
                }
                break;
            case 2:
                if (par3World.getBlockTileEntity(par4, par5, par6) != null) {
                    TileEntity t = par3World.getBlockTileEntity(par4, par5, par6);
                    String name = t.getClass().getName();
                    for (String s : valid) {
                        if (name.equals(s)) {
                            if (par1ItemStack.stackTagCompound == null) {
                                par1ItemStack.stackTagCompound = new NBTTagCompound();
                            }
                            int id2 = par3World.getBlockId(par4, par5, par6);
                            int meta = par3World.getBlockMetadata(par4, par5, par6);
                            par1ItemStack.stackTagCompound.setInteger("ID", id2);
                            par1ItemStack.stackTagCompound.setInteger("Meta", meta);
                            NBTTagCompound energy = new NBTTagCompound();
                            t.writeToNBT(energy);
                            par1ItemStack.stackTagCompound.setCompoundTag("Energy", energy);
                            par3World.setBlockAndMetadataWithNotify(par4, par5, par6, 0, 0);
                            par1ItemStack.setItemDamage(3);
                            PfF.Proxy.sendMessageToPlayer("Stored Energy Block.");
                        }
                    }
                }
                break;
            case 3:
                if (par3World.getBlockId(par4, par5 + 1, par6) != 0) {
                    return false;
                } else {
                    int id3 = par1ItemStack.stackTagCompound.getInteger("ID");
                    int meta = par1ItemStack.stackTagCompound.getInteger("Meta");
                    par3World.setBlockAndMetadataWithUpdate(par4, par5 + 1, par6, id3, meta, true);
                    TileEntity t = par3World.getBlockTileEntity(par4, par5 + 1, par6);
                    NBTTagCompound energy = par1ItemStack.getTagCompound().getCompoundTag("Energy");
                    String name = t.getClass().getName();
                    for (String s : valid) {
                        if (s.equals(name)) {
                            denLib.ReflectionHelper.setIntFromTileEntity(s, "energy", t, energy.getInteger("energy"));
                        }
                    }
                    par1ItemStack.stackTagCompound = null;
                    par1ItemStack.setItemDamage(2);
                }
                break;
            case 4:
                break;
        }
        return false;
    }
}
