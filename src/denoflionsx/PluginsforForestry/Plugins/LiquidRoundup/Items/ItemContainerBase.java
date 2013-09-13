package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFContainer;
import denoflionsx.PluginsforForestry.API.PfFAPI;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import denoflionsx.PluginsforForestry.Utils.PfFLib;
import denoflionsx.denLib.Lib.denLib;
import denoflionsx.denLib.Mod.Handlers.IDictionaryListener;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.IdenWorldEventHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ItemFluidContainer;

public class ItemContainerBase extends ItemFluidContainer implements IPfFContainer, IDictionaryListener, IdenWorldEventHandler {

    private String unloc;
    private String tag;
    private String icon;
    protected BiMap<Integer, String> fluids = HashBiMap.create();
    protected ArrayList<ItemStack> stacks = new ArrayList();
    protected HashMap<Integer, ItemStack> filledMap = new HashMap();
    private ItemStack empty;
    private boolean isBucket = false;

    public ItemContainerBase(int itemID, int capacity, String unloc, String tag, String icon) {
        super(itemID, capacity);
        this.setCreativeTab(PfFAPI.tab);
        this.setUnloc(unloc);
        this.setTag(tag);
        this.setIcon(icon);
        if (PfF.core.getMappingFile(this.getContainerTag()).exists()) {
            fluids = denLib.FileUtils.readBiMapFromFile(PfF.core.getMappingFile(this.getContainerTag()));
        }
        empty = new ItemStack(this);
        stacks.add(empty);
        this.setCreativeTab(PfFAPI.tab);
        PluginLR.stacks.put(tag, empty);
    }

    @Override
    public int fill(ItemStack container, FluidStack resource, boolean doFill) {
        if (PluginLR.blackLists.get(tag) != null) {
            if (!PluginLR.blackLists.get(tag).contains(resource.getFluid().getName())) {
                return super.fill(container, resource, doFill);
            }
        }
        return 0;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String getContainerTag() {
        return this.tag;
    }

    public boolean isIsBucket() {
        return isBucket;
    }

    public void setIsBucket(boolean isBucket) {
        this.isBucket = isBucket;
        if (this.isBucket) {
            this.setContainerItem(this);
            this.setMaxStackSize(1);
        } else {
            this.setContainerItem(null);
            this.setMaxStackSize(64);
        }
    }

    public void setUnloc(String unloc) {
        this.unloc = unloc;
    }

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack) {
        if (par1ItemStack.stackTagCompound != null) {
            NBTTagCompound fluid = par1ItemStack.stackTagCompound.getCompoundTag("Fluid");
            FluidStack f = FluidStack.loadFluidStackFromNBT(fluid);
            return PfFLib.FluidUtils.fixName(f.getFluid().getName()) + " " + PfFTranslator.instance.translateKey(unloc);
        }
        return PfFTranslator.instance.translateKey(unloc);
    }

    @Override
    public void onEvent(String tag, short channel, Object o) {
        FluidStack f = new FluidStack((Fluid) o, this.capacity);
        if (f.getFluid().isGaseous()) {
            return;
        }
        if (!PluginLR.blackLists.get(this.getContainerTag()).contains(tag) && !tag.toLowerCase().contains("molten")) {
            int id;
            if (!fluids.values().contains(tag)) {
                id = PfFLib.MathUtils.getLastID(fluids);
            } else {
                id = fluids.inverse().get(tag);
            }
            fluids.put(id, tag);
            PfF.Proxy.print("Generating " + this.tag + " for " + tag);
            ItemStack filled = new ItemStack(this, 1, id);
            filled.stackTagCompound = new NBTTagCompound();
            NBTTagCompound fluidTag = f.writeToNBT(new NBTTagCompound());
            filled.stackTagCompound.setTag("Fluid", fluidTag);
            stacks.add(filled);
            FluidContainerRegistry.registerFluidContainer(new FluidContainerData(f, filled, empty, isBucket));
            if (f.getFluid().canBePlacedInWorld() && this.isBucket) {
                filledMap.put(f.getFluid().getBlockID(), filled);
                PfF.Proxy.print(f.getFluid().getName() + " is placable. Registering as such.");
            }
        }
    }

    @Override
    public void onWorldLoaded() {
        denLib.FileUtils.saveBiMapToFile(fluids, PfF.core.getMappingFile(this.getContainerTag()));
    }

    @Override
    public void onWorldEnded() {
    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        super.registerIcons(par1IconRegister);
        this.itemIcon = par1IconRegister.registerIcon("@NAME@".toLowerCase() + ":".concat(icon));
    }

    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.addAll(stacks);
    }
}
