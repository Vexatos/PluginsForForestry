package denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.Items;

import denoflionsx.PluginsforForestry.API.PfFAPI;
import denoflionsx.PluginsforForestry.Config.PfFTuning;
import denoflionsx.PluginsforForestry.Lang.PfFTranslator;
import denoflionsx.PluginsforForestry.Plugins.LiquidRoundup.PluginLR;
import denoflionsx.denLib.Mod.Items.ItemMeta;
import net.minecraft.item.ItemStack;

public class ItemRings extends ItemMeta {

    public ItemRings(int par1) {
        super(new String[]{"@NAME@".toLowerCase().concat(":iron_hoop")}, par1);
        this.setCreativeTab(PfFAPI.tab);
        this.createItemEntry(0, PfFTranslator.instance.translateKey("item.pff.rings.name"));
        PluginLR.stacks.put("rings", new ItemStack(this, PfFTuning.getInt(PfFTuning.Barrel.rings_amountPerCraft), 0));
        PluginLR.stacks.put("ringsx", new ItemStack(this, (PfFTuning.getInt(PfFTuning.Barrel.rings_amountPerCraft) * PfFTuning.getInt(PfFTuning.Barrel.rings_steelBonusMultiplier)), 0));
    }
}
