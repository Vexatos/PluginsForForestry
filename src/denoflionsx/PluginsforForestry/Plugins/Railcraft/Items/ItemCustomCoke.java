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
        for (fuels f : fuels.values()) {
            f.setStack(this.createItemEntry(f.ordinal(), PfF.Proxy.translate(f.getUnlocalized())));
        }
    }
    
    @Override
    public CreativeTabs getCreativeTab() {
        return PfFAPI.tab;
    }
    
    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        for (fuels f : fuels.values()) {
            this.icons.put(f.ordinal(), par1IconRegister.registerIcon(f.getTexture()));
        }
    }
    
    @Override
    public int getBurnTime(ItemStack fuel) {
        if (fuel.isItemEqual(fuels.sugar_charcoal.getStack())) {
            return 400;
        } else if (fuel.isItemEqual(fuels.cactus_charcoal.getStack())) {
            return 400;
        } else if (fuel.isItemEqual(fuels.sugar_coke.getStack())) {
            return 800;
        } else if (fuel.isItemEqual(fuels.cactus_coke.getStack())) {
            return 800;
        }
        return 0;
    }
    
    @Override
    public void registerRecipe() {
        ItemStack coalCoke = GameRegistry.findItemStack("Railcraft", "railcraft.fuel.coke", 1);
        LiquidStack s = LiquidDictionary.getLiquid("Creosote Oil", 30);
        int burn = (3000 / 4);
        OreDictionary.registerOre("itemCharcoalSugar", fuels.sugar_charcoal.getStack());
        OreDictionary.registerOre("itemCokeSugar", fuels.sugar_coke.getStack());
        OreDictionary.registerOre("itemCharcoalCactus", fuels.cactus_charcoal.getStack());
        OreDictionary.registerOre("itemCokeCactus", fuels.cactus_coke.getStack());
        Railcraft.registerCokeOvenRecipe(new ItemStack(Item.sugar), fuels.sugar_charcoal.getStack(), s, burn);
        Railcraft.registerCokeOvenRecipe(new ItemStack(Block.cactus), fuels.cactus_charcoal.getStack(), s, burn);
        for (ItemStack i : OreDictionary.getOres("itemCharcoalSugar")) {
            for (ItemStack i2 : OreDictionary.getOres("itemCokeSugar")) {
                Railcraft.registerCokeOvenRecipe(i, i2, s, burn);
                ItemStack[] grid = new ItemStack[8];
                for (int q = 0; q < grid.length; q++) {
                    grid[q] = i2;
                }
                if (coalCoke != null) {
                    PfF.Proxy.registerShapelessRecipe(coalCoke, grid);
                }
            }
        }
        for (ItemStack i : OreDictionary.getOres("itemCharcoalCactus")) {
            for (ItemStack i2 : OreDictionary.getOres("itemCokeCactus")) {
                Railcraft.registerCokeOvenRecipe(i, i2, s, burn);
                ItemStack[] grid = new ItemStack[8];
                for (int q = 0; q < grid.length; q++) {
                    grid[q] = i2;
                }
                if (coalCoke != null) {
                    PfF.Proxy.registerShapelessRecipe(coalCoke, grid);
                }
            }
        }
        GameRegistry.registerFuelHandler(this);
    }
    
    public static enum fuels {
        
        sugar_charcoal("item.pff.sugarcharcoal.name", "@NAME@:railcraft/sugar_charcoal"),
        sugar_coke("item.pff.sugarcoke.name", "@NAME@:railcraft/sugar_coke"),
        cactus_charcoal("item.pff.cactuscharcoal.name", "@NAME@:railcraft/cactus_charcoal"),
        cactus_coke("item.pff.cactuscoke.name", "@NAME@:railcraft/cactus_coke");
        private String unlocalized;
        private String texture;
        private ItemStack stack;
        
        private fuels(String unlocalized, String texture) {
            this.unlocalized = unlocalized;
            this.texture = texture;
        }
        
        public String getUnlocalized() {
            return unlocalized;
        }
        
        public String getTexture() {
            return texture;
        }
        
        public ItemStack getStack() {
            return stack;
        }
        
        public void setStack(ItemStack stack) {
            this.stack = stack;
        }
    }
}
