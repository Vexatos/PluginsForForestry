package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFContainer;
import denoflionsx.PluginsforForestry.API.PfFAPI;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.ModAPIWrappers.Forestry;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import denoflionsx.PluginsforForestry.Utils.PfFLib;
import denoflionsx.denLib.Lib.denLib;
import denoflionsx.denLib.Mod.Handlers.IDictionaryListener;
import denoflionsx.denLib.Mod.Handlers.WorldHandler.IdenWorldEventHandler;
import denoflionsx.denLib.NewConfig.ConfigField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidContainerRegistry.FluidContainerData;
import net.minecraftforge.fluids.FluidStack;

public class ItemContainerBase extends Item implements IPfFContainer, IDictionaryListener, IdenWorldEventHandler {

    private String unloc;
    private String tag;
    protected BiMap<Integer, String> fluids = HashBiMap.create();
    protected HashMap<Integer, ItemStack> filledMap = new HashMap();
    protected ArrayList<ItemStack> stacks = new ArrayList();
    private ItemStack empty;
    private boolean isBucket = false;
    private int capacity;
    private Icon overlay;
    private String[] iconStrings = new String[2];
    @ConfigField(category = "rendering.colors", comment = "The 0x before the values aren't required. They are stripped off anyways.")
    public static String[] colorArray = new String[]{"water:0xFF2C3EF4", "lava:0xFFDA6717", "milk:0xFFFFFF"};
    public static HashMap<String, Integer> colorMap = new HashMap();
    public HashMap<Integer, Integer> colorMapMeta = new HashMap();

    public ItemContainerBase(int itemID, int capacity, String unloc, String tag, String icon) {
        super(itemID);
        this.setCreativeTab(PfFAPI.tab);
        this.setUnloc(unloc);
        this.setTag(tag);
        this.capacity = capacity;
        if (PfF.core.getMappingFile(this.getContainerTag()).exists()) {
            fluids = denLib.FileUtils.readBiMapFromFile(PfF.core.getMappingFile(this.getContainerTag()));
        }
        empty = new ItemStack(this);
        stacks.add(empty);
        this.setCreativeTab(PfFAPI.tab);
        PluginLR.stacks.put(tag, empty);
        iconStrings[0] = icon;
        iconStrings[1] = icon.concat("_overlay");
        for (String s : colorArray) {
            String[] parse = s.split(":");
            if (parse.length == 2) {
                colorMap.put(parse[0], parseInt(parse[1]));
            }
        }
    }

    private int parseInt(String s) {
        if (s == null) {
            return -1;
        }
        PfF.Proxy.print("Parsing " + s);
        return (int) Long.parseLong(s.replace("0x", ""), 16);
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
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("@NAME@:".toLowerCase().concat(iconStrings[0]));
        this.overlay = par1IconRegister.registerIcon("@NAME@:".toLowerCase().concat(iconStrings[1]));
    }

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack) {
        if (par1ItemStack.getItemDamage() > 0) {
            return PfFLib.FluidUtils.fixName(fluids.get(par1ItemStack.getItemDamage())).concat(" ").concat(PfFTranslator.instance.translateKey(unloc));
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
            stacks.add(filled);
            FluidContainerRegistry.registerFluidContainer(new FluidContainerData(f, filled, empty, isBucket));
            Forestry.squeezer(5, new ItemStack[]{filled}, f);
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
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
        if (par2 > 0) {
            if (colorMapMeta.containsKey(par1ItemStack.getItemDamage())) {
                return colorMapMeta.get(par1ItemStack.getItemDamage());
            } else {
                colorMapMeta.put(par1ItemStack.getItemDamage(), this.getColorForMeta(par1ItemStack));
            }
        }
        return 0xFFFFFF;
    }

    private int getColorForMeta(ItemStack par1ItemStack) {
        if (par1ItemStack.getItemDamage() == 0) {
            return 0xFFFFFF;
        }
        FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(par1ItemStack);
        if (fluid == null) {
            return 0xFFFFFF;
        }
        if (fluid.getFluid().getColor() == 0xFFFFFF) {
            if (!colorMap.containsKey(fluid.getFluid().getName())) {
                int db = tryDB(fluid.getFluid().getName());
                if (db != -1) {
                    PfF.Proxy.print("Valid overlay color for " + fluid.getFluid().getName() + " found in ColorOverlayValues.db");
                    return db;
                }
                String a = "";
                if (fluid.getFluid().getIcon() != null) {
                    a = fluid.getFluid().getIcon().getIconName();
                }
                PfF.Proxy.print("Fluid " + fluid.getFluid().getName() + " does not have a valid color assigned to it! Please report this to the author of the mod the fluid came from! (" + a.split(":")[0] + "). You can also manually assign a color in the config file.");
            } else {
                return colorMap.get(fluid.getFluid().getName());
            }
        }
        return fluid.getFluid().getColor();
    }

    private int tryDB(String fluidName) {
        return parseInt(denLib.SQLHelper.getStringInDB(new String[]{"./mods/denLib/ColorOverlayValues.db", "ColorValues"}, fluidName));
    }

    @Override
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamageForRenderPass(int par1, int par2) {
        if (par2 > 0 && par1 > 0) {
            return this.overlay;
        } else {
            return this.itemIcon;
        }
    }

    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.addAll(stacks);
    }
}
