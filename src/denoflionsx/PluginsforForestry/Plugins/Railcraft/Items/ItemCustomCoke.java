package denoflionsx.PluginsforForestry.Plugins.Railcraft.Items;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import denoflionsx.PluginsforForestry.API.PfFAPI;
import denoflionsx.PluginsforForestry.Core.PfF;
import denoflionsx.PluginsforForestry.ModAPIWrappers.Railcraft;
import denoflionsx.PluginsforForestry.Recipe.IRegisterRecipe;
import denoflionsx.denLib.Mod.Items.ItemMeta;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemCustomCoke extends ItemMeta implements IFuelHandler, IRegisterRecipe {
    
    public ItemCustomCoke(int par1) {
        super(par1);
        RCItems.sugarCharcoal = this.createItemEntry(0, PfF.Proxy.translate("item.pff.sugarcharcoal.name"));
        RCItems.sugarCoke = this.createItemEntry(1, PfF.Proxy.translate("item.pff.sugarcoke.name"));
        RCItems.cactusCharcoal = this.createItemEntry(2, PfF.Proxy.translate("item.pff.cactuscharcoal.name"));
        RCItems.cactusCoke = this.createItemEntry(3, PfF.Proxy.translate("item.pff.cactuscoke.name"));
    }
    
    @Override
    public CreativeTabs getCreativeTab() {
        return PfFAPI.tab;
    }
    
    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        this.icons.put(0, par1IconRegister.registerIcon("@NAME@:railcraft/sugar_charcoal"));
        this.icons.put(1, par1IconRegister.registerIcon("@NAME@:railcraft/sugar_coke"));
        this.icons.put(2, par1IconRegister.registerIcon("@NAME@:railcraft/cactus_charcoal"));
        this.icons.put(3, par1IconRegister.registerIcon("@NAME@:railcraft/cactus_coke"));
    }
    
    @Override
    public int getBurnTime(ItemStack fuel) {
        if (fuel.isItemEqual(RCItems.sugarCharcoal)) {
            return 400;
        } else if (fuel.isItemEqual(RCItems.cactusCharcoal)) {
            return 400;
        } else if (fuel.isItemEqual(RCItems.sugarCoke)) {
            return 800;
        } else if (fuel.isItemEqual(RCItems.cactusCoke)) {
            return 800;
        }
        return 0;
    }
    
    @Override
    public void registerRecipe() {
        LiquidStack s = LiquidDictionary.getLiquid("Creosote Oil", 30);
        Railcraft.registerCokeOvenRecipe(new ItemStack(Item.sugar), RCItems.sugarCharcoal, s, (3000 / 4));
        Railcraft.registerCokeOvenRecipe(new ItemStack(Block.cactus), RCItems.cactusCharcoal, s, (3000 / 4));
        Railcraft.registerCokeOvenRecipe(RCItems.cactusCharcoal, RCItems.cactusCoke, s, (3000 / 4));
        Railcraft.registerCokeOvenRecipe(RCItems.sugarCharcoal, RCItems.sugarCoke, s, (3000 / 4));
        for (ItemStack i : OreDictionary.getOres("itemCharcoalSugar")) {
            Railcraft.registerCokeOvenRecipe(i, RCItems.sugarCoke, s, (3000 / 4));
        }
        OreDictionary.registerOre("itemCharcoalSugar", RCItems.sugarCharcoal);
        OreDictionary.registerOre("itemCokeSugar", RCItems.sugarCoke);
        OreDictionary.registerOre("itemCharcoalCactus", RCItems.cactusCharcoal);
        OreDictionary.registerOre("itemCokeCactus", RCItems.cactusCoke);
        GameRegistry.registerFuelHandler(this);
    }
}
