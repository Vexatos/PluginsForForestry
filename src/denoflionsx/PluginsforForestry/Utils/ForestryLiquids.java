package denoflionsx.PluginsforForestry.Utils;

import forestry.api.core.ItemInterface;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

public enum ForestryLiquids {
    
    BIOMASS("liquidBiomass"),
    BIOFUEL("liquidBiofuel"),
    SEEDOIL("liquidSeedOil"),
    APPLEJUICE("liquidJuice"),
   // SHORTMEAD("shortMead"),
    CRUSHEDICE("liquidIce"),
   // HONEY("liquidHoney;"),
    MILK("liquidMilk"),
    GLASS("liquidGlass");
    
    private ItemStack stack;

    private ForestryLiquids(String tag) {
        stack = ItemInterface.getItem(tag);
    }

    public ItemStack getStack() {
        return stack;
    }
    
    public LiquidStack getLiquidStack(){
        return new LiquidStack(stack.itemID,1000,stack.getItemDamage());
    }

}
