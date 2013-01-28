package denoflionsx.PluginsforForestry_PluginIc2.Items;

import denoflionsx.LiquidRoundup.APIWrappers.APIWrappers;
import denoflionsx.PluginsforForestry.API.Interfaces.IPfFSqueezable;
import denoflionsx.PluginsforForestry.API.PfFManagers;
import denoflionsx.PluginsforForestry.Items.PfFBase;
import denoflionsx.PluginsforForestry_PluginIc2.Config.Ic2Tuning;
import denoflionsx.PluginsforForestry_PluginIc2.PfFIc2;
import ic2.api.Items;
import java.util.HashMap;
import net.minecraft.item.ItemStack;

public class ItemRadioactiveWaste extends PfFBase implements IPfFSqueezable{

    //private static final ItemStack rcell = Items.getItem("reEnrichedUraniumCell");
    private static final ItemStack dcell = Items.getItem("reactorUraniumSimple");
    private static final ItemStack cell = Items.getItem("cell");
    
    public ItemRadioactiveWaste(int par1) {
        super(par1);
        this.setTextureFile(PfFIc2.Core.spritesheet);
        this.add("Radioactive Goo", 0, 16);
    }
    
    public final void recipe() {
        HashMap<ItemStack, Integer> drops = new HashMap();
        drops.put(PfFManagers.Items.getItemByTag("radioactivegoo"), 100);
        drops.put(cell, 100);
        APIWrappers.forestry.centrifuge.addRecipe(10, dcell, drops);
        //---
        APIWrappers.TE.pulv.addRecipe((150 * 2), dcell, PfFManagers.Items.getItemByTag("radioactivegoo"), cell);
    }
    
    public ItemRadioactiveWaste() {
        this(Ic2Tuning.Items.RadioactiveGoo_ItemID);
    }
    
    @Override
    public ItemStack getItemStack() {
        return PfFManagers.Items.getItemByTag("radioactivegoo");
    }
    
    @Override
    public int getLiquidAmount() {
        return Ic2Tuning.Fuels.RadioactiveWaste_AmountPerSqueeze;
    }
    
    @Override
    public String getName() {
        return "Radioactive Goo";
    }
    
}
